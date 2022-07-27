package exercise.leetcode;

import exercise.leetcode.datastructure.TreeNode;

public class Solution114 {

	public static TreeNode AnsNode;
	public static TreeNode AnsNowNode;

	/*
	 * The number of nodes in the tree is in the range [0, 2000]. -100 <= Node.val
	 * <= 100
	 */
	public void flatten(TreeNode root) {
		AnsNode = null;
		AnsNowNode = null;

		if (root == null) {
			return;
		}

		preOrderTraversal(root);

//		parameter is reference
//		need deep copy, not just reference address copy
//		root = AnsNode;
		root.val = AnsNode.val;
		root.left = AnsNode.left;
		root.right = AnsNode.right;

		return;
	}

	public void preOrderTraversal(TreeNode nowNode) {
		if (nowNode == null) {
			return;
		}

		if (AnsNode == null) {
			AnsNode = new TreeNode(nowNode.val);
		} else {
			if (AnsNowNode == null) {
				AnsNowNode = new TreeNode(nowNode.val);
				AnsNode.right = AnsNowNode;
			} else {
				AnsNowNode.right = new TreeNode(nowNode.val);
				AnsNowNode = AnsNowNode.right;
			}
		}

		preOrderTraversal(nowNode.left);
		preOrderTraversal(nowNode.right);
	}

	public static void main(String[] args) {
		Solution114 solution114 = new Solution114();

//		Input: root = [1,2,5,3,4,null,6]
//		Output: [1,null,2,null,3,null,4,null,5,null,6]
		TreeNode tn00 = new TreeNode(1);
		TreeNode tn01 = new TreeNode(2);
		TreeNode tn02 = new TreeNode(5);
		TreeNode tn03 = new TreeNode(3);
		TreeNode tn04 = new TreeNode(4);
		TreeNode tn05 = null;
		TreeNode tn06 = new TreeNode(6);

		tn00.left = tn01;
		tn00.right = tn02;

		tn01.left = tn03;
		tn01.right = tn04;
		tn02.left = tn05;
		tn02.right = tn06;

		solution114.flatten(tn00);
		solution114.flatten(new TreeNode());
		solution114.flatten(null);

		return;
	}
}
