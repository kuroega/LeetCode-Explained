public class MultiplyStrings {

    /*
        Brute Force way
        traditional routine
        But...Stack Overflow...
    */

    public String multiply(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int l1 = num1.length();
        int l2 = num2.length();
        int res = 0;
        for (int i = l1 - 1; i >= 0; i-- ) {
            int carry = 0;
            int ten = 0;
            int prod1 = 0;
            for (int j = l2 - 1; j >= 0; j-- ) {
                int prod = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int digit = (prod + carry) % 10;
                carry = (prod + carry) / 10;
                prod1 += digit * Math.pow(10, ten);
                ten++;
            }
            prod1 += carry * Math.pow(10, ten);
            res += prod1 * Math.pow(10, l1 - i - 1);
        }
        return String.valueOf(res);
    }

    /*
        Smarter way
        use int[] to store individual digits!
    */
    public String multiply(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int l1 = num1.length();
        int l2 = num2.length();
        int[] res = new int[l1 + l2];

        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                int prod = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = prod + res[i + j + 1];

                res[i + j + 1] = sum % 10; // do not need to +=, last step added
                res[i + j] += sum / 10;
            }
        }
        for (int num : res) {
            if (!(sb.length() == 0 && num == 0) )
                sb.append(num); // StringBuilder can append int directly as str
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

}
