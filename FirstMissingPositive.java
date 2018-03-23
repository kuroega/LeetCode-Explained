public class FirstMissingPositive {
    /*
        Pre-requisite:
        Your algorithm should run in O(n) time and uses constant space.

        Solution:
            swap and use index to indicate the positive number to be placed
            > two pass
    */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        } // swap

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        } // scan and check if A[index] == index + 1, index >= 0
        return nums.length + 1; // means all index are well placed with i + 1
    }
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
