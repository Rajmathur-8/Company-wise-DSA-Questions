package MakeMyTrip;

import java.util.HashMap;

public class CopyListwithRandomPointer {
    static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public static void main(String[] args) {
        // Example usage
        Node head = new Node(1);
        head.next = new Node(2);
        head.random = head.next; // 1's random points to 2
        head.next.next = new Node(3);
        head.next.random = head; // 2's random points to 1

        Node copiedHead = copyRandomList(head);
        // Print copied list
        Node current = copiedHead;
        while (current != null) {
            System.out.print("Value: " + current.val);
            if (current.random != null) {
                System.out.print(", Random Value: " + current.random.val);
            } else {
                System.out.print(", Random Value: null");
            }
            System.out.println();
            current = current.next;
        }
    }
    /*
     * Copies a linked list with random pointers.
     * This is a brute-force approach.
     * Pseudo Code:
     * 1. Create a map to store original nodes and their copies.
     * 2. Traverse the original list and create copies of each node.
     * 3. For each original node, set the next and random pointers in the copied nodes.
     * 4. Return the head of the copied list.
     * Time Complexity: O(n)
     * - Where n is the number of nodes in the linked list.
     * Space Complexity: O(n)
     * - O(n) space used for the map to store original and copied nodes.
     */
    public static Node copyRandomList(Node head){
        if (head == null) return null;

        HashMap<Node, Node> map = new HashMap<>();
        Node current = head;
        // First pass: create a copy of each node and store in the map 
        while(current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }
        current = head;
        // Second pass: assign next and random pointers
        while(current != null) {
            Node copiedNode = map.get(current);
            copiedNode.next = map.get(current.next);
            copiedNode.random = map.get(current.random);
            current = current.next;
        }
        return map.get(head); // Return the head of the copied list
    }

    /*
     * Optimized approach to copy a linked list with random pointers.
     * Pseudo Code:
     * 1. Create new nodes and interleave them with original nodes.
     * 2. Set the random pointers for the new nodes based on the original nodes.
     * 3. Separate the two lists to return the copied list.
     * Time Complexity: O(n)
     * - Where n is the number of nodes in the linked list.
     * Space Complexity: O(1)
     * - No extra space used except for a few pointers.
     * This approach avoids using a map and directly manipulates the next pointers.
     * It is more space-efficient and runs in linear time.
     */
    public static Node copyRandomListOptimized(Node head) {
        if (head == null) return null;

        // Step 1: Create new nodes and interleave them with original nodes
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.val);
            newNode.next = current.next;
            current.next = newNode;
            current = newNode.next;
        }

        // Step 2: Set the random pointers for the new nodes
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next; // Move to the next original node
        }

        // Step 3: Separate the two lists
        Node dummy = new Node(0);
        Node copyCurrent = dummy;
        current = head;
        while (current != null) {
            copyCurrent.next = current.next; // Add copied node to the new list
            copyCurrent = copyCurrent.next;
            current.next = copyCurrent.next; // Restore original list's next pointer
            current = current.next; // Move to the next original node
        }

        return dummy.next; // Return the head of the copied list
    }
}
