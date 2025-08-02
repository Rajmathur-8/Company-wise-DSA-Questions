package MakeMyTrip;
import java.util.*;

public class LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int x) { val = x; }
        ListNode(int x, ListNode next) { val = x; this.next = next; }
    }
    public static void main(String[] args) {
        LinkedListCycle llc = new LinkedListCycle();
        ListNode head = llc.new ListNode(1, llc.new ListNode(2, llc.new ListNode(3)));
        head.next.next.next = head; // Creating a cycle
        boolean hasCycle = llc.hasCycle(head);
        System.out.println("Has cycle: " + hasCycle);
    }

    /*
     * Detects if a linked list has a cycle using a brute-force approach.
     * Pseudo Code:
     * 1. Initialize an empty set to keep track of visited nodes.
     * 2. Traverse the linked list starting from the head.
     * 3. For each node, check if it is already in the visited set.
     * 4. If it is, return true (cycle detected).
     * 5. If not, add the node to the visited set and move to the next node.
     * 6. If the end of the list is reached (null), return false (no cycle).
     * Time Complexity: O(n) - where n is the number of nodes in the linked list 
     * Space Complexity: O(n) - where n is the number of nodes in the linked list (due to the set)
     */
    public boolean hasCycleBrute(ListNode head){
        Set<ListNode> visited = new HashSet<>();

        ListNode current = head;
        while (current != null) {
            if (visited.contains(current)) {
                return true; // Cycle detected
            }
            visited.add(current);
            current = current.next;
        }

        return false; // No cycle
    }

    /*
     * Detects if a linked list has a cycle using Floyd's Tortoise and Hare algorithm (Fast & Slow Pointer).
     * Pseudo Code:
     * 1. Initialize two pointers, slow and fast, both pointing to the head of the linked list.
     * 2. Move slow pointer one step at a time and fast pointer two steps at a time.
     * 3. If there is a cycle, the fast pointer will eventually meet the slow pointer.
     * 4. If the fast pointer reaches the end of the list (null), there is no cycle.
     * 5. Return true if the two pointers meet, otherwise return false.
     * Time Complexity: O(n) - where n is the number of nodes in the linked list
     * Space Complexity: O(1) - no extra space used except for pointers
     * 
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true; // Cycle detected
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false; // No cycle
    }
}
