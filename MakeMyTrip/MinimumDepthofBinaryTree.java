package MakeMyTrip;

import java.util.*;

public class MinimumDepthofBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int minDepth = minDepth(root);
        System.out.println("Minimum depth of the binary tree: " + minDepth);
    }

    /*
     * Calculates the minimum depth of a binary tree.
     * This is a brute-force approach.
     * Pseudo Code:
     * 1. If the root is null, return 0.
     * 2. If the root is a leaf node (both left and right are null), return 1.
     * 3. If the left child is null, recursively find the minimum depth of the right subtree.
     * 4. If the right child is null, recursively find the minimum depth of the left subtree.
     * 5. If both children are present, return the minimum of the depths of the left and right subtrees plus 1.
     * Time Complexity: O(n) - where n is the number of nodes in the tree 
     * Space Complexity: O(h) - where h is the height of the tree (due to recursion stack)
     * 
     */
    public static int minDepthBrute(TreeNode root){
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int leftDepth = minDepthBrute(root.left);
        int rightDepth = minDepthBrute(root.right);

        if (root.left == null) return rightDepth + 1;
        if (root.right == null) return leftDepth + 1;

        return Math.min(leftDepth, rightDepth) + 1;
    }

    /*
     * Optimized approach to calculate the minimum depth of a binary tree.
     * Pseudo Code:
     * 1. If the root is null, return 0.
     * 2. Initialize a queue for level order traversal.
     * 3. Add the root node to the queue.
     * 4. Initialize a depth variable to 1.
     * 5. While the queue is not empty:
     *    a. For each node at the current level, check if it is a leaf node.
     *       i. If it is a leaf node, return the current depth.
     *      ii. If it has a left child, add it to the queue.
     *      iii. If it has a right child, add it to the queue.
     * 6. Increment the depth after processing all nodes at the current level.
     * Time Complexity: O(n) - where n is the number of nodes in the tree
     * Space Complexity: O(n) - for the queue used in level order traversal
     */
    public static int minDepth(TreeNode root){
        if(root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode current = queue.poll();
                // Check if it's a leaf node
                if(current.left == null && current.right == null){
                    return depth;
                }
                // Add left child to the queue if it exists
                if(current.left != null){
                    queue.add(current.left);
                }
                // Add right child to the queue if it exists
                if(current.right != null){
                    queue.add(current.right);
                }
            }
            depth++;
        }
        return depth; // This line should never be reached if the tree is valid
    }
}
