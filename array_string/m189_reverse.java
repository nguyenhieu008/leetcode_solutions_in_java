// https://leetcode.com/problems/rotate-array/description/

// Solution 3: Reverse
// We see that when rotate, last k items moved to k first items, and (n-k) first items moved to last items
// => we reverse, the subarray arrangement is ok, but each subarray is reversed.
// => We just need to reverse each subarray once more.

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
    private void reverse(int[] a, int start, int end) {
        int l = start, r = end;
        while (l < r) {
            int temp = a[l];
            a[l] = a[r];
            a[r] = temp;
            l++; r--;
        }
    }
}

// Solution 2: self-done.
/*- Imagine: move i = 0 -> i = 3 => i = 6 => ... => i = 0 => reach 0 again, but may not swap all item => need count
- At each swap, we swap with nums[start], until we reach start => no need to swap anymore.
- If LCM(k, n) = k * n => we only 1 loop 
- If LCM(k, n) < k * n => we need > 1 loop to swap all items. => There are more then 1 connected group in the set.
Here is my C++ solution, determine number of loops by gcd:
class Solution {
public:
    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    void rotate(vector<int>& nums, int k) {
        int n = nums.size();
        k %= n;
        
        int range = gcd(n, k);
        for (int i = 0; i < range; ++i) {
            int prev = (i - k + n) % n;
            int memo = nums[i];
            while (prev != i) {
                nums[(prev + k) % n] = nums[prev];
                prev = (prev - k + n) % n;
            }
            nums[i + k] = memo;
        }
    }
};
- Time complexity: O(n)
- Space complexity: O(1)
*/
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        int start = 0, count = 0;
        while (count < n) {
            // [1, 2, 3, 4, 5, 6, 7], k = 3
            // start    a[start]    i   a[i]    => temp   a[i]  a[start]   count
            // 0        1           3     4         4       1       4       1
            // 0        4           6     7         7       4       7       2
            // 0        7           2     3         3       7       3       3
            // 0        3           5     6         6       3       6       4
            // 0        6           1     2         2       6       2       5
            // 0        2           4     5         5       2       5       6
            // 0        5           0        => wrong stop condition
            // => we lack count increment. We need to increment it once before the loop start.
            // In the failed attemp, I miss the below count++;
            count++; // This requires as the loop lacks the last count increment
            for (int i = (start + k) % n; i != start; i = (i + k) % n) {
                int temp = nums[i];
                nums[i] = nums[start];
                nums[start] = temp;
                count++;
            }
            start++;
        }
    }
}

// Solution 1: self-done, use extra space
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] temp = Arrays.copyOf(nums, n);
        k = k % n;

        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }
        System.arraycopy(temp, 0, nums, 0, n);
    }
}
