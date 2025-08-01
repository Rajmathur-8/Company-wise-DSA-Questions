package ORACLE;

import java.util.*;

public class Permutations {
    public static void main(String[] args) {
        String s = "abc";
        List<String> result = permute(s);
        System.out.println("Permutations of " + s + ": " + result);
    }

    /*
     * Brute force solution to generate all permutations of a string.
     * 
     * Pseudo-code:
     * 1. Initialize an empty list to store results.
     * 2. Define a recursive function that takes the current string, the start index, and the end index.
     * 3. If the start index equals the end index, add the current permutation to results.
     * 4. Iterate through the string from the start index to the end index:
     *   a. Swap the current character with the character at the start index.
     *   b. Recur with the next index.
     *   c. Swap back to restore the original string.
     * 5. Return the list of results.
     * 
     * Time Complexity: O(n!)
     * - The number of permutations of a string of length n is n!.
     * Space Complexity: O(n)
     * - The space used for storing the permutations.
     */
    public static List<String> permuteBrute(String s) {
        List<String> result = new ArrayList<>();
        permuteHelper(s.toCharArray(), 0, s.length() - 1, result);
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
    
    /*
     * Optimized solution using backtracking to generate all permutations of a string.
     * Pseudo-code:
     * 1. Initialize an empty list to store results.
     * 2. Define a recursive function that takes the current string, a boolean array to track used characters, and the current permutation.
     * 3. If the current permutation length equals the string length, add it to results
     * 4. Iterate through the string:
     *   a. If the character is not used, mark it as used and add it to the current permutation.
     *   b. Recur to build the next character of the permutation.
     *  c. Backtrack by marking the character as unused and removing it from the current permutation.
     * 5. Return the list of results.
     * Time Complexity: O(n!)
     * - The number of permutations of a string of length n is n!.
     * Space Complexity: O(n)
     * - The space used for storing the permutations.
     */
    public static List<String> permute(String s) {
        List<String> result = new ArrayList<>();
        boolean[] used = new boolean[s.length()];
        StringBuilder current = new StringBuilder();
        backtrack(result, s, used, current);
        return result;
    }
    private static void backtrack(List<String> result, String s, boolean[] used, StringBuilder current) {
        if (current.length() == s.length()) {
            result.add(current.toString()); // Found a valid permutation
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!used[i]) {
                used[i] = true; // Mark character as used
                current.append(s.charAt(i)); // Add character to current permutation
                backtrack(result, s, used, current); // Recur to build the next character
                used[i] = false; // Backtrack: mark character as unused
                current.deleteCharAt(current.length() - 1); // Remove last character from current permutation
            }
        }
    }

}

