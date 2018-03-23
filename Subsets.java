public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, nums, new ArrayList<Integer>(), 0);
        return res;
    }
    private void backtrack(List<List<Integer>> res, int[] nums, List<Integer> tmplist, int start) {
        res.add(new ArrayList<>(tmplist));
        for (int i = start; i < nums.length; i++) {
            tmplist.add(nums[i]);
            backtrack(res, nums, tmplist, i + 1);
            tmplist.remove(tmplist.size() - 1);
        }
    }

    /*
        Follow-Up: contains duplicate nuimbers
    */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, nums, new ArrayList<Integer>(), 0);
        return res;
    }
    private void backtrack(List<List<Integer>> res, int[] nums, List<Integer> tmplist, int start) {
        res.add(new ArrayList<Integer>(tmplist));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            tmplist.add(nums[i]);
            backtrack(res, nums, tmplist, i + 1);
            tmplist.remove(tmplist.size() - 1);
        }
    }
}
