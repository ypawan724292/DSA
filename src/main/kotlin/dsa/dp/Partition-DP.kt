package dsa.dp

import java.util.*

//Whenever the order of solving the problem comes into play, we can think in terms of Partition DP.
//Example Matrix Chain Multiplication, Burst Balloons, Palindrome Partitioning, Rod Cutting, etc.

class `Partition-DP` {

    /**
     * Matrix Chain multiplication
     * Given a sequence of matrices, find the most efficient way to multiply these matrices together.
     *
     * Example 1:  arr = [10, 20, 30, 40, 30]
     * Output: 30000
     *
     */
    fun efficientMatrixChainMultiplication(arr: IntArray): Int {

        /*
        Intuition:
         1. To mutliply two matrices let says [pxq] and [rxs] if only if q==r
         2. Cost of multiplication of two matrices is p*q*s
         3. Given array of matrices, we need to find the most efficient way to multiply these matrices together.
         4. arr = [10, 20, 30, 40, 30] meaning A1=10x20, A2=20x30, A3=30x40, A4=40x30
         5  f(i,j)  represents the minimum cost of multiplying matrices from i to j Ai.Ai+1.Ai+2...Aj
         6. we will partition the matrices into two parts by iterating k from i to j-1, (A1.A2...Ak) and (Ak+1...Aj)
         7. Then, f(i,j) = min(f(i,k) + f(k+1,j) + arr[i-1]*arr[k]*arr[j])
          {p*q*s operations for multuply left partition matrix wiht right partition matrix} for i<=k<=j-1
         8. Base case f(i,j) = 0 bcuz we can't multiply single matrix
         */
        val n = arr.size

        // recursion and memoization
        val memo = Array(n) { IntArray(n) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i == j) return 0

            if (memo[i][j] != -1) return memo[i][j]
            var min = Int.MAX_VALUE
            for (k in i..j - 1) {
                val steps = f(i, k) + f(k + 1, j) + arr[i - 1] * arr[k] * arr[j]
                min = minOf(min, steps)
            }

            memo[i][j] = min
            return min
        }

//        return f(1, n - 1)

        //iteration and tabulation

        val dp = Array(n) { IntArray(n) { 0 } }
        // Length of the chain

        for (length in 2 until n) { // Start from length 2 because length 1 is the base case
            for (i in 1 until n - length + 1) {
                val j = i + length - 1
                dp[i][j] = Int.MAX_VALUE
                for (k in i until j) {
                    val cost = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j]
                    dp[i][j] = minOf(dp[i][j], cost)
                }
            }
        }

        /*
            Dry run:
            arr = [10, 20, 30, 40, 30]

            length = 2
            i = 1, j = 2
            k = 1
            dp[1][2] = min(dp[1][1] + dp[2][2] + 10*20*30, dp[1][2])

            i = 2, j = 3
            k = 2
            dp[2][3] = min(dp[2][2] + dp[3][3] + 20*30*40, dp[2][3])

            i = 3, j = 4
            k = 3
            dp[3][4] = min(dp[3][3] + dp[4][4] + 30*40*30, dp[3][4])


            length = 3
            i = 1, j = 3
            k = 1
            dp[1][3] = min(dp[1][1] + dp[2][3] + 10*20*30, dp[1][3])
            k = 2
            dp[1][3] = min(dp[1][2] + dp[3][3] + 10*30*40, dp[1][3])

            i = 2, j = 4
            k = 2
            dp[2][4] = min(dp[2][2] + dp[3][4] + 20*30*40, dp[2][4])
            k = 3
            dp[2][4] = min(dp[2][3] + dp[4][4] + 20*40*30, dp[2][4])



            length = 4
            i = 1, j = 4
            k = 1
            dp[1][4] = min(dp[1][1] + dp[2][4] + 10*20*40, dp[1][4])

            k = 2
            dp[1][4] = min(dp[1][2] + dp[3][4] + 10*30*30, dp[1][4])

            k = 3
            dp[1][4] = min(dp[1][3] + dp[4][4] + 10*40*30, dp[1][4])


           dp[1][4] = 30000



         */

        return dp[1][n - 1]
    }


    /**
     * Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:
     *
     *
     * Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
     *
     * You should perform the cuts in order, you can change the order of the cuts as you wish.
     *
     * The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts.
     * When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut).
     *
     * Please refer to the first example for a better explanation.
     *
     *
     * Return the minimum total cost of the cuts.
     *
     * Example 1:
     * cuts = [1, 3, 4, 5] , n = 7
     * Output: 16
     *
     *
     *
     */
    fun minCostCuttingRod(n: Int, cuts: IntArray): Int {/*
            Intuition:
            1. We need to cut the stick at the given positions in the array cuts.
            2. The cost of cutting the stick at position i is  length of the stick to be cut.
            3 We need to find the minimum total cost of the cuts.
         */
        val m = cuts.size

        // add 0 and n to the cuts
        val arr = IntArray(m + 2)
        arr[0] = 0
        arr[m + 1] = n
        for (i in 1..m) {
            arr[i] = cuts[i - 1]
        }

        arr.sort()

        //recursive and memoization
        val memo = Array(m + 1) { IntArray(m + 1) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i > j) return 0

            if (memo[i][j] != -1) return memo[i][j]
            var min = Int.MAX_VALUE
            for (k in i..j) {
                val cut = arr[j + 1] - arr[i - 1] + f(i, k - 1) + f(k + 1, j)
                min = minOf(min, cut)
            }
            memo[i][j] = min
            return min
        }

//        return f(1, m)

        //iteration and tabulation
        val dp = Array(m + 2) { IntArray(m + 2) { 0 } }
        for (i in m downTo 1) {
            for (j in 1..m) {
                if (i > j) continue
                var min = Int.MAX_VALUE
                for (k in i..j) {
                    val cut = arr[j + 1] - arr[i - 1] + dp[i][k - 1] + dp[k + 1][j]
                    min = minOf(min, cut)
                }
                dp[i][j] = min
            }
        }

        return dp[1][m]

        // check if we can do using Priority Queue

    }

    /**
     * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums.
     * You are asked to burst all the balloons.
     *
     * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
     * If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
     *
     * Return the maximum coins you can collect by bursting the balloons wisely.
     *
     *
     * It is Brust Balloons
     *
     * Example 1:
     *
     * Input: nums = [3,1,5,8]
     * Output: 167
     * Explanation:
     * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
     * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
     * Example 2:
     *
     * Input: nums = [1,5]
     * Output: 10
     */
    fun maxCoinsWhileBrust(nums: IntArray): Int {

        /*
        Intuition:
        This problem is a variation of matrix chain multiplication.
        need to start solving by thinking last balloon to burst first and then last second and so on.

        At every step, we will find the coins by arr[i-1]* arr[k]* arr[j+1]
        Example :  b1,b2,b3,b4

        Here we try to find the coins by bursting the last balloon to burst first and update the coins by bursting the
         last second balloon to burst and so on.
        Like in mentioned below

                        ...Soon on




                 b1,b2,b3  b1,b3,b4    b1,b2,b3   b2,b3,b4     b1,b3,b4   b2,b3,b4
                        \   /           \          /             \     /
                              b1,b3       b2,b3           b3,b4
                                \          |          /
                                          b3
         */
        val n = nums.size
        val arr = IntArray(n + 2)
        arr[0] = 1
        arr[n + 1] = 1
        for (i in 1..n) {
            arr[i] = nums[i - 1]
        }

        //recursive and memoization
        val memo = Array(n + 2) { IntArray(n + 2) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i > j) return 0

            if (memo[i][j] != -1) return memo[i][j]
            var max = Int.MIN_VALUE
            for (k in i..j) {
                val coins = arr[i - 1] * arr[k] * arr[j + 1] + f(i, k - 1) + f(k + 1, j)
                max = maxOf(max, coins)
            }
            memo[i][j] = max
            return max
        }

//        return f(1, n)

        //iteration and tabulation
        val dp = Array(n + 2) { IntArray(n + 2) { 0 } }
        for (i in n downTo 1) {
            for (j in 1..n) {
                var max = Int.MIN_VALUE
                for (k in i..j) {
                    val coins = arr[i - 1] * arr[k] * arr[j + 1] + dp[i][k - 1] + dp[k + 1][j]
                    max = maxOf(max, coins)
                }
                dp[i][j] = max
            }
        }
        return dp[1][n]

    }

    /**
     * Evaluate Boolean Expression to True | Partition DP: DP 52
     *
     *
     * Problem Statement: Given an expression, A, with operands and operators (OR, AND, XOR),
     * in how many ways can you evaluate the expression to be true, by grouping it in different ways?
     *
     * Operands are only true and false.
     *
     * Return the number of ways to evaluate the expression modulo 103 + 3.
     *
     * Example 1:
     * Input: A = "T|F"
     * Output: 1
     *
     * Example 2:
     * Input: A = "T^F&T"
     * Output: 2
     */
    fun evaluateBooleanExpression(str: String): Int {

        val n = str.length

        val memo = Array(n) { Array(n) { Array(2) { -1 } } }

        //recursive and memoization
        fun f(i: Int, j: Int, isTrue: Int): Int {
            if (i > j) return 0
            if (i == j) return if (isTrue == 1) if (str[i] == 'T') 1 else 0
            else if (str[i] == 'F') 1 else 0

            if (memo[i][j][isTrue] != -1) return memo[i][j][isTrue]

            var maxi = 0
            for (k in i + 1..j - 1 step 2) {
                val leftT = f(i, k - 1, 1)
                val rightT = f(k + 1, j, 1)


                val leftF = f(i, k - 1, 0)
                val rightF = f(k + 1, j, 0)

                maxi += when (str[k]) {
                    '&' -> {
                        if (isTrue == 1) leftT * rightT
                        else leftT * rightF + leftF * rightT + leftF * rightF
                    }

                    '|' -> {
                        if (isTrue == 1) leftT * rightT + leftT * rightF + leftF * rightT
                        else leftF * rightF
                    }

                    '^' -> {
                        if (isTrue == 1) leftT * rightF + leftF * rightT
                        else leftT * rightT + leftF * rightF
                    }

                    else -> {
                        0
                    }
                }
            }
            memo[i][j][isTrue] = maxi
            return maxi
        }


//        return f(0, n - 1, 1)

        // iteration and tabulation
        val dp = Array(n) { Array(n) { Array(2) { 0 } } }

        for (i in n - 1 downTo 0 step 2) {
            for (j in 0 until n step 2) {
                if (i > j) continue
                if (i == j) {
                    dp[i][j][0] = if (str[i] == 'F') 1 else 0
                    dp[i][j][1] = if (str[i] == 'T') 1 else 0
                } else {
                    for (k in i + 1 until j step 2) {
                        val leftT = dp[i][k - 1][1]
                        val rightT = dp[k + 1][j][1]
                        val leftF = dp[i][k - 1][0]
                        val rightF = dp[k + 1][j][0]

                        dp[i][j][1] += when (str[k]) {
                            '&' -> {
                                leftT * rightT
                            }

                            '|' -> {
                                leftT * rightT + leftT * rightF + leftF * rightT
                            }

                            '^' -> {
                                leftT * rightF + leftF * rightT
                            }

                            else -> 0
                        }
                        dp[i][j][0] += when (str[k]) {
                            '&' -> {
                                leftT * rightF + leftF * rightT + leftF * rightF
                            }

                            '|' -> {
                                leftF * rightF
                            }

                            '^' -> {
                                leftT * rightT + leftF * rightF
                            }

                            else -> 0
                        }
                    }
                }
            }
        }

        return dp[0][n - 1][1]
    }


    /**
     * Given a string s, partition s such that every
     * substring
     *  of the partition is a
     * palindrome
     * .
     *
     * Return the minimum cuts needed for a palindrome partitioning of s.
     *
     *
     * Example 1:
     * Input:
     *  s = “bababcbadcede”
     * Output : 4
     * Explanation:
     *  If we do 4 partitions in the following way,
     * each substring of the partition will be a palindrome.
     * bab | abcba | d | c | ede
     *
     *
     */

    fun minCutForPalindrone(s: String): Int {
        val n = s.length

        fun isPalindrome(i: Int, j: Int): Boolean {
            var left = i
            var right = j
            while (left < right) {
                if (s[left] != s[right]) return false
                left++
                right--
            }
            return true
        }


        //recursive and memoization
        val memo = IntArray(n) { -1 }
        fun f(i: Int): Int {
            if (i == n) return 0

            if (memo[i] != -1) return memo[i]
            var min = Int.MAX_VALUE
            for (j in i until n) {
                if (isPalindrome(i, j)) {
                    min = minOf(min, 1 + f(j + 1))
                }
            }
            memo[i] = min
            return min
        }

//        return f(0) -1
        //iteration and tabulation
        val dp = IntArray(n) { 0 }
        for (i in n - 1 downTo 0) {
            var min = Int.MAX_VALUE
            for (j in i until n) {
                if (isPalindrome(i, j)) {
                    min = minOf(min, 1 + dp[j + 1])
                }
            }
            dp[i] = min
        }
        return dp[0] - 1 // -1 because we need to return the number of cuts not the number of partitions
    }

    /**
     * Partition Array for Maximum Sum | Front Partition : DP 54
     *
     *
     * Problem Statement: Given an integer array arr, partition the array into (contiguous) subarrays of length at most k.
     * After partitioning, each subarray has its values changed to become the maximum value of that subarray.
     *
     * Return the largest sum of the given array after partitioning.
     *
     * Example 1:
     * Input:
     *  arr = [1,15,7,9,2,5,10], k = 3
     * Output
     * : 84
     * Explanation:
     *  The partition will be the following to get the largest sum:
     *  [1, 15, 7 | 9 | 2, 5, 10].
     * After replacing the elements of each subarray with its maximum,
     *
     * the array will look like this:[15,15,15,9,10,10,10] and the sum will be 84.
     */
    fun partitionArrayWithMaxSumAfterReplace(arr: IntArray, k: Int): Int {
        val n = arr.size

        //recursive and memoization
        val memo = IntArray(n) { -1 }
        fun f(i: Int): Int {
            if (i == n) return 0

            if (memo[i] != -1) return memo[i]

            var maxi = Int.MIN_VALUE
            var maxVal = 0

            for (j in i until i + k - 1) {
                if (j >= n) break

                maxVal = maxOf(maxVal, arr[j])
                val sum = maxVal * (j - i + 1)
                maxi = maxOf(maxi, sum + f(j + 1))
            }
            memo[i] = maxi
            return maxi
        }

//        return f(0)

        // tabulation and iteration
        val dp = IntArray(n) { 0 }
        for (i in n - 1 downTo 0) {
            var maxi = Int.MIN_VALUE
            var maxVal = 0
            for (j in i until i + k - 1) {
                if (j >= n) break
                maxVal = maxOf(maxVal, arr[j])
                val sum = maxVal * (j - i + 1)
                maxi = maxOf(maxi, sum + dp[j + 1])
            }
            dp[i] = maxi
        }
        return dp[0]
    }

}

fun main() {
    val obj = `Partition-DP`()
    println(obj.efficientMatrixChainMultiplication(intArrayOf(10, 20, 30, 40, 30))) //30000

}


