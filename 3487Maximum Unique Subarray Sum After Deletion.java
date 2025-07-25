// You are given an integer array nums.

// You are allowed to delete any number of elements from nums without making it empty. After performing the deletions, select a subarray of nums such that:

// All elements in the subarray are unique.
// The sum of the elements in the subarray is maximized.
// Return the maximum sum of such a subarray.

##CODE:
class Solution {
    public int maxSum(int[] nums) {
int[] mp = new int[101];
        Arrays.fill(mp, -1); // initialize to -1
        
        int sum = 0;
        int maxNeg = Integer.MIN_VALUE;
        
        for(int num : nums) {
            if(num <= 0) {
                maxNeg = Math.max(maxNeg, num);
            } else if(mp[num] == -1) {
                sum += num;
                mp[num] = 1; 
            }
        }

        return sum > 0 ? sum : maxNeg;
    }
}
