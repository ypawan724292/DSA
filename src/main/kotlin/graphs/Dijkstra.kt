package graphs

import annotations.Important
import java.util.*

class Dijkstra {

    fun createAdjList(edges: Array<IntArray>): Map<Int, List<IntArray>> {
        val adjList = mutableMapOf<Int, MutableList<IntArray>>()
        for (edge in edges) {
            adjList[edge[0]] = adjList.getOrDefault(edge[0], mutableListOf()).apply {
                add(intArrayOf(edge[1], edge[2]))
            }
            adjList[edge[1]] = adjList.getOrDefault(edge[1], mutableListOf()).apply {
                add(intArrayOf(edge[0], edge[2]))
            }
        }
        return adjList
    }

    /**
     * The Time Complexity of this function is O(V) where V is the number of vertices
     * The Space Complexity of this function is O(V) where V is the number of vertices
     * Reason
     */
    fun dijkstra(edges: Array<IntArray>, n: Int, source: Int): IntArray {
        // TC : O(V*(E+V)) = O(V^2)
        val adjList = createAdjList(edges)
        val dist = IntArray(n) { Int.MAX_VALUE }
        val visited = BooleanArray(n)
        dist[source] = 0

        for (i in 0 until n - 1) {
            val u = minDistance(dist, visited)
            visited[u] = true
            for (v in adjList[u] ?: emptyList()) {
                val (node, weight) = v
                if (!visited[node] && dist[u] != Int.MAX_VALUE && dist[u] + weight < dist[node]) {
                    dist[node] = dist[u] + weight
                }
            }
        }

        return dist
    }

    fun minDistance(dist: IntArray, visited: BooleanArray): Int {
        var min = Int.MAX_VALUE
        var minIndex = -1
        for (v in dist.indices) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v]
                minIndex = v
            }
        }
        return minIndex
    }

    /**
     * Using Priority Queue
     * The Time Complexity of this function is O(E log V) where E is the number of edges and V is the number of vertices
     * Because we are iterating over all the edges and then over all the vertices
     */
    fun dijkstraWithPriority(edges: Array<IntArray>, n: Int, source: Int): IntArray {
        // TC : O(E log V)
        val adjList = createAdjList(edges)
        val dist = IntArray(n) { Int.MAX_VALUE }
        val pq = PriorityQueue<IntArray> { a, b -> a[1] - b[1] }
        dist[source] = 0
        pq.add(intArrayOf(source, 0))

        while (pq.isNotEmpty()) {
            val (current, currentDist) = pq.remove()
            if (currentDist > dist[current]) continue

            adjList[current]?.forEach { neighbor ->
                val (next, weight) = neighbor
                val newDist = currentDist + weight
                if (newDist < dist[next]) {
                    dist[next] = newDist
                    pq.add(intArrayOf(next, newDist))
                }
            }
        }
        return dist
    }

    fun dijktraWithTreeSet(edges: Array<IntArray>, n: Int, source: Int): IntArray {
        // TC : O(E log V)
        val adjList = createAdjList(edges)
        val dist = IntArray(n) { Int.MAX_VALUE }
        val pq = TreeSet<IntArray> { a, b -> a[1] - b[1] }
        dist[source] = 0
        pq.add(intArrayOf(source, 0))

        while (pq.isNotEmpty()) {
            val (current, currentDist) = pq.pollFirst()
            if (currentDist > dist[current]) continue

            adjList[current]?.forEach { neighbor ->
                val (next, weight) = neighbor
                val newDist = currentDist + weight
                if (newDist < dist[next]) {
                    dist[next] = newDist
                    pq.add(intArrayOf(next, newDist))
                }
            }
        }
        return dist
    }


    /**
     * You are given a network of n nodes, labeled from 1 to n.
     * You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
     * where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
     *
     * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
     * If it is impossible for all the n nodes to receive the signal, return -1.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
     * Output: 2
     * Example 2:
     *
     * Input: times = [[1,2,1]], n = 2, k = 1
     * Output: 1
     * Example 3:
     *
     * Input: times = [[1,2,1]], n = 2, k = 2
     * Output: -1
     *
     *
     * Constraints:
     *
     * 1 <= k <= n <= 100
     * 1 <= times.length <= 6000
     * times[i].length == 3
     * 1 <= ui, vi <= n
     * ui != vi
     * 0 <= wi <= 100
     * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
     */

    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        // create adjlist
        val adjList = createAdjList(times)

        //init dist and pq
        val dist = IntArray(n) { Int.MAX_VALUE }
        val pq = PriorityQueue<IntArray> { a, b -> a[1] - b[1] }

        dist[k] = 0
        pq.add(intArrayOf(k, 0))


        // while pq not empty pop and check for the neighbors
        while (pq.isNotEmpty()) {
            repeat(pq.size) {
                val (cur, curDist) = pq.remove()


                adjList[cur]?.forEach { it ->
                    val (neig, weight) = it

                    val neigDist = curDist + weight
                    if (dist[neig] > neigDist) {
                        dist[neig] = neigDist
                        pq.add(intArrayOf(neig, neigDist))
                    }

                }

            }

        }

        // check for items in distance with max value return it else if one of the node dist
        //        Infinity return -1
        var maxDist = -1
        for (idist in dist) {
            if (idist == Int.MAX_VALUE) {
                return -1
            } else {
                maxDist = maxOf(maxDist, idist)
            }
        }
        return maxDist
    }


    /**
     *
     * Topics
     * Companies
     * There are n cities connected by some number of flights.
     * You are given an array flights where flights[i] = [fromi, toi, pricei]
     * indicates that there is a flight from city fromi to city toi with cost pricei.
     *
     * You are also given three integers src, dst, and k,
     * return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
     * Output: 700
     * Explanation:
     * The graph is shown above.
     * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
     * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
     * Example 2:
     *
     *
     * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
     * Output: 200
     * Explanation:
     * The graph is shown above.
     * The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
     * Example 3:
     *
     *
     * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
     * Output: 500
     * Explanation:
     * The graph is shown above.
     * The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
     *
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 0 <= flights.length <= (n * (n - 1) / 2)
     * flights[i].length == 3
     * 0 <= fromi, toi < n
     * fromi != toi
     * 1 <= pricei <= 104
     * There will not be any multiple flights between two cities.
     * 0 <= src, dst, k < n
     * src != dst
     */
    @Important
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {

        val adjList = mutableMapOf<Int, MutableList<IntArray>>()
        for (flight in flights) {
            val (u, v, w) = flight

            adjList[u] = adjList.getOrDefault(u, mutableListOf()).apply {
                add(intArrayOf(v, w))
            }
        }

        val pq = ArrayDeque<IntArray>()
        val cost = IntArray(n) { Int.MAX_VALUE }
        cost[src] = 0
        pq.add(intArrayOf(src, 0, 0))

        var level = 0
        while (pq.isNotEmpty()) {
            val size = pq.size
            repeat(size) {
                val (cur, curPrice, curStop) = pq.removeFirst()

                if (curStop <= k) {
                    adjList[cur]?.forEach { edge ->
                        val (neig, price) = edge

                        val neigPrice = curPrice + price
                        if (cost[neig] > neigPrice) {
                            cost[neig] = neigPrice
                            pq.add(intArrayOf(neig, neigPrice, curStop + 1))
                        }
                    }
                }
            }
        }

        return if (cost[dst] == Int.MAX_VALUE) -1 else cost[dst]
    }

    /**
     * A series of highways connect n cities numbered from 0 to n - 1.
     * You are given a 2D integer array highways where highways[i] = [city1i, city2i, tolli]
     * indicates that there is a highway that connects city1i and city2i, allowing a car to go from city1i to city2i and vice versa for a cost of tolli.
     *
     * You are also given an integer discounts which represents the number of discounts you have.
     * You can use a discount to travel across the ith highway for a cost of tolli / 2 (integer division).
     * Each discount may only be used once, and you can only use at most one discount per highway.
     *
     * Return the minimum total cost to go from city 0 to city n - 1, or -1 if it is not possible to go from city 0 to city n - 1.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], discounts = 1
     * Output: 9
     * Explanation:
     * Go from 0 to 1 for a cost of 4.
     * Go from 1 to 4 and use a discount for a cost of 11 / 2 = 5.
     * The minimum cost to go from 0 to 4 is 4 + 5 = 9.
     * Example 2:
     *
     *
     * Input: n = 4, highways = [[1,3,17],[1,2,7],[3,2,5],[0,1,6],[3,0,20]], discounts = 20
     * Output: 8
     * Explanation:
     * Go from 0 to 1 and use a discount for a cost of 6 / 2 = 3.
     * Go from 1 to 2 and use a discount for a cost of 7 / 2 = 3.
     * Go from 2 to 3 and use a discount for a cost of 5 / 2 = 2.
     * The minimum cost to go from 0 to 3 is 3 + 3 + 2 = 8.
     * Example 3:
     *
     *
     * Input: n = 4, highways = [[0,1,3],[2,3,2]], discounts = 0
     * Output: -1
     * Explanation:
     * It is impossible to go from 0 to 3 so return -1.
     *
     *
     * Constraints:
     *
     * 2 <= n <= 1000
     * 1 <= highways.length <= 1000
     * highways[i].length == 3
     * 0 <= city1i, city2i <= n - 1
     * city1i != city2i
     * 0 <= tolli <= 105
     * 0 <= discounts <= 500
     * There are no duplicate highways.
     */
    fun minCost(edges: Array<IntArray>, n: Int, discount: Int): Int {
        val adjList = createAdjList(edges)


        val pq = PriorityQueue<IntArray> { a, b -> a[1] - b[1] }
        val dist = Array(n) { IntArray(discount + 1) { Int.MAX_VALUE } }

        dist[0][0] = 0
        pq.add(intArrayOf(0, 0, 0))


        while (pq.isNotEmpty()) {
            repeat(pq.size) {
                val (cur, curCost, curDiscount) = pq.remove()

                if (cur == n - 1) return curCost

                adjList[cur]?.forEach {
                    val (neig, price) = it

                    val neigCost = curCost + price
                    if (dist[neig][curDiscount] > neigCost) {
                        dist[neig][curDiscount] = neigCost
                        pq.add(intArrayOf(neig, neigCost, curDiscount))
                    }

                    val neigCost2 = curCost + price / 2
                    if (curDiscount < discount && dist[neig][curDiscount + 1] > neigCost2) {
                        dist[neig][curDiscount + 1] = neigCost2
                        pq.add(intArrayOf(neig, neigCost2, curDiscount + 1))
                    }
                }
            }
        }

        return -1
    }

    /**
     * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
     * where heights[row][col] represents the height of cell (row, col).
     * You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
     * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
     *
     * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
     *
     * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
     * Output: 2
     * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
     * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
     * Example 2:
     *
     *
     *
     * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
     * Output: 1
     * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
     * Example 3:
     *
     *
     * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
     * Output: 0
     * Explanation: This route does not require any effort.
     */
    fun minimumEffortPath(heights: Array<IntArray>): Int {
        val m = heights.size
        val n = heights[0].size
        val pq = PriorityQueue<IntArray> { a, b -> a[2] - b[2] }
        val dist = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        pq.add(intArrayOf(0, 0, 0))
        dist[0][0] = 0

        val dir = listOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))

        while (pq.isNotEmpty()) {

            repeat(pq.size) {
                val (row, col, effort) = pq.remove()
                if (row == m - 1 && col == n - 1) return effort

                dir.forEach {
                    val newRow = row + it[0]
                    val newCol = col + it[1]

                    if (newRow in 0..m - 1 && newCol in 0..n - 1) {

                        val newEffort = maxOf(effort, Math.abs(heights[row][col] - heights[newRow][newCol]))

                        if (dist[newRow][newCol] > newEffort) {
                            dist[newRow][newCol] = newEffort
                            pq.add(intArrayOf(newRow, newCol, newEffort))
                        }
                    }
                }
            }
        }
        return 0
    }


    /**
     * You are given an undirected weighted graph of n nodes (0-indexed),
     * represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a
     * probability of success of traversing that edge succProb[i].
     *
     * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
     *
     * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
     * Output: 0.25000
     * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
     * Example 2:
     *
     *
     *
     * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
     * Output: 0.30000
     * Example 3:
     *
     *
     *
     * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
     * Output: 0.00000
     * Explanation: There is no path between 0 and 2.
     *
     */
    fun maxProbability(n: Int, edges: Array<IntArray>, succProb: DoubleArray, start: Int, end: Int): Double {
        val dist = Array(n) { 0.0 }
        val adj = mutableMapOf<Int, MutableList<Pair<Int, Double>>>()
        edges.forEachIndexed { i, (from, to) ->
            adj[from] = adj.getOrDefault(from, mutableListOf()).apply { add(to to succProb[i]) }
            adj[to] = adj.getOrDefault(to, mutableListOf()).apply { add(from to succProb[i]) }
        }
        val pq = PriorityQueue<Pair<Int, Double>> { a, b -> b.second.compareTo(a.second) }

        pq.add(start to 1.0)
        dist[start] = 1.0

        while (pq.isNotEmpty()) {
            val (curr, p) = pq.remove()
            adj[curr]?.forEach { (next, pnext) ->
                val np = p * pnext
                if (dist[next] < np) {
                    dist[next] = np
                    pq.add(next to np)
                }
            }
        }

        return dist[end]
    }

}