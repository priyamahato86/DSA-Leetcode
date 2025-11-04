// You are given an array nums of n integers and two integers k and x.

// The x-sum of an array is calculated by the following procedure:

// Count the occurrences of all elements in the array.
// Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences, the element with the bigger value is considered more frequent.
// Calculate the sum of the resulting array.
// Note that if an array has less than x distinct elements, its x-sum is the sum of the array.

// Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].

##CODE:

class Solution {
     private int findTopXSum(Map<Integer, Integer> map, int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]
        );

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            int val = entry.getKey();
            pq.offer(new int[]{freq, val});

            if (pq.size() > x) {
                pq.poll();
            }
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            sum += pair[0] * pair[1];
        }

        return sum;
    }
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        int i = 0, j = 0;

        while (j < n) {
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

            if (j - i + 1 == k) {
                result.add(findTopXSum(map, x));

                map.put(nums[i], map.get(nums[i]) - 1);
                if (map.get(nums[i]) == 0) map.remove(nums[i]);
                i++;
            }

            j++;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
