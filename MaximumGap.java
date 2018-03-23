public class MaximumGap {
    /*
        all elements are non-negative integers

            Use linear Time and Space

            Idea:
                1. sort in linear time/space
                2. find the maximum gap
    */

    /*
        First solution: Radix Sort
    */
    public int maximumGap(int[] nums) {
        // find max number in array
        int m = 0;
        for (int n : nums)
            if (n > m) m = n;

        // radix-sort
        for (int e = 1; m / e > 0; e *= 10)
            countSort(nums, nums.length, e);

        // find the maximum gap
        int maxG = 0;
        for (int i = 1; i < nums.length; i++)
            maxG = Math.max(maxG, nums[i] - nums[i - 1]);

        return maxG;
    }

    private void countSort(int[] nums, int n, int e) {
        int[] output = new int[n];
        int[] count = new int[10];
        int i;
        for (i = 0; i < n; i++)
            count[(nums[i] / e) % 10]++;
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (i = n - 1; i >= 0; i--) {
            output[count[(nums[i] / e) % 10] - 1] = nums[i];
            count[(nums[i] / e) % 10]--;
        }

        for (i = 0; i < n; i++)
            nums[i] = output[i];
    }

    /*
        Second Solution: Bucket Sort
    */
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0; // retuen 0 if less than 2 numbers

        // find the minimum and maximum number
        int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n > maxVal) maxVal = n;
            if (n < minVal) minVal = n;
        }

        // calculate the number of buckets
        int gap = (int)Math.ceil((double)(maxVal - minVal) / (nums.length - 1));

        // set the boundary of buckets
        int[] bucketMin = new int[nums.length - 1];
        int[] bucketMax = new int[nums.length - 1];
        Arrays.fill(bucketMin, maxVal);
        Arrays.fill(bucketMax, minVal);

        // fill the unsorted number into right bucket
        for (int n : nums) {
            if (n == minVal || n == maxVal) continue;
            int bucketIndex = (n - minVal) / gap; // the bucket for current number
            bucketMin[bucketIndex] = Math.min(bucketMin[bucketIndex], n);
            bucketMax[bucketIndex] = Math.max(bucketMax[bucketIndex], n);
        }

        // find the largest gap for successive number in sorted form
        int maxG = 0;
        int pre = minVal; // left boundary
        for (int i = 0; i < nums.length - 1; i++) {
            if (bucketMin[i] == maxVal && bucketMax[i] == minVal) continue; // empty bucket 
            maxG = Math.max(maxG, bucketMin[i] - pre);
            pre = bucketMax[i];
        }
        return Math.max(maxG, maxVal - pre); // right boundary
    }
}
