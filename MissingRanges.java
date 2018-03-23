class MissingRanges {
    /*
        exp:
            Input: [0, 1, 3, 50, 75], lower = 0 and upper = 99
            
            Output: ["2", "4->49", "51->74", "76->99"].
    */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        // empty array
        if (nums.length == 0) {
            res.add(generateRange(lower, upper));
            return res;
        }
        // i = 0, deal with lower bound
        if (nums[0] > lower) 
            res.add(generateRange(lower, nums[0] - 1));
            
        // i > 0, rest of array
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i] && nums[i - 1] + 1 != nums[i]) {
                res.add(generateRange(nums[i - 1] + 1, nums[i] - 1));
            }
        }
        // deal with upper bound
        if (nums[nums.length - 1] < upper) {
            res.add(generateRange(nums[nums.length - 1] + 1, upper));
        }
        return res;
    }
    private String generateRange(int lower, int upper) {
        if (lower == upper) {
            return lower + "";
        } else {
            return lower + "->" + upper;
        }
    }
}