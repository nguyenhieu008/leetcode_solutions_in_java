// https://leetcode.com/problems/number-of-recent-calls/description/

// Solution 1: use queue
class RecentCounter {

    Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }
    
    public int ping(int t) {
        int start = t - 3000;
        while (!queue.isEmpty() && queue.peek() < start) {
            queue.poll();
        }
        queue.offer(t);
        return queue.size();
    }
}

// Solution 2: Use tree set and clean the old one.
class RecentCounter {

    TreeSet<Integer> set;

    public RecentCounter() {
        set = new TreeSet<>();
    }
    
    public int ping(int t) {
        set.add(t);
        set.headSet(t - 3000).clear();
        return set.size();
    }
}
