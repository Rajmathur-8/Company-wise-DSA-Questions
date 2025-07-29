package ORACLE;

import java.util.*;

public class ValidParentheses {
    public static void main(String[] args) {
        String s = "({[]})";
        boolean result = isValidBrute(s);
        System.out.println("Is the string valid? " + result);
    }
    /** 
     * Brute Force Pseudo-code:
     * 1. Initialize a stack to keep track of opening parentheses.
     * 2. Iterate through each character in the string:
     *    a. If the character is an opening parenthesis ('(', '{', or '['), push it onto the stack.
     *    b. If the character is a closing parenthesis (')', '}', or ']'):
     *       i. Check if the stack is empty. If it is, return false (unmatched closing parenthesis).
     *       ii. Pop the top element from the stack and check if it matches the corresponding opening parenthesis.If it does not match, return false.
     *   c. If the character is not a parenthesis, continue to the next character.
     * 3. After processing all characters, check if the stack is empty. If it is, return true (all parentheses matched). If not, return false (some opening parentheses are unmatched).
     * Time Complexity: O(n)
     * Space Complexity: O(n) in the worst case (if all characters are opening parentheses).
    */
    public static boolean isValidBrute(String s){
        Stack<Character> stack = new Stack<>();
        for(char ch : s.toCharArray()){
            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            } else if(ch == ')' || ch == '}' || ch == ']'){
                if(stack.isEmpty()) return false; // Unmatched closing parenthesis
                char top = stack.pop();
                if(!isMatchingPair(top, ch)) return false; // Mismatched pair
            }
        }
        return stack.isEmpty(); // If stack is empty, all parentheses matched
    }
    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
               (opening == '{' && closing == '}') ||
               (opening == '[' && closing == ']');
    }

    /**
     * Optimized solution using a HashMap to store matching pairs.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for the stack in the worst case.
     * This approach uses a HashMap to store the matching pairs of parentheses, which allows for quick lookups when checking for matches.
     * The stack is used to keep track of the opening parentheses, and we check for matches as we encounter closing parentheses.
     * This method is efficient and handles all edge cases, including nested and mixed parentheses.
     * pseudo-code:
     * 1. Initialize a HashMap to store matching pairs of parentheses.
     * 2. Initialize a stack to keep track of opening parentheses.
     * 3. Iterate through each character in the string:
     *   a. If the character is an opening parenthesis, push it onto the stack.
     *  b. If the character is a closing parenthesis:
     *      i. Check if the stack is empty. If it is, return false (unmatched closing parenthesis).
     *     ii. Pop the top element from the stack and check if it matches the corresponding opening parenthesis using the HashMap. If it does not match, return false.
     * 4. After processing all characters, check if the stack is empty. If it is, return true (all parentheses matched). If not, return false (some opening parentheses are unmatched).
     */
    public static boolean isValidOptimized(String s) {
        Map<Character, Character> matchingPairs = new HashMap<>();
        matchingPairs.put(')', '(');
        matchingPairs.put('}', '{');
        matchingPairs.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (matchingPairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != matchingPairs.get(ch)) {
                    return false; // Mismatched or unmatched closing parenthesis
                }
            } else {
                stack.push(ch); // Push opening parentheses onto the stack
            }
        }
        return stack.isEmpty(); // If stack is empty, all parentheses matched
    }
}
