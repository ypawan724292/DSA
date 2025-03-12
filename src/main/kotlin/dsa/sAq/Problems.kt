package dsa.sAq

class Problems {

    /**
     * Input: "((a+b))"
     * Output: "(a+b)"
     *
     * "((a+(b*c)))"
     * "(a+(b*c))"
     *
     * "(((x)))"
     *  x
     *
     *  "(((a+(b*c))+d))"
     *  "((a+(b*c))+d)"
     *
     */
    fun removeRedundantBrackets(expression: String): String {
        val stack = ArrayDeque<Char>()
        val sb = StringBuilder()

        for (ch in expression) {
            if (ch == ')') {
                var hasOperator = false
                val tempStack = ArrayDeque<Char>() // Temporary stack to store characters inside brackets

                while (stack.isNotEmpty() && stack.last() != '(') {
                    val top = stack.removeLast()
                    tempStack.add(top)
                    if (top in listOf('+', '-', '*', '/')) hasOperator = true
                }

                if (hasOperator) {
                    while (tempStack.isNotEmpty()) {
                        stack.add(tempStack.removeLast()) // Restore the expression without redundant brackets
                    }

                } else {
                    stack.removeLast() // Remove '('
                }
            } else {
                stack.add(ch)
            }
        }

        while (stack.isNotEmpty()) {
            sb.append(stack.removeLast())
        }

        return sb.reverse().toString()
    }

    /**
     * Generate the Binary Numbers from 1 to N
     */
    fun binaryOfN(n: Int): List<String> {
        val res = mutableListOf<String>()
        val q = ArrayDeque<String>()
        q.add("1")

        for (i in 1..n) {
            val value = q.removeFirst()

            res.add(value)

            q.add(value + "0")
            q.add(value + "1")
        }

        return res
    }

    /**
     * 901. Online Stock Span
     * Medium
     * Topics
     * Companies
     * Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
     *
     * The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward)
     * for which the stock price was less than or equal to the price of that day.
     *
     * For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2,
     * then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
     * Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8,
     * then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
     * Implement the StockSpanner class:
     *
     * StockSpanner() Initializes the object of the class.
     * int next(int price) Returns the span of the stock's price given that today's price is price.
     *
     *
     * Example 1:
     *
     * Input
     * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
     * [[], [100], [80], [60], [70], [60], [75], [85]]
     * Output
     * [null, 1, 1, 1, 2, 1, 4, 6]
     *
     * [null,1,1,1]
     *
     *
     * Explanation
     * StockSpanner stockSpanner = new StockSpanner();
     * stockSpanner.next(100); // return 1
     * stockSpanner.next(80);  // return 1
     * stockSpanner.next(60);  // return 1
     * stockSpanner.next(70);  // return 2
     * stockSpanner.next(60);  // return 1
     * stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
     * stockSpanner.next(85);  // return 6
     *
     *
     * Constraints:
     *
     * 1 <= price <= 105
     * At most 104 calls will be made to next.
     */
    inner class StockSpanner {
        val stack = ArrayDeque<Pair<Int, Int>>()

        fun next(price: Int): Int {
            var span = 1
            while (stack.isNotEmpty() && stack.last().first <= price) {
                span += stack.removeLast().second
            }
            stack.add(Pair(price, span))
            return span
        }
    }

    /**
     * First Unique Character in a Stream (Real-Time First Non-Repeating Character)
     *
     * Example : aababc
     * Output = a#bb#c
     */
    fun uniqueChar(s: String): String {
        var res = ""
        val map = mutableMapOf<Char, Int>()
        val q = ArrayDeque<Char>()

        for (ch in s) {
            map[ch] = map.getOrDefault(ch, 0) + 1

            q.add(ch)

            while (q.isNotEmpty() && map[q.first()]!! > 1) {
                q.removeFirst()
            }

            res += if (q.isEmpty()) "#" else q.first()
        }

        return res
    }

    /**
     * 239. Sliding Window Maximum
     * Solved
     * Hard
     * Topics
     * Companies
     * Hint
     * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
     * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
     *
     * Return the max sliding window.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
     * Output: [3,3,5,5,6,7]
     * Explanation:
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * Example 2:
     *
     * Input: nums = [1], k = 1
     * Output: [1]
     *
     */
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val n = nums.size
        val result = IntArray(n - k + 1)
        val dq = ArrayDeque<Int>()
        for (i in nums.indices) {
            if (dq.isNotEmpty() && dq.first() < i - k + 1) {
                dq.removeFirst()
            }
            while (dq.isNotEmpty() && nums[dq.last()] < nums[i]) {
                dq.removeLast()
            }
            dq.addLast(i)
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.first()]
            }
        }
        return result
    }


    /**
     * Problem statement
     * There are ‘N’ people at a party. Each person has been assigned a unique id between 0 to 'N' - 1(both inclusive).
     * A celebrity is a person who is known to everyone but does not know anyone at the party.
     *
     * Given a helper function ‘knows(A, B)’,
     * It will returns "true" if the person having id ‘A’ know the person having id ‘B’ in the party, "false" otherwise.
     *
     * Your task is to find out the celebrity at the party. Print the id of the celebrity, if there is no celebrity at the party then print -1.
     *
     * Note:
     * 1. The helper function ‘knows’ is already implemented for you.
     * 2. ‘knows(A, B)’ returns "false", if A doesn't know B.
     * 3. You should not implement helper function ‘knows’, or speculate about its implementation.
     * 4. You should minimize the number of calls to function ‘knows(A, B)’.
     * 5. There are at least 2 people at the party.
     * 6. At most one celebrity will exist.
     * Detailed explanation ( Input/output format, Notes, Images )
     * Constraints:
     * 1 <= T <= 50
     * 2 <= N <= 10^4
     *
     * Where ‘T’ is the total number of test cases, ‘N’ is the number of people at the party.
     *
     * Time Limit: 1sec
     * Sample Input 1:
     * 1
     * 2
     * Call function ‘knows(0, 1)’ // returns false
     * Call function ‘knows(1, 0)’ // returns true
     * Sample Output 1:
     * 0
     * Explanation For Sample Input 1:
     * In the first test case, there are 2 people at the party. When we call function knows(0,1), it returns false.
     * That means the person having id ‘0’ does not know a person having id ‘1'.
     * Similarly, the person having id ‘1’  knows a person having id ‘0’ as knows(1,0) returns true.
     * Thus a person having id ‘0’ is a celebrity because he is known to everyone at the party but doesn't know anyone.
     * Sample Input 2:
     * 1
     * 2
     * Call ‘knows(0, 1)’ // returns true
     * Call ‘knows(1, 0)’ // returns true
     * 2
     * Call ‘knows(0, 1)’ // returns false
     * Call ‘knows(1, 0)’ // returns false
     * Sample Output 2:
     * -1
     * -1
     * Explanation For Sample Input 2:
     * In first test case, there are 2 people at the party. The person having id ‘0’  knows a person having id ‘1’.
     * The person having id ‘1’  knows a person having id ‘0’.
     * Thus there is no celebrity at the party, because both know each other.
     * In second test case, there are 2 people at the party. The person having id ‘0’ does not knows a person having id ‘1’.
     * The person having id ‘1’  also does not knows a person having id ‘0’.
     * Thus there is no celebrity at the party, because both does not know each other.
     */
    fun findCelebrity(n: Int): Int {

        fun knows(a: Int, b: Int): Boolean {
            return false
        }

        var candidate = 0
        for (i in 1 until n) {
            if (knows(candidate, i)) {
                candidate = i
            }
        }
        for (i in 0 until n) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) {
                return -1
            }
        }

        // using the stack
        val s = ArrayDeque<Int>()
        repeat(n) { s.add(it) }

        while (s.size > 1) {
            val a = s.removeLast()
            val b = s.removeLast()

            if (knows(a, b)) {
                s.add(b)
            } else {
                s.add(a)
            }
        }
        candidate = s.removeLast()
        for (i in 0..n - 1) {
            if (!knows(i, candidate)) {
                return -1
            }
        }

        return candidate
    }

    /**
     * 146. LRU Cache
     * Solved
     * Medium
     * Topics
     * Companies
     * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
     *
     * Implement the LRUCache class:
     *
     * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
     * int get(int key) Return the value of the key if the key exists, otherwise return -1.
     * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
     * The functions get and put must each run in O(1) average time complexity.
     *
     *
     *
     * Example 1:
     *
     * Input
     * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
     * Output
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     *
     * Explanation
     * LRUCache lRUCache = new LRUCache(2);
     * lRUCache.put(1, 1); // cache is {1=1}
     * lRUCache.put(2, 2); // cache is {1=1, 2=2}
     * lRUCache.get(1);    // return 1
     * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
     * lRUCache.get(2);    // returns -1 (not found)
     * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
     * lRUCache.get(1);    // return -1 (not found)
     * lRUCache.get(3);    // return 3
     * lRUCache.get(4);    // return 4
     */
    class LRUCache(private val capacity: Int) {

        /*
        Key Observations:
            Most Recently Used (MRU) items should be at the front of the queue.
            Least Recently Used (LRU) items should be at the end of the queue.
            On a get() call, move the accessed key to the front.
            On a put() call,
            If the key exists → update its value & move it to the front.
            If capacity exceeds → remove the LRU item from both the HashMap and Deque.
         */

        private val cache = HashMap<Int, Node>()
        private val deque = ArrayDeque<Node>()

        data class Node(val key: Int, var value: Int)

        fun get(key: Int): Int {
            if (!cache.containsKey(key)) return -1

            val node = cache[key]!!
            deque.remove(node)  // Move accessed node to front
            deque.addFirst(node)

            return node.value
        }

        fun put(key: Int, value: Int) {
            if (cache.containsKey(key)) {
                val node = cache[key]!!
                node.value = value
                deque.remove(node)  // Move to front
                deque.addFirst(node)
            } else {
                if (cache.size >= capacity) {
                    val lru = deque.removeLast()  // Remove LRU
                    cache.remove(lru.key)
                }
                val newNode = Node(key, value)
                deque.addFirst(newNode)
                cache[key] = newNode
            }
        }
    }


    /**
     * Problem Statement:
     * Given a string s representing a mathematical expression containing +, -, *, / and non-negative integers, evaluate it.
     * The operators follow the standard precedence rules (Multiplication & Division > Addition & Subtraction) but no parentheses are involved.
     *
     * 🔹 Key Observations
     * Operators have different precedence
     *
     * * and / have higher precedence than + and -.
     * Example: 3 + 2 * 2 = 3 + (2 * 2) = 3 + 4 = 7, so we must process * first.
     * We can't evaluate the expression in one pass (left to right) like normal addition/subtraction because of * and / needing immediate evaluation.
     *
     * Use a stack to handle precedence
     *
     * When encountering * or /, we compute immediately with the top of the stack.
     * When encountering + or -, we store the value in the stack to be summed later.
     *
     *
     * Character	num (Accumulating Digits)	Stack	Last Operator (sign)	Action Taken
     * '1'	1	[]	'+'	Reading number
     * '2'	12	[]	'+'	Reading number
     * '+'	0 (Reset)	[12]	'+'	Push 12 to stack (addition)
     * '3'	3	[12]	'+'	Reading number
     * '4'	34	[12]	'+'	Reading number
     * '*'	0 (Reset)	[12, 34]	'*'	Push 34 to stack (multiplication)
     * '5'	5	[12, 34]	'*'	Reading number
     * '-'	0 (Reset)	[12, 170]	'-'	Compute 34 * 5 = 170, push to stack
     * '6'	6	[12, 170]	'-'	Reading number
     * '/'	0 (Reset)	[12, 170, -6]	'/'	Push -6 to stack (subtraction)
     * '2'	2	[12, 170, -6]	'/'	Reading number
     * End	0 (Reset)	[12, 170, -3]	-	Compute -6 / 2 = -3, push to stack
     *
     */
    fun calculate(s: String): Int {
        val stack = ArrayDeque<Int>()
        var num = 0
        var lastSign = '+'  // Keep track of last operator

        for (i in s.indices) {
            val ch = s[i]

            if (ch.isDigit()) {
                num = num * 10 + (ch - '0')  // Handle multi-digit numbers
            }

            if (!ch.isDigit() && ch != ' ' || i == s.length - 1) {  // If operator or last char
                when (lastSign) {
                    '+' -> stack.add(num)  // Push positive number
                    '-' -> stack.add(-num) // Push negative number
                    '*' -> stack.add(stack.removeLast() * num)  // Evaluate multiplication
                    '/' -> stack.add(stack.removeLast() / num)  // Evaluate division
                }
                lastSign = ch
                num = 0
            }
        }

        return stack.sum()  // Sum remaining values in the stack
    }


    /**
     *Decode a String – LeetCode #394
     * Problem Statement:
     * Given a string encoded with numbers and brackets, decode it.
     *
    Example Input/Output:
     *
     * Edit
     * Input: "3[a]2[bc]"
     * Output: "aaabcbc"
     *
     * Input: "3[a2[c]]"
     * Output: "accaccacc"
     */
    fun decodeString(s: String): String {
        val countStack = ArrayDeque<Int>()
        val stringStack = ArrayDeque<StringBuilder>()
        var currentString = StringBuilder()
        var k = 0

        for (char in s) {
            when {
                char.isDigit() -> k = k * 10 + (char - '0')  // Build the multiplier
                char == '[' -> {
                    countStack.add(k)
                    stringStack.add(currentString)
                    k = 0
                    currentString = StringBuilder()
                }

                char == ']' -> {
                    val repeatTimes = countStack.removeLast()
                    val decodedPart = currentString.toString().repeat(repeatTimes)
                    currentString = stringStack.removeLast().append(decodedPart)
                }

                else -> currentString.append(char)  // Append characters normally
            }
        }

        return currentString.toString()
    }

    /**
     * Maximum Frequency Stack (LeetCode #895)
     * Problem Statement
     * Design a Frequency Stack (Max-Frequency Stack) that supports the following operations efficiently:
     *
     * push(int x): Inserts x into the stack.
     * pop(): Removes and returns the most frequently occurring element. If there’s a tie, the most recently added among them is removed first.
     *
     */
    class FreqStack {
        private val freqMap = mutableMapOf<Int, Int>() // Maps number -> frequency
        private val group = mutableMapOf<Int, ArrayDeque<Int>>() // Maps frequency -> stack of numbers
        private var maxFreq = 0

        fun push(x: Int) {
            val f = freqMap.getOrDefault(x, 0) + 1
            freqMap[x] = f
            maxFreq = maxOf(maxFreq, f)

            group.computeIfAbsent(f) { ArrayDeque() }.add(x)
        }

        fun pop(): Int {
            val stack = group[maxFreq]!!
            val x = stack.removeLast()

            // Update frequency map
            freqMap[x] = freqMap[x]!! - 1
            if (stack.isEmpty()) {
                maxFreq-- // Reduce max frequency if no more elements at this level
            }

            return x
        }
    }

}
