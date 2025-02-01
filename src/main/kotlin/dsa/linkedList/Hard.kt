package dsa.linkedList

import annotations.Revise

class Hard {

    data class Node(
        var data: Int, var next: Node? = null, var child: Node? = null, var random: Node? = null
    )

    /**
     * Problem Statement:
     * Given the head of a singly linked list of `n` nodes and an integer `k`, where k is less than or equal to `n`.
     * Your task is to reverse the order of each group of `k` consecutive nodes, if `n` is not divisible by `k`,
     * then the last group of remaining nodes should remain unchanged.
     *
     * Example 1:
     *
     * Input Format:
     *
     * LL: 1  2  3  4  5  6  7  8  9  10
     * K = 3
     *
     * Output: 3  2  1  6  5  4  9  8  7  10
     *
     * Explanation:
     *
     * Group 1: Reverse nodes 1 -> 2 -> 3 to become 3 -> 2 -> 1.
     * Group 2: Reverse nodes 4 -> 5 -> 6 to become 6 -> 5 -> 4.
     * Group 3: Reverse nodes 7 -> 8 -> 9 to become 9 -> 8 -> 7.
     * Node 10 remains as is since there are no more groups of size K remaining.
     */
    fun reverseInKGroups(head: Node?, k: Int): Node? {
        var curr = head
        var prev: Node? = null
        var next: Node? = null
        var count = 0

        // Check if there are at least k nodes to reverse
        var temp = head
        var nodeCount = 0
        while (temp != null && nodeCount < k) {
            temp = temp.next
            nodeCount++
        }
        if (nodeCount < k) return head // Not enough nodes to reverse

        // Reverse the first k nodes of the linked list
        while (curr != null && count < k) {
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
            count++
        }

        // next is now pointing to the (k+1)th node
        // Recursively call for the list starting from curr
        // And connect the rest of the list with the reversed portion
        if (next != null) {
            head?.next = reverseInKGroups(next, k)
        }

        // prev is the new head of the reversed group
        return prev
    }


    /**
     *
     * 61. Rotate List
     * Medium
     * Topics
     * Companies
     * Given the head of a linked list, rotate the list to the right by k places.
     *
     * Example 1:
     *
     * Input: head = [1,2,3,4,5], k = 2
     * Output: [4,5,1,2,3]
     *
     */
    fun rotateRight(head: Node?, k: Int): Node? {
        var len = 0
        var cur = head
        var prev: Node? = null

        while (cur != null) {
            len++
            prev = cur
            cur = cur.next
        }

        if (len == 0) return null
        val steps = len - k % len

        prev?.next = head


        cur = head
        for (i in 0 until steps) {
            prev = cur
            cur = cur?.next
        }

        prev?.next = null

        return cur
    }

    /**
     * Flattening a Linked List
     * Problem Statement: Given a linked list containing ‘N’ head nodes where every node in the linked list contains two pointers:
     *
     * ‘Next’ points to the next node in the list
     * ‘Child’ pointer to a linked list where the current node is the head
     * Each of these child linked lists is in sorted order and connected by a 'child' pointer.
     * Your task is to flatten this linked list such that all nodes appear in a single layer or level in a 'sorted order'.
     *
     * Example:
     *  Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
     *       |         |         |
     *       7         8         9
     *       |         |         |
     *       10        11        12
     *       |                 |
     *       13                14
     *
     * Output: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> 12 -> 13 -> 14 -> null
     *
     */
    @Revise
    class Flatten {

        fun merge(list1: Node?, list2: Node?): Node? {
            // Create a dummy node as a placeholder for the result
            val dummyNode = Node(-1)
            var res: Node? = dummyNode
            var l1 = list1
            var l2 = list2

            // Merge the lists based on data values
            while (l1 != null && l2 != null) {
                if (l1.data < l2.data) {
                    res?.child = l1
                    res = l1
                    l1 = l1.child
                } else {
                    res?.child = l2
                    res = l2
                    l2 = l2.child
                }
                res.next = null // Ensure 'next' is null for the flattened structure
            }

            // Connect the remaining elements if any
            res?.child = l1 ?: l2

            // Return the merged list, starting from the dummy node's child
            return dummyNode.child
        }

        fun flattenLinkedList(head: Node?): Node? {
            // If head is null or there is no next node, return head
            if (head?.next == null) return head

            // Recursively flatten the rest of the linked list
            val mergedHead = flattenLinkedList(head.next)

            // Merge current head with the flattened next list
            return merge(head, mergedHead)
        }
    }


    /**
     * Clone Linked List with Random and Next Pointer
     *
     * Problem Statement: Given a linked list where every node in the linked list contains two pointers:
     *
     * ‘next’ which points to the next node in the list.
     * ‘random’ which points to a random node in the list or ‘null’.
     * Create a ‘deep copy’ of the given linked list and return it.
     *
     *
     * Example:
     * Input: 1 -> 2 -> 3 -> 4 -> 5 -> null
     * Random pointers: 1 -> 3, 2 -> 1, 3 -> 5, 4 -> 2, 5 -> 4
     *
     * Output: 1 -> 2 -> 3 -> 4 -> 5 -> null
     * Random pointers: 1 -> 3, 2 -> 1, 3 -> 5, 4 -> 2, 5 -> 4
     */
    fun clone(head: Node?): Node? {
        if (head == null) return null

        val map = mutableMapOf<Node, Node>()

        var cur = head
        while (cur != null) {
            map[cur] = Node(cur.data)
            cur = cur.next
        }

        cur = head
        while (cur != null) {
            map[cur]?.next = map[cur.next]
            map[cur]?.random = map[cur.random]
            cur = cur.next
        }

        return map[head]
    }


}
