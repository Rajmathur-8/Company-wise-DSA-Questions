package ORACLE;

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSumOpt(nums);
        System.out.println("Triplets that sum to zero: " + result);
    }
    /**
     * Brute Force approach to find all unique triplets in the array that sum to zero.
     * Time Complexity: O(n^3)
     * - Three nested loops to check all combinations of triplets.
     * Space Complexity: O(k)
     * - k is the number of unique triplets found, which can grow up to O(n^3) in the worst case.
     * This approach is not efficient for large arrays but is straightforward.
     */
    public static List<List<Integer>> threeSumBrute(int[] nums){
        Set<List<Integer>> result = new HashSet<>();
        int n = nums.length;
        for(int i = 0;i<n-2;i++){
            for(int j = i + 1;j<n-1;j++){
                for(int k = j+1;k<n;k++){
                    if(nums[i] + nums[j] + nums[k] == 0){
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(triplet); // Sort to avoid duplicates
                        result.add(triplet);
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    /**
    * Optimized Two-Pointer approach to find all unique triplets in the array that sum to zero.
    *
    * Time Complexity: O(n^2)
    * - Sorting takes O(n log n).
    * - Outer loop runs O(n) times.
    * - Inner two-pointer traversal takes O(n) for each iteration of the outer loop.
    * - So overall: O(n log n + n^2) = O(n^2)
    *
    * Space Complexity: O(k)
    * - O(k) space for the output list of triplets (where k is the number of valid triplets).
    * - Constant extra space used aside from the result list.
    *
    * This approach is much faster than brute-force and handles duplicates efficiently.
    */
    public static List<List<Integer>> threeSumOpt(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // Sort the array to use two-pointer technique
        int n = nums.length;
        for(int i = 0;i< n-2;i++){
            if(i > 0 && nums[i] == nums[i-1]) continue; // skip duplicates
            int j = i + 1;
            int k = n - 1;
            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    while(j < k && nums[j] == nums[j + 1]) j++; // skip duplicates
                    while(j < k && nums[k] == nums[k - 1]) k--; // skip duplicates
                    j++;
                    k--;
                }
                else if(sum < 0){
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
}
