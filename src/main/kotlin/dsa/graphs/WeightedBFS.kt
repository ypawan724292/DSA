package dsa.graphs

import annotations.Important

class WeightedBFS {

    /**
     *
     * 0-1 BFS is a specialized version of the Breadth-First Search (BFS) algorithm used for graphs
     * where edge weights are either 0 or 1. It efficiently finds the shortest path in such graphs
     * by using a deque (double-ended queue) to manage nodes. Nodes connected by edges with weight 0
     * are processed before those connected by edges with weight 1, ensuring an optimal path is found.
     *
     */
    fun zeroOneBFS(edges: Array<IntArray>, n: Int, source: Int) {
        /* First we create the adjList from  u->(v,w) & v->(u,w)
           1. Create dq
           2. create distance array for vertices with INT_MAX
           3. add source toq
           4. set source dist to 0
           5. while dq is not empty
           6. remove first and check for its neighbors
           7. if dist for neighbor is greater than dist for current + weight of edge
           8. update dist for neighbor
         */
        val adjList = createAdjList(edges)

        val dq = ArrayDeque<Int>()
        val dist = IntArray(n) { Int.MAX_VALUE }
        dq.add(source)
        dist[source] = 0

        while (dq.isNotEmpty()) {
            val cur = dq.removeFirst()
            adjList[cur]?.forEach { (nei, weight) ->
                if (dist[nei] > dist[cur] + weight) {
                    dist[nei] = dist[cur] + weight
                    if (weight == 0) {
                        dq.addFirst(nei)
                    } else {
                        dq.add(nei)
                    }
                }
            }

        }
    }

    /**
     * This is adjList creation function with weight
     */
    private fun createAdjList(edges: Array<IntArray>): Map<Int, List<Pair<Int, Int>>> {
        val adjList = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        for (edge in edges) {
            val (u, v, w) = edge

            adjList[u] = adjList.getOrDefault(u, mutableListOf()).apply {
                add(Pair(v, w))
            }
            adjList[v] = adjList.getOrDefault(v, mutableListOf()).apply {
                add(Pair(u, w))
            }
        }
        return adjList
    }

    /**
     *
     *
     * 1-k BFS or Dial Algorithm is a graph traversal algorithm that finds the shortest path in a graph
     * when k is small. It is an optimized version of Dijkstra's algorithm that uses a bucket queue
     *
     */
    @Important
    fun dialAlgorithm(edges: Array<IntArray>, n: Int, source: Int, maxWeight: Int): IntArray {
        val dist = IntArray(n) { Int.MAX_VALUE }
        val buckets = Array(maxWeight * (n - 1)) { ArrayDeque<Int>() }
        val adjList = createAdjList(edges)

        dist[source] = 0
        buckets[0].add(source)

        var currentBucket = 0

        while (buckets[currentBucket].isNotEmpty()) {
            val size = buckets[currentBucket].size
            repeat(size) {

                val cur = buckets[currentBucket].removeFirst()
                adjList[cur]?.forEach { (nei, weight) ->
                    val newDist = dist[cur] + weight

                    if (dist[nei] > newDist) {
                        dist[nei] = newDist
                        buckets[newDist].add(nei)
                    }
                }
            }
            currentBucket++
        }
        return dist
    }


    /**
     * You are given a 0-indexed 2D integer array grid of size m x n.
     * Each cell has one of two values:
     *
     * 0 represents an empty cell,
     * 1 represents an obstacle that may be removed.
     * You can move up, down, left, or right from and to an empty cell.
     *
     * Return the minimum number of obstacles to remove
     * so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).
     *
     *
     *
     * Example 1:
     *
     *
     * Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
     * Output: 2
     * Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
     * It can be shown that we need to remove at least 2 obstacles, so we return 2.
     * Note that there may be other ways to remove 2 obstacles to create a path.
     * Example 2:
     *
     *
     * Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
     * Output: 0
     * Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 105
     * 2 <= m * n <= 105
     * grid[i][j] is either 0 or 1.
     * grid[0][0] == grid[m - 1][n - 1] == 0
     *
     */
    @Important
    fun minObstaclesToRemove(grid: Array<IntArray>): Int {
        /*
        1. create distance array with max value
        2. create dq
        3.add start to dq
        4. set start distance to 0
        5. while dq is not empty
        6. remove first
        7. check for neighbors
        8. if distance for neighbor is greater than distance for current + weight of edge
        9. update distance for neighbor
        10. if weight is 0 add to first else add to last
         */
        val m = grid.size
        val n = grid[0].size
        val dist = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        val dq = ArrayDeque<IntArray>()

        dist[0][0] = 0
        dq.add(intArrayOf(0, 0))

        val dir = listOf(
            intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(1, 0), intArrayOf(-1, 0),
        )

        while (dq.isNotEmpty()) {
            val (cRow, cCol) = dq.removeFirst()

            dir.forEach {
                val nRow = cRow + it[0]
                val nCol = cCol + it[1]
                if (nRow in 0 until m && nCol in 0 until n) {
                    val newDistance = dist[cRow][cCol] + grid[nRow][nCol]
                    if (dist[nRow][nCol] > newDistance) {
                        dist[nRow][nCol] = newDistance
                        if (grid[nRow][nCol] == 0)
                            dq.addFirst(intArrayOf(nRow, nCol))
                        else
                            dq.add(intArrayOf(nRow, nCol))
                    }
                }
            }
        }

        return dist[m - 1][n - 1]

    }


    /**
     * BFS->0-1 BFS -> 1-K BFS (where k is small) -> dijkstra -> bellman-ford -> floyd-warshall
     * Shortest path from source to all other problems types
     * 1. If unweighted or same weighted use -> BFS
     * 2. if weights are binary or x or y use -> 0-1 BFS
     * 3. if weights are small use -> 1-K BFS or Dial Algorithm
     * 4. if weights are large use -> dijkstra
     * 5. if with negative weights use -> bellman-ford
     * 6. if all pairs shortest path use -> floyd-warshall {multiple sources}
     */

}