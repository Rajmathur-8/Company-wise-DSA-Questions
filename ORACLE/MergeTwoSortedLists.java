package ORACLE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode mergedList = mergeTwoListsBrute(l1, l2);
        printList(mergedList);
    }
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     *  Brute Force approach to merge two sorted linked lists.
     *  Pseudo-code:
     * 1. Initialize an empty list to store the values from both linked lists.
     * 2. Traverse the first linked list and add each value to the list.
     * 3. Traverse the second linked list and add each value to the list.
     * 4. Sort the list of values.
     * 5. Create a new linked list from the sorted values.
     * 6. Return the new linked list.
     * Time Complexity: O(n log n)
     * - n is the total number of nodes in both lists combined.
     * Space Complexity: O(n)
     * - O(n) for storing the values in a list before creating the merged linked list
     * This approach is straightforward but not optimal for merging sorted lists.
     * It is useful for understanding the merging process but can be improved.
     * This method is not efficient for large lists due to the sorting step.  
     */
    public static ListNode mergeTwoListsBrute(ListNode l1,ListNode l2){
        List<Integer> values = new ArrayList<>();

        // Step 1: Traverse both lists and collect values
        while (l1 != null) {
            values.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            values.add(l2.val);
            l2 = l2.next;
        }

        // Step 2: Sort the collected values
        Collections.sort(values);

        // Step 3: Rebuild the linked list from sorted values
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * optimized approach to merge two sorted linked lists.
     * pseudo-code:
     * 1. Initialize a dummy node to help build the merged list.
     * 2. Use a pointer to track the current position in the merged list.
     * 3. Compare the values of the nodes in both lists:
     *   a. If the value of the first list's node is less than or equal to the second list's node, append it to the merged list and move the pointer in the first list.
     *  b. Otherwise, append the second list's node to the merged list and move the pointer in the second list.
     * 4. If one of the lists is exhausted, append the remaining nodes of the other list to the merged list.
     * 5. Return the merged list starting from the node next to the dummy node.
     * Time Complexity: O(n + m)
     * - n is the length of the first list and m is the length of the second list.
     * Space Complexity: O(1) for the dummy node, as we are not using any additional data structures that grow with input size.
    */
    public static ListNode mergeTwoListsOptimized(ListNode l1,ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // If one of the lists is not exhausted, append it to the merged list
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        return dummy.next; // Return the merged list starting from the next of dummy node
    }



}
