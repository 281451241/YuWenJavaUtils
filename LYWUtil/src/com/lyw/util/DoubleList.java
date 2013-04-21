package com.lyw.util;


/**
 * 双向链表
 * @author LYW
 *
 * @param <T>
 */
public class DoubleList<T> {

	private DNode head;
	private DNode last;
	private DNode current;

	public DoubleList() {
		this.current = new DNode(null);
		head = current;
		last = current;
	}

	public void add(T element) {
		current = last;
		current.setNext(new DNode(element));
		last = current.getNext();
		current = head;
	}

	public boolean hasNext() {
		if (current.getNext() != null) {
			return true;
		}
		return false;
	}

	public boolean hasPre() {
		if (current.getPre() != null) {
			return true;
		}
		return false;
	}

	public T getNext() {
		if (hasNext()) {
			return current.getNext().getElement();
		}
		return null;
	}

	public T getPre() {
		if (hasPre()) {
			return current.getPre().getElement();
		}
		return null;
	}

	private class DNode {
		private DNode next;
		private DNode pre;
		private T element;

		public DNode(T element) {
			this.element = element;
		}

		public DNode getNext() {
			return next;
		}

		public void setNext(DNode next) {
			this.next = next;
		}

		public DNode getPre() {
			return pre;
		}

		public void setPre(DNode pre) {
			this.pre = pre;
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
	
	public static void main(String[] args) {
		DoubleList<Integer> doubleList = new DoubleList<Integer>();
		for (int i = 0; i < 10; i++) {
			doubleList.add(i);
		}
		System.out.println(doubleList.hasNext());
		System.out.println(doubleList.getNext());
	}
	
}
