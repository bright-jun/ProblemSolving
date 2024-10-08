package problemsolving.leetcode;

import problemsolving.leetcode.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution102 {

    /*
     * The number of nodes in the tree is in the range [0, 2000]. -1000 <= Node.val
     * <= 1000
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        int level = 1;
        preOrderLevel(root, answer, level);
        /*
         * there are tradeoffs,
         * 	- for very 'lean' tree (most non-leaf node have only one child)
         * 		- Traversal DFS appoach consume O(n) memory - depth
         * 		- BFS approach with queue cost almost constant memory - width
         * 	- for near complete tree (most non-leaf node have two child)
         * 		- Traversal DFS appoach cost O(log(n)) memory - depth
         * 		- BFS approach cost O(n) memory - width
         * [https://leetcode.com/problems/binary-tree-level-order-traversal/discuss/33468/One-of-C++-solutions-(preorder)/32201]
         */

        return answer;
    }

    public void preOrderLevel(TreeNode treeNode, List<List<Integer>> answer, int level) {

        if (treeNode == null) {
            return;
        }

        // preorder traversal + depth
        if (answer.size() >= level) {
            answer.get(level - 1).add(treeNode.val);
        } else {
            answer.add(new ArrayList<Integer>());
            answer.get(level - 1).add(treeNode.val);
        }
        preOrderLevel(treeNode.left, answer, level + 1);
        preOrderLevel(treeNode.right, answer, level + 1);
    }

    public static void main(String[] args) {
        Solution102 solution102 = new Solution102();
        List<List<Integer>> answer;

        {
//			Input: root = [3,9,20,null,null,15,7]
//			Output: [[3],[9,20],[15,7]]
            TreeNode t0 = new TreeNode(3);
            TreeNode t1 = new TreeNode(9);
            TreeNode t2 = new TreeNode(20);
//			TreeNode t3 = new TreeNode();
//			TreeNode t4 = new TreeNode();
            TreeNode t5 = new TreeNode(15);
            TreeNode t6 = new TreeNode(7);

            t0.left = t1;
            t0.right = t2;
            t2.left = t5;
            t2.right = t6;

            answer = solution102.levelOrder(t0);
        }

        {
//			Input: root = [1]
//			Output: [[1]]
            TreeNode t0 = new TreeNode(1);
            answer = solution102.levelOrder(t0);
        }

        {
//			Input: root = []
//			Output: []
            answer = solution102.levelOrder(null);
        }
    }
}
