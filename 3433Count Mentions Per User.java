// You are given an integer numberOfUsers representing the total number of users and an array events of size n x 3.

// Each events[i] can be either of the following two types:

// Message Event: ["MESSAGE", "timestampi", "mentions_stringi"]
// This event indicates that a set of users was mentioned in a message at timestampi.
// The mentions_stringi string can contain one of the following tokens:
// id<number>: where <number> is an integer in range [0,numberOfUsers - 1]. There can be multiple ids separated by a single whitespace and may contain duplicates. This can mention even the offline users.
// ALL: mentions all users.
// HERE: mentions all online users.
// Offline Event: ["OFFLINE", "timestampi", "idi"]
// This event indicates that the user idi had become offline at timestampi for 60 time units. The user will automatically be online again at time timestampi + 60.
// Return an array mentions where mentions[i] represents the number of mentions the user with id i has across all MESSAGE events.

// All users are initially online, and if a user goes offline or comes back online, their status change is processed before handling any message event that occurs at the same timestamp.

// Note that a user can be mentioned multiple times in a single message event, and each mention should be counted separately.

##CODE:

class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] mentionCount = new int[numberOfUsers];
        int[] offlineTime = new int[numberOfUsers];

        Collections.sort(events, (a, b) -> {

            int t1 = Integer.parseInt(a.get(1));
            int t2 = Integer.parseInt(b.get(1));

            if (t1 == t2) {
                char c1 = a.get(0).charAt(1);
                char c2 = b.get(0).charAt(1);

                if (c1 > c2) return -1;
                if (c1 < c2) return 1;
                return 0;
            }

            return Integer.compare(t1, t2);
        });

        for (List<String> event : events) {
            if (event.get(0).equals("MESSAGE")) {
                applyMessageEvent(event, mentionCount, offlineTime);

            } else if (event.get(0).equals("OFFLINE")) {
                int timestamp = Integer.parseInt(event.get(1));
                int id = Integer.parseInt(event.get(2));
                offlineTime[id] = timestamp;
            }
        }

        return mentionCount;
    }

    private void applyMessageEvent(List<String> event, int[] mentionCount, int[] offlineTime) {
        int timestamp = Integer.parseInt(event.get(1));
        String[] ids = event.get(2).split(" ");

        for (String id : ids) {

            if (id.equals("ALL")) {
                for (int i = 0; i < mentionCount.length; i++) {
                    mentionCount[i]++;
                }

            } else if (id.equals("HERE")) {
                for (int i = 0; i < mentionCount.length; i++) {
                    if (offlineTime[i] == 0 || offlineTime[i] + 60 <= timestamp) {
                        mentionCount[i]++;
                    }
                }

            } else {
                int userId = Integer.parseInt(id.substring(2));
                mentionCount[userId]++;
            }
        }
    }
    }
