// Solution 1: 
// (A^A^B) = (B^A^A) = (A^B^A) = B This shows that position doesn't matter.
// Similarly , if we see , a^a^a......... (even times)=0 and a^a^a........(odd times)=a
// Because , the elements with frequency=2 will result in 0. And then the only element with frequency=1 will generate the answer.

class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int v : nums) {
            res ^= v;
        }
        return res;
    }
}

// Solution 2: after sort, the 2 items nearby will be equal. If there is a mismatch, the one at position (n % 2 == 0) will be the odd one.
class Solution {
    public int singleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[n-1];
    }
}
