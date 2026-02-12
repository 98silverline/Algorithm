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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        else if (list2 == null) return list1;

        ListNode cur = list1.val < list2.val ? list1 : list2;
        ListNode answer = list1.val < list2.val ? list1 : list2;
        ListNode another = list1.val < list2.val ? list2 : list1;
        while(cur != null) {
            if(cur.next == null) {
                cur.next = another;
                break;
            } else if (another == null) break;
            if(cur.next.val < another.val) {
                cur = cur.next;
            } else {
                ListNode tmp = cur.next;
                cur.next = another;
                cur = another;
                another = tmp;
            }
        }
        return answer;
    }
}