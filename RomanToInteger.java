public class RomanToInteger {
    /*
        Clean Switch Case Solution
        Time: O(N)
    */
    public int romanToInt(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':  res = res + (res >= 5 ? -1 : 1);
                           break;
                case 'V':  res = res + 5;
                           break;
                case 'X':  res = res + (res >= 50 ? -10 : 10);
                           break;
                case 'L':  res = res + 50;
                           break;
                case 'C':  res = res + (res >= 500 ? -100 : 100);
                           break;
                case 'D':  res = res + 500;
                           break;
                case 'M':  res = res + 1000; // 0 ~ 3999
                           break;
            }
        }
        return res;
    }
}
