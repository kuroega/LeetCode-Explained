public class RestoreIPAddresses {
    /*
        For example:
            Given "25525511135", len = 11 -> 11/4 = 2
        return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
    */

    /*
        Brute Force Way:
            1. get the length of input string
            2. for a slot the range is (len/4 ~ 3)
            3. scan left to right, find all possible slots size
            4. check the number is in 0~255 inclusive
    */

    /*
        very straight forward iteration
        Must understand!
    */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return res;
        for (int i = 1; i < 4 && i < s.length() - 2; i++)
            for (int j = i + 1; j < i + 4 && j < s.length() - 1; j++)
                for (int k = j + 1; k < j + 4 && k < s.length(); k++) {
                    String s1 = s.substring(0,i), s2 = s.substring(i, j), s3 = s.substring(j, k), s4 = s.substring(k);
                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4))
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                }
        return res;

    }
    private boolean isValid(String s) {
        int num = Integer.parseInt(s);
        if (s.length() > 1 && s.charAt(0) == '0' || num > 255)
            return false;
        return true;
    }
