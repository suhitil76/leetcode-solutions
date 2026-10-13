public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move slow pointer by 1 step
            fast = fast.next.next;     // Move fast pointer by 2 steps

            if (slow == fast) {        // Cycle detected
                return true;
            }
        }
        return false;                  // Reached end of list, no cycle
    }
}
