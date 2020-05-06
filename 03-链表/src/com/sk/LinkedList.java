package com.sk;

/*
 * 双向链表
 * */
public class LinkedList <E> extends AbstractList<E>{
	 private Node <E> first;
	 private Node<E>  last;
	 
	 private static class Node<E> {
		 E element;
		 Node<E> prev;
		 Node<E> next;

		 public Node(Node<E> prev,E element, Node<E> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		 }
		 @Override
		 public String toString() {
			
			StringBuilder sb = new StringBuilder();
			if (prev != null) {
				sb.append(prev.element);
			} else {
				sb.append("null");
			}
			sb.append("_").append(element).append("_");
			
			if (next != null) {
				sb.append(next.element);
			} else {
				sb.append("null");
			}
			return sb.toString();
		}
	 }

	
	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E oldE = node.element;
		node.element = element;
		return oldE;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		if (index == size) {//往最后面添加元素
			Node<E> oldLast  = last;
			last = new Node<> (oldLast, element, null);
			if (oldLast == null) {//这里添加的是第一个元素
				first = last;
			}else {
				oldLast.next = last;
			}
		}else {
			Node<E> next = node(index);
			Node<E> prev = next.prev;
			Node<E> node = new Node<>(prev, element, next);
			next.prev = node;
			
			if (prev == null) { //index = 0
				first = node;
			} else {
				prev.next = node;
			}
		}
//		if (index == 0) {
//			first = new Node<E>(element, first);
//		}
//		else {
//			Node<E> prev = node(index - 1);//首先通过index获取当前要添加节点的前一个节点
//			prev.next =  new Node<E>(element, prev.next); //让前一个节点的next指针指向当前新添加的节点
//			//并且让当前新添加节点的next指针,指向前一个节点的所指的next节点
//		} 
		size ++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> node = node(index);
		Node<E> prev = node.prev;
		Node<E> next = node.next;
		
		if (prev == null) {//index = 0
			first = next;
		}else {
			prev.next = next;
		}
		
		if (next == null) {//index = size -1
			last = prev;
		}else {
			next.prev = prev;
		}
		
//		Node<E> node = first;
//		if (index == 0) {
//			first = first.next;
//		} else {
//			Node<E> prev = node(index - 1);//首先通过index获取当前要添加节点的前一个节点
//			node = prev.next; //这里node保留了prev.next的节点，即使当prev.next发生改变的时候，node也不会改变，
////			System.out.println(prev.next);
////			System.out.println(node);
//			prev.next = node.next;
////			System.out.println(prev.next);
////			System.out.println(node);
//		}
		size --;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		if (element == null) {  
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element == null) return i; 
				node = node.next;
			}
		} else {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) return i; 
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	/*
	 * 获取index位置对应的节点对象
	 * @param index
	 * @return
	 * */
    
	private Node<E> node(int index) {
		rangeCheck(index);
		
		if (index < (size >> 1)) {
			Node<E> node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		}else {
			Node<E> node = last;
			for (int i = size - 1; i > index; i --) {
				node = node.prev;
			}
			return node;
		}
		
		
	}
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(node.element);
			node = node.next;
		}
		string.append("]");
		return string.toString();
	}
}
