public class StringToInteger {
    /*
        not strong restricted
        personal idea of 'atoi'
    */
    public int StringToInteger(String s) {
        int sign = 1;
        if (s.length() == 0)
            return 0; // empty input

        s.replaceAll("\\s+", ""); // remove space

        int start = 0;
        for (int i = 0; i < s.length() && s.charAt(i) - '0' < 0; i++) {
            if (s.charAt(i) == '-') {
                sign = -sign;
            }
            if (s.charAt(i) == '0') {
                start = i + 1;
            }
        }
        s = s.replaceAll("[^0-9]", "");
        s = s.substring(start, s.length());
        // handle signs

        int ans = 0;
        // int base = Math.pow(10, s.length());
        for (int i = 0; i < s.length() ; i++) {
            int digit = s.charAt(i) - '0';
            // if (digit )
            if (ans >= Integer.MAX_VALUE / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            ans = ans * 10 + digit;
        }
        // convert number and avoid overflow

        return ans * sign;
    }

    /*
        [Accpected] Solution
    */

    public int StringToInteger(String s) {
        int sign = 1, idx = 0;
        if (s.length() == 0) return 0; // empty input

        while (s.charAt(idx) == ' ' && idx < s.length()) {
            idx++;
        } // remove whitespace

        if (s.charAt(idx) == '+' || s.charAt(idx) == '-') {
            sign = s.charAt(idx) == '+' ? 1 : -1;
            idx++;
        }
        // handle signs

        int ans = 0;
        while (idx < s.length()) {
            int digit = s.charAt(idx) - '0';
            if (digit < 0 || digit > 9) {
                break;
            }
            if (Integer.MAX_VALUE/10 < ans ||
                Integer.MAX_VALUE/10 == ans && Integer.MAX_VALUE %10 < digit) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        // convert number and avoid overflow

        return ans * sign;
    }
}
