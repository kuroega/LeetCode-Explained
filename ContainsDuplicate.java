class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length <= 1) return false;
        
        // Arrays.sort(nums);
        // int pre = nums[0];
        // for (int i = 1; i < nums.length; i++) {
        //     if (pre == nums[i]) return true;
        //     else pre = nums[i];
        // }
        // return false;
        int min = nums[0];
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max)
                max = nums[i];
            if (nums[i] < min)
                min = nums[i];
        }
        boolean[] seen = new boolean[max - min + 1];
        for (int i = 0; i < nums.length; i++) {
            if (seen[nums[i] - min] == true)
                return true;
            seen[nums[i] - min] = true;
        }
        return false;
    }

    /*
        Contains Duplicate II
        Follow-Up: 
            if duplicate indices i and j have distance at most k
    */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length <= 1) return false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i - k - 1]); // remove the number exceeds the range k
            if (!set.add(nums[i])) return true; // if set still contains the number, true
        }
        return false;
    }

    /*
        Contains Duplicate III
        Follow-Up:
            whether there are two distinct indices i and j in the array
            such that the absolute difference between nums[i] and nums[j]
            is at most t and the absolute difference between i and j is at most k.

            idea: 
            1. We use the bucketing intuition, suppose t is the range 
            for each bucket, then only the number in the same bucket or in the 
            successive buckets can have a maximum difference as t.
            2. While linear scanning the array, if the size of the mapping exceeds k,
            then we will remove the i-k element.
            3. Considering the negative number, we choose to use the start value for 
            bucket as Integer.MIN_VALUE, for each number we scanned, we only store the
            difference of nums[i] and Integer.MIN_VALUE
            4. Bucket number = (nums[i] - Integer.MIN_VALUE) / (t + 1). The reason use
            (t + 1) is to avoid illegal denominator when t = 0
    */  
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.size() > k) { // exceeds k-size window 
                long bucketOut = ((long)nums[i - k - 1] - Integer.MIN_VALUE) / ((long)t + 1);
                map.remove(bucketOut); // remove the first key exceeds the window
            }
            long value = (long)nums[i] - Integer.MIN_VALUE;
            long bucket = value / ((long)t + 1); // compute which bucket should be put in
            if (map.containsKey(bucket) || 
                (map.containsKey(bucket + 1) && (map.get(bucket + 1) - value <= t)) ||
                (map.containsKey(bucket - 1) && (value - map.get(bucket - 1) <= t)))
                return true;
            map.put(bucket, value);
        }            
        return false;
    }
}