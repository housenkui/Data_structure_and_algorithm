package com.sk;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> stack = new Stack<>();
		stack.push(11);
		stack.push(22);
		stack.push(33);
		stack.push(44);
		while (!stack.isEmpty()) {
			System.out.println(stack.pop()); //神奇 ···真简单
			
		}
	}

}
