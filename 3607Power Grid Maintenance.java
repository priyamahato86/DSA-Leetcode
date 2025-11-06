// You are given an integer c representing c power stations, each with a unique identifier id from 1 to c (1‑based indexing).

// These stations are interconnected via n bidirectional cables, represented by a 2D array connections, where each element connections[i] = [ui, vi] indicates a connection between station ui and station vi. Stations that are directly or indirectly connected form a power grid.

// Initially, all stations are online (operational).

// You are also given a 2D array queries, where each query is one of the following two types:

// [1, x]: A maintenance check is requested for station x. If station x is online, it resolves the check by itself. If station x is offline, the check is resolved by the operational station with the smallest id in the same power grid as x. If no operational station exists in that grid, return -1.

// [2, x]: Station x goes offline (i.e., it becomes non-operational).

// Return an array of integers representing the results of each query of type [1, x] in the order they appear.

// Note: The power grid preserves its structure; an offline (non‑operational) node remains part of its grid and taking it offline does not alter connectivity.

##CODE:

class Solution {
    Map<Integer, TreeSet<Integer>> componentStations = new HashMap<>();

    private void dfs(int node,
                     Map<Integer, List<Integer>> adj,
                     int[] visited,
                     int componentId,
                     int[] componentOf) {

        visited[node] = 1;
        componentOf[node] = componentId;

        componentStations.putIfAbsent(componentId, new TreeSet<>());
        componentStations.get(componentId).add(node);

        for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
            if (visited[neighbor] == 0) {
                dfs(neighbor, adj, visited, componentId, componentOf);
            }
        }
    }
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
Map<Integer, List<Integer>> adj = new HashMap<>();

        
        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int[] visited = new int[c + 1];
        int[] componentOf = new int[c + 1];

        
        for (int node = 1; node <= c; node++) {
            if (visited[node] == 0) {
                dfs(node, adj, visited, node, componentOf);
            }
        }

        List<Integer> resultList = new ArrayList<>();

        
        for (int[] q : queries) {
            int type = q[0];
            int x = q[1];

            int compId = componentOf[x];
            TreeSet<Integer> set = componentStations.get(compId);

            if (type == 1) {
                if (set.contains(x)) {
                    resultList.add(x);
                } else if (!set.isEmpty()) {
                    resultList.add(set.first());
                } else {
                    resultList.add(-1);
                }
            } else {
                set.remove(x); 
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
}

 
