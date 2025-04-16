// https://leetcode.com/problems/plus-one/description/

// Solution 2: Handle this specific edge case where we only add 1
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        
        // Notice this will modify the input which sometimes are not allowable
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newDigits = new int[n+1];
        newDigits[0] = 1;
        return newDigits;
    }
}

// Solution 1: this is to handle general case, which is not best fit for this minor case (only + 1)
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        ArrayList<Integer> reversedRes = new ArrayList<>();

        int carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            carry += digits[i];
            reversedRes.add(carry % 10);
            carry /= 10;
        }
        if (carry > 0) {
            reversedRes.add(carry);
        }
        // After this, we have the result
        Collections.reverse(reversedRes);

        return reversedRes.stream().mapToInt(a -> a).toArray();
    }
}
