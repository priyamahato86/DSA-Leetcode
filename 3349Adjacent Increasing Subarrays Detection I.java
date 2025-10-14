// Given an array nums of n integers and an integer k, determine whether there exist two adjacent subarrays of length k such that both subarrays are strictly increasing. Specifically, check if there are two subarrays starting at indices a and b (a < b), where:

// Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
// The subarrays must be adjacent, meaning b = a + k.
// Return true if it is possible to find two such subarrays, and false otherwise.

##CODE:
class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();

        int currRun = 1;
        int prevRun = 0;

        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                currRun++;
            } else { // increasing order breaks here
                prevRun = currRun;
                currRun = 1;
            }

            // if current run itself can fit two subarrays of length k
            if (currRun >= 2 * k) {
                return true;
            }

            // if current and previous increasing runs can fit one subarray each
            if (Math.min(currRun, prevRun) >= k) {
                return true;
            }
        }

        return false;

    }
}

