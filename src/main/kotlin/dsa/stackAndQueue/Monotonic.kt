package dsa.stackAndQueue

import annotations.Important
import kotlin.collections.ArrayDeque
import kotlin.collections.isNotEmpty
import kotlin.collections.map
import kotlin.collections.mutableMapOf
import kotlin.collections.set
import kotlin.collections.toIntArray


class Monotonic {

    /**
     * Monotonic means the sequence is either entirely non-increasing or non-decreasing.
     * We can have monotonic arrays, stacks, and queues.
     *  Example:
     * 1. [1, 2, 3, 4, 5] is a monotonic array.
     * There used to determine if the sequence is increasing or decreasing.
     */

    /**
     * Previous Greater Element
     *
     * Input: [1, 5, 3, 2, 4, 6]
     * Output: [-1, -1, 5, 3, 5, -1]
     * Input: [6, 5, 4, 3, 2, 1]
     * Output: [-1, 6, 5, 4, 3, 2]
     */
    fun PGE(arr: IntArray): IntArray {
        val n = arr.size
        val stack = ArrayDeque<Int>()

        val res = IntArray(n)

        for (i in 0 until n) {
            // While the stack is not empty and the top element of the stack is less than or equal to the current element arr[i],
            // pop elements from the stack. These elements cannot be the nearest greater element for arr[i] or any subsequent elements.
            while (stack.isNotEmpty() && stack.last() <= arr[i]) {
                stack.removeLast()
            }

            res[i] = stack.lastOrNull() ?: -1
            stack.add(arr[i])
        }

        return res
    }

    /**
     * Next greater element to the right
     * Example:
     * NGE([2, 1, 2, 4, 3]) -> [4,2,4,-1,-1]
     */
    fun NGE(arr: IntArray): IntArray {
        val n = arr.size
        val stack = ArrayDeque<Int>()

        val res = IntArray(n)

        for (i in n - 1 downTo 0) {
            while (stack.isNotEmpty() && stack.last() <= arr[i]) {
                stack.removeLast()
            }

            res[i] = stack.lastOrNull() ?: -1
            stack.add(arr[i])
        }

        return res
    }


    /**
     * Previous Smaller Element
     * Example:
     * PSE([2, 1, 2, 4, 3]) -> [-1,-1,1,2,2]
     */
    fun PSE(arr: IntArray): IntArray {
        val n = arr.size
        val stack = ArrayDeque<Int>()

        val res = IntArray(n)

        for (i in 0 until n) {
            while (stack.isNotEmpty() && stack.last() >= arr[i]) {
                stack.removeLast()
            }

            res[i] = stack.lastOrNull() ?: -1
            stack.add(arr[i])
        }

        return res
    }

    fun NSE(arr: IntArray): IntArray {
        val n = arr.size
        val stack = ArrayDeque<Int>()

        val res = IntArray(n)

        for (i in n - 1 downTo 0) {
            while (stack.isNotEmpty() && stack.last() >= arr[i]) {
                stack.removeLast()
            }

            res[i] = stack.lastOrNull() ?: -1
            stack.add(arr[i])
        }

        return res
    }

    /**
     * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
     *
     * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
     *
     * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
     * If there is no next greater element, then the answer for this query is -1.
     *
     * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
     *
     *
     *
     * Example 1:
     *
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
     * Output: [-1,3,-1]
     * Explanation: The next greater element for each value of nums1 is as follows:
     * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
     * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
     * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
     * Example 2:
     *
     * Input: nums1 = [2,4], nums2 = [1,2,3,4]
     * Output: [3,-1]
     * Explanation: The next greater element for each value of nums1 is as follows:
     * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
     * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
     *
     */

    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
        val n = nums2.size
        val stack = ArrayDeque<Int>()

        val map = mutableMapOf<Int, Int>()

        for (i in n - 1 downTo 0) {
            while (stack.isNotEmpty() && stack.last() <= nums2[i]) {
                stack.removeLast()
            }

            map[nums2[i]] = stack.lastOrNull() ?: -1
            stack.add(nums2[i])
        }

        return nums1.map { map[it]!! }.toIntArray()
    }


    /**
     * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
     * return the next greater number for every element in nums.
     *
     * The next greater number of a number x is the first greater number to its traversing-order next in the array,
     * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,1]
     * Output: [2,-1,2]
     * Explanation: The first 1's next greater number is 2;
     * The number 2 can't find next greater number.
     * The second 1's next greater number needs to search circularly, which is also 2.
     * Example 2:
     *
     * Input: nums = [1,2,3,4,3]
     * Output: [2,3,4,-1,4]
     *
     */
    fun nextGreaterElements(nums: IntArray): IntArray {
        val n = nums.size
        val stack = ArrayDeque<Int>()

        val res = IntArray(n)


        for (i in 2 * n - 1 downTo 0) {
            while (stack.isNotEmpty() && stack.last() <= nums[i % n]) {
                stack.removeLast()
            }

            if (i < n) {
                res[i] = stack.lastOrNull() ?: -1
            }
            stack.add(nums[i % n])
        }

        return res
    }


    /**
     * Number of NGE's to right
     * Example:
     * NGEsToRight([2, 1, 2, 4, 3]) -> [1,2,1,0,0]
     */
    fun NGEsToRight(nums: IntArray): IntArray {
        val n = nums.size
        val stack = ArrayDeque<Int>()

        val res = IntArray(n)

        for (i in n - 1 downTo 0) {
            while (stack.isNotEmpty() && stack.last() <= nums[i]) {
                stack.removeLast()
            }

            res[i] = stack.size
            stack.add(nums[i])
        }

        return res
    }


    /**
     * Given an array of integers, find the sum of min(b), where b ranges over every (contiguous) subarray of arr.
     * Since the answer may be large, return the answer modulo 109 + 7.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [3,1,2,4]
     * Output: 17
     * Explanation:
     * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
     * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
     * Sum is 17.
     * Example 2:
     *
     * Input: arr = [11,81,94,43,3]
     * Output: 444
     *
     */
    private val MOD: Long = 1_00_00_00_007
    fun sumSubarrayMins(arr: IntArray): Int {

        /**
         * Intuition:
         * 1. We need to find the number of subarrays in which the current element is the minimum element.
         * 2. We can find the previous smaller element and next smaller element for each element.
         * 3. The number of subarrays in which the current element is the minimum element is equal to the number of subarrays
         *   in which the previous smaller element is the minimum element multiplied by the number of subarrays
         *   in which the next smaller element is the minimum element.
         *
         *   Example:
         *   [3,1,2,4]
         *   PSE: [-1,-1,1,2]
         *   NSE: [3,2,3,4]
         *
         *   The number of subarrays in which 1 is the minimum element is (1-(-1))*(2-1) = 2*1 = 2
         *
         *
         */

        val n = arr.size

        /*
            Why <= (smaller or equal)? If we used < (strictly smaller), we would exclude elements equal to arr[i] from
            the right boundary. This would lead to undercounting subarrays where arr[i] is the minimum.
         */
        fun findNSEE(): IntArray {
            val res = IntArray(n)
            val stack = ArrayDeque<Int>()

            for (i in n - 1 downTo 0) {
                while (stack.isNotEmpty() && arr[stack.last()] > arr[i]) {
                    stack.removeLast()
                }
                res[i] = stack.lastOrNull() ?: n
                stack.add(i)
            }
            return res
        }


        /*
            Why < (strictly smaller)? If we used <= (smaller or equal), we would include elements equal to arr[i] in the
            left boundary. This would lead to overcounting subarrays where arr[i] is not the minimum.
         */
        fun findPSE(): IntArray {
            val res = IntArray(n) { -1 }
            val stack = ArrayDeque<Int>()

            for (i in 0..n - 1) {
                while (stack.isNotEmpty() && arr[stack.last()] >= arr[i]) {
                    stack.removeLast()
                }

                res[i] = stack.lastOrNull() ?: -1
                stack.add(i)
            }
            return res
        }

        // we include the sa

        var total = 0L
        val prev = findPSE()
        val next = findNSEE()
        for (i in arr.indices) {
            val left = (i - prev[i]).toLong() % MOD
            val right = (next[i] - i).toLong() % MOD

            total += (left * right * arr[i]) % MOD
            total %= MOD

        }

        return total.toInt()
    }

    /**
     * We are given an array asteroids of integers representing asteroids in a row.
     *
     * For each asteroid, the absolute value represents its size, and the sign represents its direction
     * (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
     *
     * Find out the state of the asteroids after all collisions.
     * If two asteroids meet, the smaller one will explode.
     * If both are the same size, both will explode.
     * Two asteroids moving in the same direction will never meet.
     *
     *
     *
     * Example 1:
     *
     * Input: asteroids = [5,10,-5]
     * Output: [5,10]
     * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
     * Example 2:
     *
     * Input: asteroids = [8,-8]
     * Output: []
     * Explanation: The 8 and -8 collide exploding each other.
     * Example 3:
     *
     * Input: asteroids = [10,2,-5]
     * Output: [10]
     * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
     */
    fun asteroidCollision(asteroids: IntArray): IntArray {

        /**
         *
         *  We connect this problem with the previous smaller element problem.
         *  We can find the previous smaller element for each element.
         *  If the current asteroid is negative and the previous smaller element is positive,
         *  we need to check if the previous smaller element is smaller or equal to the current asteroid.
         *
         *
         * Intuition:
         * 1. We can use a stack to keep track of the asteroids.
         * 2. If the current asteroid is negative and the top of the stack is positive,
         *    we need to check if the top of the stack is smaller or equal to the current asteroid.
         *    If the top of the stack is smaller, we need to remove the top of the stack and continue checking.
         *    If the top of the stack is equal, we need to remove the top of the stack and break the loop.
         *    If the top of the stack is greater, we need to break the loop.
         *
         */
        val stack = ArrayDeque<Int>()

        for (asteriod in asteroids) {

            var destroy = false

            while (stack.isNotEmpty() && stack.last() > 0 && asteriod < 0) {
                if (stack.last() == -asteriod) {
                    stack.removeLast()
                    destroy = true
                    break
                } else if (stack.last() < -asteriod) {
                    stack.removeLast()
                } else {
                    destroy = true
                    break
                }
            }

            if (!destroy) {
                stack.add(asteriod)
            }
        }

        return stack.toIntArray()
    }

    /**
     * 2104. Sum of Subarray Ranges
     * Medium
     * Topics
     * Companies
     * Hint
     * You are given an integer array nums.
     * The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
     *
     * Return the sum of all subarray ranges of nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3]
     * Output: 4
     * Explanation: The 6 subarrays of nums are the following:
     * [1], range = largest - smallest = 1 - 1 = 0
     * [2], range = 2 - 2 = 0
     * [3], range = 3 - 3 = 0
     * [1,2], range = 2 - 1 = 1
     * [2,3], range = 3 - 2 = 1
     * [1,2,3], range = 3 - 1 = 2
     * So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
     * Example 2:
     *
     * Input: nums = [1,3,3]
     * Output: 4
     * Explanation: The 6 subarrays of nums are the following:
     * [1], range = largest - smallest = 1 - 1 = 0
     * [3], range = 3 - 3 = 0
     * [3], range = 3 - 3 = 0
     * [1,3], range = 3 - 1 = 2
     * [3,3], range = 3 - 3 = 0
     * [1,3,3], range = 3 - 1 = 2
     * So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
     * Example 3:
     *
     * Input: nums = [4,-2,-3,4,1]
     * Output: 59
     * Explanation: The sum of all subarray ranges of nums is 59.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * -109 <= nums[i] <= 109
     *
     *
     * Follow-up: Could you find a solution with O(n) time complexity?
     */
    fun sumOfRanges(nums: IntArray): Long {


        val n = nums.size

        fun nse(): IntArray {
            val res = IntArray(n)
            val stack = ArrayDeque<Int>()

            for (i in n - 1 downTo 0) {
                while (stack.isNotEmpty() && nums[stack.last()] >= nums[i]) {
                    stack.removeLast()
                }
                res[i] = stack.lastOrNull() ?: n
                stack.add(i)
            }
            return res
        }

        fun pse(): IntArray {
            val res = IntArray(n)
            val stack = ArrayDeque<Int>()

            for (i in 0 until n) {
                while (stack.isNotEmpty() && nums[stack.last()] > nums[i]) {
                    stack.removeLast()
                }
                res[i] = stack.lastOrNull() ?: -1
                stack.add(i)
            }
            return res
        }


        fun nge(): IntArray {
            val res = IntArray(n)
            val stack = ArrayDeque<Int>()

            for (i in n - 1 downTo 0) {
                while (stack.isNotEmpty() && nums[stack.last()] <= nums[i]) {
                    stack.removeLast()
                }
                res[i] = stack.lastOrNull() ?: n
                stack.add(i)
            }
            return res
        }

        fun pge(): IntArray {
            val res = IntArray(n)
            val stack = ArrayDeque<Int>()

            for (i in 0 until n) {
                while (stack.isNotEmpty() && nums[stack.last()] < nums[i]) {
                    stack.removeLast()
                }
                res[i] = stack.lastOrNull() ?: -1
                stack.add(i)
            }
            return res
        }

        val nse = nse()
        val pse = pse()
        val nge = nge()
        val pge = pge()

        var minTotal = 0L

        for (i in nums.indices) {
            val left = (nse[i] - i).toLong()
            val right = (i - pse[i]).toLong()

            minTotal += left * right * nums[i]
        }

        var maxTotal = 0L

        for (i in nums.indices) {
            val left = (nge[i] - i).toLong()
            val right = (i - pge[i]).toLong()

            maxTotal += left * right * nums[i]
        }

        return (maxTotal - minTotal)
    }

    /**
     * 402. Remove K Digits
     * Medium
     * Topics
     * Companies
     * Given string num representing a non-negative integer num, and an integer k,
     * return the smallest possible integer after removing k digits from num.
     *
     *
     *
     * Example 1:
     *
     * Input: num = "1432219", k = 3
     * Output: "1219"
     * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
     * Example 2:
     *
     * Input: num = "10200", k = 1
     * Output: "200"
     * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
     * Example 3:
     *
     * Input: num = "10", k = 2
     * Output: "0"
     * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
     */
    @Important
    fun removeKDigits(num: String, remove: Int): String {
        /**
         *
         * This problem is similar to the previous smaller element problem.
         * Intuition:
         * 1. We can use a stack to keep track of the digits.
         * 2. If the current digit is smaller than the top of the stack, we need to remove the top of the stack.
         *
         */
        val stack = ArrayDeque<Char>()
        var k = remove

        for (c in num) {
            while (stack.isNotEmpty() && stack.last() >= c && k > 0) {
                stack.removeLast()
                k--
            }
            stack.add(c)
        }

        while (k > 0) {
            stack.removeLast()
            k--
        }

        var res = ""
        var leadingZero = true
        for (c in stack) {
            if (leadingZero && c == '0') {
                continue
            }
            leadingZero = false
            res += c
        }

        return res.ifEmpty { "0" }
    }

    /**
     * 84. Largest Rectangle in Histogram
     * Solved
     * Hard
     * Topics
     * Companies
     * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
     * return the area of the largest rectangle in the histogram.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: heights = [2,1,5,6,2,3]
     * Output: 10
     * Explanation: The above is a histogram where width of each bar is 1.
     * The largest rectangle is shown in the red area, which has an area = 10 units.
     * Example 2:
     *
     *
     * Input: heights = [2,4]
     * Output: 4
     *
     *
     * Constraints:
     *
     * 1 <= heights.length <= 105
     * 0 <= heights[i] <= 104
     */
    fun largestRectangleArea(heights: IntArray): Int {

        //naive approach
        val n = heights.size

        var res = 0
        for (i in heights.indices) {
            var min = heights[i]
            for (j in i until heights.size) {
                min = minOf(min, heights[j])
                res = maxOf(res, min * (j - i + 1))
            }
        }

        // return res

        //optimal approach
        val pse = IntArray(n)
        val nse = IntArray(n)

        val stack = ArrayDeque<Int>()

        for (i in 0 until n) {
            while (stack.isNotEmpty() && heights[stack.last()] >= heights[i]) {
                stack.removeLast()
            }
            pse[i] = stack.lastOrNull() ?: -1
            stack.add(i)
        }

        stack.clear()

        for (i in n - 1 downTo 0) {
            while (stack.isNotEmpty() && heights[stack.last()] >= heights[i]) {
                stack.removeLast()
            }
            nse[i] = stack.lastOrNull() ?: n
            stack.add(i)
        }

        var maxArea = 0

        for (i in heights.indices) {
            maxArea = maxOf(maxArea, (nse[i] - pse[i] - 1) * heights[i])
        }

//        return maxArea

        //optimal approach 2

        for (i in heights.indices) {

            while (stack.isNotEmpty() && heights[stack.last()] > heights[i]) {
                val element = stack.removeLast()
                val nsel = i
                val psel = stack.lastOrNull() ?: -1
                maxArea = maxOf(maxArea, (nsel - psel - 1) * heights[element])
            }

            stack.add(i)
        }

        while (stack.isNotEmpty()) {
            val element = stack.removeLast()
            val nsel = heights.size
            val psel = stack.lastOrNull() ?: -1
            maxArea = maxOf(maxArea, (nsel - psel - 1) * heights[element])
        }

        return maxArea
    }


    /**
     * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
     *
     *
     * Example 1:
     *
     * Input: matrix =
     *          [["1","0","1","0","0"],
     *          ["1","0","1","1","1"],
     *          ["1","1","1","1","1"],
     *          ["1","0","0","1","0"]]
     * Output: 6
     * Explanation: The maximal rectangle is shown in the above picture.
     * Example 2:
     *
     * Input: matrix = [["0"]]
     * Output: 0f
     * Example 3:
     *
     * Input: matrix = [["1"]]
     * Output: 1
     */
    fun maximalRectangle(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) return 0

        val m = matrix[0].size

        val heights = IntArray(m)

        var maxArea = 0

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1
                } else {
                    heights[j] = 0
                }
            }

            maxArea = maxOf(maxArea, largestRectangleArea(heights))
        }

        return maxArea
    }
}


fun main() {
    val monotonic = Monotonic()
    println(monotonic.PGE(intArrayOf(2, 1, 2, 4, 3)).toList())
    println(monotonic.NGE(intArrayOf(2, 1, 2, 4, 3)).toList())
    println(monotonic.PSE(intArrayOf(2, 1, 2, 4, 3)).toList())
    println(monotonic.NSE(intArrayOf(2, 1, 2, 4, 3)).toList())
}