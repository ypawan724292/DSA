package graphs

import annotations.Important
import annotations.Revise
import java.util.*
import kotlin.collections.ArrayDeque

/**
 * N.B - Shortest path , time , moves , cost where all edge weights are UNIT(SAME) , think BFS.
 */
class SingleSourceBFS {


    /**
     *1730. Shortest Path to Get Food
     * Description
     * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
     *
     * You are given an m x n character matrix, grid, of these different types of cells:
     *
     * '*' is your location. There is exactly one '*' cell.
     * '#' is a food cell. There may be multiple food cells.
     * 'O' is free space, and you can travel through these cells.
     * 'X' is an obstacle, and you cannot travel through these cells.
     * You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
     *
     * Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
     *
     *Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
     * Output: 3
     * Explanation: It takes 3 steps to reach the food.
     *
     * Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
     * Output: -1
     * Explanation: It is not possible to reach the food.
     *
     * Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
     * Output: 6
     * Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
     *
     *If  matrix of size mxn, then cell can be identified by i*n+j
     */
    @Important
    fun findShortestPath(grid: Array<CharArray>): Int {

        /*
        TC : O(m*n)
        Here starting cell is not at 0,0 ...so we need to identify by iterating over the matrix with conditions
        1. create visited cell set
        2. from each cell check for the food cell with bfs
        3.If  matrix of size mxn, then cell can be identified by i*n+j
        Time Complexity = O(m*n)
         */
        val m = grid.size
        val n = grid[0].size


        fun bfs(i: Int, j: Int): Int {
            val queue = ArrayDeque<Int>()
            queue.add(i * n + j)
            var level = 0
            val dir = listOf(
                intArrayOf(1, 0),
                intArrayOf(-1, 0),
                intArrayOf(0, 1),
                intArrayOf(0, -1),
            )
            while (queue.isNotEmpty()) {
                val size = queue.size
                repeat(size) {
                    val cell = queue.removeFirst()
                    val x = cell / n
                    val y = cell % n
                    dir.forEach {
                        val r = x + it[0]
                        val c = y + it[1]
                        if (grid[r][c] == '#') return level + 1
                        if (r in 0..m && c in 0..n && grid[r][c] == 'O') {
                            grid[r][c] = 'X'
                            queue.add(r * n + c)
                        }
                    }
                }
                level++
            }
            return -1
        }

        for (i in grid.indices) {
            for (j in grid.indices) {
                if (grid[i][j] == '*') {
                    grid[i][j] = 'X'
                    return bfs(i, j)
                }
            }
        }
        return -1
    }

    /**
     * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle).
     * You can move up, down, left, or right from and to an empty cell in one step.
     *
     * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1)
     * given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
     *
     *
     * Example:
     * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
     * Output: 6
     * Explanation:
     * The shortest path without eliminating any obstacle is 10.
     * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
     */
    fun findShortestPath(grid: Array<IntArray>, k: Int): Int {
        /**
         * Here the start cell is at 0,0 so need to run through matrix
         * 1. create maxRemElim 2d matrix for the given matrix to matain the remaining elimination
         * 2. add to q each cell with elimation power
         * 3. perfrom bfs
         * 4. check for if obstacle and maxRemElim with curCell  elimination power
         * Time Complexity = O(m*n)
         */
        val m = grid.size
        val n = grid[0].size

        val dir = listOf(
            intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1)
        )
        val maxRemElim = Array(m) { IntArray(n) { -1 } }
        val q = ArrayDeque<IntArray>()
        q.add(intArrayOf(0, 0, k))
        maxRemElim[0][0] = k
        var steps = 0

        while (q.isNotEmpty()) {
            repeat(q.size) {
                val (curNodeRow, curNodeCol, curNodeRemElim) = q.removeFirst()

                if (curNodeRow == m - 1 && curNodeCol == n - 1) return steps
                dir.forEach { dir ->
                    val nextNodeRow = curNodeRow + dir[0]
                    val nextNodeCol = curNodeCol + dir[1]


                    if (nextNodeRow in 0..m - 1 && nextNodeCol in 0..n - 1) {
                        //if obstacle
                        if (grid[nextNodeRow][nextNodeCol] == 1 && curNodeRemElim > 0 && curNodeRemElim > maxRemElim[nextNodeRow][nextNodeCol]) {

                            q.add(intArrayOf(nextNodeRow, nextNodeCol, curNodeRemElim - 1))
                            maxRemElim[nextNodeRow][nextNodeCol] = curNodeRemElim - 1
                        }

                        //if not obstacle
                        else if (grid[nextNodeRow][nextNodeCol] == 0 && curNodeRemElim > maxRemElim[nextNodeRow][nextNodeCol]) {
                            q.add(intArrayOf(nextNodeRow, nextNodeCol, curNodeRemElim))
                            maxRemElim[nextNodeRow][nextNodeCol] = curNodeRemElim
                        }
                    }
                }
            }
            steps++
        }

        return -1
    }


    /**
     *
     * You are given an n x n integer matrix grid where each value grid[i][j]
     * represents the elevation at that point (i, j).
     *
     * The rain starts to fall. At time t, the depth of the water everywhere is t.
     * You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
     * You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
     *
     * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
     *
     *
     *
     * Example 1:
     *
     *
     * Input: grid = [[0,2],[1,3]]
     * Output: 3
     * Explanation:
     * At time 0, you are in grid location (0, 0).
     * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
     * You cannot reach point (1, 1) until time 3.
     * When the depth of water is 3, we can swim anywhere inside the grid.
     * Example 2:
     *
     *
     * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
     * Output: 16
     * Explanation: The final route is shown.
     * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
     */
    fun swimInWater(grid: Array<IntArray>): Int {
        /**
         * Intuition:
         * Using BFS to find the minimum time to reach the destination.
         * We can only move to the cell where the elevation is less than or equal to the current time
         * bcuz at time t, the depth of the water everywhere is t.
         *
         */
        val m = grid.size
        val n = grid[0].size

        val pq = PriorityQueue<IntArray> { a, b -> a[2] - b[2] }

        val visited = Array(m) { BooleanArray(n) }

        pq.add(intArrayOf(0, 0, grid[0][0]))
        visited[0][0] = true

        val dir = listOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))

        while (pq.isNotEmpty()) {
            val (row, col, time) = pq.remove()

            if (row == m - 1 && col == n - 1) return time


            dir.forEach {
                val newRow = row + it[0]
                val newCol = col + it[1]

                if (newRow in 0..m - 1 && newCol in 0..n - 1 && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true
                    pq.add(intArrayOf(newRow, newCol, maxOf(time, grid[newRow][newCol])))
                }
            }
        }

        return -1
    }


}