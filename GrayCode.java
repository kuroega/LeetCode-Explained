public class GrayCode {
    /*
        Mathematical Induction
        get the idea from n = 3 can be derived from n = 2

        for n = 2 -> ( 00, 01, 11, 10)
        for n = 3 -> (000,001,011,010)(110,111,101,100)
        the last 2 digits in n = 3 are same in the n = 2.
        also the second half of the n = 3 has symmetric last 2-digit
        as the first half.
    */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                res.add(res.get(j) | 1 << i);
            }
        }
        return res;
    }
}
