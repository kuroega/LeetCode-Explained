public class FindMinimumInRotatedSortedArray {
    /*
        (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

        duplicates does not matter.
        just observe the pattern of a rotated array.
    */
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return nums[i + 1];
        }
        return nums[0];
    }
}
