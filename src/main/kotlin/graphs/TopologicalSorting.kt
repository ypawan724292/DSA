package graphs

import annotations.Important

class TopologicalSorting {


    //Finding out whether there is a cycle in a directed graph , (Both BFS and DFS Approach)
    fun isCycle(n: Int, edges: Array<IntArray>): Boolean {
        val adjList = mutableMapOf<Int, MutableList<Int>>()
        val inDegree = IntArray(n)
        for (edge in edges) {
            val (u, v) = edge
            adjList[u] = adjList.getOrDefault(u, mutableListOf()).apply {
                add(v)
            }
            inDegree[v]++
        }

        val q = ArrayDeque<Int>()
        for (i in 0 until n) {
            if (inDegree[i] == 0) {
                q.add(i)
            }
        }

        var count = 0
        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            count++
            adjList[cur]?.forEach {
                inDegree[it]--
                if (inDegree[it] == 0) {
                    q.add(it)
                }
            }
        }

        return count != n
    }

    @Important
    fun isCycleDFS(n: Int, edges: Array<IntArray>): Boolean {
        val adjList = mutableMapOf<Int, MutableList<Int>>()
        for (edge in edges) {
            val (u, v) = edge
            adjList[u] = adjList.getOrDefault(u, mutableListOf()).apply {
                add(v)
            }
        }

        val visited = BooleanArray(n)
        // recStack is used to keep track of the nodes that are part of the current recursion stack
        val recStack = BooleanArray(n)

        fun hasCycle(i: Int): Boolean {
            if (!visited[i]) {
                visited[i] = true
                recStack[i] = true
                adjList[i]?.forEach {
                    if (!visited[it] && hasCycle(it)) {
                        return true
                    } else if (recStack[it]) {
                        return true
                    }
                }
            }
            recStack[i] = false
            return false
        }

        for (i in 0 until n) {
            if (hasCycle(i)) {
                return true
            }
        }
        return false
    }


    /**
     * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices
     * such that for every directed edge u v, vertex u comes before v in the ordering.
     * Topol
     *
     * Not possible if graph has cycle.
     * Not possible if graph is not directed.
     * At least one edge should have in-degree 0
     * At least one edge should have out-degree 0
     *
     *Input: V=6 , E = {{2,3}, {3,1}, {4,0}, {4,1}, {5,0}, {5,2}}
     *
     * Output: 4 5 2 0 3 1
     *
     */
    fun topologicalSortUsingDFSAndStack(n: Int, egdes: Array<IntArray>): List<Int> {
        val visited = BooleanArray(n)
        val stack = mutableListOf<Int>()

        val adjList = mutableMapOf<Int, MutableList<Int>>()
        for (edge in egdes) {
            val (u, v) = edge
            adjList[u] = adjList.getOrDefault(u, mutableListOf()).apply {
                add(v)
            }
        }

        fun dfs(i: Int) {
            visited[i] = true
            adjList[i]?.forEach {
                if (!visited[it]) {
                    dfs(it)
                }
            }
            stack.add(i)
        }

        for (i in 0 until n) {
            if (!visited[i]) {
                dfs(i)
            }
        }

        return if (stack.size == n) stack.reversed() else emptyList()
    }

    /**
     * Kahn’s Algorithm for Topological Sorting is a method used to order the vertices of a directed graph in a linear order
     * such that for every directed edge from vertex A to vertex B, A comes before B in the order.
     * The algorithm works by repeatedly finding vertices with no incoming edges,
     * removing them from the graph, and updating the incoming edges of the remaining vertices.
     * This process continues until all vertices have been ordered.
     *
     * It does not use the visited array,
     * instead it uses in-degree array to keep track of incoming edges.
     */
    fun topologicalSortUsingKahnsAlgorithm(n: Int, edges: Array<IntArray>): List<Int> {
        val adjList = mutableMapOf<Int, MutableList<Int>>()

        val inDegree = IntArray(n)
        for (edge in edges) {
            val (u, v) = edge
            adjList[u] = adjList.getOrDefault(u, mutableListOf()).apply {
                add(v)
            }
            inDegree[v]++
        }

        val queue = ArrayDeque<Int>()
        // A node with an in-degree of zero has no incoming edges,
        // meaning there are no dependencies that need to be processed before this node.
        for (i in 0 until n) {
            if (inDegree[i] == 0) {
                queue.add(i)
            }
        }

        val result = mutableListOf<Int>()
        while (queue.isNotEmpty()) {
            val u = queue.removeFirst()
            result.add(u)
            adjList[u]?.forEach {
                inDegree[it]--
                if (inDegree[it] == 0) {
                    queue.add(it)
                }
            }
        }

        return if (result.size == n) result else emptyList()
    }

    /**
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
     * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
     *
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return true if you can finish all courses. Otherwise, return false.
     *
     *
     *
     * Example 1:
     *
     * Input: numCourses = 2, prerequisites = [[1,0]]
     * Output: true
     * Explanation: There are a total of 2 courses to take.
     * To take course 1 you should have finished course 0. So it is possible.
     * Example 2:
     *
     * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
     * Output: false
     * Explanation: There are a total of 2 courses to take.
     * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
     */

    fun canFinish(prerequistes: Array<IntArray>, n: Int): Boolean {
        val preqMap = mutableMapOf<Int, MutableList<Int>>()
        val inDegree = IntArray(n)
        for ((course, req) in prerequistes) {
            preqMap[req] = preqMap.getOrDefault(req, mutableListOf()).apply {
                add(course)
            }

            inDegree[course]++
        }

        val q = ArrayDeque<Int>()

        for (i in 0..n - 1) {
            if (inDegree[i] == 0) {
                q.add(i)
            }
        }
        var count = 0
        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            count++
            preqMap[cur]?.forEach { nei ->
                inDegree[nei]--
                if (inDegree[nei] == 0) {
                    q.add(nei)
                }
            }
        }

        return count == n

    }

    /** Medium
    TopicsCompanies
    Hint
    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
    You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them.
    If it is impossible to finish all courses, return an empty array.

    Example 1:
    Input: numCourses = 2, prerequisites = [[1,0]]
    Output: [0,1]
    Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].


    Example 2:
    Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
    Output: [0,2,1,3]
    Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
    So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

    Example 3:
    Input: numCourses = 1, prerequisites = []
    Output: [0]
     **/

    fun orderOfCource(preq: Array<IntArray>, n: Int): IntArray {
        val preqMap = mutableMapOf<Int, MutableList<Int>>()
        val inDegree = IntArray(n)
        val res = mutableListOf<Int>()


        for ((course, preq) in preq) {
            preqMap[preq] = preqMap.getOrDefault(preq, mutableListOf()).apply {
                add(course)
            }
            inDegree[course]++
        }


        val q = ArrayDeque<Int>()
        for (i in 0..n - 1) {
            if (inDegree[i] == 0) q.add(i)
        }

        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            res.add(cur)
            preqMap[cur]?.forEach {
                inDegree[it]--
                if (inDegree[it] == 0) {
                    q.add(it)
                }
            }
        }

        return (if (res.size == n) res else emptyList()).toIntArray()
    }

    /**
     * There are N courses, labelled from 1 to N.
     *
     * We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.
     *
     * In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
     *
     * Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: N = 3, relations = [[1,3],[2,3]]
     * Output: 2
     * Explanation:
     * In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
     * Example 2:
     *
     *
     *
     * Input: N = 3, relations = [[1,2],[2,3],[3,1]]
     * Output: -1
     * Explanation:
     * No course can be studied because they depend on each other.
     *
     *
     * Note:
     *
     * 1 <= N <= 5000
     * 1 <= relations.length <= 5000
     * relations[i][0] != relations[i][1]
     * There are no repeated relations in the input.
     */
    fun minimumSemesters(N: Int, relations: Array<IntArray>): Int {
        val preqMap = mutableMapOf<Int, MutableList<Int>>()
        val inDegree = IntArray(N)
        for ((course, preq) in relations) {
            preqMap[preq] = preqMap.getOrDefault(preq, mutableListOf()).apply {
                add(course)
            }
            inDegree[course]++
        }

        val q = ArrayDeque<Int>()
        for (i in 0..N - 1) {
            if (inDegree[i] == 0) q.add(i)
        }

        var sem = 0
        var count = 0
        while (q.isNotEmpty()) {
            ++sem
            repeat(q.size) {
                val cur = q.removeFirst()
                count++
                preqMap[cur]?.forEach {
                    inDegree[it]--
                    if (inDegree[it] == 0) {
                        q.add(it)
                    }
                }
            }
        }
        return if (count == N) sem else -1
    }

    /**
     *
     * Problem Statement: Given a sorted dictionary of an alien language having N words and k starting alphabets of a standard dictionary.
     * Find the order of characters in the alien language.
     *
     * Note: Many orders may be possible for a particular test case, thus you may return any valid order.
     *
     * Examples:
     *
     * Example 1:
     * Input: N = 5, K = 4
     * dict = {"baa","abcd","abca","cab","cad"}
     * Output: b d a c
     * Explanation:
     * We will analyze every consecutive pair to find out the order of the characters.
     * The pair “baa” and “abcd” suggests ‘b’ appears before ‘a’ in the alien dictionary.
     * The pair “abcd” and “abca” suggests ‘d’ appears before ‘a’ in the alien dictionary.
     * The pair “abca” and “cab” suggests ‘a’ appears before ‘c’ in the alien dictionary.
     * The pair “cab” and “cad” suggests ‘b’ appears before ‘d’ in the alien dictionary.
     * So, [‘b’, ‘d’, ‘a’, ‘c’] is a valid ordering.
     *
     * Example 2:
     * Input: N = 3, K = 3
     * dict = {"caa","aaa","aab"}
     * Output: c a b
     * Explanation: Similarly, if we analyze the consecutive pair
     * for this example, we will figure out [‘c’, ‘a’, ‘b’] is
     * a valid ordering.
     * Disclaimer: Don't jump directly to the solution, try it out yourself first.
     */
    fun findAlienOrder(words: List<String>, v: Int): String {
        val m = words.size

        // if u can create new mothods for understanding

        //creating graph and populating it char relations
        val map = mutableMapOf<Char, MutableList<Char>>()
        val inDegree = mutableMapOf<Char, Int>()

        for (i in 0..m - 2) {
            val word1 = words[i]
            val word2 = words[i + 1]

            val size = minOf(word1.length, word2.length)
            for (j in 0..size - 1) {
                val u = word1[j]
                val v = word2[j]

                if (u != v) {
                    map[v] = map.getOrDefault(v, mutableListOf()).apply {
                        add(u)
                    }
                    inDegree[u] = inDegree.getOrDefault(u, 0) + 1
                    break
                }
            }
        }

        val q = ArrayDeque<Char>()
        for (entry in inDegree) {
            if (entry.value == 0) {
                q.add(entry.key)
            }
        }

        var res = ""
        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            res += cur
            map[cur]?.forEach {
                inDegree[it] = inDegree.getOrDefault(it, 0) - 1
                if (inDegree[it] == 0) {
                    q.add(it)
                }
            }
        }

        return if (res.length == v) res else ""

    }

    /**
     * A tree is an undirected graph in which any two vertices are connected by exactly one path.
     * In other words, any connected graph without simple cycles is a tree.
     *
     * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi]
     * indicates that there is an undirected edge between the two nodes ai and bi in the tree,
     * you can choose any node of the tree as the root.
     * When you select a node x as the root, the result tree has height h.
     * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
     *
     * Return a list of all MHTs' root labels. You can return the answer in any order.
     *
     * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
     * Output: [1]
     * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
     * Example 2:
     *
     *
     * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
     * Output: [3,4]
     *
     *
     * Constraints:
     *
     * 1 <= n <= 2 * 104
     * edges.length == n - 1
     * 0 <= ai, bi < n
     * ai != bi
     * All the pairs (ai, bi) are distinct.
     * The given input is guaranteed to be a tree and there will be no repeated edges.
     */
    @Important
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        /*
            1.to find the minHeightTree, we need to find the center of the tree or graph
            2. minHeightTree will have either 1 center for odd nodes or 2 centers for even nodes
            3. here we use modified Kahns algorithm to find the center of the tree
            3. Psuedo code:
                1. create adjList and inDegree array
                2. add all the nodes with inDegree 1 add to the queue
                3. while queue is not empty
                    1. get the size of the queue
                    2. clear the res list
                    3. repeat the size of the queue
                        1. remove the first element from the queue
                        2. add it to the res list
                        3. reduce the inDegree of the neighbors
                        4. if the inDegree of the neighbor is 1, add it to the queue
                4. return the res list

         */
        if (n == 1) return listOf(0)
        val adjList = mutableMapOf<Int, MutableList<Int>>()
        val inDegree = IntArray(n)
        for (edge in edges) {
            val (u, v) = edge
            adjList[u] = adjList.getOrDefault(u, mutableListOf()).apply {
                add(v)
            }
            adjList[v] = adjList.getOrDefault(v, mutableListOf()).apply {
                add(u)
            }
            inDegree[u]++
            inDegree[v]++
        }

        val q = ArrayDeque<Int>()
        for (i in 0 until n) {
            if (inDegree[i] == 1) {
                q.add(i)
            }
        }

        val res = mutableListOf<Int>()
        while (q.isNotEmpty()) {
            res.clear()
            repeat(q.size) {
                val cur = q.removeFirst()
                res.add(cur)
                adjList[cur]?.forEach {
                    inDegree[it]--
                    if (inDegree[it] == 1) {
                        q.add(it)
                    }
                }
            }
        }

        return res
    }

    /**
     * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients.
     * The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].
     * Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
     *
     * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
     *
     * Return a list of all the recipes that you can create. You may return the answer in any order.
     *
     * Note that two recipes may contain each other in their ingredients.
     *
     *
     *
     * Example 1:
     *
     * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
     * Output: ["bread"]
     * Explanation:
     * We can create "bread" since we have the ingredients "yeast" and "flour".
     * Example 2:
     *
     * Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
     * Output: ["bread","sandwich"]
     * Explanation:
     * We can create "bread" since we have the ingredients "yeast" and "flour".
     * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
     * Example 3:
     *
     * Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
     * Output: ["bread","sandwich","burger"]
     * Explanation:
     * We can create "bread" since we have the ingredients "yeast" and "flour".
     * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
     * We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
     */
    @Important
    fun findAvailableRecipes(recipes: List<String>, ingredients: List<List<String>>, supplies: List<String>): List<String> {
        val adjList = mutableMapOf<String, MutableList<String>>()
        val inDegree = mutableMapOf<String, Int>()
        for (i in 0 until recipes.size) {
            val recipe = recipes[i]
            val ingredient = ingredients[i]
            adjList[recipe] = adjList.getOrDefault(recipe, mutableListOf()).apply {
                addAll(ingredient)
            }
            inDegree[recipe] = inDegree.getOrDefault(recipe, 0) + ingredient.size
            for (ing in ingredient) {
                inDegree[ing] = inDegree.getOrDefault(ing, 0)
            }
        }

        val q = ArrayDeque<String>()
        for (entry in inDegree) {
            if (entry.value == 0) {
                q.add(entry.key)
            }
        }

        val res = mutableListOf<String>()
        while (q.isNotEmpty()) {
            repeat(q.size) {
                val cur = q.removeFirst()
                res.add(cur)
                adjList[cur]?.forEach {
                    inDegree[it] = inDegree.getOrDefault(it, 0) - 1
                    if (inDegree[it] == 0) {
                        q.add(it)
                    }
                }
            }
        }

        return res
    }

}