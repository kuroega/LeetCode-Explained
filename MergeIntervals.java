public class MergeIntervals {
    private Comparator<Interval> comp = new Comparator<Interval>(){
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    };
    /*
        Priority Queue Solution
        Time: O(NlogN)
        Space: O(2N)
    */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 2) return intervals;

        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>(comp);
        for (int i = 0; i < intervals.size(); i++) {
            pq.add(intervals.get(i));
        }
        Interval cur = pq.poll();
        while (pq.size() != 0) {
            if (cur.end >= pq.peek().start) { // can merge, keep merging
                if (cur.end < pq.peek().end)
                    cur.end = pq.peek().end;
                pq.poll();
            } else { // cannot merge, add cur
                res.add(cur);
                cur = pq.poll();
            }
        }
        res.add(cur);
        return res;
    }

    /*
        sort start and end separately
        Time: O(NlogN) -> sort
        Space: O(3N)
    */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        for (int head = 0, tail = 0; tail < n; tail++) {
            if (tail == n - 1 || end[tail] < start[tail + 1]) { // disjoint, cannot merge
                res.add(new Interval(start[head], end[tail]));
                head = tail + 1;
            }
        }
        return res;
    }

}
