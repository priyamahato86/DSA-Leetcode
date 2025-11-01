// You are given an array of integers nums and the head of a linked list. Return the head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums.

##CODE:
class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> st = new HashSet<>();
        for (int num : nums) {
            st.add(num);
        }

      
        while (head != null && st.contains(head.val)) {
            head = head.next;
        }

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            if (!st.contains(curr.val)) {
               
                prev = curr;
                curr = curr.next;
            } else {
               
                if (prev != null) {
                    prev.next = curr.next;
                }
                curr = curr.next;
            }
        }

        return head;
    }
}
