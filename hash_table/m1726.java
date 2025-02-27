// https://leetcode.com/problems/tuple-with-same-product/

// Solution 2: shorter. For each pair and its product, we need to check how many times that products has appeared before it.
// So, we add the hash value of product right the the result. Finally, multiply by 8.
class Solution {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> hash = new HashMap<>();

        int matched = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int product = nums[i] * nums[j];
                int c = hash.getOrDefault(product, 0);

                matched += c;
                hash.put(product, c + 1);
            }
        }

        return matched * 8;
    }
}

// Solution 1: We calculate all the appearance of products. After that, we calculate number of pair by formula: (n * (n-1)) / 2
class Solution {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> hash = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                hash.put(nums[i] * nums[j], hash.getOrDefault(nums[i] * nums[j], 0) + 1);
            }
        }

        System.out.println(hash);

        int res = 0;
        for (int count : hash.values()) {
            res += count * (count - 1) / 2 * 8;
        }
        return res;
    }
}
