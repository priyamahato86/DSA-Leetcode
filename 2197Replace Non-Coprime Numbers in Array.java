// You are given an array of integers nums. Perform the following steps:

// Find any two adjacent numbers in nums that are non-coprime.
// If no such numbers are found, stop the process.
// Otherwise, delete the two numbers and replace them with their LCM (Least Common Multiple).
// Repeat this process as long as you keep finding two adjacent non-coprime numbers.
// Return the final modified array. It can be shown that replacing adjacent non-coprime numbers in any arbitrary order will lead to the same result.

// The test cases are generated such that the values in the final array are less than or equal to 108.

// Two values x and y are non-coprime if GCD(x, y) > 1 where GCD(x, y) is the Greatest Common Divisor of x and y.

##CODE:
class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            while (!result.isEmpty()) {
                int prev = result.get(result.size() - 1);
                int g = gcd(prev, num);

                if (g == 1) {
                    break; 
                }

                
                result.remove(result.size() - 1);
                long lcm = (long) prev / g * num; 
                num = (int) lcm;
            }
            result.add(num);
        }

        return result;
    }


    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
