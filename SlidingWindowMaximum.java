public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[]{};
        if (k == 1) return nums;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];
        int i;
        for (i = 0; i < k - 1; i++) { // pre-processing
            while (dq.size() != 0 && nums[dq.peekLast()] <= nums[i])
                dq.pollLast();
            dq.addLast(i);
        }
        // continue window scanning
        for (; i < nums.length; i++) {
            while (dq.size() != 0 && dq.peekFirst() <= i - k) // remove index out of range
                dq.pollFirst();
            while (dq.size() != 0 && nums[dq.peekLast()] <= nums[i])
                dq.pollLast();
            dq.addLast(i);
            ans[i - k + 1] = nums[dq.peekFirst()];
        }
        return ans;
	}
}

/*
    Sliding Window = Deque

    Pre-processing:

[    1  3]  -1 -3  5  3  6  7       3
    
    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7

     0  1   2   3  4  5  6  7
*/