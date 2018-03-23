public class Sqrt {

    /*
        Functional Programming Paradigm: tail recursion
        rewritten version of Scala code by Martin Odersky
    */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        Double res = sqrtIter(1.0, x);
        return res.intValue();
    }
    private Double sqrtIter(Double guess, int x) {
        if (isGoodEnough(guess, x)) return guess;
        else return sqrtIter(improve(guess, x), x);
    }
    private boolean isGoodEnough(Double guess, int x) {
        return Math.abs(guess * guess - x) / x < 0.0000000001; // 1e-10
    }
    private Double improve(Double guess, int x) {
        return (guess + x / guess) / 2;
    }

    /*
        iterative Solution
    */

    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = Integer.MAX_VALUE;
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left)/2;
            // mid is the guess value
            if (mid > x/mid) { // means guess is too big
                right = mid - 1;
            } else { // means guess can be larger
                ans = mid; // because we return the int part, so ans <= sqrt(x)
                left = mid + 1;
            }
        }
        return ans;
    }
}
