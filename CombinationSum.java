public class CombinationSum {
    /*
        all numbers can be formed by (>=0)odd number(s) and (>=0)even number(s)

        (not finished)
    */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int num = candidates.length;
        Arrays.sort(candidates); // sort

        // find the smallest even and odd number
        int min_even = 0;
        int min_odd = 0;
        for (int i = 0; i < num; i++) { // loop over candidates
            if (candidates[i] % 2 == 0) {
                min_even = candidates[i];
                break;
            }
        }
        for (int i = 0; i < num; i++) { // loop over candidates
            if (candidates[i] % 2 == 1) {
                min_odd = candidates[i];
                break;
            }
        }
        // ... boring math
    }

    /*
        More interesting Solution
        Backtracking
    */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(res, candidates, new ArrayList<Integer>(), target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] candidates, List<Integer> tmplist, int remain, int start) {
        if (remain < 0) return;
        // invalid remain number
        else if (remain == 0) {
            res.add(new ArrayList<>(tmplist));
        }
        // right combination sum
        else {
            for (int i = start; i < candidates.length; i++) {
                tmplist.add(candidates[i]); // record candidate
                backtrack(res, candidates, tmplist, remain - candidates[i], i); //bt
                tmplist.remove(tmplist.size() - 1); // if back, remove and try next
            } // try another number
        }
    }

    /*
        Combination Sum II
        Follow-up: not reuse number to get the sum
    */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // sort
        backtrack(res, candidates, target, new ArrayList<Integer>(), 0);
        return res;
    }
    public void backtrack(List<List<Integer>> res, int[] candidates, int remain, List<Integer> tmplist, int start) {
        if (remain < 0) return;
        else if (remain == 0) res.add(new ArrayList<>(tmplist));
        else {
            for (int i = start; i < candidates.length; i++) { // no reuse!
                if (i > start && candidates[i] == candidates[i - 1]) continue;
                tmplist.add(candidates[i]);
                backtrack(res, candidates, remain - candidates[i], tmplist, i + 1); // i + 1 to no reuse
                tmplist.remove(tmplist.size() - 1);
            }
        }
    }

    /*
        Combination Sum III
        Follow-Up: combination of k numbers, only allow use 1 ~ 9
    */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, k, n, new ArrayList<Integer>(), 1); 
        return res;
    }
    private void backtrack(List<List<Integer>> res, int k, int remain, List<Integer> tmplist, int start) {
        if (tmplist.size() > k || remain < 0) return;
        else if (tmplist.size() == k && remain == 0)
            res.add(new ArrayList<>(tmplist));
        else {
            for (int i = start; i <= 9; i++) {
                tmplist.add(i);
                backtrack(res, k, remain - i, tmplist, i + 1);
                tmplist.remove(tmplist.size() - 1);
            }
        }
    }

}
