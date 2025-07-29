package ORACLE;

public class ReverseInteger {
    public static void main(String[] args) {
        int x = 123;
        int result = reverse(x);
        System.out.println("Reversed integer: " + result);
    }
    /**
     * Reverses the digits of an integer.
        * Time Complexity: O(n)
        * - Appending to and reversing the StringBuilder takes O(n) time,
        *   where n is the number of digits in x (maximum 10).
        * - Parsing substrings and full string to integers is also O(n).
        * - So overall time complexity is O(n), where n ≤ 10.

        * Space Complexity: O(n)
        * - StringBuilder and intermediate strings use O(n) space,
        *   where n is the number of digits (again, ≤ 10).
     */
    public static int reverse(int x) {
        StringBuilder sb = new StringBuilder();
        sb.append(Math.abs(x));
        sb.reverse();
        if(sb.length() >= 10){
            int c1 = Integer.parseInt(sb.substring(0,5));
            int c2 = Integer.parseInt(sb.substring(5,10));
            if(c1 > 21474 || c2 > 83647 ) return 0;
        }
        int ans = Integer.parseInt(sb.toString());
        return (x < 0) ? -ans : ans;
    }
    /**
    * Time Complexity: O(n)
    * - Where n is the number of digits in x (at most 10 for 32-bit integers).
    * - Each digit is processed once.

    * Space Complexity: O(1)
    * - Only a constant amount of extra space is used (rev, rem).
    * - No additional data structures.
    */
    public int reverseOpt(int x) {
        int rev = 0;
        while( x != 0){
            int rem = x % 10;
            x /= 10;
            if(rev > Integer.MAX_VALUE / 10 || rev < Integer.MIN_VALUE/10) return 0;
            rev = rev * 10 + rem;
        }
        return rev;
    }
}
