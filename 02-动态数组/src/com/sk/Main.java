package com.sk;

public class Main {

	public static void main(String[] args) {
		int  array[] = new int[] {11,22,33};
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
//		ArrayList list = new ArrayList();
//		list.add(10);
		test();

	}
	
	static void test() {
		// int -> Integer
	
		// 所有的类，最终都继承java.lang.Object

		// new是向堆空间申请内存
		ArrayList<Person> persons  = new ArrayList<>();
		persons.add(new Person(10, "Jack"));
		persons.add(new Person(12, "James"));
		persons.add(new Person(15, "Rose"));
		persons.clear();
		persons.add(new Person(22, "abc"));
		
		System.out.println(persons);
		ArrayList<Integer> ints  = new ArrayList<>();
		ints.add(10);
		ints.add(10);
		ints.add(22);
		ints.add(33);
		System.out.println(ints);
	}

}
