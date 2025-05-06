// https://leetcode.com/problems/build-array-from-permutation/description/

// Solution 2: Try to store both numbers in a same slot
// the result number will be store int: (result * n) + original
// => after we build the next array, we just need to extract result by nums[i] / n
// Inside the first loop, when we need to get the original, we just do nums[i] % n

class Solution {
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] += (nums[nums[i]] % n) * n;
        }
        for (int i = 0; i < n; i++) {
            nums[i] /= n;
        }
        return nums;
    }
}

// Solution 1a: Traverse like a graph, it will end at current index. So we need to mark them as visited by making it negative
class Solution {
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                int curIdx = i;
                int startValue = nums[i];
                int nextIdx = nums[i];

                while (nextIdx != i) {
                    nums[curIdx] = -nums[nextIdx] - 1;
                    curIdx = nextIdx;
                    nextIdx = nums[curIdx];
                }
                
                nums[curIdx] = -startValue - 1;
            }
        }
        for (int i = 0; i < n; i++) {
            nums[i] = -nums[i] - 1;
        }
        return nums;
    }
}

// Solution 1:
class Solution {
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[nums[i]];
        }
        return res;
    }
}
