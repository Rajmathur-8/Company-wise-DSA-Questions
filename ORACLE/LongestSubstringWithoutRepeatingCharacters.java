package ORACLE;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "abcabcbb";
        int result = lengthOfLongestSubstring(s);
        System.out.println("Length of the longest substring without repeating characters: " + result);
    }

    /**
     * Brute Force approach (O(n^2)) to find the longest substring without repeating characters.
     *
     * Time Complexity: O(n^2)
     * - Outer loop for starting index: O(n)
     * - Inner loop for ending index and Set checking: O(n)
     *
     * Space Complexity: O(k)
     * - Set can grow up to size of character set (e.g., 26 or 128 depending on charset).
     */
    public static int lengthOfLongestSubstringBruteForce(String s) {
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                if (seen.contains(ch)) {
                    break;
                }
                seen.add(ch);
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }

        return maxLength;
    }

    /**
     * Optimized solution using sliding window technique.
     * Finds the length of the longest substring without repeating characters using sliding window.
     *
     * Time Complexity: O(n)
     * - Each character is visited at most twice (once by j and once by i).
     *
     * Space Complexity: O(m)
     * - m is the size of the character set (e.g., 26 for lowercase letters, 128 for ASCII).
     * - In worst case (all characters unique), set stores up to m characters.
     */
    public static int lengthOfLongestSubstring(String s){
        int n = s.length();
        int maxLength = 0;
        int i = 0;
        Set<Character> set = new HashSet<>();

        for (int j = 0; j < n; j++) {
            while (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i));
                i++;
            }
            set.add(s.charAt(j));
            maxLength = Math.max(maxLength, j - i + 1);
        }

        return maxLength;
    }
}
//r