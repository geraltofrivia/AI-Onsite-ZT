// https://github.com/ashishps1/awesome-leetcode-resources/tree/17cc5669f2bd5f2efe784ea0e1ce6f8e5fb5ec01/patterns/java/FastAndSlowPointers.java#L76-L87
public class TempClass {
    public ListNode middleNodeFastAndSlowPointerApproach(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Move slow by 1 and fast by 2 steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; // Slow will be at the middle node
    }

}