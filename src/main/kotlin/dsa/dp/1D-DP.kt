package dsa.dp

import annotations.Important

class `1-DP` {

    /**
     *  What is Dyanmic Programming?
     *  - Dynamic Programming is a method for solving a complex problem by breaking it down into a collection of simpler subproblems,
     *  solving each of those subproblems just once, and storing their solutions.
     *
     *  It involves two steps:
     *  1. Memoization: Store the results of expensive function calls and return the cached result when the same inputs occur again.
     *  2. Tabulation: Store the results of subproblems in a table and use those results to build up to the solution of the main problem.
     *
     *
     *  When to use Dynamic Programming?
     *  - Overlapping Subproblems: A problem is said to have overlapping subproblems
     *  if it can be broken down into subproblems which are reused several times.
     *
     *
     *  - Optimal Substructure: A problem is said to have optimal substructure
     *  if an optimal solution can be constructed from optimal solutions of its subproblems.
     *
     *
     */

    fun fib(n: Int): Int {

        // recursive {top-down} and memoization
        val memo = IntArray(n + 1) { -1 }
        fun f(i: Int): Int {
            if (i == 0) return 0
            if (i == 1) return 1
            if (memo[i] != -1) return memo[i]
            memo[i] = f(i - 1) + f(i - 2)
            return memo[i]
        }

        // return f(n)

        //Iterative {bottom-up} and tabulation
        val dp = IntArray(n + 1)
        for (i in 0..n) {
            when (i) {
                0 -> dp[i] = 0
                1 -> dp[i] = 1
                else -> dp[i] = dp[i - 1] + dp[i - 2]
            }
        }

        // return dp[n]

        //space optimized
        var a = 0
        var b = 1
        for (i in 2..n) {
            val temp = a + b
            a = b
            b = temp
        }

        return b
    }

    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     *
     *
     * Example 1:
     *
     * Input: n = 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * Example 2:
     *
     * Input: n = 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     *
     *
     * Constraints:
     *
     *
     */
    fun climbStairs(n: Int): Int {

        //recursive {top-down} and memoization
        val memo = IntArray(n + 1) { -1 }
        fun f(i: Int): Int {
            if (i == 0) return 1
            if (i == 1) return 1
            if (memo[i] != -1) return memo[i]
            memo[i] = f(i - 1) + f(i - 2)
            return memo[i]
        }

        // return f(n)

        // iterative {bottom-up} and tabulation
        val dp = IntArray(n + 1)
        for (i in 0..n) {
            when (i) {
                0, 1 -> dp[i] = 1
                else -> dp[i] = dp[i - 1] + dp[i - 2]
            }
        }

        // return dp[n]

        //space optimized
        var a = 1
        var b = 1
        for (i in 2..n) {
            val temp = a + b
            a = b
            b = temp
        }

        return b
    }

    /**
     * You are given an array of integers cost where cost[i] is the cost of taking a step from the ith floor of a staircase.
     * After paying the cost, you can step to either the (i + 1)th floor or the (i + 2)th floor.
     *
     * You may choose to start at the index 0 or the index 1 floor.
     *
     * Return the minimum cost to reach the top of the staircase, i.e. just past the last index in cost.
     *
     * Example 1:
     *
     * Input: cost = [1,2,3]
     *
     * Output: 2
     * Explanation: We can start at index = 1 and pay the cost of cost[1] = 2 and take two steps to reach the top. The total cost is 2.
     *
     *
     */
    fun minCostClimbingStairs(cost: IntArray): Int {
        //recursive {top-down} and memoization

        /*
        The cost is considered even if you are already at the step because the problem statement specifies that
         there is a cost associated with standing on each step.
         This cost must be paid regardless of whether you start from that step or move to it from another step.
                Example
                Consider the cost array: [10, 15, 20].
                Step 0: The cost to stand on step 0 is cost[0] = 10.
                Step 1: The cost to stand on step 1 is cost[1] = 15.
                Even if you start at step 0 or step 1, you still incur the respective costs. This is why dp[0] is initialized to cost[0] and dp[1] is initialized to cost[1].
         */
        // TC : O(2^n)
        val memo = IntArray(cost.size) { -1 }
        fun f(i: Int): Int {
            if (i == 0) return cost[0]
            if (i == 1) return cost[1]
            if (memo[i] != -1) return memo[i]
            memo[i] = cost[i] + minOf(f(i - 1), f(i - 2))
            return memo[i]
        }

//        return minOf(f(cost.size - 1), f(cost.size - 2))

        // iterative {bottom-up} and tabulation
        val dp = IntArray(cost.size)
        for (i in 0 until cost.size) {
            when (i) {
                0, 1 -> dp[i] = cost[i]
                else -> dp[i] = cost[i] + minOf(dp[i - 1], dp[i - 2])
            }
        }

//        return minOf(dp[cost.size-1], dp[cost.size-2])

        //space optimized
        var a = cost[0]
        var b = cost[1]
        for (i in 2 until cost.size) {
            val temp = cost[i] + minOf(a, b)
            a = b
            b = temp
        }
        return minOf(a, b)
    }


    /**
     * Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the (N-1)th stair.
     * At a time the frog can climb either one or two steps. A height[N] array is also given.
     * Whenever the frog jumps from a stair i to stair j, the energy consumed in the jump is abs(height[i]- height[j]),
     * where abs() means the absolute difference.
     * We need to return the minimum energy that can be used by the frog to jump from stair 0 to stair N-1.
     */
    @Important
    fun minEnergy(height: IntArray): Int {
        val n = height.size
        // recursive {top-down} and memoization
        // TC : O(2^n)
        val memo = IntArray(n) { -1 }
        fun f(i: Int): Int {
            if (i == 0) return 0 //because the frog does not jump to the first stone; it starts there.
            if (i == 1) return Math.abs(height[1] - height[0]) // The frog can only arrive at the second stone (index 1)
            // by a single jump from the first stone. Thus, no additional options need to be considered.

            if (memo[i] != -1) return memo[i]
            val jumpOne = Math.abs(height[i] - height[i - 1]) + f(i - 1)
            val jumpTwo = Math.abs(height[i] - height[i - 2]) + f(i - 2)
            memo[i] = minOf(jumpOne, jumpTwo)
            return memo[i]
        }
//         return f(n-1)

        // iterative {bottom-up} and tabulation
        val dp = IntArray(n)
        for (i in 0..n - 1) {
            when (i) {
                0 -> dp[i] = 0
                1 -> dp[i] = Math.abs(height[1] - height[0])
                else -> {
                    val jumpOne = Math.abs(height[i] - height[i - 1]) + dp[i - 1]
                    val jumpTwo = Math.abs(height[i] - height[i - 2]) + dp[i - 2]
                    dp[i] = minOf(jumpOne, jumpTwo)
                }
            }
        }
//        return dp[n - 1]

        //space optimized
        var a = 0
        var b = Math.abs(height[1] - height[0])
        for (i in 2 until n) {
            val jumpOne = Math.abs(height[i] - height[i - 1]) + b
            val jumpTwo = Math.abs(height[i] - height[i - 2]) + a
            val cur = minOf(jumpOne, jumpTwo)
            a = b
            b = cur
        }

        return b
    }

    /**
     * Problem Statement:
     *
     * This is a follow-up question to “Frog Jump” discussed in the previous article.
     * In the previous question, the frog was allowed to jump either one or two steps at a time.
     * In this question, the frog is allowed to jump up to ‘K’ steps at a time.
     * If K=4, the frog can jump 1,2,3, or 4 steps at every index.
     */
    fun minEnergy(height: IntArray, k: Int): Int {
        val n = height.size
        // recursive {top-down} and memoization
        // TC : O(k^n)
        val memo = IntArray(n) { -1 }
        fun f(i: Int): Int {
            if (i == 0) return 0
            if (i == 1) return Math.abs(height[1] - height[0])
            if (memo[i] != -1) return memo[i]
            var curMax = Int.MAX_VALUE
            for (j in 1..k) {
                if (j <= i) {
                    val jump = Math.abs(height[i] - height[i - j]) + f(i - j)
                    curMax = minOf(curMax, jump)
                }
            }
            memo[i] = curMax
            return memo[i]
        }
//        return f(n - 1)

        // iterative {bottom-up} and tabulation
        // TC : O(n*k)
        val dp = IntArray(n)
        for (i in 0 until n) {
            when (i) {
                0 -> dp[i] = 0
                1 -> dp[i] = Math.abs(height[1] - height[0])
                else -> {
                    var curMax = Int.MAX_VALUE
                    for (j in 1..k) {
                        if (j <= i) {
                            val jump = Math.abs(height[i] - height[i - j]) + dp[i - j]
                            curMax = minOf(curMax, jump)
                        }
                    }
                    dp[i] = curMax
                }
            }
        }

        return dp[n - 1]

        //space optimized is not possible because we need to keep track of all the previous k values
        // which is not possible in constant space
    }


    /**
     * Given an array of ‘N’  positive integers, we need to return the maximum sum of the subsequence such that no
     * two elements of the subsequence are adjacent elements in the array.
     *
     * Note: A subsequence of an array is a list with elements of the array where some elements are deleted
     * ( or not deleted at all) and the elements should be in the same order in the subsequence as in the array.
     *
     */
    fun maxSumNoAdjacent(arr: IntArray): Int {
        val n = arr.size
        // recursive {top-down} and memoization
        // TC : O(2^n)
        val memo = IntArray(n) { -1 }
        fun f(i: Int): Int {
            if (i == 0) return arr[0]
            if (i == 1) return maxOf(arr[0], arr[1])

            if (memo[i] != -1) return memo[i]

            val take = arr[i] + f(i - 2)
            val notTake = 0 + f(i - 1)

            memo[i] = maxOf(take, notTake)
            return memo[i]
        }

//        return f(n-1)

        // iterative {bottom-up} and tabulation
        val dp = IntArray(n)
        for (i in 0 until n) {
            when (i) {
                0 -> dp[i] = arr[0]
                1 -> dp[i] = maxOf(arr[0], arr[1])
                else -> {
                    val take = arr[i] + dp[i - 2]
                    val notTake = 0 + dp[i - 1]
                    dp[i] = maxOf(take, notTake)
                }
            }
        }

//        return dp[n-1]

        //space optimized
        // TC : O(n)
        var a = arr[0]
        var b = maxOf(arr[0], arr[1])
        for (i in 2 until n) {
            val take = arr[i] + a
            val notTake = b
            val cur = maxOf(take, notTake)
            a = b
            b = cur
        }

        return b
    }

    /**
     * A thief needs to rob money in a street. The houses in the street are arranged in a circular manner.
     * Therefore the first and the last house are adjacent to each other.
     * The security system in the street is such that if adjacent houses are robbed, the police will get notified.
     *
     * Given an array of integers “Arr'' which represents money at each house,
     * we need to return the maximum amount of money that the thief can rob without alerting the police.
     *
     */
    fun maxMoneyRobbed(arr: IntArray): Int {
        //TC : O(n)
        val n = arr.size
        val arr1 = IntArray(n - 1)
        val arr2 = IntArray(n - 1)

        for (i in 0 until n) {
            if (i != n - 1) arr1[i] = arr[i]
            if (i != 0) arr2[i] = arr[i]
        }

        return maxOf(maxSumNoAdjacent(arr1), maxSumNoAdjacent(arr2))
    }


    /**
     * You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
     *
     * "1" -> 'A'
     *
     * "2" -> 'B'
     *
     * ...
     *
     * "25" -> 'Y'
     *
     * "26" -> 'Z'
     *
     * However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
     *
     * For example, "11106" can be decoded into:
     *
     * "AAJF" with the grouping (1, 1, 10, 6)
     * "KJF" with the grouping (11, 10, 6)
     * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
     * Note: there may be strings that are impossible to decode.
     *
     * Given a string s containing only digits, return the number of ways to decode it.
     * If the entire string cannot be decoded in any valid way, return 0.
     *
     * The test cases are generated so that the answer fits in a 32-bit integer.
     *
     *
     */
    fun decodeWays(str: String): Int {
        val n = str.length

        // recursive {top-down} and memoization
        val memo = IntArray(n + 1) { -1 }
        fun f(i: Int): Int {
            if (i == n) return 1
            if (str[i] == '0') return 0 // if the current character is '0', then it cannot be decoded

            if (memo[i] != -1) return memo[i]


            var ways = f(i + 1)

            if (i < n - 1 && str.substring(i, i + 2).toInt() in 0..26) {
                ways += f(i + 2)
            }

            memo[i] = ways
            return ways
        }

        return f(0)
    }

}


fun main() {
    val dp = `1-DP`()
//    println(dp.fib(5))
//    println(dp.climbStairs(5))
//    println(dp.minCostClimbingStairs(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1)))
//    println(dp.minEnergy(intArrayOf(10, 50, 10)))
//    println(dp.minEnergy(intArrayOf(10, 40, 30, 10), 2))
//    println(dp.maxSumNoAdjacent(intArrayOf(2,1,4,9)))
    println(dp.maxMoneyRobbed(intArrayOf(2, 3, 2)))
}