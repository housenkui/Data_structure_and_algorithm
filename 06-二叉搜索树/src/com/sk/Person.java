package com.sk;

public class Person implements Comparable<Person>{
	private int age;
	private String name;
	
	public int  getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Person (int age){
		this.age = age;
	}
	
	public Person(int age, String name) {
		
		this.age = age;
		this.name = name;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return age - o.age;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return age + "_" + name;
	}
}
