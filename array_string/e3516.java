// https://leetcode.com/problems/find-closest-person/description/

class Solution {
    public int findClosest(int x, int y, int z) {
        int distanceX = Math.abs(z - x);
        int distanceY = Math.abs(z - y);
        if (distanceX < distanceY) {
            return 1;
        } else if (distanceX > distanceY) {
            return 2;
        }
        return 0;
    }
}

class Solution {
    public int findClosest(int x, int y, int z) {
        switch (Integer.compare(Math.abs(z - x), Math.abs(z - y))) {
            case -1: 
                return 1;
            case 1: 
                return 2;
            case 0:
                return 0;
        }
        return 0;
    }
}
