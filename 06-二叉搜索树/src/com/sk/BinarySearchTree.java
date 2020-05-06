package com.sk;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;



import com.sk.printer.BinaryTreeInfo;
@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {
	
	private int size;
	private Node<E> root;
	private Comparator<E> comparator;
	
	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	public void add(E element) {
		elementNotNNUllCheck(element);
		
		//添加第一个节点
		if (root == null) {
			root = new Node<>(element,null);
			size++;
			return;
		}
		//添加的不是第一个节点
		//找到父节点
		Node<E> parent = root;
		Node<E> node = root;
		int cmp = 0;
		do {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else { //相等 不用添加
				node.element = element;
			}
		} while (node != null);//通过循环不停的在找父节点
		
		//看看插入到父节点的哪个位置
		Node<E> newNode = new Node<>(element,parent);
		if (cmp > 0) {
			parent.right = newNode; 
		} else {
			parent.left = newNode;
		}
		size++;
	}
	
	public void remove(E element) {
		remove(node(element)); //删除值为element的节点
	}
	public boolean contains(E element) {
		return node(element) != null;
	}
	
	/*
	 * 这个需要对照这个图去看，去想
	 * */
	private void remove(Node<E> node) {
		if (node == null) {
			return;
		}
		if (node.hasTwoChildren()) { //度为2的节点
			//找到后继节点
			Node<E> s  = successor(node);
			//用后继节点的值覆盖度为2的节点的值
			node.element = s.element;
			//删除后继节点
			node = s;
		}
		//删除node 节点，(node的度必然是1或者0)
		Node<E> replacement = node.left != null ? node.left : node.right;
		
		if (replacement != null) { //node是度为1的节点
			//更改parent
			replacement.parent = node.parent;
			
			//更改parent的left、right的指向、
			if (node.parent == null) { //node是度为1的节点，并且是根节点
				root = replacement;
			}else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else {
				node.parent.right = replacement;
			}
		} else if (node.parent == null) { // node 是叶子节点并且是根节点
			root = null;
		} else { //node 是叶子节点、但不是跟节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else { // node == node.parent.right
			    node.parent.right = null;	
			}
		}
	}
	
	
	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);//从根节点开始比较
			if (cmp == 0) {
				return node;
			}
			if (cmp > 0) {
				node = node.right;//比根节点大的全部在右子树上，所以需要往右找
			}else {
				node = node.left;//比根节点小的全部在左子树上，所以需要往左找
			}
		}
		return null;
	}
	
	//前序遍历：顺序
	/**
	 * 根节点、前序遍历左子树、前序遍历右子树
	 * */
	public void preorder(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		preorder(root,visitor);
	}
	
	private void preorder(Node<E> node,Visitor<E> visitor) {
		if (node == null ||visitor.stop) {
			return;
		}
		/*   2
		 * 	
		 * 1     3
		 * 可以用这个简单的二叉树思考，
		 * 
		 * visitor.visit(node.element);因为node是root，而且visitor会在外界打印node.element,所以
		 * 根节点会先打印出来 ②，之后执行preorder(node.left,visitor);，
		 * 代码又会执行visitor.stop = visitor.visit(node.element);而此时node.element是1，所以外面会打印出①
		 * preorder(node.left,visitor);继续执行，但是此时 node.left = null; 根据条件这条线会被终止；
		 * 开始执行preorder(node.right,visitor);函数会调用visitor.stop = visitor.visit(node.element);
		 * 此时的node 依旧是root,而node.right 则是以3为element的节点。所以外界会打印③，
		 * 继续往下执行preorder(node.left,visitor); preorder(node.right,visitor);
		 * 发现node是叶子节点，它的node.left = null,node.right = null会被条件终止,至此整个前序遍历完毕.
		 * */
		visitor.stop = visitor.visit(node.element);
		preorder(node.left,visitor);
		preorder(node.right,visitor);
				
	}
	
	public void inorder(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		inorder(root, visitor);
	}
	/**
	 * 中序遍历顺序:
	 * 中序遍历左子树、根节点、中序遍历右子树
	 * */
	private void inorder(Node<E> node,Visitor<E> visitor) {
		if (node == null || visitor.stop) {
			return;
		}
		inorder(node.left, visitor);
		if (visitor.stop) {
			return;
		}
		visitor.stop = visitor.visit(node.element);
		inorder(node.right, visitor);
	}
	
	public void postorder(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		postorder(root,visitor);
	}
	/**
	 * 后序遍历顺序:
	 * 后序遍历左子树、后序遍历右子树、根节点
	 * */
	private void postorder(Node<E> node,Visitor<E> visitor) {
		if (node == null || visitor.stop) {
			return;
		}
		postorder(node.left, visitor);
		postorder(node.right, visitor);
		if (visitor.stop) {
			return;
		}
		visitor.stop = visitor.visit(node.element);
	}
	
	/*
	 * 层序遍历访问顺序:
	 * 从上到下、从左到右依次访问每一个节点
	 * 实现思路:
	 * 1.将跟节点入队
	 * 2.循环执行以下操作，直到队列为空
	 * 将队头节点A出队，进行访问
	 * 将A的左子节点入队
	 * 将A的右子节点入队
	 * 			7
	 * 		4       9
	 *    2   5   8   11 
	 *    以这个简单的二叉树分析，7先入队，之后7出队，然后4入队，9入队，队列形如：9->4 此时4是队头节点，所以下次循环
	 *    4出队，将2，5 入队，队列形如：5->2->9,此时9是队头元素，然后下次循环9出队，队列形如：5->2，将8 11依次入队，
	 *    队列形如：11->8->5->2,之前的出队顺序是7，4，9，然后余下队列的出队顺序是2，5，8，11，所以整那个出队顺序是
	 *    7 4 9 2 5 8 11 这个正是层序遍历的顺序.
	 *    
	 * */
	
	public void levelOrder(Visitor<E> visitor) {
		if (root == null || visitor == null) {
			return;
		}
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);//根节点入队
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();//队头出队
			if (visitor.visit(node.element)) {//拿到队头进行访问
				return;
			}
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}
	/*
	 *           1
	 *       2       3
	 *    4
	 * 是否是完全二叉树
	 * 
	 * 如果树为空，返回false
	 * 如果树不为空，开始层序遍历二叉树(用队列)
	 * 如果node.left !=null,将node.left 入队
	 * 如果node.left == null && node.right!=null,返回false
	 * 如果node.right != null，将node.right入队
	 * 如果node.right == null
	 * 那么后面遍历的节点应该都为叶子节点，才是完全二叉树
	 * 否则返回false
	 * 遍历结束，返回true
	 * */
	public boolean isComplete() {
		if (root == null) {
			return false;
		}
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);

		boolean leaf = false;//不是叶子节点
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (leaf && !node.isLeaf()) {
				return false;
			}
			if (node.left != null) {
				queue.offer(node.left);
			}else if (node.right != null) {
				return false;//这种情况是左子树为空，右子树不为空，所以不是完全二次树
			}
			if (node.right != null) {
				queue.offer(node.right);
			}else {//node.right == null 之后都是叶子节点了
				leaf = true;
			}
		}
		return true;
	}
	/*
	 * 计算二叉树的高度
	 *     1
	 *   2   3 
	 *   可以使用这个简单的二叉树思考
	 *   1先入队，队列不为空，levelSize-- 为 0; 左右子树分别入队，levelSize = 0,但是queue.size = 2
	 *   height++ 就是1，
	 *   下次循环，队列出队，队列中还有一个元素，levelSize = 1,左右节点都是null,levelSize != 0, 无法访问下一层节点
	 *   再次循环，队列出队，此时队列为空，levelSize == 0，height++ 就是2
	 *   再次循环，队列为空，退出，返回height 
	 * */
	public int height() {
		
		if (root == null) {
			return 0;
		}
		//树的高度
		int height = 0;
		int levelSize = 1;//存储着每一层的元素数量
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--;
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}//左右子树入队
			if (levelSize == 0) { //意味着即将要访问下一层
				levelSize = queue.size();
				height ++;
			}
		}
		
		return height;
	}
	public int  height2() {
		return height(root);
	}
	private int  height(Node<E> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root,sb,"");
		return sb.toString();
	}
	
	private void toString(Node<E> node ,StringBuilder sb, String prefix) {
		if (node == null) {
			return;
		}
		
		toString(node.left, sb, prefix + "L--");
		sb.append(prefix).append(node.element).append("\n");
		toString(node.right.left, sb, prefix + "R---");
	}
	
	
	
	/**
	 * 返回值等于0，代表e1和e2相等；返回值大于0，代表e1 > e2;返回值小于0，代表e1 < e2
	 * */
	
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	private void elementNotNNUllCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	/**
	 * 前驱节点:中序遍历时的前一个节点
	 * 如果是二叉搜索树，前驱节点就是前一个比它小的节点
	 * */
	@SuppressWarnings("unused")
	private Node<E> predecessor(Node <E> node ) {
		if (node == null) {
			return null;
		}
		//前驱节点在左子树中(node.left.right.right.right...)
		Node<E> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
		//从父节点、祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		//ndoe.parent == null
		
		return node.parent;
	}
	
	private Node<E> successor(Node<E> node ) {
		if (node == null) {
			return null;
		}
		//后继节点在右子树中
		Node<E> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		//从父节点、祖父节点中寻找后继节点
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		return node.parent;
	}
	public static abstract class Visitor<E> {
		boolean stop;
		/**
		 * 如果返回 true，就代表停止遍历
		 * */
		public abstract boolean visit(E element);
	}
	
	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		public boolean isLeaf() {return left == null && right == null;}
		
		public boolean hasTwoChildren() {return left != null && right != null;}
		
	}
	

	@Override
	public Object root() {
		// TODO Auto-generated method stub
		return root;
	}

	
	@Override
	public Object left(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		
		Node<E> myNode = (Node<E>)node;
		
		String parentString = "null";
		
		if (myNode.parent != null) {
			parentString = myNode.parent.element.toString();
		}
		
		return myNode.element + "_p(" + parentString + ")";
	}

}
