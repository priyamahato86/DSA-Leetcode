// Fruits are available at some positions on an infinite x-axis. You are given a 2D integer array fruits where fruits[i] = [positioni, amounti] depicts amounti fruits at the position positioni. fruits is already sorted by positioni in ascending order, and each positioni is unique.

// You are also given an integer startPos and an integer k. Initially, you are at the position startPos. From any position, you can either walk to the left or right. It takes one step to move one unit on the x-axis, and you can walk at most k steps in total. For every position you reach, you harvest all the fruits at that position, and the fruits will disappear from that position.

// Return the maximum total number of fruits you can harvest.

##CODE:
class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
       int n = fruits.length;
        int[] prefixSum = new int[n];
        int[] indices = new int[n];

        // Build prefix sum and extract indices
        for (int i = 0; i < n; i++) {
            indices[i] = fruits[i][0];
            prefixSum[i] = fruits[i][1] + (i > 0 ? prefixSum[i - 1] : 0);
        }

        int maxFrutis = 0;

        for (int d = 0; d <= k / 2; d++) {
            // First case: Move left 'd' steps then pick 'remain' steps
            int remain = k - 2 * d;
            int i = startPos - d;
            int j = startPos + remain;

            // Find the range [i, j] using binary search
            int left  = lowerBound(indices, i);
            int right = upperBound(indices, j) - 1;

            if (left <= right) {
                int total = prefixSum[right] - (left > 0 ? prefixSum[left - 1] : 0);
                maxFrutis = Math.max(maxFrutis, total);
            }

            // Second case: Move right 'd' steps then pick 'remain' steps
            remain = k - 2 * d;
            i  = startPos - remain;
            j = startPos + d;

            left = lowerBound(indices, i);
            right = upperBound(indices, j) - 1;

            if (left <= right) {
                int total = prefixSum[right] - (left > 0 ? prefixSum[left - 1] : 0);
                maxFrutis = Math.max(maxFrutis, total);
            }
        }

        return maxFrutis;
    }

    // Helper method to find the lower bound index
    private int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // Helper method to find the upper bound index
    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
