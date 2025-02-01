package dsa.dp

import annotations.Revise

class `Squares-Dp` {
    /**
     * Largest Area in a Histogram
     * Use the stack monotonically increasing stack to solve this problem
     */
    fun largestArea(arr: IntArray): Int {
        val n = arr.size
        var res = Int.MIN_VALUE
        for (i in 0..n - 1) {
            var min = Int.MAX_VALUE
            for (j in 0..i) {
                min = minOf(min, arr[j])
                res = maxOf(res, min * (j - i + 1))
            }
        }
        return res
    }

    /**
     * Problem Statement: Given a row X cols binary matrix filled with 0's and 1's,
     * find the largest rectangle containing only 1's and return its area.
     *
     *
     * Input: matrix = [[1,0,1,0,0],[1,0,1,1,1],[1,1,1,1,1],[1,0,0,1,0]]
     * Output: 6
     */
    fun maximalRectangle(matrix: Array<IntArray>): Int {
        /**
         * Intution :
         * We will calculate histogram for each row and then find the largest area in the histogram
         *
         */
        val n = matrix.size
        val m = matrix[0].size
        val histogram = IntArray(m)
        var res = Int.MIN_VALUE
        for (i in 0..n - 1) {
            for (j in 0..m - 1) {
                if (matrix[i][j] == 0) {
                    //reset the hieght of the jth column to 0
                    histogram[j] = 0
                } else {
                    histogram[j] += 1
                }
            }
            val area = largestArea(histogram)
            res = maxOf(res, area)
        }

        return res
    }


    /**
     * Problem Statement: Given an n * m matrix of ones and zeros,
     * return how many square submatrices have all ones.
     *
     * Example 1:
     * Input: matrix = [
     * [0,1,1,1],
     * [1,1,1,1],
     * [0,1,1,1]]
     *
     * Output: 15
     *
     */
    fun countSquares(matrix: Array<IntArray>): Int {

        /*
        * Intution:
        * We will use the dynamic programming to solve this problem
        * We will create a dp array of the same size as the matrix
        * dp[i][j] will store the maximum size of the square submatrices ending at matrix[i][j]
        *
         */
        var res = 0

        val m = matrix.size
        val n = matrix[0].size
        val dp = Array(m) { IntArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j]
                } else {
                    if (matrix[i][j] == 1) {
                        dp[i][j] = 1 + minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
                    } else {
                        dp[i][j] = 0
                    }
                }
                res += dp[i][j]
            }
        }


        return res
    }

    /**
     * 1139. Largest 1-Bordered Square
     * Medium
     * Topics
     * Companies
     * Hint
     * Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border,
     * or 0 if such a subgrid doesn't exist in the grid.
     *
     *
     *
     * Example 1:
     *
     * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
     * Output: 9
     * Example 2:
     *
     * Input: grid = [[1,1,0,0]]
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= grid.length <= 100
     * 1 <= grid[0].length <= 100
     * grid[i][j] is 0 or 1
     */
    @Revise
    fun largest1BorderedSquare(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val left = Array(m) { IntArray(n) }
        val top = Array(m) { IntArray(n) }

        var res = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 1) {
                    left[i][j] = if (j == 0) 1 else left[i][j - 1] + 1
                    top[i][j] = if (i == 0) 1 else top[i - 1][j] + 1
                }
            }
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                var len = minOf(left[i][j], top[i][j])
                while (len > res) {
                    val leftDown = top[i][j - len + 1]
                    val topRight = left[i - len + 1][j]
                    if (leftDown >= len && topRight >= len) {
                        res = len
                    }
                    len--
                }
            }
        }

        return res * res
    }

}