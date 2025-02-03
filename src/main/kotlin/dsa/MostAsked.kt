package dsa

import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

class MostAsked {

    /**
     * Given a string s, reverse only all the vowels in the string and return it.
     * Example:
     * Input: s = "hello"
     * Output: "holle"
     */
    fun reverseVowels(s: String): String {
        val vowels = "aeiouAEIOU"
        val chars = s.toCharArray()
        var left = 0
        var right = s.length - 1

        while (left < right) {
            while (left < right && chars[left] !in vowels) left++
            while (left < right && chars[right] !in vowels) right--

            chars[left] = chars[right].also { chars[right] = chars[left] }
            left++
            right--
        }

        return String(chars)
    }

    fun longestPalindrome(s: String): String {
        if (s.isEmpty()) return ""

        var start = 0
        var end = 0

        fun expandAroundCenter(left: Int, right: Int): Int {
            var l = left
            var r = right
            while (l >= 0 && r < s.length && s[l] == s[r]) {
                l--
                r++
            }
            return r - l - 1
        }

        for (i in s.indices) {
            val len1 = expandAroundCenter(i, i)
            val len2 = expandAroundCenter(i, i + 1)
            val len = maxOf(len1, len2)

            if (len > end - start) {
                start = i - (len - 1) / 2
                end = i + len / 2
            }
        }

        return s.substring(start, end + 1)
    }

    fun largestIsland(grid: Array<IntArray>): Int {
        val directions = arrayOf(
            intArrayOf(0, 1), intArrayOf(1, 0),
            intArrayOf(0, -1), intArrayOf(-1, 0)
        )

        val n = grid.size
        var maxIsland = 0

        fun dfs(i: Int, j: Int): Int {
            if (i !in 0 until n || j !in 0 until n || grid[i][j] == 0) return 0
            grid[i][j] = 0
            var size = 1
            for (dir in directions) {
                size += dfs(i + dir[0], j + dir[1])
            }
            return size
        }

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 1) {
                    maxIsland = maxOf(maxIsland, dfs(i, j))
                }
            }
        }

        return maxIsland
    }

    fun longestRepeatingSubsequence(str: String): Int {
        val n = str.length
        val dp = Array(n + 1) { IntArray(n + 1) }

        for (i in 1..n) {
            for (j in 1..n) {
                if (str[i - 1] == str[j - 1] && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }

        return dp[n][n]
    }


    data class TreeNode(val value: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    fun serialize(root: TreeNode?): String {
        if (root == null) return "null"
        return "${root.value},${serialize(root.left)},${serialize(root.right)}"
    }

    fun deserialize(data: String): TreeNode? {
        val queue: Queue<String> = LinkedList(data.split(","))

        fun buildTree(): TreeNode? {
            if (queue.isEmpty()) return null
            val value = queue.poll()
            if (value == "null") return null
            val node = TreeNode(value.toInt())
            node.left = buildTree()
            node.right = buildTree()
            return node
        }

        return buildTree()
    }

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val merged = (nums1 + nums2).sorted()
        val n = merged.size
        return if (n % 2 == 0) (merged[n / 2 - 1] + merged[n / 2]) / 2.0 else merged[n / 2].toDouble()
    }


    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val pq = PriorityQueue<Int>()
        for (row in matrix) for (num in row) pq.add(num)
        repeat(k - 1) { pq.poll() }
        return pq.poll()
    }


}