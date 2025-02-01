package dsa.heap

import java.util.*

class Hard {

    /**
     * Design Twitter
     * https://leetcode.com/problems/design-twitter/
     */
    class Twitter() {

        /** Initialize your data structure here. */
        private val tweets = mutableMapOf<Int, ArrayDeque<Pair<Int, Int>>>()
        private val followers = mutableMapOf<Int, MutableSet<Int>>()


        /** Compose a new tweet. */
        private var timestamp = 0
        fun postTweet(userId: Int, tweetId: Int) {
            tweets[userId] = tweets.getOrDefault(userId, ArrayDeque()).apply {
                add(Pair(tweetId, timestamp++))
                if (size > 10) {
                    removeFirst()
                }
            }
        }

        /**
         * Retrieve the 10 most recent tweet ids in the user's news feed.
         * Each item in the news feed must be posted by users who the user followed or by the user herself.
         */
        fun getNewsFeed(userId: Int): List<Int> {
            val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
            val followees = followers.getOrDefault(userId, mutableSetOf()).apply { add(userId) }
            followees.forEach { followee ->
                tweets[followee]?.forEach { tweet ->
                    pq.add(tweet)
                    if (pq.size > 10) {
                        pq.poll()
                    }
                }
            }

            return pq.toList().map { it.first }.reversed()
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        fun follow(followerId: Int, followeeId: Int) {
            followers[followerId] = followers.getOrDefault(followerId, mutableSetOf()).apply {
                add(followeeId)
            }

        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        fun unfollow(followerId: Int, followeeId: Int) {
            followers[followerId]?.remove(followeeId)
        }
    }


    /**
     *
     * Connect n ropes with minimum cost
     *
     * Problem: https://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/
     *
     * Given n ropes of different lengths, we need to connect these ropes into one rope.
     * We can connect only 2 ropes at a time. The cost required to connect 2 ropes is equal to sum of their lengths.
     * The length of this connected rope is also equal to the sum of their lengths.
     *
     * Example:
     * Input: ropes = [8, 4, 6, 12]
     * Output: 58
     *
     *
     */
    fun connectRopesWithMinimumCost(ropes: IntArray): Int {
        val pq = PriorityQueue<Int>()
        ropes.forEach { pq.add(it) }
        var cost = 0
        while (pq.size > 1) {
            val a = pq.poll()
            val b = pq.poll()
            cost += a + b
            pq.add(a + b)
        }
        return cost
    }

    /**
     * 703. Kth Largest Element in a Stream
     * You are part of a university admissions office and need to keep track of the kth highest test score
     * from applicants in real-time.
     * This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.
     *
     * You are tasked to implement a class which, for a given integer k,
     * maintains a stream of test scores and continuously returns the kth highest test score after a new score
     * has been submitted. More specifically, we are looking for the kth highest score in the sorted list of all scores.
     *
     * Implement the KthLargest class:
     *
     * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of test scores nums.
     * int add(int val) Adds a new test score val to the stream and returns the element
     * representing the kth largest element in the pool of test scores so far.
     */
    class KthLargest(val k: Int, nums: IntArray) {
        private val pq = PriorityQueue<Int>()

        init {
            nums.forEach { add(it) }
        }

        fun add(value: Int): Int {
            pq.add(value)
            if (pq.size > k) {
                pq.remove()
            }
            return pq.peek()
        }
    }

    /**
     * Maximum Sum Combinations
     * Problem Description
     *
     * Given two equally sized 1-D arrays A, B containing N integers each.
     *
     * A sum combination is made by adding one element from array A and another element of array B.
     *
     * Return the maximum C valid sum combinations from all the possible sum combinations.
     *
     *
     * Problem Constraints
     * 1 <= N <= 105
     * 1 <= A[i] <= 105
     * 1 <= C <= N
     *
     * Example Input
     * Input 1:
     *
     *  A = [3, 2]
     *  B = [1, 4]
     *  C = 2
     * Input 2:
     *
     *  A = [1, 4, 2, 3]
     *  B = [2, 5, 1, 6]
     *  C = 4
     *
     * Example Output
     * Output 1:
     *
     *  [7, 6]
     * Output 1:
     *
     *  [10, 9, 9, 8]
     *
     *
     * Example Explanation
     * Explanation 1:
     *
     *  7     (A : 3) + (B : 4)
     *  6     (A : 2) + (B : 4)
     * Explanation 2:
     *
     *  10   (A : 4) + (B : 6)
     *  9   (A : 4) + (B : 5)
     *  9   (A : 3) + (B : 6)
     *  8   (A : 3) + (B : 5)
     */
    fun solve(A: IntArray, B: IntArray, C: Int): IntArray {
        val pq = PriorityQueue<Int>()
        for (a in A) {
            for (b in B) {
                pq.add(a + b)
                if (pq.size > C) {
                    pq.poll()
                }
            }
        }
        val res = IntArray(C)
        for (i in C - 1 downTo 0) {
            res[i] = pq.poll()
        }
        return res
    }

    /**
     * Find the median of a stream of numbers
     * https://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/
     *
     * Problem:
     * 295. Find Median from Data Stream
     * Companies
     * The median is the middle value in an ordered integer list.
     * If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
     *
     * For example, for arr = [2,3,4], the median is 3.
     * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
     * Implement the MedianFinder class:
     *
     * MedianFinder() initializes the MedianFinder object.
     * void addNum(int num) adds the integer num from the data stream to the data structure.
     * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
     *
     * Example:
     * Input: 5, 15, 1, 3
     * Output: 5, 10, 5, 4
     *
     */
    class MedianFinder() {
        /*
        Intuition:
            To efficiently find the median in a dynamic data stream, we can use two heaps:
            Max-Heap: To store the smaller half of the numbers.
            Min-Heap: To store the larger half of the numbers.
            The max-heap will contain the largest element of the smaller half, and the min-heap will contain the smallest element of the larger half. This allows us to quickly find the median:
         */
        private val maxHeap = PriorityQueue<Int>(compareBy { -it })
        private val minHeap = PriorityQueue<Int>()

        fun addNum(num: Int) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.add(num)
            } else {
                minHeap.add(num)
            }

            if (maxHeap.size > minHeap.size + 1) {
                minHeap.add(maxHeap.remove())
            } else if (minHeap.size > maxHeap.size) {
                maxHeap.add(minHeap.remove())
            }
        }

        fun findMedian(): Double {
            return if (maxHeap.size == minHeap.size) {
                (maxHeap.peek() + minHeap.peek()) / 2.0
            } else {
                maxHeap.peek().toDouble()
            }
        }

    }

    /**
     * 347. Top K Frequent Elements
     * Given an integer array nums and an integer k, return the k most frequent elements.
     * You may return the answer in any order.
     *
     * Example 1:
     *
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     * Example 2:
     *
     * Input: nums = [1], k = 1
     * Output: [1]
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * k is in the range [1, the number of unique elements in the array].
     * It is guaranteed that the answer is unique.
     *
     */
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }
        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        for ((key, value) in map) {
            minHeap.add(Pair(key, value))
            if (minHeap.size > k) {
                minHeap.remove()
            }
        }

        val res = IntArray(k)

        for (i in k - 1 downTo 0) {
            res[i] = minHeap.remove().first
        }

        return res
    }

}