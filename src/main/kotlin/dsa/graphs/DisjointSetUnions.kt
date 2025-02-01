package dsa.graphs

import annotations.Important
import java.util.*


class DisjointSetUnions {


    inner class BruteForce {
        private val adjList = mutableMapOf<Int, MutableList<Int>>()

        /*
        * Union operation
        * Time complexity: O(1)
         */
        fun union(edge: IntArray) {
            val (u, v) = edge
            adjList[u] = adjList.getOrDefault(u, mutableListOf()).apply { add(v) }
        }

        private fun dfs(node: Int, visited: MutableSet<Int>) {
            visited.add(node)
            for (neighbour in adjList.getOrDefault(node, mutableListOf())) {
                if (neighbour !in visited) {
                    dfs(neighbour, visited)
                }
            }
        }

        /*
         * find operation
         * Time complexity: O(V+ E)
         */
        fun checkIfUAndVInSame(u: Int, v: Int): Boolean {
            val visited = mutableSetOf<Int>()
            dfs(u, visited)
            return v in visited
        }

    }


    inner class Parent(n: Int) {
        private val parent = IntArray(n) { -1 }

        /*
         * Union operation
         * Time complexity: O(N)
         */

        fun union(edge: IntArray) {
            val (u, v) = edge
            val parentU = find(u)
            val parentV = find(v)
            if (parentU != parentV) {
                parent[parentU] = parentV
            }
        }

        /**
         * find operation
         * Time complexity: O(H) where H is the height of the tree in skew tree
         * In the worst case, the height of the tree can be n-1
         */
        private fun find(node: Int): Int {
            if (parent[node] == -1) return node
            return find(parent[node])
        }

        /*
         * find operation
         * Time complexity: O(N)
         */
        fun checkIfUAndVInSame(u: Int, v: Int): Boolean {
            return find(u) == find(v)
        }
    }


    inner class Rank(n: Int) {

        /*
         * we use the rank array to keep track of the size of the tree and make tree balanced
         * This is called union by rank
         */
        private val parent = IntArray(n) { -1 }
        private val rank = IntArray(n) { 1 }

        /*
         * Union operation
         * Time complexity: O(logN)
         */
        fun union(edge: IntArray) {
            val (u, v) = edge
            val parentU = find(u)
            val parentV = find(v)
            if (parentU != parentV) {
                if (rank[parentU] > rank[parentV]) {
                    parent[parentV] = parentU
                } else if (rank[parentU] < rank[parentV]) {
                    parent[parentU] = parentV
                } else {
                    parent[parentU] = parentV
                    rank[parentV] += 1
                }
            }
        }

        /**
         * find operation
         * Time complexity: O(logN) where H is the height of the tree
         * In the worst case, the height of the tree can be n-1
         */
        private fun find(node: Int): Int {
            if (parent[node] == -1) return node
            return find(parent[node])
        }

        /*
         * find operation
         * Time complexity: O(logN)
         */
        fun checkIfUAndVInSame(u: Int, v: Int): Boolean {
            return find(u) == find(v)
        }
    }

    /**
     *
     *  DSU or Disjoint Set Union is a data structure that keeps track of a set of elements
     *  partitioned into a number of disjoint (non-overlapping) subsets.
     *
     *
     *  The main points:
     *  Disjoint Set Union (DSU) or Union-Find data structure is typically used for undirected graphs.
     *  It is designed to manage and merge disjoint sets, which is useful for detecting cycles in undirected graphs,
     *  finding connected components, and other similar tasks.
     *
     *  For directed graphs, other algorithms and data structures, such as Depth-First Search (DFS), Breadth-First Search (BFS),
     *  and topological sorting, are more appropriate for tasks like cycle detection,
     *  finding strongly connected components, and shortest path calculations.
     *
     */

    inner class DSU(n: Int) {
        val parent = IntArray(n) { it } // Initialize each node as its own parent
        val rank = IntArray(n) { 1 }   // Initialize rank to 1 for all nodes

        /*
         * Union operation
         * Time complexity: O(α(n)), where α(n) is the inverse Ackermann function
         */
        fun union(u: Int, v: Int): Boolean {
            val rootU = find(u)
            val rootV = find(v)

            if (rootU != rootV) {
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV
                } else {
                    parent[rootV] = rootU
                    rank[rootU] += 1
                }
                return true
            }
            return false
        }

        /*
         * Find operation with path compression
         * Time complexity: amortized O(1)
         */
        fun find(x: Int): Int {
            if (x < 0 || x >= parent.size) throw IllegalArgumentException("Invalid node index: $x")
            if (parent[x] != x) {
                parent[x] = find(parent[x]) // Path compression
            }
            return parent[x]
        }
    }

    /**
     * When size is not fixed
     */
    inner class DSUWithMap {
        val parent = mutableMapOf<Int, Int>()
        val rank = mutableMapOf<Int, Int>()

        fun union(u: Int, v: Int): Boolean {
            val c = find(u)
            val d = find(v)
            if (c != d) {
                val rankC = rank.getOrDefault(c, 0)
                val rankD = rank.getOrDefault(d, 0)
                if (rankC > rankD) {
                    parent[d] = c
                } else if (rankC < rankD) {
                    parent[c] = d
                } else {
                    parent[d] = c
                    rank[c] = rankC + 1
                }
                return true
            }
            return false
        }

        fun find(x: Int): Int {
            if (!parent.containsKey(x)) {
                parent[x] = x // Initialize parent for new element
                rank[x] = 0   // Initialize rank for new element
            }
            if (parent[x] != x) {
                parent[x] = find(parent[x]!!) // Path compression
            }
            return parent[x]!!
        }
    }


    /**
     * Minimum Spanning Tree (MST) is a subset of the edges of a connected,
     * edge-weighted graph that connects all the vertices together,
     * in such a way that the total edge weight is minimized.
     * Krushkal's algorithm to find the minimum spanning tree
     * Time complexity: O(ElogE)
     *
     */
    fun kruskalMST(n: Int, edges: Array<IntArray>): List<IntArray> {
        /**
         * Here we are using DSU to find the minimum spanning tree
         * 1. Sort the edges by weight
         * 2. Iterate over the edges and add the edge to the result if u,v are not same connected component
         * 3. for this we are using DSU
         * 4. return the result
         */
        val dsu = DSU(n)
        val result = mutableListOf<IntArray>()
        edges.sortBy { it[2] } // Sort edges by weight

        for (edge in edges) {
            val (u, v, weight) = edge
            if (dsu.union(u, v)) {
                result.add(edge)
            }
        }

        return result
    }


    /**
     * This method helps to connect two nodes in the edge
     */
    fun dsuOnEdges(n: Int, edges: Array<IntArray>) {
        val dsu = DSU(n)
        for (edge in edges) {
            val (u, v) = edge
            dsu.union(u, v)
        }

        var connected = 0
        for (root in 0..n - 1) {
            if (dsu.find(root) == root)
                connected++
        }
    }

    /**
     *
     */
    fun dsuOnMatrix(grid: Array<IntArray>) {
        val m = grid.size
        val n = grid[0].size

        val dsu = DSU(n * m)

        val dir = listOf(
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(-1, 0)
        )

        // TC: O(m*n* alpha(m*n))
        for (i in 0..m - 1) {
            for (j in 0..n - 1) {
                if (grid[i][j] == 1) {
                    for (d in dir) {
                        val ni = i + d[0]
                        val nj = j + d[1]
                        if (ni in 0 until m && nj in 0 until n && grid[ni][nj] == 1) {
                            dsu.union(i * n + j, ni * n + nj)
                        }
                    }
                }
            }
        }

        var connectedComp = 0
        for (root in 0..n * m - 1) {
            if (root == dsu.find(root))
                connectedComp++
        }
    }


    /**
     * There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a
     * network where connections[i] = [ai, bi] represents a connection between computers ai and bi.
     * Any computer can reach any other computer directly or indirectly through the network.
     *
     * You are given an initial computer network connections. You can extract certain cables between
     * two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.
     *
     * Return the minimum number of times you need to do this in order to make all the computers connected.
     * If it is not possible, return -1.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: n = 4, connections = [[0,1],[0,2],[1,2]]
     * Output: 1
     * Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
     * Example 2:
     *
     *
     * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
     * Output: 2
     * Example 3:
     *
     * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
     * Output: -1
     * Explanation: There are not enough cables.
     *
     *
     * Constraints:
     *
     * 1 <= n <= 105
     * 1 <= connections.length <= min(n * (n - 1) / 2, 105)
     * connections[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * There are no repeated connections.
     * No two computers are connected by more than one cable.
     */
    fun minCostToConnectAllComputers(n: Int, connections: Array<IntArray>): Int {/*
         1. union all the computers which are not connected
         2. connect in the same component
         3 will inc for redudant connect i.e cost
         4. then we will find the number comp
         5. to connect n components we need n-1 connections ex: 3 components need 2 edges
         6. if redandant connect > required connect then return -1
         7. else return comp - 1
         */
        val dsu = DSU(n)
        var cost = 0
        for (connect in connections) {
            val (u, v) = connect
            if (!dsu.union(u, v)) {
                cost++
            }
        }

        var comp = 0
        for (i in 0..n - 1) {
            if (dsu.find(i) == i) {
                comp++
            }
        }
        return if (cost < comp - 1) -1 else comp - 1
    }


    /**
     * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
     *
     * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
     *
     * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
     * return the largest possible number of stones that can be removed so that there are no stones sharing.
     *
     *
     *
     * Example 1:
     *
     * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
     * Output: 5
     * Explanation: One way to remove 5 stones is as follows:
     * 1. Remove stone [2,2] because it shares the same row as [2,1].
     * 2. Remove stone [2,1] because it shares the same column as [0,1].
     * 3. Remove stone [1,2] because it shares the same row as [1,0].
     * 4. Remove stone [1,0] because it shares the same column as [0,0].
     * 5. Remove stone [0,1] because it shares the same row as [0,0].
     * Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
     * Example 2:
     *
     * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
     * Output: 3
     * Explanation: One way to make 3 moves is as follows:
     * 1. Remove stone [2,2] because it shares the same row as [2,0].
     * 2. Remove stone [2,0] because it shares the same column as [0,0].
     * 3. Remove stone [0,2] because it shares the same row as [0,0].
     * Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
     * Example 3:
     *
     * Input: stones = [[0,0]]
     * Output: 0
     * Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
     *
     *
     * Constraints:
     *;';
     * 1 <= stones.length <= 1000
     * 0 <= xi, yi <= 104
     * No two stones are at the same coordinate point.
     */
    @Important
    fun removeStones(stones: Array<IntArray>): Int {
        /*
        Each stone is represented by a node, whose initial "parent" is itself, indicating that it is initially in its own connected component.
        Iterate over all stones. For each stone (x, y), unify the component containing x with the component containing y + n
        (we offset y by n to avoid collisions between row and column indices as they could be the same).
        After all stones have been iterated over, we count unique representatives of the connected components which are the parent nodes
        for the rows and columns.
        The answer is the total number of stones minus the number of unique representatives (connected components).


                Let's consider a small example with 5 stones to illustrate the solution approach.

                Assume we have n = 5 stones at the following coordinates on a 2D plane: [[0,0], [0,2], [1,1], [2,0], [2,2]].
                So our stones array looks like this: stones = [[0,0], [0,2], [1,1], [2,0], [2,2]].

                        Steps According to the Solution Approach:
                        Initialization: We create an array p of size 2*n = 10.
                        This array will help us keep track of the parent of each coordinate considering both x and y coordinates.

                        The find function:
                        Let's define our find function which takes an index x and recursively
                        finds the representative of x's set, while applying path compression.

                        Unification Process:

                        Looking at stones[0] = [0,0], we unify the x-coordinate (0) with the y-coordinate (0 + 5) to avoid collision with the x-coordinates.
                        Then, stones[1] = [0,2], we unify 0 with 2 + 5.
                        For stones[2] = [1,1], we unify 1 with 1 + 5.
                        Next, stones[3] = [2,0], we unify 2 with 0 + 5.
                        Finally, stones[4] = [2,2], we unify 2 with 2 + 5.

                        Counting Connected Components:

                        We keep a set s and insert the root parent for each x-coordinate after unification.
                        After the unification, we have root parents for the x-coordinates: [0, 1, 2].
                        Let's assume union-find identifies 0, 1, and 2 as the roots for our example, meaning we have 3 connected components.
                        Calculating the Answer:

                        We have a total of 5 stones, and we've identified 3 connected components.
                        The maximum number of stones that we can remove is len(stones) - len(s) which is 5 - 3 = 2. Thus, we can remove a maximum of 2 stones while ensuring that no stone is isolated.
           */
        val n = stones.size
        val dsu = DSU(2 * n)
        for (stone in stones) {
            dsu.union(stone[0], stone[1] + n)
        }

        val set = mutableSetOf<Int>()
        for (stone in stones) {
            set.add(dsu.find(stone[0]))
        }
        return stones.size - set.size
    }

    /**
     *You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
     *
     * The rain starts to fall. At time t, the depth of the water everywhere is t.
     *
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
     *
     *
     * Constraints:
     *
     * n == grid.length
     * n == grid[i].length
     * 1 <= n <= 50
     * 0 <= grid[i][j] < n2
     * Each value grid[i][j] is unique.
     */
    fun swimInWater(grid: Array<IntArray>): Int {/*
        1. we will create a list of pair of elevation and index
        2. we will sort the list by elevation
        3. we will create a DSU
        4. we will iterate over the sorted list and union the cell which are connected
        5. we will return the time when we can reach the bottom right cell
         */
        val n = grid.size
        val pq = PriorityQueue<IntArray> { a, b -> a[2] - b[2] }
        for (i in 0 until n) {
            for (j in 0 until n) {
                pq.add(intArrayOf(i, j, grid[i][j]))
            }
        }
        val dsu = DSU(n * n)
        val directions = listOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
        while (pq.isNotEmpty()) {
            val (cRow, cCol, cEle) = pq.remove()

            directions.forEach {
                val nRow = cRow + it[0]
                val nCol = cCol + it[1]
                if (nRow in 0 until n && nCol in 0 until n && grid[nRow][nCol] <= cEle) {
                    dsu.union(cRow * n + cCol, nRow * n + nCol)
                }
            }

            if (dsu.find(0) == dsu.find(n * n - 1)) {
                return cEle
            }
        }
        return -1
    }


    /**
     * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
     *
     * Return the size of the largest island in grid after applying this operation.
     *
     * An island is a 4-directionally connected group of 1s.
     *
     *
     *
     * Example 1:
     *
     * Input: grid = [[1,0],[0,1]]
     * Output: 3
     * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
     * Example 2:
     *
     * Input: grid = [[1,1],[1,0]]
     * Output: 4
     * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
     * Example 3:
     *
     * Input: grid = [[1,1],[1,1]]
     * Output: 4
     * Explanation: Can't change any 0 to 1, only one island with area = 4.
     *
     *
     * Constraints:
     *
     * n == grid.length
     * n == grid[i].length
     * 1 <= n <= 500
     * grid[i][j] is either 0 or 1.
     */
    @Important
    fun largeIsland(grid: Array<IntArray>): Int {/*
        1. we will create a DSU
        2. we will iterate over the grid and union the cell which are connected
        3. we will create a map of component and its size
        4. we will iterate over the grid and find the largest island
        5. we will return the largest island
         */
        val n = grid.size
        val dsu = DSU(n * n)
        val directions = listOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
        val map = mutableMapOf<Int, Int>()

        //run bfs and union the connected cells
        for (i in 0 until n) {
            for (j in 0 until n) {
                val cur = i * n + j
                if (grid[i][j] == 1) {
                    directions.forEach {
                        val nRow = i + it[0]
                        val nCol = j + it[1]
                        val nCell = nRow * n + nCol
                        if (nRow in 0 until n && nCol in 0 until n && grid[nRow][nCol] == 1) {
                            dsu.union(cur, nCell)
                        }
                    }
                }
            }
        }

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (grid[i][j] == 1) {
                    val parent = dsu.find(i * n + j)
                    map[parent] = map.getOrDefault(parent, 0) + 1
                }
            }
        }

        var max = 0
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (grid[i][j] == 0) {
                    // it will find the unique parent of the connected cells
                    val set = mutableSetOf<Int>()
                    for (dir in directions) {
                        val nRow = i + dir[0]
                        val nCol = j + dir[1]
                        if (nRow in 0 until n && nCol in 0 until n && grid[nRow][nCol] == 1) {
                            val parent = dsu.find(nRow * n + nCol)
                            set.add(parent)
                        }
                    }
                    var size = 1
                    for (parent in set) {
                        size += map.getOrDefault(parent, 0)
                    }

                    max = maxOf(max, size)
                }
            }
        }
        return if (max == 0) n * n else max
    }


    /**
     * A 2d grid map of m rows and n columns is initially filled with water.
     * We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate,
     * count the number of islands after each addLand operation.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     *
     * Example:
     *
     * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
     * Output: [1,1,2,3]
     * Explanation:
     *
     * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
     *
     * 0 0 0
     * 0 0 0
     * 0 0 0
     * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
     *
     * 1 0 0
     * 0 0 0   Number of islands = 1
     * 0 0 0
     * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
     *
     * 1 1 0
     * 0 0 0   Number of islands = 1
     * 0 0 0
     * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
     *
     * 1 1 0
     * 0 0 1   Number of islands = 2
     * 0 0 0
     * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
     *
     * 1 1 0
     * 0 0 1   Number of islands = 3
     * 0 1 0
     * Follow up:
     *
     * Can you do it in time complexity O(k log mn), where k is the length of the positions?
     */

    fun numOfIslands(positions: Array<IntArray>, m: Int, n: Int): List<Int> {
        val dsu = DSU(m * n)
        val directions = listOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
        val grid = Array(m) { IntArray(n) { 0 } }
        val result = mutableListOf<Int>()
        var islandCount = 0

        for ((i, j) in positions) {
            val current = i * n + j
            if (grid[i][j] == 1) {
                result.add(islandCount)
                continue
            }
            grid[i][j] = 1
            islandCount++
            for (dir in directions) {
                val newRow = i + dir[0]
                val newCol = j + dir[1]
                val neighbor = newRow * n + newCol
                if (newRow in 0 until m && newCol in 0 until n && grid[newRow][newCol] == 1) {
                    if (dsu.union(current, neighbor)) {
                        islandCount--
                    }
                }
            }
            result.add(islandCount)
        }

        return result
    }


    /**
     * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
     *
     * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|,
     * where |val| denotes the absolute value of val.
     *
     * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
     * Output: 20
     * Explanation:
     *
     * We can connect the points as shown above to get the minimum cost of 20.
     * Notice that there is a unique path between every pair of points.
     * Example 2:
     *
     * Input: points = [[3,12],[-2,5],[-4,1]]
     * Output: 18
     *
     *
     * Constraints:
     *
     * 1 <= points.length <= 1000
     * -106 <= xi, yi <= 106
     * All pairs (xi, yi) are distinct.
     *
     * It is example of the krushkal's algorithm
     */
    fun minCostConnectPoints(points: Array<IntArray>): Int {
        val n = points.size
        val edges = mutableListOf<Triple<Int, Int, Int>>()

        // Generate all edges with their Manhattan distance as cost
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                val (x1, y1) = points[i]
                val (x2, y2) = points[j]
                val cost = kotlin.math.abs(x1 - x2) + kotlin.math.abs(y1 - y2)
                edges.add(Triple(i, j, cost)) // we consider the edge as i,j and cost
            }
        }

        // Sort edges by cost
        edges.sortBy { it.third }

        // Initialize DSU for Kruskal's algorithm
        val dsu = DSU(n)
        var res = 0

        // Process edges in ascending order of cost
        for ((u, v, cost) in edges) {
            if (dsu.union(u, v)) { // Add edge if it doesn't form a cycle
                res += cost
            }
        }

        return res
    }

    /**
     * Prims algorithm to find the minimum spanning tree
     */
    fun primsAlgorithm(n: Int, edges: Array<IntArray>): List<IntArray> {
        val adjList = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        for (edge in edges) {
            val (u, v, w) = edge
            adjList.getOrPut(u, { mutableListOf() }).add(Pair(v, w))
            adjList.getOrPut(v, { mutableListOf() }).add(Pair(u, w))
        }
        val visited = BooleanArray(n)
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        val result = mutableListOf<IntArray>()
        pq.add(Pair(0, 0))
        while (pq.isNotEmpty()) {
            val (node, weight) = pq.remove()
            if (visited[node]) continue
            visited[node] = true
            for (neighbour in adjList.getOrDefault(node, mutableListOf())) {
                val (v, w) = neighbour
                if (!visited[v]) {
                    pq.add(Pair(v, w))
                }
            }
        }
        return result
    }

    /**
     * We need a data structure to efficiently represent a city map in memory.
     * * The map starts entirely as empty lots.
     * Two types of points can exist: buildings or empty lots.
     * * The following two methods need to be efficient:
     * * hasBuilding(x, y): This method returns true if the point at coordinates (x, y) has a building.
     * * addBuilding(x, y): This method sets the point at coordinates (x, y) to have a building.
     * * countBlocks(): This method counts the number of distinct blocks at any given point in time.
     *
     * * A block is defined as a group of horizontally or vertically connected buildings.
     * * * Sample Test case 1:
     * * addBuilding(1, 1) -1
     * * hasBuilding(1, 1) -> true
     * * countBlocks() -> 1
     * * addBuilding(1, 3)
     * * hasBuilding(2, 1) -> false
     * * countBlocks() -> 2
     */
    inner class CityMap() {
        private val parent = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
        private val rank = mutableMapOf<Pair<Int, Int>, Int>()

        private val directions = listOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))

        fun hasBuilding(x: Int, y: Int): Boolean {
            return parent.contains(Pair(x, y))
        }

        fun addBuilding(x: Int, y: Int) {
            val cur = Pair(x, y)
            for (dir in directions) {
                val newRow = x + dir[0]
                val newCol = y + dir[1]
                val neighbor = Pair(newRow, newCol)
                if (parent.contains(neighbor)) {
                    union(cur, neighbor)
                }
            }
        }

        private fun find(x: Pair<Int, Int>): Pair<Int, Int> {
            if (parent[x] == null) return x
            parent[x] = find(parent[x]!!)
            return parent[x]!!
        }

        private fun union(x: Pair<Int, Int>, y: Pair<Int, Int>) {
            val parentX = find(x)
            val parentY = find(y)
            if (parentX != parentY) {
                if (rank[parentX]!! > rank[parentY]!!) {
                    parent[parentY] = parentX
                } else if (rank[parentX]!! < rank[parentY]!!) {
                    parent[parentX] = parentY
                } else {
                    parent[parentX] = parentY
                    rank[parentY] = rank[parentY]!! + 1
                }
            }
        }

        fun countBlocks(): Int {
            val set = mutableSetOf<Pair<Int, Int>>()
            for (key in parent.keys) {
                set.add(find(key))
            }
            return set.size
        }
    }
}

fun main() {
    val dsu = DisjointSetUnions()
    val cityMap = dsu.CityMap()
    cityMap.addBuilding(1, 1)
    println(cityMap.hasBuilding(1, 1)) // true
    println(cityMap.countBlocks()) // 1
    cityMap.addBuilding(1, 3)
    println(cityMap.hasBuilding(2, 1)) // false
    println(cityMap.countBlocks()) // 2
}
