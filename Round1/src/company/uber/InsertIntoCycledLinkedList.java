package company.uber;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.util.Locale;

import chapter4.linkedlist.ListNode;

/**
 * 插入一个新的节点到一个sorted cycle linkedlist（升序），返回新的节点。给的list节点不一定是最小节点。
 * 
 * 所以需要考虑两种情况： 1，正常插入在两个节点中间； 2，插入在最大最小即排序起始处。 注意：可能list中含有duplicate！！！
 */
public class InsertIntoCycledLinkedList {

	ListNode Solution(ListNode head, int toInsert) {
		ListNode newNode = new ListNode(toInsert);

		if (head == null) { // Used to be null
			head = newNode;
			head.next = head;
			return head;
		}

		if (head.next == head) { // If only 1 item
			head.next = newNode;
			newNode.next = head;
			return head;
		}

		ListNode cur = head;
		while (cur.next != head) {
			ListNode next = cur.next;

			if (toInsert >= cur.val && toInsert <= next.val) {
				cur.next = newNode;
				newNode.next = next;

				return head;
			}

			if (cur.val > next.val) { // Find the gap
				if (toInsert >= cur.val || toInsert <= next.val) { // Largest or
																	// smallest
					cur.next = newNode;
					newNode.next = next;

					return head;
				}
			}
		}

		// All are duplicates in cycle, insert to anywhere
		head.next = newNode;
		newNode.next = head;

		return head;
	}

	public ListNode learnFromOthers(ListNode head, int val) {
		// 如果原本head为空，添加新的节点，next指向自己
		if (head == null) {
			ListNode node = new ListNode(val);
			node.next = node;
			return node;
		}

		ListNode cur = head;

		do {
			// 如果val的值正好介于两个节点之间，结束循环，等号是有必要的
			if (val >= cur.val && val <= cur.next.val)
				break;
			// 如果正好是排序链断开处
			if (cur.val > cur.next.val && (val < cur.next.val || val > cur.val))
				break;
			// 否则继续往下找
			cur = cur.next;
		} while (cur != head);
		// 循环直到又到head

		// 插入新节点
		ListNode newNode = new ListNode(val);
		newNode.next = cur.next;
		cur.next = newNode;
		return newNode;
	}

	/*
	 * 给出 ［“1999-02-13”，
	 * “2001-03-14"］ 打印出中间所有的月份 ［“1999-02-13”， “1999-02-28"]，［“1999-03-01”，
	 * “1999-03-31"] ...  ["2001-03-01", “2001-03-14"］ 当然input可以是任意年月日
	 */
	static void printMonth() throws ParseException {
		LocalDate start = LocalDate.of(1999, 03, 13);
		LocalDate end = LocalDate.of(2001, 03, 14);
		
		LocalDate nextDay = start;
		while (nextDay.isBefore(end) || nextDay.equals(end)) {
			System.out.print(nextDay);
			nextDay = lastDayOfMonth(nextDay.toString());
//			nextDay = nextDay.with(lastDayOfMonth());
			if (nextDay.isBefore(end)) {
				System.out.print("--" + nextDay);
				System.out.println();
			}
			nextDay = nextDay.plusDays(1);
		}
		
		if (nextDay.isAfter(end))
			System.out.print("--" + end);
		System.out.println();
	}

	private static LocalDate lastDayOfMonth(String dateString) throws ParseException {
//		String dateString = "01/13/2012";
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US); 
		LocalDate date = LocalDate.parse(dateString, dateFormat);
		LocalDate newDate = date.withDayOfMonth(date.getMonth().maxLength());
//		System.out.println(newDate);
		return newDate;
	}
	
	public static void main(String[] args) throws Exception {
		printMonth();
	}
}
