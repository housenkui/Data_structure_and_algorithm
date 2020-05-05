package com.sk;

public abstract class AbstractList<E> implements List<E>{
	/**
	 * 元素的数量
	 */
	protected int size;
		
	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean contains(E element) {
		
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	
	@Override
	public boolean isEmpty() {
		
		return size == 0;
	}

	@Override
	public void add(E element) {
		add(size, element);
		
	}
	protected void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
	}
	
	protected void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	protected void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
}
