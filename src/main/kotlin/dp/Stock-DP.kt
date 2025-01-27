package dp

class `Stock-Dp` {
    /**
     * Best time to buy and sell stock
     *
     *We can buy and sell a stock only once.
     * We can buy and sell the stock on any day but to sell the stock, we need to first buy it on the same or any previous day.
     * We need to tell the maximum profit one can get by buying and selling this stock.
     *
     * In this we can buy and sell the stock only once.
     */
    fun bestTimeToBuyAndSellStock(prices: IntArray): Int {
        var res = 0
        var i = 0
        var j = 1
        while (i < j && j < prices.size) {
            if (prices[i] > prices[j]) {
                i = j
            } else {
                res = maxOf(res, prices[j] - prices[i])
            }
            j++
        }

//        return res


        // using  dp
        var maxProfit = 0;
        var mini = prices[0];

        for (i in 1..prices.lastIndex) {
            val curProfit = prices[i] - mini;
            maxProfit = Math.max(maxProfit, curProfit);
            mini = Math.min(mini, prices[i]);
        }
        return maxProfit;
    }


    /**
     *
     * Buy and Sell Stock - II
     *
     * Problem Link: Best Time to Buy and Sell Stock II
     *
     * We are given an array Arr[] of length n. It represents the price of a stock on ‘n’ days. The following guidelines need to be followed:
     *
     * We can buy and sell the stock any number of times.
     * In order to sell the stock, we need to first buy it on the same or any previous day.
     * We can’t buy a stock again after buying it once.
     * In other words, we first buy a stock and then sell it. After selling we can buy and sell again.
     * But we can’t sell before buying and can’t buy before selling any previously bought stock.
     *
     * We need to tell the maximum profit one can get by buying and selling this stock.
     *
     */
    fun maxProfit(prices: IntArray): Int {
        val n = prices.size

        // if we start recursion from n-1 to 0, the res will be different bcuz

        // recursive and memoization {top-down}
        val memo = Array(n + 1) { IntArray(2) { -1 } }
        fun f(i: Int, buy: Int): Int {
            if (i == n) {
                return 0
            }
            if (memo[i][buy] != -1) return memo[i][buy]

            memo[i][buy] = if (buy == 0) {
                maxOf(
                    -prices[i] + f(i + 1, 1),
                    0 + f(i + 1, 0)
                )
            } else {
                maxOf(
                    prices[i] + f(i + 1, 0),
                    0 + f(i + 1, 1)
                )
            }
            return memo[i][buy]
        }

//        return f(0, 0)

        // tabulation {bottom-up}
        val dp = Array(n + 1) { IntArray(2) }
        for (i in n downTo 0) {
            for (buy in 1 downTo 0) {
                if (i == n) {
                    dp[i][buy] = 0
                } else {
                    dp[i][buy] = if (buy == 0) {
                        maxOf(
                            -prices[i] + dp[i + 1][1],
                            0 + dp[i + 1][0]
                        )
                    } else {
                        maxOf(
                            prices[i] + dp[i + 1][0],
                            0 + dp[i + 1][1]
                        )
                    }
                }
            }
        }

//        return dp[0][0]

        var prev: IntArray? = null
        val cur = IntArray(2)
        for (i in n downTo 0) {
            for (buy in 1 downTo 0) {
                if (i == 0) {
                    cur[buy] = 0
                } else {
                    cur[buy] = if (buy == 0) {
                        maxOf(
                            -prices[i] + prev!![0],
                            0 + prev!![1]
                        )
                    } else {
                        maxOf(
                            prices[i] + prev!![1],
                            0 + prev!![0]
                        )
                    }
                }
            }
            prev = cur
        }

        return cur[0]
    }

    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * Find the maximum profit you can achieve. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *
     *
     *
     * Example 1:
     *
     * Input: prices = [3,3,5,0,0,3,1,4]
     * Output: 6
     * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     * Example 2:
     *
     * Input: prices = [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
     * Example 3:
     *
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     *
     * Constraints:
     *
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 105
     */
    fun maxProfit3(prices: IntArray): Int {
        val n = prices.size

        //Memoization
        // for c transactions we have B , S , B , S
        val memo = Array(n + 1) { IntArray(5) { -1 } }
        fun f(i: Int, tranx: Int): Int {
            if (tranx == 4) return 0
            if (i == n) return 0

            if (memo[i][tranx] != -1) return memo[i][tranx]
            memo[i][tranx] = if (tranx % 2 == 0) {
                maxOf(
                    -prices[i] + f(i + 1, tranx + 1),
                    0 + f(i + 1, tranx)
                )
            } else {
                maxOf(
                    prices[i] + f(i + 1, tranx + 1),
                    0 + f(i + 1, tranx)
                )

            }

            return memo[i][tranx]
        }

//        return f(0, 0)


        //iterative dp Bottom-Up approach
        // base condition

        val dp = Array(n + 1) { IntArray(5) { -1 } }

        for (i in n downTo 0) {
            for (j in 4 downTo 0) {
                if (j == 4) {
                    dp[i][j] = 0
                } else if (i == n) {
                    dp[i][j] = 0
                } else {
                    dp[i][j] = if (j % 2 == 0) {
                        maxOf(
                            -prices[i] + dp[i + 1][j + 1],
                            0 + dp[i + 1][j]
                        )
                    } else {
                        maxOf(
                            prices[i] + dp[i + 1][j + 1],
                            0 + dp[i + 1][j]
                        )
                    }
                }
            }
        }


//        return dp[0][0]

        //space optimized
        var prev: IntArray? = null
        val cur = IntArray(5)
        for (i in n downTo 0) {
            for (j in 4 downTo 0) {
                if (j == 4) {
                    cur[j] = 0
                } else if (i == n) {
                    cur[j] = 0
                } else {
                    cur[j] = if (j % 2 == 0) {
                        maxOf(
                            -prices[i] + prev!![j + 1],
                            0 + prev!![j]
                        )
                    } else {
                        maxOf(
                            prices[i] + prev!![j + 1],
                            0 + prev!![j]
                        )
                    }
                }
            }
            prev = cur
        }
        return cur[0]
    }


    /**
     * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
     *
     * Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *
     *
     *
     * Example 1:
     *
     * Input: k = 2, prices = [2,4,1]
     * Output: 2
     * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
     * Example 2:
     *
     * Input: k = 2, prices = [3,2,6,5,0,3]
     * Output: 7
     * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     *
     *
     */
    fun maxProfit(k: Int, prices: IntArray): Int {
        val n = prices.size

        //memoization
        val memo = Array(n + 1) { IntArray(2 * k + 1) { -1 } }
        fun backtrack(i: Int, j: Int): Int {
            if (j == 2 * k) return 0
            if (i == n) return 0

            if (memo[i][j] != -1) return memo[i][j]
            memo[i][j] = if (j % 2 == 0) {
                maxOf(
                    -prices[i] + backtrack(i + 1, j + 1),
                    0 + backtrack(i + 1, j)
                )
            } else {
                maxOf(
                    prices[i] + backtrack(i + 1, j + 1),
                    0 + backtrack(i + 1, j)
                )
            }

            return memo[i][j]
        }

//        return backtrack(0,0)

        //bottom up approach or tabulation
        val dp = Array(n + 1) { IntArray(2 * k + 1) }
        for (i in n downTo 0) {
            for (j in 2 * k downTo 0) {
                if (j == 2 * k) {
                    dp[i][j] = 0
                } else if (i == n) {
                    dp[i][j] = 0
                } else {
                    dp[i][j] = if (j % 2 == 0) {
                        maxOf(
                            -prices[i] + dp[i + 1][j + 1],
                            0 + dp[i + 1][j]
                        )
                    } else {
                        maxOf(
                            prices[i] + dp[i + 1][j + 1],
                            0 + dp[i + 1][j]
                        )
                    }
                }
            }
        }

//        return dp[0][0]


        //using two dp array
        var prev = IntArray(2 * k + 1)
        val curr = IntArray(2 * k + 1)
        for (i in n downTo 0) {
            for (j in 2 * k downTo 0) {
                if (j == 2 * k) {
                    curr[j] = 0
                } else if (i == n) {
                    curr[j] = 0
                } else {
                    curr[j] = if (j % 2 == 0) {
                        maxOf(
                            -prices[i] + prev[j + 1],
                            0 + prev[j]
                        )
                    } else {
                        maxOf(
                            prices[i] + prev[j + 1],
                            0 + prev[j]
                        )
                    }
                }
            }
            prev = curr
        }
        return curr[0]
    }

    /**
     * Buy and Sell Stocks With Cooldown | (DP - 39)
     *
     *
     * We are given an array Arr[] of length n. It represents the price of a stock on ‘n’ days. The following guidelines need to be followed:
     *
     * We can buy and sell the stock any number of times.
     * In order to sell the stock, we need to first buy it on the same or any previous day.
     * We can’t buy a stock again after buying it once. In other words, we first buy a stock and then sell it.
     * After selling we can buy and sell again. But we can’t sell before buying and can’t buy before selling any previously bought stock.
     * We can’t buy a stock on the very next day of selling it. This is the cooldown clause.
     *
     * We need to tell the maximum profit one can get by buying and selling this stock.
     */
    fun maxProfitCooldown(prices: IntArray): Int {
        val n = prices.size

        //memoization
        val memo = Array(n + 1) { IntArray(2) { -1 } }
        fun f(i: Int, buy: Int): Int {
            if (i == n) return 0

            if (memo[i][buy] != -1) return memo[i][buy]
            memo[i][buy] = if (buy == 0) {
                maxOf(
                    -prices[i] + f(i + 1, 1),
                    0 + f(i + 1, 0)
                )
            } else {
                maxOf(
                    prices[i] + f(i + 2, 0),
                    0 + f(i + 1, 1)
                )
            }

            return memo[i][buy]

        }

//        return f(0, 0)

        //tabulation
        val dp = Array(n + 1) { IntArray(2) }
        for (i in n downTo 0) {
            for (buy in 0..1) {
                if (i == n) {
                    dp[i][buy] = 0
                } else {
                    dp[i][buy] = if (buy == 0) {
                        maxOf(
                            -prices[i] + dp[i + 1][1],
                            0 + dp[i + 1][0]
                        )
                    } else {
                        maxOf(
                            prices[i] + dp[i + 2][0],
                            0 + dp[i + 1][1]
                        )
                    }
                }
            }
        }

//        return dp[0][0]

        //space optimized
        var prev: IntArray? = null
        val cur = IntArray(2)

        for (i in n downTo 0) {
            for (buy in 1 downTo 0) {
                if (i == n) {
                    cur[buy] = 0
                } else {
                    cur[buy] = if (buy == 0) {
                        maxOf(
                            -prices[i] + prev!![1],
                            0 + prev!![0]
                        )
                    } else {
                        maxOf(
                            prices[i] + prev!![0],
                            0 + prev!![1]
                        )
                    }
                }
            }
            prev = cur
        }

        return cur[0]
    }

    /**
     * We are given an array Arr[] of length n. It represents the price of a stock on ‘n’ days. The following guidelines need to be followed:
     *
     * We can buy and sell the stock any number of times.
     * In order to sell the stock, we need to first buy it on the same or any previous day.
     * We can’t buy a stock again after buying it once. In other words, we first buy a stock and then sell it. After selling we can buy and sell again. But we can’t sell before buying and can’t buy before selling any previously bought stock.
     * After every transaction, there is a transaction fee (‘fee’) associated with it.
     *
     * We need to tell the maximum profit one can get by buying and selling this stock.
     */
    fun maxProfitWithFee(prices: IntArray, fee: Int): Int {
        val n = prices.size

        //memoization
        val memo = Array(n + 1) { IntArray(2) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i == n)
                return 0

            if (memo[i][j] != -1) return memo[i][j]

            memo[i][j] = if (j == 0) {
                maxOf(
                    -prices[i] + f(i + 1, 1),
                    0 + f(i + 1, 0)
                )
            } else {
                maxOf(
                    prices[i] - fee + f(i + 1, 0),
                    0 + f(i + 1, 1)
                )

            }

            return memo[i][j]
        }

//        return f(0, 0)

        //tabulation
        val dp = Array(n + 1) { IntArray(2) }
        for (i in n downTo 0) {
            for (j in 1 downTo 0) {
                if (i == n) {
                    dp[i][j] = 0
                } else {
                    dp[i][j] = if (j == 0) {
                        maxOf(
                            -prices[i] + dp[i + 1][1],
                            0 + dp[i + 1][0]
                        )
                    } else {
                        maxOf(
                            prices[i] - fee + dp[i + 1][0],
                            0 + dp[i + 1][1]
                        )
                    }
                }
            }
        }

//        return dp[0][0]


        ///space optimized
        var prev: IntArray? = null
        val cur: IntArray = IntArray(2)
        for (i in n downTo 0) {
            for (j in 1 downTo 0) {
                if (i == n) {
                    cur[j] = 0
                } else {
                    cur[j] = if (j == 0) {
                        maxOf(
                            -prices[i] + prev!![1],
                            0 + prev!![0]
                        )
                    } else {
                        maxOf(
                            prices[i] - fee + prev!![0],
                            0 + prev!![1]
                        )
                    }
                }
            }
            prev = cur
        }

        return cur[0]
    }


}


fun main() {
    val obj = `Stock-Dp`()
    println(obj.bestTimeToBuyAndSellStock(intArrayOf(7, 1, 5, 3, 6, 4))) // 5
    println(obj.bestTimeToBuyAndSellStock(intArrayOf(1, 2, 3, 4, 5, 6, 7))) // 0
    println(obj.maxProfitWithFee(intArrayOf(1, 3, 2, 8, 4, 9), 2)) // 8
}