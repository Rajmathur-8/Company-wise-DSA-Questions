package ORACLE;

import java.util.*;

public class LongestValidParentheses {
    public static void main(String[] args) {
        String s = "(()())";
        int result = longestValidParentheses(s);
        System.out.println("Longest valid parentheses length: " + result);
    }

    /**
     * Finds the length of the longest valid parentheses substring.
     * pseudo-code:
     * 1. Initialize a dp array to store lengths of valid parentheses ending at each index.
     * 2. Iterate through the string from index 1 to n-1.
     * 3. If the current character is ')', check the previous character.
     *    a. If the previous character is '(', set dp[i] = dp[i-2] + 2.
     *    b. If the previous character is ')', check if the substring before the last valid substring is '('.
     *       i. If it is, set dp[i] = dp[i-1] + dp[i - dp[i - 1] - 2] + 2.
     * 4. Keep track of the maximum length found in the dp array.
     * Time Complexity: O(n)
     * - n is the length of the string s
     * Space Complexity: O(n)
     * - O(n) for the dp array to store lengths of valid parentheses substrings 
     */
    public static int longestValidParentheses(String s) {
        int maxLength = 0;
        int[] dp = new int[s.length()];
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxLength = Math.max(maxLength, dp[i]);
            }
        }
        
        return maxLength;
    }
    /**
     * Optimized solution using two scans (left to right and right to left).
     * This approach uses two counters to track the number of '(' and ')' characters.
     * pseudo-code:
     * 1. Initialize two counters: left and right.
     * 2. Scan the string from left to right:
     *    a. Increment left for '(' and right for ')'.
     *    b. If left equals right, update maxLength to the maximum of current maxLength and 2 * right.
     *    c. If right exceeds left, reset both counters to 0.
     * 3. Reset counters and scan from right to left:
     *    a. Increment left for ')' and right for '('.
     *    b. If left equals right, update maxLength to the maximum of current maxLength and 2 * left.
     *    c. If left exceeds right, reset both counters to 0.
     * 4. Return maxLength.
     * 
     * Time Complexity: O(n)
     * - Each character is processed twice.
     * Space Complexity: O(1)
     * - No additional space used except for counters.
     */
    public static int longestValidParenthesesOptimized(String s) {
        int maxLength = 0;
        int left = 0, right = 0;

        // Left to right scan
        for (char c : s.toCharArray()) {
            if (c == '(') left++;
            else right++;
            if (left == right) maxLength = Math.max(maxLength, 2 * right);
            else if (right > left) left = right = 0; // reset counters
        }

        left = right = 0; // reset counters for right to left scan

        // Right to left scan
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') right++;
            else left++;
            if (left == right) maxLength = Math.max(maxLength, 2 * left);
            else if (left > right) left = right = 0; // reset counters
        }

        return maxLength;
    }

    /**
     * Finds the length of the longest valid parentheses substring using a stack.
     * This approach uses a stack to keep track of indices of unmatched opening parentheses.
     * pseudo-code:
     * 1. Initialize a stack to keep track of indices of unmatched opening parentheses.
     * 2. Initialize a variable to keep track of the last invalid index.
     * 3. Iterate through the string:
     *    a. If the character is '(', push its index onto the stack.
     *    b. If the character is ')':
     *       i. If the stack is empty, update lastInvalidIndex to the current index.
     *       ii. If the stack is not empty, pop from the stack and calculate the length of the valid substring.
     *          - If the stack is empty after popping, calculate length from lastInvalidIndex.
     *          - If not empty, calculate length from top of the stack.
     * 4. Return the maximum length found.
     *
     * Time Complexity: O(n)
     * - n is the length of the string s
     * Space Complexity: O(n)
     * - O(n) for the stack in case all characters are '('
     */
    public static int longestValidParenthesesUsingStack(String s) {
        int maxLength = 0;
        int lastInvalidIndex = -1;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    lastInvalidIndex = i; // Update last invalid index
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        maxLength = Math.max(maxLength, i - lastInvalidIndex);
                    } else {
                        maxLength = Math.max(maxLength, i - stack.peek());
                    }
                }
            }
        }

        return maxLength;
    }
}
