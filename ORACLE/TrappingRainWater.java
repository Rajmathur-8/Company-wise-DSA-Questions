package ORACLE;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int result = trap(height);
        System.out.println("Trapped rainwater: " + result);
    }
    /*
     * brute-force approach:
     * 1. For each element, find the maximum height to the left and right.
     * 2. The water trapped above the element is the minimum of these two heights minus the height of the element.
     * 3. Sum the water trapped above each element.
     * Time Complexity: O(n^2) - where n is the length of the height array
     * Space Complexity: O(1) - no extra space used
     */
    public static int trapBrute(int[] height) {
        int n = height.length;
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            int leftMax = 0, rightMax = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            totalWater += Math.max(0, Math.min(leftMax, rightMax) - height[i]);
        }
        return totalWater;
    }
    /*
     * Optimized approach using two pointers:
     * 1. Initialize two pointers, left and right, at the start and end of the array.
     * 2. Maintain two variables, leftMax and rightMax, to track the maximum heights from both ends.
     * 3. Move the pointers towards each other:
     *    a. If height[left] is less than or equal to height[right], calculate water trapped at left pointer.
     *    b. Otherwise, calculate water trapped at right pointer.
     * 4. Continue until the two pointers meet.
     * Time Complexity: O(n) - where n is the length of the height array
     * Space Complexity: O(1) - no extra space used
     */
    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }
        return totalWater;
    }
}
