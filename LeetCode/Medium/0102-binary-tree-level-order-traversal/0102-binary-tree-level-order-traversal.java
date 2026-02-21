/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    static List<List<Integer>> answer;
    public List<List<Integer>> levelOrder(TreeNode root) {
        answer = new LinkedList<>();
        if(root == null) return answer;
        solution(1, root);
        return answer;
    }

    static void solution (int level, TreeNode root) {
        if(answer.size() < level) answer.add(new LinkedList<>());
        answer.get(level - 1).add(root.val);
        if(root.left != null) solution(level + 1, root.left);
        if(root.right != null) solution(level + 1, root.right);
    }
}