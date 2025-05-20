// https://leetcode.com/problems/convert-the-temperature/description/

class Solution {
    public double[] convertTemperature(double celsius) {
        double kelvin = celsius + 273.15;
        double fah = celsius * 1.8 + 32.00;
        return new double[]{kelvin, fah};
    }
}
