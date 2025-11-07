// You are given a 0-indexed integer array stations of length n, where stations[i] represents the number of power stations in the ith city.

// Each power station can provide power to every city in a fixed range. In other words, if the range is denoted by r, then a power station at city i can provide power to all cities j such that |i - j| <= r and 0 <= i, j <= n - 1.

// Note that |x| denotes absolute value. For example, |7 - 5| = 2 and |3 - 10| = 7.
// The power of a city is the total number of power stations it is being provided power from.

// The government has sanctioned building k more power stations, each of which can be built in any city, and have the same range as the pre-existing ones.

// Given the two integers r and k, return the maximum possible minimum power of a city, if the additional power stations are built optimally.

// Note that you can build the k power stations in multiple cities.

##CODE:
class Solution {
    private boolean check(long mid, long[] diff, int r, long k, int n) {
        long[] tempDiff = Arrays.copyOf(diff, n);
        long cumSum = 0;

        for (int i = 0; i < n; i++) {
            cumSum += tempDiff[i];

            if (cumSum < mid) {
                long need = mid - cumSum;
                if (need > k) {
                    return false;
                }

                k -= need;
                cumSum += need;

               
                if (i + 2L * r + 1 < n) {
                    tempDiff[(int)(i + 2L * r + 1)] -= need;
                }
            }
        }
        return true;
    }
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] diff = new long[n];

       
        for (int i = 0; i < n; i++) {
            int left = Math.max(0, i - r);
            int right = i + r + 1;
            diff[left] += stations[i];
            if (right < n) diff[right] -= stations[i];
        }

        long left = Arrays.stream(stations).min().getAsInt();
        long right = Arrays.stream(stations).asLongStream().sum() + k;
        long result = 0;

       
        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (check(mid, diff, r, k, n)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}
