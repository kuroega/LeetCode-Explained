public class DivideTwoNumbers {
    /*
        Convert the problem to positive division is easier!
        use long to avoid overflow
    */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE; // edge case

        int sign = 1;
        if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0)
            sign = -1;
        // set sign

        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor); // convert to positive

        if (ldividend == 0 || ldividend < ldivisor) return 0;
        long lans = ldivide(ldividend, ldivisor);
        // compute long ans
        int ans;
        if (lans > Integer.MAX_VALUE) {
            ans = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = sign * (int)lans;
        }
        // output ans

        return ans;
    }
    private long ldivide(long ldividend, long ldivisor) { // recursively
        if (ldividend < ldivisor) return 0;
        // base case

        long sum = ldivisor;
        long multiple = 1;
        while ((sum + sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        // recursive case

        return multiple + ldivide(ldividend - sum, ldivisor);
    }
}
