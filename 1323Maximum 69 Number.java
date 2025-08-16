// You are given a positive integer num consisting only of digits 6 and 9.

// Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).

##CODE:
class Solution {
    public int maximum69Number (int num) {
        int place = 0;
        int index = -1;
        int temp = num;
        
        while (temp > 0) {
            int remain = temp % 10;
            if (remain == 6) {
                index = place;
            }
            temp /= 10;
            place++;
        }
        
        if (index == -1) return num;
        
        return num + 3 * (int)Math.pow(10, index);

    }
}
