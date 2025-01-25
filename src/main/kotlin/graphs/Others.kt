package graphs

class Others {


    /**
     * Concept : BellFord
     *
     * The Bellman-Ford algorithm is used for finding the shortest paths from a single source vertex
     * to all other vertices in a weighted graph.
     * It can handle graphs with negative weight edges, unlike Dijkstra's algorithm.
     * However, it cannot handle graphs with negative weight cycles.
     */

    fun bellmanFord(edges: Array<IntArray>, n: Int, source: Int): IntArray {
        /*
          Intution :
            1. Initialize the distance array with infinity and source with 0
            2. Relax the edges n-1 times bcuz the shortest path can have at most n-1 edges
            3. Check for negative cycle ,if the distance can be minimized further then return empty array


            Here Relaxing the edges means checking if the distance to the destination vertex can be minimized
            by taking the current edge.


            Time Complexity = O(n*m)
         */
        val dist = IntArray(n) { Int.MAX_VALUE }
        dist[source] = 0

        for (i in 1 until n) {
            for (j in edges.indices) {
                val (u, v, w) = edges[j]
                if (dist[u] != Int.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w
                }
            }
        }

        // Negative cycle is when the distance from a node to source decreases after n-1 iteration
        for (j in edges.indices) {
            val (u, v, w) = edges[j]
            if (dist[u] != Int.MAX_VALUE && dist[u] + w < dist[v]) {
                return intArrayOf()
            }
        }

        return dist
    }

    /**
     * Concept : Floyd Warshall
     *
     * Floyd Warshall algorithm is used to find the shortest paths between all pairs of nodes in a graph.
     *
     * It works for both directed and undirected graphs and also for graphs with negative edge weights.
     *
     * Example:
     * Input: edges = [[0,1,2],[1,2,3],[2,3,1]], n = 4
     *
     * Output:
     *  [[0,2,5,6],
     *  [Int.MAX_VALUE,0,3,4],
     *  [Int.MAX_VALUE,Int.MAX_VALUE,0,1],
     *  [Int.MAX_VALUE,Int.MAX_VALUE,Int.MAX_VALUE,0]]
     *
     *
     *  Note: Can help to detect negative cycle:
     *
     */
    fun floydWarshall(edges: Array<IntArray>, n: Int): Array<IntArray> {
        /*
          Intution :
            1. Create a 2d array with n*n with max value which adjacentcy matrix
            2. Initialize the diagonal with 0
            3. Update the distance array with the given edges weight
            4. Run the loop for k from 0 to n
            5. Run the loop for i from 0 to n
            6. Run the loop for j from 0 to n
            7. If the distance from i to k and k to j is less than the distance from i to j then update the distance from i to j
            8. Return the distance array

            Time Complexity = O(n^3)
         */
        val dist = Array(n) { IntArray(n) { Int.MAX_VALUE } }

        for (i in 0 until n) {
            dist[i][i] = 0
        }

        for (edge in edges) {
            val (u, v, w) = edge
            dist[u][v] = w
        }

        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    dist[i][j] = minOf(dist[i][j], dist[i][k] + dist[k][j])
                }
            }
        }

        // Negative cycle is when the distance from a node to itself is negative and decreases with each iteration
        // for negative cycle detection
        for (i in 0 until n) {
            if (dist[i][i] < 0) {
                return emptyArray()
            }
        }

        return dist
    }


    /**
     * Concept : Kosuraju
     */
    fun kosaraju(edges: Array<IntArray>, n: Int): List<List<Int>> {
        val adjList = createAdjList(edges)
        val visited = mutableSetOf<Int>()
        val stack = ArrayDeque<Int>()

        fun dfs1(node: Int) {
            visited.add(node)
            adjList[node]?.forEach {
                if (it !in visited) {
                    dfs1(it)
                }
            }
            stack.add(node)
        }

        for (i in 0 until n) {
            if (i !in visited) {
                dfs1(i)
            }
        }

        val reverseAdjList = createReverseAdjList(edges)
        visited.clear()
        val components = mutableListOf<List<Int>>()

        fun dfs2(node: Int, component: MutableList<Int>) {
            visited.add(node)
            component.add(node)
            reverseAdjList[node]?.forEach {
                if (it !in visited) {
                    dfs2(it, component)
                }
            }
        }

        while (stack.isNotEmpty()) {
            val node = stack.removeLast()
            if (node !in visited) {
                val component = mutableListOf<Int>()
                dfs2(node, component)
                components.add(component)
            }
        }

        return components
    }

    fun createAdjList(edges: Array<IntArray>): Map<Int, List<Int>> {
        val adjList = mutableMapOf<Int, MutableList<Int>>()
        for (edge in edges) {
            val (u, v) = edge

            adjList[u] = adjList.getOrDefault(u, mutableListOf()).apply {
                add(v)
            }
            adjList[v] = adjList.getOrDefault(v, mutableListOf()).apply {
                add(u)
            }
        }
        return adjList
    }

    fun createReverseAdjList(edges: Array<IntArray>): Map<Int, List<Int>> {
        val adjList = mutableMapOf<Int, MutableList<Int>>()
        for (edge in edges) {
            val (u, v) = edge

            adjList[v] = adjList.getOrDefault(v, mutableListOf()).apply {
                add(u)
            }
        }
        return adjList
    }


    /**
     * Tarjan's Algorithm
     *
     * Tarjan's Algorithm is a depth-first search (DFS)-based algorithm used to find strongly connected components (SCCs)
     * in a directed graph in linear time,
     * O(V+E), where
     * V is the number of vertices and
     * E is the number of edges.
     *
     * An SCC is a maximal subset of a directed graph where every vertex is reachable from every other vertex in the subset.
     *
     * Key Concepts:
     * DFS Tree: The algorithm uses a DFS traversal to explore the graph.
     * Low-Link Values: A low-link value of a node is the smallest node ID reachable from that node, including itself
     * and through any of its descendants in the DFS tree.
     * Stack: A stack keeps track of the nodes in the current SCC.
     * Visited Nodes: Nodes in the current SCC are marked in the stack to avoid revisiting
     *
     */
    fun tarjanAlgorithm(edges: Array<IntArray>, n: Int): List<List<Int>> {
        val adjList = createAdjList(edges)
        val stack = ArrayDeque<Int>()
        val visited = mutableSetOf<Int>()
        val onStack = mutableSetOf<Int>()
        val ids = IntArray(n) { -1 }
        val low = IntArray(n) { -1 }
        var id = 0
        val components = mutableListOf<List<Int>>()

        fun dfs(node: Int) {
            stack.add(node)
            visited.add(node)
            onStack.add(node)
            ids[node] = id
            low[node] = id
            id++

            adjList[node]?.forEach {
                if (it !in visited) {
                    dfs(it)
                }
                if (it in onStack) {
                    low[node] = minOf(low[node], low[it])
                }
            }

            if (ids[node] == low[node]) {
                val component = mutableListOf<Int>()
                while (stack.isNotEmpty()) {
                    val cur = stack.removeLast()
                    component.add(cur)
                    onStack.remove(cur)
                    low[cur] = ids[node]
                    if (cur == node) {
                        break
                    }
                }
                components.add(component)
            }
        }

        for (i in 0 until n) {
            if (i !in visited) {
                dfs(i)
            }
        }

        return components
    }


}