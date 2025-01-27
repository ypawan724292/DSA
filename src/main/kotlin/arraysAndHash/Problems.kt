package arraysAndHash

import annotations.Revise

class Problems {


    /**
     * 2 Sum Problem
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement)) {
                return intArrayOf(map[complement]!!, i)
            }
            map[nums[i]] = i
        }
        return intArrayOf(-1, -1)
    }

    /**
     * Sort array of 1s, 2s and 0s
     *
     * Example:
     * Input: [0, 1, 2, 0, 1, 2]
     * Output: [0, 0, 1, 1, 2, 2]
     */
    fun sortColors(nums: IntArray) {
        var left = 0
        var i = 0
        var right = nums.size - 1
        while (i < nums.size) {
            when {
                nums[i] == 0 -> {
                    nums[i] = nums[left]
                    nums[left] = 0
                    left++
                    i++
                }

                nums[i] == 2 -> {
                    nums[i] = nums[right]
                    nums[right] = 2
                    right--
                }

                else -> i++
            }
        }
    }

    /**
     * Moore's Voting Algorithm
     *
     * Given an array nums of size n, return the majority element.
     *
     * The majority element is the element that appears more than ⌊n / 2⌋ times.
     * You may assume that the majority element always exists in the array.
     *
     *
     * Example 1:
     *
     * Input: nums = [3,2,3]
     * Output: 3
     * Example 2:
     *
     * Input: nums = [2,2,1,1,1,2,2]
     * Output: 2
     *
     */
    fun majorityElement(nums: IntArray): Int {
        var candidate = 0
        var count = 0
        for (num in nums) {
            if (candidate == num) {
                count++
            } else if (count == 0) {
                candidate = num
                count = 1
            } else {
                count--
            }
        }

        count = 0
        for (num in nums) {
            if (num == candidate) {
                count++
            }
        }
        return if (count > nums.size / 2) candidate else -1
    }


    /**
     * Kadane's Algorithm
     * It is used the max/min sum of subarray in a given array
     *
     * Example:
     * Input: [-2,1,-3,4,-1,2,1,-5,4]
     */
    fun maxSum(arr: IntArray): Int {
        var res = Int.MIN_VALUE
        var curSum = 0
        for (num in arr) {
            curSum = maxOf(num, curSum + num)
            res = maxOf(res, curSum)
        }
        return res
    }


    /**
     * Print the max/min sum of subarray in a given array
     *
     * Input: [-2,1,-3,4,-1,2,1,-5,4]
     */
    fun maxSumSubArray(arr: IntArray): Int {
        var res = Int.MIN_VALUE
        var curSum = 0
        var start = 0
        var end = 0
        var s = 0
        for (i in arr.indices) {
            curSum += arr[i]
            if (curSum < arr[i]) {
                curSum = arr[i]
                s = i
            }
            if (curSum > res) {
                res = curSum
                start = s
                end = i
            }
        }
        println("Start: $start, End: $end")
        return res
    }

    /**
     * You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.
     *
     * You should return the array of nums such that the the array follows the given conditions:
     *
     * Every consecutive pair of integers have opposite signs.
     * For all integers with the same sign, the order in which they were present in nums is preserved.
     * The rearranged array begins with a positive integer.
     * Return the modified array after rearranging the elements to satisfy the aforementioned conditions.
     * Example 1:
     * Input: nums = [1,-3,2,-4]
     * Output: [1,2,-3,-4]
     */
    fun rearrangeArray(nums: IntArray): IntArray {
        val n = nums.size
        val res = IntArray(n)
        var i = 0
        var j = 1
        for (num in nums) {
            if (num < 0) {
                res[i] = num
                i += 2
            } else {
                res[j] = num
                j += 2
            }
        }
        return res
    }

    /**
     * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
     *
     * For example, for arr = [1,2,3], the following are all the permutations of
     * arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
     * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
     * More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
     *
     * For example, the next permutation of arr = [1,2,3] is [1,3,2].
     * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
     * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
     * Given an array of integers nums, find the next permutation of nums.
     *
     * The replacement must be in place and use only constant extra memory.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3]
     * Output: [1,3,2]
     * Example 2:
     *
     * Input: nums = [3,2,1]
     * Output: [1,2,3]
     * Example 3:
     *
     * Input: nums = [1,1,5]
     * Output: [1,5,1]
     *
     *Example 4: Bigger input
     * Input: nums = [1,2,3,4,5,6,7,8,9,10]
     * Output: [1,2,3,4,5,6,7,8,10,9]
     *
     */
    fun nextPermutation(nums: IntArray) {
        fun swap(i: Int, j: Int) {
            val temp = nums[i]
            nums[i] = nums[j]
            nums[j] = temp
        }

        fun reverse(start: Int) {
            var i = start
            var j = nums.size - 1
            while (i < j) {
                swap(i, j)
                i++
                j--
            }
        }

        var ind = -1
        val n = nums.size

        for (i in n - 2 downTo 0) {
            if (nums[i] < nums[i + 1]) {
                ind = i
                break
            }
        }

        if (ind == -1) {
            reverse(0)
            return
        }

        for (i in n - 1 downTo ind + 1) {
            if (nums[i] > nums[ind]) {
                swap(i, ind)
                break
            }
        }

        reverse(ind + 1)
    }

    /**
     * Array Leaders
     * You are given an array arr of positive integers. Your task is to find all the leaders in the array.
     * An element is considered a leader if it is greater than or equal to all elements to its right. The rightmost element is always a leader.
     *
     * Examples:
     *
     * Input: arr = [16, 17, 4, 3, 5, 2]
     * Output: [17, 5, 2]
     * Explanation: Note that there is nothing greater on the right side of 17, 5 and, 2.
     */
    fun leadersInArray(arr: IntArray): List<Int> {
        val n = arr.size
        val res = mutableListOf<Int>()
        var max = arr[n - 1]
        res.add(max)
        for (i in n - 2 downTo 0) {
            if (arr[i] >= max) {
                max = arr[i]
                res.add(max)
            }
        }
        return res.reversed()
    }

    /**
     * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
     *
     * You must write an algorithm that runs in O(n) time.
     *
     * Example 1:
     *
     * Input: nums = [100,4,200,1,3,2]
     * Output: 4
     * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     * Example 2:
     *
     * Input: nums = [0,3,7,2,5,8,4,6,0,1]
     * Output: 9
     */
    fun longestConsecutive(nums: IntArray): Int {
        val set = nums.toHashSet()
        var max = 0
        /**
         * Checking for the start of the sequence ensures that we only count each sequence once.
         * If we don't check for the start, we might count the same sequence multiple times, leading to incorrect results.
         */
        for (num in nums) {
            if (!set.contains(num - 1)) { // Check if it's the start of a sequence
                var curr = num
                var currMax = 1
                while (set.contains(curr + 1)) {
                    curr++
                    currMax++
                }
                max = maxOf(max, currMax)
            }
        }
        return max
    }

    /**
     * 560. Subarray Sum Equals K
     * Solved
     * Medium
     * Topics
     * Companies
     * Hint
     * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,1], k = 2
     * Output: 2
     * Example 2:
     *
     * Input: nums = [1,2,3], k = 3
     * Output: 2
     *
     * Input: nums = [1,2,3,4,5], k = 10
     * Output: 1
     *
     * // +ve and -ve
     * Input: nums =  [3, 4, 7, 2, -3, 1, 4, 2], k = 7
     * Output: 4
     */
    @Revise
    fun subarraySum(nums: IntArray, k: Int): Int {
        val map = mutableMapOf<Int, Int>()
        var count = 0
        var sum = 0
        map[0] = 1 // Initialize to handle subarrays starting from the beginning

        for (num in nums) {
            sum += num // Update cumulative sum

            if (map.containsKey(sum - k)) { // Check if there is a subarray with sum k
                count += map[sum - k]!!
            }

            map[sum] = map.getOrDefault(sum, 0) + 1 // Update the frequency of the cumulative sum
        }

        return count
    }


    /**
     * Longest Sub-Array with Sum K
     * Difficulty: MediumAccuracy: 24.64%Submissions: 494K+Points: 4
     * Given an array arr[] containing integers and an integer k, your task is to
     * find the length of the longest subarray where the sum of its elements is equal to the given value k.
     * It is guaranteed that a valid subarray exists.
     *
     * Examples:
     *
     * Input: arr[] = [10, 5, 2, 7, 1, 9], k = 15
     * Output: 4
     * Explanation: The subarray [5, 2, 7, 1] has a sum of 15 and length 4.
     * Input: arr[] = [-1, 2, -3], k = -2
     * Output: 3
     * Explanation: The subarray [-1, 2, -3] has a sum of -2 and length 3.
     * Input: arr[] = [1, -1, 5, -2, 3], k = 3
     * Output: 4
     * Explanation: The subarray [1, -1, 5, -2] has a sum of 3 and a length 4.
     */
    @Revise
    fun longestSubArrayWithSumK(arr: IntArray, k: Int): Int {
        // if negative numbers are present
        val map = mutableMapOf<Int, Int>()
        var sum = 0
        var max = 0
        for (i in arr.indices) {
            sum += arr[i]
            if (sum == k) {
                max = i + 1
            } else if (map.containsKey(sum - k)) {
                max = maxOf(max, i - map[sum - k]!!)
            }
            if (!map.containsKey(sum)) {
                map[sum] = i
            }
        }

//        return max

        //if only positive numbers
        var left = 0
        var right = 0
        sum = 0
        max = 0
        while (right < arr.size) {
            sum += arr[right]
            while (sum > k) {
                sum -= arr[left]
                left++
            }
            if (sum == k) {
                max = maxOf(max, right - left + 1)
            }
            right++
        }
        return max
    }

    /**
     *  Given the array and queries where each query consists of two integers left and right,
     *  return the sum of the subarray sum of the given range.
     *
     * Example:
     * Input: arr = [1, 2, 3, 4, 5], queries = [[1, 3], [0, 1], [2, 4]]
     * Output: [9, 3, 12]
     */
    fun rangeSumQuery(arr: IntArray, queries: Array<IntArray>): IntArray {
        val n = arr.size
        val prefixSum = IntArray(n)
        prefixSum[0] = arr[0]

        for (i in 1 until n) {
            prefixSum[i] = prefixSum[i - 1] + arr[i]
        }

        val results = IntArray(queries.size)
        for (i in queries.indices) {
            val query = queries[i]
            val left = query[0]
            val right = query[1]

            results[i] = if (left == 0) {
                prefixSum[right]
            } else {
                prefixSum[right] - prefixSum[left - 1]
            }
        }

        return results
    }

    /**
     * Problem: Find an index in an array such that the sum of elements to the left of the index is equal to the sum
     * of elements to the right of the index.
     *
     * Example:
     * Input: [-7, 1, 5, 2, -4, 3, 0]
     * Output: 3
     */
    fun findEquilibriumIndex(arr: IntArray): Int {
        val n = arr.size
        val totalSum = arr.sum()
        var leftSum = 0

        for (i in 0 until n) {
            val rightSum = totalSum - leftSum - arr[i]
            if (leftSum == rightSum) {
                return i
            }
            leftSum += arr[i]
        }

        return -1 // If no equilibrium index is found
    }
}