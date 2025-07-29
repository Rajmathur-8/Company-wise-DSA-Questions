package ORACLE;

import java.util.*;

public class MergekSortedLists {
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
    public static void main(String[] args) {
        // Example usage
        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
        lists[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
        lists[2] = new ListNode(2, new ListNode(6));

        ListNode mergedList = mergeKLists(lists);
        printList(mergedList);
    }
    /**
     * Brute Force approach to merge k sorted linked lists.
     * Pseudo-code:
     * 1. Initialize an empty list to store the values from all linked lists.
     * 2. Traverse each linked list and add each value to the list.
     * 3. Sort the list of values.
     * 4. Create a new linked list from the sorted values.
     * 5. Return the new linked list.
     * Time Complexity: O(n log n)
     * - n is the total number of nodes in all lists combined.
     * Space Complexity: O(n)
     * - O(n) for storing the values in a list before creating the merged linked list
     * This approach is straightforward but not optimal for merging sorted lists.
     * It is useful for understanding the merging process but can be improved.
    */
    public static ListNode mergeKListsBrute(ListNode[] lists){
        List<Integer> values = new ArrayList<>();

        // Step 1: Collect all node values from all lists
        for (ListNode list : lists) {
            while (list != null) {
                values.add(list.val);
                list = list.next;
            }
        }

        // Step 2: Sort the collected values
        Collections.sort(values);

        // Step 3: Build new sorted linked list from sorted values
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    /**
     * Merges k sorted linked lists into one sorted linked list.
     * psuedo-code:
     * 1. Initialize a priority queue (min-heap) to store the head nodes of each list.
     * 2. Add the head of each list to the priority queue.
     * 3. Initialize a dummy node to build the merged list.
     * 4. While the priority queue is not empty:
     *   a. Poll the smallest node from the priority queue.
     *   b. Add this node to the merged list.
     *   c. If the polled node has a next node, add it to the priority queue.
     * 5. Return the next of the dummy node as the head of the merged list.
     * 
     * Time Complexity: O(n log k)
     * - n is the total number of nodes across all lists.
     * - log k is for the priority queue operations.
     * 
     * Space Complexity: O(k)
     * - For storing the head nodes of each list in the priority queue.
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;

            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }

        return dummy.next;
    }
}
