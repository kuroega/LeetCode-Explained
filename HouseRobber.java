class HouseRobber {

    // array of non-negative number
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int[][] dp = new int[2][n]; // 1st row => exclude current max; 2nd row => include current max
        dp[0][0] = 0;
        dp[1][0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1]);
            dp[1][i] = dp[0][i - 1] + nums[i];
        }
        return Math.max(dp[0][n - 1], dp[1][n - 1]);
    }
    
    /*
        avoid table version:
        since we only need dp[0][i - 1] and dp[1][i - 1];
        we can use 2 variables to store
    */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int preExclusiveMax = 0;
        int preInclusiveMax = nums[0];
        for (int i = 1; i < n; i++) {
            int tmp = Math.max(preInclusiveMax, preExclusiveMax);
            preInclusiveMax = preExclusiveMax + nums[i];
            preExclusiveMax = tmp;
        }
        return Math.max(preInclusiveMax, preExclusiveMax);
    }

    /*
        HouseRobber II
        Follow-Up: the houses are in a circle

            idea: the max $ is the maximum amount of money
                of: 1. rob from first to 2nd last
                    2. rob from second to the last
    */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int exclusive1 = 0;
        int inclusive1 = nums[0];
        int exclusive2 = 0;
        int inclusive2 = nums[1];

        for (int i = 1; i < nums.length; i++) {

            // 0 ~ n - 1
            if (i < nums.length - 1) { 
                int tmp = Math.max(exclusive1, inclusive1);
                inclusive1 = exclusive1 + nums[i];
                exclusive1 = tmp;
            }

            // 1 ~ n
            if (i >= 2) { 
                int tmp = Math.max(exclusive2, inclusive2);
                inclusive2 = exclusive2 + nums[i];
                exclusive2 = tmp;
            }
        }
        
        int max1 = Math.max(exclusive1, inclusive1);
        int max2 = Math.max(exclusive2, inclusive2);
        return Math.max(max1, max2);
    } 
}
