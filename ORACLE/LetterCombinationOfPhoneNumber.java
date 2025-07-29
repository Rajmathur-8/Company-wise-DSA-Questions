package ORACLE;

import java.util.*;


public class LetterCombinationOfPhoneNumber {
    public static void main(String[] args) {
        String digits = "23";
        System.out.println("Letter combinations for " + digits + ": " + letterCombinations(digits));
    }

    /**
     * This class solves the classic Letter Combinations of a Phone Number problem.
     *
     * Given a string containing digits from 2–9, return all possible letter combinations
     * that the number could represent based on the classic phone keypad.
     *
     * Time Complexity: O(3^N * 4^M), where
     * - N is the number of digits that map to 3 letters (e.g., '2', '3', '4', '5', '6', '8')
     * - M is the number of digits that map to 4 letters (e.g., '7', '9')
     * - Each digit leads to branching depending on how many letters are mapped to it
     *
     * Space Complexity: O(3^N * 4^M)
     * - Due to the output list that holds all combinations
     * - Recursive stack space is O(digits.length())
    */
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // Mapping of digits to letters (index = digit)
        String[] keys = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };

        // Begin backtracking from index 0
        backtrack(result, new StringBuilder(), digits, keys, 0);
        return result;
    }
    private static void backtrack(List<String> result, StringBuilder current, String digits, String[] keys, int index) {
        if (index == digits.length()) {
            result.add(current.toString()); // Found a complete combination
            return;
        }

        char digit = digits.charAt(index); // Get the digit at current index
        String letters = keys[digit - '0']; // Get the corresponding letters

        // Try each letter for current digit
        for (char letter : letters.toCharArray()) {
            current.append(letter); // Choose
            backtrack(result, current, digits, keys, index + 1); // Explore
            current.deleteCharAt(current.length() - 1); // Un-choose (backtrack)
        }
    }
}
