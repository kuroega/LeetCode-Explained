pubic class NextPermutation {
    /*
        Narayana Pandita's algorithm
    */
    public void nextPermutation(int[] nums) {
        int k = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                k = i;
                break;  // the 1st index meets the repuirement
            }
        } // iterate from the end and find the 1st k s.t. a[k] < a[k + 1]

        if (k == -1) { // means the current is the last permutation
            reverse(nums, 0, nums.length - 1);
            return;
        } // reverse the nums in ascending order

        int l = 0;
        for (int i = nums.length - 1; i > k; i--) {
            if (nums[k] < nums[i]) {
                l = i;
                break;  // the 1st index meets the repuirement
            }
        } // iterate from the end and find the 1st l s.t. a[k] < a[l]

        swap(nums, k, l);
        // swap a[k] with a[l] (val)

        reverse(nums, k + 1, nums.length - 1);
        // reverse the sequence from k+1 to the end

        return;
     }

     private void swap(int[] nums, int a, int b) {
         int tmp = nums[a];
         nums[a] = nums[b];
         nums[b] = tmp;
     }

     private void reverse(int[] nums, int start, int end) {
         while (start < end) {
             int tmp = nums[end];
             nums[end] = nums[start];
             nums[start] = tmp;
             start++;
             end--;
         }
         return;
     }
}
}
