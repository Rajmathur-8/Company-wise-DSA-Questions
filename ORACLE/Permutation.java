package ORACLE;

import java.util.*;

public class Permutation {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("Permutations of " + Arrays.toString(nums) + ":");
        List<List<Integer>> result = permute(nums);
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }
    /**
     * Generates all permutations of a given array of integers using backtracking.
     * Brute-force approach:
     * 1. Initialize an empty list to store results.
     * 2. Define a recursive function that takes the current permutation, a boolean array to track used elements, and the original array.
     * 3. If the current permutation length equals the original array length, add it to results.
     * 4. Iterate through the original array:
     *   a. If the element is not used, mark it as used and add it to the current permutation.
     *   b. Recur to build the next element of the permutation.
     * 5. Backtrack by unmarking the element as used and removing it from the current permutation.
     * 6. Return the list of results.
     * 
     *  Time Complexity: O(n!)
     * - The number of permutations of an array of length n is n!.
     * Space Complexity: O(n)
     * - The space used for storing the permutations.
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(result, new ArrayList<>(), nums, used);
        return result;
    }
    private static void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, boolean[] used) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current)); // Found a valid permutation
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // Skip used elements
            used[i] = true; // Mark as used
            current.add(nums[i]); // Add to current permutation
            backtrack(result, current, nums, used); // Recur for the next element
            used[i] = false; // Backtrack: unmark as used
            current.remove(current.size() - 1); // Remove last element from current permutation
        }
    }
    /*
     * Optimized solution using iterative approach to generate all permutations of a string.
     * Pseudo-code:
     * 1. Initialize a list with the first character of the string.
     * 2. For each character in the string, iterate through the current list of permutations.
     * 3. For each permutation, insert the new character at every possible position.
     * 4. Update the list with new permutations.
     * 5. Return the list of results.
     *
     * Time Complexity: O(n!)
     * - The number of permutations of a string of length n is n!.
     * Space Complexity: O(n)
     * - The space used for storing the permutations.
     */
    public static List<String> permute(String s) {
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        permuteHelper(chars, 0, chars.length - 1, result);
        return result;
    }
    private static void permuteHelper(char[] chars, int start, int end, List<String> result) {
        if (start == end) {
            result.add(new String(chars)); // Found a valid permutation
        } else {
            for (int i = start; i <= end; i++) {
                swap(chars, start, i); // Swap current character with the start character
                permuteHelper(chars, start + 1, end, result); // Recur for the next index
                swap(chars, start, i); // Swap back to restore the original string
            }
        }
    }
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
