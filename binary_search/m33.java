// This is my long solution that does not take care about the position of the pivot point and the mid point
// When we care about that, we come to the next solution 

class Solution {
    // pivot k => new j = i + k + 1 % n;

    int findPivot(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (r - l > 0) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    int realIndex(int i, int k, int n) {
        return (i + k) % n;
    }

    int search(int[] nums, int k, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        // n = 7;
        // k = 4;
        // target = 3;
        // l        r       mid        realMid      a[realMid]   nextl      nextr
        // 0        6       3           0               4          0          2
        // 0        2       1           5               1          1          2
        // 1        2       1           5               1          1          

        // l = 0; reall = 4; a[reall] = 0  => return 4


        while (r - l > 0) {
            int mid = l + (r - l) / 2;
            // System.out.println(l + " " +  r + " " + mid + " " + nums[realIndex(mid, k, n)]);
            int amid = nums[realIndex(mid, k, n)];

            if (amid > target) {
                r = mid - 1;
            } else if (amid < target){
                l = mid + 1;
            } else {
                return realIndex(mid, k, n);
            }
        }

        if (nums[realIndex(l, k, n)] == target) {
            return realIndex(l, k, n);
        }
        return -1;
    }


    public int search(int[] nums, int target) {
        int k = findPivot(nums);
        System.out.println("k = " + k);
        return search(nums, k, target);
    }
}

// Below solution is copied from solutions of leetcode

public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
        return -1;
    }
    
    /*.*/
    int left = 0, right = nums.length - 1;
    //when we use the condition "left <= right", we do not need to determine if nums[left] == target
    //in outside of loop, because the jumping condition is left > right, we will have the determination
    //condition if(target == nums[mid]) inside of loop
    while (left <= right) {
        //left bias
        int mid = left + (right - left) / 2;
        if (target == nums[mid]) {
            return mid;
        }
        //if left part is monotonically increasing, or the pivot point is on the right part
        if (nums[left] <= nums[mid]) {
            //must use "<=" at here since we need to make sure target is in the left part,
            //then safely drop the right part
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;
            }
            else {
                //right bias
                left = mid + 1;
            }
        }

        //if right part is monotonically increasing, or the pivot point is on the left part
        else {
            //must use "<=" at here since we need to make sure target is in the right part,
            //then safely drop the left part
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
    }
    return -1;
}


// Solution 3: just reference: 
// https://leetcode.com/problems/search-in-rotated-sorted-array/solutions/14435/clever-idea-making-it-simple/?envType=study-plan-v2&envId=top-interview-150

class Solution {
    public int search(int[] nums, int target) {
        // ascending order, distinct values
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int virtualNumAtMid = nums[mid];

            if (!((nums[mid] < nums[l]) == (target < nums[l]))) {
                if (target < nums[l]) {
                    virtualNumAtMid = Integer.MIN_VALUE;
                } else {
                    virtualNumAtMid = Integer.MAX_VALUE;
                }
            }

            if (virtualNumAtMid < target) {
                l = mid + 1;
            } else if (virtualNumAtMid > target) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }   
}
