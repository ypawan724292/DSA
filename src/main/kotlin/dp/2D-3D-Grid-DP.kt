package dp

import annotations.Important

class `2D-3D-Grid-DP` {

    /**
     * Problem Statement: A Ninja has an ‘N’ Day training schedule.
     * He has to perform one of these three activities (Running, Fighting Practice, or Learning New Moves) each day.
     * There are merit points associated with performing an activity each day.
     * The same activity can’t be performed on two consecutive days.
     * We need to find the maximum merit points the ninja can attain in N Days.
     *
     * We are given a 2D Array POINTS of size ‘N*3’ which tells us the merit point of specific activity on that particular day.
     * Our task is to calculate the maximum number of merit points that the ninja can earn.
     *
     */
    fun maxMeritPoints(points: Array<IntArray>): Int {
        val n = points.size
        // recursive {top -down}  and memo
        //here we also have 4 choice , we can pick any of the 3 activities or we can skip the current day
        val memo = Array(n) { IntArray(4) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i == 0) {
                var max = -1
                for (k in 0..2) {
                    if (k != j) {
                        max = maxOf(max, points[i][k])
                    }
                }
                memo[i][j] = max
                return max
            }

            if (memo[i][j] != -1) return memo[i][j]
            var curMax = -1
            for (k in 0..2) {
                if (k != j) {
                    val pick = points[i][k] + f(i - 1, k)
                    curMax = maxOf(curMax, pick)
                }
            }
            memo[i][j] = curMax
            return curMax
        }


//        return f(n - 1, 3)

        // iterative {bottom-up} and tabulation
        val dp = Array(n) { IntArray(4) { 0 } }
        for (i in 0..n - 1) {
            for (j in 0..3) {
                when (i) {
                    0 -> {
                        var max = -1
                        for (k in 0..2) {
                            if (k != j) {
                                max = maxOf(max, points[i][k])
                            }
                        }
                        dp[i][j] = max
                    }

                    else -> {
                        var curMax = -1
                        for (k in 0..2) {
                            if (k != j) {
                                val pick = points[i][k] + dp[i - 1][k]
                                curMax = maxOf(curMax, pick)
                            }
                        }
                        dp[i][j] = curMax
                    }
                }
            }
        }

//        return dp[n - 1][3]

        //space optimized
        var prev: IntArray? = null
        for (i in 0..n - 1) {
            val cur = IntArray(4)
            for (j in 0..3) {
                when (i) {
                    0 -> {
                        var max = -1
                        for (k in 0..2) {
                            if (k != j) {
                                max = maxOf(max, points[i][k])
                            }
                        }
                        cur[j] = max
                    }

                    else -> {
                        var curMax = -1
                        for (k in 0..2) {
                            if (k != j) {
                                val pick = points[i][k] + prev!![k]
                                curMax = maxOf(curMax, pick)
                            }
                        }
                        cur[j] = curMax
                    }
                }
            }
            prev = cur
        }

        return prev!![3]
    }


    /**
     * Given two values M and N, which represent a matrix[M][N].
     * We need to find the total unique paths from the top-left cell (matrix[0][0]) to the rightmost cell (matrix[M-1][N-1]).
     *
     * At any cell we are allowed to move in only two directions:- bottom and right.
     *
     */
    fun uniquePaths(m: Int, n: Int): Int {
        // recursive {bottom-up} and memo
        val memo = Array(m) { IntArray(n) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i < 0 || j < 0) return 0
            if (i == 0 && j == 0) return 1
            if (memo[i][j] != -1) return memo[i][j]
            memo[i][j] = f(i - 1, j) + f(i, j - 1)
            return memo[i][j]
        }

//        return f(m - 1, n - 1)

        //tabulation bottom-up and iterative
        val dp = Array(m) { IntArray(n) { 0 } }
        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                }
            }
        }

//        return dp[m-1][n-1]

        //space optimized
        var prev: IntArray? = null
        for (i in 0..m) {
            val cur = IntArray(n)
            for (j in 0..n) {
                if (i == 0 && j == 0) {
                    cur[j] = 1
                } else {
                    cur[j] = prev!![j] + cur[j - 1]
                }
            }
            prev = cur
        }
        return prev!![n - 1]
    }

    /**
     * We are given an “N*M” Maze. The maze contains some obstacles.
     * A cell is ‘blockage’ in the maze if its value is -1. 0 represents non-blockage.
     * There is no path possible through a blocked cell.
     *
     * We need to count the total number of unique paths from the top-left corner of the maze to the bottom-right corner.
     * At every cell, we can move either down or towards the right.
     *
     */
    fun uniquePathsInMaze(maze: Array<IntArray>): Int {
        val m = maze.size
        val n = maze[0].size
        // recursive {top - down} and memo
        val memo = Array(m) { IntArray(n) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i > 0 && j > 0 && maze[i][j] == -1) return 0
            if (i == 0 && j == 0) return 1
            if (memo[i][j] != -1) return memo[i][j]
            var up = 0
            if (i > 0) up = f(i - 1, j)
            var left = 0
            if (j > 0) left = f(i, j - 1)
            memo[i][j] = up + left
            return memo[i][j]
        }

        //tabulation - bottom up and iterative
        val dp = Array(m) { IntArray(n) { 0 } }
        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (maze[i][j] == -1) dp[i][j] = 0
                else if (i == 0 && j == 0) dp[i][j] = 1
                else {
                    var up = 0
                    if (i > 0) up = dp[i - 1][j]
                    var left = 0
                    if (j > 0) left = dp[i][j - 1]
                    dp[i][j] = up + left
                }
            }

        }
//        return dp[m - 1][n - 1]

        // space optimized
        var prev: IntArray? = null
        for (i in 0..m - 1) {
            val cur = IntArray(n)
            for (j in 0..n - 1) {
                if (maze[i][j] == -1) cur[j] = 0
                else if (i == 0 && j == 0) cur[j] = 1
                else {
                    var up = 0
                    if (i > 0) up = prev!![j]
                    var left = 0
                    if (j > 0) left = cur[j - 1]
                    cur[j] = up + left
                }
            }
            prev = cur
        }

        return prev!![n - 1]
    }

    /**
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
     * which minimizes the sum of all numbers along its path.
     *
     * Note: You can only move either down or right at any point in time.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
     * Output: 7
     * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
     * Example 2:
     *
     * Input: grid = [[1,2,3],[4,5,6]]
     * Output: 12
     *
     */
    fun minPathSum(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        // recursive {top - down} and memo
        val memo = Array(m) { IntArray(n) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i == 0 && j == 0) return grid[i][j]
            if (memo[i][j] != -1) return memo[i][j]
            var up = Int.MAX_VALUE
            var left = Int.MAX_VALUE
            if (i > 0)
                up = f(i - 1, j)
            if (j > 0)
                left = f(i, j - 1)
            memo[i][j] = grid[i][j] + minOf(up, left)
            return memo[i][j]
        }

//        return f(m - 1, n - 1)

        // tabulation {bottom-up} and iterative
        val dp = Array(m) { IntArray(n) { 0 } }
        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j]
                } else {
                    var up = Int.MAX_VALUE
                    var left = Int.MAX_VALUE
                    if (i > 0)
                        up = dp[i - 1][j]
                    if (j > 0)
                        left = dp[i][j - 1]
                    dp[i][j] = grid[i][j] + minOf(up, left)
                }
            }
        }

//        return dp[m - 1][n - 1]

        //space optimized
        var prev: IntArray? = null

        for (i in 0..m - 1) {
            val cur = IntArray(n)
            for (j in 0..n - 1) {
                if (i == 0 && j == 0) {
                    cur[j] = grid[i][j]
                } else {
                    var up = Int.MAX_VALUE
                    var left = Int.MAX_VALUE
                    if (i > 0)
                        up = prev!![j]
                    if (j > 0)
                        left = cur[j - 1]
                    cur[j] = grid[i][j] + minOf(up, left)
                }
            }
            prev = cur
        }

        return prev!![n - 1]
    }

    /**
     * Problem Description:
     *
     * We are given a Triangular matrix. We need to find the minimum path sum from the first row to the last row.
     *
     * At every cell we can move in only two directions: either to the bottom cell (↓) or to the bottom-right cell(↘)
     */
    fun minPathSumInTriangle(triangle: List<List<Int>>): Int {
        val n = triangle.size
        // recursive {top - down} and memo
        val memo = Array(n) { IntArray(n) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i == n - 1) return triangle[i][j]
            if (memo[i][j] != -1) return memo[i][j]
            val left = f(i + 1, j)
            val right = f(i + 1, j + 1)
            memo[i][j] = triangle[i][j] + minOf(left, right)
            return memo[i][j]
        }

//        return f(0,0)

        //tabulation {bottom-up} and iterative
        val dp = Array(n) { IntArray(n) { 0 } }
        for (i in n - 1 downTo 0) {
            for (j in i downTo 0) {
                if (i == n - 1) {
                    dp[i][j] = triangle[i][j]
                } else {
                    dp[i][j] = triangle[i][j] + minOf(dp[i + 1][j], dp[i + 1][j + 1])
                }
            }
        }
//        return dp[0][0]

        //space optimized
        var prev: IntArray? = null
        for (i in n - 1 downTo 0) {
            val cur = IntArray(n)
            for (j in i downTo 0) {
                if (i == n - 1) {
                    cur[j] = triangle[i][j]
                } else {
                    cur[j] = triangle[i][j] + minOf(prev!![j], prev!![j + 1])
                }
            }
            prev = cur
        }

        return prev!![0]

    }

    /**
     * We are given an ‘N*M’ matrix.
     * We need to find the maximum/min path sum from any cell of the first row to any cell of the last row.
     *
     * At every cell we can move in three directions:
     * to the bottom cell (↓), to the bottom-right cell(↘), or to the bottom-left cell(↙).
     */
    fun minPathSumFromFirstRowToLastRow(matrix: Array<IntArray>): Int {
        val m = matrix.size
        val n = matrix[0].size

        // recursive {top - down} and memo
        val memo = Array(m) { IntArray(n) { -1 } }
        fun f(i: Int, j: Int): Int {
            if (i == 0) return matrix[i][j]
            if (memo[i][j] != -1) return memo[i][j]
            val top = f(i - 1, j)
            val topRight = if (j < n - 1) f(i - 1, j + 1) else Int.MAX_VALUE
            val topLeft = if (j > 0) f(i - 1, j - 1) else Int.MAX_VALUE
            memo[i][j] = matrix[i][j] + minOf(top, topRight, topLeft)
            return memo[i][j]
        }

        var res = Int.MAX_VALUE
        for (j in 0..n - 1) {
            res = minOf(res, f(m - 1, j))
        }

//        return res

        // tabulation {bottom-up} and iterative
        val dp = Array(m) { IntArray(n) { 0 } }
        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j]
                } else {
                    val top = dp[i - 1][j]
                    val topRight = if (j < n - 1) dp[i - 1][j + 1] else Int.MAX_VALUE
                    val topLeft = if (j > 0) dp[i - 1][j - 1] else Int.MAX_VALUE
                    dp[i][j] = matrix[i][j] + minOf(top, topRight, topLeft)
                }
            }
        }

        for (j in 0..n - 1) {
            res = minOf(res, dp[m - 1][j])
        }

//        return res

        //space optimized

        var prev: IntArray? = null
        for (i in 0..m - 1) {
            val cur = IntArray(n)
            for (j in 0..n - 1) {
                if (i == 0) {
                    cur[j] = matrix[i][j]
                } else {
                    val top = prev!![j]
                    val topRight = if (j < n - 1) prev!![j + 1] else Int.MAX_VALUE
                    val topLeft = if (j > 0) prev!![j - 1] else Int.MAX_VALUE
                    cur[j] = matrix[i][j] + minOf(top, topRight, topLeft)
                }
            }
            prev = cur
        }

        for (j in 0..n - 1) {
            res = minOf(res, prev!![j])
        }

        return res
    }

    /**
     * We are given an ‘N*M’ matrix. Every cell of the matrix has some chocolates on it, mat[i][j] gives us the number of chocolates.
     * We have two friends ‘Alice’ and ‘Bob’. initially, Alice is standing on the cell(0,0) and Bob is standing on the cell(0, M-1).
     * Both of them can move only to the cells below them in these three directions:
     * to the bottom cell (↓), to the bottom-right cell(↘), or to the bottom-left cell(↙).
     *
     * When Alica and Bob visit a cell, they take all the chocolates from that cell with them.
     * It can happen that they visit the same cell, in that case, the chocolates need to be considered only once.
     *
     * They cannot go out of the boundary of the given matrix, we need to return the maximum number of chocolates that Bob and Alice can together collect.
     *
     */
    @Important
    fun collectChocolates(matrix: Array<IntArray>): Int {
        val m = matrix.size
        val n = matrix[0].size

        // recursive {top - down} and memo
        val memo = Array(m) { Array(n) { IntArray(n) { -1 } } }
        fun f(i: Int, j1: Int, j2: Int): Int {
            if (i == 0) {
                return if (j1 == j2) matrix[i][j1]
                else matrix[i][j1] + matrix[i][j2]
            }
            if (memo[i][j1][j2] != -1) return memo[i][j1][j2]
            var maxi = Int.MIN_VALUE
            for (d1 in -1..1) {
                for (d2 in -1..1) {
                    if (j1 + d1 in 0..n - 1 && j2 + d2 in 0..n - 1) {
                        val pick = if (j1 == j2) matrix[i][j1] + f(i - 1, j1 + d1, j2 + d2)
                        else matrix[i][j1] + matrix[i][j2] + f(i - 1, j1 + d1, j2 + d2)
                        maxi = maxOf(maxi, pick)
                    }
                }
            }
            memo[i][j1][j2] = maxi
            return maxi
        }

//        return f(m - 1, 0, n - 1)

        // tabulation {bottom-up} and iterative
        val dp = Array(m) { Array(n) { IntArray(n) { 0 } } }
        for (i in 0..m - 1) {
            for (j1 in 0..n - 1) {
                for (j2 in 0..n - 1) {
                    if (i == 0) {
                        dp[i][j1][j2] = if (j1 == j2) matrix[i][j1] else matrix[i][j1] + matrix[i][j2]
                    } else {
                        var maxi = Int.MIN_VALUE
                        for (d1 in -1..1) {
                            for (d2 in -1..1) {
                                if (j1 + d1 in 0..n - 1 && j2 + d2 in 0..n - 1) {
                                    val pick = if (j1 == j2) matrix[i][j1] + dp[i - 1][j1 + d1][j2 + d2]
                                    else matrix[i][j1] + matrix[i][j2] + dp[i - 1][j1 + d1][j2 + d2]
                                    maxi = maxOf(maxi, pick)
                                }
                            }
                        }
                        dp[i][j1][j2] = maxi
                    }
                }
            }
        }

//        return dp[m - 1][0][n - 1]

        //space optimized
        var prev: Array<IntArray>? = null
        for (i in 0..m - 1) {
            val cur = Array(n) { IntArray(n) { 0 } }
            for (j1 in 0..n - 1) {
                for (j2 in 0..n - 1) {
                    if (i == 0) {
                        cur[j1][j2] = if (j1 == j2) matrix[i][j1] else matrix[i][j1] + matrix[i][j2]
                    } else {
                        var maxi = Int.MIN_VALUE
                        for (d1 in -1..1) {
                            for (d2 in -1..1) {
                                if (j1 + d1 in 0..n - 1 && j2 + d2 in 0..n - 1) {
                                    val pick = if (j1 == j2) matrix[i][j1] + prev!![j1 + d1][j2 + d2]
                                    else matrix[i][j1] + matrix[i][j2] + prev!![j1 + d1][j2 + d2]
                                    maxi = maxOf(maxi, pick)
                                }
                            }
                        }
                        cur[j1][j2] = maxi
                    }
                }
            }
            prev = cur
        }
        return prev!![0][n - 1]
    }

}

fun main() {
    val obj = `2D-3D-Grid-DP`()
//    println(obj.maxMeritPoints(arrayOf(intArrayOf(1, 2, 5), intArrayOf(3, 3, 1), intArrayOf(3, 3, 3))))
//    println(obj.uniquePaths(3, 2))
//    println(obj.uniquePathsInMaze(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0))))
//    println(obj.minPathSum(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))))
//    println(obj.minPathSumInTriangle(listOf(listOf(2), listOf(3, 4), listOf(6, 5, 7), listOf(4, 1, 8, 3))))
    println(obj.minPathSumFromFirstRowToLastRow(arrayOf(intArrayOf(2, 1, 3), intArrayOf(6, 5, 4), intArrayOf(7, 8, 9))))
}