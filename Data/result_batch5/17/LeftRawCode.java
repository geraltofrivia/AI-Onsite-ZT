// https://github.com/kdn251/interviews/tree/03fdcb2703ce72dc0606748733d0c13f09d41d21/cracking-the-coding-interview/chapter-two-linked-lists/FindBeginning.java#L5-L35
public class TempClass {
	LinkedListNode findBeginning(LinkedListNode head) {
		LinkedListNode slow = head;
		LinkedListNode fast = head;

		/* find meeting point. This will be LOOP_SIZE - k
		 * steps int othe linked list */
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(fast == slow) {
				break;
			}
		}

		/* error checking - no meeting point, and therefore no loop */
		if(fast == null || fast.next == null) {
			return null;
		}

		/* move slow to head. Keep fast at meeting point. Each are k
		 * steps from the loop start. If they move at the same pace,
		 * they must meet at the loop start */
		slow = head;
		while(slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		/* both now point to the start of the loop */
		return fast;
	}

}