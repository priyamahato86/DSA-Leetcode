// You are given an integer array nums and two integers k and numOperations.

// You must perform an operation numOperations times on nums, where in each operation you:

// Select an index i that was not selected in any previous operations.
// Add an integer in the range [-k, k] to nums[i].
// Return the maximum possible frequency of any element in nums after performing the operations.

##CODE:
class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
                int maxEl = Arrays.stream(nums).max().getAsInt() + k;

        int[] freq = new int[maxEl + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // cumulative sum of frequencies
        for (int i = 1; i <= maxEl; i++) {
            freq[i] += freq[i - 1];
        }

        int result = 0;
        for (int target = 0; target <= maxEl; target++) {
            if (freq[target] == 0)
                continue;

            int leftNum = Math.max(0, target - k);
            int rightNum = Math.min(maxEl, target + k);

            int totalCount = freq[rightNum] - (leftNum > 0 ? freq[leftNum - 1] : 0);
            int targetCount = freq[target] - (target > 0 ? freq[target - 1] : 0);

            int needConversion = totalCount - targetCount;
            int maxPossibleFreq = targetCount + Math.min(needConversion, numOperations);

            result = Math.max(result, maxPossibleFreq);
        }

        return result;

    }
}
