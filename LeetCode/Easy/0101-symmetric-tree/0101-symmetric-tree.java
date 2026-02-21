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
    public boolean isSymmetric(TreeNode root) {
        if(root.left == null && root.right == null) return true;
        else if ((root.left == null && root.right != null) || (root.right == null && root.left != null)) return false;
        else if (root.left.val != root.right.val) return false;
        return solution(root.left, root.right);
    }

    static boolean solution(TreeNode ln, TreeNode rn) {
        //둘 다 노드의 끝임(자식노드 존재X)
        if(ln.left == null && ln.right == null && rn.left == null && rn.right == null) {
            return true;
        }

        //한쪽이 null인 경우(자식노드 하나임)
        if(ln.left == null && rn.right == null && ln.right != null && rn.left != null && ln.right.val == rn.left.val) {
            if(solution(ln.right, rn.left)) return true;
        } else if (ln.right == null && rn.left == null && ln.left != null && rn.right != null && ln.left.val == rn.right.val) {
            if(solution(ln.left, rn.right)) return true;
        } else if (ln.left != null && ln.right != null && rn.left != null && rn.right != null && ln.left.val == rn.right.val && ln.right.val == rn.left.val) {
            if(solution(ln.left, rn.right) && solution(ln.right, rn.left)) return true;
        }

        return false;
    }
}