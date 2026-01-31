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
        int cnt = 0;
        ListNode cur = head;
        while(cur != null) {
            cnt++;
            cur = cur.next;
        }

        Stack<Integer> stk = new Stack<>();
        cur = head;
        for(int i = 0; i < cnt / 2; i++) {
            stk.push(cur.val);
            cur = cur.next;
        }

        if (cnt % 2 == 1) cur = cur.next;
        while(!stk.isEmpty()) {
            if(cur.val != stk.pop()) return false;
            cur = cur.next;
        }
        return true;
    }
}