// https://leetcode.com/problems/single-element-in-a-sorted-array/description/

// Solution: We divide by 2, if number of items on the right is odd, and nums[mid] == nums[mid+1] => the single item is on the right => keep searching on the right.
// Keep doing it until we got 3 items subarray, then single item will be r or l. Be aware that we should keep l and r not be the same. Otherwise, we will loop forever with "l = mid"

// Time complexity: O(logn)
// Space complexity: O(1);

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (r - l > 1) {
            int mid = l + (r - l) / 2;

            if ((r - mid) % 2 == 0) {
                if (nums[mid] == nums[mid + 1]) {
                    l = mid;
                } else {
                    r = mid;
                }
            } else {
                if (nums[mid] == nums[mid+1]) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
        }

        if (l == 0 || nums[l] != nums[l-1]) {
            return nums[l];
        }
        return nums[r];
    }
}

// Solution 2: If not add 1 single item => the pair first item is always at even index, and second item is at odd index.
// If add the single item => the next pairs indexes are reversed
// So we check if the mid is single or odd and check the proper pair index, to determine the next halp to search.

class Solution {
public:
    int singleNonDuplicate(vector<int>& nums) {
        int l = 0, r = nums.size();
        while (r - l > 1) {
            int mid = l + (r - l) / 2;
            if ((mid % 2 == 0 && nums[mid] != nums[mid-1]) || (mid % 2 == 1 && nums[mid] == nums[mid-1])) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
};
