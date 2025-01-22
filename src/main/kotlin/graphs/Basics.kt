package graphs

import annotations.Important

/**
 * @link : https://docs.google.com/document/d/1bNslc9kOjZ2EiEYJNcdvxzoGJ3u_LpFHfu3G5AdYa1c/edit#heading=h.vvamagu18pwl
 */
class Basics {
    /*
    Graph : is a data structure that consists of a finite set of nodes (vertices) and a set of edges connecting them.
    There is no restriction on the number of edges between two vertices. Not a hierarchical data structure.

    Graph Terms:
    Vertex: Node in a graph.
    Edge: Connection between two nodes.
    Path: Sequence of nodes connected by edges.
    Cycle: Path that starts and ends at the same node.
    Adjacency: Two nodes are adjacent if they are connected by an edge.
    Degree: Number of edges connected to a node.
    Directed Edge: Edge with direction.
    Weighted Edge: Edge with weight.

    Graph Representation:
    Adjacency Matrix: 2D array where a[i][j] = 1 if there is an edge between i and j, 0 otherwise.
        -O(V^2) space complexity. O(1) time complexity to check if there is an edge between two nodes.
        Example:
        [
            [0, 1, 1, 0],
            [1, 0, 1, 0],
            [1, 1, 0, 1],
            [0, 0, 1, 0]
        ]

    Adjacency List: Array of linked lists where each element is a list of nodes adjacent to a vertex.
        -O(V+E) space complexity. O(V) time complexity to check if there is an edge between two nodes.
        Example:
        {
            0: [1, 2],
            1: [0, 2],
            2: [0, 1, 3],
            3: [2]
        }

    Object and pointers: Each node is an object with a list of pointers to other nodes.
        -O(V+E) space complexity. O(V) time complexity to check if there is an edge between two nodes.
        Example:
        {
            0: [1, 2],
            1: [0, 2],
            2: [0, 1, 3],
            3: [2]
        }




    Types of Graphs:
    1. Directed Graph: Edges have direction.
    2. Undirected Graph: Edges have no direction.
    3. Weighted Graph: Edges have weights.
    4. Unweighted Graph: Edges have no weights.
    5. Cyclic Graph: Contains a cycle.
    6. Acyclic Graph: Does not contain a cycle.
    7. Connected Graph: There is a path between every pair of vertices.
    8. Disconnected Graph: There is no path between every pair of vertices.
    9. Complete Graph: Every pair of vertices is connected by an edge.
    10. Sparse Graph: Few edges.
    11. Dense Graph: Many edges.
   */

    /*
     DFS: Depth First Search
        - Start from a node and visit all its neighbors before moving to the next node.
        - Uses a stack to keep track of nodes to visit.
        - Recursive implementation.
        - Time complexity: O(V+E)
        - Space complexity: O(V)
        - Applications: Topological sorting, cycle detection, path finding.
        - Not optimal for finding shortest path.
     */
    fun dfs(graph: Map<Int, List<Int>>, start: Int, visited: MutableSet<Int>) {
        visited.add(start)
        println(start)
        graph[start]?.forEach {
            if (it !in visited) {
                dfs(graph, it, visited)
            }
        }
    }

    fun dfsOnAdjMatrix(graph: Array<IntArray>, start: Int, visited: MutableSet<Int>) {
        visited.add(start)
        println(start)
        graph[start].forEachIndexed { index, i ->
            if (i == 1 && index !in visited) {
                dfsOnAdjMatrix(graph, index, visited)
            }
        }
    }

    /*
     BFS: Breadth First Search
        - Start from a node and visit all its neighbors before moving to the next level.
        - Uses a queue to keep track of nodes to visit.
        - Iterative implementation.
        - Time complexity: O(V+E)
        - Space complexity: O(V)
        - Applications: Shortest path, minimum spanning tree, social networking.
        - Optimal for finding shortest path.
     */
    fun bfs(graph: Map<Int, List<Int>>, start: Int, visited: MutableSet<Int>) {
        val queue = ArrayDeque<Int>()
        queue.add(start)
        visited.add(start)
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            println(node)
            graph[node]?.forEach {
                if (it !in visited) {
                    queue.add(it)
                    visited.add(it)
                }
            }
        }
    }

    fun checkConnectedComponents(graph: Map<Int, List<Int>>): Int {/*
        1. iterate over graph nodes
        2. check if node is not visited
        3. else perform dfs traversal and mark visited nodes
        4. increment components count when dfs is done
        5. return components count
        6. to check for complete graph, compare components count with graph size
         */
        val visited = mutableSetOf<Int>()
        var components = 0
        for (node in graph.keys) {
            if (node !in visited) {
                dfs(graph, node, visited)
                components++
            }
        }
        return components
    }

    /**
     * Whenever we find connected components, we can use DFS
     */
    @Important
    fun numberOfIslands(graph: Array<CharArray>): Int {
        fun dfs(i: Int, j: Int) {
            if (i !in graph.indices || j !in graph[0].indices || graph[i][j] == '0') return
            graph[i][j] = '0'
            dfs(i + 1, j)
            dfs(i - 1, j)
            dfs(i, j + 1)
            dfs(i, j - 1)
        }

        var count = 0

        for (i in graph.indices) {
            for (j in graph[0].indices) {
                if (graph[i][j] == '1') {
                    count++
                    dfs(i, j)
                }
            }
        }
        return count
    }

    fun findNumberOfLevels(graph: Map<Int, List<Int>>, start: Int): Int {
        val queue = ArrayDeque<Int>()
        queue.add(start)
        val visited = mutableSetOf<Int>()
        visited.add(start)
        var levels = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val node = queue.removeFirst()
                graph[node]?.forEach {
                    if (it !in visited) {
                        queue.add(it)
                        visited.add(it)
                    }
                }
            }
            levels++
        }
        return levels
    }

    /**
     * Whenever we find min moves, no of steps and shortest path with unit weight, we can use BFS
     */
    @Important
    fun findShortestPath(graph: Map<Int, List<Int>>, start: Int, end: Int): Int {
        val queue = ArrayDeque<Int>()
        queue.add(start)
        val visited = mutableSetOf<Int>()
        visited.add(start)
        var distance = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val node = queue.removeFirst()
                if (node == end) return distance+1
                graph[node]?.forEach {
                    if (it !in visited) {
                        visited.add(it)
                        queue.add(it)
                    }
                }
            }
            distance++
        }
        return -1
    }

    @Important
    fun findShortestPath(graph: Array<IntArray>, start: IntArray, end: IntArray): Int {
        val queue = ArrayDeque<IntArray>()
        queue.add(start)
        graph[start[0]][start[1]] = 1
        var distance = 0
        val directions = arrayOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val node = queue.removeFirst()
                if (node.contentEquals(end)) return distance
                directions.forEach { dir ->
                    val x = node[0] + dir[0]
                    val y = node[1] + dir[1]
                    if (x in graph.indices && y in graph[0].indices && graph[x][y] == 0) {
                        queue.add(intArrayOf(x, y))
                        graph[x][y] = 1
                    }
                }
            }
            distance++
        }
        return -1
    }

    /**
     * word path finding
     */
    fun findWord(board: Array<CharArray>, word: String): Boolean {
        /*
        1. iterate over board
        2. perform dfs on the board[i][j]
         */
//        var k = 0
//
//        fun dfs(i: Int, j: Int): Boolean {
//            if (i !in board.indices || j !in board[0].indices || board[i][j] != word[k]) return false
//            if (k > word.lastIndex) return false
//            k++
//
//            board[i][j] = board[i][j] - 100
//            return dfs(i + 1, j) || dfs(i - 1, j) || dfs(i, j + 1) || dfs(i, j - 1)
//        }
//        for (i in board.indices) {
//            for (j in board[0].indices) {
//                if (board[i][j] == word[k]) {
//                    if (dfs(i, j)) return true
//                }
//            }
//        }
//
//        return false

        fun dfs(i: Int, j: Int, k: Int): Boolean {
            if (i !in board.indices || j !in board[0].indices || board[i][j] != word[k]) return false
            if (k == word.lastIndex) return true
            board[i][j] = board[i][j] - 100
            val found = dfs(i + 1, j, k + 1) || dfs(i - 1, j, k + 1) || dfs(i, j + 1, k + 1) || dfs(i, j - 1, k + 1)
            board[i][j] = board[i][j] + 100
            return found
        }

        for (i in board.indices) {
            for (j in board[0].indices) {
                if (dfs(i, j, 0)) return true
            }
        }

        return false

    }

}

