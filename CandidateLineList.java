package com.guobi.wgim.utils.view;

public class CandidateLineList<T> {
	private DNode current;
	private DNode last;
	private DNode head;

	private boolean isFirstStart;

	public CandidateLineList() {
		current = new DNode(null);
		last = current;
		head = current;
		isFirstStart = true;
	}

	public void add(T element) {
		current = last;
		DNode nextNode = new DNode(element);
		current.setNextNode(nextNode);
		nextNode.setPreNode(current);
		current = current.getNextNode();
		last = current;
	}

	public void addAndMoveToFirst(T element) {
		current = last;
		current.setNextNode(new DNode(element));
		last = current.getNextNode();
		current = head;
	}

	public void moveToNext() {
		current = current.getNextNode();
	}

	public void moveToPre() {
		current = current.getPreNode();
	}

	public void moveToFirst() {
		current = head;
	}

	public boolean hasNext() {
		if (current.getNextNode() != null) {
			return true;
		}
		return false;
	}

	public boolean hasPre() {
		if (current.getPreNode() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 取下一节点元素
	 * 
	 * @return
	 */
	public T getNext() {
		if (hasNext()) {
			return current.getNextNode().getElement();
		}
		return null;
	}

	/**
	 * 取上一节点元素
	 * 
	 * @return
	 */
	public T getPre() {
		if (hasPre()) {
			return current.getPreNode().getElement();
		}
		return null;
	}

	public T getCur() {
		return current.getElement();
	}

	private class DNode {
		private DNode nextNode;
		private DNode preNode;
		private T element;

		public DNode(T element) {
			this.element = element;
		}

		public DNode getNextNode() {
			return nextNode;
		}

		public void setNextNode(DNode nextNode) {
			this.nextNode = nextNode;
		}

		public DNode getPreNode() {
			return preNode;
		}

		public void setPreNode(DNode preNode) {
			this.preNode = preNode;
		}

		public T getElement() {
			return element;
		}

		public void setElement(T element) {
			this.element = element;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return element + "";
		}
	}
}
