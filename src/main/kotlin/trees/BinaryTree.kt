package trees

import annotations.Important

class BinaryTree {
    /**
    Tree Terms:
     *         Root Node: The topmost node.
     *         Parent Node: A node with children.
     *         Child Node: A node with a parent.
     *         Sibling Node: Nodes with the same parent.
     *         Leaf Node: A node with no children.
     *         Internal Node: A node with children.
     *         Edge: Connection between two nodes.
     *         Path: Sequence of nodes connected by edges.
     *         Ancestor Node: A node which is on the path between the root node and the node.
     *         Descendant Node: A node which is on the path between the node and the leaf node.
     *         Level: Number of edges between the root node and the node.
     *         Height: Number of edges between the node and the deepest leaf node.
     *         Depth: Number of edges between the node and the root node.
     *         Degree: Number of children of a node.
     *
     *
     *
     * Types of Trees:
     *  1. Binary Tree: A tree in which each node has at most two children.
     *  2. Binary Search Tree: A binary tree in which the left child is less than the parent and the right child is greater than the parent.
     *  3. AVL Tree: A self-balancing binary search tree.
     *  4. Red-Black Tree: A self-balancing binary search tree.
     *  5. B-Tree: A self-balancing tree that can have more than two children.
     */
    data class TreeNode(
        var value: Int, var left: TreeNode? = null, var right: TreeNode? = null
    )

    //<-----------------------------------------------------------------------MEDIUM--------------------------------------------------------------------------------------------------->


    /**
     * Given the root of a binary tree, invert the tree, and return its root.
     *
     * Example 1:
     *
     *
     * Input: root = [4,2,7,1,3,6,9]
     * Output: [4,7,2,9,6,3,1]
     * Example 2:
     *
     *
     * Input: root = [2,1,3]
     * Output: [2,3,1]
     * Example 3:
     *
     * Input: root = []
     * Output: []
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 100].
     * -100 <= Node.val <= 100
     */
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null

        //dfs
//        val left = invertTree(root.left)
//        val right = invertTree(root.right)
//        root.left = right
//        root.right = left
//        return root


        //bfs
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val temp = current.left
            current.left = current.right
            current.right = temp
            current.left?.let { queue.add(it) }
            current.right?.let { queue.add(it) }
        }

        return root
    }

    /**
     * Given the root of a binary tree, return its maximum depth.
     *
     * A binary tree's maximum depth is the number of nodes along the longest path
     * from the root node down to the farthest leaf node.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [3,9,20,null,null,15,7]
     * Output: 3
     * Example 2:
     *
     * Input: root = [1,null,2]
     * Output: 2
     *
     */
    fun maxDepth(root: TreeNode?): Int {/*
        the maximum depth of a tree is equal to its height.
         Both terms refer to the longest path from the root node down to the farthest leaf node.
         */
//        if (root == null) return 0
//        val queue = ArrayDeque<TreeNode>()
//        var depth = 0
//        queue.add(root)
//        while (queue.isNotEmpty()) {
//            depth++
//            repeat(queue.size) {
//                val current = queue.removeFirst()
//                listOf(current.left, current.right).forEach {
//                    it?.let { queue.add(it) }
//                }
//            }
//
//        }
//        return depth

        // using dfs
        if (root == null) return 0
        return (1 + maxOf(maxDepth(root.left), maxDepth(root.right)))
    }


    /**
     * Balanced binary tree is a binary tree in which the depth/height of the two subtrees
     * of every node never differs by more than one.
     *
     * Example :
     * Input: root = [3,9,20,null,null,15,7]
     *  Tree Diagram:
     *          3  delta (lh(2)- rh(1)) = 1
     *         / \
     *       9    20 delta (1-1) = 0
     *           /  \
     *          15    7
     *
     *   At all nodes delta is not greater than 1, so the tree is balanced.
     */

    fun isBalanced(root: TreeNode?): Boolean {
        fun dfs(node: TreeNode?): Int {
            if (node == null) return 0
            val left = dfs(node.left)
            val right = dfs(node.right)
            if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1
            return 1 + maxOf(left, right)
        }
        return dfs(root) != -1
    }

    /**
     * Diameter of a binary tree is the number of nodes on the longest path between two nodes
     * in the tree.
     */
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        var diameter = 0
        fun dfs(node: TreeNode?): Int {
            if (node == null) return 0
            val left = dfs(node.left)
            val right = dfs(node.right)
            diameter = maxOf(diameter, left + right - 1)
            return 1 + maxOf(left, right)
        }
        dfs(root)
        return diameter
    }


    /**
     *A path in a binary tree is a sequence of nodes where each pair of
     * adjacent nodes in the sequence has an edge connecting them.
     * A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
     *
     * The path sum of a path is the sum of the node's values in the path.
     *
     * Given the root of a binary tree, return the maximum path sum of any
     * non-empty path.
     *
     * Node values can be negative as well.
     */
    fun maxPathSum(root: TreeNode?): Int {
        var maxSum = Int.MIN_VALUE
        fun dfs(node: TreeNode?): Int {
            if (node == null) return 0
            val left = maxOf(0, dfs(node.left)) // if the left is negative, we don't need it
            val right = maxOf(0, dfs(node.right)) // if the right is negative, we don't need it
            maxSum = maxOf(maxSum, left + right + node.value)
            return maxOf(left, right) + node.value
        }
        dfs(root)
        return maxSum
    }


    /**
     * Same tree is a tree in which both the structure and the nodes are same.
     */
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false
        if (p.value != q.value) return false
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }


    /**
     * Sub tree is a tree which is a part of another tree.
     */
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        fun dfs(node: TreeNode?): Boolean {
            if (node == null) return false
            if (isSameTree(node, subRoot)) return true
            return dfs(node.left) || dfs(node.right)
        }
        return dfs(root)
    }


    /**
     * Boundary Traversal of a Binary Tree
     *
     * Problem Statement:
     * Given a Binary Tree, perform the boundary traversal of the tree.
     * The boundary traversal is the process of visiting the boundary nodes of the binary tree
     * in the anticlockwise direction, starting from the root.
     * Example:
     *                  9
     *                 / \
     *                5   6
     *              / \  / \
     *             7  8 1  2
     *            / \
     *           3  4
     * res - {left boundary} + {leaf nodes} + {right boundary}
     *
     * Input : root = [9,5,6,7,8,1,2,3,4]
     *  Output : [9,5,7,3,4,8,1,2,6]
     */
    fun boundaryTraversal(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        val result = mutableListOf<Int>()

        fun isLeaf(node: TreeNode?): Boolean {
            return node?.left == null && node?.right == null
        }

        fun addLeftBoundary(root: TreeNode?) {
            if (root == null) return
            if (!isLeaf(root)) result.add(root.value)
            addLeftBoundary(root.left)
        }

        fun addRightBoundary(root: TreeNode?) {
            if (root == null) return
            if (!isLeaf(root)) result.add(root.value)
            addRightBoundary(root.right)
        }

        fun addLeafs(root: TreeNode?) {
            if (root == null) return
            if (isLeaf(root)) {
                result.add(root.value)
                return
            }
            addLeafs(root.left)
            addLeafs(root.right)
        }

        result.add(root.value)
        addLeftBoundary(root.left)
        addLeafs(root)
        addRightBoundary(root.right)
        return result
    }

    /**
     * Zigzag traversal of a binary tree
     *
     * Example:
     *                             ->   6
     *      *      *                   / \
     *      *      *                  4   8   <-----
     *      *      *                 / \  / \
     *      *      * -->           2   5 7  9
     *      *      *               / \
     *      *      *             1    3    <------
     *
     *   Input: root = [6,4,8,2,5,7,9,1,3]
     *
     *   Output: [[6],[8,4],[2,5,7,9],[3,1]]
     *
     */
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        val result = mutableListOf<List<Int>>()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        var isLeftToRight = true
        while (queue.isNotEmpty()) {
            val level = mutableListOf<Int>()
            repeat(queue.size) {
                val current = queue.removeFirst()
                if (isLeftToRight) level.add(current.value)
                else level.add(0, current.value)
                current.left?.let { queue.add(it) }
                current.right?.let { queue.add(it) }
            }
            result.add(level)
            isLeftToRight = !isLeftToRight
        }
        return result
    }


    /**
     * Vertical Order Traversal of a Binary Tree
     *
     * Given the root of a binary tree, return the vertical order traversal of its nodes' values.
     *
     * Vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index
     * starting from the leftmost column and ending on the rightmost column.
     *
     * Example 1:
     * Input:Binary Tree: 1 2 3 4 10 9 11 -1 5 -1 -1 -1 -1 -1 -1 -1 6
     *
     *
     * Output: [[4], [2], [1, 5, 6], [3], [10], [9], [11]]
     *
     * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
     *
     * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
     *
     * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
     *
     * Return the vertical order traversal of the binary tree.
     *
     */
    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        val result = mutableListOf<List<Int>>()
        val map = mutableMapOf<Int, MutableList<Int>>()
        val queue = ArrayDeque<Pair<TreeNode, Int>>()
        queue.add(root to 0)
        var min = 0
        var max = 0
        while (queue.isNotEmpty()) {
            val (current, vertical) = queue.removeFirst()
            map[vertical] = map.getOrDefault(vertical, mutableListOf()).apply { add(current.value) }
            min = minOf(min, vertical)
            max = maxOf(max, vertical)
            current.left?.let { queue.add(it to vertical - 1) }
            current.right?.let { queue.add(it to vertical + 1) }
        }
        for (i in min..max) {
            result.add(map[i]!!)
            // if asked to sort the values in each column
            // result.add(map[i]!!.sorted())
        }
//        return result

        val columnMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        val queue2 = ArrayDeque<Triple<TreeNode, Int, Int>>()
        queue2.add(Triple(root, 0, 0))  // root, column, row

        var minColumn = 0
        var maxColumn = 0

        // Perform BFS
        while (queue.isNotEmpty()) {
            val (node, row, col) = queue2.removeFirst()

            // Store node with row index and value at the corresponding column index
            columnMap.computeIfAbsent(col) { mutableListOf() }.add(Pair(row, node.value))

            // Update min and max column indices
            minColumn = minOf(minColumn, col)
            maxColumn = maxOf(maxColumn, col)

            // Add children with their respective positions
            node.left?.let { queue2.add(Triple(it, row + 1, col - 1)) }
            node.right?.let { queue2.add(Triple(it, row + 1, col + 1)) }
        }


        // Process each column from minColumn to maxColumn
        for (col in minColumn..maxColumn) {
            val columnNodes = columnMap[col]!!.sortedWith(compareBy({ it.first }, { it.second }))
            result.add(columnNodes.map { it.second })  // Extract only node values
        }

        return result
    }

    /**
     * Problem Statement:
     * Given a Binary Tree, return its Top View. The Top View of a Binary Tree is the set of nodes visible when we see the tree from the top.
     *
     * Example:
     *                  9
     *                 / \
     *                5   6
     *              / \  / \
     *             7  8 1  2
     *            / \
     *           3  4
     *
     * Input    : root = [9,5,6,7,8,1,2,3,4]
     * Output   : [3,7,5,9,6,2]
     */
    fun topView(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        val result = mutableListOf<Int>()
        val map = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Pair<TreeNode, Int>>()
        queue.add(root to 0)
        var min = 0
        var max = 0
        while (queue.isNotEmpty()) {
            val (current, vertical) = queue.removeFirst()
            if (!map.containsKey(vertical)) {
                map[vertical] = current.value
            }
            min = minOf(min, vertical)
            max = maxOf(max, vertical)
            current.left?.let { queue.add(it to vertical - 1) }
            current.right?.let { queue.add(it to vertical + 1) }
        }
        for (i in min..max) {
            result.add(map[i]!!)
        }
        return result
    }

    /**
     *  Problem Statement:
     *
     *  Given a Binary Tree, return its Bottom View.
     *  The Bottom View of a Binary Tree is the set of nodes visible when we see the tree from the bottom.
     * Example:
     *                  9
     *                 / \
     *                5   6
     *              / \  / \
     *             7  8 1  2
     *            / \
     *           3  4
     *
     * Input    : root = [9,5,6,7,8,1,2,3,4]
     * Output   : [3,7,5,9,6,2]
     */
    fun bottomView(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        val result = mutableListOf<Int>()
        val map = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Pair<TreeNode, Int>>()
        queue.add(root to 0)
        var min = 0
        var max = 0
        while (queue.isNotEmpty()) {
            val (current, vertical) = queue.removeFirst()
            map[vertical] = current.value
            min = minOf(min, vertical)
            max = maxOf(max, vertical)
            current.left?.let { queue.add(it to vertical - 1) }
            current.right?.let { queue.add(it to vertical + 1) }
        }
        for (i in min..max) {
            result.add(map[i]!!)
        }
        return result
    }

    /**
     * Given the root of a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
     */
    fun rightSideView(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        val result = mutableListOf<Int>()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) { it ->
                val current = queue.removeFirst()
                if (it == size - 1) result.add(current.value)
                current.left?.let { queue.add(it) }
                current.right?.let { queue.add(it) }
            }
        }
        return result
    }

    /**
     *Given the root of a binary tree, imagine yourself standing on the left side of it,
     *return the values of the nodes you can see ordered from top to bottom.
     */
    fun leftSideView(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        val result = mutableListOf<Int>()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) { it ->
                val current = queue.removeFirst()
                if (it == 0) result.add(current.value)
                current.left?.let { queue.add(it) }
                current.right?.let { queue.add(it) }
            }
        }
        return result
    }

    /**
     * Problem Statement:
     * Given a Binary Tree, determine whether the given tree is symmetric or not.
     *
     *  A Binary Tree would be Symmetric, when its mirror image is exactly the same as the original tree.
     * If we were to draw a vertical line through the centre of the tree, the nodes on the left and right side would be mirror images of each other.
     *
     */
    fun isSymmetric(root: TreeNode?): Boolean {
        fun isMirror(left: TreeNode?, right: TreeNode?): Boolean {
            if (left == null && right == null) return true
            if (left == null || right == null) return false
            return left.value == right.value && isMirror(left.left, right.right) && isMirror(left.right, right.left)
        }
        return isMirror(root, root)
    }

    /**
     * Perfect Binary Tree is a binary tree in which all the internal nodes have two children
     */
    fun isPerfectBinaryTree(root: TreeNode?): Boolean {
        if (root == null) return true
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        var isLeaf = false
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current.left == null && current.right == null) {
                isLeaf = true
            }
            if (isLeaf && (current.left != null || current.right != null)) {
                return false
            }
            if (current.left != null && current.right != null) {
                queue.add(current.left!!)
                queue.add(current.right!!)
            } else if (current.left == null && current.right != null) {
                return false
            } else if (current.left != null && current.right == null) {
                isLeaf = true
                queue.add(current.left!!)
            }
        }

        return true

    }


    //<-----------------------------------------------------------------------HARD--------------------------------------------------------------------------------------------------->

    /**
     * Given a binary tree root, a node X in the tree is named good
     * if in the path from root to X there are no nodes with a value greater than X.
     * Return the number of good nodes in the binary tree.
     */
    fun goodNodes(root: TreeNode?): Int {
        var count = 0
        fun dfs(node: TreeNode?, max: Int) {
            if (node == null) return
            if (node.value >= max) count++
            dfs(node.left, maxOf(max, node.value))
            dfs(node.right, maxOf(max, node.value))
        }
        dfs(root, Int.MIN_VALUE)
        return count
    }


    /**
     * Problem Statement: Given a Binary Tree and a reference to a root belonging to it.
     * Return the path from the root node to the given leaf node.
     *
     * No two nodes in the tree have the same data value.
     * It is assured that the given node is present and a path always exists.
     *
     * Example 1:
     * Input:Binary Tree: 1 2 3 4 5 -1 -1 -1 -1, Node: 7
     *
     * Output: [1, 2, 5, 7]
     */
    fun pathToNode(root: TreeNode?, node: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        fun dfs(temp: TreeNode?): Boolean {
            if (temp == null) return false
            result.add(temp.value)
            if (temp == node) return true
            if (dfs(temp.left)) return true
            if (dfs(temp.right)) return true
            result.removeAt(result.size - 1)
            return false
        }
        dfs(root)
//        return result

        if (root == null) return emptyList()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            result.add(current.value)
            if (current == node) break
            current.left?.let { queue.add(it) }
            current.right?.let { queue.add(it) }
        }
        return result
    }

    /**
     * Lowest common ancestor in a Binary Tree of two nodes
     * Example:
     *                  9
     *                 / \
     *                5   6
     *              / \  / \
     *             7  8 1  2
     *            / \
     *           3  4
     *
     *  LCA(1,5) = 9
     *  LCA(1,2) = 6
     */
    @Important
    fun findLowestCommonAncestor(node: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        /*
        Intuition:
        // base case if node is null or node is equal to p or q, return node

        The LCA of p and q in a binary tree is the lowest node that has both p and q as descendants
         1. we will find the LCA of p and q in the left subtree
         2. we will find the LCA of p and q in the right subtree
            3. if left is null, return right
            4. if right is null, return left
            5. if left and right are not null, return root
            TC: O(n) where n is no of nodes in the tree
         */
        if (node == null || node == p || node == q) return node
        val left = findLowestCommonAncestor(node.left, p, q)
        val right = findLowestCommonAncestor(node.right, p, q)

        return if (left == null) right else if (right == null) left else node
    }

    /**
     * Maximum Width of a Binary Tree
     *
     * Problem Statement: Given a Binary Tree, return its maximum width.
     * The maximum width of a binary tree is the maximum number of nodes present at any level of the tree.
     *The width or diameter of a level is the number of nodes between the leftmost and rightmost nodes.
     *
     * Example 1:
     *
     */
    fun maxWidthOfBinaryTree(root: TreeNode?): Int {
        if (root == null) return 0
        var maxWidth = 0
        val queue = ArrayDeque<Pair<TreeNode, Int>>()
        queue.add(root to 0)
        while (queue.isNotEmpty()) {
            val size = queue.size
            var left = 0
            var right = 0
            repeat(size) {
                val (current, index) = queue.removeFirst()
                if (it == 0) left = index
                if (it == size - 1) right = index
                current.left?.let { queue.add(it to 2 * index) }
                current.right?.let { queue.add(it to 2 * index + 1) }
            }
            maxWidth = maxOf(maxWidth, right - left + 1)
        }
        return maxWidth
    }


    /**
     * Check for Children Sum Property in a Binary Tree
     *
     * Problem Statement:
     * Given a Binary Tree, convert the value of its nodes to follow the Children Sum Property.
     * The Children Sum Property in a binary tree states that for every node, the sum of its children's values (if they exist)
     * should be equal to the node's value. If a child is missing, it is considered as having a value of 0.
     *
     * Note:
     * The node values can be increased by any positive integer any number of times, but decrementing any node value is not allowed.
     * A value for a NULL node can be assumed as 0.
     * We cannot change the structure of the given binary tree.
     *
     * Example 1:
     * Input:Binary Tree: 2 35 10 2 3 5 2
     * Output: Binary Tree: 45 35 10 30 5 8 2
     */
    fun childrenSumProperty(root: TreeNode?): TreeNode? {
        if (root == null) return null
        fun dfs(node: TreeNode?) {
            if (node == null) return
            val left = node.left?.value ?: 0
            val right = node.right?.value ?: 0

            val childsum = left + right
            if (childsum >= node.value) {
                node.value = childsum
            } else {
                if (node.left != null) {
                    node.left!!.value = node.value
                }

                if (node.right != null) {
                    node.right!!.value = node.value
                }
            }

            dfs(node.left)
            dfs(node.right)

            var total = 0
            if (node.left != null) total += node.left!!.value
            if (node.right != null) total += node.right!!.value

            if (node.left != null || node.right != null) {// check if not leaf node
                node.value = total
            }
        }

        dfs(root)
        return root
    }

    /**
     * Print all the Nodes at a distance of K in a Binary Tree
     *
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
     * Output: [7,4,1]
     * Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
     *
     */
    @Important
    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        val result = mutableListOf<Int>()
        val map = mutableMapOf<TreeNode, TreeNode?>()

        //create the parent map
        fun dfs(node: TreeNode?, parent: TreeNode?) {
            if (node == null) return
            map[node] = parent
            dfs(node.left, node)
            dfs(node.right, node)
        }
        dfs(root, null)

        val queue = ArrayDeque<TreeNode?>()
        val visited = mutableSetOf<TreeNode?>()
        queue.add(target)
        visited.add(target)
        var distance = 0

        while (queue.isNotEmpty()) {
            if (distance == k) {
                result.addAll(queue.mapNotNull { it?.value })
                break
            }
            repeat(queue.size) {
                val current = queue.removeFirst()
                listOf(current?.left, current?.right, map[current]).forEach {
                    if (it != null && it !in visited) {
                        queue.add(it)
                        visited.add(it)
                    }
                }
            }
            distance++
        }

        queue.mapNotNull { it?.value }
        return result
    }

    /**
     * Minimum time taken to BURN the Binary Tree from a Node
     *
     * Given a binary tree and a node data called target.
     * Find the minimum time required to burn the complete binary tree if the target is set on fire.
     * It is known that in 1 second all nodes connected to a given node get burned. That is its left child, right child, and parent.
     * Note: The tree contains unique values.
     *
     *Input:
     *           1
     *         /   \
     *       2      3
     *     /  \      \
     *    4    5      6
     *        / \      \
     *       7   8      9
     *                    \
     *                    10
     * Target Node = 8
     * Output: 7
     * Explanation: If leaf with the value 8 is set on fire.
     * After 1 sec: 5 is set on fire.
     * After 2 sec: 2, 7 are set to fire.
     * After 3 sec: 4, 1 are set to fire.
     * After 4 sec: 3 is set to fire.
     * After 5 sec: 6 is set to fire.
     * After 6 sec: 9 is set to fire.
     * After 7 sec: 10 is set to fire.
     * It takes 7s to burn the complete tree.
     */
    fun burningTree(root: TreeNode, target: TreeNode): Int {

        val map = mutableMapOf<TreeNode, TreeNode?>()

        fun dfs(node: TreeNode?, parent: TreeNode?) {
            if (node == null) return
            map[node] = parent
            dfs(node.left, node)
            dfs(node.right, node)
        }

        dfs(root, null)

        val queue = ArrayDeque<TreeNode>()
        val visited = mutableSetOf<TreeNode>()

        queue.add(target)
        visited.add(target)

        var distance = 0
        while (queue.isNotEmpty()) {
            distance++
            repeat(queue.size) {
                val current = queue.removeFirst()
                listOf(current.left, current.right, map[current]).forEach {
                    if (it != null && it !in visited) {
                        queue.add(it)
                        visited.add(it)
                    }
                }
            }
        }
        return 0
    }

    /**
     * Problem statement
     * You are given the root of a complete binary tree, you need to calculate the number of nodes in the given complete binary tree.
     *
     * A complete binary tree is a tree in which all the levels are completely filled except the last level.
     * Nodes in the last level are as left as possible.
     *
     * For Example:
     * Input: root = [1,2,3,4,5,6]
     * Output: 6
     *
     */
    fun countNodes(root: TreeNode?): Int {
        // Naive approach
        fun dfs(node: TreeNode?): Int {
            if (node == null) return 0
            val left = dfs(node.left)
            val right = dfs(node.right)
            return 1 + left + right
        }

        //return dfs(root)

        // no of nodes in perfect binary tree = 2^h - 1, h = height of the tree


        // height of the left subtree
        fun leftHeight(node: TreeNode?): Int {
            return if (node == null) 0 else 1 + leftHeight(node.left)
        }

        // TC : O(h^2)
        fun rightHeight(node: TreeNode?): Int {
            return if (node == null) 0 else 1 + rightHeight(node.right)
        }

        fun f(node: TreeNode?): Int {
            if (node == null) return 0
            val left = leftHeight(node.left)
            val right = rightHeight(node.right)

            if (left == right) {
                return (1 shl left) - 1// we can use left shift operator here bcuz 2^h = 1 << h, it has O(1) time complexity compared to Math.pow(2, h)
            }
            return 1 + f(node.left) + f(node.right)
        }

        return f(root)
    }

    /**
     * Requirements needed to construct a Unique Binary Tree | Theory
     *
     * Geek wants to know the traversals required to construct a unique binary tree.
     * Given a pair of traversal, return true if it is possible to construct unique binary tree from the given traversals otherwise return false.
     *
     * Each traversal is represented with an integer: preorder - 1, inorder - 2, postorder - 3.
     *
     * Example 1:
     *
     * Input:
     * a = 1, b=2
     * Output: 1
     * Explanation: We can construct binary tree using inorder traversal and preorder traversal.
     * Example 2:
     *
     * Input: a = 1, b=3
     * Output: 0
     * Explanation: We cannot construct binary tree using preorder traversal and postorder traversal.
     */
    fun isPossibleToConstructTree(a: Int, b: Int): Boolean {
        /**
         * We can construct a unique binary tree from the given traversals if and only if the traversals are
         *  1. Preorder and Inorder
         *  2. Inorder and Postorder
         *  3. Preorder and Postorder
         */
        return a != b
    }

    /**
     * Given two integer arrays preorder and inorder where preorder
     * is the preorder traversal of a binary tree and inorder is the
     * inorder traversal of the same tree, construct and return the binary tree.
     *
     *Example 1:
     * preOrder = [3,9,20,15,7]
     * inOrder = [9,3,15,20,7]
     *
     * Output: [3,9,20,null,null,15,7]
     *
     */
    fun buildTreeUsingPreOrderAndInOrder(preorder: IntArray, inorder: IntArray): TreeNode? {
        /*
        We make use of a map to store the inorder traversal to get the index of the root node in O(1) time.
        preOrder gives us the root node from the first element.
        inOrder gives us the left and right subtree of the root node.
         */
        val inOrderMap = mutableMapOf<Int, Int>()
        for (i in inorder.indices) {
            inOrderMap[inorder[i]] = i
        }
        var preIndex = 0

        fun helper(left: Int, right: Int): TreeNode? {
            if (left > right) return null
            val rootValue = preorder[preIndex]
            val root = TreeNode(rootValue)
            val index = inOrderMap[rootValue]!!
            preIndex++
            root.left = helper(left, index - 1)
            root.right = helper(index + 1, right)
            return root
        }
        return helper(0, inorder.size - 1)
    }

    /**
     * Construct Binary Tree from Inorder and PostOrder Traversal
     *
     * Problem Statement: Given the Postorder and Inorder traversal of a Binary Tree,
     * construct the Unique Binary Tree represented by them.
     *
     *
     * Example 1:
     * Input: postOrder = [9,15,7,20,3], inOrder = [9,3,15,20,7]
     *
     * Output: [3,9,20,null,null,15,7]
     */
    fun buildTreeUsingPostOrderAndInOrder(postOrder: IntArray, inOrder: IntArray): TreeNode? {
        /*
        We make use of a map to store the inorder traversal to get the index of the root node in O(1) time.
        inOrder gives us the left and right subtree of the root node.
        postOrder gives us the root node from the last element.
         */
        val inOrderMap = mutableMapOf<Int, Int>()
        for (i in inOrder.indices) {
            inOrderMap[inOrder[i]] = i
        }
        var postIndex = postOrder.lastIndex

        fun helper(left: Int, right: Int): TreeNode? {
            if (left > right) return null
            val rootValue = postOrder[postIndex]
            val root = TreeNode(rootValue)
            val index = inOrderMap[rootValue]!!
            postIndex--
            root.right = helper(index + 1, right)
            root.left = helper(left, index - 1)
            return root
        }
        return helper(0, inOrder.size - 1)
    }

    /**
     * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
     * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in
     * the same or another computer environment.
     *
     * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your s
     * erialization/deserialization algorithm should work. You just need to ensure that a binary tree can be
     * serialized to a string and this string can be deserialized to the original tree structure.
     *
     *
     *
     */
    inner class Codec {
        // Encodes a tree to a single string.
        fun serialize(root: TreeNode?): String {
            val sb = StringBuilder()
            fun dfs(node: TreeNode?) {
                if (node == null) {
                    sb.append("null,")
                    return
                }
                sb.append("${node.value},")
                dfs(node.left)
                dfs(node.right)
            }
            dfs(root)
            return sb.toString()
        }

        // Decodes your encoded data to tree.
        fun deserialize(data: String): TreeNode? {
            val nodes = data.split(",")
            var index = 0
            fun dfs(): TreeNode? {
                if (nodes[index] == "null") {
                    index++
                    return null
                }
                val node = TreeNode(nodes[index].toInt())
                index++
                node.left = dfs()
                node.right = dfs()
                return node
            }
            return dfs()
        }
    }


    /**
     * Morris Inorder Traversal of a Binary Tree
     * Problem Statement:
     *
     * Given a Binary Tree, implement Morris Inorder Traversal and return the array containing its inorder sequence.
     *
     * Morris Traversal is a tree traversal algorithm aiming to achieve a space complexity of O(1)
     * without recursion or an external data structure.
     *
     * Example 1: Input: root = [1,2,3,4,5,6,7]
     *
     * Output: [4,2,5,1,6,3,7]
     */
    fun morrisInorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        var curr = root
        while (curr != null) {
            if (curr.left == null) {
                result.add(curr.value)
                curr = curr.right
            } else {
                var pre = curr.left
                while (pre?.right != null && pre.right != curr) {
                    pre = pre.right
                }
                if (pre?.right == null) {
                    pre?.right = curr
                    curr = curr.left
                } else {
                    pre.right = null
                    result.add(curr.value)
                    curr = curr.right
                }
            }
        }
        return result
    }

    /**
     * Example 1:  Input: root = [1,2,3,4,5,6,7]
     * Output: [1,2,4,5,3,6,7]
     */
    fun morrisPreorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        var curr = root
        while (curr != null) {
            if (curr.left == null) {
                result.add(curr.value)
                curr = curr.right
            } else {
                var pre = curr.left
                while (pre?.right != null && pre.right != curr) {
                    pre = pre.right
                }
                if (pre?.right == null) {
                    result.add(curr.value)
                    pre?.right = curr
                    curr = curr.left
                } else {
                    pre.right = null
                    curr = curr.right
                }
            }
        }
        return result
    }

    /**
     * Flatten Binary Tree to Linked List
     *
     * Problem Statement: Given a Binary Tree, convert it to a Linked List where the linked list nodes follow
     * the same order as the pre-order traversal of the binary tree.
     *
     * Use the right pointer of the Binary Tree as the ‘next’ pointer for the linked list and set the left pointer to null.
     * Do this in place and do not create extra nodes.
     */
    fun flatten(root: TreeNode?) {
        var current = root
        while (current != null) {
            if (current.left != null) {
                var pre = current.left
                while (pre?.right != null) {
                    pre = pre.right
                }
                pre?.right = current.right
                current.right = current.left
                current.left = null
            }
            current = current.right
        }
    }


}

fun main() {
    val binaryTree = BinaryTree()
    val root = BinaryTree.TreeNode(6)
    root.left = BinaryTree.TreeNode(4)
    root.right = BinaryTree.TreeNode(8)
    root.left?.left = BinaryTree.TreeNode(2)
    root.left?.right = BinaryTree.TreeNode(5)
    root.right?.left = BinaryTree.TreeNode(7)
    root.right?.right = BinaryTree.TreeNode(9)
    root.left?.left?.left = BinaryTree.TreeNode(1)
    root.left?.left?.right = BinaryTree.TreeNode(3)

//    println(binaryTree.boundaryTraversal(root))
    println(binaryTree.verticalOrder(root))
}