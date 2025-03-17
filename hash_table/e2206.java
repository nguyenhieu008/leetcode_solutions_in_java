// https://leetcode.com/problems/divide-array-into-equal-pairs/description/

class Solution {
    public boolean divideArray(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int v : nums) {
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        for (Integer c : map.values()) {
            if (c % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}
