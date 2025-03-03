// Solution 2: if nums[mid] < nums[r] => need set r = mid (mid can also be the answer). Otherwise, l = mid + 1, because nums[mid] is greater => it cannot be the min value.
// This way, we can handle the case where the array is not rotated. For that case, we update r = mid (because a[mid] < a[r]), 
// instead of update l = mid wrongly (because a[mid] also > a[l], but should not update l because l is min item)
class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (r - l > 0) {
            int mid = l + (r - l) / 2;

            if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}

// Solution 1: If a[mid] < a[l] => the min is at left of or at mid => r = mid
// Otherwise, it is at right of or at mid => l = mid. But we need to care about the case where min = l, that case we should not update l
// => we should not use this approach (compare to l), because we need to handle special case by checking (nums[mid] < nums[r], the array is not rotated)
// => we should use solution 2: compare nums[mid] with nums[r]

class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (r - l > 1) {
            int mid = l + (r - l) / 2;

            if (nums[mid] < nums[l]) {
                r = mid;
            } else {
                if (nums[mid] < nums[r]) {
                    return nums[l];
                }
                l = mid;
            }
        }
        return Math.min(nums[l], nums[r]);
    }
}
