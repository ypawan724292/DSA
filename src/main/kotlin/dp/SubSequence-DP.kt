package dp

import annotations.Important

class `SubSequence-DP` {

    /**
     * We are given an array ‘ARR’ with N positive integers.
     * We need to find if there is a subset in “ARR” with a sum equal to K. If there is, return true else return false.
     */
    fun isSubsetSum(arr: IntArray, sum: Int): Boolean {
        val n = arr.size

        // recursive {top- down} and memoization
        fun f(i: Int, k: Int): Boolean {
            if (k == 0) return true
            if (i == 0) return arr[i] == k

            val notake = f(i - 1, k)
            var take = false
            if (arr[i] <= k)
                take = f(i - 1, k - arr[i])

            return notake || take
        }

//        return f(n - 1, sum)

        // tabulation {bottom-up}
        val dp = Array(n) { BooleanArray(sum + 1) }
        for (i in 0 until n) {
            for (j in 0..sum) {
                if (j == 0) {
                    dp[i][j] = true
                } else if (i == 0) {
                    dp[i][j] = arr[i] == j
                } else {
                    val notake = dp[i - 1][j]
                    var take = false
                    if (arr[i] <= j)
                        take = dp[i - 1][j - arr[i]]
                    dp[i][j] = notake || take
                }
            }
        }

//        return dp[n - 1][sum]

        //space optimized
        var prev: BooleanArray? = null
        for (i in 0 until n) {
            val cur = BooleanArray(sum + 1)
            for (j in 0..sum) {
                if (j == 0) {
                    cur[j] = true
                } else if (i == 0) {
                    cur[j] = arr[i] == j
                } else {
                    val notake = prev!![j]
                    var take = false
                    if (arr[i] <= j)
                        take = prev[j - arr[i]]
                    cur[j] = notake || take
                }
            }
            prev = cur
        }
        return prev!![sum]
    }


    /**
     * We are given an array ‘ARR’ with N positive integers.
     * We need to find if we can partition the array into two subsets such that the sum of elements of each subset is equal to the other.
     *
     * If we can partition, return true else return false.
     */
    @Important
    fun canPartition(arr: IntArray): Boolean {
        val n = arr.size
        val sum = arr.sum()

        if (sum % 2 != 0) return false

        // recursive {top- down} and memoization
        fun f(i: Int, k: Int): Boolean {
            if (k == 0) return true
            if (i == 0) return arr[i] == k

            val notake = f(i - 1, k)
            var take = false
            if (arr[i] <= k)
                take = f(i - 1, k - arr[i])

            return notake || take
        }

//        return f(n - 1, sum / 2)

        // tabulation {bottom-up}
        val dp = Array(n) { BooleanArray(sum / 2 + 1) }
        for (i in 0 until n) {
            for (j in 0..sum / 2) {
                if (j == 0) {
                    dp[i][j] = true
                } else if (i == 0) {
                    dp[i][j] = arr[i] == j
                } else {
                    val notake = dp[i - 1][j]
                    var take = false
                    if (arr[i] <= j)
                        take = dp[i - 1][j - arr[i]]
                    dp[i][j] = notake || take
                }
            }
        }

        //space optimized
        var prev = BooleanArray(sum / 2 + 1)
        for (i in 0 until n) {
            val cur = BooleanArray(sum / 2 + 1)
            for (j in 0..sum / 2) {
                if (j == 0) {
                    cur[j] = true
                } else if (i == 0) {
                    cur[j] = arr[i] == j
                } else {
                    val notake = prev[j]
                    var take = false
                    if (arr[i] <= j)
                        take = prev[j - arr[i]]
                    cur[j] = notake || take
                }
            }
            prev = cur
        }

        return prev!![sum / 2]

    }


    /**
     * We are given an array ‘ARR’ with N positive integers.
     * We need to partition the array into two subsets such that the absolute difference of the sum of elements of the subsets is minimum.
     *
     * We need to return only the minimum absolute difference of the sum of elements of the two partitions.
     *
     */
    @Important
    fun minAbsDiff(arr: IntArray): Int {
        /* Intution:

         3. in the count subset with sum target we check f(n-1, target) meaning we are finding if arr-> n-1 to 0,
         and target is possible or not
         4. we do tabulation for above dp[n-1][target]
         Like n = 3, target = 10

         tabulated dp look like
              n/t  0  1  2  3  4  5  6  7  8  9 10
               0  T   F  F  F  F  F  F  F  F  F  F
               1  T   F  F  F  T  F  F  F  F  F  F
               2  T   T  F  F  F  F  T  F  F  F  F

         when we check if for arr of 3 target 10 is possible or not, we fill other possible target like
                dp[2][t->0...10] not alone  dp[2][10]


          which is our building block for this problem

          1. assume s1 and s2 are sum of two subsets
          2. we need minOf of (abs|s1-s2|) possible , or minOf abs|sum - 2*s1| possible,
          3. Hence we need all subsets of total
          4. for every subset s1 we can find s2 = sum - s1 and store minOf(abs|sum - 2*s1|) possible


//           if -ve integer is allowed then we can have sum = 0, and s1 = 0, s2 = 0, hence abs|sum - 2*s1| = 0
         */
        val n = arr.size
        val sum = arr.sum()
        // recursive {top- down} and memoization
        val memo = Array(n) { BooleanArray(sum + 1) }
        fun f(i: Int, k: Int): Boolean {
            if (k == 0) return true
            if (i == 0) return arr[i] == k

            if (memo[i][k]) return true
            val noTake = f(i - 1, k)
            var take = false
            if (arr[i] <= k) {
                take = f(i - 1, k - arr[i])
            }

            memo[i][k] = noTake || take
            return memo[i][k]
        }


        // tabulation {bottom-up}
        val dp = Array(n) { BooleanArray(sum + 1) }
        for (i in 0..n - 1) {
            for (k in 0..sum) {
                if (k == 0) {
                    dp[i][k] = true
                } else if (i == 0) {
                    dp[i][k] = arr[i] == k
                } else {
                    val noTake = dp[i - 1][k]
                    var take = false
                    if (arr[i] <= k) {
                        take = dp[i - 1][k - arr[i]]
                    }
                    dp[i][k] = noTake || take
                }
            }
        }


        //space optimized
        var prev: BooleanArray? = null

        for (i in 0..n - 1) {
            val cur = BooleanArray(sum + 1)
            for (k in 0..sum) {
                if (k == 0) {
                    cur[k] = true
                } else if (i == 0) {
                    cur[k] = arr[i] == k
                } else {
                    val noTake = prev!![k]
                    var take = false
                    if (arr[i] <= k) {
                        take = prev[k - arr[i]]
                    }
                    cur[k] = noTake || take
                }
            }

            prev = cur
        }


        var res = Int.MAX_VALUE
        for (i in 0..sum / 2) { // sum/2 bcuz the from sum/2 to sum yields same result
            if (prev!![i]) {
                res = minOf(res, Math.abs(sum - 2 * i))
            }
        }

        return res
    }

    /**
     * We are given an array ‘ARR’ with N positive integers and an integer K.
     * We need to find the number of subsets whose sum is equal to K.
     */
    fun countSubsetWithSum(arr: IntArray, k: Int): Int {

        //Here elements do not include 0,
        // if 0 is included answer will be different
        val n = arr.size
        // recursive {top- down} and memoization
        // f(i,k) denotes numbers of subsets with sum k with elements from n-1 to i
        // TC : O(2^n)
        val mem0 = Array(n) { IntArray(k + 1) }
        fun f(i: Int, sum: Int): Int {
            if (sum == 0) return 1
            if (i == 0) return if (arr[i] == sum) 1 else 0

            val notake = f(i - 1, sum)
            val take = 0
            if (arr[i] <= sum)
                f(i - 1, sum - arr[i])

            return take + notake
        }

        /**
         * this recursion includes zero in subset
         */
        fun g(i: Int, sum: Int): Int {
            if (i == 0) {
                if (sum == 0 && arr[0] == 0) return 2
                if (sum == 0 || arr[0] == sum) return 1
            }
            val notake = f(i - 1, sum)
            val take = 0
            if (arr[i] <= sum)
                f(i - 1, sum - arr[i])

            return take + notake
        }

//        return f(n-1, k)

        // tabulation {bottom-up}
        val dp = Array(n) { IntArray(k + 1) }
        for (i in 0 until n) {
            for (j in 0..k) {
                if (j == 0) {
                    dp[i][j] = 1
                } else if (i == 0) {
                    dp[i][j] = if (arr[i] == j) 1 else 0
                } else {
                    val notake = dp[i - 1][j]
                    var take = 0
                    if (arr[i] <= j)
                        take = dp[i - 1][j - arr[i]]
                    dp[i][j] = take + notake
                }
            }
        }


        //space optimized
        var prev: IntArray? = null
        for (i in 0 until n) {
            val cur = IntArray(k + 1)
            for (j in 0..k) {
                if (j == 0) {
                    cur[j] = 1
                } else if (i == 0) {
                    cur[j] = if (arr[i] == j) 1 else 0
                } else {
                    val notake = prev!![j]
                    var take = 0
                    if (arr[i] <= j)
                        take = prev[j - arr[i]]
                    cur[j] = take + notake
                }
            }
            prev = cur
        }

        return prev!![k]
    }

    /**
     * We are given an array ‘ARR’ with N positive integers and an integer D.
     *  We need to count the number of ways we can partition the given array into two subsets,
     *  S1 and S2 such that S1 - S2 = D and S1 is always greater than or equal to S2.
     */
    fun countPartitionWithDiff(arr: IntArray, diff: Int): Int {
        val n = arr.size
        val sum = arr.sum()
        val target = (sum + diff) / 2

        // recursive {top- down} and memoization
        val memo = Array(n) { IntArray(target + 1) }
        fun f(i: Int, k: Int): Int {
            if (k == 0) return 1
            if (i == 0) return if (arr[i] == k) 1 else 0

            if (memo[i][k] != 0) return memo[i][k]
            val noTake = f(i - 1, k)
            var take = 0
            if (arr[i] <= k) {
                take = f(i - 1, k - arr[i])
            }

            memo[i][k] = take + noTake
            return memo[i][k]
        }

        // tabulation {bottom-up}
        val dp = Array(n) { IntArray(target + 1) }
        for (i in 0 until n) {
            for (j in 0..target) {
                if (j == 0) {
                    dp[i][j] = 1
                } else if (i == 0) {
                    dp[i][j] = if (arr[i] == j) 1 else 0
                } else {
                    val noTake = dp[i - 1][j]
                    var take = 0
                    if (arr[i] <= j) {
                        take = dp[i - 1][j - arr[i]]
                    }
                    dp[i][j] = take + noTake
                }
            }
        }

        //space optimized
        var prev: IntArray? = null
        for (i in 0 until n) {
            val cur = IntArray(target + 1)
            for (j in 0..target) {
                if (j == 0) {
                    cur[j] = 1
                } else if (i == 0) {
                    cur[j] = if (arr[i] == j) 1 else 0
                } else {
                    val noTake = prev!![j]
                    var take = 0
                    if (arr[i] <= j) {
                        take = prev[j - arr[i]]
                    }
                    cur[j] = take + noTake
                }
            }
            prev = cur
        }

        return prev!![target]
    }


    /**
     * We are given a target sum of ‘X’ and ‘N’ distinct numbers denoting the coin denominations.
     * We need to tell the minimum number of coins required to reach the target sum.
     * We can pick a coin denomination for any number of times we want.
     *
     */
    fun minCoins(nums: IntArray, target: Int): Int {
        val n = nums.size

        // recursive {top- down} and memoization
        // f(i,k) denotes numbers of coins needed to make sum k with coins from n-1 to i
        //base (0,k)
        val memo = Array(n) { IntArray(target + 1) { -1 } }

        // TC : O(2^n) we take or do not take
        fun f(i: Int, k: Int): Int {
            if (k == 0) return 0
            if (i == 0) {
                return if (k % nums[i] == 0) k / nums[i]
                else return Int.MAX_VALUE
            }

            if (memo[i][k] != -1) return memo[i][k]
            val noTake = f(i - 1, nums[i])

            var take = Int.MAX_VALUE
            if (nums[i] <= k) {
                take = 1 + f(i, k - nums[i])
            }

            memo[i][k] = minOf(take, noTake)
            return memo[i][k]
        }

//        return f(n-1, target)

        //tabulation {bottom-up}
        // TC : O(n*target)
        val dp = Array(n) { IntArray(target + 1) }
        for (i in 0 until n) {
            for (j in 0..target) {
                if (j == 0) {
                    dp[i][j] = 0
                } else if (i == 0) {
                    dp[i][j] = if (j % nums[i] == 0) j / nums[i] else Int.MAX_VALUE
                } else {
                    val noTake = dp[i - 1][j]
                    var take = Int.MAX_VALUE
                    if (nums[i] <= j) {
                        take = dp[i][j - nums[i]]
                    }
                    dp[i][j] = minOf(take, noTake)
                }
            }
        }

        //space optimized
        var prev: IntArray? = null
        for (i in 0 until n) {
            val cur = IntArray(target + 1)
            for (j in 0..target) {
                if (j == 0) {
                    cur[j] = 0
                } else if (i == 0) {
                    cur[j] = if (j % nums[i] == 0) j / nums[i] else Int.MAX_VALUE
                } else {
                    val noTake = prev!![j]
                    var take = Int.MAX_VALUE
                    if (nums[i] <= j) {
                        take = cur[j - nums[i]]
                    }
                    cur[j] = minOf(take, noTake)
                }
            }
            prev = cur
        }

        return prev!![target]

    }

    /**
     * We are given an array ‘ARR’ of size ‘N’ and a number ‘Target’.
     * Our task is to build an expression from the given array where we can place a ‘+’ or ‘-’ sign in front of an integer.
     * We want to place a sign in front of every integer of the array and get our required target.
     * We need to count the number of ways in which we can achieve our required target.
     */
    fun countWaysToTarget(nums: IntArray, target: Int): Int {
        /**
         * Intution:
         * If we take one plus and neg and do recursive call for rest of the array
         * We might have to handle the -ve target as well, in memoization have -ve index is not possible
         *
         * Here we are add and sub all possible ways to get target, meaning s1 -s2 = target
         * s1 is sum of all +ve numbers and s2 is sum of all -ve numbers
         *
         *
         * this is same as countPartitionWithDiff
         * 1. s1- s2 = target - Eq1
         * 2. s1 + s2 = sum - Eq2
         * 3. we can derive s1 = (sum + target)/2
         */
        val n = nums.size
        val sum = nums.sum()
        val s1 = (sum + target) / 2
        // recursive {top- down} and memoization
        val memo = Array(n) { IntArray(s1 + 1) { -1 } }
        fun f(i: Int, k: Int): Int {
            if (k == 0) return 1
            if (i == 0) return if (nums[i] == k) 1 else 0

            if (memo[i][k] != 0) return memo[i][k]
            val noTake = f(i - 1, k)
            var take = 0
            if (nums[i] <= k) {
                take = f(i - 1, k - nums[i])
            }

            memo[i][k] = noTake + take
            return memo[i][k]
        }

        return f(n - 1, s1)
    }

    /**
     * We are given an array Arr with N distinct coins and a target. We have an infinite supply of each coin denomination.
     *  We need to find the number of ways we sum up the coin values to give us the target.
     */
    fun countWaysToSum(nums: IntArray, target: Int): Int {
        val n = nums.size
        // recursive {top- down} and memoization
        val memo = Array(n) { IntArray(target + 1) { -1 } }
        fun f(i: Int, k: Int): Int {
            if (k == 0) return 1
            if (i == 0) return if (k % nums[i] == 0) 1 else 0

            if (memo[i][k] != -1) return memo[i][k]
            val noTake = f(i - 1, k)
            var take = 0
            if (nums[i] <= k) {
                take = f(i, k - nums[i])
            }

            memo[i][k] = noTake + take
            return memo[i][k]
        }

        return f(n - 1, target)


    }

    /**
     * A thief wants to rob a store. He is carrying a bag of capacity W. The store has ‘n’ items of infinite supply.
     * Its weight is given by the ‘wt’ array and its value by the ‘val’ array.
     * He can either include an item in its knapsack or exclude it but can’t partially have it as a fraction.
     * We need to find the maximum value of items that the thief can steal.
     * He can take a single item any number of times he wants and put it in his knapsack.
     *
     */
    fun knapsack(wt: IntArray, price: IntArray, W: Int): Int {
        val n = wt.size
        // recursive {top- down} and memoization

        val memo = Array(n) { IntArray(W + 1) { -1 } }
        fun f(i: Int, w: Int): Int {
            if (w == 0) return 0
            //bound knapsack when we only one item to take
            if (i == 0) {
                //bcuz we have infinite supply of items
                return w / wt[i] * price[i]
            }

            if (memo[i][w] != -1) return memo[i][w]
            val noTake = f(i - 1, w)
            var take = 0
            if (wt[i] <= w) {
                take = f(i, w - wt[i]) + price[i]
            }

            memo[i][w] = maxOf(take, noTake)
            return memo[i][w]
        }

//        return f(n - 1, W)
        //tabulation {bottom-up}

        val dp = Array(n) { IntArray(W + 1) }
        for (i in 0..n - 1) {
            for (j in 0..W) {
                if (j == 0) {
                    dp[i][j] = 0
                } else if (i == 0) {
                    dp[i][j] = j / wt[i] * price[i]
                } else {
                    val noTake = dp[i - 1][j]
                    var take = 0
                    if (wt[i] <= j) {
                        take = dp[i][j - wt[i]] + price[i]
                    }
                    dp[i][j] = maxOf(take, noTake)
                }
            }
        }

        //space optimized
        var prev: IntArray? = null

        for (i in 0..n - 1) {
            val cur = IntArray(W + 1)
            for (j in 0..W) {
                if (j == 0) {
                    cur[j] = 0
                } else if (i == 0) {
                    cur[j] = j / wt[i] * price[i]
                } else {
                    val noTake = prev!![j]
                    var take = 0
                    if (wt[i] <= j) {
                        take = prev[j - wt[i]] + price[i]
                    }
                    cur[j] = maxOf(take, noTake)
                }
            }
            prev = cur
        }

        return prev!![W]
    }


    /**
     * We are given a rod of size ‘N’. It can be cut into pieces. Each length of a piece has a particular price given by the price array.
     * Our task is to find the maximum revenue that can be generated by selling the rod after cutting( if required) into pieces.
     *
     * price array is zero-indexed
     *
     * prices[i] denotes the price of length i+1
     */
    fun rodCutting(price: IntArray, n: Int): Int {
        /*
        Intution:
        1. we can cut the rod at any length from 1 to n
        2. from 1 to n we to pick rods which maximize the price
        3. it is similar to knapsack problem, we to collect the wt which maximize the price
        4. here we will collect the rods of any size which will maximize the price
        5. given problem is zero-index,
        0-> price of length
        1-> price of length 1 and so oon

         */
        // recursive {top- down} and memoization
        val memo = Array(n) { IntArray(n + 1) { -1 } }
        fun f(i: Int, ln: Int): Int {
            if (ln == 0) return 0
            if (i == 0) {
                return ln / 1 * price[i]
            }
            if (memo[i][ln] != -1) return memo[i][ln]
            val notCollect = 0 + f(i - 1, ln)
            var collect = Int.MIN_VALUE
            if (i <= ln) {
                collect = price[i] + f(i, ln - i)
            }

            memo[i][ln] = maxOf(collect, notCollect)
            return memo[i][ln]
        }

//        return f(n - 1, n)

        //tabulation {bottom-up}

        val dp = Array(n) { IntArray(n + 1) }
        for (i in 0..n - 1) {
            for (j in 0..n) {
                if (j == 0) {
                    dp[i][j] = 0
                } else if (i == 0) {
                    dp[i][j] = j / 1 * price[i]
                } else {
                    val notCollect = dp[i - 1][j]
                    var collect = Int.MIN_VALUE
                    if (i <= j) {
                        collect = price[i] + dp[i][j - i]
                    }
                    dp[i][j] = maxOf(collect, notCollect)
                }
            }
        }

        return dp[n - 1][n]
    }
}

fun main() {
    val obj = `SubSequence-DP`()
    println(obj.minAbsDiff(intArrayOf(1, 6, 11, 5)))
}