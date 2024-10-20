/*
 * Parker Zhang
 * Turner pd 7
 * class that makes a binary tree data structure that works with comparable with working methods
 */

public class ParkerZhangBinaryTree<E extends Comparable<E>> {

	private TreeNode root;

	public ParkerZhangBinaryTree(E[] elements) {

		root = arrayHelper(elements, 0);

	}

	private TreeNode arrayHelper(E[] elements, int curIndex) {

		if (curIndex >= elements.length || elements[curIndex] == null)
			return null;

		return new TreeNode(elements[curIndex], arrayHelper(elements, 2 * curIndex + 1),
				arrayHelper(elements, 2 * curIndex + 2));

	}

	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(TreeNode r) {

		if (r != null) {
			inOrder(r.left);
			System.out.print(r.data + " ");
			inOrder(r.right);
		}

	}

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(TreeNode r) {

		if (r != null) {
			System.out.print(r.data + " ");
			preOrder(r.left);
			preOrder(r.right);
		}
	}

	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(TreeNode r) {

		if (r != null) {
			postOrder(r.left);
			postOrder(r.right);
			System.out.print(r.data + " ");

		}
	}

	public boolean find(E item) {
		return find(root,item);
	}

	private boolean find(TreeNode r, E item) {
		
		if(r == null) 
			return false;
		
		if(r.data.equals(item)||find(r.left,item)||find(r.right,item)) 
			return true;
		
		return false;
		
	}
	
	public int height() {
		return height(root);
	}
	
	//returns the height of the binary tree
	private int height(TreeNode r) {
		
		if(r == null)
			return 0;
		
		int left =height(r.left);
		
		int right =height(r.right);
		
		if(left>right) 
			return 1 + left;
		
		else 
			return 1 + right;
		
	}
	
	public E max() {
		return max(root);
	}
	
	private E max(TreeNode r) {
		
		if(r==null)
			return null;
		
		E leafMax;
		
		E leftMax = max(r.left);
		E rightMax = max(r.right);
		
		//gets the max out of left and right
		if(leftMax.compareTo(rightMax)>0)
			leafMax = leftMax;
		else
			leafMax = rightMax;
		
		//compares that max to current data
		if(r.data.compareTo(leafMax)>0)
			return r.data;
		
		return leafMax;
		
	}

	public class TreeNode {
		private E data;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(E d, TreeNode l, TreeNode r) {
			data = d;
			left = l;
			right = r;
		}
	}

	public static void main(String[] args) {

		Integer[] vals = { 16, 8, 23, 4, 15, null, 42 };

		ParkerZhangBinaryTree tree = new ParkerZhangBinaryTree(vals);
		tree.inOrder();
		System.out.println();
		tree.preOrder();
		System.out.println();
		tree.postOrder();
		System.out.println();
		System.out.println(tree.find(43));
		
		System.out.println(tree.height());
		System.out.println(tree.max());
	}

}
