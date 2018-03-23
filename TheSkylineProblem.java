public class TheSkylineProblem {
    /*
       best intuitive solution
           for better understand the problem
    */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();
        for (int[] bd : buildings) {
            // reverse the sign to make sure left higher bar is always before
            // left lower bar; also negative sign can indicate a start point
            heights.add(new int[]{bd[0], -bd[2]});
            heights.add(new int[]{bd[1], bd[2]});
        }
        Collections.sort(heights, (a, b) -> {
            if (a[0] == b[0]) // pre-validate
                return a[1] - b[1]; // LL: high->low | LR: 
            return a[0] - b[0]; // in x axis ascending order
        });
        // rewrite the comparator using lambda, create max heap
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0); // baseline
        int preHeight = 0; // initial height is zero
        for (int[] arr : heights) {
            if (arr[1] < 0)
                pq.offer(-arr[1]); // negative height -> left side -> start point
            else
                pq.remove(arr[1]); // positive -> right side -> end point

            // assign a max height so far in pq
            int curHeight = pq.peek();

            // avoid add 2 points for a same building
            if (preHeight != curHeight) {
                res.add(new int[]{arr[0], curHeight});
                preHeight = curHeight;
            }
        }
        return res;
    }
    /*
        For performance improvement:
            use linked list 
    */
}