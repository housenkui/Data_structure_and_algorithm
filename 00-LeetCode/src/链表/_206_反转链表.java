package 链表;

public class _206_反转链表 {
	public ListNode reverseList(ListNode head) {
		ListNode newHead = reverseList(head.next);
		head.next.next =  head;
		head.next = null;
		return newHead;
	}
}
