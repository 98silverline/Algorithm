/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stk = new Stack<>();
        int len = 0;
        ListNode cur = head;
        while(cur != null) {
            stk.push(cur.val);
            cur = cur.next;
            len++;
        }

        cur = head;
        for(int i = 0; i < len / 2; i++) {
            if(cur.val != stk.pop()) return false;
            cur = cur.next;
        }
        return true;
    }
}