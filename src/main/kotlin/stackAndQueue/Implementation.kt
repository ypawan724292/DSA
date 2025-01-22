package stackAndQueue

class Implementation {

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
        val stack = ArrayDeque<Int>()
        for (i in nums.indices) {
            while (stack.isNotEmpty() && stack.first() < i - k + 1) {
                stack.removeFirst()
            }
            while (stack.isNotEmpty() && nums[stack.last()] < nums[i]) {
                stack.removeLast()
            }
            stack.add(i)
            if (i >= k - 1) {
                result[i - k + 1] = nums[stack.first()]
            }
        }
        return result
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
        private val cache = LinkedHashMap<Int, Int>(capacity, 0.75f, true)

        fun get(key: Int): Int {
            return cache.getOrDefault(key, -1)
        }

        fun put(key: Int, value: Int) {
            if (cache.size == capacity && !cache.containsKey(key)) {
                cache.remove(cache.keys.first())
            }
            cache[key] = value
        }
    }
}