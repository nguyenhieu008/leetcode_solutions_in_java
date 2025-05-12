// https://leetcode.com/problems/finding-3-digit-even-numbers/description/

// NOTICE: THERE IS ANOTHER APPROACH TO LOOP FROM 100-998 TO FIND AN EVEN NUMBER CAN BUILD FROM THE ORIGINAL DIGITS. SOLUTION 2 IS EASIER TO IMPLEMENT SO I USED IT.

// Solution 2: Count the digits. And try to build the number in the increasing order by using that counts.
// One trick is to remove the count for each digit when it's already used in hundred/ten position add added back after finished using it (just like backtracking)
// Because we build the number by increasing the hundred/ten/one position => we always get the result in increased order, without the need to boolean-mark them.
// Time complexity: O(min(n, 10^3) as there are 3 loop with maximum 10 items each.
// Space complexity: O(10) = O(1)

class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        int[] digitCount = new int[10];
        for (int v : digits) {
            digitCount[v]++;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (digitCount[i] == 0) continue;
            digitCount[i]--;

            for (int j = 0; j <= 9; j++) {
                if (digitCount[j] == 0) continue;
                digitCount[j]--;

                for (int k = 0; k <= 8; k += 2) {
                    if (digitCount[k] == 0) continue;

                    int v = i * 100 + j * 10 + k;
                    res.add(v);
                }
                digitCount[j]++;
            }

            digitCount[i]++;
        }

        int[] resArr = new int[res.size()];
        int idx = 0;
        for (int v : res) {
            resArr[idx++] = v;
        }
        return resArr;
    }
}

// Solution 1: Sort first to guarantee the output will be in increasing order
// Then make 3 for loops to build 3 numerals, skip if they used the same digit. And also need to de-duplicate by marking used number.
// Time complexity: O(n^3 + nlog(n)), we need sort.
// Space complexity: O(1000)

class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        boolean[] used = new boolean[1000];

        Arrays.sort(digits);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) continue;

            for (int j = 0; j < n; j++) {
                if (j == i) continue;

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue; 
                    
                    int v = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (used[v] || v % 2 != 0) continue;

                    used[v] = true;
                    res.add(v);
                }
            }
        }
        int[] resArr = new int[res.size()];
        int idx = 0;
        for (int v : res) {
            resArr[idx++] = v;
        }
        return resArr;
    }
}
