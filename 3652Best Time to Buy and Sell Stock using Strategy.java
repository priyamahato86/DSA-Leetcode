// You are given two integer arrays prices and strategy, where:

// prices[i] is the price of a given stock on the ith day.
// strategy[i] represents a trading action on the ith day, where:
// -1 indicates buying one unit of the stock.
// 0 indicates holding the stock.
// 1 indicates selling one unit of the stock.
// You are also given an even integer k, and may perform at most one modification to strategy. A modification consists of:

// Selecting exactly k consecutive elements in strategy.
// Set the first k / 2 elements to 0 (hold).
// Set the last k / 2 elements to 1 (sell).
// The profit is defined as the sum of strategy[i] * prices[i] across all days.

// Return the maximum possible profit you can achieve.

// Note: There are no constraints on budget or stock ownership, so all buy and sell operations are feasible regardless of past actions.

##CODE:

class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;

        long actualProfit = 0;
        long[] profit = new long[n]; 

        for (int idx = 0; idx < n; idx++) {
            profit[idx] = (long) strategy[idx] * prices[idx];
            actualProfit += profit[idx];
        }

        long originalWindowProfit = 0;
        long modifiedWindowProfit = 0;
        long maxGain = 0; 

        int i = 0, j = 0;

        while (j < n) {

            originalWindowProfit += profit[j];

            
            if (j - i + 1 > k / 2) {
                modifiedWindowProfit += prices[j];
            }

            if (j - i + 1 > k) {
                originalWindowProfit -= profit[i];
                modifiedWindowProfit -= prices[i + k / 2];
                i++;
            }

            
            if (j - i + 1 == k) {
                maxGain = Math.max(maxGain, modifiedWindowProfit - originalWindowProfit);
            }

            j++;
        }

        return actualProfit + maxGain;
    }
}
