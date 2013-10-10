package com.lyw.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import com.lyw.io.ReadFile;
import com.lyw.io.WriteFile;
import com.lyw.net.CrawingWebContent;

/**
 * 爬去http://book.kanunu.org/ 内容
 * 
 * @author 梁煜文
 * 
 */
public class CrawingWebKanunu {

	private final static String savaPath = "D:/test/测试/";
	private final static String catalogFile = "catalog.txt";
	private final static String mTitleIndexStr = "<font color=\"#dc143c\">";
	private final static String mNextIndexStr = "<a href='";
	
	
	private String mBaseUrl = "http://book.kanunu.org/files/chinese/201104/2603/";
	private String mHomeUrl = "http://book.kanunu.org/files/chinese/201104/2603.html";
	private String mUrlItem = "62540.html";
	private String charSet = "gbk";
	private int fileName = 0;
	private boolean hasNextPage = true;

	public CrawingWebKanunu() {
	}

	private final void downloadPageCapter(String d, String c, String n) {
		List<String> content = CrawingWebContent.getWebContent(d, c);
		BufferedWriter writer = WriteFile.openFile(savaPath + n);
		boolean flag = false;
		try {
			for (String line : content) {
				if (line.contains("<p>"))
					flag = true;

				if (flag) {
					line = line.replace("<p>", "");
					line = line.replace("<br />", "");
					writer.append(line + '\n');
				}

				if (line.contains("</p>"))
					flag = false;
				
				if(line.contains(mTitleIndexStr)) {
					int start = line.indexOf(mTitleIndexStr)
							+ mTitleIndexStr.length();
					int end = line.indexOf("</font>");
					String str = new ReadFile().read(savaPath + catalogFile);
					BufferedWriter titlWriter = WriteFile.openFile(savaPath + catalogFile);
					titlWriter.append(str +
							line.substring(start, end).replaceAll(" ", ""));
					WriteFile.closeFile(titlWriter);
				}
				
				if (line.contains("下一页")) {
					int start = line.indexOf(mNextIndexStr)
							+ mNextIndexStr.length();
					int end = line.indexOf("'>");
					
					this.mUrlItem = line.substring(start, end);
					this.hasNextPage = hasNextPage(mUrlItem);
					this.fileName++;
					
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WriteFile.closeFile(writer);
	}

	private final boolean hasNextPage(String str) {
		if (str == null || str.startsWith("../"))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		CrawingWebKanunu mCrawingWeb = new CrawingWebKanunu();

		while (mCrawingWeb.hasNextPage) {
			mCrawingWeb.downloadPageCapter(mCrawingWeb.mBaseUrl + mCrawingWeb.mUrlItem,
					mCrawingWeb.charSet, mCrawingWeb.fileName + ".txt");
		}
		
	}
}
