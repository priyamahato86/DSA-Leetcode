// You are given two integers, m and k, and an integer array nums.

// A sequence of integers seq is called magical if:
// seq has a size of m.
// 0 <= seq[i] < nums.length
// The binary representation of 2seq[0] + 2seq[1] + ... + 2seq[m - 1] has k set bits.
// The array product of this sequence is defined as prod(seq) = (nums[seq[0]] * nums[seq[1]] * ... * nums[seq[m - 1]]).

// Return the sum of the array products for all valid magical sequences.

// Since the answer may be large, return it modulo 109 + 7.

// A set bit refers to a bit in the binary representation of a number that has a value of 1.

##CODE:
class Solution {
    static final int MOD = 1_000_000_007;
    int N, K;
    long[] fact, invFact;
    Map<String, Long> memo;

    
    long findPower(long a, long b) {
        if (b == 0) return 1;
        long half = findPower(a, b / 2);
        long result = (half * half) % MOD;
        if (b % 2 == 1) result = (result * a) % MOD;
        return result;
    }

    
    long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
    }

    long solve(long binarySum, int m, int k, int i, int[] nums) {
        String key = binarySum + "," + m + "," + k + "," + i;
        if (memo.containsKey(key)) return memo.get(key);

        if (m == 0 && Long.bitCount(binarySum) == k)
            return 1;
        if (m == 0 || i >= N)
            return 0;

        long totalSum = 0;

        
        totalSum = (totalSum + solve(binarySum >> 1, m, k - (int)(binarySum & 1), i + 1, nums)) % MOD;

        
        for (int freq = 1; freq <= m; freq++) {
            long newBinarySum = binarySum + freq;
            long prod = solve(newBinarySum >> 1, m - freq, k - (int)(newBinarySum & 1), i + 1, nums);

            prod = (findPower(nums[i], freq) % MOD * prod % MOD) % MOD;
            prod = (prod * nCr(m, freq)) % MOD;
            totalSum = (totalSum + prod) % MOD;
        }

        memo.put(key, totalSum);
        return totalSum;
    }
    public int magicalSum(int m, int k, int[] nums) {
        this.K = k;
        this.N = nums.length;

        fact = new long[m + 1];
        invFact = new long[m + 1];
        memo = new HashMap<>();

        fact[0] = 1;
        for (int i = 1; i <= m; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        for (int i = 0; i <= m; i++) {
            invFact[i] = findPower(fact[i], MOD - 2);
        }

        return (int)(solve(0L, m, k, 0, nums) % MOD);
    }
}
