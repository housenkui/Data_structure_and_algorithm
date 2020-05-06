package com.sk;

public class Main {

	public static void main(String[] args) {
		
		List<Integer> list = new LinkedList<>();
		list.add(20);
		System.out.println(list);
		list.add(0,10);
		System.out.println(list);
		list.add(30);
		System.out.println(list);
		list.add(list.size(),40);
		System.out.println(list);
		list.remove(1);
		System.out.println(list);
		list.set(0, 0);
		System.out.println(list);
	}

}
