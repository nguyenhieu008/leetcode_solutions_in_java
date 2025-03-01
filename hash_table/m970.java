// https://leetcode.com/problems/powerful-integers/

// Solution: Think of it as a 2-d array v[i][j] = x^i + y^j => as i and j increase, the v increased up to bound, and form an area in the 2-d array.
// So, we just need to do 2-d loop until we reach the bound, some attentions:
//   - if x or y == 1, as i or j increased, it makes infinite loops => need to break.
//   - add the sum to set to remove dups
//   - the inner condition should be "powerx + powery <= bound" so we do not need another condition inside.

// Time complexity: O(log(bound) ^ 2));
// Space complexity: O(log(bound) ^ 2));
class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        HashSet<Integer> set = new HashSet<>();

        for (int powerx = 1; powerx < bound; powerx *= x) {
            for (int powery = 1; powerx + powery <= bound; powery *= y) {
                set.add(powerx + powery);    
                
                if (y == 1) break;
            }
            if (x == 1) break;
        }
        
        return new ArrayList<Integer>(set);
    }
}
