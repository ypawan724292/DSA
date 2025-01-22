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

    /**
     * Concept : Kosuraju
     */
}