// https://leetcode.com/problems/add-binary/description/
// Solution 1: add bits as usual

class Solution {
    public String addBinary(String a, String b) {
        char[] charA = a.toCharArray(), charB = b.toCharArray();
        int remember = 0;
        int m = a.length(), n = b.length();
        int max = Math.max(m, n);
        char[] res = new char[max];
        Arrays.fill(res, '0');

        for (int i = 1; i <= max; i++) {
            int bitA = (i <= m) ? charA[m - i] - '0' : 0;
            int bitB = (i <= n) ? charB[n - i] - '0' : 0;

            res[max - i] += (bitA + bitB + remember) % 2;
            remember = (bitA + bitB + remember) / 2;
        }
        
        String resStr = new String(res);
        return remember == 1 ? "1" + resStr : resStr;
    }
}

// Solution 2: Same as solution 1. 2 pointers for readability, but need to care about the pointers i, j and the operation --, which can easily make mistake
class Solution {
    public String addBinary(String a, String b) {
        int m = a.length(), n = b.length();
        int remember = 0;
        StringBuilder res = new StringBuilder();
        int i = m - 1, j = n - 1;

        while (i >= 0 || j >= 0 || remember > 0) {
            if (i >= 0) {
                remember += a.charAt(i) - '0';
                i--;
            }
            
            if (j >= 0) {
                remember += b.charAt(j) - '0';
                j--;
            }

            res.append(remember % 2);
            remember /= 2;
        }
        
        return res.reverse().toString();
    }
}
