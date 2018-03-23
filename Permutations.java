class Solution {

    /*
        Permutation I
        backtrack!
        nums[] only contains distinct elements
    */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, new ArrayList<Integer>());
        return res;
    }
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> tmplist) {
        if (tmplist.size() == nums.length) {
            res.add(new ArrayList<>(tmplist));
            return;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!tmplist.contains(nums[i])) {
                    tmplist.add(nums[i]);
                    backtrack(nums, res, tmplist);
                    tmplist.remove(tmplist.size() - 1);
                }
            }
        }
    }

    /*
        Permutation II
        Follow-Up
        nums[] may contain duplicates
    */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, res, new ArrayList<Integer>(), new boolean[nums.length]);
        return res;
    }
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> tmplist, boolean[] used) {
        if (tmplist.size() == nums.length) {
            res.add(new ArrayList<>(tmplist));
            return;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                tmplist.add(nums[i]);
                used[i] = true;
                backtrack(nums, res, tmplist, used);
                tmplist.remove(tmplist.size() - 1);
                used[i] = false;
            }
        }
    }

}
