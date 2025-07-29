package ORACLE;

public class AddTwoNumbers {

    /**
     * Adds two numbers represented as linked lists in reverse order.
     * Each node contains a single digit. The sum is returned as a linked list.
     * 
     * Time Complexity: O(max(n, m)) – where n and m are the lengths of l1 and l2
     * Space Complexity: O(max(n, m)) – for the resulting linked list
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));

        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode result = addTwoNumbers(l1, l2);

        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) System.out.print(" -> ");
            result = result.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
/**
     * Brute Force Pseudo-code:
     * ------------------------
     * 1. Convert both linked lists to their corresponding integer values (reversed).
     *    For example, 2 -> 4 -> 3 becomes 342.
     * 2. Add the two integers.
     * 3. Convert the result back to a linked list in reverse order.
     *    For example, 807 becomes 7 -> 0 -> 8.
     *
     * Time Complexity (Brute Force):
     * - O(n + m) to traverse both lists and build the integers.
     * - O(1) addition (but integers can be large).
     * - O(log(sum)) to convert the result into a list.
     * Overall: O(n + m + log(sum)).
     * Space Complexity (Brute Force):
     * - O(n + m) for storing the integers.
     */
    