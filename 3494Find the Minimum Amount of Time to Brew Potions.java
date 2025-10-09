// You are given two integer arrays, skill and mana, of length n and m, respectively.

// In a laboratory, n wizards must brew m potions in order. Each potion has a mana capacity mana[j] and must pass through all the wizards sequentially to be brewed properly. The time taken by the ith wizard on the jth potion is timeij = skill[i] * mana[j].

// Since the brewing process is delicate, a potion must be passed to the next wizard immediately after the current wizard completes their work. This means the timing must be synchronized so that each wizard begins working on a potion exactly when it arrives. ​

// Return the minimum amount of time required for the potions to be brewed properly.

##CODE:
class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        long[] finishTime = new long[n]; /

        for (int j = 0; j < m; ++j) {
            finishTime[0] += (long) mana[j] * skill[0];

            for (int i = 1; i < n; ++i) {
                finishTime[i] = Math.max(finishTime[i], finishTime[i - 1]) + (long) mana[j] * skill[i];
            }

            for (int i = n - 1; i > 0; --i) {
                finishTime[i - 1] = finishTime[i] - (long) mana[j] * skill[i];
            }
        }

        return finishTime[n - 1];
    }
}
