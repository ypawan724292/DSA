package dsa.strings

import java.util.*

class Problems {

    /**
     * 451. Sort Characters By Frequency
     * Medium
     * Topics
     * Companies
     * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
     *
     * Return the sorted string. If there are multiple answers, return any of them.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "tree"
     * Output: "eert"
     * Explanation: 'e' appears twice while 'r' and 't' both appear once.
     * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
     * Example 2:
     *
     * Input: s = "cccaaa"
     * Output: "aaaccc"
     * Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
     * Note that "cacaca" is incorrect, as the same characters must be together.
     * Example 3:
     *
     * Input: s = "Aabb"
     * Output: "bbAa"
     * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
     * Note that 'A' and 'a' are treated as two different characters.
     */
    fun frequencySort(s: String): String {
        //using map and priority queue
        val map = mutableMapOf<Char, Int>()
        for (ch in s) {
            map[ch] = map.getOrDefault(ch, 0) + 1
        }
        val pq = PriorityQueue<Char> { a, b -> map[b]!! - map[a]!! }

        pq.addAll(map.keys)

        var sb = ""
        while (pq.isNotEmpty()) {
            val ch = pq.poll()
            for (i in 0 until map[ch]!!) {
                sb += ch
            }
        }
        return sb
    }

    /**
     * 1614. Maximum Nesting Depth of the Parentheses
     * Solved
     * Easy
     * Topics
     * Companies
     * Hint
     * Given a valid parentheses string s, return the nesting depth of s. The nesting depth is the maximum number of nested parentheses.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "(1+(2*3)+((8)/4))+1"
     *
     * Output: 3
     *
     * Explanation:
     *
     * Digit 8 is inside of 3 nested parentheses in the string.
     *
     * Example 2:
     *
     * Input: s = "(1)+((2))+(((3)))"
     *
     * Output: 3
     *
     * Explanation:
     *
     * Digit 3 is inside of 3 nested parentheses in the string.
     *
     * Example 3:
     *
     * Input: s = "()(())((()()))"
     *
     * Output: 3
     *
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
     * It is guaranteed that parentheses expression s is a VPS.
     */
    fun maxDepth(s: String): Int {
        var max = 0
        var count = 0
        for (ch in s) {
            if (ch == '(') {
                count++
                max = maxOf(max, count)
            } else if (ch == ')') {
                count--
            }
        }
        return max
    }

    /**
     * 13. Roman to Integer
     * Solved
     * Easy
     * Topics
     * Companies
     * Hint
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
     *
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
     *
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * Given a roman numeral, convert it to an integer.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "III"
     * Output: 3
     * Explanation: III = 3.
     * Example 2:
     *
     * Input: s = "LVIII"
     * Output: 58
     * Explanation: L = 50, V= 5, III = 3.
     * Example 3:
     *
     * Input: s = "MCMXCIV"
     * Output: 1994
     * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 15
     * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
     * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
     */
    fun romanToInt(s: String): Int {
        val map = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )
        var res = 0
        var prev = 0
        for (i in s.length - 1 downTo 0) {
            val num = map[s[i]]!!
            if (num < prev) {
                res -= num
            } else {
                res += num
            }
            prev = num
        }

        return res
    }

    /**
     * 8. String to Integer (atoi)
     *
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
     *
     * The algorithm for myAtoi(string s) is as follows:
     *
     * Whitespace: Ignore any leading whitespace (" ").
     * Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
     * Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached.
     * If no digits were read, then the result is 0.
     * Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1],
     * then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
     * Return the integer as the final result.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "42"
     *
     * Output: 42
     *
     * Explanation:
     *
     * The underlined characters are what is read in and the caret is the current reader position.
     * Step 1: "42" (no characters read because there is no leading whitespace)
     *          ^
     * Step 2: "42" (no characters read because there is neither a '-' nor '+')
     *          ^
     * Step 3: "42" ("42" is read in)
     *            ^
     * Example 2:
     *
     * Input: s = " -042"
     *
     * Output: -42
     *
     * Explanation:
     *
     * Step 1: "   -042" (leading whitespace is read and ignored)
     *             ^
     * Step 2: "   -042" ('-' is read, so the result should be negative)
     *              ^
     * Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
     *                ^
     * Example 3:
     *
     * Input: s = "1337c0d3"
     *
     * Output: 1337
     *
     * Explanation:
     *
     * Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
     *          ^
     * Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
     *          ^
     * Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
     *              ^
     * Example 4:
     *
     * Input: s = "0-1"
     *
     * Output: 0
     *
     * Explanation:
     *
     * Step 1: "0-1" (no characters read because there is no leading whitespace)
     *          ^
     * Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
     *          ^
     * Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
     *           ^
     * Example 5:
     *
     * Input: s = "words and 987"
     *
     * Output: 0
     *
     * Explanation:
     *
     * Reading stops at the first non-digit character 'w'.
     *
     *
     *
     * Constraints:
     *
     * 0 <= s.length <= 200
     * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
     */
    fun atoi(s: String): Int {
        var i = 0
        var sign = 1
        var res = 0
        while (i < s.length && s[i] == ' ') {
            i++
        }
        if (i < s.length && (s[i] == '+' || s[i] == '-')) {
            sign = if (s[i] == '-') -1 else 1
            i++
        }
        while (i < s.length && s[i].isDigit()) {
            val digit = s[i] - '0'
            // this is to check if the number is out of range && Int.MAX_VALUE is 2147483647.
            if (res > Int.MAX_VALUE / 10 || (res == Int.MAX_VALUE / 10 && digit > 7)) {
                return if (sign == 1) Int.MAX_VALUE else Int.MIN_VALUE
            }
            res = res * 10 + digit
            i++
        }
        return res * sign
    }

    /**
     * Count number of substrings
     * Given a string of lowercase alphabets, count all possible substrings (not necessarily distinct) that have exactly k distinct characters.
     *
     * Example 1:
     *
     * Input: S = "aba", K = 2
     * Output:3
     * Explanation:The substrings are: "ab", "ba" and "aba".
     *
     * Input: S = "abaaca", K = 1
     * Output: 7
     * Explanation: The substrings are: "a", "b", "a", "aa", "a", "c", "a".
     *
     * Constraints:
     * 1 ≤ |S| ≤ 106
     * 1 ≤ K ≤ 26
     */
    fun countSubstrings(s: String, k: Int): Int {

        fun atMostk(m: Int): Int {
            var res = 0
            val map = mutableMapOf<Char, Int>()
            var left = 0
            var right = 0
            while (right < s.length) {
                map[s[right]] = map.getOrDefault(s[right], 0) + 1
                while (map.size > m) {
                    map[s[left]] = map[s[left]]!! - 1
                    if (map[s[left]] == 0) {
                        map.remove(s[left])
                    }
                    left++
                }
                res += right - left + 1
                right++
            }
            return res
        }
        return atMostk(k) - atMostk(k - 1)
    }

    /**
     * Longest Palindromic Substring
     */
    fun longestPalindrome(s: String): String {

        fun expand(s: String, left: Int, right: Int): String {
            var l = left
            var r = right
            while (l >= 0 && r < s.length && s[l] == s[r]) {
                l--
                r++
            }
            return s.substring(l + 1, r)
        }


        // Time Complexity: O(n^2)
        var res = ""
        for (i in s.indices) {
            val s1 = expand(s, i, i)
            val s2 = expand(s, i, i + 1)
            if (s1.length > res.length) res = s1
            if (s2.length > res.length) res = s2
        }
        return res
    }

    /**
     * 1781. Sum of Beauty of All Substrings
     * Medium
     * Topics
     * Companies
     * Hint
     * The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
     *
     * For example, the beauty of "abaacc" is 3 - 1 = 2.
     * Given a string s, return the sum of beauty of all of its substrings.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "aabcb"
     * Output: 5
     * Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.
     * Example 2:
     *
     * Input: s = "aabcbaa"
     * Output: 17
     */
    fun beautySum(s: String): Int {
        //brute force or freq map
        var res = 0
        for (i in s.indices) {
            val freq = IntArray(26)
            for (j in i until s.length) {
                freq[s[j] - 'a']++
                var max = 0
                var min = Int.MAX_VALUE
                for (k in freq) {
                    if (k > 0) {
                        max = maxOf(max, k)
                        min = minOf(min, k)
                    }
                }
                res += max - min
            }
        }
        return res
    }

    /**
     * Count Vowel Strings in Ranges
     * Medium
     * Topics
     * Companies
     * Hint
     * You are given a 0-indexed array of strings words and a 2D array of integers queries.
     *
     * Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to ri (both inclusive) of words that start and end with a vowel.
     *
     * Return an array ans of size queries.length, where ans[i] is the answer to the ith query.
     *
     * Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.
     *
     *
     *
     * Example 1:
     *
     * Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
     * Output: [2,3,0]
     * Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
     * The answer to the query [0,2] is 2 (strings "aba" and "ece").
     * to query [1,4] is 3 (strings "ece", "aa", "e").
     * to query [1,1] is 0.
     * We return [2,3,0].
     */
    fun countVowelStrings(words: Array<String>, queries: Array<IntArray>): IntArray {
        val vset = mutableSetOf<Char>('a', 'e', 'i', 'o', 'u')

        fun isStartAndEndVowel(s: String): Int {
            return if (vset.contains(s[0]) && vset.contains(s.last())) 1 else 0
        }

        val prefixSum = IntArray(words.size)

        var count = 0
        for (i in words.indices) {
            val s = words[i]
            count += isStartAndEndVowel(s)
            prefixSum[i] = count
        }

        val res = IntArray(queries.size)

        for (i in queries.indices) {

            val (s, e) = queries[i]

            res[i] = prefixSum[e] - if (s > 0) prefixSum[s - 1] else 0

        }

        return res

    }
}