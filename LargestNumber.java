public class LargestNumber {
    /*
        Using a PriorityQueue to maintain the order
    */
    private Comparator<Integer> comparator = new Comparator<Integer>(){
        public int compare(Integer a, Integer b) {
            String s1 = Integer.toString(a);
            String s2 = Integer.toString(b);
            for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
                if (s1.charAt(i) > s2.charAt(i)) return 1;
                else if (s1.charAt(i) < s2.charAt(i)) return -1;
            }
            // if the common length part is equivalent, i.e. 12 vs 121
            int minLen = Math.min(s1.length(), s2.length());
            s1 = s1 + s2;
            s2 = s2 + s1;
            // 12 128
            for (int i = minLen; i < s1.length(); i++) {
                if (s1.charAt(i) > s2.charAt(i)) return 1;
                else if (s1.charAt(i) < s2.charAt(i)) return -1;
            }
            return 0;

        }
    };

    public String largestNumber(int[] nums) {
        int zeros = 0;
        for (int n : nums)
            if (n == 0) zeros++;
        if (zeros == nums.length) return "0";

        String res = "";
        PriorityQueue<Integer> pq = new PriorityQueue<>(comparator);
        for (int n : nums)
            pq.add(n);

        while (pq.size() != 0)
            res = pq.poll() + res;

        return res;
    }

    /*
        But think again, the order in this problem is actually static
        we do not need a dynamic order maintained.

        Just use a immutable array is enough
    */
    public String largestNumber(int[] nums) {
        String[] strNum = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strNum[i] = String.valueOf(nums[i]);

        Arrays.sort(strNum, new Comparator<String>(){
            public int compare(String s1, String s2) {
                return -(s1 + s2).compareTo(s2 + s1);
            }
        });
        // a stringbuilder is more efficient when requiring a lot of adding or deleting operations
        StringBuilder sb = new StringBuilder();

        for (String str : strNum)
            sb.append(str);

        while (sb.length() > 1 && sb.charAt(0) == '0') // remove leading 0s when length > 1
            sb.deleteCharAt(0);

        return sb.toString();
    }
}
