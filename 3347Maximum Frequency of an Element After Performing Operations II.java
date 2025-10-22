// You are given an integer array nums and two integers k and numOperations.

// You must perform an operation numOperations times on nums, where in each operation you:

// Select an index i that was not selected in any previous operations.
// Add an integer in the range [-k, k] to nums[i].
// Return the maximum possible frequency of any element in nums after performing the operations.

##CODE:
class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int res = 0;
        Arrays.sort(nums);
        int left = 0;
        int right = 0;
        int n = nums.length;
        int i = 0;
        // case 1, num is in the arr
        while (i < n) {
            int val = nums[i];
            int same = 0;
            while (i < n && nums[i] == val) {
                same++;
                i++;
            }
            while (right < n && nums[right] <= val + k) {
                right++;
            }
            while (left < n && nums[left] < val - k) {
                left++;
            }
            // FIX 1: Replaced 'ops' with 'numOperations'
            res = Math.max(res, Math.min(same + numOperations, right - left));
        }
        // case 2, num is not in the arr
        left = 0;
        right = 0;
        while (right < n) {
            while (right < n && (long) nums[left] + k + k >= nums[right]) {
                right++;
            }
            // FIX 2: Replaced 'ops' with 'numOperations'
            res = Math.max(res, Math.min(right - left, numOperations));
            left++;
        }
        return res;
    }
}
