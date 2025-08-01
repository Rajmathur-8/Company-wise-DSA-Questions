package ORACLE;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strs);
        System.out.println("Grouped anagrams: " + result);
    }

    /*
     * Brute-force approach:
     * 1. Sort each string and use it as a key in a HashMap.
     * 2. Group strings with the same sorted key together.
     * 3. Return the values of the HashMap as a list of lists.
     * Time Complexity: O(n * k log k)
     * - n is the number of strings, k is the maximum length of a string.
     * Space Complexity: O(n * k)
     * - O(n) for the HashMap to store grouped anagrams.
     *  -O(k) for the sorted string as the key.
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramMap = new HashMap<>();
        
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray); // Sort the characters of the string
            String key = new String(charArray); // Use sorted string as key
            
            anagramMap.putIfAbsent(key, new ArrayList<>()); // Initialize list if not present
            anagramMap.get(key).add(str); // Add original string to the list
        }
        
        return new ArrayList<>(anagramMap.values()); // Return grouped anagrams as a list of lists
    }
    /*
     * Optimized approach:
     * 1. Use a frequency count of characters as the key.
     * 2. Create a unique key based on character counts.
     * 3. Group strings with the same character count key together.
     * Time Complexity: O(n * k)
     * - n is the number of strings, k is the maximum length of a string.
     * Space Complexity: O(n)
     * - O(n) for the HashMap to store grouped anagrams.
     */
    public static List<List<String>> groupAnagramsOptimized(String[] strs) {
        Map<String, List<String>> anagramMap = new HashMap<>();
        
        for (String str : strs) {
            int[] charCount = new int[26]; // Assuming only lowercase letters a-z
            for (char c : str.toCharArray()) {
                charCount[c - 'a']++; // Count frequency of each character
            }
            StringBuilder keyBuilder = new StringBuilder();
            for (int count : charCount) {
                keyBuilder.append('#').append(count); // Create a unique key based on counts
            }
            String key = keyBuilder.toString();
            
            anagramMap.putIfAbsent(key, new ArrayList<>()); // Initialize list if not present
            anagramMap.get(key).add(str); // Add original string to the list
        }
        
        return new ArrayList<>(anagramMap.values()); // Return grouped anagrams as a list of lists
    }
}
