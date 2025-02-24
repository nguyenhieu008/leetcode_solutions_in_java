// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

// For the start of Range, if the mid == target, we still need to search to the left => set r = mid => if amid >= target, r = mid. => the start of range always at r or -1.
// Do the same with left



class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findFirst(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (r - l > 0) {
            int mid = l + (r - l) / 2;

            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (nums[r] == target) {
            return r;
        } else {
            return -1;
        }
    }

    private int findLast(int nums[], int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (r - l > 1) {
            int mid = l + (r - l) / 2;

            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        if (nums[r] == target) {
            return r;
        } else if (nums[l] == target) {
            return l;
        } else {
            return -1;
        }
    }

}

// // There is another shorter solution from leetcode

public class Solution {
	public int[] searchRange(int[] A, int target) {
		int start = Solution.firstGreaterEqual(A, target);
		if (start == A.length || A[start] != target) {
			return new int[]{-1, -1};
		}
		return new int[]{start, Solution.firstGreaterEqual(A, target + 1) - 1};
	}

	//find the first number that is greater than or equal to target.
	//could return A.length if target is greater than A[A.length-1].
	//actually this is the same as lower_bound in C++ STL.
	private static int firstGreaterEqual(int[] A, int target) {
		int low = 0, high = A.length;
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			//low <= mid < high
			if (A[mid] < target) {
				low = mid + 1;
			} else {
				//should not be mid-1 when A[mid]==target.
				//could be mid even if A[mid]>target because mid<high.
				high = mid;
			}
		}
		return low;
	}
}
