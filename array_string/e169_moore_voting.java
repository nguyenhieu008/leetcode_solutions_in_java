// https://leetcode.com/problems/majority-element/description/
// Moore voting solution

class Solution {
    public int majorityElement(int[] nums) {
        int major = 0;
        int freq = 0;
        for (int v : nums) {
            if (freq == 0) {
                major = v;
            } 
            if (v == major) {
                freq++;
            } else {
                freq--;
            }
        }
        return major;
    }
}

// self-done
class Solution {
    public int majorityElement(int[] nums) {
        int val = 0;
        int freq = 0;
        for (int v : nums) {
            if (freq == 0) {
                val = v;
                freq = 1;
            } else {
                freq += val != v ? -1 : 1;
            }
        }
        return val;
    }
}
