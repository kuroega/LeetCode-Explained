public class ZigzagConversion {
    public String convert(String s, int numRows) {
        StringBuilder[] s_arr = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++)
            s_arr[i] = new StringBuilder();

        int i = 0, counter = 0;
        while (i < s.length()) {
            counter = 0;
            while (counter < numRows && i < s.length()) {
                s_arr[counter].append(s.charAt(i));
                i++;
                counter++;
            } // "↓"

            counter = numRows - 2; // start from 2nd row from the end
            while (counter > 0 && i < s.length()) {
                s_arr[counter].append(s.charAt(i));
                counter--;
                i++;
            } // "↗"
        } // fill string in zigzag fashion

        StringBuilder res = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            if (s_arr[j] == null) {
                break;
            }
            res.append(s_arr[j]); // append each row
        }
        return res.toString();
    }
}
