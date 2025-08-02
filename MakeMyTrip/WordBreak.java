package MakeMyTrip;

public class WordBreak {
    public static void main(String[] args) {
        String s = "leetcode";
        String[] wordDict = {"leet", "code"};
        boolean result = wordBreak(s, wordDict);
        System.out.println("Can the string be segmented: " + result);
    }
 
    /*
     * Checks if a string can be segmented into words from a dictionary.
     * This is a brute-force approach.
     * Pseudo Code:
     * 1. If the string is empty, return true.
     * 2. For each word in the dictionary, check if the string starts with that word.
     * 3. If it does, recursively check the remaining substring.
     * 4. If any recursive call returns true, return true.
     * 5. If no words match, return false.
     * 
     * Time Complexity: O(n * m^2)
     * - Where n is the length of the string and m is the average length of words in the dictionary.
     * Space Complexity: O(n)
     * - O(n) space used for the recursion stack.
     */
    public static boolean wordBreakBrute(String s,String[] wordDict){
        return canBreak(s, wordDict);
    }
    private static boolean canBreak(String s, String[] wordDict) {
        if (s.isEmpty()) return true;

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (canBreak(s.substring(word.length()), wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Checks if a string can be segmented into words from a dictionary using dynamic programming.
     * Pseudo Code:
     * 1. Create a boolean array dp of size n+1, where n is the length of the string.
     * 2. Initialize dp[0] to true (empty string can be segmented).
     * 3. For each index i from 1 to n:
     *    a. For each word in the dictionary:
     *      i. If the substring from j to i (where j is the length of the word) is equal to the word and dp[j] is true,
     *         set dp[i] to true.
     * 4. Return dp[n] (whether the entire string can be segmented).
     * Time Complexity: O(n * m)
     * - Where n is the length of the string and m is the number of words in the dictionary.
     * Space Complexity: O(n)
     * - O(n) space used for the dp array.
     * 
     */
    public static boolean wordBreak(String s, String[] wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Base case: empty string can be segmented

        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                int len = word.length();
                if (i >= len && s.substring(i - len, i).equals(word) && dp[i - len]) {
                    dp[i] = true;
                    break; // No need to check further if we found a match
                }
            }
        }
        return dp[n];
    }
}
