package dsa.sAq

class Basics {
    /**
     * ArrayDeque as Stack use add and removeLast
     * ArrayDeque as Queue use add and removeFirst
     *
     * ArrayDeque as Dq use addLast addFirst , removeFirst removeLast
     */

    /**
     * Implement a stack using an array
     *
     * Last In First Out (LIFO)
     */
    inner class StackUsingArray {
        val MOD = 1_000_000_007

        private val stack = IntArray(10000)
        private var top = -1

        fun push(value: Int) {
            stack[++top] = value
        }

        fun pop(): Int {
            return stack[top--]
        }

        fun peek(): Int {
            if (top == -1) {
                throw IllegalStateException("Stack is empty")
            }
            return stack[top]
        }

        fun isEmpty(): Boolean {
            return top == -1
        }

        fun size(): Int {
            return top + 1
        }
    }

    /**
     * Implement a queue using an array
     *
     * First In First Out (FIFO)
     */
    inner class QueueUsingArray {
        private val queue = IntArray(10000)
        private var front = 0
        private var rear = 0

        fun enqueue(value: Int) {
            queue[rear++] = value
        }

        fun dequeue(): Int {
            return queue[front++]
        }

        fun peek(): Int {
            if (front == rear) {
                throw IllegalStateException("Queue is empty")
            }
            return queue[front]
        }

        fun isEmpty(): Boolean {
            return front == rear
        }

        fun size(): Int {
            return rear - front
        }
    }

    /**
     * Implement stack using queue
     */
    inner class StackUsingQueue {

        /**
         * Push: Insert the element normally.
         * Pop: Rotate the queue until the last element is at the front, then remove it.
         * Top: Same as pop() but put the element back.
         * Time Complexity:
         * push() → O(n) (Due to rotation)
         * pop() → O(1)
         * top() → O(1)
         * empty() → O(1)
         *
         */
        private val q: ArrayDeque<Int> = ArrayDeque()

        fun push(x: Int) {
            q.add(x)
            repeat(q.size - 1) { q.addLast(q.removeFirst()) } // Rotate to maintain stack order
        }

        fun pop(): Int {
            return q.removeFirst() // LIFO behavior
        }

        fun top(): Int {
            return q.first() // Peek the front element
        }

        fun empty(): Boolean {
            return q.isEmpty()
        }

    }

    /**
     * Implement queue using stack
     */
    inner class QueueUsingStack {

        /*
        Intution:
        1. We can use two stacks to implement a queue.
        2. One stack is used for enqueue operation and
        another stack is used for dequeue operation.
        3. We can move all elements from the first stack to the second stack
        when we need to perform the dequeue operation.
        4. We can move all elements from the second stack to the first stack
        when we need to perform the enqueue operation.

         */
        private val s1 = ArrayDeque<Int>()
        private val s2 = ArrayDeque<Int>()

        fun enqueue(value: Int) {
            s1.add(value)
        }

        fun dequeue(): Int {
            if (s2.isEmpty()) {
                while (s1.isNotEmpty()) {
                    s2.add(s1.removeLast())
                }
            }
            return s2.removeLast()
        }

        fun peek(): Int {
            if (s2.isEmpty()) {
                while (s1.isNotEmpty()) {
                    s2.add(s1.removeLast())
                }
            }
            return s2.last()
        }

        fun isEmpty(): Boolean {
            return s1.isEmpty() && s2.isEmpty()
        }
    }

    data class Node(val value: Int) {
        var next: Node? = null
    }

    /**
     * Stack using linked list
     */
    inner class StackUsingLinkedList {

        private var top: Node? = null

        fun push(value: Int) {
            val node = Node(value)
            node.next = top
            top = node
        }

        fun pop(): Int {
            if (top == null) {
                throw IllegalStateException("Stack is empty")
            }
            val value = top!!.value
            top = top?.next
            return value
        }

        fun peek(): Int {
            if (top == null) {
                throw IllegalStateException("Stack is empty")
            }
            return top!!.value
        }

        fun isEmpty(): Boolean {
            return top == null
        }

        fun size(): Int {
            var count = 0
            var current = top
            while (current != null) {
                count++
                current = current.next
            }
            return count
        }
    }

    /**
     * Queue using LinkedList
     */
    inner class QueueUsingLinkedList {

        private var front: Node? = null
        private var rear: Node? = null

        fun enqueue(value: Int) {
            val node = Node(value)
            if (front == null) {
                front = node
                rear = node
            } else {
                rear?.next = node
                rear = node
            }
        }

        fun dequeue(): Int {
            if (front == null) {
                throw IllegalStateException("Queue is empty")
            }
            val value = front!!.value
            front = front?.next
            if (front == null) {
                rear = null
            }
            return value
        }

        fun peek(): Int {
            if (front == null) {
                throw IllegalStateException("Queue is empty")
            }
            return front!!.value
        }

        fun isEmpty(): Boolean {
            return front == null
        }

        fun size(): Int {
            var count = 0
            var current = front
            while (current != null) {
                count++
                current = current.next
            }
            return count
        }
    }

    /**
     * A Circular Queue is a fixed-size queue that treats the array as circular. This means:
     *
     * The last position connects to the first (wrap-around behavior).
     * It prevents wasted space by reusing empty slots when elements are dequeued.
     * It uses two pointers:
     * front → Points to the first element.
     * rear → Points to the last element.
     *
     */
    class MyCircularQueue(private val capacity: Int) {
        // Here is size variable defines the
        private val data = IntArray(capacity)
        private var front = 0
        private var rear = -1
        private var size = 0

        fun enqueue(value: Int): Boolean {
            if (isFull()) return false
            rear = (rear + 1) % capacity
            data[rear] = value
            size++
            return true
        }

        fun dequeue(): Int? {
            if (isEmpty()) return null
            front = (front + 1) % capacity
            size--
            return data[front]
        }

        fun front(): Int {
            return if (isEmpty()) -1 else data[front]
        }

        fun rear(): Int {
            return if (isEmpty()) -1 else data[rear]
        }

        fun isEmpty(): Boolean = size == 0

        fun isFull(): Boolean = size == capacity
    }


    //-----------------------------------------------Start Revise the concepts-----------------------------------------------------------------------------------------//
    /**
     * Example : 1 - 2 -3 -4 -5
     * Output : 5-4-3-4-1
     */
    fun reverseQueue(q: ArrayDeque<Int>) {
        if (q.isEmpty()) return

        val front = q.removeFirst()
        reverseQueue(q)
        q.add(front)
    }

    fun reverseStack(stack: ArrayDeque<Int>) {
        if (stack.isEmpty()) return

        val top = stack.removeLast() // Remove top element
        reverseStack(stack) // Reverse remaining stack
        insertAtBottom(stack, top) // Insert removed element at bottom
    }

    /**
     *  Example : 1 - 2 -3 -4 -5
     *  Output : 5-4-3-4-1
     */
    fun insertAtBottom(stack: ArrayDeque<Int>, value: Int) {
        if (stack.isEmpty()) {
            stack.add(value)
            return
        }
        val top = stack.removeLast()
        insertAtBottom(stack, value)
        stack.add(top)
    }

    fun sortStack(stack: ArrayDeque<Int>) {
        if (stack.isNotEmpty()) {
            val temp = stack.removeLast()  // Step 1: Remove top element
            sortStack(stack)         // Step 2: Sort remaining stack
            insertInSortedOrder(stack, temp)  // Step 3: Insert in correct position
        }
    }

    fun insertInSortedOrder(stack: ArrayDeque<Int>, element: Int) {
        if (stack.isEmpty() || stack.last() <= element) {
            stack.add(element)  // If empty or top is smaller, insert
        } else {
            val temp = stack.removeLast()  // Remove larger element
            insertInSortedOrder(stack, element)  // Recur until correct position is found
            stack.add(temp)  // Push back the removed element
        }
    }

    //-----------------------------------------------End Revise the concepts-----------------------------------------------------------------------------------------//


    /**
     * Valid Parentheses
     */
    fun validParentheses(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        for (c in s) {
            when (c) {
                '(', '[', '{' -> stack.add(c)
                ')' -> if (stack.removeLastOrNull() != '(') return false
                ']' -> if (stack.removeLastOrNull() != '[') return false
                '}' -> if (stack.removeLastOrNull() != '{') return false
            }
        }
        return stack.isEmpty()
    }

    /**
     * min stack
     */
    inner class MinStack {
        inner class Node(val value: Int, var min: Int)

        private val stack = ArrayDeque<Node>()

        fun push(`val`: Int) {
            if (stack.isEmpty()) {
                stack.add(Node(`val`, `val`))
            } else {
                val min = stack.last().min
                stack.add(Node(`val`, minOf(min, `val`)))
            }
        }

        fun pop() {
            stack.removeLast()
        }

        fun top(): Int {
            return stack.last().value
        }

        fun getMin(): Int {
            return stack.last().min
        }

    }
}