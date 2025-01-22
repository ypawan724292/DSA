package graphs

class Bipartite {

    /**
     * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
     *
     * There are no self-edges (graph[u] does not contain u).
     * There are no parallel edges (graph[u] does not contain duplicate values).
     * If v is in graph[u], then u is in graph[v] (the graph is undirected).
     * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
     * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
     *
     * Return true if and only if it is bipartite.
     *
     * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
     * Output: false
     * Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
     *
     *
     * graph.length == n
     * 1 <= n <= 100
     * 0 <= graph[u].length < n
     * 0 <= graph[u][i] <= n - 1
     * graph[u] does not contain u.
     * All the values of graph[u] are unique.
     * If graph[u] contains v, then graph[v] contains u.
     */
    fun isBipartite(graph: Array<IntArray>): Boolean {
        val n = graph.size
        val color = IntArray(n) { -1 }

        fun bfs(node: Int): Boolean {
            val queue = ArrayDeque<Int>()
            queue.add(node)
            color[node] = 0
            while (queue.isNotEmpty()) {
                val curr = queue.removeFirst()
                graph[curr].forEach {
                    if (color[it] == -1) {
                        color[it] = 1 - color[curr]
                        queue.add(it)
                    } else if (color[it] == color[curr]) {
                        return false
                    }
                }
            }
            return true
        }

        /*
            What Happens If We Don't Check All Components?
               -If we don't check all components, we might miss a component that is not bipartite.
               This could lead to incorrect conclusions about the bipartiteness of the entire graph.
               For example, if one component is bipartite but another is not, the entire graph is not bipartite.
         */
        //why are we check for all nodes, because the graph may not be connected
        for (i in 0 until n) {
            if (color[i] == -1 && !bfs(i)) {
                return false
            }
        }
        return true
    }

}