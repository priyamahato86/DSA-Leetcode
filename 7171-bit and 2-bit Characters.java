// We have two special characters:

// The first character can be represented by one bit 0.
// The second character can be represented by two bits (10 or 11).
// Given a binary array bits that ends with 0, return true if the last character must be a one-bit character.

##CODE:
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int count1 = 0;

        for (int i = n - 2; i >= 0 && bits[i] == 1; i--) {
            count1++;
        }

        return (count1 % 2 == 0);
    }
}
