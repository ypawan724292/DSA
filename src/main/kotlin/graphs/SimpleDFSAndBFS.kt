package graphs

import annotations.Important
import annotations.Revise

class SimpleDFSAndBFS {
    /**
     * Create adjacency list from edges
     */
    fun createAdjList(edges: Array<IntArray>): Map<Int, List<Int>> {
        // TC : O(E)
        // SC : O(V+E) Bcuz we are storing all the edges and vertices
        val adjList = mutableMapOf<Int, MutableList<Int>>()
        for (edge in edges) {
            adjList[edge[0]] = adjList.getOrDefault(edge[0], mutableListOf()).apply {
                add(edge[1])
            }
            adjList[edge[1]] = adjList.getOrDefault(edge[1], mutableListOf()).apply {
                add(edge[0])
            }
        }
        return adjList
    }


    /**
     * Types of Topology:
     * 1. Star Topology:
     * A graph of V vertices represents a star topology if it satisfies the following three conditions:
     * One node (also called the central node) has degree V – 1.
     * All nodes except the central node have degree 1.
     * No of edges = No of Vertices – 1.
     *
     * 2. Bus Topology:
     * A graph of V vertices represents a bus topology if it satisfies the following two conditions:
     *
     * Each node except the starting and ending ones has degree 2 while the starting and ending have degree 1.
     * No of edges = No of Vertices – 1.
     *
     *3. Ring Topology: -
     *  A graph of V vertices represents a Ring topology if it satisfies the following three conditions:
     * Number of vertices >= 3.
     * All vertices should have degree 2.
     * No of edges = No of Vertices.
     *
     */


    /**
     * 1971. Find if Path Exists in Graph
     * Solved
     * Easy
     * Topics
     * Companies
     * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
     * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge
     * between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
     *
     * You want to determine if there is a valid path that exists from vertex source to vertex destination.
     *
     * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
     */
    fun findIfPathExists(edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        // TC : O(V+E)
        val adjList = mutableMapOf<Int, MutableList<Int>>()

        for (edge in edges) {
            adjList[edge[0]] = adjList.getOrDefault(edge[0], mutableListOf()).apply {
                add(edge[1])
            }

            adjList[edge[1]] = adjList.getOrDefault(edge[1], mutableListOf()).apply {
                add(edge[0])
            }
        }


        val stack = ArrayDeque<Int>()
        val visited = mutableSetOf<Int>()
        stack.add(source)
        visited.add(source)
        while (stack.isNotEmpty()) {
            val curr = stack.removeLast()
            if (curr == destination) return true
            adjList[curr]?.forEach { neighbour ->
                if (neighbour !in visited) {
                    stack.add(neighbour)
                    visited.add(neighbour)
                }
            }
        }

        return false
    }


    @Important
    fun checkCycle(edges: Array<IntArray>): Boolean {
        /*
        1. create adjacency list
        2. create visited set
        3. create parent map : it is used to keep track of parent node of child node
        4. 1->2->3->4->1

        Intuition:
        1. To detect cycle in undirected graph, we need to keep track of parent node of child node
        2. If we visit a node which is already visited and it is not parent of current node, then there is a cycle
         */
        val adjList = createAdjList(edges)

        val visited = mutableSetOf<Int>()
        val parent = mutableMapOf<Int, Int>()
        val stack = ArrayDeque<Int>()
        stack.add(0)
        visited.add(0)

        while (stack.isNotEmpty()) {
            val curr = stack.removeLast()
            adjList[curr]?.forEach { neighbour ->
                if (neighbour !in visited) {
                    stack.add(neighbour)
                    visited.add(neighbour)
                    parent[neighbour] = curr
                } else if (parent[curr] != neighbour) {
                    return true
                }
            }
        }

        return false
    }



    /**
     * 841. Keys and Rooms
     * Solved
     * Medium
     * Topics
     * Companies
     * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0.
     * Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
     *
     * When you visit a room, you may find a set of distinct keys in it. Each key has a number on it,
     * denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
     *
     * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i,
     * return true if you can visit all the rooms, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: rooms = [[1],[2],[3],[]]
     * Output: true
     * Explanation:
     * We visit room 0 and pick up key 1.
     * We then visit room 1 and pick up key 2.
     * We then visit room 2 and pick up key 3.
     * We then visit room 3.
     * Since we were able to visit every room, we return true.
     * Example 2:
     *
     * Input: rooms = [[1,3],[3,0,1],[2],[0]]
     * Output: false
     * Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
     *
     */
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {

        val visited = mutableSetOf<Int>()

        fun dfs(room: Int) {
            if (room in visited) return
            visited.add(room)
            rooms[room].forEach {
                dfs(it)
            }
        }
        dfs(0)


        /*  val queue = ArrayDeque<Int>()
          queue.add(0)
          visited.add(0)
          while (queue.isNotEmpty()) {
              val room = queue.removeFirst()
              rooms[room].forEach {
                  if (it !in visited) {
                      visited.add(it)
                      queue.add(it)
                  }
              }
          }*/

        return rooms.size == visited.size
    }


    /**
     * 133. Clone Graph
     * Solved
     * Medium
     * Topics
     * Companies
     * Given a reference of a node in a connected undirected graph.
     *
     * Return a deep copy (clone) of the graph.
     *
     * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
     *
     * class Node {
     *     public int val;
     *     public List<Node> neighbors;
     * }
     *
     *
     * Test case format:
     *
     * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
     *
     * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
     *
     * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
     *
     *
     */
    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList()
    }

    @Important
    fun cloneGraph(node: Node?): Node? {
        node ?: return null
        val map = mutableMapOf<Node, Node>()
        /* val clone = Node(node.`val`)
         map[node] = clone
         val queue = ArrayDeque<Node>()
         queue.add(node)

         while (queue.isNotEmpty()) {
             val curr = queue.removeFirst()
             curr?.neighbors?.forEach {
                 if (it != null) {
                     if (it !in map) {
                         map[it] = Node(it.`val`)
                         queue.add(it)
                     }
                     map[curr]?.neighbors?.add(map[it])
                 }
             }
         }*/

        fun dfs(node: Node?): Node? {
            if (node == null) return null
            if (node in map) return map[node]

            val copy = Node(node.`val`)
            map[node] = copy
            node.neighbors.forEach {
                copy.neighbors.add(dfs(it))
            }
            return copy
        }

        return dfs(node)

    }
}