class TwoSumClass {
    Map<Integer, Integer> map;
    int minAllow; // optimization for early termination
    int maxAllow;

    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
        minAllow = Integer.MAX_VALUE;
        maxAllow = Integer.MIN_VALUE;
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        minAllow = Math.min(minAllow, number); // maintains min so far
        maxAllow = Math.max(maxAllow, number); // maintains max so far
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if ((value < 2 * minAllow) || (value > 2 * maxAllow)) return false; // early termination
        for (int number : map.keySet()) {
            int target = value - number;
            if ((target == number && map.get(number) > 1) || (target != number && map.containsKey(target)))
                return true;
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
