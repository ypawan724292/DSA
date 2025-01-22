package trees

//https://docs.google.com/document/d/1HPtAGnG6Uua4jUYG_LD2Rm1QNNwlw6-2t0jJJ_Locd8/edit#heading=h.qlh7ecjgd1b
class Traversals {

    /**
     * What are Tree?
     * - Tree is a type of graph that have a root node and children nodes.
     * - It is a hierarchical data structure.
     * - It is a non-linear data structure.
     * - It is a collection of entities called nodes.
     * - Nodes are connected by edges.
     * - It is a recursive data structure.
     *
     * What are Binary Trees?
     * - A binary tree is a tree data structure in which each node has at most two children,
     * which are referred to as the left child and the right child.
     *
     * Traversals in Binary Tree : Pre-order, In-order, Post-order
     *
     * In n Array Tree we will not have pre-order, in-order, post-order traversals.
     * It has only one traversal which is DFS traversal or BFS traversal.
     */
    data class TreeNode(
        val value: Int, val children: List<TreeNode> = emptyList()
    )

    data class BinaryTreeNode(
        val value: Int, val left: BinaryTreeNode? = null, val right: BinaryTreeNode? = null
    )


    /**
     *  Preorder Traversal
     * Root -> Left -> Right
     */
    fun preorder(node: BinaryTreeNode?) {
        if (node == null) return
        print(node.value)
        preorder(node.left)
        preorder(node.right)
    }

    /**
     * Inorder Traversal
     *  Left -> Root -> Right
     */
    fun inorder(node: BinaryTreeNode?) {
        if (node == null) return
        inorder(node.left)
        print(node.value)
        inorder(node.right)
    }


    /**
     * Postorder Traversal
     * Left -> Right -> Root
     */
    fun postorder(node: BinaryTreeNode?) {
        if (node == null) return
        postorder(node.left)
        postorder(node.right)
        print(node.value)
    }


    fun dfsIterative(node: TreeNode) {
        val stack = ArrayDeque<TreeNode>()
        stack.add(node)
        while (stack.isNotEmpty()) {
            val current = stack.removeLast()
            print(current.value)
            stack.addAll(current.children)
        }
    }

    fun bfs(node: TreeNode) {
        val queue = ArrayDeque<TreeNode>()
        queue.add(node)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            print(current.value)
            queue.addAll(current.children)
        }
    }

    fun dfs(node: TreeNode) {
        print(node.value)
        node.children.forEach {
            dfs(it)
        }
    }

    fun bfs(node: BinaryTreeNode) {
        val queue = ArrayDeque<BinaryTreeNode>()
        queue.add(node)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            print(current.value)
            current.left?.let { queue.add(it) }
            current.right?.let { queue.add(it) }
        }
    }

    /**
     * Given the root of a binary tree,
     * return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
     *
     */
    fun levelOrder(root: BinaryTree.TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        val result = mutableListOf<List<Int>>()
        val queue = ArrayDeque<BinaryTree.TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val level = mutableListOf<Int>()
            repeat(queue.size) {
                val current = queue.removeFirst()
                level.add(current.value)
                current.left?.let { queue.add(it) }
                current.right?.let { queue.add(it) }
            }
            result.add(level)
        }
        return result
    }

    fun iterativePreorder(root: BinaryTreeNode) {
        val stack = ArrayDeque<BinaryTreeNode>()
        stack.add(root)
        while (stack.isNotEmpty()) {
            val current = stack.removeLast()
            print(current.value)
            current.right?.let { stack.add(it) }
            current.left?.let { stack.add(it) } // important check here
        }
    }

    fun iterativeInorder(root: BinaryTreeNode) {
        val stack = ArrayDeque<BinaryTreeNode>()
        var current: BinaryTreeNode? = root
        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.add(current)
                current = current.left
            }
            current = stack.removeLast()
            print(current.value)
            current = current.right
        }
    }

    fun iterativePostorderUsingTwoStacks(node: BinaryTreeNode) {
        val stack = ArrayDeque<BinaryTreeNode>()
        val output = ArrayDeque<Int>()
        stack.add(node)
        while (stack.isNotEmpty()) {
            val current = stack.removeLast()
            output.addFirst(current.value)
            current.left?.let { stack.add(it) }
            current.right?.let { stack.add(it) }
        }
        output.forEach { print(it) }
    }

    fun iterativePostorderUsingOneStack(node: BinaryTreeNode) {
        val stack = ArrayDeque<BinaryTreeNode>()
        var current: BinaryTreeNode? = node
        var prev: BinaryTreeNode? = null
        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.add(current)
                current = current.left
            }
            current = stack.last()
            if (current.right == null || current.right == prev) {
                print(current.value)
                stack.removeLast()
                prev = current
                current = null
            } else {
                current = current.right
            }
        }
    }

    fun prePostInorderTraversalsInOneTraversal(node: BinaryTreeNode) {
        val preOrder = mutableListOf<Int>()
        val postOrder = mutableListOf<Int>()
        val inOrder = mutableListOf<Int>()

        fun dfs(node: BinaryTreeNode?) {
            if (node == null) return
            preOrder.add(node.value)
            dfs(node.left)
            inOrder.add(node.value)
            dfs(node.right)
            postOrder.add(node.value)
        }
        dfs(node)
        println("PreOrder: $preOrder")
        println("InOrder: $inOrder")
        println("PostOrder: $postOrder")
    }

}