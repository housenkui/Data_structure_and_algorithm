package com.sk;

import java.util.Comparator;

/*
 * 平衡因子：某节点的左右子树的高度差
 * AVL树的特点:
 * 每个节点的平衡因子只可能是1，0，-1 （绝对值小于等于1，如果超过1，称为失衡）
 * 每个节点的左右子树的高度差不超过1
 * 搜索、添加、删除的时间复杂度是O(logN)
 * */
public class AVLTree<E> extends BBST<E> {
	
	public AVLTree() {
		this(null);
	}
	
	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	/*
	 * 可能会导致所有祖先节点都失衡
	 * 只要让高度最低的失衡节点恢复平衡，整棵树就恢复平衡(仅需O(1)次调整)
	 * */
	@Override
	protected void afterAdd(Node<E> node) {
		while ((node = node.parent) != null) {
			if (isBalanced(node)) {
				//更新高度
				updateHeight(node);
			} else {
				//恢复平衡
				rebalance(node);
				//整棵树恢复平衡
				break;
			}
		}
	}
	/*
	 * 可能会导致父节点或祖先节点失衡(只有1个节点会失衡)
	 * 恢复平衡后，可能会导致更高层的祖先节点失衡【最多需要O(logn)次调整】
	 * */
	@Override
	protected void afterRemove(Node<E> node) {
		while ((node = node.parent) != null) {
			if (isBalanced(node)) {
				//更新高度
				updateHeight(node);
			} else {
				//恢复平衡
				rebalance(node);
			}
		}
	}
	
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		// TODO Auto-generated method stub
		return new AVLNode(element, parent);
	}
	/**
	 * 恢复平衡
	 * @param grand高度最低的那个不平衡节点
	 * */
	@SuppressWarnings("unused")
	private void rebalance2(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		Node<E> node = ((AVLNode<E>)parent).tallerChild();
		if (parent.isLeftChild()) { //L
			if (node.isLeftChild()) { //LL
				rotateRight(grand);
			} else {//LR
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else { // R
			if (node.isLeftChild()) {//RL
				rotateRight(parent);
				rotateLeft(grand);
			} else { //RR
				rotateLeft(grand);
			}
		}
	}
	/**
	 * 恢复平衡
	 * @param grand高度最低的那个不平衡节点
	 * */
	private void rebalance(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		Node<E> node = ((AVLNode<E>)parent).tallerChild();
		if (parent.isLeftChild()) { //L
			if (node.isLeftChild()) { //LL
				rotate(grand, node, node.right, parent, parent.right, grand);
			} else {//LR
				rotate(grand, parent, node.left, node, node.right, grand);
			}
		} else { // R
			if (node.isLeftChild()) {//RL
				rotate(grand, grand, node.left, node, node.right, parent);
			} else { //RR
				rotate(grand, grand, parent.left, parent, node.left, node);
			}
		}
	}
	
	@Override
	protected void rotate(
			Node<E> r,//子树的根节点
			Node<E> b,Node<E> c,
			Node<E> d,
			Node<E> e,Node<E> f) {
		
		super.rotate(r, b, c, d, e, f);
		updateHeight(b);
		updateHeight(f);
		updateHeight(d);
	}
	@Override
	protected void afterRotate(Node<E> grand,Node<E> parent, Node<E> child) {
		super.afterRotate(grand, parent, child);
		
		//更新高度
		updateHeight(grand);
		updateHeight(parent);
	}
	
	
	
	private boolean isBalanced(Node<E> node) {
		return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
	}
	
	private void updateHeight(Node<E> node) {
		((AVLNode<E>)node).updateHeight();
	}
	
	private static class AVLNode<E>extends Node<E> {
		
		int height = 1;
		
		public AVLNode(E element,Node<E> parent) {
			super(element, parent);
		}
		
		public int balanceFactor() {
			int leftHeight = left == null ? 0:((AVLNode<E>)left).height; 
			int rightHeight = right == null ? 0:((AVLNode<E>)right).height; 
			return leftHeight - rightHeight;
		}
		
		public void updateHeight() {
			int leftHeight = left == null ? 0:((AVLNode<E>)left).height; 
			int rightHeight = right == null ? 0:((AVLNode<E>)right).height; 
			height = 1 + Math.max(leftHeight, rightHeight);
		}
		
		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			if (leftHeight > rightHeight) return left;
			if (leftHeight < rightHeight) return right;
			return isLeftChild() ? left : right;
		}
		
		@Override
		public String toString() {
			String parentString = "null";
			if (parent != null) {
				parentString = parent.element.toString();
			}
			return element + "_p(" + parentString + ")_h(" + height + ")";
		}
		
	}
}
