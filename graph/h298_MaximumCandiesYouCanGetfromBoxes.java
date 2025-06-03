// https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/

// Solution: Simply use graph. Detail in comment.
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        /*
            own: set of boxes that I own
            boolean[] ownedKeys[i]: whether I own key i
            boolean[] used[i]: box[i] already opened

            available: queue of available boxes to open

            check all current boxes:
                - If already open or have a key => put to available
                - If not put own set:
            
            Popping from the queue:
                - mark used
                - add candies to the result
                - if there are some keys, marked it to ownedKeys array
                    - Check if the corresponding box is in the own? if Yes, or the box is already opened, then remove that box from own and put to available
                - If there are some boxes:
                    - Check if we have the corresponding key? If yes, then put to available
                    - If not put to own set
        */

        int n = status.length;
        Set<Integer> own = new HashSet<>();
        boolean[] ownedKeys = new boolean[n];
        boolean[] used = new boolean[n];

        Queue<Integer> available = new LinkedList<>();

        for (int box : initialBoxes) {
            if (status[box] == 1) {
                available.offer(box);
            } else {
                own.add(box);
            }
        }

        int res = 0;
        while (!available.isEmpty()) {
            int box = available.poll();
            used[box] = true; // Whether we need this

            res += candies[box];

            // Check keys that contained in the box
            for (int key : keys[box]) {
                int correspondingBox = key;
                ownedKeys[key] = true;
                if (own.contains(correspondingBox)) {
                    own.remove(correspondingBox);
                    available.offer(correspondingBox);
                }
            }

            // Check the contained boxs that are in the box
            for (int containedBox : containedBoxes[box]) {
                if (ownedKeys[containedBox] || status[containedBox] == 1) {
                    available.offer(containedBox);
                } else {
                    own.add(containedBox);
                }
            }
        }
        return res;
    }
}
