public class JumpGame {
    /*
        return whether can we reach the last index

        index  0 1 2 3 4
              [2,3,1,1,4] 3
              [2,0,0]
    */
    public boolean canJump(int[] nums) {
        if (nums.length < 2) return true;
        int currentMax = 0; // from index=0
        int nextMax = 0;
        int i = 0;

        while (currentMax >= i) {
            nextMax = Math.max(nextMax, nums[i] + i);
            if (nextMax >= nums.length - 1)
                return true;
            i++;
            if (currentMax < i)
                currentMax = nextMax;
        }
        return false;
     }

    /*
        Follow-Up: return the minimum steps to reach the last index

      index  0 1 2 3 4
            [2,3,1,1,4] 3
    */
    public int jump(int[] nums) {
        if (nums.length < 2) return 0;
        int level = 1; // if not 0, then at least 1 step
        int currentMax = 0;
        int nextMax = 0;
        int i = 0;

        while (currentMax >= i) {
            nextMax = Math.max(nextMax, nums[i] + i);
            // the longest from i to next can be
            if (nextMax >= nums.length - 1)
                return level; // can reach the end from i
            i++;
            if (currentMax < i) { // have to take one more step
                currentMax = nextMax;
                level++;
            }
        }
        return Integer.MAX_VALUE; // means impossible
    }
}
