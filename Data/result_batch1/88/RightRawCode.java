// https://github.com/ashishps1/awesome-leetcode-resources/tree/17cc5669f2bd5f2efe784ea0e1ce6f8e5fb5ec01/patterns/java/ReverseLinkedList.java#L9-L19
public class TempClass {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null; // Previous node, initially null
        ListNode curr = head; // Current node starts from the head
        while (curr != null) {
            ListNode next = curr.next; // Store next node
            curr.next = prev; // Reverse the current node's pointer
            prev = curr; // Move prev to current
            curr = next; // Move curr to next
        }
        return prev; // New head of the reversed list
    }    

}