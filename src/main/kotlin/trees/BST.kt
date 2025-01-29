package trees

import annotations.Important

class BST {

    data class TreeNode(
        var value: Int,
        var left: TreeNode? = null,
        var right: TreeNode? = null
    )

    /**
     * Introduction to Binary Search Tree
     *
     * Binary Search Tree : A binary tree is a tree data structure in which each node has at most two children,
     * which are referred to as the left child and the right child. A binary search tree is a binary tree in which
     * the value of the left child of a node is less than the value of the node and the value of the right child of a
     * node is greater than the value of the node.
     *
     *  ie : left < root < right, Often called BST
     *
     * So, generally in a BST, the maximum height in almost all cases is kept in order of log(N) where N = No. of nodes
     * which is in contrast to the plain Binary Tree whose maximum height can reach the order of N when the tree is skewed.
     */


    fun searchInBST(root: TreeNode?, target: Int): TreeNode? {
        var cur = root
        while (cur != null) {
            cur = when {
                cur.value == target -> return cur
                cur.value < target -> cur.right
                else -> cur.left
            }
        }
        return null
    }

    fun minInBST(root: TreeNode?): Int {
        var cur = root
        while (cur?.left != null) {
            cur = cur.left
        }
        return cur!!.value
    }

    fun maxInBST(root: TreeNode?): Int {
        var cur = root
        while (cur?.right != null) {
            cur = cur.right
        }
        return cur!!.value
    }

    fun ceilInBST(root: TreeNode?, target: Int): Int {
        var cur = root
        var ceil = Int.MAX_VALUE
        while (cur != null) {
            if (cur.value == target) return cur.value
            if (cur.value < target) {
                cur = cur.right
            } else {
                ceil = cur.value
                cur = cur.left
            }
        }
        return ceil
    }

    fun floorInBST(root: TreeNode?, target: Int): Int {
        var cur = root
        var floor = Int.MIN_VALUE
        while (cur != null) {
            if (cur.value == target) return cur.value
            if (cur.value < target) {
                floor = cur.value
                cur = cur.right
            } else {
                cur = cur.left
            }
        }
        return floor
    }

    /**
     * Insert into a Binary Search Tree
     */
    inner class BST(value: Int) {
        private var value = value
        private var left: BST? = null
        private var right: BST? = null

        fun insert(value: Int) {
            /*
            Intution :
            We try to find the leaf node where the new node can be inserted by
             comparing the value with the current node value
             if left node is null, we insert the new node there
             else if right node is null, we insert the new node there
             */
            var cur: BST? = this
            while (cur !== null) {
                when {
                    cur.value < value -> {
                        if (cur.right == null) {
                            cur.right = BST(value)
                            return
                        }
                        cur = cur.right
                    }

                    else -> {
                        if (cur.left == null) {
                            cur.left = BST(value)
                            return
                        }
                        cur = cur.left
                    }
                }
            }
        }

        fun contains(value: Int): Boolean {
            var cur: BST? = this
            while (cur !== null) {
                cur = when {
                    cur.value == value -> return true
                    cur.value < value -> cur.right
                    else -> cur.left
                }
            }
            return false
        }

        fun remove(value: Int) {
            var cur: BST? = this

            fun removeNode(node: BST): BST? {
                if (node.left == null && node.right == null) return null
                if (node.left == null) return node.right
                if (node.right == null) return node.left

                var temp = node
                while (temp.right != null) {
                    temp = temp.right!!
                }
                temp.left = node.left
                return node.right
            }

            while (cur != null) {
                if (cur.value < value) {
                    if (cur.right != null && cur.right!!.value == value) {
                        cur.right = removeNode(cur.right!!)
                        break
                    } else {
                        cur = cur.right
                    }
                } else {
                    if (cur.left != null && cur.left!!.value == value) {
                        cur.left = removeNode(cur.left!!)
                        break
                    } else {
                        cur = cur.left
                    }
                }
            }

        }
    }


    /**
     * kth smallest or kth largest element in a BST
     *
     * The inorder traversal of a BST gives the elements in sorted order ascending
     */
    fun kthSmallestOrLargest(root: TreeNode?, k: Int): Pair<Int, Int> {

        /*
         We can do inorder and get the kth element for smalled or size - k for largest, for naive approach
         Here we do not know the size of the tree, so we have to do reverse inorder
         we can get the size by any traversal and increment count

         In BST, the inorder traversal always gives the elements in sorted order
         */
        var count = 0
        var small = 0

        fun inorder(node: TreeNode?) {
            if (node == null || count > k) return
            inorder(node.left)
            if (count++ == k - 1) small = node.value // when we are at node, we increment the count
            inorder(node.right)
        }

        inorder(root)

        var large = 0

        fun reverseInorder(node: TreeNode?) {
            if (node == null || count > k) return
            reverseInorder(node.right)
            if (count++ == k - 1) large = node.value // we
            reverseInorder(node.left)
        }

        reverseInorder(root)

        return Pair(small, large)
    }


    /**
     * Check if a binary tree is a binary search tree
     *                           6
     *      *                   / \
     *      *                  4   8
     *      *                 / \  / \
     *      *                2   5 7  9
     *      *               / \
     *      *             1    3
     *
     * Input: root = [6,4,8,3,5,7,9,1,2]
     * The above tree is a binary search tree
     */
    @Important
    fun isValidBST(root: TreeNode?): Boolean {
        /*
        Intution:
            The
         root node will lie in between the range of Int.MIN_VALUE and Int.MAX_VALUE
            left node will lie in between the range of Int.MIN_VALUE and root node value
            right node will lie in between the range of root node value and Int.MAX_VALUE

            So on
         */
        fun dfs(node: TreeNode?, min: Int, max: Int): Boolean {
            if (node == null) return true
            if (node.value < min || node.value >= max) return false // here node.value >= max because we are checking for right node
            return dfs(node.left, min, node.value) && dfs(node.right, node.value, max)
        }
        return dfs(root, Int.MIN_VALUE, Int.MAX_VALUE)
    }

    /**
     * Lowest common ancestor in a Binary Search Tree of two nodes
     * Example:
     *                    6
     *                   / \
     *                  4   8
     *                 / \  / \
     *                3   5 7  9
     *               / \
     *             1    2
     *
     */
    fun findLowestCommonAncestorBST(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        /**
         * Now, consider two nodes, n1 and n2, in the BST. We want to find their LCA. Here's the intuitive approach:
         * Start at the root: Begin your search at the root of the BST.
         * Compare with current node:
         * If both n1 and n2 are smaller than the current node's value, move to the left subtree.
         * If both n1 and n2 are larger than the current node's value, move to the right subtree.
         * If one node is smaller and the other is larger (or if one of them is equal to the current node), then the current node is the LCA.
         */
        if (root == null) return null
        if (p!!.value < root.value && q!!.value < root.value) return findLowestCommonAncestorBST(root.left, p, q)
        if (p.value > root.value && q!!.value > root.value) return findLowestCommonAncestorBST(root.right, p, q)
        return root
    }


    /**
     * Construct a BST from preorder traversal
     *
     * Example:
     * Input: preorder = [8,5,1,7,10,12]
     */
    fun constructBSTFromPreorder(preorder: IntArray): TreeNode? {
        /*
        Base case: If the preIndex is out of bounds or the current element is outside the allowed range (min to max), return null.
            Create a new node with the current element.
            Increment the preIndex.
            Recursively construct the left and right subtrees.
            Return the newly created node.
         */
        var index = 0
        fun helper(lower: Int, upper: Int): TreeNode? {
            if (index == preorder.size) return null
            val value = preorder[index]
            if (value < lower || value > upper) return null
            index++
            val root = TreeNode(value)
            root.left = helper(lower, value)
            root.right = helper(value, upper)
            return root
        }
        return helper(Int.MIN_VALUE, Int.MAX_VALUE)
    }

    /**
     * Inorder Successor/Predecessor in BST
     *
     * Inorder Successor: The inorder successor of a node in a BST is the node that has the smallest value greater than the value of the node.
     *
     * Example:
     *                     6
     *                   / \
     *                  4   8
     *                 / \  / \
     *                3   5 7  9
     *
     *
     * Inorder traversal of the above tree is 3,4,5,6,7,8,9
     * Inorder Successor of 4 is 5
     * InOrder Predecessor of 4 is 3
     *
     * Predecessor: The inorder predecessor of a node in a BST is the node that has the largest value smaller than the value of the node.
     *
     */
    fun inorderSuccessor(root: TreeNode?, target: TreeNode): Pair<TreeNode?, TreeNode?> {
        var successor: TreeNode? = null
        var predecessor: TreeNode? = null

        var temp = root
        while (temp != null) {
            if (temp.value <= target.value) {
                predecessor = temp
                temp = temp.right
            } else {
                successor = temp
                temp = temp.left
            }
        }

        return successor to predecessor
    }

    /**
     * Merge 2 BSTs
     */
    fun mergeBSTs(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        fun inorder(node: TreeNode?, list: MutableList<Int>) {
            if (node == null) return
            inorder(node.left, list)
            list.add(node.value)
            inorder(node.right, list)
        }

        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        inorder(root1, list1)
        inorder(root2, list2)

        val mergedList = mutableListOf<Int>()
        var i = 0
        var j = 0
        while (i < list1.size && j < list2.size) {
            if (list1[i] < list2[j]) {
                mergedList.add(list1[i])
                i++
            } else {
                mergedList.add(list2[j])
                j++
            }
        }
        while (i < list1.size) {
            mergedList.add(list1[i])
            i++
        }
        while (j < list2.size) {
            mergedList.add(list2[j])
            j++
        }

        fun constructBST(list: List<Int>, start: Int, end: Int): TreeNode? {
            if (start > end) return null
            val mid = start + (end - start) / 2
            val root = TreeNode(list[mid])
            root.left = constructBST(list, start, mid - 1)
            root.right = constructBST(list, mid + 1, end)
            return root
        }

        return constructBST(mergedList, 0, mergedList.size - 1)
    }

    /**
     *  Two sum in BST, check if there are two elements in a BST that sums up to a specific target
     */
    fun findTarget(root: TreeNode?, k: Int): Boolean {
        val set = mutableSetOf<Int>()
        fun dfs(node: TreeNode?): Boolean {
            if (node == null) return false
            if (set.contains(k - node.value)) return true
            set.add(node.value)
            return dfs(node.left) || dfs(node.right)
        }
        return dfs(root)
    }

    /**
     *
     * Recover BST | Correct the swapped nodes of a BST
     */
    fun recoverTree(root: TreeNode?) {

        /**
         * Intution :
         * We can do inorder traversal and get the nodes which are not in the correct order
         * We can swap the values of the nodes
         *
         * Example : [1,3,2,4,5]
         *  [1, 5, 3, 4, 2, 6]
         *
         *
         * Here 3 and 2 are swapped so 3>2, maintain the prev and first and second nodes which are swaped
         *
         * if prev > current, then there is swap,
         * first = prev, second = current
         */
        var first: TreeNode? = null
        var second: TreeNode? = null
        var prev: TreeNode? = null

        fun inorder(node: TreeNode?) {
            if (node == null) return
            inorder(node.left)
            if (prev != null && prev!!.value > node.value) {
                if (first == null) first = prev
                second = node
            }
            prev = node
            inorder(node.right)
        }

        inorder(root)
        val temp = first!!.value
        first!!.value = second!!.value
        second.value = temp
    }

    /**
     * Largest BST Subtree
     *
     */
    fun largestBSTSubtree(root: TreeNode?): Int {
        fun dfs(node: TreeNode?): Triple<Int, Int, Int> {
            if (node == null) return Triple(0, Int.MAX_VALUE, Int.MIN_VALUE)
            val (leftSize, leftMin, leftMax) = dfs(node.left)
            val (rightSize, rightMin, rightMax) = dfs(node.right)
            if (leftSize == -1 || rightSize == -1 || node.value <= leftMax || node.value >= rightMin) {
                return Triple(-1, 0, 0)
            }
            return Triple(leftSize + rightSize + 1, minOf(leftMin, node.value), maxOf(rightMax, node.value))
        }
        return dfs(root).first
    }
}