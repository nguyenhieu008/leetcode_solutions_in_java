// https://leetcode.com/problems/sort-characters-by-frequency/description/

// Solution 3: We can store the character in the priority queue only. The frequence can be referred in the original hash table.
// sorted array and priority queue here are equal, we can use either one.
class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        ArrayList<Character> al = new ArrayList(count.keySet());
        Collections.sort(al, (a, b) -> count.get(b) - count.get(a));

        StringBuilder sb = new StringBuilder();
        for (char c : al) {
            char[] temp = new char[count.get(c)];
            Arrays.fill(temp, c);

            sb.append(temp);
        }
        return sb.toString();
    }
}

// Solution 2: priority can contain Map.Entry => shorter code without defining custom class
class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(count.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> cc = pq.poll();

            char[] temp = new char[cc.getValue()];
            Arrays.fill(temp, cc.getKey());

            sb.append(temp);
        }
        return sb.toString();
    }
}

// Solution 1: create a custom class for character and its frequency, so can add to priority queue. A bit too long and complex for interview.
class Solution {
    class CountableChar implements Comparable<CountableChar> {
        int freq;
        char c;

        CountableChar(int f, char c) {
            this.freq = f;
            this.c = c;
        }

        public int compareTo(CountableChar that) {
            return that.freq - this.freq;
        }

        public String toString() {
            return String.valueOf(c).repeat(freq);
        }
    }

    public String frequencySort(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<CountableChar> pq = new PriorityQueue<>();
        count.forEach((c, f) -> {
            pq.offer(new CountableChar(f, c));
        });

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            CountableChar cc = pq.poll();
            sb.append(cc);
        }
        return sb.toString();
    }
}
