package com.guobi.wgim.utils.view;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.guobi.gbime.engine.GBIMECandidatePage;
import com.guobi.gbime.engine.GBIMECandidatePageManager;
import com.guobi.gbime.engine.GBIMECandidatePagingCallback;
import com.guobi.gfc.GBMiscUtils.log.GBLogUtils;
import com.guobi.gfc.GBMiscUtils.text.GBTextUtils;
import com.guobi.gfc.GBMiscUtils.view.GBViewUtils;
import com.guobi.gfc.WGTheme.WGThemeDrawableAbstract;
import com.guobi.wgim.framework.lolila.view.WGIMHandwritingCanceller;
import com.guobi.wgim.utils.visual.button.WGIMButton;
import com.guobi.wgim.utils.visual.button.WGIMStateColorSet;
import com.guobi.wgim.utils.visual.button.WGIMStateIconSet;
import com.guobi.wgim.utils.visual.key.WGIMCandidateItem;
import com.guobi.wgim.utils.visual.key.WGIMKey;
import com.guobi.wgim.utils.visual.key.WGIMOnCandidateItemClickListener;
import com.guobi.wgim.utils.visual.key.WGIMOnCandidateItemLongClickListener;

public class LYWFullScreenCandidateView extends FrameLayout {

	public LYWFullScreenCandidateView(Context context) {
		super(context);

		setBackgroundColor(Color.WHITE);
		singleLineCandidates = new MultiLineCandidates(context, null);
		topBlur = new BlurView(context, "top");
		bottomBlur = new BlurView(context, "bottom");

		addView(topBlur);
		addView(bottomBlur);
		addView(singleLineCandidates);

	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(mWidth, mHeight);

		measureChildren(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		View childLeftBlur = getChildAt(0);
		childLeftBlur.layout(0, 0, mWidth, mBlurWidth);

		View childRightBlur = getChildAt(1);
		childRightBlur.layout(0, mHeight - mBlurWidth, mWidth, mHeight);

		View childSingleLineCandidates = getChildAt(2);
		childSingleLineCandidates.layout(0, 0, mWidth, mHeight);
	}

	/**
	 * 设置分页管理器
	 * 
	 * @param pageMgr
	 */
	public final void setPageManager(GBIMECandidatePageManager pageMgr) {
		if (pageMgr != null) {
			mPageMgr = pageMgr;
			mPageMgr.setPageWidth(getMaxFreeWidth());
			mPageMgr.setMaxLinePerPage(4);
			mPageMgr.setWordSpace(mItemSeparatorWidth);
			mPageMgr.setPagingCallback(myGBIMECandidatePagingCallback, null);
			reload();
		}
	}

	/**
	 * 重新加载第一页候选
	 */
	public final void reload() {
		if (mPageMgr != null) {
			GBIMECandidatePage g = null;
			mPageMgr.clear();
			mPageMgr.reload();
			mTotalPage = 0;
			rowNum = 0;
			mCurKeyList.clear();

			mCandidateLineList = new CandidateLineList<LinkedList<WGIMKey>>();

			g = mPageMgr.moveToFirstPage();
			if (g != null && g.getLineCount() > 0) {
				appendCandidates(g);
				singleLineCandidates.setToScreen(0);
			}
		}
		singleLineCandidates.showBlur();
	}

	public GBIMECandidatePageManager getPageManager() {
		if (mPageMgr != null)
			return mPageMgr;

		return null;
	}

	public void setNextPage() {
		if (mCurPageIdx + 1 < mTotalPage) {
			mCandidateLineList.moveToNext();
			singleLineCandidates.setToScreen(mCurPageIdx + 1); // 向右滑动
		}
	}

	public void setPrevPage() {
		if (mCurPageIdx > 0) {
			mCandidateLineList.moveToPre();
			singleLineCandidates.setToScreen(mCurPageIdx - 1); // 向上滑动
		}
	}

	/**
	 * 获取最大可容纳候选项目的宽度
	 * 
	 * @return
	 */
	private final int getMaxFreeWidth() {
		final int contentWidth = notLessThenZero(mWidth - 2
				* mHorContentPadding);
		return contentWidth;
	}

	/**
	 * 根据给定的候选字符串，计算其对应的最小候选项目宽度
	 * 
	 * @param candStr
	 * @return
	 */
	private final int getMinCandItemWidth(String candStr) {
		if (candStr == null || candStr.length() <= 0)
			return 0;
		Rect rcText = GBTextUtils.getTextBounds(candStr, mCandItemFontSize,
				null);
		return mCandItemHorContentPadding * 2 + rcText.width();
	}

	/**
	 * 获取当前页面中指定索引的候选
	 * 
	 * @param idx
	 * @return
	 */
	public final WGIMCandidateItem getItemInPage(int idx) {
		if (idx >= 0 && mCurKeyList != null && idx < mCurKeyList.size()) {
			return (WGIMCandidateItem) mCurKeyList.get(idx);
		}
		return null;
	}

	/**
	 * 
	 * @param page
	 */
	public final void appendCandidates(GBIMECandidatePage page) {
		int rowNum = page.getLineCount();
		int firstMinWidth = mWidth;
		int lastMinWidth = mWidth;
		// 新加载页的KeyList
		LinkedList<WGIMKey> mCurPageItemList = new LinkedList<WGIMKey>();
		// 记录当前页每一行的候选词数
		CandidateLineList<Integer> mCurLineItemSizeList = new CandidateLineList<Integer>();

		CandidateLineList<Integer> perLineFreeWidth = new CandidateLineList<Integer>();
		// 内容区尺寸
		final int contentHeight = notLessThenZero(mHeight / 4 - 2
				* mVerContentPadding);

		if (GBLogUtils.DEBUG)
			GBLogUtils.DEBUG_DISPLAY(this, "Append Candidates Start..");

		if (mEnableTileAlign) {
			for (int j = 0; j < rowNum; j++) {
				final int candCount = page.getCandCountInLine(j);
				mCurLineItemSizeList.addAndMoveToFirst(candCount);

				int addOnFreeWidthPerItem = 0;

				// 统计所有候选项目所占用的最小宽度
				int curFreeWidth = getMaxFreeWidth();
				if (rowNum == 1 && candCount < 4) {
					firstMinWidth = 0;
				} else {

					// 减去所有Item之间的空隙
					curFreeWidth = notLessThenZero(curFreeWidth
							- (candCount - 1) * mItemSeparatorWidth);

					// 减去所有字符的宽度
					for (int i = 0; i < candCount; ++i) {
						curFreeWidth = notLessThenZero(curFreeWidth
								- getMinCandItemWidth(page
										.getCandStrByIdx(j, i)));
					}

					// 平分剩余宽度，即分散对齐
					if (curFreeWidth > 0) {
						addOnFreeWidthPerItem = curFreeWidth / candCount;
					}

					// 记录四行中,平分后的最小剩余宽度
					firstMinWidth = addOnFreeWidthPerItem > firstMinWidth ? firstMinWidth
							: addOnFreeWidthPerItem;
				}
				// 记录每行减去所有Item之间的空隙和所有字符的宽度后的剩余宽度
				perLineFreeWidth.addAndMoveToFirst(curFreeWidth);
			}
		}

		for (int j = 0; j < rowNum; j++) {
			int curFreeWidth = 0;
			int addOnFreeWidthPerItem = 0;
			final int candCount = page.getCandCountInLine(j);

			// 计算每行扣除第一个候选的剩余宽度
			if (perLineFreeWidth.hasNext()) {
				perLineFreeWidth.moveToNext();
				curFreeWidth = perLineFreeWidth.getCur() - firstMinWidth;
			}

			if (curFreeWidth > 0 && candCount != 1) {
				addOnFreeWidthPerItem = curFreeWidth / (candCount - 1);
			}

			// 记录四行中,平分后的最小剩余宽度
			lastMinWidth = addOnFreeWidthPerItem > lastMinWidth ? lastMinWidth
					: addOnFreeWidthPerItem;
		}

		perLineFreeWidth.moveToFirst();

		for (int j = 0; j < rowNum; j++) {
			int curFreeWidth = 0;
			int addOnModIdx = 0;
			int addOnFreeWidthPerItem = 0;
			// 创建每个候选
			WGIMCandidateItem baseKey = null;
			final int candCount = page.getCandCountInLine(j);
			if (perLineFreeWidth.hasNext()) {
				perLineFreeWidth.moveToNext();
				curFreeWidth = perLineFreeWidth.getCur();
			}

			if (candCount > 2) {
				curFreeWidth = curFreeWidth - firstMinWidth - lastMinWidth;
			}

			if (curFreeWidth > 0) {
				// 最后一页最后一行
				if (!mPageMgr.hasNextPage() && j == rowNum - 1) {
					if (candCount < 4) {
						addOnFreeWidthPerItem = firstMinWidth;
						addOnModIdx = curFreeWidth % candCount;
					} else {
						addOnFreeWidthPerItem = curFreeWidth / (candCount - 2);
						addOnModIdx = curFreeWidth % (candCount - 2);
					}
				} else {
					if (candCount > 2) {
						addOnFreeWidthPerItem = curFreeWidth / (candCount - 2);
						addOnModIdx = curFreeWidth % (candCount - 2);
					} else {
						addOnFreeWidthPerItem = perLineFreeWidth.getCur()
								- firstMinWidth;
						addOnModIdx = curFreeWidth % candCount;
					}
				}
			}

			for (int i = 0; i < candCount; ++i) {
				WGIMCandidateItem item = new WGIMCandidateItem(
						mCandItemBgIconSet, baseKey, page.getCandByIdx(j, i),
						j, i);

				if (GBLogUtils.DEBUG)
					GBLogUtils.DEBUG_DISPLAY(this,
							"WGIMCandidateItem: " + item.getText());

				item.setHorContentPadding(mCandItemHorContentPadding);
				item.setVerContentPadding(mCandItemVerContentPadding);
				item.setTextFontSize(mCandItemFontSize);
				item.setTextColorSet(mCandItemTextColorSet);
				item.setTextPaint(mCandItemTextPaint);

				mCurPageItemList.add(item);

				// 最后一页最后一行,且候选个数小于4
				if (!mPageMgr.hasNextPage() && j == rowNum - 1 && candCount < 4) {
					item.measure(
							getMinCandItemWidth(page.getCandStrByIdx(j, i))
									+ addOnFreeWidthPerItem
									+ ((i < addOnModIdx) ? 1 : 0),
							contentHeight);
				} else {
					// 第一个用四行中平分后的最小剩余宽度,其余的,扣掉第一个后的剩余宽度
					if (i == 0) {
						item.measure(
								getMinCandItemWidth(page.getCandStrByIdx(j, i))
										+ firstMinWidth
										+ ((i < addOnModIdx) ? 1 : 0),
								contentHeight);

						// 最后一个
					} else if (candCount > 2 && i == candCount - 1) {
						item.measure(
								getMinCandItemWidth(page.getCandStrByIdx(j, i))
										+ lastMinWidth
										+ ((i < addOnModIdx) ? 1 : 0),
								contentHeight);
					} else {
						item.measure(
								getMinCandItemWidth(page.getCandStrByIdx(j, i))
										+ addOnFreeWidthPerItem
										+ ((i < addOnModIdx) ? 1 : 0),
								contentHeight);
					}
				}

				if (baseKey == null)
					baseKey = item;
			}
		}
		mCandidateLineList.add(mCurPageItemList);

		if (GBLogUtils.DEBUG)
			GBLogUtils.DEBUG_DISPLAY(this, "Append Candidates end..");

		// 排布所有按键
		int keyLeft = mHorContentPadding;
		int keyTop = mVerContentPadding + mTotalPage * mHeight;
		int j = 0;

		for (WGIMKey key : mCurPageItemList) {
			j++;
			key.layout(keyLeft, keyTop);
			if (mCurLineItemSizeList.hasNext()) {
				if (j == mCurLineItemSizeList.getNext()) {
					keyLeft = mHorContentPadding;
					keyTop += mHeight / 4;
					mCurLineItemSizeList.moveToNext();
					j = 0;
				} else {
					keyLeft += key.getWidth() + mItemSeparatorWidth;
				}
			}
		}
		mTotalPage++; // 更新总页数
	}

	/**
	 * 
	 * @param listener
	 */
	public final void setOnItemClickedListener(
			WGIMOnCandidateItemClickListener listener) {
		mOnItemClickedListener = listener;
	}

	/**
	 * 
	 * @param listener
	 */
	public final void setOnItemLongClickedListener(
			WGIMOnCandidateItemLongClickListener listener) {
		mOnItemLongClickedListener = listener;
	}

	/**
	 * 
	 * @param width
	 */
	public final void setWidth(int width) {
		mWidth = notLessThenZero(width);
	}

	/**
	 * 
	 * @param height
	 */
	public final void setHeight(int height) {
		mHeight = notLessThenZero(height);
	}

	/**
	 * 
	 * @param padding
	 */
	public final void setHorContentPadding(int padding) {
		mHorContentPadding = notLessThenZero(padding);
	}

	/**
	 * 
	 * @param padding
	 */
	public final void setVerContentPadding(int padding) {
		mVerContentPadding = notLessThenZero(padding);
	}

	/**
	 * 设置项目分隔条宽度，0表示不使用分隔条
	 * 
	 * @param width
	 */
	public final void setItemSeparatorWidth(int width) {
		mItemSeparatorWidth = notLessThenZero(width);
	}

	/**
	 * 设置控件分隔条高度，0表示不使用
	 * 
	 * @param height
	 */
	public final void setCtrlSeparatorHeight(int height) {
		mCtrlSeparatorHeight = notLessThenZero(height);
	}

	/**
	 * 设置候选项目按键背景
	 * 
	 * @param iconSet
	 */
	public final void setCandItemBgIconSet(WGIMStateIconSet iconSet) {
		mCandItemBgIconSet = iconSet;
	}

	/**
	 * 设置候选项目文字颜色
	 * 
	 * @param colorSet
	 */
	public final void setCandItemTextColorSet(WGIMStateColorSet colorSet) {
		mCandItemTextColorSet = colorSet;
	}

	/**
	 * 设置候选项目内容水平衬垫
	 * 
	 * @param padding
	 */
	public final void setCandItemHorContentPadding(int padding) {
		mCandItemHorContentPadding = notLessThenZero(padding);
	}

	/**
	 * 设置候选项目内容垂直衬垫
	 * 
	 * @param padding
	 */
	public final void setCandItemVerContentPadding(int padding) {
		mCandItemVerContentPadding = notLessThenZero(padding);
	}

	/**
	 * 设置候选项目文本大小
	 * 
	 * @param fontSize
	 */
	public final void setCandItemFontSize(float fontSize) {
		mCandItemFontSize = notLessThenZero(fontSize);
	}

	/**
	 * 设置候选项目画笔
	 * 
	 * @param textPaint
	 */
	public final void setCandItemTextPaint(Paint textPaint) {
		mCandItemTextPaint = null;
		mCandItemTextPaint = textPaint;
	}

	/**
	 * 设置项目分隔条图案
	 * 
	 * @param sp
	 */
	public final void setItemSeparatorDrawable(WGThemeDrawableAbstract sp) {
		mItemSeparatorDrawable = null;
		mItemSeparatorDrawable = sp;
	}

	/**
	 * 设置控件分隔条图案
	 * 
	 * @param sp
	 */
	public final void setCtrlSeparatorDrawable(WGThemeDrawableAbstract sp) {
		mCtrlSeparatorDrawable = null;
		mCtrlSeparatorDrawable = sp;
	}

	/**
	 * 设置是否允许绘制水平分隔条
	 * 
	 * @param on
	 */
	public final void enableDrawCtrlSeparator(boolean on) {
		mEnableDrawCtrlSeparator = on;
	}


	private final int notLessThenZero(int val) {
		return (val < 0) ? 0 : val;
	}

	private final float notLessThenZero(float val) {
		return (val < 0.0f) ? 0.0f : val;
	}

	// ////////////////////////////////////////////////////////////////////
	// Implements other interfaces
	// ////////////////////////////////////////////////////////////////////

	private GBIMECandidatePagingCallback myGBIMECandidatePagingCallback = new GBIMECandidatePagingCallback() {

		@Override
		public int onQueryStringWidth(String str, Object param) {
			// TODO Auto-generated method stub
			return getMinCandItemWidth(str);
		}
	};

	// ////////////////////////////////////////////////////////////////////
	// Private Members
	// ////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	private WGIMOnCandidateItemClickListener mOnItemClickedListener = null;
	/**
	 * 
	 */
	private WGIMOnCandidateItemLongClickListener mOnItemLongClickedListener = null;
	/**
	 * 
	 */
	private int mWidth = 0;
	/**
	 * 
	 */
	private int mHeight = 0;
	/**
	 * 
	 */
	private int mPageKeyWidth = 0;
	/**
	 * 
	 */
	private int mItemSeparatorWidth = 10;
	/**
	 * 
	 */
	private int mCtrlSeparatorHeight = 0;
	/**
	 * 
	 */
	private WGThemeDrawableAbstract mItemSeparatorDrawable = null;
	/**
	 * 
	 */
	private WGThemeDrawableAbstract mCtrlSeparatorDrawable = null;
	/**
	 * 
	 */
	private WGIMStateIconSet mCandItemBgIconSet = null;
	/**
	 * 
	 */
	private WGIMStateColorSet mCandItemTextColorSet = null;
	/**
	 * 
	 */
	private int mCandItemHorContentPadding = 0;
	/**
	 * 
	 */
	private int mCandItemVerContentPadding = 0;
	/**
	 * 
	 */
	private int mHorContentPadding = 0;
	/**
	 * 
	 */
	private int mVerContentPadding = 0;
	/**
	 * 
	 */
	private float mCandItemFontSize = 0.0f;
	/**
	 * 
	 */
	private Paint mCandItemTextPaint = null;
	/**
	 * 
	 */
	private boolean mEnableDrawCtrlSeparator = false;
	/**
	 * 
	 */
	private GBIMECandidatePageManager mPageMgr = null;
	/**
	 * 
	 */
	private MultiLineCandidates singleLineCandidates = null;
	/**
	 * 当前参与工作按键序列，包括翻页按键和候选项目
	 */
	private LinkedList<WGIMKey> mCurKeyList = new LinkedList<WGIMKey>();
	/**
	 * 当前页的总行数
	 */
	private int rowNum = 0;
	/**
	 * 是否开启分散对齐
	 */
	private boolean mEnableTileAlign = true;
	/**
	 * 记录当前显示页
	 */
	private int mCurPageIdx = 0;
	/**
	 * 模糊块宽度
	 */
	private int mBlurWidth = 20;
	/**
	 * 左侧模糊块
	 */
	private BlurView topBlur = null;
	/**
	 * 右侧模糊块
	 */
	private BlurView bottomBlur = null;
	/**
	 * 已加载的页序列,记录每一页
	 */
	private CandidateLineList<LinkedList<WGIMKey>> mCandidateLineList;
	/**
	 * 当前已载入的总页数
	 */
	private int mTotalPage = 0;

	private class MultiLineCandidates extends WGIMButtonContainerView {

		/**
		 * 
		 * @param context
		 */
		public MultiLineCandidates(Context context,
				WGIMHandwritingCanceller hwCanceller) {
			super(context, hwCanceller);

			mScroller = new Scroller(context);
			mCurPageIdx = 0;
		}

		// ////////////////////////////////////////////////////////////////////
		//
		// ////////////////////////////////////////////////////////////////////

		@Override
		protected final WGIMButton findBingoButton(int ptX, int ptY) {
			boolean flag = false;
			for (WGIMKey key : mCurKeyList) {
				flag = key.getBoundsRect().contains(ptX,
						ptY + mCurPageIdx * mHeight);
				if (flag) {
					return key;
				}
			}
			return null;
		}

		@Override
		protected final void onButtonClicked(WGIMButton lastUpKey) {
			if (lastUpKey instanceof WGIMCandidateItem) {
				if (mOnItemClickedListener != null) {
					mOnItemClickedListener
							.onItemClicked((WGIMCandidateItem) lastUpKey);
				}
			}
		}

		@Override
		protected boolean onButtonLongClicked(WGIMButton lastUpKey) {
			if (mOnItemLongClickedListener != null) {
				return mOnItemLongClickedListener
						.onItemLongClicked((WGIMCandidateItem) lastUpKey);
			}
			return false;
		}

		// ////////////////////////////////////////////////////////////////////
		// Override methods from super class
		// ////////////////////////////////////////////////////////////////////

		@Override
		protected boolean onFling(MotionEvent e1, MotionEvent e2,
				float velocityX, float velocityY) {
			if (mVelocityTracker == null) {
				mVelocityTracker = VelocityTracker.obtain();
			}
			mVelocityTracker.computeCurrentVelocity(1000);

			if (velocityY > mSnapVelocity && mCurPageIdx > 0) {

				if (GBLogUtils.DEBUG)
					GBLogUtils.DEBUG_DISPLAY(this, "fling up");

				mCandidateLineList.moveToPre();
				setToScreen(mCurPageIdx - 1); // 向上滑动
				return true;
			} else if (velocityY < -mSnapVelocity
					&& mCurPageIdx + 1 < mTotalPage) {

				if (GBLogUtils.DEBUG)
					GBLogUtils.DEBUG_DISPLAY(this, "fling buttom");

				mCandidateLineList.moveToNext();
				setToScreen(mCurPageIdx + 1); // 向右滑动
				return true;
			}

			if (mVelocityTracker != null) {
				mVelocityTracker.recycle();
				mVelocityTracker = null;
			}
			return super.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public void computeScroll() {
			if (GBLogUtils.DEBUG)
				GBLogUtils.DEBUG_DISPLAY(this, "computeScroll");

			if (mScroller.computeScrollOffset()) {
				scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
				postInvalidate();
			}
		}

		@Override
		protected final void onMeasure(int widthMeasureSpec,
				int heightMeasureSpec) {
			// TODO Auto-generated method stub

			if (GBLogUtils.DEBUG) {
				GBLogUtils.DEBUG_DISPLAY(this, "onMeasure begin...");
				GBViewUtils.dumpMeasureSpec("widthMeasureSpec",
						widthMeasureSpec);
				GBViewUtils.dumpMeasureSpec("heightMeasureSpec",
						heightMeasureSpec);
			}

			setMeasuredDimension(mWidth, mHeight);

			if (GBLogUtils.DEBUG) {
				GBLogUtils.DEBUG_DISPLAY(this, "onMeasure end."
						+ "measured width: " + getMeasuredWidth()
						+ ",measured height: " + getMeasuredHeight());
			}
		}

		@Override
		protected final void onDraw(Canvas canvas) {
			if (GBLogUtils.DEBUG)
				GBLogUtils.DEBUG_DISPLAY(this, "onDraw Begin...");

			// 绘制当前页项目
			boolean isFirstKey = true;
			int keySpLeft = 0;
			int keySpTop = mVerContentPadding + mCurPageIdx * mHeight;
			final int keySpHeight = notLessThenZero(mHeight / 4 - 2
					* mVerContentPadding);
			for (WGIMKey key : mCurKeyList) {
				if (isFirstKey) {
					isFirstKey = false;
					keySpLeft = key.getLeft();
				} else {
					if (keySpLeft > getMaxFreeWidth()) {
						keySpLeft = mHorContentPadding;
					} else {
						drawSp(canvas, mItemSeparatorDrawable, keySpLeft,
								keySpTop, mItemSeparatorWidth, keySpHeight);
						keySpLeft += mItemSeparatorWidth;
					}
				}
				key.draw(canvas);
				keySpLeft += key.getWidth();
			}
			// 绘制上下分隔线
			if (mCtrlSeparatorHeight > 0 && mCtrlSeparatorDrawable != null) {
				if (mEnableDrawCtrlSeparator) {
					for (int i = 0; i < 4; i++) {
						if (i % 4 == 0)
							continue;
						drawSp(canvas, mCtrlSeparatorDrawable, 0, mHeight * i
								/ 4 + (mCurPageIdx) * mHeight
								- mCtrlSeparatorHeight / 2, mWidth,
								mCtrlSeparatorHeight);
					}
				}
			}

			if (GBLogUtils.DEBUG)
				GBLogUtils.DEBUG_DISPLAY(this, "onDraw End.");
		}

		/**
		 * 转到指定页
		 * 
		 * @param whichScreen
		 */
		private void setToScreen(int whichScreen) {
			final int delta;
			int duration = 200; // 动画完成毫秒数

			mCurPageIdx = whichScreen;
			delta = mCurPageIdx * mHeight - getScrollY();

			mCurKeyList = mCandidateLineList.getCur();
			if (GBLogUtils.DEBUG)
				GBLogUtils.DEBUG_DISPLAY(this, "startScroll");

			mScroller.startScroll(0, getScrollY(), 0, delta, duration);
			invalidate(); // Redraw the layout

			if (mCurPageIdx + 1 >= mTotalPage
					&& LYWFullScreenCandidateView.this.mPageMgr
							.moveToNextPage() != null) {
				appendCandidates(LYWFullScreenCandidateView.this.mPageMgr
						.getCurPage());
				mCandidateLineList.moveToPre();
			}
			showBlur();
		}

		/**
		 * 显示左右两端的模糊块
		 */
		private void showBlur() {
			if (mCurPageIdx != 0) {
				topBlur.setVisibility(View.VISIBLE);
			} else {
				topBlur.setVisibility(View.INVISIBLE);
			}
			if (mTotalPage != 0 && mCurPageIdx + 1 < mTotalPage) {
				bottomBlur.setVisibility(View.VISIBLE);
			} else {
				bottomBlur.setVisibility(View.INVISIBLE);
			}
		}

		// ////////////////////////////////////////////////////////////////////
		// Private Helpers
		// ////////////////////////////////////////////////////////////////////

		private final void drawSp(Canvas canvas, WGThemeDrawableAbstract sp,
				int l, int t, int w, int h) {
			if (sp == null || sp.isNull())
				return;
			if (w <= 0 && h <= 0)
				return;
			Drawable drawable = sp.get();
			if (drawable != null) {
				drawable.setBounds(0, 0, w, h);
				canvas.translate(l, t);
				drawable.draw(canvas);
				canvas.translate(0 - l, 0 - t);
			}
		}

		/**
		 * 触发onfling事件的滑动速度
		 */
		private int mSnapVelocity = 600;
		/**
		 * 滑动速度跟踪器
		 */
		private VelocityTracker mVelocityTracker = null;
		/**
		 * 滚动块
		 */
		private Scroller mScroller = null;
	}

	public class BlurView extends View {

		public BlurView(Context context, String direction) {
			super(context);

			paint = new Paint();
			mDirection = direction;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Shader mShader;

			int startY = 0;
			int endY = 0;
			int mColor[] = null;

			canvas.drawColor(Color.TRANSPARENT);
			paint.setAntiAlias(true);

			if (mDirection.equals("top")) {
				startY = -30;
				endY = 20;
				mColor = new int[] { Color.BLACK, Color.TRANSPARENT };
			} else if (mDirection.equals("bottom")) {
				startY = 0;
				endY = 50;
				mColor = new int[] { Color.TRANSPARENT, Color.BLACK };
			} else {
				return;
			}
			mShader = new LinearGradient(0, startY, 0, endY, mColor, null,
					Shader.TileMode.REPEAT);

			paint.setShader(mShader);

			canvas.drawRect(0, 0, mWidth, mBlurWidth, paint);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			setMeasuredDimension(mWidth, mHeight);
		}

		/**
		 * 画笔
		 */
		private Paint paint;
		/**
		 * 渐变效果的方向,允许为"left"和"right"
		 */
		private String mDirection = "";
	}
}
