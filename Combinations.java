public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, n, k, new ArrayList<Integer>(), 1);
        return res;
    }
    private void backtrack(List<List<Integer>> res, int n, int k, List<Integer> tmplist, int start) {
        if (tmplist.size() == k) {
            res.add(Arrays.asList(tmplist));
        } else {
            for (int i = start; i <= n; i++) {
                tmplist.add(i);
                backtrack(res, n, k, tmplist, i + 1);
                tmplist.remove(tmplist.size() - 1);
            }
        }
    }
}
