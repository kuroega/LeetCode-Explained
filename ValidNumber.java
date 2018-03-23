class ValidNumber {
    /*
        My Intuition:
            In the real-world production
            it is like a comipler, then I choose to
            implement this by regex.
            ...but low in effeciency
    */
    public boolean isNumber(String s) {
        String str = s.trim();
        if (str.matches("[+-]?(([0-9]+(\\.[0-9]*)?)|([0-9]*\\.[0-9]+))(e[+-]?[0-9]+)?"))
            return true;
        else
            return false;
    }

    /*
        linear check
        Time: O(N)
        much faster
        ...but slightly more difficult to cover all the cases while coding
    */
    public boolean isNumber(String s) {
        String str = s.trim();

        boolean numSeen = false;
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numAfterE = true;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                if (i != 0 && str.charAt(i - 1) != 'e')
                    return false;
            } else if ('0' <= str.charAt(i) && str.charAt(i) <= '9') {
                numSeen = true;
                numAfterE = true;
            } else if (str.charAt(i) == '.' && !pointSeen && !eSeen) {
                pointSeen = true;
            } else if (str.charAt(i) == 'e' && !eSeen && numSeen) {
                eSeen = true;
                numAfterE = false;
            } else {
                return false;
            }
        }
        return numSeen && numAfterE;
    }
}
