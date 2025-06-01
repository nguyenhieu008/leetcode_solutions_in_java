// https://leetcode.com/problems/distribute-candies-among-children-ii/description/

// solution 2: there is a solution using combinatoric approach.
// Editorial: https://leetcode.com/problems/distribute-candies-among-children-ii/editorial/

// Solution 1a:
class Solution {
    public long distributeCandies(int n, int limit) {
        long res = 0;
        
        /*
            Distribute m candies to 2 child, with limit 
            - Case 1: a = 0, b = m, if b <= limit => res++
            - Case 2: a = 1, b = m - 1, if (m - 1 <= limit) => res++
            - Case x: a = x, b = m - x, if (x <= limit && m - x <= limit) => res++
            
            => find x, (x <= limit && m - x <= limit)
            => x = (m - limit), if x <= limit => res += [x, m - x] = (m - x) - x + 1 = m - 2 * x + 1
            => res += m - 2 * (m - limit) + 1 = limit * 2 - m + 1

            In case (m <= limit) => res += (m + 1) 
            => Otherwise, res = Math.max(limit * 2 - m + 1, 0);

            0 --- x --(valid range)-- limit (m - x) ---- m
            => valid range = right - left + 1;
            if (m > 2 * limit) res = 0
            else left = max(0, m - limit), right = min(m, limit) (incase m < limit)
        */

        for (int child1 = 0; child1 <= Math.min(n, limit); child1++) {
            int m = n - child1;
            if (m <= 2 * limit) {
                res += Math.min(m, limit) - Math.max(0, m - limit) + 1;
            }
        }
        return res;
    }
}

// Solution 1:
class Solution {
    public long distributeCandies(int n, int limit) {
        long res = 0;
        
        /*
            Distribute m candies to 2 child, with limit 
            - Case 1: a = 0, b = m, if b <= limit => res++
            - Case 2: a = 1, b = m - 1, if (m - 1 <= limit) => res++
            - Case x: a = x, b = m - x, if (x <= limit && m - x <= limit) => res++
            
            => find x, (x <= limit && m - x <= limit)
            => x = (m - limit), if x <= limit => res += [x, m - x] = (m - x) - x + 1 = m - 2 * x + 1
            => res += m - 2 * (m - limit) + 1 = limit * 2 - m + 1

            In case (m <= limit) => res += (m + 1) 
            => Otherwise, res = Math.max(limit * 2 - m + 1, 0);
        */

        for (int child1 = 0; child1 <= Math.min(n, limit); child1++) {
            int n2 = n - child1;
            if (n2 <= limit) {
                res += n2 + 1;
            } else {
                res += Math.max(limit * 2 - n2 + 1, 0);
            }
            // System.out.format("child1 = %d, n2 = %d, res = %d\n", child1, n2, res);
        }
        return res;
    }
}
