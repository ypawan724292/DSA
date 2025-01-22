package dp

import annotations.Important

class `String-DP` {
    /**
     * Longest Common Subsequence
     *
     * Example: str1 = "abcde" , str2 = "ace"
     * LCS = "ace" => 3
     */
    fun longestCommonSubsequence(str1: String, str2: String): Int {
        val m = str1.length
        val n = str2.length

        /**
         * Here we are have shifted indices by 1 for memoization purpose, else we can have -ve indices
         * which is not possible to put array
         */

        // recursive {top-down} approach and memoization
        val memo = Array(m + 1) { IntArray(n + 1) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i == 0 || j == 0) return 0
            if (memo[i][j] != -1) return memo[i][j]

            memo[i][j] = if (str1[i - 1] == str2[j - 1]) {
                1 + f(i - 1, j - 1)
            } else {
                maxOf(f(i - 1, j), f(i, j - 1))
            }
            return memo[i][j]
        }

//        return f(m, n)// bcuz we right shifted by 1 f(m,n) not f(m-1,n-1)

        //tabulation {bottom-up} approach
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) {
            for (j in 0..n) {
                if (i == 0 || j == 0) dp[i][j] = 0
                else {
                    dp[i][j] = if (str1[i - 1] == str2[j - 1]) {
                        1 + dp[i - 1][j - 1]
                    } else {
                        maxOf(dp[i - 1][j], dp[i][j - 1])
                    }
                }
            }
        }

        // return   dp[n][m]  //bcuz we right shifted by 1 f(m,n) not f(m-1,n-1)


        //space optimized
        var prev: IntArray? = null
        for (i in 0..m) {
            val cur = IntArray(n + 1)
            for (j in 0..n) {
                if (i == 0 || j == 0) {
                    cur[j] = 0
                } else {
                    cur[j] = if (str1[i - 1] == str2[j - 1]) {
                        1 + prev!![j - 1]
                    } else {
                        maxOf(prev!![j], cur[j - 1])
                    }
                }
            }
            prev = cur
        }

        return prev!![n]
    }

    /**
     * Print Longest Common Subsequence of two strings
     */
    @Important
    fun printLongestCommonSubsequence(str1: String, str2: String): String {
        val m = str1.length
        val n = str2.length

        /*
         LCS table for
         */

        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) {
            for (j in 0..n) {
                if (i == 0 || j == 0) dp[i][j] = 0
                else {
                    dp[i][j] = if (str1[i - 1] == str2[j - 1]) {
                        1 + dp[i - 1][j - 1]
                    } else {
                        maxOf(dp[i - 1][j], dp[i][j - 1])
                    }
                }
            }
        }

        var i = m
        var j = n
        val sb = StringBuilder()
        while (i > 0 && j > 0) {
            if (str1[i - 1] == str2[j - 1]) {
                sb.append(str1[i - 1])
                i--
                j--
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--
            } else {
                j--
            }
        }

        return sb.reverse().toString()
    }

    /**
     * Longest common substring
     */
    fun longestCommonSubstring(str1: String, str2: String): Int {
        val m = str1.length
        val n = str2.length

        //this is tabulation for the LCS
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) {
            for (j in 0..n) {
                if (i == 0 || j == 0) dp[i][j] = 0
                else dp[i][j] = if (str1[i - 1] == str2[j - 1]) {
                    1 + dp[i - 1][j - 1]
                } else {
                    maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }

        // we try to use the same dp array to find the longest common substring
        // we just need to find the max value in the dp array and set dp[i][j] = 0 if common char is broken

        var max = 0
        for (i in 0..m) {
            for (j in 0..n) {
                if (i == 0 || j == 0) dp[i][j] = 0
                else dp[i][j] = if (str1[i - 1] == str2[j - 1]) {
                    val value = 1 + dp[i - 1][j - 1]
                    max = maxOf(max, value)
                    value
                } else {
                    0
                }
            }
        }
        return max
    }

    /**
     * Longest Palindrome Subsequence
     */
    fun longestPalindromeSubsequence(str: String): Int {
        /**
         * Intution :
         * We have to find the LCS for str and str.reversed()
         * Example:
         * str = "agbcba"
         * str.reversed() = "abcbga"
         *
         * LCS = "abcba" => 5
         */
        return longestCommonSubsequence(str, str.reversed())
    }

    /**
     * Minimum number of insertions to make a string palindrome
     */
    fun minInsertionsToMakePalindrome(str: String): Int {/*
        * Intution:
        * We have to find the minimum number of insertions to make a string palindrome
        * we can find the longest palindromic subsequence and subtract it from the length of the string
        *
        * Example :
        * str = "agbcba" , str.reversed() = "abcbga"
        * LCS = "abcba" => 5
        *
        * From above we have g missing in the LCS, so we need to insert g to make it palindrome
        * minInsertions = str.length - LCS = 6 - 5 = 1
        *
        *
         */
        return str.length - longestPalindromeSubsequence(str)
    }

    /**
     *Minimum Insertions/Deletions to Convert String A to String B
     */
    fun minInsertionsDeletionsToConvertStringAtoStringB(str1: String, str2: String): Int {/*
        Intution:
        We have to find the minimum number of insertions and deletions to convert string A to string B
        1. Find all the LCS of the two strings
        2. minInsertions = str1.length - LCS
        3. minDeletions = str2.length - LCS
        4 minInsertions + minDeletions = str1.length + str2.length - 2 * LCS
         */
        val lcs = longestCommonSubsequence(str1, str2)
        return str1.length - lcs + str2.length - lcs
    }


    /**
     * print shortest common supersequence
     *
     * A supersequence is defined as the string which contains both the strings S1 and S2 as subsequences.
     */
    fun shortestCommonSuperSequence(str1: String, str2: String): String {/*
        * Intution:
        * We have to find the shortest common supersequence
        * 1. Find the LCS of the two strings
        * 2. The shortest common supersequence = str1.length + str2.length - LCS
        *
        * Example:
        * str1 = "aggtab" , str2 = "gxtxayb"
        * LCS = "gtab" => 4
        * Shortest common supersequence = 7 + 7 - 4 = 10
        *
         */

        val m = str1.length
        val n = str2.length


        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) {
            for (j in 0..n) {
                if (i == 0 || j == 0) dp[i][j] = 0
                else {
                    dp[i][j] = if (str1[i - 1] == str2[j - 1]) {
                        1 + dp[i - 1][j - 1]
                    } else {
                        maxOf(dp[i - 1][j], dp[i][j - 1])
                    }
                }
            }
        }

        var i = m
        var j = n

        var res = ""
        while (i > 0 && j > 0) {
            res += if (str1[i - 1] == str2[j - 1]) {
                i--
                j--
                str1[i]
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--
                str1[i]
            } else {
                j--
                str2[j]
            }
        }

        while (i > 0) {
            i--
            res += str1[i]
        }
        while (j > 0) {
            j--
            res += str2[j]
        }


        //this is provide the count of longest common supersequence
//        val lcs = longestCommonSubsequence(str1, str2)
//        return str1.length + str2.length - lcs


        return res.reversed()
    }

    /**
     * Distinct Subsequences
     * We are given two strings S1 and S2, we want to know how many distinct subsequences of S2 are present in S1.
     *
     * Example:
     * S1 = "rabbbit" , S2 = "rabbit"
     * Distinct Subsequences = 3
     * Explanation: There are 3 distinct subsequences of S2 in S1, they are highlighted below
     */
    fun distinctSubsequences(str1: String, str2: String): Int {
        val m = str1.length
        val n = str2.length
        //recursive approach {top-down} and memoization
        val memo = Array(m + 1) { IntArray(n + 1) { -1 } }
        fun f(i: Int, j: Int): Int {
            if(j==0) return 1
            if(i==0) return 0

            if (memo[i][j] != -1) return memo[i][j]

            memo[i][j] = if (str1[i - 1] == str2[j - 1]) {
                // If the characters at the current positions match, we can either leave one character from s1
                // or continue to the next character in s1 while staying at the same character in s2.
                val take = f(i - 1, j - 1)
                val noTake = 0 + f(i - 1, j)
                take + noTake
            } else {
                f(i - 1, j)
            }
            return memo[i][j]
        }

//        return f(m, n)

        //tabulation {bottom-up} approach
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) {
            for (j in 0..n) {
                if ( j == 0) dp[i][j] = 1
                if (i == 0) dp[i][j] = 0
                else dp[i][j] = if (str1[i - 1] == str2[j - 1]) {
                    val take =  dp[i - 1][j - 1]
                    val noTake = 0 + dp[i - 1][j]
                    take + noTake
                } else {
                    dp[i - 1][j]
                }
            }
        }

        return dp[m][n]
    }

    /**
     * Convert S1 to S2 in minimum operations with below possible operations
     * 1. Insert a character
     * 2. Delete a character
     * 3. Replace a character
     *
     * Example:
     * S1 = "horse
     * S2 = "ros"
     * replace 'h' with 'r' in s1
     * deleter r, e in s1, remaining s1 = "ros"
     * Minimum operations = 3
     */
    @Important
    fun minOperationsToConvertS1ToS2(str1: String, str2: String): Int {
        val m = str1.length
        val n = str2.length

        //recursive approach {top-down} and memoization
        val memo = Array(m + 1) { IntArray(n + 1) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i == 0 || j == 0) return maxOf(i, j)

            return if (str1[i - 1] != str2[j - 1]) {
                val insert = 1 + f(
                    i, j - 1
                ) // here consider we inserted a character in s1 at i and matched with s2 j, so we have only j-1 left in s2
                val delete = 1 + f(i - 1, j) // here we shrunk the string s1
                val replace = 1 + f(i - 1, j - 1)// here we replace
                minOf(insert, delete, replace)
            } else {
                f(i - 1, j - 1)
            }
        }

//        return f(m, n)
        //tabulation {bottom-up} approach
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) {
            for (j in 0..n) {
                if (i == 0 || j == 0) dp[i][j] = maxOf(i, j)
                else {
                    dp[i][j] = if (str1[i - 1] != str2[j - 1]) {
                        val insert = 1 + dp[i][j - 1]
                        val delete = 1 + dp[i - 1][j]
                        val replace = 1 + dp[i - 1][j - 1]
                        minOf(insert, delete, replace)
                    } else {
                        dp[i - 1][j - 1]
                    }
                }
            }
        }

        return dp[m][n]
    }

    /**
     * Wild card matching
     * We are given two strings ‘S1’ and ‘S2’. String S1 can have the following two special characters:
     *
     * ‘?’ can be matched to a single character of S2.
     * ‘*’ can be matched to any sequence of characters of S2. (sequence can be of length zero or more).
     * We need to check whether strings S1 and S2 match or not.
     *
     * Example:
     * ab*cd
     * abdefcd
     *
     * Output: true
     */

    fun wildCardMatch(str1: String, str2: String): Boolean {
        val m = str1.length
        val n = str2.length

        //recursion {top-down} and memoization
        val memo = Array(m + 1) { BooleanArray(n + 1) }
        fun f(i: Int, j: Int): Boolean {
            //base case
            // if both strings are empty, then return true
            if (i == 0 && j == 0) return true
            // if str1 is empty and str2 is not empty, then return false
            if (i == 0) return false
            // if str2 is empty and str1 is not empty,
            if (j == 0) {
                for (k in 0 until i) {
                    if (str1[k] != '*') return false
                }
                return true
            }

            if (memo[i][j]) return memo[i][j]

            memo[i][j] = if (str1[i - 1] == str2[j - 1] || str1[i - 1] == '?') {
                f(i - 1, j - 1)
            } else if (str1[i - 1] == '*') {
                f(i - 1, j) || f(i, j - 1)
            } else {
                false
            }
            return memo[i][j]
        }

//        return f(m, n)


        //tabulation {bottom-up} approach
        val dp = Array(m + 1) { BooleanArray(n + 1) }
        for (i in 0..m) {
            for (j in 0..n) {
                if (i == 0 && j == 0) dp[i][j] = true
                else if (i == 0) dp[i][j] = false
                else if (j == 0) {
                    var flag = true
                    for (k in 0 until i) {
                        if (str1[k] != '*') {
                            flag = false
                            break
                        }
                    }
                    dp[i][j] = flag
                } else {
                    dp[i][j] = if (str1[i - 1] == str2[j - 1] || str1[i - 1] == '?') {
                        dp[i - 1][j - 1]
                    } else if (str1[i - 1] == '*') {
                        dp[i - 1][j] || dp[i][j - 1]
                    } else {
                        false
                    }
                }
            }
        }

        return dp[m][n]
    }

    /**
     * Regular Expression Matching
     * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where:
     *
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element
     *
     * Example:
     * Input: s = "aa", p = "a"
     * Output: false
     *
     * Input: s = "aa", p = "a*"
     * Output: true
     *
     * Input: s = "ab", p = ".*"
     * Exaplanation: here . can be a and * can match 0 or more of a
     *
     * Output: true
     *
     */
    fun regularExpressionMatching(s: String, p: String): Boolean {
        val m = s.length
        val n = p.length

        //recursion {top-down} and memoization
        val memo = Array(m + 1) { BooleanArray(n + 1) }
        fun f(i: Int, j: Int): Boolean {
            if (i == 0 && j == 0) return true
            if (j == 0) return false
            if (i == 0) {
                for (k in 0 until j) {
                    if (p[k] != '*') return false
                }
                return true
            }

            if (memo[i][j]) return memo[i][j]

            memo[i][j] = if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
                f(i - 1, j - 1)
            } else if (p[j - 1] == '*') {
                val zero = f(i, j - 1) // zero occurrences of '.' // 'character before *'
                val one = (s[i - 1] == p[j - 2] || p[j - 2] == '.') && f(
                    i - 1, j
                ) // one or more occurrences of '.'// 'any number of preceding element'
                zero || one
            } else {
                false
            }
            return memo[i][j]
        }
        return f(m, n)

        /*
        Dry Run :
        Call f(2, 2):
            s[1] = 'b', p[1] = '*'
            p[1] is '*', so we check two cases:
            zero = f(2, 1) (zero occurrences of '.')
            one = (s[1] == p[0] || p[0] == '.') && f(1, 2) (one or more occurrences of '.')

            Call f(2, 1):
            s[1] = 'b', p[0] = '.'
            s[1] matches p[0] because p[0] is '.'
            f(2, 1) = f(1, 0)

            Call f(1, 0):
            j == 0, return false
            f(2, 1) = false

            Call f(1, 2):
            s[0] = 'a', p[1] = '*'
            p[1] is '*', so we check two cases:
            zero = f(1, 1) (zero occurrences of '.')
            one = (s[0] == p[0] || p[0] == '.') && f(0, 2) (one or more occurrences of '.')

            Call f(1, 1):
            s[0] = 'a', p[0] = '.'
            s[0] matches p[0] because p[0] is '.'
            f(1, 1) = f(0, 0)

            Call f(0, 0):
            i == 0 and j == 0, return true
            f(1, 1) = true

            Call f(0, 2):
            i == 0, check if p[0..1] can match an empty string
            p[0] = '.', p[1] = '*', which can match an empty string
            f(0, 2) = true

            Combine Results:
            f(1, 2) = zero || one = true || true = true
            f(2, 2) = zero || one = false || true = true




         */
    }

}

fun main() {
    val obj = `String-DP`()
//    println(obj.longestCommonSubsequence("acd", "ced")) // 3
}