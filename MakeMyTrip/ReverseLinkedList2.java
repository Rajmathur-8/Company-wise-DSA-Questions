package MakeMyTrip;

import java.util.ArrayList;
import java.util.List;

public class ReverseLinkedList2 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int x) { val = x; }
        ListNode(int x, ListNode next) { val = x; this.next = next; }
    }
    public static void main(String[] args){
        ReverseLinkedList2 rll = new ReverseLinkedList2();
        ListNode head = rll.new ListNode(1, rll.new ListNode(2, rll.new ListNode(3, rll.new ListNode(4, rll.new ListNode(5)))));
        int left = 2, right = 4;
        ListNode result = rll.reverseBetween(head, left, right);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
    /*
     * Reverses a portion of a linked list from position left to right.
     * This is a brute-force approach.
     * Pseudo Code:
     * 1. Store the nodes in an array list.
     * 2. Reverse the pointers between left and right.
     * 3. Reconnect the nodes.
     * 
     * Time Complexity: O(n)
     * - Where n is the length of the linked list.
     * Space Complexity: O(n)
     * - O(n) space used for storing nodes in an array list.
     */
    public ListNode reverseBetweenBrute(ListNode head,int left,int right){
        if (head == null || left == right) return head;

        // Step 1: Store nodes in an array list
        List<ListNode> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }

        // Step 2: Reverse the pointers between left and right
        int l = left - 1;
        int r = right - 1;

        // reverse the actual connections
        while (l < r) {
            ListNode temp = list.get(l);
            list.set(l, list.get(r));
            list.set(r, temp);
            l++;
            r--;
        }

        // Step 3: Reconnect the nodes
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        list.get(list.size() - 1).next = null;

        // Return the new head
        return list.get(0);
    }

    /*
     * Reverses a portion of a linked list from position left to right.
     * This is an optimized approach using a two-pointer technique.
     * Pseudo Code:
     * 1. Initialize a dummy node to simplify edge cases.
     * 2. Use two pointers to traverse the list to the left position.
     * 3. Reverse the sublist from left to right using a loop.
     * 4. Connect the reversed sublist back to the main list.
     * Time Complexity: O(n)
     * - Where n is the length of the linked list.
     * Space Complexity: O(1)
     * - No extra space used except for a few pointers.
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // Move prev to the node just before the left position
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // Start reversing the sublist
        ListNode current = prev.next;
        ListNode next = null;
        for (int i = 0; i < right - left; i++) {
            next = current.next;
            current.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next;
    }
}
