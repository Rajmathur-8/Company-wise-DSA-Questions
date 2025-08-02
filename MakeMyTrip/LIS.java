package MakeMyTrip;

import java.util.*;

public class LIS {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int result = lengthOfLIS(nums);
        System.out.println("Length of Longest Increasing Subsequence: " + result);
        // Print the longest increasing subsequence
        List<Integer> lis = getLongestIncreasingSubsequence(nums);
        System.out.println("Longest Increasing Subsequence: " + lis);
    }   
    /*
     * Finds the length of the longest increasing subsequence in an array.
     * This is a brute-force approach.
     * Pseudo Code:
     * 1. Initialize a recursive helper function that takes the current index and the previous element's value.
     * 2. If the current index is equal to the length of the array, return 0.
     * 3. Exclude the current element and call the helper function for the next index.
     * 4. Include the current element if it is greater than the previous element's value.
     * 5. If included, add 1 to the result of the recursive call for the next index with the current element's value.
     * 6. Return the maximum of the two results.
     * 
     * Time Complexity: O(2^n)
     * - Where n is the length of the array.
     * Space Complexity: O(n)
     * - O(n) space used for the recursion stack.
     * 
     */
    public static int lengthOfLISBrute(int[] nums) {
        return lisHelper(nums, 0, Integer.MIN_VALUE);
    }
    private static int lisHelper(int[] nums, int index, int prev) {
        if (index == nums.length) {
            return 0; // Base case: reached the end of the array
        }

        // Exclude the current element
        int notTake = lisHelper(nums, index + 1, prev);

        // Include the current element if it's greater than the previous one
        int take = 0;
        if (nums[index] > prev) {
            take = 1 + lisHelper(nums, index + 1, nums[index]);
        }

        return Math.max(take, notTake); // Return the maximum of both choices
    }


    /*
     * Optimized approach using dynamic programming(Memoization).
     * Pseudo Code:
     * 1. Initialize a 2D array dp to store results of subproblems.
     * 2. Fill the dp array with -1 to indicate uncomputed states.
     * 3. Create a recursive helper function that takes the current index and the previous element's index.
     * 4. If the current index is equal to the length of the array, return 0.
     * 5. If the result for the current index and previous index is already computed, return it.
     * 6. Exclude the current element and call the helper function for the next index.
     * 7. Include the current element if it is greater than the previous element's value.
     * 8. If included, add 1 to the result of the recursive call for the next index with the current index as the previous element's index.
     * 9. Store the result in the dp array and return it.
     * 
     * Time Complexity: O(n^2)
     * - Where n is the length of the array.
     * Space Complexity: O(n^2)
     * - O(n^2) space used for the dp array.
     */
    public static int LengthofLISMemo(int[] nums){
        int[][] dp = new int[nums.length][nums.length + 1];
        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], -1);
        return memoHelper(nums, 0, -1, dp);
    }
    private static int memoHelper(int[] nums, int index, int prevIndex, int[][] dp) {
        if (index == nums.length) {
            return 0; // Base case: reached the end of the array
        }

        if (dp[index][prevIndex + 1] != -1) {
            return dp[index][prevIndex + 1]; // Return cached result
        }

        // Exclude the current element
        int notTake = memoHelper(nums, index + 1, prevIndex, dp);

        // Include the current element if it's greater than the previous one
        int take = 0;
        if (prevIndex == -1 || nums[index] > nums[prevIndex]) {
            take = 1 + memoHelper(nums, index + 1, index, dp);
        }

        dp[index][prevIndex + 1] = Math.max(take, notTake); // Cache the result
        return dp[index][prevIndex + 1];
    }

    /*
     * Optimized approach using dynamic programming.(Tabulation)
     * Pseudo Code:
     * 1. Initialize a dp array of size n, where n is the length of the input array.
     * 2. Fill the dp array with 1, as the minimum length of increasing subsequence is 1 (each element itself).
     * 3. Loop through each element in the array.
     * 4. For each element, loop through all previous elements.
     * 5. If the current element is greater than a previous element, update the dp value at the current index to be the maximum of its current value and the previous element's dp value plus one.
     * 6. After processing all elements, find the maximum value in the dp array.
     * 
     * Time Complexity: O(n^2)
     * - Where n is the length of the array.
     * Space Complexity: O(n)
     * - O(n) space used for the dp array.
     */
    public static int lengthOfLISTable(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLen = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    /*
     * Optimized approach using dynamic programming with binary search lower bound.
     * Pseudo Code:
     * 1. Initialize an empty list to store the longest increasing subsequence.
     * 2. Loop through each element in the input array.
     * 3. Use binary search to find the position of the current element in the list.
     * 4. If the position is not found, append the current element to the list.
     * 5. If the position is found, replace the element at that position with the current element.
     * 6. The size of the list at the end will be the length of the longest increasing subsequence.
     * Time Complexity: O(n log n)
     * - Where n is the length of the array.
     * Space Complexity: O(n)
     * - O(n) space used for the list to store the longest increasing subsequence.
     */
    public static int lengthOfLIS(int[] nums){
        int n = nums.length;
        if(n == 0) return 0;

        List<Integer> sortedList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int pos = Collections.binarySearch(sortedList, nums[i]);
            if (pos == -1) {
                sortedList.add(nums[i]);
            }else{
                sortedList.set(pos, nums[i]); // Replace the element at the found position
            }
            
        }
        return sortedList.size(); // The size of the list is the length of the longest increasing subsequence
    }
    /*
     * Prints the longest increasing subsequence from the input array.
     * Pseudo Code:
     * 1. Initialize an empty list to store the longest increasing subsequence.
     * 2. Create a dp array to store the length of the longest increasing subsequence ending at each index.
     * 3. Loop through each element in the input array.
     * 4. For each element, loop through all previous elements to find the longest increasing subsequence.
     * 5. If the current element is greater than a previous element, update the dp value at the current index to be the maximum of its current value and the previous element's dp value plus one.
     * 6. After processing all elements, find the maximum value in the dp array.    
     * 7. Backtrack through the dp array to find the elements of the longest increasing subsequence.
     * Time Complexity: O(n^2)
     * - Where n is the length of the array.
     * Space Complexity: O(n)
     * - O(n) space used for the dp array and the list to store the longest increasing subsequence.
     */
    public static List<Integer> getLongestIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        if (n == 0) return Collections.emptyList();

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        // Backtrack to find the longest increasing subsequence
        List<Integer> lis = new ArrayList<>();
        for (int i = maxIndex; i >= 0; i--) {
            if (dp[i] == maxLen) {
                lis.add(nums[i]);
                maxLen--;
            }
        }
        Collections.reverse(lis); // Reverse to get the correct order
        return lis;
    }
}
