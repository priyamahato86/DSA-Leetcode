// Given an integer array nums, return the number of subarrays filled with 0.

// A subarray is a contiguous non-empty sequence of elements within an array.

##CODE:
class Solution {
    public long zeroFilledSubarray(int[] nums) {
long result = 0;
        
        int n = nums.length;
        
        long countSubArray = 0;
        
        for (int i = 0; i < n; i++) {
            
            if (nums[i] == 0)
                countSubArray += 1;
            else
                countSubArray = 0;
            
            result += countSubArray;
        }
        
        return result;
    }
}
