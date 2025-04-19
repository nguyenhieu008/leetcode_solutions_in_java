// https://leetcode.com/problems/ipo/solutions/
// Each time we do a project, we increase our capital => we can do more projects with high requirement for capital.
// => can use an increasing array (or even min heap) to add more projects to the list of do-able-projects
// Each time we do a new do-able-project, we try to maximize the capital by choosing the largest possible profit
// => we should use a max heap to do that with O(logn)
// Time complexity: O(nlogn) for sort + O(klogn) for do projects = O(nlogn)
// Space complexity: O(n) for both temporary array and heap.

class Solution {
    class Pair{
        int profit;
        int capital;

        Pair(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Pair[] sortedTask = new Pair[n]; // by capital
        for (int i = 0; i < n; i++) {
            sortedTask[i] = new Pair(profits[i], capital[i]);
        }
        Arrays.sort(sortedTask, (t1, t2) -> {
            return Integer.compare(t1.capital, t2.capital);
        });
        // heap by max profit
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((t1, t2) -> {
            return Integer.compare(t2.profit, t1.profit);
        });

        int i = 0;
        int currentCapital = w;
        int numProject = 0;
        while (numProject < k && numProject < n) {
            while (i < n && sortedTask[i].capital <= currentCapital) {
                maxHeap.offer(sortedTask[i]);
                i++;
            }
            if (maxHeap.isEmpty()) {
                // Because heap is empty, there is no way to update currentCapital => can not do more project
                // => this is the maximum capital we can reach
                return currentCapital;
            }
            currentCapital += maxHeap.poll().profit;
            numProject++;
        }
        return currentCapital;
    }
}
