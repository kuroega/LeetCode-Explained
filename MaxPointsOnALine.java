/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class MaxPointsOnALine {
    /*
        Brute Force Idea:
            1. record all slope
            2. for each slope, we map the number of the points on it
            3. calculate the maximum number of points in all slopes
    */
    /*
        DON'T USE slope as key to count the number of the points,
        because the precision of the double is not 100% guaranteed
    */

    /*
        Best Idea So far:
            divide the counting points on the same line in terms of 3 attributes:
                1. same X (include same Y) -> tan(90˚) OR overlap
                2. same Y (exclude same X) -> tan(0˚)
                3. others -| same slope
                           | same points (x1 = x2, y1 = y2)
    */
    public int maxPoints(Point[] points) {
        if (points.length <= 2) return points.length;

        int res = 0;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int overlap = 1; // overlap requires 2 points at least, hence start by 1
            int max = 0; // max points for current i
            for (int j = i + 1; j < points.length; j++) {
                int xdiff = points[i].x - points[j].x;
                int ydiff = points[i].y - points[j].y;
                if (xdiff == 0 && ydiff == 0) {
                    overlap++;
                    continue;
                }
                int gcd = gcd(xdiff, ydiff);
                if (gcd != 0) {
                    xdiff /= gcd;
                    ydiff /= gcd;
                }
                if (map.containsKey(xdiff)) {
                    if (map.get(xdiff).containsKey(ydiff)) {
                        map.get(xdiff).put(ydiff, map.get(xdiff).get(ydiff) + 1); // same slope, + 1
                    } else {
                        map.get(xdiff).put(ydiff, 1); // new slope, =1
                    }
                } else {
                    Map<Integer, Integer> m = new HashMap<>();
                    m.put(ydiff, 1);
                    map.put(xdiff, m);
                }
                max = Math.max(max, map.get(xdiff).get(ydiff)); // update for every (i, j)
            }
            res = Math.max(res, max + overlap); // update global maximum
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }


}
