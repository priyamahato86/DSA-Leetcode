// Design a data structure that can efficiently manage data packets in a network router. Each data packet consists of the following attributes:

// source: A unique identifier for the machine that generated the packet.
// destination: A unique identifier for the target machine.
// timestamp: The time at which the packet arrived at the router.
// Implement the Router class:

// Router(int memoryLimit): Initializes the Router object with a fixed memory limit.

// memoryLimit is the maximum number of packets the router can store at any given time.
// If adding a new packet would exceed this limit, the oldest packet must be removed to free up space.
// bool addPacket(int source, int destination, int timestamp): Adds a packet with the given attributes to the router.

// A packet is considered a duplicate if another packet with the same source, destination, and timestamp already exists in the router.
// Return true if the packet is successfully added (i.e., it is not a duplicate); otherwise return false.
// int[] forwardPacket(): Forwards the next packet in FIFO (First In First Out) order.

// Remove the packet from storage.
// Return the packet as an array [source, destination, timestamp].
// If there are no packets to forward, return an empty array.
// int getCount(int destination, int startTime, int endTime):

// Returns the number of packets currently stored in the router (i.e., not yet forwarded) that have the specified destination and have timestamps in the inclusive range [startTime, endTime].
// Note that queries for addPacket will be made in increasing order of timestamp.

##CODE:
class Router {
      private int MAX_SIZE;  
    private Map<String, int[]> packetStore;          
    private Map<Integer, ArrayList<Integer>> destTimestamps;  
    private Map<Integer, Integer> startIndex;       
    private Queue<String> que; 
    public Router(int memoryLimit) {
         MAX_SIZE = memoryLimit;
        packetStore = new HashMap<>();
        destTimestamps = new HashMap<>();
        startIndex = new HashMap<>();
        que = new LinkedList<>();
    }

     private String makeKey(int source, int destination, int timestamp) {
        return source + "#" + destination + "#" + timestamp;
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
         String key = makeKey(source, destination, timestamp);

        if (packetStore.containsKey(key)) return false; 

        if (packetStore.size() >= MAX_SIZE) {
            forwardPacket(); 
        }

        packetStore.put(key, new int[]{source, destination, timestamp});
        que.offer(key);

        destTimestamps.putIfAbsent(destination, new ArrayList<>());
        destTimestamps.get(destination).add(timestamp);

        startIndex.putIfAbsent(destination, 0);

        return true;
    }
    
    public int[] forwardPacket() {
        if (packetStore.isEmpty()) return new int[0];

        String key = que.poll();
        int[] packet = packetStore.get(key);
        packetStore.remove(key);

        int dest = packet[1];
        int idx = startIndex.get(dest);
        startIndex.put(dest, idx + 1); 

        return packet;
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        if (!destTimestamps.containsKey(destination)) return 0;

        ArrayList<Integer> list = destTimestamps.get(destination);
        int idx = startIndex.get(destination);
        int left = lowerBound(list, startTime, idx);
        int right = upperBound(list, endTime, idx);

        return right - left;
    }
     private int lowerBound(ArrayList<Integer> list, int target, int startIdx) {
        int low = startIdx, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) >= target) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    
    private int upperBound(ArrayList<Integer> list, int target, int startIdx) {
        int low = startIdx, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) > target) high = mid;
            else low = mid + 1;
        }
        return low;
    }
}
