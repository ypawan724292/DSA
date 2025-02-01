package dsa.stackAndQueue

class Basics {

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
        /*
        Intution:
        1. We can use two queues to implement a stack.
        2. One queue is used for push operation and
        another queue is used for pop operation.
        3. We can move all elements from the first queue to the second queue
        when we need to perform the pop operation.
        4. We can move all elements from the second queue to the first queue
        when we need to perform the push operation.

        */
        var queue1 = ArrayDeque<Int>()
        var queue2 = ArrayDeque<Int>()

        fun push(value: Int) {
            queue1.add(value)
            while (queue2.isNotEmpty()) {
                queue1.add(queue2.removeFirst())
            }
            val temp = queue1
            queue1 = queue2
            queue2 = temp
        }

        fun pop(): Int {
            if (queue2.isEmpty()) {
                throw IllegalStateException("Stack is empty")
            }
            return queue2.removeFirst()
        }

        fun peek(): Int {
            if (queue2.isEmpty()) {
                throw IllegalStateException("Stack is empty")
            }
            return queue2.first()
        }

        fun isEmpty(): Boolean {
            return queue2.isEmpty()
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
        val stack1 = ArrayDeque<Int>()
        val stack2 = ArrayDeque<Int>()

        fun enqueue(value: Int) {
            stack1.add(value)
        }

        fun dequeue(): Int {
            if (stack2.isEmpty()) {
                while (stack1.isNotEmpty()) {
                    stack2.add(stack1.removeLast())
                }
            }
            return stack2.removeLast()
        }

        fun peek(): Int {
            if (stack2.isEmpty()) {
                while (stack1.isNotEmpty()) {
                    stack2.add(stack1.removeLast())
                }
            }
            return stack2.last()
        }

        fun isEmpty(): Boolean {
            return stack1.isEmpty() && stack2.isEmpty()
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
     * Valid Parentheses
     */
    fun validParentheses(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        for (c in s) {
            when (c) {
                '(', '[', '{' -> stack.add(c)
                ')' -> if (stack.isEmpty() || stack.removeLast() != '(') return false
                ']' -> if (stack.isEmpty() || stack.removeLast() != '[') return false
                '}' -> if (stack.isEmpty() || stack.removeLast() != '{') return false
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
                stack.addLast(Node(`val`, `val`))
            } else {
                val min = stack.last().min
                stack.addLast(Node(`val`, minOf(min, `val`)))
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