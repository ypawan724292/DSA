package dsa.strings

class Basics {
    /**
     * 1021. Remove Outermost Parentheses
     * Easy
     * Topics
     * Companies
     * Hint
     * A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.
     *
     * For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
     * A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split it into s = A + B, with A and B nonempty valid parentheses strings.
     *
     * Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk, where Pi are primitive valid parentheses strings.
     *
     * Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "(()())(())"
     * Output: "()()()"
     * Explanation:
     * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
     * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
     * Example 2:
     *
     * Input: s = "(()())(())(()(()))"
     * Output: "()()()()(())"
     * Explanation:
     * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
     * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
     * Example 3:
     *
     * Input: s = "()()"
     * Output: ""
     * Explanation:
     * The input string is "()()", with primitive decomposition "()" + "()".
     * After removing outer parentheses of each part, this is "" + "" = "".
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 105
     * s[i] is either '(' or ')'.
     * s is a valid parentheses string.
     */
    fun removeOuterParentheses(s: String): String {
        /*

         */
        val result = StringBuilder()
        var open = 0
        var closed = 0
        for (c in s) {
            if (c == '(') {
                open++
                if (open > 1)
                    result.append(c)
            } else {
                closed++
                if (open == closed) {
                    open = 0
                    closed = 0
                } else {
                    result.append(c)
                }
            }
        }
        return result.toString()
    }

    /**
     * 151. Reverse Words in a String
     * Attempted
     * Medium
     * Topics
     * Companies
     * Given an input string s, reverse the order of the words.
     *
     * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
     *
     * Return a string of the words in reverse order concatenated by a single space.
     *
     * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "the sky is blue"
     * Output: "blue is sky the"
     * Example 2:
     *
     * Input: s = "  hello world  "
     * Output: "world hello"
     * Explanation: Your reversed string should not contain leading or trailing spaces.
     * Example 3:
     *
     * Input: s = "a good   example"
     * Output: "example good a"
     * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
     */
    fun reverseWords(s: String): String {

        //using in built functions
        val words = s.trim().split("\\s+".toRegex())
//        return words.reversed().joinToString(" ")

        // without using in built functions
        var res = ""
        var word = ""
        for (i in s.length - 1 downTo 0) {
            if (s[i] == ' ') {
                if (word.isNotEmpty()) {
                    res += "$word "
                    word = ""
                }
            } else {
                word = "${s[i]}$word"
            }
        }

        if (word.isNotEmpty()) {
            res += word
        }
        return res

    }

    /**
     * 1903. Largest Odd Number in String
     * Easy
     * Topics
     * Companies
     * Hint
     * You are given a string num, representing a large integer.
     * Return the largest-valued odd integer (as a string) that is a non-empty substring of num,
     * or an empty string "" if no odd integer exists.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     *
     *
     * Example 1:
     *
     * Input: num = "52"
     * Output: "5"
     * Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.
     * Example 2:
     *
     * Input: num = "4206"
     * Output: ""
     * Explanation: There are no odd numbers in "4206".
     * Example 3:
     *
     * Input: num = "35427"
     * Output: "35427"
     * Explanation: "35427" is already an odd number.
     *
     *
     * Constraints:
     *
     * 1 <= num.length <= 105
     * num only consists of digits and does not contain any leading zeros.
     */
    fun largestOddNumber(num: String): String {
        var res = ""
        for (i in num.lastIndex downTo 0) {
            val ch = num[i] - '0'
            if (ch % 2 != 0) {
                res = num.substring(0, i + 1)
                break
            }
        }

        return res
    }


    /**
     * 14. Longest Common Prefix
     * Solved
     * Easy
     * Topics
     * Companies
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     * If there is no common prefix, return an empty string "".
     *
     *
     *
     * Example 1:
     *
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     * Example 2:
     *
     * Input: strs = ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     */
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.size == 1) return strs[0]
        var prefix = ""
        val first = strs[0]

        for (i in first.indices) {
            for (s in strs) {
                if (i == s.length || first[i] != s[i]) return prefix
            }
            prefix += first[i]
        }

//        return prefix

        if (strs.isEmpty()) return ""

        fun isCommonPrefix(strs: Array<String>, length: Int): Boolean {
            val prefix = strs[0].substring(0, length)
            for (i in 1 until strs.size) {
                if (!strs[i].startsWith(prefix)) {
                    return false
                }
            }
            return true
        }

        var low = 1
        var high = strs.minOf { it.length }

        while (low <= high) {
            val mid = (low + high) / 2
            if (isCommonPrefix(strs, mid)) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return strs[0].substring(0, (low + high) / 2)
    }

    /**
     * 205. Isomorphic Strings
     * Solved
     * Easy
     * Topics
     * Companies
     * Given two strings s and t, determine if they are isomorphic.
     *
     * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
     *
     * All occurrences of a character must be replaced with another character while preserving the order of characters.
     * No two characters may map to the same character, but a character may map to itself.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "egg", t = "add"
     *
     * Output: true
     *
     * Explanation:
     *
     * The strings s and t can be made identical by:
     *
     * Mapping 'e' to 'a'.
     * Mapping 'g' to 'd'.
     * Example 2:
     *
     * Input: s = "foo", t = "bar"
     *
     * Output: false
     *
     * Explanation:
     *
     * The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.
     *
     * Example 3:
     *
     * Input: s = "paper", t = "title"
     *
     * Output: true
     */
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val map = hashMapOf<Char, Char>()

        for (i in s.indices) {
            val sCh = s[i]
            val tCh = t[i]
            if (map.containsKey(sCh)) {
                if (map[sCh] != tCh) return false
            } else {
                if (map.containsValue(tCh)) return false
                map[sCh] = tCh
            }
        }
        return true
    }

    /**
     * 796. Rotate String
     * Easy
     * Topics
     * Companies
     * Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
     *
     * A shift on s consists of moving the leftmost character of s to the rightmost position.
     *
     * For example, if s = "abcde", then it will be "bcdea" after one shift.
     *
     *
     * Example 1:
     *
     * Input: s = "abcde", goal = "cdeab"
     * Output: true
     * Example 2:
     *
     * Input: s = "abcde", goal = "abced"
     * Output: false
     *
     *
     * Constraints:
     *
     * 1 <= s.length, goal.length <= 100
     * s and goal consist of lowercase English letters.
     */
    fun rotateString(s: String, goal: String): Boolean {
        if (s.length != goal.length) return false
        return (s + s).contains(goal)
    }

    /**
     * 242. Valid Anagram
     * Solved
     * Easy
     * Topics
     * Companies
     * Given two strings s and t, return true if t is an
     * anagram
     *  of s, and false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "anagram", t = "nagaram"
     *
     * Output: true
     *
     * Example 2:
     *
     * Input: s = "rat", t = "car"
     *
     * Output: false
     *
     *
     *
     * Constraints:
     *
     * 1 <= s.length, t.length <= 5 * 104
     * s and t consist of lowercase English letters.
     *
     *
     * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
     */
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val map = mutableMapOf<Char, Int>()
        for (c in s) {
            map[c] = map.getOrDefault(c, 0) + 1
        }

        for (c in t) {
            if (!map.containsKey(c)) return false
            map[c] = map[c]!! - 1
            if (map[c] == 0) map.remove(c)
        }

        return map.isEmpty()
    }


    /**
     * Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).
     *
     * The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.
     *
     *
     * Example 1:
     *
     * Input: s = "011101"
     * Output: 5
     * Explanation:
     * All possible ways of splitting s into two non-empty substrings are:
     * left = "0" and right = "11101", score = 1 + 4 = 5
     * left = "01" and right = "1101", score = 1 + 3 = 4
     * left = "011" and right = "101", score = 1 + 2 = 3
     * left = "0111" and right = "01", score = 1 + 1 = 2
     * left = "01110" and right = "1", score = 2 + 1 = 3
     */

    fun maxScore(s: String): Int {
        var one = 0
        var zero = 0
        var res = 0

        for (c in s) {
            if (c == '1') one++
        }

        for (i in 0 until s.length) {
            if (s[i] == '0') zero++
            else one--

            res = maxOf(res, zero + one)
        }
        return res
    }

}