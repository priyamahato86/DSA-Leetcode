// You are given an array nums of n integers and two integers k and x.

// The x-sum of an array is calculated by the following procedure:

// Count the occurrences of all elements in the array.
// Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences, the element with the bigger value is considered more frequent.
// Calculate the sum of the resulting array.
// Note that if an array has less than x distinct elements, its x-sum is the sum of the array.

// Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].

##CODE:

class Solution {
    long sum = 0; 
    TreeSet<int[]> main; 
    TreeSet<int[]> sec;  
    Map<Integer, Integer> freq; 
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        sum = 0;
        freq = new HashMap<>();

        Comparator<int[]> comp = (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        };

        main = new TreeSet<>(comp);
        sec = new TreeSet<>(comp);

        List<Long> resultList = new ArrayList<>();

        int i = 0;
        int j = 0;
        while (j < n) {
            int num = nums[j];

            
            if (freq.getOrDefault(num, 0) > 0) {
                removeFromSet(new int[]{freq.get(num), num}, x);
            }

           
            freq.put(num, freq.getOrDefault(num, 0) + 1);

           
            insertInSet(new int[]{freq.get(num), num}, x);

            
            if (j - i + 1 == k) {
                resultList.add(sum);

                
                int outNum = nums[i];
                removeFromSet(new int[]{freq.get(outNum), outNum}, x);
                freq.put(outNum, freq.get(outNum) - 1);

                if (freq.get(outNum) == 0) {
                    freq.remove(outNum);
                } else {
                    insertInSet(new int[]{freq.get(outNum), outNum}, x);
                }

                i++;
            }

            j++;
        }

        
        long[] result = new long[resultList.size()];
        for (int idx = 0; idx < resultList.size(); idx++) {
            result[idx] = resultList.get(idx);
        }
        return result;
    }

    void insertInSet(int[] p, int x) {
        if (main.size() < x || comparePairs(p, main.first()) > 0) {
            sum += 1L * p[0] * p[1];
            main.add(p);

            if (main.size() > x) {
                int[] smallest = main.first();
                sum -= 1L * smallest[0] * smallest[1];
                main.remove(smallest);
                sec.add(smallest);
            }
        } else {
            sec.add(p);
        }
    }

    void removeFromSet(int[] p, int x) {
        if (main.contains(p)) {
            sum -= 1L * p[0] * p[1];
            main.remove(p);

            if (!sec.isEmpty()) {
                int[] largest = sec.last();
                sec.remove(largest);
                main.add(largest);
                sum += 1L * largest[0] * largest[1];
            }
        } else {
            sec.remove(p);
        }
    }

    int comparePairs(int[] a, int[] b) {
        if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
        return Integer.compare(a[1], b[1]);
    }
}
