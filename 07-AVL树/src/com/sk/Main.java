package com.sk;

import com.sk.file.Files;
import com.sk.printer.BinaryTreeInfo;
import com.sk.printer.BinaryTrees;

import com.sk.AVLTree;
import com.sk.BinaryTree;
import com.sk.BinaryTree.Visitor;

public class Main {
	
	static void test1() {
		Integer data[] = new Integer[] {
				67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
		};
		
		AVLTree<Integer> avl = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BinaryTrees.println(avl);
//			System.out.println("---------------------------------------");
		}
		
//		for (int i = 0; i < data.length; i++) {
//			avl.remove(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BinaryTrees.println(avl);
//			System.out.println("---------------------------------------");
//		}
		
		
		BinaryTrees.println(avl);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

}
