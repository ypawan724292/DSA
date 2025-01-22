package pointers

class Medium {

    /**
     * 3. Longest Substring Without Repeating Characters
     * Solved
     * Medium
     * Topics
     * Companies
     * Hint
     * Given a string s, find the length of the longest
     * substring
     *  without repeating characters.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     *
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     *
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */
    fun longestSubstringWithRepeatingCharacters(s: String): Int {
        // TC : O(2n)
        val n = s.length
        var ans = 0
        val set = mutableSetOf<Char>()
        var j = 0
        var i = 0
        while (j < n) {
            while (s[j] in set) {
                set.remove(s[i])
                i++
            }
            set.add(s[j])
            j++
            ans = maxOf(ans, j - i)
        }
//        return ans

        // TC : O(n)
        //using hashmap to store the index of the character
        val map = mutableMapOf<Char, Int>()
        var res = 0
        var l = 0
        var r = 0
        while (r < s.length) {
            if (map.containsKey(s[r])) {
                // if the character is already in the map, then we need to move the left pointer to the right
                // maxOf is used to avoid the case where the left pointer is moved to the left
                l = maxOf(l, map[s[r]]!!)
            }
            res = maxOf(res, r - l + 1)
            map[s[r]] = r + 1
            r++
        }

        return res
    }

    /**
     * Best Time to Buy and Sell Stock
     *  Example 1:
     *  Input: prices = [7,1,5,3,6,4]
     *  Output: 5
     */
    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        var j = 1
        var i = 0
        while (j < prices.size) {
            if (prices[j] > prices[i]) {
                maxProfit = maxOf(maxProfit, prices[j] - prices[i])
            } else {
                i++
            }
            j++
        }
        return maxProfit
    }

    /**
     * 1004. Max Consecutive Ones III
     * Medium
     * Topics
     * Companies
     * Hint
     * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
     * Output: 6
     * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
     * Example 2:
     *
     * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
     * Output: 10
     * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
     */
    fun maxOnesWithKFlips(arr: IntArray, k: Int): Int {
        var count = 0

        var res = 0
        var j = 0
        var i = 0

        while (j < arr.size) {

            if (arr[j] == 0) count++

            while (count > k) {
                if (arr[i] == 0) count--
                i++
            }

            if (count <= k)
                res = maxOf(res, j - i + 1)
            j++
        }

//        return res

        //optimized
        var l = 0
        var r = 0
        var zeros = 0
        while (l < arr.size) {
            if (arr[r] == 0) zeros++

            if (zeros > k) {
                if (arr[l] == 0) zeros--
                l++
            }

            // we will update the result only if the number of zeros is less than or equal to k
            if (zeros <= k)
                res = maxOf(res, r - l + 1)
            r++
        }

        return res
    }


    /**
     * Fruit Into Baskets
     * Difficulty: MediumAccuracy: 47.98%Submissions: 70K+Points: 4
     * You are visiting a farm that has a single row of fruit trees arranged from left to right.
     * The trees are represented by an integer array of arr[], where arr[i]  is the type of fruit the ith tree produces.
     * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow :
     *
     * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
     * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right.
     * The picked fruits must fit in one of the baskets.
     * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
     *
     * Given the integer array of fruits, return the maximum number of fruits you can pick.
     *
     * Examples:
     *
     * Input: arr[]= [2, 1, 2]
     * Output: 3
     * Explanation: We can pick one fruit from all three trees. Please note that the type of fruits is same in the 1st and 3rd baskets.
     * Input: arr[] = [3, 1, 2, 2, 2, 2]
     * Output: 5
     * Explanation: It's optimal to pick from the last 5 trees. Please note that we do not pick the first basket as we would have to stop at thrid tree which would result in only 2 fruits collected.
     * Expected Time Complexity: O(n).
     * Expected Auxiliary Space: O(1).
     *
     * Constraints:
     * 1 ≤ arr.size() ≤ 105
     * 1 ≤ arr[i] <=n
     *
     */
    fun maxFruits(arr: IntArray): Int {
        //

        var maxFruits = 0
        val fruitCount = mutableMapOf<Int, Int>()
        var left = 0
        var right = 0

        while (right < arr.size) {
            fruitCount[arr[right]] = fruitCount.getOrDefault(arr[right], 0) + 1

            while (fruitCount.size > 2) {
                fruitCount[arr[left]] = fruitCount[arr[left]]!! - 1
                if (fruitCount[arr[left]] == 0) {
                    fruitCount.remove(arr[left])
                }
                left++
            }

            if (fruitCount.size <= 2)
                maxFruits = maxOf(maxFruits, right - left + 1)

            right++
        }

//        return maxFruits

        //optimized
        var res = 0
        var l = 0
        var r = 0
        val map = mutableMapOf<Int, Int>()

        while (r < arr.size) {
            map[arr[r]] = map.getOrDefault(arr[r], 0) + 1

            if (map.size > 2) {
                map[arr[l]] = map[arr[l]]!! - 1
                if (map[arr[l]] == 0) map.remove(arr[l])
                l++
            }

            if (map.size <= 2) {
                res = maxOf(res, r - l + 1)
            }
            r++
        }

        return res
    }


    /**
     *
     * Code
     * Testcase
     * 424. Longest Repeating Character Replacement
     * Solved
     * Medium
     * Topics
     * Companies
     * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
     *
     * Return the length of the longest substring containing the same letter you can get after performing the above operations.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "ABAB", k = 2
     * Output: 4
     * Explanation: Replace the two 'A's with two 'B's or vice versa.
     * Example 2:
     *
     * Input: s = "AABABBA", k = 1
     * Output: 4
     * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
     * The substring "BBBB" has the longest repeating letters, which is 4.
     * There may exists other ways to achieve this answer too.
     */
    fun longestRepeatingCharacterReplacement(s: String, k: Int): Int {

        /*
        Intuition to solve this problem is to use the sliding window technique.
        We can len - maxF < k
        len is the length of the window
        maxF is the frequency of the most frequent character in the window
        k is the number of changes we can make
         */


        // navie appraoch
        var res = 0
        for (i in s.indices) {
            var maxCount = 0
            val map = mutableMapOf<Char, Int>()
            for (j in i until s.length) {
                map[s[j]] = map.getOrDefault(s[j], 0) + 1
                maxCount = maxOf(maxCount, map[s[j]]!!)
                val count = j - i + 1 - maxCount
                if (count <= k) {
                    res = maxOf(res, j - i + 1)
                } else {
                    break
                }
            }
        }

        // using shringking window
        var l = 0
        var r = 0
        var maxCount = 0
        val map = mutableMapOf<Char, Int>()
        while (r < s.length) {
            map[s[r]] = map.getOrDefault(s[r], 0) + 1
            maxCount = maxOf(maxCount, map[s[r]]!!)

            while (r - l + 1 - maxCount > k) {
                map[s[l]] = map[s[l]]!! - 1
                l++
            }

            res = maxOf(res, r - l + 1)
            r++
        }


        //optimized


        while (r < s.length) {
            map[s[r]] = map.getOrDefault(s[r], 0) + 1
            maxCount = maxOf(maxCount, map[s[r]]!!)

            if (r - l + 1 - maxCount > k) {
                map[s[l]] = map[s[l]]!! - 1
                l++
            }

            if (r - l + 1 - maxCount <= k) {
                res = maxOf(res, r - l + 1)
            }
            r++
        }

        return res
    }

    /**
     * 930. Binary Subarrays With Sum
     * Solved
     * Medium
     * Topics
     * Companies
     * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
     *
     * A subarray is a contiguous part of the array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,0,1,0,1], goal = 2
     * Output: 4
     * Explanation: The 4 subarrays are bolded and underlined below:
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * Example 2:
     *
     * Input: nums = [0,0,0,0,0], goal = 0
     * Output: 15
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 3 * 104
     * nums[i] is either 0 or 1.
     * 0 <= goal <= nums.length
     */
    fun numSubarraysWithSum(nums: IntArray, goal: Int): Int {
       // Refer Arrays and Hash Problems File

        /*
        Intution we will find max subarray with sum at most k and max subarray with sum at most k - 1,
         this use the sliding window and then subtract the two to get the result
         if integers are positive then we can use the sliding window approach
         */
        fun sumWithAtMostK(nums: IntArray, k: Int): Int {
            if (k < 0) return 0
            var res = 0
            var l = 0
            var r = 0
            var sum = 0
            while (r < nums.size) {
                sum += nums[r]
                while (sum > k) {
                    sum -= nums[l]
                    l++
                }
                res += r - l + 1
                r++
            }
            return res
        }

        return sumWithAtMostK(nums, goal) - sumWithAtMostK(nums, goal - 1)
    }


    /**
     *
     * Code
     * Testcase
     * Testcase
     * Test Result
     * 1248. Count Number of Nice Subarrays
     * Solved
     * Medium
     * Topics
     * Companies
     * Hint
     * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
     *
     * Return the number of nice sub-arrays.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,2,1,1], k = 3
     * Output: 2
     * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
     * Example 2:
     *
     * Input: nums = [2,4,6], k = 1
     * Output: 0
     * Explanation: There are no odd numbers in the array.
     * Example 3:
     *
     * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
     * Output: 16
     */
    fun numberOfNiceSubarrays(nums: IntArray, k: Int): Int {
        // using prefix sum
        val prefix = IntArray(nums.size + 1)
        var count = 0
        var res = 0
        prefix[0] = 1
        for (num in nums) {
            count += num and 1
            res += if (count >= k) prefix[count - k] else 0
            prefix[count]++
        }

        // using sliding window

        fun f(nums: IntArray, k: Int): Int {
            if (k < 0) return 0
            var res = 0
            var count = 0
            var l = 0
            var r = 0
            while (r < nums.size) {
                if (nums[r] % 2 == 1) count++

                while (count > k) {
                    if (nums[l] % 2 == 1) count--
                    l++
                }

                res += r - l + 1
                r++
            }

            return res
        }

        return f(nums, k) - f(nums, k - 1)
    }

    /**
     * 1358. Number of Substrings Containing All Three Characters
     * Medium
     * Topics
     * Companies
     * Hint
     * Given a string s consisting only of characters a, b and c.
     *
     * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "abcabc"
     * Output: 10
     * Explanation: The substrings containing at least one occurrence of the characters a, b and c are
     * "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
     * Example 2:
     *
     * Input: s = "aaacb"
     * Output: 3
     * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
     * Example 3:
     *
     * Input: s = "abc"
     * Output: 1
     */
    fun numberOfSubstrings(s: String): Int {
        // using sliding window
        var res = 0
        val map = mutableMapOf<Char, Int>()
        var l = 0
        var r = 0
        while (r < s.length) {
            map[s[r]] = map.getOrDefault(s[r], 0) + 1
            while (map.size == 3) {
                map[s[l]] = map[s[l]]!! - 1
                if (map[s[l]] == 0) map.remove(s[l])
                l++
            }
            res += l
            r++
        }

//        return res

        //optimized

        while (r < s.length) {
            map[s[r]] = r
            if (map.size == 3) {
                res += 1 + minOf(map['a']!!, minOf(map['b']!!, map['c']!!))
            }
            r++
        }

        return res
    }

    /**
     * 1423. Maximum Points You Can Obtain from Cards
     * Medium
     * Topics
     * Companies
     * Hint
     * There are several cards arranged in a row, and each card has an associated number of points.
     * The points are given in the integer array cardPoints.
     *
     * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
     *
     * Your score is the sum of the points of the cards you have taken.
     *
     * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
     *
     *
     *
     * Example 1:
     *
     * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
     * Output: 12
     * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score.
     * The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
     * Example 2:
     *
     * Input: cardPoints = [2,2,2], k = 2
     * Output: 4
     * Explanation: Regardless of which two cards you take, your score will always be 4.
     * Example 3:
     *
     * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
     * Output: 55
     * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
     */
    fun maxScore(cardPoints: IntArray, k: Int): Int {
        //[11,49,100,20,86,29,72] 232 , 4
        var res = 0
        var lSum = 0
        var rSum = 0

        for (i in 0 until k) {
            lSum += cardPoints[i]
        }
        res = lSum

        var l = k - 1
        var r = cardPoints.lastIndex
        while(l >= 0) {
            rSum += cardPoints[r]
            lSum -= cardPoints[l]
            res = maxOf(res, lSum + rSum)
            l--
            r--
        }

        return res
    }
}

fun main() {
    val obj = Medium()
//    println(obj.maxOnesWithKFlips(intArrayOf(0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1), 3))
//    println(obj.maxFruits(intArrayOf(2, 2, 2, 2, 3, 4, 4, 4)))
    println(obj.maxScore(intArrayOf(9, 7, 7, 9, 7, 7, 9), 7))
}
