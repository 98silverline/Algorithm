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

    static class Point {
        boolean flag;
        int min;
        int max;

        Point(boolean flag, int min, int max) {
            this.flag = flag;
            this.min = min;
            this.max = max;
        }
    }
    public boolean isValidBST(TreeNode root) {
        Point answer = solution(root);
        return answer.flag;
    }

    static Point solution(TreeNode node) {
        //자식 노드 아예 없는 경우
        if(node.left == null && node.right == null) {
            return new Point(true, node.val, node.val);
        }

        //자식 노드 한쪽만 있는 경우
        if(node.left == null) {
            Point rp = solution(node.right);
            //다른 자식 노드 false면 비교할 필요 없이 false 리턴
            if(!rp.flag) return new Point (false, 0, 0);
            //오른쪽 자식 노드에서 온 최솟값이 현재 노드의 값보다 큰지 비교
            if(node.val < rp.min) return new Point (true, node.val, Math.max(node.val, rp.max));
        } else if (node.right == null) {
            Point lp = solution(node.left);
            if(!lp.flag) return new Point(false, 0, 0);
            if(node.val > lp.max) return new Point(true, Math.min(node.val, lp.min), node.val);
        }
        //자식 노드 양쪽 다 있음
        else {
            Point lp = solution(node.left);
            Point rp = solution(node.right);
            if(lp.flag && rp.flag) {
                if(node.val < rp.min && node.val > lp.max) {
                    return new Point(true, Math.min(node.val, lp.min), Math.max(node.val, rp.max));
                }
            } else return new Point(false, 0, 0);
        }

        return new Point(false, 0, 0);
    }
}