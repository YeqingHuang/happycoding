class Logger {
    // thoughts: keep record of the latest occurence of each message
    private Map<String, Integer> map;

    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message)) {
            map.put(message, timestamp);
            return true;
        } else {
            if (timestamp - map.get(message) < 10) {
                return false;
            } else {
                map.put(message, timestamp);
                return true;
            }
        }
    }

     public boolean shouldPrintMessage1(int timestamp, String message) {
        if(map.containsKey(message) && timestamp - map.get(message) < 10) {
            return false;
        }
        map.put(message, timestamp); // either create or update
        return true;
    }
}

class Logger1 {
    class Record {
        int t;
        String msg;
        
        public Record(int t, String msg) {
            this.t = t;
            this.msg = msg;
        }
    }
    
    // another method: only keep a window of 10 seconds
    // use a linkedlist, whenever we call shouldPrintMessage()
    // we also check if the head(or several nodes in front) should be removed
    // each node stores message and timestamp
    private LinkedList<Record> linkedlist;
    private Set<String> seen; 
    
    /** Initialize your data structure here. */
    public Logger() {
        linkedlist = new LinkedList<>();
        seen = new HashSet<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        // step1: check if we need to remove from the front
        while (!linkedlist.isEmpty()) {
            Record head = linkedlist.getFirst();
            if (timestamp - head.t >= 10) {
                linkedlist.pollFirst();
                seen.remove(head.msg);
            } else {
                break;
            }
        }
        
        // step2: get conclusion
        if (seen.contains(message)) {
            return false;
        } else {
            linkedlist.addLast(new Record(timestamp, message));
            seen.add(message);
            return true;
        }
    }
}