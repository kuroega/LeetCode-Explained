class BitwiseAndOfNumbersRange {
    /*
        long version:
            iterative approach
    */
    public int rangeBitwiseAnd(int m, int n) {
        int res = 0;

        while (m != 0 && n != 0) {
            // find the position of the leftmost '1'
            // for smallest
            int small = m;
            int position1 = 0;
            while (small != 0) {
                position1++;
                small >>>= 1;
            }
            // for largest
            int large = n;
            int position2 = 0;
            while (large != 0) {
                position2++;
                large >>>= 1;
            }
            if (position1 != position2) break;
            int common = (int)Math.pow(2, position1 - 1);
            res += common;
            m -= common;
            n -= common;
        }

        return res;
    }

    /*
        short version:
            recursion approach
        faster
    */
    public int rangeBitwiseAnd(int m, int n) {
        return m == n ? m : rangeBitwiseAnd((m >> 1), (n >> 1)) << 1;
    }
}
