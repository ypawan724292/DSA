package dsa.pointers

import annotations.Important


class Hard {


    /**
     * In this problem, we are given a string s and an integer k.
     * Our task is to find the length of the longest substring (which is a contiguous block of characters in the string)
     * within s that contains at most k distinct characters.
     *
     * A distinct character means that no matter how many times the character appears in the substring,
     * it is counted only once. For example, if k = 2, the substring "aabbc" has 3 distinct characters ('a', 'b', and 'c'),
     * thus, it does not meet the requirement.
     *
     * The goal here is to achieve this while maximizing the length of the substring.
     * A substring could range from containing a single character up to the length of the entire string,
     * if k is sufficiently large to cover all distinct characters in the string s.
     *
     * Example 1:
     * Input: s = "eceba", k = 2
     * Output: 3
     */
    @Important
    fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
        val n = s.length
        if (n * k == 0) return 0

        var left = 0
        var right = 0
        var maxLen = 1
        val charMap = mutableMapOf<Char, Int>()

        //using sliding window approach with while

        while (right < n) {
            charMap[s[right]] = charMap.getOrDefault(s[right], 0) + 1

            while (charMap.size > k) {
                charMap[s[left]] = charMap[s[left]]!! - 1
                if (charMap[s[left]] == 0) {
                    charMap.remove(s[left])
                }
                left++
            }
            maxLen = maxOf(maxLen, right - left + 1)
            right++
        }

        //optimized approach


        while (right < n) {
            charMap[s[right]] = right
            if (charMap.size > k) {
                val delIdx = charMap.values.min()
                charMap.remove(s[delIdx])
                left = delIdx + 1
            }
            maxLen = maxOf(maxLen, right - left + 1)
            right++
        }

        return maxLen
    }


    /**
     * 992. Subarrays with K Different Integers
     * Hard
     * Topics
     * Companies
     * Hint
     * Given an integer array nums and an integer k, return the number of good subarrays of nums.
     *
     * A good array is an array where the number of different integers in that array is exactly k.
     *
     * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
     * A subarray is a contiguous part of an array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,1,2,3], k = 2
     * Output: 7
     * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
     * Example 2:
     *
     * Input: nums = [1,2,1,3,4], k = 3
     * Output: 3
     * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
     */
    fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
        // using sliding window
        fun atMostK(nums: IntArray, k: Int): Int {
            var res = 0
            var l = 0
            var r = 0
            val map = mutableMapOf<Int, Int>()
            while (r < nums.size) {
                map[nums[r]] = map.getOrDefault(nums[r], 0) + 1
                while (map.size > k) {
                    map[nums[l]] = map[nums[l]]!! - 1
                    if (map[nums[l]] == 0) map.remove(nums[l])
                    l++
                }
                res += r - l + 1
                r++
            }
            return res
        }

        return atMostK(nums, k) - atMostK(nums, k - 1)
    }


    /**
     * 76. Minimum Window Substring
     * Solved
     * Hard
     * Topics
     * Companies
     * Hint
     * Given two strings s and t of lengths m and n respectively, return the minimum window
     * substring
     *  of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
     *
     * The testcases will be generated such that the answer is unique.
     *
     *
     * Example 1:
     *
     * Input: s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     * Example 2:
     *
     * Input: s = "a", t = "a"
     * Output: "a"
     * Explanation: The entire string s is the minimum window.
     * Example 3:
     *
     * Input: s = "a", t = "aa"
     * Output: ""
     * Explanation: Both 'a's from t must be included in the window.
     * Since the largest window of s only has one 'a', return empty string.
     *
     */
    fun minWindow(s: String, t: String): String {
        /**
         * s ="cabwefgewcwaefgcf"
         * t ="cae"
         *
         * Use Testcase
         * Output = "aefg"
         *
         */
        val sMap = mutableMapOf<Char, Int>()
        val tMap = mutableMapOf<Char, Int>()

        for (c in t) {
            tMap[c] = tMap.getOrDefault(c, 0) + 1
        }

        var l = 0
        var r = 0
        var minLen = Int.MAX_VALUE
        var minLeft = 0
        var count = 0

        while (r < s.length) {
            sMap[s[r]] = sMap.getOrDefault(s[r], 0) + 1
            if (tMap.containsKey(s[r]) && sMap[s[r]] == tMap[s[r]]) {
                count++
            }

            while (count == tMap.size) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1
                    minLeft = l
                }
                sMap[s[l]] = sMap[s[l]]!! - 1
                if (tMap.containsKey(s[l]) && sMap[s[l]]!! < tMap[s[l]]!!) {
                    count--
                }
                l++
            }
            r++
        }

        return if (minLen == Int.MAX_VALUE) "" else s.substring(minLeft, minLeft + minLen)
    }


    /**
     * 3-Sum Problem
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = mutableListOf<List<Int>>()
        for (i in 0 until nums.size - 2) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                var left = i + 1
                var right = nums.lastIndex
                while (left < right) {
                    val sum = nums[i] + nums[left] + nums[right]
                    when {
                        sum == 0 -> {
                            result.add(listOf(nums[i], nums[left], nums[right]))
                            while (left < right && nums[left] == nums[left + 1]) left++
                            while (left < right && nums[right] == nums[right - 1]) right--
                            left++
                            right--
                        }

                        sum < 0 -> left++
                        else -> right--
                    }
                }
            }
        }
        return result
    }

    /**
     * 4-Sum Problem
     */
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()
        val result = mutableListOf<List<Int>>()
        for (i in 0 until nums.size - 3) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (j in i + 1 until nums.size - 2) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        var left = j + 1
                        var right = nums.lastIndex
                        while (left < right) {
                            val sum = nums[i] + nums[j] + nums[left] + nums[right]
                            when {
                                sum == target -> {
                                    result.add(listOf(nums[i], nums[j], nums[left], nums[right]))
                                    while (left < right && nums[left] == nums[left + 1]) left++
                                    while (left < right && nums[right] == nums[right - 1]) right--
                                    left++
                                    right--
                                }

                                sum < target -> left++
                                else -> right--
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
     *
     * Example 1:
     *
     *
     * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
     * Example 2:
     *
     * Input: height = [4,2,0,3,2,5]
     * Output: 9
     */
    fun trap(height: IntArray): Int {
        // total += minOf(leftMax[i], rightMax[i]) - height[i]
        // TC : O(n) SC : O(n)
        val n = height.size
        val leftMax = IntArray(n)
        val rightMax = IntArray(n)

        leftMax[0] = height[0]
        rightMax[n - 1] = height[n - 1]

        for (i in 1 until n) {
            leftMax[i] = maxOf(leftMax[i - 1], height[i])
        }

        for (i in n - 2 downTo 0) {
            rightMax[i] = maxOf(rightMax[i + 1], height[i])
        }

        var res = 0

        var left = -1

        for (i in 0 until n) {
            left = maxOf(left, height[i])
            res += minOf(left, rightMax[i]) - height[i]
        }

//        return res


        //approach 2 : TC : O(n) SC : O(1)
        var leftMax2 = 0
        var rightMax2 = 0

        var left2 = 0
        var right2 = n - 1
        var res2 = 0

        while (left2 < right2) {
            if (height[left2] < height[right2]) {
                if (height[left2] >= leftMax2) {
                    leftMax2 = height[left2]
                } else {
                    res2 += leftMax2 - height[left2]
                }
                left2++
            } else {
                if (height[right2] >= rightMax2) {
                    rightMax2 = height[right2]
                } else {
                    res2 += rightMax2 - height[right2]
                }
                right2--
            }
        }

        return res2
    }

}

fun main() {
    val obj = Hard()
    println(obj.minWindow("ADOBECODEBANC", "ABC"))
}
