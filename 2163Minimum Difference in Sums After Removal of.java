// You are given a 0-indexed integer array nums consisting of 3 * n elements.

// You are allowed to remove any subsequence of elements of size exactly n from nums. The remaining 2 * n elements will be divided into two equal parts:

// The first n elements belonging to the first part and their sum is sumfirst.
// The next n elements belonging to the second part and their sum is sumsecond.
// The difference in sums of the two parts is denoted as sumfirst - sumsecond.

// For example, if sumfirst = 3 and sumsecond = 2, their difference is 1.
// Similarly, if sumfirst = 2 and sumsecond = 3, their difference is -1.
// Return the minimum difference possible between the sums of the two parts after the removal of n elements.

##CODE:
class Solution {
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        int len = nums.length;

        // Store prefix minimum sums (left n elements from 0 to 2n-1)
        long[] leftSum = new long[len];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long leftTotal = 0;

        for (int i = 0; i < len; i++) {
            maxHeap.offer(nums[i]);
            leftTotal += nums[i];
            if (maxHeap.size() > n) {
                leftTotal -= maxHeap.poll();
            }
            if (maxHeap.size() == n) {
                leftSum[i] = leftTotal;
            } else {
                leftSum[i] = Long.MAX_VALUE;
            }
        }

        // Store suffix maximum sums (right n elements from len-1 to n)
        long[] rightSum = new long[len];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long rightTotal = 0;

        for (int i = len - 1; i >= 0; i--) {
            minHeap.offer(nums[i]);
            rightTotal += nums[i];
            if (minHeap.size() > n) {
                rightTotal -= minHeap.poll();
            }
            if (minHeap.size() == n) {
                rightSum[i] = rightTotal;
            } else {
                rightSum[i] = Long.MIN_VALUE;
            }
        }

        // Find minimal difference between prefix sum and suffix sum
        long minDiff = Long.MAX_VALUE;
        for (int i = n - 1; i < 2 * n; i++) {
            if (leftSum[i] != Long.MAX_VALUE && rightSum[i + 1] != Long.MIN_VALUE) {
                minDiff = Math.min(minDiff, leftSum[i] - rightSum[i + 1]);
            }
        }

        return minDiff;
    }
}
