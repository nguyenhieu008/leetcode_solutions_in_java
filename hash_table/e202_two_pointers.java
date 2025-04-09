// https://leetcode.com/problems/happy-number/description/

// Here we use hash table to store then numbers that we alrady encountered.
// Can also use 2 pointers, slow - fast to detect cycle.
class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> visited = new HashSet<>();
        int nextInt = n;
        while (!visited.contains(nextInt)) {
            // Need to add first, to not checking existence right after added to set.
            visited.add(nextInt);
            nextInt = sumSquare(nextInt);
            // visited.add(nextInt); => Do not do this
        }
        return nextInt == 1;
    }
    private int sumSquare(int number) {
        int res = 0;
        while (number > 0) {
            int remainder = number % 10;
            number /= 10;
            res += remainder * remainder;
        }
        return res;
    }
}
