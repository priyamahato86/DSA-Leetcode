// You are given a 0-indexed array nums of length n, consisting of non-negative integers. For each index i from 0 to n - 1, you must determine the size of the minimum sized non-empty subarray of nums starting at i (inclusive) that has the maximum possible bitwise OR.

// In other words, let Bij be the bitwise OR of the subarray nums[i...j]. You need to find the smallest subarray starting at i, such that bitwise OR of this subarray is equal to max(Bik) where i <= k <= n - 1.
// The bitwise OR of an array is the bitwise OR of all the numbers in it.

// Return an integer array answer of size n where answer[i] is the length of the minimum sized subarray starting at i with maximum bitwise OR.

// A subarray is a contiguous non-empty sequence of elements within an array.

##CODE:
class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int[] setBitIndex = new int[31];
        Arrays.fill(setBitIndex, -1);

        for (int i = n - 1; i >= 0; i--) {
            int endIndex = i;
            for (int j = 0; j < 31; j++) {
                if ((nums[i] & (1 << j)) == 0) {
                    if (setBitIndex[j] != -1) {
                        endIndex = Math.max(endIndex, setBitIndex[j]);
                    }
                } else {
                    setBitIndex[j] = i;
                }
            }
            result[i] = endIndex - i + 1;
        }

        return result;
    }
}
