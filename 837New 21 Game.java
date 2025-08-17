// Alice plays the following game, loosely based on the card game "21".

// Alice starts with 0 points and draws numbers while she has less than k points. During each draw, she gains an integer number of points randomly from the range [1, maxPts], where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.

// Alice stops drawing numbers when she gets k or more points.

// Return the probability that Alice has n or fewer points.

// Answers within 10-5 of the actual answer are considered accepted.

##CODE:
class Solution {
    public double new21Game(int n, int k, int maxPts) {
         if (k == 0 || n >= k + maxPts) return 1.0;

        double[] dp = new double[n + 1];
        dp[0] = 1.0; // Probability of having 0 points is 1

        double windowSum = 1.0; // running sum of last maxPts probabilities
        double result = 0.0;

        for (int i = 1; i <= n; i++) {
            dp[i] = windowSum / maxPts;

            // If i < k, Alice can continue drawing, so add dp[i] to windowSum
            if (i < k) {
                windowSum += dp[i];
            } else {
                // If i >= k, Alice stops; count this as a valid end result
                result += dp[i];
            }

            // Slide the window: remove dp[i - maxPts] if it's outside the range
            if (i - maxPts >= 0) {
                windowSum -= dp[i - maxPts];
            }
        }

        return result;
    }
}
