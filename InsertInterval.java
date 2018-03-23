public class InsertInterval {
    private Comparator<Interval> comp = new Comparator<Interval>(){
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    };
    /*
        Priority Queue Solution
        Time: O(NlogN)
        Space: O(2N)
        Not the best way...
    */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>(comp);
        for (int i = 0; i < intervals.size(); i++) {
            pq.add(intervals.get(i));
        }
        pq.add(newInterval);
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
        Most Straight Forward Solution
        we only need to take care of the merging of the newInterval,
        because the input list is already sorted according to start value.
    */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();

        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i++));
        } // add the disjoin intervals before merging

        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval(
                Math.min(newInterval.start, intervals.get(i).start),
                Math.max(newInterval.end, intervals.get(i).end)
            );
            i++;
        } // keep finding the widest merging interval and
        res.add(newInterval); // add

        while (i < intervals.size())
            res.add(intervals.get(i++));
        // add the rest of the (disjoint) intervals
        return res;
    }

}
