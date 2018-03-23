public class RotateArray {
    /*
        use extra space:
            Time: O(N)
            Space: O(N)
    */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (k % n == 0) return;
        k = k % n;
        int[] tmp = new int[k];
        int i;
        for (i = n - k; i < n; i++) {
            tmp[i - n + k] = nums[i];
        }
        for (i = n - k - 1; i >= 0; i--) {
            nums[i + k] = nums[i];
        }
        for (i = 0; i < k; i++) {
            nums[i] = tmp[i];
        }
    }
    /*
        right shift one by one step

        Time: O(k * N) => TLE
        Space: O(1)
    */
    public void ratate(int[] nums, int k) {
        int n = nums.length;
        if (k % n == 0) return;
        k = k % n;

        for (int i = 0; i < k; i++) {
            int tmp = nums[n - 1];
            for (int j = n - 2; j >= 0; j--) {
                nums[j + 1] = nums[j];
            }
            nums[0] = tmp;
        }
    }

    /*
        Best Solution: Juggle
            Time: O(N)
            Space; O(1)
    */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (k % n == 0) return;
        k = k % n;

        int gcd = gcd(n, k);
        int i, j, tmp, next;
        for (i = 0; i < gcd; i++) {
            j = i;
            tmp = nums[i];
            while (true) {
                next = j - k; // this is right shift case, if we do left shift, it is "next = j + k;"
                if (next < 0)                                                      // "if (next >= n)"
                    next = next + n;                                               //   "next = next % n;"
                if (next == i)
                    break;
                nums[j] = nums[next];
                j = next;
            }
            nums[j] = tmp;
        }
    }
    private int gcd(int a, int b) {
        if (a == 0) return b;
        else return gcd(b % a, a);
    }
}
