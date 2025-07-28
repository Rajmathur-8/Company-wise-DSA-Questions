package ORACLE;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TwoSum{

    //Brute force solution
    //Time complexity: O(n^2)
    public static int[] twoSum(int[] nums,int target){
        int n = nums.length;
        if (n < 2) {
            return new int[]{-1, -1}; // Return -1, -1 if not enough elements
        }
        int[] result = new int[2];
        for(int i = 0;i<n;i++){
            for(int j = i+1;j<n;j++){
                if(nums[i]+nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return new int[]{-1, -1}; // Return -1, -1 if no solution found
    }
    //Optimized solution using HashMap
    //Time complexity: O(n)
    public static int[] twoSumOptimized(int[] nums,int target){
        int n = nums.length;
        if (n < 2) return new int[]{-1, -1}; // Not enough elements

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int remaining = target - nums[i];
            if (map.containsKey(remaining)) {
                return new int[]{map.get(remaining), i}; // Found the pair
            }
            map.put(nums[i], i); // Store value and index
        }

        return new int[]{-1, -1}; // No pair found
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();
       // int[] result = twoSum(nums, target);
        int[] result = twoSumOptimized(nums, target);
        if (result != null) {
            System.out.println(result[0] + " " + result[1]);
        } else {
            System.out.println("No solution found");
        }
        sc.close();
    }

}