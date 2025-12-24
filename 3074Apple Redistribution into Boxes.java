// You are given an array apple of size n and an array capacity of size m.

// There are n packs where the ith pack contains apple[i] apples. There are m boxes as well, and the ith box has a capacity of capacity[i] apples.

// Return the minimum number of boxes you need to select to redistribute these n packs of apples into boxes.

// Note that, apples from the same pack can be distributed into different boxes.

##CODE:

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalApple = 0;
        for (int a : apple) {
            totalApple += a;
        }

        int[] freq = new int[51];
        for (int cap : capacity) {
            freq[cap]++;
        }

        int count = 0;

        for (int cap = 50; cap >= 1 && totalApple > 0; cap--) {
            while (freq[cap] > 0 && totalApple > 0) {
                totalApple -= cap;
                freq[cap]--;
                count++;
            }
        }

        return count;
    }
}
