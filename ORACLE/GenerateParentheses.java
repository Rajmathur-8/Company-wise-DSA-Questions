package ORACLE;

import java.util.*;

public class GenerateParentheses {
    public static void main(String[] args) {
        int n = 3; // Example input
        List<String> result = generateParenthesis(n);
        System.out.println("Generated Parentheses: " + result);
    }
    /**
     * Generates all combinations of well-formed parentheses.
     * psuedo-code:
     * 1. Initialize an empty list to store results.
     * 2. Define a recursive function that takes the current string, count of open and close parentheses, and the maximum number of pairs.
     * 3. If the current string length is equal to 2 * n, add it to the results.
     * 4. If the count of open parentheses is less than n, add an open parenthesis and call the function recursively.
     * 5. If the count of close parentheses is less than the count of open parentheses, add a close parenthesis and call the function recursively.
     * 6. Return the list of results.   
     * Time Complexity: O(4^n / sqrt(n))
     * - The number of valid combinations grows exponentially with n.
     * - Catalan number C(n) gives the count of valid combinations.
     *
     * Space Complexity: O(n)
     * - The depth of recursion can go up to 2n, and we store results in a list.
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }
    private static void generate(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }
        if (open < max) {
            generate(result, current + "(", open + 1, close, max);
        }
        if (close < open) {
            generate(result, current + ")", open, close + 1, max);
        }
    }
}
