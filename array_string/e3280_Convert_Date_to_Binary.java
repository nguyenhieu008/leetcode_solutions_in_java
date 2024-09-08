// https://leetcode.com/problems/convert-date-to-binary/

class Solution {

    String istr2Bin(String date, int s, int e) {
        return Integer.toBinaryString(Integer.parseInt(date.substring(s, e)));
    }

    public String convertDateToBinary(String date) {
        String year = istr2Bin(date, 0, 4);
        String month = istr2Bin(date, 5, 7);
        String day = istr2Bin(date, 8, 10);

        return year + "-" + month + "-" + day;
    }
}
