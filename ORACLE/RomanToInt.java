package ORACLE;

public class RomanToInt {
    public static void main(String[] args) {
        String s = "MCMXCIV";
        int result = romanToInt(s);
        System.out.println("Integer value: " + result);
    }
    /**
     * Converts a Roman numeral to an integer.
     * 
     * Time Complexity: O(n)
     * - Each character in the string is processed once.
     * - The maximum length of a Roman numeral is 15 characters.
     *
     * Space Complexity: O(1)
     * - Only a constant amount of extra space is used for variables.
     */
    public static int romanToInt(String s) {
        int ans = 0, num = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }
    /**
     * Optimized version of Roman numeral to integer conversion.
     * 
     * Time Complexity: O(n)
     * - Each character in the string is processed once.
     *
     * Space Complexity: O(1)
     * - Only a constant amount of extra space is used for variables.
     */
    public int romanToIntOptimized(String s) {
        int ans = 0, prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int num = 0;
            switch(s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if (num < prev) ans -= num;
            else ans += num;
            prev = num;
        }
        return ans;
    }
}
