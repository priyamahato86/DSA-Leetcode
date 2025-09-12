// Alice and Bob are playing a game on a string.

// You are given a string s, Alice and Bob will take turns playing the following game where Alice starts first:

// On Alice's turn, she has to remove any non-empty substring from s that contains an odd number of vowels.
// On Bob's turn, he has to remove any non-empty substring from s that contains an even number of vowels.
// The first player who cannot make a move on their turn loses the game. We assume that both Alice and Bob play optimally.

// Return true if Alice wins the game, and false otherwise.

// The English vowels are: a, e, i, o, and u.

##CODE:
class Solution {
    public boolean doesAliceWin(String s) {
         for (char ch : s.toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                return true;
            }
        }
        return false;
    }
}
