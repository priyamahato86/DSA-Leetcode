// You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

// Find two lines that together with the x-axis form a container, such that the container contains the most water.

// Return the maximum amount of water a container can store.

// Notice that you may not slant the container.

##CODE:
class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int maxWater = 0;

        int lp = 0;
        int rp = n - 1;

        while (lp < rp) {
            int width = rp - lp;
            int minHeight = Math.min(height[rp], height[lp]);
            int water = minHeight * width;
            maxWater = Math.max(maxWater, water);

            if (height[lp] < height[rp]) {
                lp += 1;
            } else {
                rp -= 1;
            }
        }

        return maxWater;
    }
}
