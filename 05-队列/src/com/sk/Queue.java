package com.sk;

//普通队列
public class Queue<E> {
	private List<E> list = new LinkedList<>();
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void clear() {
		list.clear();
	}
	
	//入队
	public void enQueue(E element) {
		list.add(element);
	}
	
	//出队
	public E  deQueue() {
		return list.remove(0);
	}
	
	//获得队头元素
	public E  front() {
		return list.get(0);
	}
}
