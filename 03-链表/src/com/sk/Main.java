package com.sk;

import javax.jws.soap.SOAPBinding;

public class Main {
	
	static void testList(List<Integer> list) { //多态
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);

		list.add(0, 55); // [55, 11, 22, 33, 44]
		list.add(2, 66); // [55, 11, 66, 22, 33, 44]
		list.add(list.size(), 77); // [55, 11, 66, 22, 33, 44, 77]

		list.remove(0); // [11, 66, 22, 33, 44, 77]
		list.remove(2); // [11, 66, 33, 44, 77]
		list.remove(list.size() - 1); // [11, 66, 33, 44]

		Asserts.test(list.indexOf(44) == 3);
		Asserts.test(list.indexOf(22) == List.ELEMENT_NOT_FOUND);
		Asserts.test(list.contains(33));
		Asserts.test(list.get(0) == 11);
		Asserts.test(list.get(1) == 66);
		Asserts.test(list.get(list.size() - 1) == 44);
		
		System.out.println(list);
	}
	
	static void testList0() {
		List<Integer> list = new ArrayList<>();
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
	static void josephus() {
		
		
		CircleLinkedList<Integer> list = new CircleLinkedList<Integer>();
		for (int i = 0; i <= 8; i++) {
			list.add(i);
		}
		
		//从未想过如此简单 神奇
		//指向头结点(指向1)
		list.reset();
		
		while (!list.isEmpty()) {
			list.next();
			list.next();
			System.out.println(list.remove());
			
		}
	}
	public static void main(String[] args) {
		josephus();
		testList(new ArrayList<>());
		testList(new LinkedList<>());
		testList(new SingleCircleLinkedList<>());
		testList(new CircleLinkedList<>()); //双向循环链表
	}

}
