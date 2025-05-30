// https://leetcode.com/problems/get-watched-videos-by-your-friends/description/

// Solution 2: Shorter code (but lower performance on Leetcode). Sort the string array by defining the custom comparator.
// Time complexity: O(n + n * m * l + n * m  * log(n * m) * l) = O(m * n * log(m * n) * l),
//   where n is number of people, m = number of videos, l = average length of a video
// Space complexity: O(m * n * l);

class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        Queue<Integer> q = new LinkedList<>();
        q.offer(id);
        int curLevel = 0;
        boolean[] visited = new boolean[n];
        visited[id] = true;

        while (!q.isEmpty() && curLevel < level) {
            int size = q.size();
            // todo: update curLevel;
            while (size-- > 0) {
                int person = q.poll();

                int[] curFriends = friends[person];
                for (int f : curFriends) {
                    if (!visited[f]) {
                        q.offer(f);
                        visited[f] = true;
                    }
                }
            }
            curLevel++;
        }
        
        Map<String, Integer> videoCount = new HashMap<>();
        while (!q.isEmpty()) {
            int person = q.poll();

            List<String> videos = watchedVideos.get(person);
            for (String v : videos) {
                int newCount = videoCount.getOrDefault(v, 0) + 1;
                videoCount.put(v, newCount);
            }
        }

        List<String> res = new ArrayList<>(videoCount.keySet());
        res.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int freqA = videoCount.get(a), freqB = videoCount.get(b);
                if (freqA == freqB) {
                    return a.compareTo(b);
                }
                return Integer.compare(freqA, freqB);
            }
        });
        return res;
    }
}
