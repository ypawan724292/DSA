package dsa.binarySearch

import annotations.Revise

class `2D` {

    /**
     * Row with max 1s in a sorted matrix
     *
     * Example :
     * Input: mat[][] = { {0, 0, 0, 1},
     *                   {0, 1, 1, 1},
     *                   {1, 1, 1, 1},
     *                   {0, 0, 0, 0} }
     *
     * Output: 2
     */
    fun rowWithMax1s(matrix: Array<IntArray>): Int {
        // here we can use first occurrence of 1 in each row with the help lower bound

        val m = matrix.size
        val n = matrix[0].size
        var maxRow = 0
        var maxCount = 0

        fun lowerBound(arr: IntArray, k: Int): Int {
            // arr[mid] >= k
            var low = 0
            var high = arr.lastIndex
            var res = 0
            while (low <= high) {
                val mid = low + (high - low) / 2
                if (arr[mid] < k) {
                    low = mid + 1
                } else {
                    res = mid
                    high = mid - 1
                }

            }
            return res
        }
        for (i in 0 until m) {
            val index = lowerBound(matrix[i], 1)
            val count = n - index
            if (count > maxCount) {
                maxCount = count
                maxRow = i
            }
        }

        return maxRow
    }

    /**
     * Find Target in Matrix
     */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return false
        val m = matrix.size
        val n = matrix[0].size
        var low = 0
        var high = m * n - 1
        while (low <= high) {
            val mid = low + (high - low) / 2
            val midValue = matrix[mid / n][mid % n]
            if (midValue == target) return true
            if (midValue < target) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return false
    }

    /**
     * Problem Statement:
     * You have been given a 2-D array 'mat' of size 'N x M' where 'N' and 'M' denote the number of rows and columns, respectively.
     * The elements of each row and each column are sorted in non-decreasing order.
     * But, the first element of a row is not necessarily greater than the last element of the previous row (if it exists).
     * You are given an integer ‘target’, and your task is to find if it exists in the given 'mat' or not.
     *
     * Example 1:
     * Input: mat =
     * [1, 3, 5],
     * [7, 9, 11],
     * [13, 15, 17]
     *
     * target = 9
     *
     */
    @Revise
    fun searchMatrix2(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return false
        val m = matrix.size
        val n = matrix[0].size
        var row = 0
        var col = n - 1
        // we are traversing from top right corner i.e 0,n-1
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) return true
            if (matrix[row][col] < target) {
                row++
            } else {
                col--
            }
        }
        return false
    }

    /**
     * Median of Row Wise Sorted Matrix
     *
     *
     * Problem Statement: Given a row-wise sorted matrix of size MXN,
     * where M is no. of rows and N is no. of columns, find the median in the given matrix.
     *
     * Note: MXN is odd.
     *
     * Example 1:
     * Input Format:M = 3, N = 3, matrix[][] =
     *
     *                     1 4 9
     *                     2 5 6
     *                     3 8 7
     *
     * Result: 5
     * Explanation:  If we find the linear sorted array, the array becomes 1 2 3 4 5 6 7 8 9. So, median = 5
     * Example 2:
     * Input Format:M = 3, N = 3, matrix[][] =
     *
     *                     1 3 8
     *                     2 3 4
     *                     1 2 5
     *
     * Result: 3
     * Explanation:  If we find the linear sorted array, the array becomes 1 1 2 2 3 3 4 5 7 8. So, median = 3
     */
    @Revise
    fun median(matrix: Array<IntArray>): Int {

        fun upperBound(arr: IntArray, k: Int): Int {
            // arr[mid] <= k
            var low = 0
            var high = arr.lastIndex
            var res = 0
            while (low <= high) {
                val mid = low + (high - low) / 2
                if (arr[mid] <= k) {
                    res = mid
                    low = mid + 1
                } else {
                    high = mid - 1
                }

            }
            return res
        }

        val m = matrix.size
        val n = matrix[0].size
        var low = Int.MAX_VALUE
        var high = Int.MIN_VALUE
        for (i in 0 until m) {
            low = minOf(low, matrix[i][0])
            high = maxOf(high, matrix[i][n - 1])
        }
        val desired = (m * n + 1) / 2
        while (low < high) {
            val mid = low + (high - low) / 2
            var count = 0
            for (i in 0 until m) {
                count += upperBound(matrix[i], mid)
            }
            if (count < desired) {
                low = mid + 1
            } else {
                high = mid
            }
        }
        return low
    }
}