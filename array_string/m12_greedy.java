// https://leetcode.com/problems/integer-to-roman/

// Solution 1: denumerize the int from largest unit first.
class Solution {
    public String intToRoman(int num) {
        Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        String res = "";
        for (int unit : map.keySet()) {
            String mappedValue = map.get(unit);

            while (num / unit > 0) {
                res += mappedValue;
                num -= unit;
            }
        }
        return res;
    }
}

// Solution 2: just reference:
class Solution {
public:
    string intToRoman(int num) {
        string ones[] = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        string tens[] = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        string hrns[] = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        string ths[]={"","M","MM","MMM"};
        
        return ths[num/1000] + hrns[(num%1000)/100] + tens[(num%100)/10] + ones[num%10];
    }
};
