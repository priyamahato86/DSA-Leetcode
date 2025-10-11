// A magician has various spells.

// You are given an array power, where each element represents the damage of a spell. Multiple spells can have the same damage value.

// It is a known fact that if a magician decides to cast a spell with a damage of power[i], they cannot cast any spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.

// Each spell can be cast only once.

// Return the maximum possible total damage that a magician can cast.

##CODE:
class Solution {
    public long maximumTotalDamage(int[] power) {
         Map<Long, Long> freq = new HashMap<>();
        for (int x : power)
            freq.put((long)x, freq.getOrDefault((long)x, 0L) + 1);

        
        List<Long> nums = new ArrayList<>(freq.keySet());
        Collections.sort(nums);
        int n = nums.size();
        long[] dp = new long[n];

        long result = Long.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {

            long skip = (i + 1 < n) ? dp[i + 1] : 0;

    
            int j = lowerBound(nums, i + 1, nums.get(i) + 3);
            long take = nums.get(i) * freq.get(nums.get(i)) + ((j < n) ? dp[j] : 0);

            dp[i] = Math.max(skip, take);
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    
    private int lowerBound(List<Long> nums, int start, long target) {
        int low = start, high = nums.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums.get(mid) < target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}
