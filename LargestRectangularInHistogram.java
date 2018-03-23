public class LargestRectangularInHistogram {

    /*
        Brute Force Way:
            find all pairs of rectangular bars and
            maintaining a maximum area while computing
            their areas.
    */

     /*
        Stack Solution
        Time: O(N)
     */

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] h = new int[n + 1];
        for (int i = 0; i < n; i++)
            h[i] = heights[i];
        h[n] = 0;
        int max = 0;
        Stack<Integer> index = new Stack<>();

        for (int i = 0; i <= n; i++) { 
            // find a descending pair
            while (!index.empty() && h[index.peek()] >= h[i]) {
                int hi = h[index.pop()];
                int sidx = index.empty() ? -1 : index.peek();

                if (hi * (i - sidx - 1) > max) {
                    max = hi * (i - sidx - 1);
                }
            }
            index.push(i);
        }
        return max;
    }

    /*
        Much faster: Two-Pointers Solution
        Time: O(N)
        
    */

}
