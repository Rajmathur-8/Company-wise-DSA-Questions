package ORACLE;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = search(nums, target);
        System.out.println("Index of target " + target + ": " + result);
    }
    /*
     * Searches for a target value in a rotated sorted array.
     * brute-force approach:
     * 1. Iterate through the array.
     * 2. If the current element equals the target, return its index.
     * 3. If no match is found, return -1.
     * Time Complexity: O(n) - where n is the length of the array
     * Space Complexity: O(1) - no extra space used
     */
    public static int searchBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
    /*
     * Optimized binary search approach to find the target in a rotated sorted array.
     * pseudo-code:
     * 1. Initialize left and right pointers.
     * 2. While left is less than or equal to right:
     *    a. Calculate mid index.
     *    b. If nums[mid] equals target, return mid.
     *    c. If the left half is sorted:
     *       i. If target is in the left half, adjust right pointer.
     *       ii. Otherwise, adjust left pointer.
     *    d. If the right half is sorted:
     *       i. If target is in the right half, adjust left pointer.
     *       ii. Otherwise, adjust right pointer.
     * 3. Return -1 if target is not found.
     * Time Complexity: O(log n) - where n is the length of the array
     * Space Complexity: O(1) - no extra space used
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // Target found
            }

            // Check if the left half is sorted
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Target is in the left half
                } else {
                    left = mid + 1; // Target is in the right half
                }
            } else { // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // Target is in the right half
                } else {
                    right = mid - 1; // Target is in the left half
                }
            }
        }

        return -1; // Target not found
    }
}
