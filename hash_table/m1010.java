// 1010. Pairs of Songs With Total Durations Divisible by 60
// https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] dur = new int[60];
        
        for (int i = 0; i < time.length; i++) {
            dur[time[i] % 60]++;
        }

        long res = 0;
        
        for (int i = 1; i <= 29; i++) {
            res += dur[i] * dur[60 - i];
        }
        
        res += (long) dur[30] * (dur[30] - 1) / 2;
        res += (long) dur[0] * (dur[0] - 1) / 2;

        return (int) res;
    }
}

class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] remainderFreq = new int[60];
        
        int res = 0;
        for (int t : time) {
            int r = t % 60;
            res += remainderFreq[(60 - r) % 60];
            remainderFreq[r]++;
        }

        return res;
    }
}
