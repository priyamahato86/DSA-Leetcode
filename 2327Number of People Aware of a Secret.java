// On day 1, one person discovers a secret.

// You are given an integer delay, which means that each person will share the secret with a new person every day, starting from delay days after discovering the secret. You are also given an integer forget, which means that each person will forget the secret forget days after discovering it. A person cannot share the secret on the same day they forgot it, or on any day afterwards.

// Given an integer n, return the number of people who know the secret at the end of day n. Since the answer may be very large, return it modulo 109 + 7.

##CODE:
class Solution {
     int MOD = 1_000_000_007;
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int[] t = new int[n + 1];
        // t[day] = number of people who first learn the secret on "day"

        t[1] = 1;
        long count = 0; // number of people who can share the secret today

        for (int day = 2; day <= n; day++) {
            // people who become eligible to share on this day
            if (day - delay > 0) {
                count = (count + t[day - delay]) % MOD;
            }
            // people who forget on this day
            if (day - forget > 0) {
                count = (count - t[day - forget] + MOD) % MOD;
            }
            t[day] = (int) count; // number of people who learn on this day
        }

        // count the people who still remember on day n
        int result = 0;
        for (int day = n - forget + 1; day <= n; day++) {
            if (day > 0) {
                result = (result + t[day]) % MOD;
            }
        }

        return result;
    }
}
