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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode next = l1;
        while(true) {
            boolean flag = false;
            if(l2 != null) {
                next.val += l2.val;
                l2 = l2.next;
            }
            if(next.val >= 10) {
                next.val -= 10;
                flag = true;
            }
            if(next.next == null && (l2 != null || flag)) {
                next.next = new ListNode();
            }
            next = next.next;
            if(flag) next.val++;
            if (l2 == null && next == null) break;
        }
        return l1;
    }
}