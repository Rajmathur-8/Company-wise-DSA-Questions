package ORACLE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringwithConcatenationofAllWords {
    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        List<Integer> result = findSubstring(s, words);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
    // findSubstring method to find the starting index of the substring
    /**
     * pseudo-code:
     * 1. Initialize an empty list to store results.
     * 2. Calculate the length of the string and the number of words.
     * 3. If the string is empty or there are no words, return the empty list.
     * 4. Calculate the length of each word and the total length of all words combined.
     * 5. Create a frequency map to count occurrences of each word.
     * 6. Loop through the string, checking each substring of total length.
     * 7. For each substring, break it into words and count occurrences.
     * 8. If the counts match the frequency map, add the starting index to the results.
     * 9. Return the list of starting indices.
     * Time Complexity: O(n * m)
     * - n is the length of the string s
     * - m is the number of words
     * Space Complexity: O(m)
     * - O(m) for the frequency map to store word counts
     */
    public static List<Integer> findSubstringBrute(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int len = s.length();
        int wordCount = words.length;
        if (len == 0 || wordCount == 0) return ans;

        int wordLen = words[0].length();
        int totalLen = wordCount * wordLen;

        // Preprocess the frequency of words
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        // Brute force loop through all substrings of totalLen
        for (int i = 0; i <= len - totalLen; i++) {
            String sub = s.substring(i, i + totalLen);
            Map<String, Integer> seen = new HashMap<>();
            boolean valid = true;

            // Break substring into words and count
            for (int j = 0; j < totalLen; j += wordLen) {
                String word = sub.substring(j, j + wordLen);
                if (!freq.containsKey(word)) {
                    valid = false;
                    break;
                }
                seen.put(word, seen.getOrDefault(word, 0) + 1);
                if (seen.get(word) > freq.get(word)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ans.add(i);
            }
        }

        return ans;
    }
    /**
     * Optimized approach using sliding window 
     * 
     * Steps:
     * 1. Build a frequency map of the given words.
     * 2. Iterate over each offset from 0 to wordLen - 1.
     * 3. Use a sliding window of size wordLen to parse words.
     * 4. Use a map to track seen words and match frequencies.
     * 5. Adjust the window if any word is over-used.
     * 6. If all words match, record the starting index.
     * 
     * Time Complexity: O(N), where N is the length of s.
     * Space Complexity: O(M), where M is the number of words.
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return ans;

        int wordLen = words[0].length();
        int totalWords = words.length;
        int windowSize = wordLen * totalWords;
        int strLen = s.length();

        if (strLen < windowSize) return ans;

        // Step 1: Frequency map of words
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        // Step 2: Sliding window for each offset
        for (int i = 0; i < wordLen; i++) {
            int start = i;
            int count = 0;
            Map<String, Integer> seen = new HashMap<>();

            for (int j = i; j <= strLen - wordLen; j += wordLen) {
                String word = s.substring(j, j + wordLen);

                if (freq.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;

                    // Step 3: Shrink window if word appears too many times
                    while (seen.get(word) > freq.get(word)) {
                        String leftWord = s.substring(start, start + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        count--;
                        start += wordLen;
                    }

                    // Step 4: Valid window match
                    if (count == totalWords) {
                        ans.add(start);
                    }
                } else {
                    // Reset if word is not valid
                    seen.clear();
                    count = 0;
                    start = j + wordLen;
                }
            }
        }

        return ans;
    }

}
