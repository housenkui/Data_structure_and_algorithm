package com.sk;

//双端队列 可以在收尾进行操作
public class Deque<E> {
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
	
	//尾部入队
	public void enQueueRear(E element) {
		list.add(element);
	}
	
	//头部出队
	public E  deQueueFront() {
		return list.remove(0);
	}
	//头部入队
	public void enQueueFront(E element) {
		 list.add(0, element);
	}
	
	//尾部出队
	public E deQueueRear() {
		return list.remove(list.size() - 1);
	}
	
	//获得队头元素
	public E  front() {
		return list.get(0);
	}
	//获得队尾元素
	public E rear() {
		return list.get(list.size() - 1);
		
	}
}
