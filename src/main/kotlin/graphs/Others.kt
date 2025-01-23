package graphs

class Others {


    /**
     * Concept : BellFord
     *
     *The Bellman-Ford algorithm is used for finding the shortest paths from a single source vertex to all other vertices in a weighted graph.
     * It can handle graphs with negative weight edges, unlike Dijkstra's algorithm.
     * However, it cannot handle graphs with negative weight cycles.
     */

    fun bellmanFord(edges: Array<IntArray>, n: Int, source: Int): IntArray {
        val dist = IntArray(n) { Int.MAX_VALUE }
        dist[source] = 0

        for (i in 1 until n) {
            for (j in edges.indices) {
                val u = edges[j][0]
                val v = edges[j][1]
                val w = edges[j][2]
                if (dist[u] != Int.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w
                }
            }
        }

        for (j in edges.indices) {
            val u = edges[j][0]
            val v = edges[j][1]
            val w = edges[j][2]
            if (dist[u] != Int.MAX_VALUE && dist[u] + w < dist[v]) {
                return intArrayOf()
            }
        }

        return dist
    }

    /**
     * Concept : Floyd Warshall
     *
     */
    fun floydWarshall(edges: Array<IntArray>, n: Int): Array<IntArray> {
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
                    if (dist[i][k] != Int.MAX_VALUE && dist[k][j] != Int.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]
                    }
                }
            }
        }

        return dist}

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

}