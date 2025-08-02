package MakeMyTrip;

import java.util.*;

public class ImplementStackusingQueues {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Top element: " + stack.top()); // Should print 3
        System.out.println("Popped element: " + stack.pop()); // Should print 3
        System.out.println("Is stack empty? " + stack.empty()); // Should print false

        System.out.println("--- Using Two Queues ---");
        MyStackTwoQueues stack2 = new MyStackTwoQueues();
        stack2.push(1);
        stack2.push(2);
        stack2.push(3);
        System.out.println("Top element: " + stack2.top()); // Should print 3
        System.out.println("Popped element: " + stack2.pop()); // Should print 3
        System.out.println("Is stack empty? " + stack2.empty()); // Should print false
    }
}

// ✅ MyStack using one queue (rotation on push)
/*
 * Implements a stack using a single queue.
 * Pseudo Code: 
 * 1. Use a queue to store stack elements.
 * 2. On push, add the new element to the queue.
 * 3. Rotate the queue to move the new element to the front.   
 * 4. On pop, remove the front element of the queue.
 * 5. On top, return the front element of the queue without removing it.
 * 6. On empty, check if the queue is empty.
 * 7. Return the top element, popped element, and empty status as needed. 
 * Time Complexity: O(n) for push, O(1) for pop and top.
 * Space Complexity: O(n) for the queue.
 */
class MyStack {
    private Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

// ✅ MyStack using two queues (rotation on push)
/*
 * Implements a stack using two queues.
 * Pseudo Code:
 * 1. Use two queues to store stack elements.
 * 2. On push, add the new element to the first queue.
 * 3. Move all elements from the second queue to the first queue.
 * 4. Swap the names of the two queues.
 * 5. On pop, remove the front element of the second queue.
 * 6. On top, return the front element of the second queue without removing it.
 * 7. On empty, check if the second queue is empty.
 * Time Complexity: O(n) for push, O(1) for pop and top.
 * Space Complexity: O(n) for the queues.
 */
class MyStackTwoQueues {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public MyStackTwoQueues() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.add(x);
        while (!queue2.isEmpty()) {
            queue1.add(queue2.poll());
        }
        // Swap queues
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        return queue2.poll();
    }

    public int top() {
        return queue2.peek();
    }

    public boolean empty() {
        return queue2.isEmpty();
    }
}
