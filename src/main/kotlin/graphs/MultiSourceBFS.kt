package graphs

import java.util.*

class MultiSourceBFS {

    /**
     * In these problems we have multiple sources and will be added to queue at the start before the BFS starts
     * Trie.kt
     */
    fun multiSourceBFS(grid: Array<CharArray>, sources: List<Pair<Int, Int>>): Int {
        val m = grid.size
        val n = grid[0].size

        fun bfs(sources: List<Pair<Int, Int>>): Int {
            val queue = ArrayDeque<Pair<Int, Int>>()
            sources.forEach { queue.add(it) }
            var level = 0
            val dir = listOf(
                intArrayOf(1, 0),
                intArrayOf(-1, 0),
                intArrayOf(0, 1),
                intArrayOf(0, -1)
            )
            val visited = Array(m) { BooleanArray(n) { false } }
            sources.forEach { (r, c) -> visited[r][c] = true }

            while (queue.isNotEmpty()) {
                val size = queue.size
                repeat(size) {
                    val (x, y) = queue.removeFirst()
                    dir.forEach {
                        val r = x + it[0]
                        val c = y + it[1]
                        if (r in 0 until m && c in 0 until n && !visited[r][c] && grid[r][c] != 'X') {
                            visited[r][c] = true
                            queue.add(Pair(r, c))
                        }
                    }
                }
                level++
            }
            return level
        }

        return bfs(sources)
    }

    /**
     * 407. Trapping Rain Water II
     * Hard
     * Topics
     * Companies
     * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map,
     * return the volume of water it can trap after raining.
     *
     *
     * Example 1:
     *
     * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
     * Output: 4
     * Explanation: After the rain, water is trapped between the blocks.
     * We have two small ponds 1 and 3 units trapped.
     * The total volume of water trapped is 4.
     *
     * Example 2:
     *
     * Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
     * Output: 10
     *
     * Constraints:
     *
     * m == heightMap.length
     * n == heightMap[i].length
     * 1 <= m, n <= 200
     * 0 <= heightMap[i][j] <= 2 * 104
     *
     *
     * [1,4,3,1,3,2],
     * [3,2,1,3,2,4],
     * [2,3,3,2,3,1]
     */
    fun trapRainWater(grid: Array<IntArray>): Int {
        // this is multi source problem weff initially add all the min height cells to the priority queue
        val m = grid.size
        val n = grid[0].size

        val visited = Array(m) { BooleanArray(n) { false } }
        val pq = PriorityQueue<IntArray> { a, b -> a[2] - b[2] }

        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (i == 0 || j == 0 || i == m - 1 || j == m - 1) {
                    visited[i][j] = true
                    pq.add(intArrayOf(i, j, grid[i][j]))
                }
            }
        }

        var water = 0


        val dir = listOf(
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(0, -1)
        )

        while (pq.isNotEmpty()) {
            repeat(pq.size) {
                val (curRow, curCol, curHeight) = pq.remove()
                dir.forEach { dir ->
                    val nextRow = curRow + dir[0]
                    val nextCol = curCol + dir[1]

                    if (nextRow in 0..m - 1 && nextCol in 0..n - 1 && visited[nextRow][nextCol]) {
                        visited[nextRow][nextCol] = true
                        water += maxOf(0, curHeight - grid[nextRow][nextCol])
                        val deligateHeight = maxOf(curHeight, grid[nextRow][nextCol])
                        pq.add(intArrayOf(nextRow, nextCol, deligateHeight))
                    }
                }
            }
        }
        return water
    }

    /**
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     *
     * The distance between two adjacent cells is 1.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
     * Output: [[0,0,0],[0,1,0],[0,0,0]]
     * Example 2:
     *
     *
     * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
     * Output: [[0,0,0],[0,1,0],[1,2,1]]
     *
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 104
     * 1 <= m * n <= 104
     * mat[i][j] is either 0 or 1.
     * There is at least one 0 in mat.
     */
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val m = mat.size
        val n = mat[0].size
        val res = Array(m) { IntArray(n) { -1 } }
        val q = ArrayDeque<IntArray>()

        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (mat[i][j] == 0) {
                    res[i][j] = 0
                    q.add(intArrayOf(i, j, 0))
                }
            }
        }
        val dir = listOf(
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(0, -1)
        )

        while (q.isNotEmpty()) {
            repeat(q.size) {
                val (i, j, d) = q.removeFirst()
                dir.forEach {
                    val r = i + it[0]
                    val c = j + it[1]
                    if (r in 0..m - 1 && c in 0..n - 1 && res[r][c] == -1) {
                        res[r][c] = d + 1
                        q.add(intArrayOf(r, c, d + 1))
                    }
                }
            }
        }

        return res
    }


    /**
     * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
     *
     * Connect: A cell is connected to adjacent cells horizontally or vertically.
     * Region: To form a region connect every 'O' cell.
     * Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
     * A surrounded region is captured by replacing all 'O's with 'X's in the input matrix board.
     *
     *
     *
     * Example 1:
     *
     * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
     *
     * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
     *
     * Explanation:
     *
     *
     * In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.
     *
     * Example 2:
     *
     * Input: board = [["X"]]
     *
     * Output: [["X"]]
     *
     *
     *
     * Constraints:
     *
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 200
     * board[i][j] is 'X' or 'O'.
     */
    fun solve(grid: Array<CharArray>): Unit {

        val m = grid.size
        val n = grid[0].size

        val q = ArrayDeque<IntArray>()
        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    if (grid[i][j] == 'O') {
                        q.add(intArrayOf(i, j))
                        grid[i][j] = 'S'
                    }
                }
            }
        }

        val dir = listOf(intArrayOf(0, 1), intArrayOf(-1, 0), intArrayOf(0, -1), intArrayOf(1, 0))


        while (q.isNotEmpty()) {
            val (curRow, curCol) = q.removeFirst()
            dir.forEach {
                val nextRow = curRow + it[0]
                val nextCol = curCol + it[1]
                if (nextRow in 0..m - 1 && nextCol in 0..n - 1 && grid[nextRow][nextCol] == 'O') {
                    grid[nextRow][nextCol] = 'S'
                    q.add(intArrayOf(nextRow, nextCol))
                }
            }
        }


        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (grid[i][j] == 'O') {
                    grid[i][j] = 'X'
                } else if (grid[i][j] == 'S') {
                    grid[i][j] = 'O'
                }
            }
        }
    }

    /**
     * You are given a m x n 2D grid initialized with these three possible values.
     *
     * -1 - A wall or an obstacle.
     * 0 - A gate.
     * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
     * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
     *
     * Example:
     *
     * Given the 2D grid:
     *
     * INF  -1  0  INF
     * INF INF INF  -1
     * INF  -1 INF  -1
     *   0  -1 INF INF
     * After running your function, the 2D grid should be:
     *
     *   3  -1   0   1
     *   2   2   1  -1
     *   1  -1   2  -1
     *   0  -1   3   4
     * Difficulty :Medium
     *
     * Amazon Bloomberg ByteDance Facebook Google Microsoft Uber
     * Problem
     */
    fun findNeareastGates(grid: Array<IntArray>) {
        val m = grid.size
        val n = grid[0].size
        val q = ArrayDeque<IntArray>()

        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (grid[i][j] == 0) {
                    q.add(intArrayOf(i, j))
                }
            }
        }
        val dir = listOf(
            intArrayOf(0, 1),
            intArrayOf(0, -1),
            intArrayOf(-1, 0),
            intArrayOf(1, 0)
        )
        var distance = 0
        while (q.isNotEmpty()) {
            ++distance
            repeat(q.size) {
                val (cRow, cCol) = q.removeFirst()
                dir.forEach { it ->
                    val nRow = cRow + it[0]
                    val nCol = cCol + it[1]

                    if (nRow in 0..m - 1 && nCol in 0..n - 1 &&
                        grid[nRow][nCol] == Int.MAX_VALUE
                    ) {
                        grid[nRow][nCol] = distance
                        q.add(intArrayOf(cRow, cCol))
                    }
                }
            }
        }
    }

    /**
     * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
     * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
     *
     * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
     *
     * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height.
     * Water can flow from any cell adjacent to an ocean into the ocean.
     *
     * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
     * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
     * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
     * [0,4]: [0,4] -> Pacific Ocean
     *        [0,4] -> Atlantic Ocean
     * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
     *        [1,3] -> [1,4] -> Atlantic Ocean
     * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
     *        [1,4] -> Atlantic Ocean
     * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
     *        [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
     * [3,0]: [3,0] -> Pacific Ocean
     *        [3,0] -> [4,0] -> Atlantic Ocean
     * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
     *        [3,1] -> [4,1] -> Atlantic Ocean
     * [4,0]: [4,0] -> Pacific Ocean
     *        [4,0] -> Atlantic Ocean
     * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
     * Example 2:
     *
     * Input: heights = [[1]]
     * Output: [[0,0]]
     * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
     *
     *
     * Constraints:
     *
     * m == heights.length
     * n == heights[r].length
     * 1 <= m, n <= 200
     * 0 <= heights[r][c] <= 105
     */

    fun findCellsFlowToPAndA(grid: Array<IntArray>): List<List<Int>> {
        // space and time complexity is O(m*n)
        val m = grid.size
        val n = grid[0].size
        val pacific = Array(m) { BooleanArray(n) }
        val atlantic = Array(m) { BooleanArray(n) }
        val pacificQueue = ArrayDeque<Pair<Int, Int>>()
        val atlanticQueue = ArrayDeque<Pair<Int, Int>>()

        for (i in 0 until m) {
            pacificQueue.add(Pair(i, 0))
            atlanticQueue.add(Pair(i, n - 1))
            pacific[i][0] = true
            atlantic[i][n - 1] = true
        }

        for (j in 0 until n) {
            pacificQueue.add(Pair(0, j))
            atlanticQueue.add(Pair(m - 1, j))
            pacific[0][j] = true
            atlantic[m - 1][j] = true
        }

        val directions = listOf(
            intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1)
        )

        fun bfs(queue: ArrayDeque<Pair<Int, Int>>, visited: Array<BooleanArray>) {
            while (queue.isNotEmpty()) {
                val (x, y) = queue.removeFirst()
                for (dir in directions) {
                    val newX = x + dir[0]
                    val newY = y + dir[1]
                    if (newX in 0 until m && newY in 0 until n &&
                        !visited[newX][newY] && grid[newX][newY] >= grid[x][y]
                    ) {
                        visited[newX][newY] = true
                        queue.add(Pair(newX, newY))
                    }
                }
            }
        }

        bfs(pacificQueue, pacific)
        bfs(atlanticQueue, atlantic)

        val result = mutableListOf<List<Int>>()
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(listOf(i, j))
                }
            }
        }

        return result
    }

    /**
     *
     * You are given an m x n grid where each cell can have one of three values:
     *
     * 0 representing an empty cell,
     * 1 representing a fresh orange, or
     * 2 representing a rotten orange.
     * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
     *
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
     * Output: 4
     * Example 2:
     *1
     * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
     * Output: -1
     * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
     * Example 3:
     *
     * Input: grid = [[0,2]]
     * Output: 0
     * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 10
     * grid[i][j] is 0, 1, or 2.
     */
    fun findTimeElapsedToRottenOranges(grid: Array<IntArray>): Int {
        // add all the 2 cells to q
        // run bfs to find the min steps to traverse the remaining cells
        // check if any 1 in the cells
        // if yes -1 else steps


        val m = grid.size
        val n = grid[0].size
        var fresh = 0

        val q = ArrayDeque<IntArray>()

        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (grid[i][j] == 2) {
                    q.add(intArrayOf(i, j))
                }
                if (grid[i][j] == 1) fresh++
            }

        }
        println(fresh)

        val dir = listOf(
            intArrayOf(0, -1),
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
        )

        var steps = 0

        while (q.isNotEmpty() && fresh > 0) {
            repeat(q.size) {
                val (cRow, cCol) = q.removeFirst()

                dir.forEach {
                    val nRow = cRow + it[0]
                    val nCol = cCol + it[1]


                    if (nRow in 0..m - 1 && nCol in 0..n - 1 &&
                        grid[nRow][nCol] == 1
                    ) {
                        grid[nRow][nCol] = 2
                        q.add(intArrayOf(nRow, nCol))
                        fresh--
                    }
                }

                steps++
            }
        }
        println(steps)
        return if (fresh > 0) -1 else steps

    }


    /**
     * Given a path in the form of a rectangular matrix having few landmines arbitrarily placed (marked as 0),
     * calculate length of the shortest safe route possible from any cell in the first column
     * to any cell in the last column of the matrix.
     * We have to avoid landmines and their four adjacent cells (left, right, above and below) as they are also unsafe.
     * We are allowed to move to only adjacent cells which are not landmines. i.e. the route cannot contains any diagonal moves.
     *
     *
     * Input:
     * A 12 x 10 matrix with landmines marked as 0
     *
     * [ 1  1  1  1  1  1  1  1  1  1 ]
     * [ 1  0  1  1  1  1  1  1  1  1 ]
     * [ 1  1  1  0  1  1  1  1  1  1 ]
     * [ 1  1  1  1  0  1  1  1  1  1 ]
     * [ 1  1  1  1  1  1  1  1  1  1 ]
     * [ 1  1  1  1  1  0  1  1  1  1 ]
     * [ 1  0  1  1  1  1  1  1  0  1 ]
     * [ 1  1  1  1  1  1  1  1  1  1 ]
     * [ 1  1  1  1  1  1  1  1  1  1 ]
     * [ 0  1  1  1  1  0  1  1  1  1 ]
     * [ 1  1  1  1  1  1  1  1  1  1 ]
     * [ 1  1  1  0  1  1  1  1  1  1 ]
     *
     * Output:
     * Length of shortest safe route is 13 (Highlighted in Bold)
     */
    fun shortestSafeRoute(matrix: Array<IntArray>): Int {
        val m = matrix.size
        val n = matrix[0].size
        val visited = Array(m) { BooleanArray(n) }
        val directions = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
        val queue = ArrayDeque<IntArray>()

        // Mark unsafe cells
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (matrix[i][j] == 0) {
                    directions.forEach { dir ->
                        val ni = i + dir[0]
                        val nj = j + dir[1]
                        if (ni in 0 until m && nj in 0 until n && matrix[ni][nj] == 1) {
                            matrix[ni][nj] = -1
                        }
                    }
                }
            }
        }

        // Add all safe cells in the first column to the queue
        for (i in 0 until m) {
            if (matrix[i][0] == 1) {
                queue.add(intArrayOf(i, 0))
                visited[i][0] = true
            }
        }

        var res = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val (curRow, curCol) = queue.removeFirst()
                if (curCol == n - 1) return res
                directions.forEach { dir ->
                    val nextRow = curRow + dir[0]
                    val nextCol = curCol + dir[1]
                    if (nextRow in 0 until m && nextCol in 0 until n && matrix[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                        queue.add(intArrayOf(nextRow, nextCol))
                        visited[nextRow][nextCol] = true
                    }
                }
            }
            res++
        }
        return -1
    }

}