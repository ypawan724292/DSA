package math

class Geometry {

    /**
     * Given an m x n matrix of integers matrix, if an element is 0, set its entire row and column to 0's.
     *
     * You must update the matrix in-place.
     *
     * Follow up: Could you solve it using O(1) space?
     */
    fun setZeroes(matrix: Array<IntArray>) {
        val m = matrix.size
        val n = matrix[0].size

        //using extra space
        val rows = BooleanArray(m)
        val cols = BooleanArray(n)

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == 0) {
                    rows[i] = true
                    cols[j] = true
                }
            }
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0
                }
            }
        }


        //using O(1) space

        var isCol = false
        var isRow = false

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == 0) {
                    if (i == 0) isRow = true
                    if (j == 0) isCol = true
                    matrix[0][j] = 0
                    matrix[i][0] = 0
                }
            }
        }


        for (i in 1 until m) {
            for (j in 1 until n) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0
                }
            }
        }

        if (isRow) for (j in 0 until n) matrix[0][j] = 0
        if (isCol) for (i in 0 until m) matrix[i][0] = 0
    }


    /**
     * Given a square n x n matrix of integers matrix, rotate it by 90 degrees clockwise.
     *
     * You must rotate the matrix in-place. Do not allocate another 2D matrix and do the rotation.
     */
    fun rotateImageBy90(matrix: Array<IntArray>) {
        val n = matrix.size
        // find the transpose of the matrix meaning swap the rows and columns

        for (i in 0..n - 1) {
            for (j in i..n - 1) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = temp
            }
        }

        // reverse the rows
        for (i in 0..n - 1) {
            for (j in 0..n / 2 - 1) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[i][n - j - 1]
                matrix[i][n - j - 1] = temp
            }
        }
    }


    /**
     * Spiral Matrix
     * Given an m x n matrix of integers matrix, return a list of all elements within the matrix in spiral order.
     */

    fun spiralMatrix(mat: Array<IntArray>): List<Int> {
        val m = mat.size
        val n = mat[0].size


        var left = 0
        var right = n - 1
        var top = 0
        var bottom = m - 1

        val result = mutableListOf<Int>()

        var direction = 0

        while (left <= right && top <= bottom) {
            when (direction) {
                0 -> {
                    for (i in left..right) {
                        result.add(mat[top][i])
                    }
                    top++
                }
                1 -> {
                    for (i in top..bottom) {
                        result.add(mat[i][right])
                    }
                    right--
                }
                2 -> {
                    for (i in right downTo left) {
                        result.add(mat[bottom][i])
                    }
                    bottom--
                }
                3 -> {
                    for (i in bottom downTo top) {
                        result.add(mat[i][left])
                    }
                    left++
                }
            }
            direction = (direction + 1) % 4
        }

        return result

    }

    /**
     * 1886. Determine Whether Matrix Can Be Obtained By Rotation
     * Given two n x n binary matrices mat and target,
     * return true if it is possible to make mat equal to target by rotating mat in 90-degree increments,
     * or false otherwise.
     *
     * Example 1:
     * Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
     * Output: true
     */
    fun findRotation(mat: Array<IntArray>, target: Array<IntArray>): Boolean {
        val m = mat.size
        val n = mat[0].size
        val arr = BooleanArray(4) { true }
        for (i in 0..m-1) {
            for (j in 0..n-1) {
                if (mat[i][j] != target[i][j]) arr[0] = false
                if (mat[i][j] != target[m-i-1][n-j-1]) arr[2] = false
                if (mat[i][j] != target[j][n-i-1]) arr[1] = false
                if (mat[i][j] != target[m-j-1][i]) arr[3] = false
            }
        }
        return arr[0] || arr[1] || arr[2] || arr[3]
    }
}