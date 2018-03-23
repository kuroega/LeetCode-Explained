public class MaximumProductSubarray {
    /*
        This is a very tricky but clear solution
    */

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int min = nums[0];   // maintains a min value end in current index from SOMEWHERE before
        int max = nums[0];   // maintains a max value end in current index from SOMEWHERE before
        int maxSoFar = nums[0]; // the overall maximum result

        for (int i = 1; i < n; i++) {
            if (nums[i] < 0) { // a max product for negative value (negative times smaller number is larger)
                int tmp = max;
                max = min;
                min = tmp;
            }

            max = Math.max(nums[i], max * nums[i]);  
            min = Math.min(nums[i], min * nums[i]);

            maxSoFar = Math.max(max, maxSoFar);
        }
        return maxSoFar;
    }
}
