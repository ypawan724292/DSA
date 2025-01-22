package greedy

import kotlin.math.absoluteValue

class Problems {

    /**
     * Problem Statement: Given an array where each element represents the maximum number of steps
     * you can jump forward from that element, return true if we can reach the last index starting
     * from the first index. Otherwise, return false.
     *
     * Example 1:
     * Input:nums = [2, 3, 1, 0, 4]
     *
     * Output: True
     * Explanation:
     *
     * We start at index 0, with value 2 this means we can jump to index 1 or 2.
     * From index 1, with value 3, we can jump to index 2, 3, or 4. However, if we jump to index 2 with value 1, we can only jump to index 3.
     * So we jump to index 1 then index 4 reaching the end of the array.
     * Hence, we return true.
     *
     */
    fun canJump(nums: IntArray): Boolean {
        var maxReach = 0
        for (i in nums.indices) {
            if (i > maxReach) return false
            maxReach = maxOf(maxReach, i + nums[i])
        }
        return true
    }

    /**
     * 45. Jump Game II
     * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
     *
     * Each element nums[i] represents the maximum length of a forward jump from index i.
     * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
     *
     * 0 <= j <= nums[i] and
     * i + j < n
     * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Example 2:
     *
     * Input: nums = [2,3,0,1,4]
     * Output: 2
     */
    fun jump(nums: IntArray): Int {
        var jumps = 0
        var currentJumpEnd = 0
        var farthest = 0
        for (i in 0 until nums.size - 1) {
            farthest = maxOf(farthest, i + nums[i])
            if (i == currentJumpEnd) {
                jumps++
                currentJumpEnd = farthest
            }
        }
        return jumps
    }


    /**
     * Shortest Job First (SJF) Scheduling
     *
     * In Shortest Job First (SJF) Scheduling, the process with the smallest execution time is selected for execution next.
     *
     * Example 1:
     * Input: arr = [3, 1, 2, 5, 4]
     */
    fun shortestJobFirst(arr: IntArray, n: Int): Int {
        var wtTime = 0
        var t = 0
        arr.sort()
        for (i in arr.indices) {
            wtTime += t
            t += arr[i]
        }
        return wtTime / n
    }

    /**
     *
     * 135. Candy
     * Solved
     * Hard
     * Topics
     * Companies
     * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
     *
     * You are giving candies to these children subjected to the following requirements:
     *
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * Return the minimum number of candies you need to have to distribute the candies to the children.
     *
     *
     *
     * Example 1:
     *
     * Input: ratings = [1,0,2]
     * Output: 5
     * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
     * Example 2:
     *
     * Input: ratings = [1,2,2]
     * Output: 4
     * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
     * The third child gets 1 candy because it satisfies the above two conditions.
     */
    fun candy(ratings: IntArray): Int {
        val n = ratings.size
        val left = IntArray(n) { 1 }
        val right = IntArray(n) { 1 }
        for (i in 1 until n) {
            if (ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1
        }
        for (i in n - 2 downTo 0) {
            if (ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1
        }
        var ans = 0
        for (i in ratings.indices) {
            ans += maxOf(left[i], right[i])
        }
//        return ans

        //using slope dry to understand
        var slope = 0
        var prevSlope = 0
        var candies = 0
        for (i in 1 until n) {
            if (ratings[i] > ratings[i - 1]) slope++
            else if (ratings[i] < ratings[i - 1]) slope--
            else slope = 0
            if (slope == 0 || (prevSlope > 0 && slope <= 0) || (prevSlope < 0 && slope >= 0)) {
                candies += (1 + slope) * (slope.absoluteValue + 1) / 2
                if (slope.absoluteValue >= prevSlope.absoluteValue)
                    candies += slope.absoluteValue - prevSlope.absoluteValue + 1
                slope = 0
            }
            prevSlope = slope
        }

        return candies + n
    }
}