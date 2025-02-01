package dsa.bitManipulation

class Problems {


    /**
     * 29. Divide Two Integers
     * Solved
     * Medium
     * Topics
     * Companies
     * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
     *
     * The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
     *
     * Return the quotient after dividing dividend by divisor.
     *
     * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.
     *
     *
     *
     * Example 1:
     *
     * Input: dividend = 10, divisor = 3
     * Output: 3
     * Explanation: 10/3 = 3.33333.. which is truncated to 3.
     * Example 2:
     *
     * Input: dividend = 7, divisor = -3
     * Output: -2
     * Explanation: 7/-3 = -2.33333.. which is truncated to -2.
     */
    fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == Int.MIN_VALUE && divisor == -1) {
            return Int.MAX_VALUE
        }

        var a = Math.abs(dividend.toLong())
        val b = Math.abs(divisor.toLong())
        var res = 0

        while (a >= b) {
            var x = 0
            // using x shl y
            while (a >= b shl x) {
                x++
            }
            res += 1 shl (x - 1)
            a -= b shl (x - 1)
        }

        return if (dividend > 0 == divisor > 0) res else -res
    }

    /**
     * 2220. Minimum Bit Flips to Convert Number
     * Solved
     * Easy
     * Topics
     * Companies
     * Hint
     * A bit flip of a number x is choosing a bit in the binary representation of x and flipping it from either 0 to 1 or 1 to 0.
     *
     * For example, for x = 7, the binary representation is 111 and we may choose any bit (including any leading zeros not shown) and flip it. We can flip the first bit from the right to get 110, flip the second bit from the right to get 101, flip the fifth bit from the right (a leading zero) to get 10111, etc.
     * Given two integers start and goal, return the minimum number of bit flips to convert start to goal.
     *
     *
     *
     * Example 1:
     *
     * Input: start = 10, goal = 7
     * Output: 3
     * Explanation: The binary representation of 10 and 7 are 1010 and 0111 respectively. We can convert 10 to 7 in 3 steps:
     * - Flip the first bit from the right: 1010 -> 1011.
     * - Flip the third bit from the right: 1011 -> 1111.
     * - Flip the fourth bit from the right: 1111 -> 0111.
     * It can be shown we cannot convert 10 to 7 in less than 3 steps. Hence, we return 3.
     * Example 2:
     *
     * Input: start = 3, goal = 4
     * Output: 3
     * Explanation: The binary representation of 3 and 4 are 011 and 100 respectively. We can convert 3 to 4 in 3 steps:
     * - Flip the first bit from the right: 011 -> 010.
     * - Flip the second bit from the right: 010 -> 000.
     * - Flip the third bit from the right: 000 -> 100.
     * It can be shown we cannot convert 3 to 4 in less than 3 steps. Hence, we return 3.
     */
    fun minFlips(start: Int, goal: Int): Int {
        var res = 0
        var a = start
        var b = goal

        while (a > 0 || b > 0) {
            if (a and 1 != b and 1) {
                res++
            }
            a = a shr 1
            b = b shr 1
        }

        return res
    }

    /**
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     *
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,2,1]
     * Output: 1
     * Example 2:
     *
     * Input: nums = [4,1,2,1,2]
     * Output: 4
     * Example 3:
     *
     * Input: nums = [1]
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 3 * 104
     * -3 * 104 <= nums[i] <= 3 * 104
     * Each element in the array appears twice except for one element which appears only once.
     */
    fun singleNumber(nums: IntArray): Int {
        var res = 0
        for (num in nums) {
            res = res xor num
        }
        return res
    }

    /**
     * Power Set
     */
    fun powerSet(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        val n = nums.size
        for (i in 0 until (1 shl n)) {
            val temp = mutableListOf<Int>()
            for (j in 0 until n) {
                if (i and (1 shl j) > 0) {
                    temp.add(nums[j])
                }
            }
            res.add(temp)
        }
        return res
    }

    /**
     * Xor from 1 to n
     */
    fun xorFrom1ToN(n: Int): Int {
        //Explanation
        // n=1  , 1 = 1
        // n=2  , 1^2 = 3
        // n=3  , 1^2^3 = 0
        // n=4  , 1^2^3^4 = 4


        //n=5 , 1^2^3^4^5 = 1
        //n=6 , 1^2^3^4^5^6 = 7
        //n=7 , 1^2^3^4^5^6^7 = 0
        //n=8 , 1^2^3^4^5^6^7^8 = 8

        //..so on

        return when (n % 4) {
            0 -> n
            1 -> 1
            2 -> n + 1
            else -> 0
        }
    }


    /**
     * Xor from l to r
     */
    fun xorFromLToR(l: Int, r: Int): Int {
        return xorFrom1ToN(r) xor xorFrom1ToN(l - 1)
    }

    /**
     * wo numbers with odd occurrences
     * Difficulty: MediumAccuracy: 49.49%Submissions: 62K+Points: 4
     * Given an unsorted array, Arr[] of size N and that contains even number of occurrences for all numbers except two numbers. Find the two numbers in decreasing order which has odd occurrences.
     *
     * Example 1:
     *
     * Input:
     * N = 8
     * Arr = {4, 2, 4, 5, 2, 3, 3, 1}
     * Output: {5, 1}
     * Explanation: 5 and 1 have odd occurrences.
     *
     * Example 2:
     *
     * Input:
     * N = 8
     * Arr = {1 7 5 7 5 4 7 4}
     * Output: {7, 1}
     * Explanation: 7 and 1 have odd occurrences.
     *
     * Your Task:
     * You don't need to read input or print anything. Your task is to complete the function twoOddNum() which takes the array Arr[] and its size N as input parameters and returns the two numbers in decreasing order which have odd occurrences.
     */
    fun twoOddNum(arr: IntArray, n: Int): IntArray {
        var num = 0
        for (it in arr) {
            num = num xor it
        }

        val rightSetBit = (num and num - 1) xor num

        var x = 0
        var y = 0
        for (it in arr) {
            if (num and rightSetBit > 0) {
                x = x xor it
            } else {
                y = y xor it
            }
        }

        return if (x < y) {
            intArrayOf(y, x)
        } else {
            intArrayOf(x, y)
        }
    }
}