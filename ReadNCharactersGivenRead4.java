public class ReadNCharactersGivenRead4 {
    /*
        this is question is not intuitive.
        sometimes without a solution it will be misleading
    */

    int i4 = 0, n4 = 0;
    char[] buf4 = new char[4];
    public int read(char[] buf, int n) {
        int i = 0;
        while (i < n) {
            if (i4 == 0) {
                n4 = read4(buf4);
            }
            if (n4 == 0) break;
            while (i < n && i4 < n4) {
                buf[i++] = buf4[i4++];
            }
            if (i4 == n4) i4 = 0;
        }
        return i;
    }
}
