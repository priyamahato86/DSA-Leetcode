// You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

// Return the maximum score you can get by erasing exactly one subarray.

// An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

##CODE:
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] cus = new int[n]; 
        cus[0] = nums[0];
        for(int i = 1; i < n; i++)
            cus[i] = cus[i-1] + nums[i];

        int[] mp = new int[10001];
        Arrays.fill(mp, -1);
        
        int maxS = 0;
        int i = 0;
        int j = 0;

        while(j < n) {
            i       = Math.max(i, mp[nums[j]] + 1); 
            int jthSum  = cus[j];
            int ithSum  = i-1 < 0 ? 0 : cus[i-1];
            maxS        = Math.max(maxS, jthSum - ithSum);
            mp[nums[j]] = j;

            j++;
        }

        return maxS;
    }
}
