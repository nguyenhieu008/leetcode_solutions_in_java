// https://leetcode.com/problems/count-complete-subarrays-in-an-array/

class Solution {
    // We try to call number of complete subarrays that ends at specific i.
    // So for a complete subarray [j, i] (j <= i), all index < j will also make a complete subarray
    // => we use sliding window, for each right, the find the smallest left that does not satisfy a complete subarray 
    // => all index before left will yield complete subarray => add to result
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        HashSet<Integer> setAll = Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));

        int left = 0, res = 0;
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int right = 0; right < n; right++) {
            frequency.put(nums[right], frequency.getOrDefault(nums[right], 0) + 1);
            // frequency size at most == set all 
            // left <= right kind of not need, since it will surely make the size of frequency to 0
            while (frequency.size() == setAll.size()) {
                frequency.put(nums[left], frequency.get(nums[left]) - 1);
                if (frequency.get(nums[left]) <= 0) {
                    frequency.remove(nums[left]);
                }

                left++;
            }

            // Number of starting for subarray exceptbefore left, where left not satisfy the condition
            // If left not ever moved (== 0), it will add 0 to result
            res += left;
        }
        return res;
    }
}
