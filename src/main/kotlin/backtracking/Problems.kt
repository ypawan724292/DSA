package backtracking

import annotations.Important
import graphs.MultiSourceBFS

class Problems {

    /**
     * There are n friends that are playing a game.
     * The friends are sitting in a circle and are numbered from 1 to n in clockwise order.
     * More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n,
     * and moving clockwise from the nth friend brings you to the 1st friend.
     *
     * The rules of the game are as follows:
     *
     * Start at the 1st friend.
     * Count the next k friends in the clockwise direction including the friend you started at.
     * The counting wraps around the circle and may count some friends more than once.
     * The last friend you counted leaves the circle and loses the game.
     * If there is still more than one friend in the circle,
     * go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
     * Else, the last friend in the circle wins the game.
     * Given the number of friends, n, and an integer k, return the winner of the game.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: n = 5, k = 2
     * Output: 3
     * Explanation: Here are the steps of the game:
     * 1) Start at friend 1.
     * 2) Count 2 friends clockwise, which are friends 1 and 2.
     * 3) Friend 2 leaves the circle. Next start is friend 3.
     * 4) Count 2 friends clockwise, which are friends 3 and 4.
     * 5) Friend 4 leaves the circle. Next start is friend 5.
     * 6) Count 2 friends clockwise, which are friends 5 and 1.
     * 7) Friend 1 leaves the circle. Next start is friend 3.
     * 8) Count 2 friends clockwise, which are friends 3 and 5.
     * 9) Friend 5 leaves the circle. Only friend 3 is left, so they are the winner.
     * Example 2:
     *
     * Input: n = 6, k = 5
     * Output: 1
     * Explanation: The friends leave in this order: 5, 4, 6, 2, 3. The winner is friend 1.
     *
     *
     * Constraints:
     *
     * 1 <= k <= n <= 500
     *
     *
     * Follow up:
     *
     * Could you solve this problem in linear time with constant space?
     */
    fun findTheWinner(n: Int, k: Int): Int {
//        val list = mutableListOf<Int>()
//        for (i in 1..n) {
//            list.add(i)
//        }
//        var index = 0
//        while (list.size > 1) {
//            index = (index + k - 1) % list.size
//            list.removeAt(index)
//        }
//        return list[0]

        //using recursion
        return if (n == 1) 1
        else (findTheWinner(n - 1, k) + k - 1) % n + 1
    }

    /**
     * Given a string containing digits from 2-9 inclusive,
     * return all possible letter combinations that the number could represent. Return the answer in any order.
     *
     * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     *
     *
     *
     *
     * Example 1:
     *
     * Input: digits = "23"
     * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * Example 2:
     *
     * Input: digits = ""
     * Output: []
     * Example 3:
     *
     * Input: digits = "2"
     * Output: ["a","b","c"]
     */
    val map = mutableMapOf(
        2 to "abc",
        3 to "def",
        4 to "ghi",
        5 to "jkl",
        6 to "mno",
        7 to "pqrs",
        8 to "tuv",
        9 to "wxyz"
    )

    fun findcombination(str: String): List<String> {
        val res = mutableListOf<String>()
        if (str.isEmpty()) return res

        fun backtrack(i: Int, comb: String) {
            if (i == str.length) {
                res.add(comb)
                return
            }
            for (ch in map[str[i] - '0']!!) {
                backtrack(i + 1, comb + ch)
            }
        }
        backtrack(0, "")
        return res
    }

    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 3
     * Output: ["((()))","(()())","(())()","()(())","()()()"]
     * Example 2:
     *
     * Input: n = 1
     * Output: ["()"]
     *
     */
    fun generateParenthesis(n: Int): List<String> {
        val res = mutableListOf<String>()

        fun backTrack(open: Int, close: Int, str: String) {
            if (open == n && close == n) {
                res.add(str)
                return
            }

            if (open < n) {
                backTrack(open + 1, close, str + '(')
            }
            if (open > close) {
                backTrack(open, close + 1, str + ')')
            }
        }
        backTrack(0, 0, "")
        return res
    }

    /**
     * Given a string s, partition s such that every
     * substring
     *  of the partition is a
     * palindrome
     * . Return all possible palindrome partitioning of s.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "aab"
     * Output: [["a","a","b"],["aa","b"]]
     * Example 2:
     *
     * Input: s = "a"
     * Output: [["a"]]
     *
     */
    fun partition(s: String): List<List<String>> {
        val res = mutableListOf<List<String>>()

        fun isPalindrome(str: String): Boolean {
            var i = 0
            var j = str.lastIndex
            while (i < j) {
                if (str[i] != str[j]) return false
                i++
                j--
            }
            return true
        }

        fun backTrack(i: Int, list: MutableList<String>) {
            if (i == s.length) {
                res.add(list.toList())
                return
            }
            for (j in i until s.length) {
                if (isPalindrome(s.substring(i, j + 1))) {
                    list.add(s.substring(i, j + 1))
                    backTrack(j + 1, list)
                    list.removeAt(list.size - 1)
                }
            }
        }
        backTrack(0, mutableListOf())
        return res
    }

    /**
     * You are given a string ‘STR’ consisting of English lowercase letters.
     *
     * Your task is to find out all the generalised abbreviations of ‘STR’
     * and print an array/list of these abbreviations sorted in increasing order.
     *
     * Note:
     *
     * A string is said to be a generalised abbreviations string of ‘STR’ if we remove a substring of length ‘X’ from ‘STR’
     * and put the number ‘X’ at the place of the removed substring.
     *
     * We can not remove two consecutive substrings or we can say generalised abbreviations will never have two consecutive numbers.
     * For example:
     *
     * If ‘STR’ = “abc”,
     * Sorted generalized abbreviations of ‘STR’ are: [“1b1”, “1bc”, “2c”, “3”, “a1c”, “a2”, “ab1”, “abc”].
     * Detailed explanation ( Input/output format, Notes, Images )
     * Sample Input 1:
     * 2
     * ab
     * xyz
     * Sample Output 1:
     * 1b  2  a1  ab
     * 1y1  1yz  2z  3  x1z   x2   xy1   xyzf
     * Explanation Of Sample Input 1:
     * For test case 1:
     * "ab" can be written as {1b,  2,  a1,  ab}.
     *
     * For test case 2:
     * "xyz" can be written as {1y1, 1yz,  2z,  3,  x1z,   x2,   xy1,   xyz}.
     * Sample Input 2:
     * 2
     * n
     * code
     * Sample Output 2:
     * 1 n
     * 1o1e  1o2  1od1  1ode  2d1  2de  3e  4  c1d1  c1de  c2e  c3  co1e  co2  cod1  code
     * Explanation Of Sample Input 2:
     * For test case 1:
     * "n" can be written as {1, n}.
     *
     * For test case 2:
     * "code" can be written as {1o1e,  1o2,  1od1,  1ode, 2d1,  2de,  3e,  4,  c1d1,  c1de,  c2e,  c3,  co1e,  co2,  cod1,  code}.
     *
     */
    @Important
    fun abbreviations(str: String): List<String> {
        val res = mutableListOf<String>()

        fun backTrack(i: Int, cur: String, count: Int) {
            if (i == str.length) {
                res.add(cur)
                return
            }
            //if we take the current character
            backTrack(i + 1, if (count > 0) "$cur$count${str[i]}" else "$cur${str[i]}", 0)
            //if we skip the current character
            backTrack(i + 1, cur, count + 1)
        }
        backTrack(0, "", 0)
        return res
    }


    /**
     * Given an integer array a[ ] of N elements and an integer K,
     * the task is to check if the array a[ ] could be divided into K non-empty subsets with equal sum of elements.
     * Note: All elements of this array should be part of exactly one partition.
     *
     * Example 1:
     *
     * Input:
     * N = 5
     * a[] = {2,1,4,5,6}
     * K = 3
     * Output:
     * 1
     * Explanation: we can divide above array
     * into 3 parts with equal sum as (2, 4),
     * (1, 5), (6)
     * Example 2:
     *
     * Input:
     * N = 5
     * a[] = {2,1,5,5,6}
     * K = 3
     * Output:
     * 0
     * Explanation: It is not possible to divide
     * above array into 3 parts with equal sum.
     */
    fun findKSubsetsWithEqualSum(arr: IntArray, k: Int): Boolean {
        val sum = arr.sum()
        if (sum % k != 0) return false
        val target = sum / k
        val visited = BooleanArray(arr.size)
        fun backTrack(i: Int, currentSum: Int, currentK: Int): Boolean {
            if (currentK == k) return true
            if (currentSum == target) return backTrack(0, 0, currentK + 1)
            for (j in i until arr.size) {
                if (!visited[j] && currentSum + arr[j] <= target) {
                    visited[j] = true
                    if (backTrack(j + 1, currentSum + arr[j], currentK)) return true
                    visited[j] = false
                }
            }
            return false
        }
        return backTrack(0, 0, 0)
    }

    /**
     * Consider a rat placed at (0, 0) in a square matrix mat of order n* n. It has to reach the destination at (n - 1, n - 1). Find all possible paths that the rat can take to reach from source to destination. The directions in which the rat can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right). Value 0 at a cell in the matrix represents that it is blocked and rat cannot move to it while value 1 at a cell in the matrix represents that rat can be travel through it.
     * Note: In a path, no cell can be visited more than one time. If the source cell is 0, the rat cannot move to any other cell. In case of no path, return an empty list. The driver will output "-1" automatically.
     *
     * Examples:
     *
     * Input: mat[][] = [[1, 0, 0, 0],
     *                 [1, 1, 0, 1],
     *                 [1, 1, 0, 0],
     *                 [0, 1, 1, 1]]
     * Output: DDRDRR DRDDRR
     * Explanation: The rat can reach the destination at (3, 3) from (0, 0) by two paths - DRDDRR and DDRDRR, when printed in sorted order we get DDRDRR DRDDRR.
     * Input: mat[][] = [[1, 0],
     *                 [1, 0]]
     * Output: -1
     * Explanation: No path exists and destination cell is blocked.
     * Expected Time Complexity: O(3n^2)
     * Expected Auxiliary Space: O(l * x)
     * Here l = length of the path, x = number of paths.
     *
     * Constraints:
     * 2 ≤ n ≤ 5
     * 0 ≤ mat[i][j] ≤ 1
     */
    fun findPath(mat: Array<IntArray>): List<String> {
        val res = mutableListOf<String>()
        val n = mat.size
        fun backTrack(i: Int, j: Int, path: String) {
            if (i !in 0..n - 1 || j !in 0..n - 1 && mat[i][j] == 0) return
            if (i == n - 1 && j == n - 1) {
                res.add(path)
                return
            }
            mat[i][j] == 0
            backTrack(i + 1, j, path + 'D')
            backTrack(i - 1, j, path + 'U')
            backTrack(i, j + 1, path + 'R')
            backTrack(i, j - 1, path + 'L')
        }
        backTrack(0, 0, "")
        return res
    }

    /**
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     *
     * A sudoku solution must satisfy all of the following rules:
     *
     * Each of the digits 1-9 must occur exactly once in each row.
     * Each of the digits 1-9 must occur exactly once in each column.
     * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     * The '.' character indicates empty cells.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
     * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
     * Explanation: The input board is shown above and the only valid solution is shown below:
     *
     *
     *
     *
     * Constraints:
     *
     * board.length == 9
     * board[i].length == 9
     * board[i][j] is a digit or '.'.
     * It is guaranteed that the input board has only one solution.
     */
    fun solveNQueens(n: Int): List<List<String>> {
        /*
        // rather putting any all queen at any position we will restrict
            R1 = Q1 and soon
        // and maintain each col one Q
        // while we put in the cell we check in the diagonal if already exist then we will kill/retuen the backtrack and start from prev state.
            Above step is done when ever there is quene attack from existing queen

         */
        val res = mutableListOf<List<String>>()
        val board = Array(n) { CharArray(n) { '.' } }

        fun isSafe(row: Int, col: Int): Boolean {
            // check if there is any queen in the same row
            for (i in 0 until row) {
                if (board[i][col] == 'Q') return false
            }

            // check if there is any queen in the right diagonal
            var i = row - 1
            var j = col - 1
            while (i >= 0 && j >= 0) {
                if (board[i][j] == 'Q') return false
                i--
                j--
            }
            i = row - 1
            j = col + 1
            // check if there is any queen in the left diagonal
            while (i >= 0 && j < n) {
                if (board[i][j] == 'Q') return false
                i--
                j++
            }
            return true
        }

        fun backTrack(row: Int) {
            if (row == n) {
                res.add(board.map { it.joinToString("") })
                return
            }
            for (col in 0 until n) {
                if (isSafe(row, col)) {
                    board[row][col] = 'Q'
                    backTrack(row + 1)
                    board[row][col] = '.'
                }
            }
        }
        backTrack(0)
        return res

//        val res = mutableListOf<List<String>>()
//        val board = Array(n) { CharArray(n) { '.' } }
//        val col = mutableSetOf<Int>()
//        val diag1 = mutableSetOf<Int>()
//        val diag2 = mutableSetOf<Int>()
//
//
//        fun backTrack(r : Int){
//            if(r == n){
//                res.add(board.toList().map { it.joinToString("") })
//                return
//            }
//            for(c in 0 until n){
//                if(c in col || r-c in diag1 || r+c in diag2) continue
//                col.add(c)
//                diag1.add(r-c)
//                diag2.add(r+c)
//                backTrack(r+1)
//                col.remove(c)
//                diag1.remove(r-c)
//                diag2.remove(r+c)
//            }
//        }
//
//        backTrack(0)
//        return res
    }

    /**
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     *
     * A sudoku solution must satisfy all of the following rules:
     *
     * Each of the digits 1-9 must occur exactly once in each row.
     * Each of the digits 1-9 must occur exactly once in each column.
     * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     * The '.' character indicates empty cells.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
     * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
     * Explanation: The input board is shown above and the only valid solution is shown below:
     *
     *
     *
     *
     * Constraints:
     *
     * board.length == 9
     * board[i].length == 9
     * board[i][j] is a digit or '.'.
     * It is guaranteed that the input board has only one solution.
     */
    fun solveSudoKu(board: Array<CharArray>) {
        val visited = mutableSetOf<String>()
        val n = board.size
        val emptyCell = mutableListOf<IntArray>()

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (board[i][j] == '.') {
                    emptyCell.add(intArrayOf(i, j))
                } else {
                    visited.add("r$i${board[i][j]}")
                    visited.add("c$j${board[i][j]}")
                    visited.add("b${i / 3}${j / 3}${board[i][j]}")
                }
            }
        }


        fun backTrack(index: Int): Boolean {
            if (index == emptyCell.size) return true
            val (i, j) = emptyCell[index]
            for (num in '1'..'9') {
                if (!visited.contains("r$i$num") && !visited.contains("c$j$num") && !visited.contains("b${i / 3}${j / 3}$num")) {
                    board[i][j] = num
                    visited.add("r$i$num")
                    visited.add("c$j$num")
                    visited.add("b${i / 3}${j / 3}$num")
                    if (backTrack(index + 1)) return true
                    visited.remove("r$i$num")
                    visited.remove("c$j$num")
                    visited.remove("b${i / 3}${j / 3}$num")
                }
            }
            board[i][j] = '.'
            return false
        }

        backTrack(0)

    }

    /**
     *You are given an array of strings arr.
     *  A string s is formed by the concatenation of a subsequence of arr that has unique characters.
     *
     * Return the maximum possible length of s.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = ["un","iq","ue"]
     * Output: 4
     * Explanation: All the valid concatenations are:
     * - ""
     * - "un"
     * - "iq"
     * - "ue"
     * - "uniq" ("un" + "iq")
     * - "ique" ("iq" + "ue")
     * Maximum length is 4.
     * Example 2:
     *
     * Input: arr = ["cha","r","act","ers"]
     * Output: 6
     * Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
     * Example 3:
     *
     * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
     * Output: 26
     * Explanation: The only string in arr has all 26 characters.
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 16
     * 1 <= arr[i].length <= 26
     * arr[i] contains only lowercase English letters.
     */
    fun maxLength(arr: List<String>): Int {
        var res = Int.MIN_VALUE

        fun backtrack(index: Int, str: String) {
            if (str.length != str.toSet().size) return
            res = maxOf(res, str.length)
            for (i in index until arr.size) {
                backtrack(i + 1, str + arr[i])
            }
        }
        backtrack(0, "")
        return res
    }


    /**
     * Given an m x n board of characters and a list of strings words, return all words on the board.
     *
     * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
     * The same letter cell may not be used more than once in a word.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
     * Output: ["eat","oath"]
     * Example 2:
     *
     *
     * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
     * Output: []
     *
     *
     * Constraints:
     *
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 12
     * board[i][j] is a lowercase English letter.
     * 1 <= words.length <= 3 * 104
     * 1 <= words[i].length <= 10
     * words[i] consists of lowercase English letters.
     *
     */

    inner class Trie {
        val children = Array<Trie?>(26) { null }
        var word: String? = null

        fun insert(word: String) {
            var node = this
            for (ch in word) {
                val index = ch - 'a'
                if (node.children[index] == null) {
                    node.children[index] = Trie()
                }
                node = node.children[index]!!
            }
            node.word = word
        }

    }

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val res = mutableListOf<String>()
        val trie = Trie()

        for (word in words) {
            trie.insert(word)
        }

        fun backtrack(i: Int, j: Int, node: Trie) {
            if (i !in 0 until board.size || j !in 0 until board[0].size || board[i][j] == '#') return
            val c = board[i][j]
            val next = node.children[c - 'a'] ?: return
            if (next.word != null) {
                res.add(next.word!!)
                next.word = null
            }
            board[i][j] = '#'
            backtrack(i + 1, j, next)
            backtrack(i - 1, j, next)
            backtrack(i, j + 1, next)
            backtrack(i, j - 1, next)
            board[i][j] = c
        }

        for (i in board.indices) {
            for (j in board[0].indices) {
                backtrack(i, j, trie)
            }
        }
        return res
    }

    /**
     * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
     *
     * Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "()())()"
     * Output: ["(())()","()()()"]
     * Example 2:
     *
     * Input: s = "(a)())()"
     * Output: ["(a())()","(a)()()"]
     * Example 3:
     *
     * Input: s = ")("
     * Output: [""]
     */
    fun removeInvalidParentheses(s: String): List<String> {
        // using the backtracking
        val res = mutableListOf<String>()
        val visited = mutableSetOf<String>()
        fun getMRA(str: String): Int {
            var stack = ArrayDeque<Char>()
            for (ch in str) {
                if (ch == '(') stack.add(ch)
                else if (ch == ')') {
                    if (stack.isEmpty() || stack.last() != '(') stack.add(ch)
                    else stack.removeLast()
                }
            }
            return stack.size
        }

        fun backtrack(str: String, mra: Int) {
            if (str in visited) return
            visited.add(str)
            if (mra == 0) {
                if (getMRA(str) == 0) {
                    res.add(str)
                }
                return
            }
            for (i in str.indices) {
                val left = str.substring(0, i)
                val right = str.substring(i + 1)
                backtrack(left + right, mra - 1)
            }
        }

        backtrack(s, getMRA(s))
        return res
    }

    /**
     * Given a number K and string str of digits denoting a positive integer,
     * build the largest number possible by performing swap operations on the digits of str at most K times.
     *
     *
     * Example 1:
     *
     * Input:
     * K = 4
     * str = "1234567"
     * Output:
     * 7654321
     * Explanation:
     * Three swaps can make the
     * input 1234567 to 7654321, swapping 1
     * with 7, 2 with 6 and finally 3 with 5
     * Example 2:
     *
     * Input:
     * K = 3
     * str = "3435335"
     * Output:
     * 5543333
     * Explanation:
     * Three swaps can make the input
     * 3435335 to 5543333, swapping 3
     * with 5, 4 with 5 and finally 3 with 4
     *
     * Your task:
     * You don't have to read input or print anything. Your task is to complete the function findMaximumNum() which takes the string and an integer as input and returns a string containing the largest number formed by perfoming the swap operation at most k times.
     *
     *
     * Expected Time Complexity: O(n!/(n-k)!) , where n = length of input string
     * Expected Auxiliary Space: O(n)
     *
     *
     * Constraints:
     * 1 ≤ |str| ≤ 30
     * 1 ≤ K ≤ 10
     */
    fun findMaximumNum(str: String, k: Int): String {
        //using backtracking
        var res = ""

        fun backtrack(str: String, k: Int) {
            if (k == 0) {
                res = maxOf(res, str)
                return
            }
            for (i in 0 until str.length) {
                for (j in i + 1 until str.length) {
                    if (str[i] < str[j]) {
                        val newStr =
                            str.substring(0, i) + str[j] + str.substring(i + 1, j) + str[i] + str.substring(j + 1)
                        backtrack(newStr, k - 1)
                    }
                }
            }
        }
        backtrack(str, k)
        return res
    }

    /**
     * In a gold mine grid of size m x n, each cell in this mine has an integer representing
     * the amount of gold in that cell, 0 if it is empty.
     *
     * Return the maximum amount of gold you can collect under the conditions:
     *
     * Every time you are located in a cell you will collect all the gold in that cell.
     * From your position, you can walk one step to the left, right, up, or down.
     * You can't visit the same cell more than once.
     * Never visit a cell with 0 gold.
     * You can start and stop collecting gold from any position in the grid that has some gold.
     *
     *
     * Example 1:
     *
     * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
     * Output: 24
     * Explanation:
     * [[0,6,0],
     *  [5,8,7],
     *  [0,9,0]]
     * Path to get the maximum gold, 9 -> 8 -> 7.
     * Example 2:
     *
     * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
     * Output: 28
     * Explanation:
     * [[1,0,7],
     *  [2,0,6],
     *  [3,4,5],
     *  [0,3,0],
     *  [9,0,20]]
     * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 15
     * 0 <= grid[i][j] <= 100
     * There are at most 25 cells containing gold.
     */
    fun getMaximumGold(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        var res = 0

        fun backtrack(i: Int, j: Int, sum: Int) {
            if (i !in 0 until m || j !in 0 until n || grid[i][j] == 0) return
            val temp = grid[i][j]
            grid[i][j] = 0
            res = maxOf(res, sum + temp)
            backtrack(i + 1, j, sum + temp)
            backtrack(i - 1, j, sum + temp)
            backtrack(i, j + 1, sum + temp)
            backtrack(i, j - 1, sum + temp)
            grid[i][j] = temp
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                backtrack(i, j, 0)
            }
        }
        return res
    }

    /**
     * 96. Unique Binary Search Trees
     * Medium
     * Topics
     * Companies
     * Given an integer n, return the number of structurally unique BST's (binary search trees)
     * which has exactly n nodes of unique values from 1 to n.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: n = 3
     * Output: 5
     * Example 2:
     *
     * Input: n = 1
     * Output: 1
     */
    fun numTrees(n: Int): Int {
        val res = 0
        fun backtrack(n: Int): Int {
            if (n == 0 || n == 1) return 1
            var sum = 0
            for (i in 1..n) {
                sum += backtrack(i - 1) * backtrack(n - i)
            }
            return sum
        }
        backtrack(n)
        return res
    }

    /**
     * ou have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
     *
     * "1" -> 'A'
     *
     * "2" -> 'B'
     *
     * ...
     *
     * "25" -> 'Y'
     *
     * "26" -> 'Z'
     *
     * However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
     *
     * For example, "11106" can be decoded into:
     *
     * "AAJF" with the grouping (1, 1, 10, 6)
     * "KJF" with the grouping (11, 10, 6)
     * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
     * Note: there may be strings that are impossible to decode.
     *
     * Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.
     *
     * The test cases are generated so that the answer fits in a 32-bit integer.
     *
     *
     */
    fun decodeWays(str: String): Int {
        var res = 0

        //without memoization
        fun backtrack(i: Int) {
            if (i == str.length) {
                res++
                return
            }
            // write intuitive code
            if (str[i] == '0') return
            backtrack(i + 1)
            if (i + 2 < str.lastIndex && str.substring(i, i + 2).toInt() <= 26) {
                backtrack(i + 2)
            }

        }
        backtrack(0)


        //with memoization
        val memo = mutableMapOf<Int, Int>()
        fun backtrackM(i: Int): Int {
            if (i == str.length) return 1
            if (str[i] == '0') return 0
            if (memo.containsKey(i)) return memo[i]!!
            var sum = 0
            sum += backtrackM(i + 1)
            if (i + 2 < str.length && str.substring(i, i + 2).toInt() <= 26) {
                sum += backtrackM(i + 2)
            }
            memo[i] = sum
            return sum
        }
        return memo[0]!!
    }

    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * Find the maximum profit you can achieve. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *
     *
     *
     * Example 1:
     *
     * Input: prices = [3,3,5,0,0,3,1,4]
     * Output: 6
     * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     * Example 2:
     *
     * Input: prices = [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
     * Example 3:
     *
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     *
     * Constraints:
     *
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 105
     */
    fun maxProfit(prices: IntArray): Int {

//        fun backtrack(i: Int, buy: Int, cap: Int): Int {
//            if (i == prices.size || cap == 0) return 0
//
//            return if (buy == 0) {
//                maxOf(
//                    -prices[i] + backtrack(i + 1, 1, cap),
//                    0 + backtrack(i + 1, buy, cap)
//                )
//            } else {
//                maxOf(
//                    prices[i] + backtrack(i + 1, 0, cap - 1),
//                    0 + backtrack(i + 1, buy, cap)
//                )
//            }
//        }

        fun backtrack(i: Int, tranx: Int): Int {
            if (i == prices.size || tranx == 4) return 0

            return if (tranx % 2 == 0) {
                maxOf(
                    -prices[i] + backtrack(i + 1, tranx + 1),
                    0 + backtrack(i + 1, tranx)
                )
            } else {
                maxOf(
                    prices[i] + backtrack(i + 1, tranx + 1),
                    0 + backtrack(i + 1, tranx)
                )
            }
        }
        return backtrack(0, 0)
    }

    /**
     * KnapSack Problem
     */
    fun knapSack(W: Int, wt: IntArray, value: IntArray, n: Int): Int {
        //using backtrack
        fun f(i: Int, w: Int): Int {
            if (i == 0) {
                return if (wt[i] <= w) value[i]
                else 0
            }
            val noTake = f(i - 1, w)
            var take = 0
            if (wt[i] <= w) {
                take = value[i] + f(i - 1, w - wt[i])
            }
            return maxOf(take, noTake)

        }

//        return f(n - 1, W)

        //using memoization
        val memo = Array(n) { IntArray(W + 1) { -1 } }
        fun f1(i: Int, w: Int): Int {
            if (i == 0) {
                return if (wt[i] <= w) value[i]
                else 0
            }
            if (memo[i][w] != -1) return memo[i][w]
            val noTake = f1(i - 1, w)
            var take = 0
            if (wt[i] <= w) {
                take = value[i] + f1(i - 1, w - wt[i])
            }
            memo[i][w] = maxOf(take, noTake)
            return memo[i][w]
        }

//        return f1(n - 1, W)


        //using tabulation
        val dp = Array(n) { IntArray(W + 1) { 0 } }
        // i, w ---> reverse the loop
        for(i in 0 until n){
            for(w in 0..W){
                if(i == 0){
                    dp[i][w] = if(wt[i] <= w) value[i] else 0
                }else{
                    val noTake = dp[i-1][w]
                    var take = 0
                    if(wt[i] <= w){
                        take = value[i] + dp[i-1][w - wt[i]]
                    }
                    dp[i][w] = maxOf(take, noTake)
                }
            }
        }

//        return dp[n-1][W]

        //using 2 array
        var prev = IntArray(W + 1)
        val cur = IntArray(W + 1)

        for(i in 0 until n){
            for(w in 0..W){
                if(i == 0){
                    cur[w] = if(wt[i] <= w) value[i] else 0
                }else{
                    val noTake = prev[w]
                    var take = 0
                    if(wt[i] <= w){
                        take = value[i] + prev[w - wt[i]]
                    }
                    cur[w] = maxOf(take, noTake)
                }
            }
            prev = cur.copyOf()
        }

//        return cur[W]


        //using 1 array
        val dp1 = IntArray(W + 1)
        for(i in 0 until n){
            for(w in W downTo 0){
                if(i == 0){
                    dp1[w] = if(wt[i] <= w) value[i] else 0
                }else{
                    val noTake = dp1[w]
                    var take = 0
                    if(wt[i] <= w){
                        take = value[i] + dp1[w - wt[i]]
                    }
                    dp1[w] = maxOf(take, noTake)
                }
            }
        }

        return dp1[W]
    }

}

fun main(args: Array<String>) {

    val obj = Problems()
    val res = obj.knapSack(45, intArrayOf(10, 20, 30), intArrayOf(120, 100, 120), 3)
    println("res $res")
}
