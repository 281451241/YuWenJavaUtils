package com.lyw.util;


public class CandidateLineList<T> {
	private DNode current;
	private DNode last;
	private DNode head;
	private int dNodeSize;

	public CandidateLineList() {
		current = new DNode(null);
		last = current;
		head = current;
		dNodeSize = 0;
	}

	public void add(T element) {
		current = last;
		DNode nextNode = new DNode(element);
		current.nextNode = nextNode;
		nextNode.preNode = current;
		current = current.nextNode;
		last = current;
		dNodeSize++;
	}

	public void addAndMoveToFirst(T element) {
		current = last;
		current.nextNode = new DNode(element);
		last = current.nextNode;
		current = head;
		dNodeSize++;
	}

	public void moveToNext() {
		current = current.nextNode;
	}

	public void moveToPre() {
		current = current.preNode;
	}

	public void moveToFirst() {
		current = head;
	}

	public boolean hasNext() {
		if (current.nextNode != null) {
			return true;
		}
		return false;
	}

	public boolean hasPre() {
		if (current.preNode != null) {
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
			return current.nextNode.element;
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
			return current.preNode.element;
		}
		return null;
	}

	public T getCur() {
		return current.element;
	}
	
	public int size() {
		return dNodeSize;
	}

	private class DNode {
		public DNode nextNode;
		public DNode preNode;
		public T element;

		public DNode(T element) {
			this.element = element;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return element + "";
		}
	}
}
