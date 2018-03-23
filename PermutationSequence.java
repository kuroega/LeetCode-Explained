public class PermutationSequence {
    /*
        Idea: find the pattern
        Time: O(N)
        Space: O(2N)
    */
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> nums = new ArrayList<>();

        int fac = 1;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            fac = fac * i;
        }// a list for 1 ~ n & compute the factorial(n)
        k = k - 1; // because index is start by 0
        while (n > 0) {
            if (n == 0) break; // if n is 0, finish
            int group = k / (fac / n); // k / (n - 1) !,
            sb.append(String.valueOf(nums.get(group))); // fill the number
            nums.remove(group);

            k = k % (fac / n); // k = k % (n - 1) ! update k,
            fac = fac / n; // update factorial,
            n--; // update n
        }
        return sb.toString();
    }
}
