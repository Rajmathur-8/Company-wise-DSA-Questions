package ORACLE;

import java.util.*;

public class CombinationSum2 {
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> result = combinationSum2(candidates, target);
        System.out.println("Combinations that sum to " + target + ": " + result);
    }
    /**
     * Finds all unique combinations of candidates that sum to the target.
     * Pseudo-code:
     * 1. Sort the candidates to handle duplicates.
     * 2. Use backtracking to explore combinations:
     *    a. If the current sum equals the target, add the combination to results.
     *    b. If the current sum exceeds the target, return.
     *    c. Iterate through candidates, skipping duplicates.
     *    d. Recur with the next index to avoid reusing the same element.
     * 3. Return the list of results.
     *
     * Time Complexity: O(2^n) - where n is the number of candidates
     * Space Complexity: O(n) - for storing combinations
     */

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sort to handle duplicates
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }
    private static void backtrack(List<List<Integer>> result, List<Integer> current, int[] candidates, int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(current)); // Found a valid combination
            return;
        }
        if (target < 0) {
            return; // Exceeded the target
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue; // Skip duplicates
            current.add(candidates[i]);
            backtrack(result, current, candidates, target - candidates[i], i + 1); // Move to the next index
            current.remove(current.size() - 1); // Backtrack
        }
    }
    /*
     * brute-force approach:
     * 1. Generate all possible combinations of candidates.
     * 2. Filter combinations that sum to the target.
     * 3. Remove duplicates from the result.
     * Time Complexity: O(2^n) - where n is the number of candidates
     * Space Complexity: O(n) - for storing combinations
     */
}
