// You are given a string s and an integer k.

// First, you are allowed to change at most one index in s to another lowercase English letter.

// After that, do the following partitioning operation until s is empty:

// Choose the longest prefix of s containing at most k distinct characters.
// Delete the prefix from s and increase the number of partitions by one. The remaining characters (if any) in s maintain their initial order.
// Return an integer denoting the maximum number of resulting partitions after the operations by optimally choosing at most one index to change.

##CODE:
class Solution {
    private Map<Long, Integer> mp = new HashMap<>();
    private String S;
    private int K;

    public int solve(long i, long uniqueChars, boolean canChange) {
        long key = (i << 27) | (uniqueChars << 1) | (canChange ? 1 : 0);

        if (mp.containsKey(key)) {
            return mp.get(key);
        }

        if (i == S.length()) {
            return 0;
        }

        int characterIndex = S.charAt((int) i) - 'a';
        long uniqueCharsUpdated = uniqueChars | (1L << characterIndex);
        int uniqueCharCount = Long.bitCount(uniqueCharsUpdated);

        int result;
        if (uniqueCharCount > K) {
            result = 1 + solve(i + 1, 1L << characterIndex, canChange);
        } else {
            result = solve(i + 1, uniqueCharsUpdated, canChange);
        }

        if (canChange) {
            for (int newCharIndex = 0; newCharIndex < 26; ++newCharIndex) {
                long newSet = uniqueChars | (1L << newCharIndex);
                int newUniqueCharCount = Long.bitCount(newSet);

                if (newUniqueCharCount > K) {
                    result = Math.max(result, 1 + solve(i + 1, 1L << newCharIndex, false));
                } else {
                    result = Math.max(result, solve(i + 1, newSet, false));
                }
            }
        }

        mp.put(key, result);
        return result;
    }
    public int maxPartitionsAfterOperations(String s, int k) {
        S = s;
        K = k;
        return solve(0, 0, true) + 1;
    }
}
