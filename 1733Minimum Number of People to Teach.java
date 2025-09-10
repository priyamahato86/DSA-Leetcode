// On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.

// You are given an integer n, an array languages, and an array friendships where:

// There are n languages numbered 1 through n,
// languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
// friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the users u​​​​​​​​​​​i​​​​​ and vi.
// You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.

// Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.

##CODE:
class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
Set<Integer> sadUsers = new HashSet<>(); // users who can't talk to their friend
        
        // Find sadUsers
        for (int[] friends : friendships) {
            int u = friends[0] - 1;
            int v = friends[1] - 1;
            
            // check if u and v share a common language
            Set<Integer> langSet = new HashSet<>();
            for (int lang : languages[u]) {
                langSet.add(lang);
            }
            
            boolean canTalk = false;
            for (int lang : languages[v]) {
                if (langSet.contains(lang)) {
                    canTalk = true;
                    break;
                }
            }
            
            // if they cannot talk, mark them as sad
            if (!canTalk) {
                sadUsers.add(u);
                sadUsers.add(v);
            }
        }
        
        // Count how many sadUsers already know each language
        int[] languageCount = new int[n + 1];
        int mostKnownLang = 0;
        
        for (int user : sadUsers) {
            for (int lang : languages[user]) {
                languageCount[lang]++;
                mostKnownLang = Math.max(mostKnownLang, languageCount[lang]);
            }
        }
        
        // total sadUsers - mostKnownLang
        return sadUsers.size() - mostKnownLang;
    }
}
 
