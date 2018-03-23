public class IntegerToRoman {
    /*
        List All conditions
        Naive & Lazy Solution
    */
    public String intToRoman(int num) {
        String[] I = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] X = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] C = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] M = new String[]{"", "M", "MM", "MMM"}; // (0 ~ 3999)

        return M[num / 1000] + C[num % 1000 / 100] + X[num % 100 / 10] + I[num % 10];
    }

    /*
        Better idea
        Time: O(N)
    */
    private static int[] nums = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400,
            500, 900, 1000};
    private static String[] roms = new String[]{"I", "IV", "V", "IX", "X",
            "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        for (int i = nums.length - 1; i >= 0 ; i--) {
            while (num >= nums[i]) { // 1443
                res.append(roms[i]);
                num = num - nums[i];
            }
        }
        return res.toString();
    }
}
