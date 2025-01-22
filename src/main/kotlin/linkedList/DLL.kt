package linkedList

class DLL {

    /**
     * A significant characteristic of singly linked lists is their unidirectional nature, allowing traversal in only one
     * direction: forward. Moving backward is not possible because each node in a singly linked list holds two pieces of information -
     * the data and next a pointer that indicates the address of the next node.
     *
     * This structure enables efficient forward navigation, but the absence of a backward pointer restricts reverse traversal.
     *
     * Doubly Linked Lists,  as the name suggests, take the concept of 2-way traversal by introducing two pointers in each node.
     * This enables seamless traversal in both directions, making them a valuable tool for various advanced data structure applications.
     *
     * It has next and prev pointers.
     *
     */

    data class Node(
        var data: Int,
        var next: Node? = null,
        var prev: Node? = null
    )


    fun insertAtHead(head: Node?, data: Int): Node {
        // TC : O(1)
        val newNode = Node(data)
        newNode.next = head
        head?.prev = newNode

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
        newNode.prev = temp

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
        head?.next?.prev = null
        return head?.next
    }

    fun reverseDLL(head: Node?): Node? {

        var cur = head
        var prev: Node? = null

        while (cur != null) {
            val next = cur.next
            cur.next = prev
            cur.prev = next
            prev = cur
            cur = next
        }

        return prev

    }

    /**
     *
     * Function to delete all occurrences of a key
     *
     * Original DLL:
     * 10 <-> 20 <-> 10 <-> 30 <-> 10 <-> null
     * DLL after deleting all occurrences of 10:
     * 20 <-> 30 <-> null
     *
     */
    fun deleteAllOccurrences(head: Node?, key: Int): Node? {
        if (head == null) {
            return null // List is empty
        }

        var current = head
        var newHead = head

        while (current != null) {
            if (current.data == key) {
                // If the node to be deleted is the head
                if (current == newHead) {
                    current.next?.prev = null
                } else {
                    // Update the links of previous and next nodes
                    current.prev?.next = current.next
                    current.next?.prev = current.prev
                }
            }
            current = current.next
        }

        return newHead
    }

    // Function to find pairs with the given sum
    fun findPairsWithSum(head: Node?, target: Int) {
        var low = head
        var high = head

        // Move high to the last node
        while (high?.next != null) {
            high = high.next
        }

        println("Pairs with sum $target:")
        var found = false
        while (low != null && high != null && low != high && low != high.next) {
            val sum = low.data + high.data
            when {
                sum == target -> {
                    println("(${low.data}, ${high.data})")
                    found = true
                    low = low.next
                    high = high.prev
                }

                sum < target -> low = low.next
                else -> high = high.prev
            }
        }
        if (!found) {
            println("No pairs found.")
        }
    }


    // Function to remove duplicates from a sorted DLL
    fun removeDuplicates(head: Node?) {
        var current = head
        while (current?.next != null) {
            if (current.data == current.next?.data) {
                // Duplicate found; remove the next node
                current.next = current.next?.next
                current.next?.next?.prev = current
            } else {
                // Move to the next node if no duplicate
                current = current.next
            }
        }
    }
}