// You are given two non-negative integers num1 and num2.

// In one operation, if num1 >= num2, you must subtract num2 from num1, otherwise subtract num1 from num2.

// For example, if num1 = 5 and num2 = 4, subtract num2 from num1, thus obtaining num1 = 1 and num2 = 4. However, if num1 = 4 and num2 = 5, after one operation, num1 = 4 and num2 = 1.
// Return the number of operations required to make either num1 = 0 or num2 = 0.

##CODE:
class Solution {
    public int countOperations(int num1, int num2) {
         if (num1 == 0 || num2 == 0)
            return 0;

        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }

        return num1 / num2 + countOperations(num1 % num2, num2);
    }
}
