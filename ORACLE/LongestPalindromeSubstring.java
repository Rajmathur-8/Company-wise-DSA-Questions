package ORACLE;

import java.util.Arrays;

public class LongestPalindromeSubstring {
    public static void main(String[] args) {
        String s = "babad";
        String result = longestPalindrome(s);
        System.out.println("Longest palindromic substring: " + result);
    }
    /**
     * Finds the longest palindromic substring using a brute force approach.
     * 
     * Time Complexity: O(n^3)
     * - Outer loop for starting index: O(n)
     * - Inner loop for ending index: O(n)
     * - Checking if substring is palindrome: O(n)
     *
     * Space Complexity: O(1) (ignoring input string storage)
     */
    public static String longestPalindrome(String s) {
        int n = s.length();
        int maxLength = 0;
        int sp = 0;
        for(int i = 0;i<n;i++){
            for(int j = i;j<n;j++){
                if(isPalindrome(s,i,j)){
                    if(j-i+1 > maxLength){
                        maxLength = j-i+1;
                        sp = i;
                    }
                }
            }
        }
        return s.substring(sp,sp+maxLength);
    }
    public static boolean isPalindrome(String s,int i,int j){
        while(i<j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    /**
     * using Memoization to optimize the palindrome check
     * Time Complexity: O(n^2)
     * - Outer loop for starting index: O(n)    
     * - Inner loop for ending index: O(n)
     * Space Complexity: O(n^2) for the dp array
     * - dp[i][j] stores whether the substring s[i..j] is a palindrome
     */
    public static String longestPalindromeMemo(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int[] d : dp)Arrays.fill(d, -1);
        int maxLength = 0;
        int start = 0;
        for(int i = n-1;i>=0;i--){
            for(int j = i;j<n;j++){
                if(solve(i,j,s,dp) == 1){
                    if(j-i+1 > maxLength){
                        maxLength = j-i+1;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLength);
    }
    public static int solve(int i,int j,String s,int[][] dp){
        if(i >= j) return 1;
        if(dp[i][j] != -1) return dp[i][j];
        if(s.charAt(i) != s.charAt(i)) dp[i][j] = 0;
        return dp[i][j] = solve(i+1,j-1,s,dp);
    }
    /**
     * using Table DP to find the longest palindromic substring
     * Time Complexity: O(n^2) 
     *  - Outer loop for length of substring: O(n)
     *  - Inner loop for starting index: O(n)
     * Space Complexity: O(n^2) for the dp array
     * - dp[i][j] stores whether the substring s[i..j] is a palindrome
     */
    public static String longestPalindromeTable(String s){
        int n = s.length();
        int maxLength = 0;
        int start = 0;
        int[][] dp = new int[n][n];
        for(int i = 0;i<n;i++){
            dp[i][i] = 1; // Single character is a palindrome
        }
        for(int l = 2;l < n;l++){
            for(int i = 0;i < n-l+1;i++){
                int j = i + l -1;
                if(s.charAt(i) == s.charAt(j)){
                    if(l == 2 || dp[i+1][j-1]==1){
                        dp[i][j] = 1; // s[i..j] is a palindrome
                        if(l > maxLength){
                            maxLength = l;
                            start = i;
                        }
                    } else {
                        dp[i][j] = 0; // Not a palindrome
                    }
                }
            }
        }
        return s.substring(start, start + maxLength);
    }
}
