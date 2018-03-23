public class LongestConsecutiveSequence {
    /*
        Two set solution

        Time: O(N)
    */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num);
        Set<Integer> seen = new HashSet<>();
        int max = 0;
        for (int n : numSet) {
            if (seen.contains(n)) continue;
            int number = n;
            int count = 1;
            seen.add(n);
            while (numSet.contains(n - 1)) {
                seen.add(n - 1);
                n--;
                count++;
            }
            n = number;
            while (numSet.contains(n + 1)) {
                seen.add(n + 1);
                n++;
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
