// https://leetcode.com/problems/find-all-good-indices/description/

class Solution {
    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        // nonIncreasing[i] = the length of non-increasing subarray ends at i
        // nonDecreasing[i] = the length of non-decreasing subarray starts at i
        int[] nonIncreasing = new int[n]; 
        int[] nonDecreasing = new int[n];

        Arrays.fill(nonIncreasing, 1);
        Arrays.fill(nonDecreasing, 1);

        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i-1]) {
                nonIncreasing[i] = nonIncreasing[i-1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i+1]) {
                nonDecreasing[i] = nonDecreasing[i+1] + 1;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            if (nonIncreasing[i - 1] >= k && nonDecreasing[i + 1] >= k) {
                res.add(i);
            }
        }
        return res;
    }
}

// https://leetcode.com/problems/find-good-days-to-rob-the-bank/

// Solution 1: Preprocess left and right to not calculating the length of non-increasing/non-decreasing repeatedly.
// If we try from an index i, then expand to both sides, it will take O(n^2), in solution 0.

class Solution {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;

        int[] nonIncreasing = new int[n]; // left to right
        int[] nonDecreasing = new int[n]; // right to left
        
        for (int i = 1; i < n; i++) {
            if (security[i - 1] >= security[i]) {
                nonIncreasing[i] = nonIncreasing[i - 1] + 1;
            } else {
                nonIncreasing[i] = 0;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (security[i] <= security[i + 1]) {
                nonDecreasing[i] = nonDecreasing[i + 1] + 1;
            } else {
                nonDecreasing[i] = 0;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = time; (i + time) < n; i++) {
            if (nonIncreasing[i] >= time && nonDecreasing[i] >= time) {
                res.add(i);
            }
        }
        return res;
    }

}

// Solution 0: try expand to both side from index i, but it get TLE because of O(n^2)
class Solution {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;

        List<Integer> res = new ArrayList<>();
        // (i >= time) && (i <= n - 1 - time)
        for (int i = time; (i + time) < n; i++) {
            if (isGoodDay(security, i, time)) {
                res.add(i);
            }
        }
        return res;
    }

    // Make sure (i >= time) && (i <= n - 1 - time) before calling this function
    private boolean isGoodDay(int[] security, int cur, int time) {
        for (int i = 0; i < time; i++) {
            if (security[cur - i - 1] < security[cur - i] || security[cur + i + 1] < security[cur + i]) {
                return false;
            }
        }
        return true;
    }
}
