public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        // deal with corner case
        if (denominator == 0) return "Denominator cannot be 0!";
        if (numerator == 0) return "0";

        StringBuilder res = new StringBuilder();

        // sign
        res.append((numerator > 0) ^ (denominator > 0) ? "-" : "");
        long num = Math.abs((long)numerator); // convert to long to avoid overflow
        long den = Math.abs((long)denominator);

        // integral part
        long quotient = num / den;
        res.append(quotient);
        long remain = num % den;
        if (remain == 0) return res.toString();

        // fraction part
        Map<Long, Integer> map = new HashMap<>(); // use a map to store the remainder and index
        res.append(".");
        while (true) {
            map.put(remain, res.length());
            quotient = (remain * 10) / den;
            remain = (remain * 10) % den;
            if (map.containsKey(remain)) {
                res.append(quotient);
                int index = map.get(remain);
                res.insert(index, '(');
                res.append(')');
                break;
            }
            if (remain == 0) {
                res.append(quotient);
                break;
            }
            res.append(quotient);
        }

        return res.toString();
    }
}
