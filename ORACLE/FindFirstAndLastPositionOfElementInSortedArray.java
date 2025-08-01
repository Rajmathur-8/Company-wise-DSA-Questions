package ORACLE;

public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = searchRange(nums, target);
        System.out.println("First and Last Position: [" + result[0] + ", " + result[1] + "]");
    }
    /*
     * Finds the first and last position of a target element in a sorted array.
     * this is a brute force approach.
     * pseudo-code:
     * 1. Initialize two variables, first and last, to -1.
     * 2. Iterate through the array:
     *   a. If the current element equals the target, check if first is -1; if so, set first to the current index.
     *   b. Always update last to the current index if the current element equals the target.
     * 3. Return an array containing first and last.
     * 
     * Time Complexity: O(n) - where n is the length of the array
     * Space Complexity: O(1) - no extra space used
     * 
     */
    public static int[] searchRangeBrute(int[] nums,int target){
        int first = -1, last = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) {
                    first = i; // Found first occurrence
                }
                last = i; // Always update last occurrence
            }
        }
        return new int[]{first, last};
    }
    /*
     * Optimized binary search approach to find the first and last position of a target element.
     * pseudo-code:
     * 1. Define a helper function to find the leftmost index of the target.
     * 2. Use binary search to narrow down the range:
     *   a. If mid element is less than target, move left pointer to mid + 1.
     *   b. If mid element is greater than or equal to target, move right pointer to mid.
     * 3. Repeat until left pointer is less than right pointer.
     * 4. Define another helper function to find the rightmost index of the target.
     * 5. Return an array containing the leftmost and rightmost indices.
     * 
     * Time Complexity: O(log n) - where n is the length of the array
     * Space Complexity: O(1) - no extra space used
     */
    public static int[] searchRange(int[] nums, int target) {
        int left = findLeftIndex(nums, target);
        int right = findRightIndex(nums, target);
        return new int[]{left, right};
    }
    public static int findLeftIndex(int[] nums,int target){
        int n = nums.length;
        int l = 0,r = n-1;
        int ans = -1;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(nums[mid] == target){
                ans = mid;
                r = mid-1;
            }else if(nums[mid]>target){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return ans;
    }
    public static int findRightIndex(int[] nums,int target){
        int n = nums.length;
        int l = 0,r = n-1;
        int ans = -1;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(nums[mid] == target){
                ans = mid;
                l = mid+1;
            }else if(nums[mid]>target){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return ans;
    }
}
