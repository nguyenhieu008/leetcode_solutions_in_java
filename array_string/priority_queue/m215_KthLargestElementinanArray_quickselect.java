// https://leetcode.com/problems/kth-largest-element-in-an-array/description/

// Solution 1: Priority queue. When we keep a min heap of size k, and add all items of array go through the heap, poping the smallest one out.
// => the last heap will contain k max-items of the array. And the top of the heap will be the k-largest element.
// Time complexity: O(n*logk), as the heap has at-max k elements.
// Space complexity: O(k) for the heap.

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue();
        for (int v : nums) {
            heap.offer(v);

            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }
}

// Solution 2: Can use counting sort. https://leetcode.com/problems/kth-largest-element-in-an-array/solutions/6186858/video-4-solutions-with-sorting-heap-counting-sort-and-quick-select/
// Solution 3: Quick select.
// Because in each recursion, we only go with one halp, so in the average case, the number of ops could be: (n + (n / 2) + (n/4) + ... + 1) < 2 * n
// Notice that, even if using random pivot, if we partion from 1-end like this one: https://www-geeksforgeeks-org.translate.goog/quickselect-algorithm/?_x_tr_sl=en&_x_tr_tl=vi&_x_tr_hl=vi&_x_tr_pto=tc
// It will also be O(n^2) in case of all same items [1, 1, 1, 1, 1, ...., 1, 1]
// => The best solution is to use l, r from 2-end, then in case of all same items, we can always divide in half like below.
// => Time complexity: O(n)
// Space complexity: O(log n) for stack call. Can be O(1) if using iterative version (modify left, right instead of recursion)

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickselect(nums, 0, nums.length - 1, k);
    }

    private int quickselect(int[] nums, int left, int right, int k) {
        int partitionIdx = partition(nums, left, right);

        if (partitionIdx == k - 1) {
            return nums[partitionIdx];
        } else if (partitionIdx > k - 1) {
            return quickselect(nums, left, partitionIdx - 1, k);
        } else {
            return quickselect(nums, partitionIdx + 1, right, k);
        }
    }

    private int partition(int[] nums, int left, int right) {
        Random rand = new Random();
        int randomIdx = left + rand.nextInt(right - left + 1);
        swap (nums, left, randomIdx);

        int pivot = nums[left];
        int l = left + 1, r = right;

        while (l <= r) {
            while (l <= r && nums[l] > pivot) {
                l++;
            }
            while (l <= r && nums[r] < pivot) {
                r--;
            }

            if (l <= r) {
                swap(nums, l, r);
                l++; r--;
            }
        }
        swap(nums, left, r);
        return r;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
