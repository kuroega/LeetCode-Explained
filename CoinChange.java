public class Solution {
    public int coinChange(int[] coins, int amount) {
        return coinChange(0, amount, coins[])
    }
    private int coinChange(int idxCoin, int amount, int[] coins) {
        if (amount == 0)
            return 0;
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount / coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = coinChange(idxCoin + 1, amount - x * coins[idxCoin], coins[]);
                    if (res != -1) {
                        minCost = Math.min(minCost, res + x);
                    }
                }
            }
            return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
        }
        return -1;
    }
}
// This is a brute force approach which causes TIME LIMIT EXCEEDED
// Time: O(S^n) exponential time
// Space: O(N) n system recursive stacks

public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }
    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin: coins) {
            int res = coinChange(coins[], rem - coin, count);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
}
// This is a DP Solution (Top-down Memoization): recursion
// Time: O(S * n) recursion depth: S, for each recursion: n loops
// Space: O(S)

public class Solution {
    public int coinChange(int coins[], int amount) {
        if (amount == 0) return 0;
        return coinChange(coins, amount, new int[amount + 1]);
    }
    private int coinChange(int[] coins[], int amount, int[] dp) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] < i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return (dp[amount] == max) ? -1 : dp[amount];
    }
}
// This is a DP Solution (Bottom-Up Memoization): iterative
// Time: O(S*N) outer loop: S, inner loop: N
// Space: O(S + 1) Size of the Memoization Table
