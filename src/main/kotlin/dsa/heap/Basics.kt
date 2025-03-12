package dsa.heap

import java.util.*

class Basics {

    /**
     * What is Heap?
     * Heap is a special type of binary tree-based data structure that satisfies the heap property.
     *
     * Heap :  special type of binary {complete binary} tree-based data structure that satisfies the heap property.
     *          It is used to implement priority queues.
     *          Types of Heap:
     *          1. Min Heap : parent node is less than or equal to child nodes
     *          2. Max Heap : parent node is greater than or equal to child nodes
     *          Operations:
     *          1. add : O(log n)
     *          2. Delete : O(log n)
     *          3. Extract Min/Max : O(log n)
     *          4. Get Min/Max : O(1)
     *          Array Representation:
     *          1. Parent Node : i-1/2
     *          2. Left Child : 2i+1
     *          3. Right Child : 2i+2
     *          example: 8,3,10,1,6,14,4,7,13
     */


    /**
     * Binary Heap Operations
     * A binary heap is a Binary Tree with the following properties:
     * 1) Its a complete tree (All levels are completely filled except possibly the last level and
     * the last level has all keys as left as possible). This property of Binary Heap makes them suitable to be stored in an array.
     *
     * 2) A Binary Heap is either Min Heap or Max Heap. In a Min Binary Heap, the key at the root
     * must be minimum among all keys present in Binary Heap. The same property must be recursively true for all nodes in Binary Tree.
     * Max Binary Heap is similar to MinHeap.
     *
     * You are given an empty Binary Min Heap and some queries and your task is to implement the
     * three methods insertKey,  deleteKey,  and extractMin on the Binary Min Heap and call them as per the query given below:
     * 1) 1  x  (a query of this type means to insert an element in the min-heap with value x )
     * 2) 2  x  (a query of this type means to remove an element at position x from the min-heap)
     * 3) 3  (a query like this removes the min element from the min-heap and prints it ).
     *
     * Example 1:
     *
     * Input:
     * Q = 7
     * Queries:
     * insertKey(4)
     * insertKey(2)
     * extractMin()
     * insertKey(6)
     * deleteKey(0)
     * extractMin()
     * extractMin()
     * Output: 2 6 - 1
     * Explanation: In the first test case for
     * query
     * insertKey(4) the heap will have  {4}
     * insertKey(2) the heap will be {2 4}
     * extractMin() removes min element from
     *              heap ie 2 and prints it
     *              now heap is {4}
     * insertKey(6) inserts 6 to heap now heap
     *              is {4 6}
     * deleteKey(0) delete element at position 0
     *              of the heap,now heap is {6}
     * extractMin() remove min element from heap
     *              ie 6 and prints it  now the
     *              heap is empty
     * extractMin() since the heap is empty thus
     *              no min element exist so -1
     *              is printed.
     * Example 2:
     *
     * Input:
     * Q = 5
     * Queries:
     * insertKey(8)
     * insertKey(9)
     * deleteKey(1)
     * extractMin()
     * extractMin()
     * Output: 8 -1
     * Your Task:
     * You are required to complete the 3 methods insertKey() which take one argument the value to be inserted, deleteKey() which takes one argument the position from where the element is to be deleted and extractMin() which returns the minimum element in the heap(-1 if the heap is empty)
     *
     * Expected Time Complexity: O(Q*Log(size of Heap) ).
     * Expected Auxiliary Space: O(1).
     *
     * Constraints:
     * 1 <= Q <= 104
     * 1 <= x <= 104
     */

    class MinHeap {
        private val heap = mutableListOf<Int>()

        private fun parent(index: Int) = (index - 1) / 2
        private fun leftChild(index: Int) = 2 * index + 1
        private fun rightChild(index: Int) = 2 * index + 2

        private fun swap(index1: Int, index2: Int) {
            val temp = heap[index1]
            heap[index1] = heap[index2]
            heap[index2] = temp
        }

        fun add(key: Int) {
            heap.add(key)
            var index = heap.size - 1
            while (index != 0 && heap[parent(index)] > heap[index]) {
                swap(index, parent(index))
                index = parent(index)
            }
        }

        fun remove(key: Int): Boolean {
            val index = heap.indexOf(key)
            if (index == -1) return false

            swap(index, heap.size - 1)
            heap.removeAt(heap.size - 1)
            minHeapify(index)
            return true
        }

        private fun minHeapify(index: Int) {
            val left = leftChild(index)
            val right = rightChild(index)
            var smallest = index

            if (left < heap.size && heap[left] < heap[smallest]) smallest = left
            if (right < heap.size && heap[right] < heap[smallest]) smallest = right

            if (smallest != index) {
                swap(index, smallest)
                minHeapify(smallest)
            }
        }

        fun extractMin(): Int {
            if (heap.isEmpty()) throw NoSuchElementException("Heap is empty")
            if (heap.size == 1) return heap.removeAt(0)

            val root = heap[0]
            heap[0] = heap.removeAt(heap.size - 1)
            minHeapify(0)
            return root
        }

        fun getMin(): Int {
            if (heap.isEmpty()) throw NoSuchElementException("Heap is empty")
            return heap[0]
        }
    }


    /**
     * Max heap using tree
     */
    class MaxHeap {
        class Node(var key: Int) {
            var left: Node? = null
            var right: Node? = null
        }

        private var root: Node? = null

        // Helper function to swap keys of two nodes
        private fun swap(node1: Node, node2: Node) {
            val temp = node1.key
            node1.key = node2.key
            node2.key = temp
        }

        // Function to add a new key to the heap
        fun add(key: Int) {
            root = addRec(root, key)
            heapifyUp(root)
        }

        private fun addRec(node: Node?, key: Int): Node {
            if (node == null) return Node(key)
            if (node.left == null) {
                node.left = addRec(node.left, key)
            } else if (node.right == null) {
                node.right = addRec(node.right, key)
            } else {
                if (height(node.left) <= height(node.right)) {
                    node.left = addRec(node.left, key)
                } else {
                    node.right = addRec(node.right, key)
                }
            }
            return node
        }

        private fun height(node: Node?): Int {
            if (node == null) return 0
            return 1 + maxOf(height(node.left), height(node.right))
        }

        private fun heapifyUp(node: Node?) {
            if (node == null) return
            if (node.left != null && node.left!!.key > node.key) {
                swap(node, node.left!!)
                heapifyUp(node.left)
            }
            if (node.right != null && node.right!!.key > node.key) {
                swap(node, node.right!!)
                heapifyUp(node.right)
            }
        }

        // Function to remove a key from the heap
        fun remove(key: Int): Boolean {
            val node = findNode(root, key) ?: return false
            val lastNode = getLastNode(root) ?: return false
            swap(node, lastNode)
            removeLastNode(root)
            heapifyDown(node)
            return true
        }

        private fun findNode(node: Node?, key: Int): Node? {
            if (node == null) return null
            if (node.key == key) return node
            return findNode(node.left, key) ?: findNode(node.right, key)
        }

        private fun getLastNode(node: Node?): Node? {
            if (node == null) return null
            val queue: Queue<Node> = LinkedList()
            queue.add(node)
            var lastNode: Node? = null
            while (queue.isNotEmpty()) {
                lastNode = queue.poll()
                if (lastNode.left != null) queue.add(lastNode.left)
                if (lastNode.right != null) queue.add(lastNode.right)
            }
            return lastNode
        }

        private fun removeLastNode(node: Node?) {
            if (node == null) return
            val queue: Queue<Node> = LinkedList()
            queue.add(node)
            var parent: Node? = null
            var lastNode: Node? = null
            while (queue.isNotEmpty()) {
                parent = lastNode
                lastNode = queue.poll()
                if (lastNode.left != null) queue.add(lastNode.left)
                if (lastNode.right != null) queue.add(lastNode.right)
            }
            if (parent != null) {
                if (parent.left == lastNode) parent.left = null
                else parent.right = null
            }
        }

        private fun heapifyDown(node: Node?) {
            if (node == null) return
            var largest = node
            if (node.left != null && node.left!!.key > largest.key) {
                largest = node.left!!
            }
            if (node.right != null && node.right!!.key > largest.key) {
                largest = node.right!!
            }
            if (largest != node) {
                swap(node, largest)
                heapifyDown(largest)
            }
        }

        // Function to extract the maximum element from the heap
        fun extractMax(): Int {
            if (root == null) throw NoSuchElementException("Heap is empty")
            val max = root!!.key
            remove(max)
            return max
        }

        // Function to get the maximum element without removing it
        fun getMax(): Int {
            if (root == null) throw NoSuchElementException("Heap is empty")
            return root!!.key
        }
    }

    inner class PriorityQueueUsingBinaryHeap {
        private val pq = PriorityQueue<Int>()
        fun add(num: Int) {
            pq.add(num)
        }

        fun delete(num: Int) {
            pq.remove(num)
        }

        fun extractMin(): Int {
            return pq.poll()
        }

        fun getMin(): Int {
            return pq.peek()
        }
    }

    /**
     * Does array represent Heap
     * Difficulty: EasyAccuracy: 30.97%Submissions: 72K+Points: 2
     * Given an array arr of size n, the task is to check if the given array can be a level order representation of a Max Heap.
     *
     * Example 1:
     *
     * Input:
     * n = 6
     * arr[] = {90, 15, 10, 7, 12, 2}
     * Output:
     * 1
     * Explanation:
     * The given array represents below tree
     *        90
     *      /    \
     *    15      10
     *   /  \     /
     * 7    12  2
     * The tree follows max-heap property as every
     * node is greater than all of its descendants.
     * Example 2:
     * Input:
     * n = 6
     * arr[] = {9, 15, 10, 7, 12, 11}
     * Output:
     * 0
     * Explanation:
     * The given array represents below tree
     *        9
     *      /    \
     *    15      10
     *   /  \     /
     * 7    12  11
     * The tree doesn't follows max-heap property 9 is
     * smaller than 15 and 10, and 10 is smaller than 11.
     * Your Task:
     * You don't need to read input or print anything. Your task is to complete the function isMaxHeap()
     * which takes the array arr[] and its size n as inputs and returns True if the given array could represent
     * a valid level order representation of a Max Heap, or else, it will return False.
     *
     * Expected Time Complexity: O(n)
     * Expected Auxiliary Space: O(1)
     *
     * Constraints:
     * 1 ≤ n ≤ 105
     * 1 ≤ arri ≤ 105
     *
     */
    fun isMaxHeap(arr: IntArray, n: Int): Boolean {
        for (i in 0 until n / 2) {
            val left = 2 * i + 1
            val right = 2 * i + 2
            if (left < n && arr[i] < arr[left]) return false
            if (right < n && arr[i] < arr[right]) return false
        }
        return true
    }

    /**
     * Convert Min Heap to Max Heap
     * You are given an array arr of N integers representing a min Heap. The task is to convert it to max Heap.
     *
     * A max-heap is a complete binary tree in which the value in each internal node is greater than or equal to the values in the children of that node.
     *
     * Example 1:
     *
     * Input:
     * N = 4
     * arr = [1, 2, 3, 4]
     * Output:
     * [4, 2, 3, 1]
     * Explanation:
     *
     * The given min Heap:
     *
     *           1
     *         /   \
     *       2       3
     *      /
     *    4
     *
     * Max Heap after conversion:
     *
     *          4
     *        /   \
     *       2     3
     *     /
     *    1
     * Example 2:
     *
     * Input:
     * N = 5
     * arr = [3, 4, 8, 11, 13]
     * Output:
     * [13, 11, 8, 3, 4]
     * Explanation:
     *
     * The given min Heap:
     *
     *           3
     *         /   \
     *       4      8
     *     /   \
     *   11     13
     *
     * Max Heap after conversion:
     *
     *           13
     *         /    \
     *       11      8
     *     /   \
     *    3     4
     *
     *
     * Your Task:
     * Complete the function int convertMinToMaxHeap(), which takes integer N and array represented minheap as input and converts it to the array representing maxheap. You don't need to return or print anything, modify the original array itself.
     *
     * Note: Only an unique solution is possible under the expected time complexity.
     *
     * Expected Time Complexity: O(N * log N)
     * Expected Auxiliary Space: O(N)
     *
     *
     * Constraints:
     *
     * 1 <= N <= 105
     * 1 <= arr[i] <= 109
     */
    fun convertMinToMaxHeap(arr: IntArray, n: Int) {
        fun maxHeapify(i: Int) {
            val left = 2 * i + 1
            val right = 2 * i + 2
            var largest = i
            if (left < n && arr[left] > arr[largest]) largest = left
            if (right < n && arr[right] > arr[largest]) largest = right
            if (largest != i) {
                val temp = arr[i]
                arr[i] = arr[largest]
                arr[largest] = temp
                maxHeapify(largest)
            }

        }
        for (i in n / 2 - 1 downTo 0) {
            maxHeapify(i)
        }
    }

    /*
    -------------------------------------------------------------------------------------------------------------------------------------------------------------
     */

    /**
     * Given k sorted arrays arranged in the form of a matrix of size k * k.
     * The task is to merge them into one sorted array.
     * Return the merged sorted array ( as a pointer to the merged sorted arrays in cpp,
     * as an ArrayList in java, and list in python).
     *
     */
    fun mergeKArrays(arr: Array<IntArray>): IntArray {
        // brute force
        val result = mutableListOf<Int>()
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                result.add(arr[i][j])
            }
        }
        //Time complexity: O(n log n)
//        return result.sort()

        // using merge two arrays
        fun merge(arr1: IntArray, arr2: IntArray): IntArray {
            val result = mutableListOf<Int>()
            var i = 0
            var j = 0
            while (i < arr1.size && j < arr2.size) {
                if (arr1[i] < arr2[j]) {
                    result.add(arr1[i])
                    i++
                } else {
                    result.add(arr2[j])
                    j++
                }
            }
            while (i < arr1.size) {
                result.add(arr1[i])
                i++
            }
            while (j < arr2.size) {
                result.add(arr2[j])
                j++
            }
            return result.toIntArray()
        }

        for (i in 1 until arr.size) {
            arr[0] = merge(arr[0], arr[i])
        }

        // time complexity: O(n * k2)

        //Using Priority Queue
        val pq = PriorityQueue<Pair<Int, Int>> { a, b -> a.first - b.first }
        for (i in arr.indices) {
            pq.add(Pair(arr[i][0], i))
        }

        while (pq.isNotEmpty()) {
            val (value, index) = pq.poll()
            result.add(value)
            if (arr[index].size > 1) {
                pq.add(Pair(arr[index][1], index))
                arr[index] = arr[index].sliceArray(1 until arr[index].size)
            }
        }

        // Tc : O(nk log k)
        return result.toIntArray()
    }


    /**
     * Replace elements by its rank in the array
     * Difficulty: MediumAccuracy: 49.96%Submissions: 21K+Points: 4
     * Given an array arr of N integers, the task is to replace each element of the array by its rank in the array.
     * The rank of an element is defined as the distance between the element with the first element of the array when
     * the array is arranged in ascending order. If two or more are same in the array then their rank is also the same
     * as the rank of the first occurrence of the element.
     *
     * Example 1:
     *
     * Input:
     * N = 6
     * arr = [20, 15, 26, 2, 98, 6]
     * Output:
     * 4, 3, 5, 1, 6, 2
     * Explanation:
     * After sorting, array becomes {2,6,15,20,26,98}
     * Rank(2) = 1 (at index 0)
     * Rank(6) = 2 (at index 1)
     * Rank(15) = 3 (at index 2)
     * Rank(20) = 4 (at index 3) and so on..
     * Example 2:
     *
     * Input:
     * N = 4
     * arr = [2, 2, 1, 6]
     * Output:
     * 2, 2, 1, 3
     * Explanation:
     * After sorting, array becomes {1, 2, 2, 6}
     * Rank(1) = 1 (at index 0)
     * Rank(2) = 2 (at index 1)
     * Rank(2) = 2 (at index 2)
     * Rank(6) = 3 (at index 3)
     * Rank(6) = 3 because rank after 2 is 3 as rank
     * of same element remains same and for next element
     * increases by 1.
     *
     */
    fun arrayRankTransform(arr: IntArray): IntArray {
        // brute force
        val sortedArr = arr.sorted()
        val map = mutableMapOf<Int, Int>()
        var rank = 1
        for (num in sortedArr) {
            if (!map.containsKey(num)) {
                map[num] = rank
                rank++
            }
        }

        val result = IntArray(arr.size)
        for (i in arr.indices) {
            result[i] = map[arr[i]]!!
        }

//        return result

        // using min heap
        val pq = PriorityQueue<Int>()
        for (num in arr) {
            pq.add(num)
        }

        while (pq.isNotEmpty()) {
            val num = pq.poll()
            if (!map.containsKey(num)) {
                map[num] = rank
                rank++
            }
        }

        for (i in arr.indices) {
            result[i] = map[arr[i]]!!
        }

        return result
    }

    /**
     *You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.
     *
     * ​Return the minimum number of intervals required to complete all tasks.
     *
     *
     *
     * Example 1:
     *
     * Input: tasks = ["A","A","A","B","B","B"], n = 2
     *
     * Output: 8
     *
     * Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
     *
     * After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.
     *
     * Example 2:
     *
     * Input: tasks = ["A","C","A","B","D","B"], n = 1
     *
     * Output: 6
     *
     * Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
     *
     * With a cooling interval of 1, you can repeat a task after just one other task.
     *
     * Example 3:
     *
     * Input: tasks = ["A","A","A", "B","B","B"], n = 3
     *
     * Output: 10
     *
     * Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
     *
     * There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.
     *
     *
     *
     * Constraints:
     *
     * 1 <= tasks.length <= 104
     * tasks[i] is an uppercase English letter.
     * 0 <= n <= 100
     */
    fun leastInterval(tasks: CharArray, n: Int): Int {
        /*
        It invoves three steps:
        1. Create a map to store the frequency of each task.
        2. Create a priority queue to store the tasks and retrieve max freq
        3. Iterate over the tasks and add to the queue and poll the task and add to the temp list
         */

        //store freq
        val map = mutableMapOf<Char, Int>()
        for (task in tasks) {
            map[task] = map.getOrDefault(task, 0) + 1
        }

        //pq for executing the max freq task and cool down time
        val pq = PriorityQueue<Int> { a, b -> b - a }
        for (value in map.values) {
            pq.add(value)
        }

        var time = 0

        val q = ArrayDeque<Pair<Int, Int>>()
        while (pq.isNotEmpty() || q.isNotEmpty()) {
            time++
            if (pq.isNotEmpty()) {
                val freq = pq.poll()
                if (freq > 1) {
                    q.add(Pair(freq - 1, time + n))
                }
            }

            if (q.isNotEmpty() && q.peek().second == time) {
                pq.add(q.remove().first)
            }
        }

        return time
    }


    /**
     * You are given an array nums of n integers and two integers k and x.
     *
     * The x-sum of an array is calculated by the following procedure:
     *
     * Count the occurrences of all elements in the array.
     * Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences, the element with the bigger value is considered more frequent.
     * Calculate the sum of the resulting array.
     * Note that if an array has less than x distinct elements, its x-sum is the sum of the array.
     *
     * Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the
     * subarra nums[i..i + k - 1].
     *
     *
     */
    fun xSum(nums: IntArray, k: Int, x: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }

        val pq = PriorityQueue<Pair<Int, Int>> { a, b ->
            if (a.second == b.second) {
                b.first - a.first
            } else {
                b.second - a.second
            }
        }

        for ((key, value) in map) {
            pq.add(Pair(key, value))
        }

        val result = IntArray(nums.size - k + 1)
        for (i in 0 until k) {
            val (key, value) = pq.poll()
            if (value > 1) {
                pq.add(Pair(key, value - 1))
            }
        }

        result[0] = pq.sumBy { it.first }

        for (i in k until nums.size) {
            val (key, value) = pq.poll()
            if (value > 1) {
                pq.add(Pair(key, value - 1))
            }
            val (key1, value1) = pq.poll()
            if (value1 > 1) {
                pq.add(Pair(key1, value1 - 1))
            }
            result[i - k + 1] = pq.sumBy { it.first }
        }

        return result
    }


    /**
     * You are given an integer array coins representing coins of different denominations and
     * an integer amount representing a total amount of money.
     */
    fun maxGold(coins: IntArray, k: Int): Double {
        // Max heap (using a PriorityQueue with reverse order)
        val maxHeap = PriorityQueue<Double>(compareByDescending { it })

        // Add all coins to the heap
        coins.forEach { maxHeap.add(it.toDouble()) }

        var totalGold = 0.0

        // Perform k chances
        repeat(k) {
            // Extract the max coin value
            val maxCoin = maxHeap.poll()
            totalGold += maxCoin

            // Push half of this value back into the heap
            maxHeap.add(maxCoin / 2)
        }

        return totalGold
    }


}
