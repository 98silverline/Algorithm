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
    class TreeDepth {
        TreeNode node;
        int depth;

        TreeDepth(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeDepth> que = new LinkedList<>();
        que.offer(new TreeDepth(root, 1));
        int answer = 0;
        while(!que.isEmpty()) {
            TreeDepth cur = que.poll();
            answer = Math.max(answer, cur.depth);
            TreeNode node = cur.node;
            if (node.left != null) {
                que.offer(new TreeDepth(node.left, cur.depth + 1));
            }
            if (node.right != null) {
                que.offer(new TreeDepth(node.right, cur.depth + 1));
            }
        }

        return answer;
    }
}