public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // int res = 0;
        // for (int i = 0; i < 32; i++) {
        //     res += (n & 1);
        //     n >>>= 1;
        //     if (i < 31) {
        //         res <<= 1;
        //     }
        // }
        // return res;
        int res = (n << 16 | n >>> 16);                             // x        y        -> y        x           reverse 1/2: 16b-wise complete
        res = ((res & 0x00ff00ff) << 8 | (res & 0xff00ff00) >>> 8); // a   b    c   d    -> b   a    d   c       reverse 1/2: 8b-wise complete
        res = ((res & 0x0f0f0f0f) << 4 | (res & 0xf0f0f0f0) >>> 4); // a b a b  c d c d  -> b a b a  d c d c     reverse 1/2: 4b-wise complete
        res = ((res & 0x33333333) << 2 | (res & 0xcccccccc) >>> 2); // abababab cdcdcdcd -> babababa dcdcdcdc    reverse 1/2: 2b-wise complete
        res = ((res & 0x55555555) << 1 | (res & 0xaaaaaaaa) >>> 1); // the final reverse between every 1 bit     reverse 1/2: 1b-wise complete
        return res; // reverse bit complete
    }
}
