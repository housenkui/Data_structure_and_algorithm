package com.sk;

import java.util.Comparator;

import com.sk.file.Files;
import com.sk.printer.BinaryTreeInfo;
import com.sk.printer.BinaryTrees;

import com.sk.BST;
import com.sk.BinaryTree;
import com.sk.BinaryTree.Visitor;

public class Main {

	static void test1() {
		Integer data[] = new Integer[] {
				7,4,9,2,5,8,11,3,12,1
		};
		BST<Integer> bst = new BST<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		BinaryTrees.println(bst);
		bst.remove(7);
		BinaryTrees.println(bst);
	}
	
	static void test2() {
		Integer data[] = new Integer[] {
				// 7, 4
				// 7, 4, 9
				// 7, 4, 9, 5
				// 7, 4, 9, 2
				// 7, 4, 9, 2, 8
				7, 4, 9, 2
		};
		
		BST<Integer> bst = new BST<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		System.out.println("----------------------------------");
		System.out.println(bst.isComplete());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test2();
	}

}
