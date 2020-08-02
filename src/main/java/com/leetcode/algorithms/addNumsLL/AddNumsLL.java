package com.leetcode.algorithms.addNumsLL;

 class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class AddNumsLL {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(), curr = head, prev = null;

        while(l1 != null || l2 != null) {
            int l1Val = 0, l2Val = 0;

            if(l1 != null) { l1Val = l1.val; l1 = l1.next; }
            if(l2 != null) { l2Val = l2.val; l2 = l2.next; }

            int result = l1Val + l2Val + carry;
            carry = result / 10;

            if(curr == null) curr = new ListNode();
            curr.val = result % 10;

            if(prev != null) prev.next = curr;
            prev = curr;
            curr = curr.next;
        }

        if(carry > 0) prev.next = new ListNode(carry);
        return head;
    }
}
