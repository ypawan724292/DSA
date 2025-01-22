package linkedList

class SL {

    /**
     * What is a Linked List?
     * It is a linear data structure that can be visualized as a chain with different nodes connected,
     * where each node represents a different element.
     * The difference between arrays and linked lists is that, unlike arrays,
     * the elements are not stored at a contiguous location.
     *
     * Since for any element to be added in an array, we need the exact next memory location to be empty,
     * and it is impossible to guarantee that it is possible.
     * Hence adding elements to an array is not possible after the initial assignment of size.
     *
     * A linked list is a data structure containing two crucial pieces of information, the first being the data and
     * the other being the pointer to the next element.
     *
     * The ‘head’ is the first node,
     * and the ‘tail’ is the last node in a linked list.
     *
     *
     * Types of Linked Lists:
     * 1. Singly Linked List: Each node contains only one pointer to the next node.
     * 2. Doubly Linked List: Each node contains two pointers, one to the next node and the other to the previous node.
     * 3. Circular Linked List: The last node points back to the first node.
     *
     *
     * Memory Allocation:
     * In a linked list, memory is allocated dynamically, and the size of the linked list can be increased or decreased as needed.
     * Each Node takes data and next as the two fields. So total space required for each node is 4 bytes.
     */


    data class Node(
        var data: Int, var next: Node? = null
    )


    fun insertAtHead(head: Node?, data: Int): Node {
        // TC : O(1)
        val newNode = Node(data)
        newNode.next = head
        return newNode
    }

    fun insertAtTail(head: Node?, data: Int): Node {
        // TC : O(n)
        val newNode = Node(data)
        if (head == null) {
            return newNode
        }
        var temp = head
        while (temp?.next != null) {
            temp = temp.next
        }
        temp?.next = newNode
        return head
    }

    fun deleteAtTail(head: Node?): Node? {
        // TC : O(n)
        if (head?.next == null) {
            return null
        }
        var temp = head
        while (temp?.next?.next != null) {
            temp = temp.next
        }
        temp?.next = null
        return head
    }

    fun deleteAtHead(head: Node?): Node? {
        // TC : O(1)
        return head?.next
    }

    /**
     * There is a singly-linked list head and we want to delete a node node in it.
     *
     * You are given the node to be deleted node. You will not be given access to the first node of head.
     *
     * All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.
     *
     * Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:
     *
     * The value of the given node should not exist in the linked list.
     * The number of nodes in the linked list should decrease by one.
     * All the values before node should be in the same order.
     * All the values after node should be in the same order.
     * Custom testing:
     *
     * For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
     * We will build the linked list and pass the node to your function.
     * The output will be the entire list after calling your function.
     */
    fun deleteNode(node: Node?) {
        // TC : O(1)
        node?.data = node?.next?.data!!
        node.next = node.next?.next
    }

    fun lengthOfLinkedList(head: Node?): Int {
        // TC : O(n)
        var temp = head
        var count = 0
        while (temp != null) {
            count++
            temp = temp.next
        }
        return count
    }

    fun search(head: Node?, key: Int): Boolean {
        // TC : O(n)
        var temp = head
        while (temp != null) {
            if (temp.data == key) {
                return true
            }
            temp = temp.next
        }
        return false
    }


    /**
     * Tortoise and Hare Algorithm
     *
     * It is a cycle detection algorithm that uses two pointers to traverse a linked list.
     */

    fun findMiddle(head: Node?): Node? {
        // TC : O(n)
        var slow = head
        var fast = head

        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        return slow
    }

    fun reverseLL(head: Node?): Node? {
        // TC : O(n)
        //iterative
        var prev: Node? = null
        var cur = head

        while (cur != null) {
            val next = cur.next
            cur.next = prev
            prev = cur
            cur = next
        }
//        return prev

        // using recursion
        if (head?.next == null) {
            return head
        }

        val newHead = reverseLL(head.next)
        head.next?.next = head
        head.next = null
        return newHead
    }

    fun detectLoop(head: Node?): Boolean {
        // TC : O(n)
        var slow = head
        var fast = head

        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow == fast) {
                return true
            }
        }

        return false
    }

    /**
     * Problem Statement:
     * Given the head of a linked list that may contain a cycle,
     * return the starting point of that cycle.
     *
     * If there is no cycle in the linked list return null.
     *
     */
    fun detectCycle(head: Node?): Node? {
        // TC : O(n)
        var slow = head
        var fast = head

        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow == fast) {
                slow = head
                while (slow != fast) {
                    slow = slow?.next
                    fast = fast?.next
                }
                return slow
            }
        }

        return null
    }

    /**
     *
     * Problem statement
     * Given the head of a linked list, determine the length of a loop present in the linked list; if not present, return 0.
     */
    fun lengthOfLoop(head: Node?): Int {
        // TC : O(n)
        var slow = head
        var fast = head

        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow == fast) {
                var count = 1
                slow = slow?.next
                while (slow != fast) {
                    count++
                    slow = slow?.next
                }
                return count
            }
        }
        return 0
    }

    /**
     * Problem Statement:
     *
     * Check if LL is palindrone or not
     */
    fun isPalindrome(head: Node?): Boolean {
        // TC : O(n)
        var slow = head
        var fast = head
        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        var secondHalf = slow?.next
        slow?.next = null
        secondHalf = reverseLL(secondHalf)

        var first = head
        var second = secondHalf

        while (second != null) {
            if (first?.data != second.data) {
                return false
            }
            first = first.next
            second = second.next
        }

        return true
    }


    /**
     * Problem Statement : Segregate even and odd nodes in LinkedList
     *
     * Given the head of a singly linked list, group all the nodes with odd indices together
     * followed by the nodes with even indices, and return the reordered list.
     *
     * The first node is considered odd, and the second node is even, and so on.
     *
     * Note that the relative order inside both the even and odd groups should remain as it was in the input.
     *
     * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
     */
    fun oddEvenList(head: Node?): Node? {
        // TC : O(n)
        if (head?.next == null) {
            return head
        }

        var odd = Node(-1)
        var even = Node(-1)
        var temp = head

        while (temp != null) {
            odd.next = temp.next
            odd = odd.next!!
            temp = temp.next
            even.next = temp?.next
            even = even.next!!
            temp = temp?.next
        }

        odd.next = even
        return odd
    }

    /**
     * Problem Statement:
     * Given a linked list and an integer N,
     * the task is to delete the Nth node from the end of the linked list and print the updated linked list.
     *
     *
     * Example 1:
     *
     * Input Format: 5->1->2, N=2
     *
     * Result: 5->2
     *
     *
     * Explanation: The 2nd node from the end of the linked list is 1. Therefore, we get this result after removing 1 from the linked list.
     *
     * Example 2:
     *
     * Input Format: 1->2->3->4->5, N=3
     *
     * Result: 1->2->4->5
     *
     * Explanation: The 3rd node from the end is 3, therefore, we remove 3 from the linked list.
     */
    fun removeNthFromEnd(head: Node?, n: Int): Node? {
        // TC : O(n)
        val dummy = Node(0)
        dummy.next = head
        var first: Node? = dummy
        var second: Node? = dummy

        for (i in 1..n + 1) {
            first = first?.next
        }

        while (first != null) {
            first = first.next
            second = second?.next
        }

        second?.next = second?.next?.next
        return dummy.next
    }

    /**
     * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
     *
     * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing,
     * where ⌊x⌋ denotes the largest integer less than or equal to x.
     *
     * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
     *
     *
     * Example 1:
     *
     *
     * Input: head = [1,3,4,7,1,2,6]
     * Output: [1,3,4,1,2,6]
     * Explanation:
     * The above figure represents the given linked list. The indices of the nodes are written below.
     * Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
     * We return the new list after removing this node.
     * Example 2:
     *
     *
     * Input: head = [1,2,3,4]
     * Output: [1,2,4]
     * Explanation:
     * The above figure represents the given linked list.
     * For n = 4, node 2 with value 3 is the middle node, which is marked in red.
     * Example 3:
     * Input: head = [2,1]
     * Output: [2]
     * Explanation:
     * The above figure represents the given linked list.
     * For n = 2, node 1 with value 1 is the middle node, which is marked in red.
     * Node 0 with value 2 is the only node remaining after removing node 1.
     *
     *
     */
    fun deleteMiddleNode(head: Node?): Node? {
        // TC : O(n)
        if (head?.next == null) {
            return null
        }

        if (head.next?.next == null) {
            head.next = null
            return head
        }
        var slow = head
        var fast = head
        var prev: Node? = null

        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            prev = slow
        }

        //if odd
        if (fast?.next == null) {
            prev?.next = slow?.next
        } else {
            slow?.next = slow?.next?.next
        }

        return head
    }

    /**
     * Given the head of a linked list, return the list after sorting it in ascending order.
     *
     */
    fun sortList(head: Node?): Node? {
        // TC : O(nlogn)

        if (head?.next == null) {
            return head
        }

        var fast: Node? = head
        var slow: Node? = head

        while (fast?.next != null && fast.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        val second = slow?.next
        slow?.next = null

        val fHalf = sortList(head)
        val sHalf = sortList(second)

        fun merge(): Node? {
            val dummy = Node(-1)
            var temp = dummy
            var f: Node? = fHalf
            var s: Node? = sHalf

            while (f != null && s != null) {

                if (f.data < s.data) {
                    temp.next = f
                    f = f.next
                } else {
                    temp.next = s
                    s = s.next
                }
                temp = temp.next!!
            }

            if (f != null) {
                temp.next = f
            }

            if (s != null) {
                temp.next = s
            }

            return dummy.next
        }

        return merge()
    }

    /**
     * Sort a linked list of 0s, 1s and 2s
     */
    fun sortList012(head: Node?): Node? {
        // TC : O(n)
        var zero: Node? = Node(-1)
        var one: Node? = Node(-1)
        var two: Node? = Node(-1)

        val zeroHead = zero
        val oneHead = one
        val twoHead = two

        var temp = head

        while (temp != null) {
            when (temp.data) {
                0 -> {
                    zero?.next = temp
                    zero = zero?.next!!
                }

                1 -> {
                    one?.next = temp
                    one = one?.next!!
                }

                else -> {
                    two?.next = temp
                    two = two?.next!!
                }
            }
            temp = temp.next
        }

        zero?.next = oneHead?.next
        one?.next = twoHead?.next
        two?.next = null

        return zeroHead?.next
    }

    /**
     * Problem Statement:
     * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
     *
     *
     * Example 1:
     * Input:
     * List 1 = [1,3,1,2,4], List 2 = [3,2,4]
     * Output:
     * 2 [2,4]
     * Explanation: Here, both lists intersecting nodes start from node 2.
     */
    fun getIntersectionNode(headA: Node?, headB: Node?): Node? {
        // approach 1 using set
        val set = HashSet<Node>()
        var temp = headA
        while (temp != null) {
            set.add(temp)
            temp = temp.next
        }

        temp = headB
        while (temp != null) {
            if (set.contains(temp)) {
                return temp
            }
            temp = temp.next
        }


        // apprach using length diff
        val lenA = lengthOfLinkedList(headA)
        val lenB = lengthOfLinkedList(headB)

        var x = headA
        var y = headB

        if (lenA > lenB) {
            for (i in 0 until lenA - lenB) {
                x = x?.next
            }
        } else {
            for (i in 0 until lenB - lenA) {
                y = y?.next
            }
        }

        while (x != y) {
            x = x?.next
            y = y?.next
        }

//        return x

        //using cyclic when one of the list is null to meet at the delta of length TC : O(n)
        var a = headA
        var b = headB

        while (a != b) {
            a = if (a == null) headB else a.next
            b = if (b == null) headA else b.next
        }

        return a
    }


    /**
     * Add 1 to a number represented by LL
     */
    fun plusOne(head: Node?): Node? {
        // TC : O(n)
        val temp = reverseLL(head)
        var carry = 1
        var prev: Node? = null
        var cur = temp

        while (cur != null) {
            val sum = cur.data + carry
            carry = sum / 10
            cur.data = sum % 10
            prev = cur
            cur = cur.next
        }

        if (carry > 0) {
            prev?.next = Node(carry)
        }

        return reverseLL(temp)
    }

    /**
     * Add 2 numbers represented by LL
     */
    fun add(head1: Node?, head2: Node?): Node? {
        // TC : O(n)
        val temp1 = reverseLL(head1)
        val temp2 = reverseLL(head2)

        var carry = 0
        var prev: Node? = null
        var cur1 = temp1
        var cur2 = temp2

        while (cur1 != null || cur2 != null) {
            val x = cur1?.data ?: 0
            val y = cur2?.data ?: 0

            val sum = x + y + carry
            carry = sum / 10
            val newNode = Node(sum % 10)
            newNode.next = prev
            prev = newNode

            cur1 = cur1?.next
            cur2 = cur2?.next
        }

        if (carry > 0) {
            val newNode = Node(carry)
            newNode.next = prev
            prev = newNode
        }

        return prev
    }

    fun mergeTwoLists(list1: Node?, list2: Node?): Node? {
        val dummy = Node(0) // Dummy node to simplify logic
        var current = dummy
        var l1 = list1
        var l2 = list2

        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                current.next = l1
                l1 = l1.next
            } else {
                current.next = l2
                l2 = l2.next
            }
            current = current.next!!
        }

        // Append the remaining nodes
        current.next = l1 ?: l2

        return dummy.next
    }

}