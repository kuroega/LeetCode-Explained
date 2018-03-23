public class GasStation {
    /*
        O(N^2)
            Brute Force
    */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] balance = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            balance[i] = gas[i] - cost[i];
        }
        for (int i = 0; i < balance.length; i++) {
            if (travelAround(balance, i))
                return i;
        }
        return -1;
    }
    private boolean travelAround(int[] balance, int index) {
        int remain = 0;
        for (int i = index; i < index + balance.length; i++) {
            remain += balance[i % balance.length];
            if (remain < 0) return false;
        }
        return true;
    }

    /*
        O(N)
    */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int remain = 0;
        int total = 0;
        for (int i = 0; i < gas.length; i++) {
            remain = remain + gas[i] - cost[i];
            if (remain < 0) {
                start = i + 1;
                total = total + remain;
                remain = 0;
            }
        }
        return (total + remain < 0) ? -1 : start;
        // when toal + remain = 0 means we can just make a
        // circle travel without remaining gas
    }
}
