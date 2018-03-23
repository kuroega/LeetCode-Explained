public class BestTimeToBuyAndSellStock {
    /*
        Buy one and sell one
    */
    public int maxProfit(int[] prices) {
        int max_profit = 0;
        int buy_index = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prices[buy_index]) // maintain the minimum value of buy
                buy_index = i;
            else {
                max_profit = Math.max(max_profit, prices[i] - prices[buy_index]);
            }
        }
        return max_profit;
    }

    /*
        Follow-Up: buy and sell multiple times

        Simple iteration:
            because profit gaining only happen in a
            non-descending order and all profit of
            buy-and-sell can be formed by (multiple)contiguous transactions
    */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int max_profit = 0;
        for (int i = 0; i < n - 1; i++) {
            if (prices[i + 1] > prices[i])
                max_profit += prices[i + 1] - prices[i];
        }
        return max_profit;
    }

    /*
        Follow-Up: allow at most 2 transactions

        Idea: DP
            Consider only four conditions:
            1. Just bought 1st stock
            2. Just sold 1st stock
            3. Just bought 2nd stock
            4. Just sold 2nd stock

        but we have to update 1 - 4 in inverse order, which means that
        we can only sell current stock after we bought a previous one
    */
    public int maxProfit(int[] prices) {
        int firstBought, firstSold, secondBought, secondSold;
        firstBought = secondBought = Integer.MIN_VALUE;
        firstSold = secondSold = 0;
        for (int price : prices) {
            secondSold = Math.max(secondSold, secondBought + price);
            secondBought = Math.max(secondBought, firstSold - price);
            firstSold = Math.max(firstSold, price + firstBought);
            firstBought = Math.max(firstBought, -price);
        }
        return secondSold;
    }

    /*
        Allow at most K transactions
            consider two cases:
                1. k >= prices.length / 2 , which means we can make as many transactions as we want
                2. k < price.length / 2, which means we need to use a dp solution like in previous follow-up
    */

    public int maxProfit(int k, int[] prices) {
        if (k >= prices.length / 2)
            return unlimited(prices); // case 1

        int[][] dp = new int[2][k];   // case 2
        for (int i = 0; i < k; i++)
            dp[0][i] = Integer.MIN_VALUE;

        for (int price : prices) {
            for (int i = k - 1; i >= 0; i--) {
                dp[1][i] = Math.max(dp[1][i], dp[0][i] + price);
                if (i > 0)
                    dp[0][i] = Math.max(dp[0][i], dp[1][i - 1] - price);
                else
                    dp[0][i] = Math.max(dp[0][i], -price);
            }
        }
        return k == 0 ? 0 : dp[1][k - 1];
    }

    private int unlimited(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1])
                profit += prices[i + 1] - prices[i];
        }
        return profit;
    }
}
