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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int cnt = 0;
        ListNode node = head;
        while(node != null) {
            cnt++;
            node = node.next;
        }
        if(cnt == 1) return null;
        if(cnt == n) return head.next;
        int idx = cnt - n;
        node = head;
        int cur = 1;
        while(cur < idx) {
            node = node.next;
            cur++;
        }
        System.out.println(cur + " " + node.val);
        if(node.next != null) node.next = node.next.next;
        return head;
    }
}