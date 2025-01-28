package dp

import annotations.Important

class `LIS-DP` {

    /**
     * Longest Increasing Subsequence with Binary Search
     * TC : O(nlogn)
     */
    fun LISUsingBS(arr: IntArray): Int {
        val n = arr.size
        val dp = IntArray(n) { 0 }
        var len = 0
        for (i in 0 until n) {
            var l = 0
            var r = len
            while (l < r) {
                val mid = l + (r - l) / 2
                if (dp[mid] < arr[i]) {
                    l = mid + 1
                } else {
                    r = mid
                }
            }
            dp[l] = arr[i]
            if (l == len) {
                len++
            }
        }

        return len
    }

    /**
     * Longest Increasing Subsequence
     */
    fun LIS(arr: IntArray): Int {
        val n = arr.size

        // recursion and memoization

        val memo = Array(n) { IntArray(n + 1) { -1 } }
        fun f(i: Int, j: Int): Int {
            // j is the prev picked index
            if (i == n) return 0
            if (memo[i][j + 1] != -1) return memo[i][j + 1]
            val notTake = 0 + f(i + 1, j)
            var take = 0
            if (j == -1 || arr[i] > arr[j]) {
                // when we pick the element , we update prev picked index to i
                take = 1 + f(i + 1, i)
            }
            memo[i][j + 1] = maxOf(notTake, take)
            return memo[i][j + 1]
        }

//        return f(0, -1)
        //tabulation
        val dp = Array(n) { IntArray(n + 1) { 0 } }
        for (i in n - 1 downTo 0) {
            for (j in i - 1 downTo -1) {
                val notTake = 0 + dp[i + 1][j + 1]
                var take = 0
                if (j == -1 || arr[i] > arr[j]) {
                    take = 1 + dp[i + 1][i + 1]
                }
                dp[i][j + 1] = maxOf(notTake, take)
            }
        }
        //space optimized
        var next = IntArray(n + 1) { 0 }
        val cur = IntArray(n + 1) { 0 }
        for (i in n - 1 downTo 0) {
            for (j in i - 1 downTo -1) {
                val notTake = 0 + cur[j + 1]
                var take = 0
                if (j == -1 || arr[i] > arr[j]) {
                    take = 1 + next[i + 1]
                }
                cur[j + 1] = maxOf(notTake, take)
            }
            next = cur.clone()
        }

//        return dp[0][0]


        // bottom - up approach
        var res = 0
        val dp3 = IntArray(n) { 1 }
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (arr[i] > arr[j]) {
                    dp3[i] = maxOf(dp3[i], 1 + dp3[j])
                }
            }
            res = maxOf(res, dp3[i])
        }
        return res
    }

    /**
     * Count no of LIS
     *
     *
     * Example Consider the array [1, 3, 5, 4, 7].
     * dp and count are initialized to [1, 1, 1, 1, 1].
     * Processing 3: dp[1] = 2, count[1] = 1 (extends 1)
     * Processing 5: dp[2] = 3, count[2] = 1 (extends 1, 3)
     * Processing 4: dp[3] = 3, count[3] = 1 (extends 1, 3), but also count[3] += count[2] = 2 (another subsequence of length 3)
     * Processing 7: dp[4] = 4, count[4] = count[2] + count[3] = 3 (extends 1, 3, 5 and 1, 3, 4)
     * The length of the LIS is 4.
     * The total number of LIS is count[4] = 3. In summary, the intuition behind counting the LIS is to keep track of the number
     * of ways to reach each element with an increasing subsequence of a specific length, and then accumulate these counts to
     * find the total number of longest increasing subsequences. I hope this explanation helps! Let me know if you have any other questions.
     */
    fun countNoOfLIS(arr: IntArray): Int {
        val n = arr.size
        val dp = IntArray(n) { 1 }
        val count = IntArray(n) { 1 }
        for (i in 0..n - 1) {
            for (j in 0..i - 1) {
                if (arr[i] > arr[j]) {
                    if (dp[i] == 1 + dp[j])
                        count[i] += count[j]
                    else if (dp[i] < dp[j] + 1) {
                        dp[i] = 1 + dp[j]
                        count[i] = count[j]
                    }
                }
            }
        }

        return count.max()
    }

    /**
     * Printing the LIS
     */
    fun printLIS(arr: IntArray) {
        //TC : O(n^2)
        val n = arr.size
        val dp = IntArray(n) { 1 }
        val prev = IntArray(n) { -1 }
        var maxIndex = 0
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (arr[i] > arr[j] && dp[i] < 1 + dp[j]) {
                    dp[i] = 1 + dp[j]
                    prev[i] = j
                }
            }
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i
            }
        }
        val lis = mutableListOf<Int>()
        var k = maxIndex
        while (k >= 0) {
            lis.add(arr[k])
            k = prev[k]
        }
        lis.reverse()
        println(lis)
    }

    /**
     * Maximum Sum Increasing Subsequence
     */
    fun MSIS(arr: IntArray): Int {
        val n = arr.size
        val dp = IntArray(n) { arr[it] }
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (arr[i] > arr[j]) {
                    dp[i] = maxOf(dp[i], arr[i] + dp[j])
                }
            }
        }
        return dp.maxOrNull()!!
    }

    fun printMSIS(array: IntArray): IntArray {
        val n = array.size
        val dp = IntArray(n) { array[it] }
        val prev = IntArray(n) { -1 }
        var maxIndex = 0
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (array[i] > array[j] && dp[i] < array[i] + dp[j]) {
                    dp[i] = array[i] + dp[j]
                    prev[i] = j
                }
            }
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i
            }
        }
        val lis = mutableListOf<Int>()
        var k = maxIndex
        while (k >= 0) {
            lis.add(array[k])
            k = prev[k]
        }
        lis.reverse()
        return lis.toIntArray()
    }


    /**
     * Given an integer n, return the least number of perfect square numbers that sum to n.
     *
     * A perfect square is an integer that is the square of an integer; in other words,
     * it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 12
     * Output: 3
     * Explanation: 12 = 4 + 4 + 4.
     * Example 2:
     *
     * Input: n = 13
     * Output: 2
     * Explanation: 13 = 4 + 9.
     *
     *
     * Constraints:
     *
     * 1 <= n <= 104
     */
    fun numSquares(n: Int): Int {
        val dp = IntArray(n + 1) { Int.MAX_VALUE }
        dp[0] = 0
        for (i in 1..n) {
            var j = 1
            while (j * j <= i) {
                dp[i] = minOf(dp[i], 1 + dp[i - j * j])
                j++
            }
        }
        return dp[n]
    }


    /**
     * Longest Divisible Subset
     *
     * Example :
     * Input: nums = [1,2,3]
     * Output: [1,2] (of course, [1,3] will also be ok)
     *
     * Input: nums = [1,2,4,8,3]
     * Output: [1,2,4,8]
     *
     */
    fun LDS(nums: IntArray): List<Int> {
        val arr = nums.toSet().sortedBy { it }
        val n = nums.size
        val pos = IntArray(n) { -1 }
        val dp = IntArray(n) { 1 }
        var maxLen = 1
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (arr[i] % arr[j] == 0) {
                    dp[i] = maxOf(dp[i], 1 + dp[j])
                    pos[i] = j
                }
            }
            maxLen = maxOf(maxLen, dp[i])
        }

        var k = dp.indexOf(maxLen)
        val res = mutableListOf<Int>()
        while (k != -1) {
            res.add(arr[k])
            k = pos[k]
        }

        return res
    }

    /**
     * Given an array of positive integers. Find the maximum length of Bitonic subsequence.
     * A subsequence of array is called Bitonic if it is first strictly increasing, then strictly decreasing. Return the maximum length of bitonic subsequence.
     *
     * Note : A strictly increasing or a strictly decreasing sequence should not be considered as a bitonic sequence
     *
     * Examples :
     *
     * Input: n = 5, nums[] = [1, 2, 5, 3, 2]
     * Output: 5
     * Explanation: The sequence {1, 2, 5} is increasing and the sequence {3, 2} is decreasing so merging both we will get length 5.
     * Input: n = 8, nums[] = [1, 11, 2, 10, 4, 5, 2, 1]
     * Output: 6
     * Explanation: The bitonic sequence {1, 2, 10, 4, 2, 1} has length 6.
     * Expected Time Complexity: O(n2)
     * Expected Space Complexity: O(n)
     *
     *
     * Constraints:
     * 1 ≤ length of array ≤ 103
     * 1 ≤ arr[i] ≤ 104
     *
     */
    fun bitonicSequence(nums: IntArray): Int {
        val n = nums.size
        val lis = IntArray(n) { 1 }
        val lds = IntArray(n) { 1 }

        for (i in 0 until n) {
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    lis[i] = maxOf(lis[i], lis[j] + 1)
                }
            }
        }

        for (i in n - 1 downTo 0) {
            for (j in n - 1 downTo i) {
                if (nums[i] > nums[j]) {
                    lds[i] = maxOf(lds[i], lds[j] + 1)
                }
            }
        }

        var res = 0
        for (i in 0 until n) {
            res = maxOf(res, lis[i] + lds[i] - 1)
        }
        return res
    }


    /**
     * Longest String Chain
     *
     * What is the String chain :
     * A string chain is a sequence of strings [s1, s2, ..., sk] with k >= 1,
     * where all the strings are from words and s[i] = s[i + 1] - 1 (1-indexed).
     *
     * Example 1:
     * Input: words = ["a","b","ba","bca","bda","bdca"]
     * Output: 4
     *
     * All String chain possible are
     * ["a","ba","bca","bdca"]
     * ["a","ba","bda"]
     * ["a","b","bca","bdca"]
     * ["a","ba""]
     * etc
     *
     * Among all the longest chain is ["a","ba","bca","bdca"]
     */
    fun longestStringChain(arr: Array<String>): Int {

        fun compare(s1: String, s2: String): Boolean {
            if (s1.length != s2.length + 1) return false

            var i = 0
            var j = 0
            while (i < s1.length) {
                if (j < s2.length && s1[i] == s2[j]) {
                    i++
                    j++
                } else {
                    i++
                }
            }
            return i == s1.length && j == s2.length
        }

        arr.sortBy { it.length }
        val n = arr.size
        val dp = IntArray(n) { 1 }

        for (i in 0 until n) {
            for (j in 0 until i) {
                if (compare(arr[i], arr[j])) {
                    dp[i] = maxOf(dp[i], 1 + dp[j])
                }
            }
        }

        return dp.max()
    }


    /**
     * You are given a set of N types of rectangular 3-D boxes, where the ith box has height h, width w and length l.
     * Your task is to create a stack of boxes which is as tall as possible, but you can only stack a box on top of another box if the dimensions of the 2-D base of the lower box are each strictly larger than those of the 2-D base of the higher box. Of course, you can rotate a box so that any side functions as its base.It is also allowable to use multiple instances of the same type of box. Your task is to complete the function maxHeight which returns the height of the highest possible stack so formed.
     *
     *
     * Note:
     * Base of the lower box should be strictly larger than that of the new box we're going to place.
     * This is in terms of both length and width, not just in terms of area.
     * So, two boxes with same base cannot be placed one over the other.
     *
     *
     *
     * Example 1:
     *
     * Input:
     * n = 4
     * height[] = {4,1,4,10}
     * width[] = {6,2,5,12}
     * length[] = {7,3,6,32}
     * Output: 60
     * Explanation: One way of placing the boxes is
     * as follows in the bottom to top manner:
     * (Denoting the boxes in (l, w, h) manner)
     * (12, 32, 10) (10, 12, 32) (6, 7, 4)
     * (5, 6, 4) (4, 5, 6) (2, 3, 1) (1, 2, 3)
     * Hence, the total height of this stack is
     * 10 + 32 + 4 + 4 + 6 + 1 + 3 = 60.
     * No other combination of boxes produces a
     * height greater than this.
     * Example 2:
     *
     * Input:
     * n = 3
     * height[] = {1,4,3}
     * width[] = {2,5,4}
     * length[] = {3,6,1}
     * Output: 15
     * Explanation: One way of placing the boxes is
     * as follows in the bottom to top manner:
     * (Denoting the boxes in (l, w, h) manner)
     * (5, 6, 4) (4, 5, 6) (3, 4, 1), (2, 3, 1)
     * (1, 2, 3).
     * Hence, the total height of this stack is
     * 4 + 6 + 1 + 1 + 3 = 15
     * No other combination of boxes produces a
     * height greater than this.
     * Your Task:
     * You don't need to read input or print anything. Your task is to complete the function maxHeight() which takes three arrays height[], width[], length[], and N as a number of boxes and returns the highest possible stack height which could be formed.
     *
     *
     * Expected Time Complexity : O(N*N)
     * Expected Auxiliary Space: O(N)
     *
     *
     * Constraints:
     * 1<= N <=100
     * 1<= l,w,h <=100
     */
    @Important
    fun maxHeight(height: IntArray, width: IntArray, length: IntArray, n: Int): Int {
        /*
        Box stacking - given listOf((h,l,b)) find max height after stacking
            Constraints - base area should be strictly increasing meaning basearea1 > basearea2 ie. l1>l2 && w1>w2
            Rotations are possible…since cubiod has 3 uniqu base (lb, hb, lh) we will have n*3 rotations for all boxes to stack
            We can put a object any no of items following constraint 1.. So at max each box can be stacked 3 times as we have 3 unique base orientation and after that we get duplicates hence constraint is failed

        Intution
        We create list of all the rotation possible
        While we make rotation of a box we will should check for l1>l2 && w1>w2
        Sort the list as per height based on area of rotates
        After that it is possible to get base area1 > area2 which is not accepted …so should skip area2. like
        1,2,5,3,6,4,10 -> 1,2,3,4,10  which LIS
        Then perform LIS

         */
        // we only get 3*n rotations, bzuz we have 3 unique base area out 6 possible
        val box = Array(3 * n) { Triple(0, 0, 0) }
        var index = 0
        //create all possible rotations as we
        for (i in 0 until n) {
            // why we are taking max and min because we need to check for base area
            // when we compare base area we should have l1>l2 && w1>w2
            box[index++] = Triple(height[i], maxOf(width[i], length[i]), minOf(width[i], length[i]))
            box[index++] = Triple(width[i], maxOf(height[i], length[i]), minOf(height[i], length[i]))
            box[index++] = Triple(length[i], maxOf(width[i], height[i]), minOf(width[i], height[i]))
        }

        box.sortByDescending { it.second }

        val dp = IntArray(3 * n)
        for (i in box.indices) {
            dp[i] = box[i].first
            for (j in 0 until i) {
                if (box[j].second > box[i].second && box[j].third > box[i].third) {
                    dp[i] = maxOf(dp[i], dp[j] + box[i].first)
                }
            }
        }

        return dp.maxOrNull()!!
    }


    /**
     * Maximum Length Chain of Pairs | DP-20
     * Last Updated : 12 Apr, 2023
     * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
     * A pair (c, d) can follow another pair (a, b) if b < c. Chain of pairs can be formed in this fashion.
     * Find the longest chain which can be formed from a given set of pairs.
     * Source: Amazon Interview | Set 2
     * For example, if the given pairs are {{5, 24}, {39, 60}, {15, 28}, {27, 40}, {50, 90} },
     * then the longest chain that can be formed is of length 3, and the chain is {{5, 24}, {27, 40}, {50, 90}}
     *
     */
    fun maxChainLength(arr: Array<Pair<Int, Int>>): Int {
        /*
        1. Sort the array based on second element
        2. Then find the LIS
         */
        // TC - O(n^2)
        val dp = IntArray(arr.size) { 1 }
        var res = 1
        for (i in arr.indices) {
            for (j in 0 until i) {
                if (arr[j].second < arr[i].first) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
            res = maxOf(res, dp[i])
        }
//        return res


        //using intervals
        // TC - O(nlogn)
        arr.sortBy { it.second }
        var prev = arr[0]
        for (i in 1..arr.lastIndex) {
            if (arr[i].first > prev.second) {
                res++
                prev = arr[i]
            }
        }

        return res
    }


    /**
     * 2501. Longest Square Streak in an Array
     * Solved
     * Medium
     * Topics
     * Companies
     * Hint
     * You are given an integer array nums. A subsequence of nums is called a square streak if:
     *
     * The length of the subsequence is at least 2, and
     * after sorting the subsequence, each element (except the first element) is the square of the previous number.
     * Return the length of the longest square streak in nums, or return -1 if there is no square streak.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [4,3,6,16,8,2]
     * Output: 3
     * Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes [2,4,16].
     * - 4 = 2 * 2.
     * - 16 = 4 * 4.
     * Therefore, [4,16,2] is a square streak.
     * It can be shown that every subsequence of length 4 is not a square streak.
     * Example 2:
     *
     * Input: nums = [2,3,5,6,7]
     * Output: -1
     * Explanation: There is no square streak in nums so return -1.
     */
    @Important
    fun longestSquareStreak(nums: IntArray): Int {
        val n = nums.size
        val max = nums.max()
        var res = 0


        //using set
        val set = nums.toSet()

        for (i in 0 until n) {
            var num = nums[i]
            var count = 1
            while (num <= max!!) {
                if (!set.contains(num)) {
                    break
                }
                num *= num
                count++
            }

            res = maxOf(res, count)

        }


        //using dp
        val dp = IntArray(max + 1)
        for (i in 0 until n) {
            dp[nums[i]] = 1
        }

        for (i in 0 until n) {
            if (dp[i] != 0) {
                val sq = i * i
                if (sq > max) break
                if (dp[sq] != 0) {
                    dp[sq] = maxOf(dp[sq], 1 + dp[i])
                    res = maxOf(res, dp[sq])
                }
            }
        }
        return res
    }


}

fun main() {

}