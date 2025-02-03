package dsa.heap

import java.util.*

class Medium {

    /**
     * Kth Largest Element in an Array
     */
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Int>()
        for (num in nums) {
            pq.add(num)
            if (pq.size > k) {
                pq.remove()
            }
        }


//        return pq.peek()

        // using divide and conquer {quick select}
        val pivot = nums.size - k

        fun partition(nums: IntArray, left: Int, right: Int): Int {
            var low = left
            var high = right
            val pivot = nums[left]
            while (low < high) {
                while (low < high && nums[high] >= pivot) high--
                while (low < high && nums[low] <= pivot) low++
                if (low < high) {
                    val temp = nums[low]
                    nums[low] = nums[high]
                    nums[high] = temp
                }
            }
            nums[left] = nums[low]
            nums[low] = pivot
            return low
        }

        var left = 0
        var right = nums.size - 1
        while (left < right) {
            val index = partition(nums, left, right)
            when {
                index == pivot -> return nums[index]
                index < pivot -> left = index + 1
                else -> right = index - 1
            }
        }

        return nums[left]
    }

    /**
     * Kth Smallest Element in an Array
     */
    fun findKthSmallest(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Int> { a, b -> b - a }
        for (num in nums) {
            pq.add(num)
            if (pq.size > k) {
                pq.remove()
            }
        }
        return pq.peek()
    }

    /**
     * Top K Frequent Elements
     */
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }
        val pq = PriorityQueue<Int> { a, b -> map[a]!! - map[b]!! }
        for (key in map.keys) {
            pq.add(key)
            if (pq.size > k) {
                pq.remove()
            }
        }
        return pq.toIntArray()
    }

    /**
     * Merge k Sorted Arrays
     * Difficulty: MediumAccuracy: 67.25%Submissions: 104K+Points: 4
     * Given k sorted arrays arranged in the form of a matrix of size k * k.
     * The task is to merge them into one sorted array.
     * Return the merged sorted array ( as a pointer to the merged sorted arrays in cpp, as an ArrayList in java, and list in python).
     *
     *
     * Examples :
     *
     * Input: k = 3, arr[][] = {{1,2,3},{4,5,6},{7,8,9}}
     * Output: 1 2 3 4 5 6 7 8 9
     * Explanation: Above test case has 3 sorted arrays of size 3, 3, 3 arr[][] = [[1, 2, 3],[4, 5, 6],[7, 8, 9]]. The merged list will be [1, 2, 3, 4, 5, 6, 7, 8, 9].
     * Input: k = 4, arr[][]={{1,2,3,4},{2,2,3,4},{5,5,6,6},{7,8,9,9}}
     * Output: 1 2 2 2 3 3 4 4 5 5 6 6 7 8 9 9
     * Explanation: Above test case has 4 sorted arrays of size 4, 4, 4, 4 arr[][] = [[1, 2, 2, 2], [3, 3, 4, 4], [5, 5, 6, 6], [7, 8, 9, 9 ]]. The merged list will be [1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 9, 9].
     * Expected Time Complexity: O(k2*Log(k))
     * Expected Auxiliary Space: O(k2)
     *
     * Constraints:
     * 1 <= k <= 100
     */
    fun mergeKArrays(arr: Array<IntArray>, k: Int): IntArray {
        //writing in readable code
        val pq = PriorityQueue<Pair<Int, Int>> { a, b -> a.first - b.first }
        for (i in 0 until k) {
            pq.add(Pair(arr[i][0], i))
        }
        val result = mutableListOf<Int>()
        val index = IntArray(k)
        while (pq.isNotEmpty()) {
            val (value, row) = pq.remove()
            result.add(value)
            index[row]++
            if (index[row] < k) {
                pq.add(Pair(arr[row][index[row]], row))
            }
        }
        return result.toIntArray()
    }

    /**
     * Replace elements by its rank in the array
     * Difficulty: MediumAccuracy: 49.96%Submissions: 27K+Points: 4
     * Given an array arr of N integers, the task is to replace each element of the array by its rank in the array. The rank of an element is defined as the distance between the element with the first element of the array when the array is arranged in ascending order. If two or more are same in the array then their rank is also the same as the rank of the first occurrence of the element.
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
     * Your Task:
     * Complete the function int replaceWithRank(), which takes integer N  and an array of N integers as input and returns the list in which element at each position of original array is replaced by the rank of that element.
     *
     * Expected Time Complexity: O(N * logN)
     * Expected Auxiliary Space: O(N)
     */
    fun rankTransform(arr: IntArray): IntArray {
        val sorted = arr.sorted()
        val map = mutableMapOf<Int, Int>()
        var rank = 1
        for (num in sorted) {
            map.putIfAbsent(num, rank++)
        }
//        return arr.map { map[it]!! }.toIntArray()

        // using priority queue
        val pq = PriorityQueue<Pair<Int, Int>>() { a, b -> a.first - b.first }
        for (i in 0 until arr.size) {
            pq.add(Pair(arr[i], i))
        }

        val result = mutableListOf<Int>()
        val res = IntArray(arr.size)
        var rankk = 1
        while (pq.isNotEmpty()) {
            val (num, index) = pq.poll()
            res[index] = rankk
            rankk++
        }

        return res
    }

    /**
     * 621. Task Scheduler
     * Solved
     * Medium
     * Topics
     * Companies
     * Hint
     * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n.
     * Each CPU interval can be idle or allow the completion of one task.
     * Tasks can be completed in any order, but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.
     *
     * Return the minimum number of CPU intervals required to complete all tasks.
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
     * After completing task A, you must wait two intervals before doing A again.
     * The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th interval, you can do A again as 2 intervals have passed.
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
     * There are only two types of tasks, A and B, which need to be separated by 3 intervals.
     * This leads to idling twice between repetitions of these tasks.
     */
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val map = mutableMapOf<Char, Int>()
        for (task in tasks) {
            map[task] = map.getOrDefault(task, 0) + 1
        }
        val pq = PriorityQueue<Int> { a, b -> b - a }
        pq.addAll(map.values)
        var cycles = 0
        while (pq.isNotEmpty()) {
            val temp = mutableListOf<Int>()
            for (i in 0..n) {
                if (pq.isNotEmpty()) {
                    temp.add(pq.poll() - 1)
                }
            }
            for (num in temp) {
                if (num > 0) {
                    pq.add(num)
                }
            }
            cycles += if (pq.isEmpty()) temp.size else n + 1
        }
        return cycles
    }

    /**
     * 846. Hand of Straights
     * Solved
     * Medium
     * Topics
     * Companies
     * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize,
     * and consists of groupSize consecutive cards.
     *
     * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize,
     * return true if she can rearrange the cards, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
     * Output: true
     * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
     * Example 2:
     *
     * Input: hand = [1,2,3,4,5], groupSize = 4
     * Output: false
     * Explanation: Alice's hand can not be rearranged into groups of 4.
     */
    fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
        val map = mutableMapOf<Int, Int>()
        for (num in hand) {
            map[num] = map.getOrDefault(num, 0) + 1
        }
        val pq = PriorityQueue<Int>()
        pq.addAll(map.keys)
        while (pq.isNotEmpty()) {
            val start = pq.poll()
            for (i in 0 until groupSize) {
                if (map.getOrDefault(start + i, 0) == 0) {
                    return false
                }
                map[start + i] = map[start + i]!! - 1
                if (map[start + i] == 0) {
                    pq.remove(start + i)
                }
            }
        }
        return true
    }

    /**
     * Find Subsequence of Length K With the Largest Sum
     * You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.
     *
     * Return any such subsequence as an integer array of length k.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
     *
     *
     * Example 1:
     *
     * Input: nums = [2,1,3,3], k = 2
     * Output: [3,3]
     * Explanation:
     * The subsequence has the largest sum of 3 + 3 = 6.
     * Example 2:
     *
     * Input: nums = [-1,-2,3,4], k = 3
     * Output: [-1,3,4]
     * Explanation:
     * The subsequence has the largest sum of -1 + 3 + 4 = 6.
     * Example 3:
     *
     * Input: nums = [3,4,3,3], k = 2
     * Output: [3,4]
     * Explanation:
     * The subsequence has the largest sum of 3 + 4 = 7.
     * Another possible subsequence is [4, 3].
     */
    fun maxSubsequence(nums: IntArray, k: Int): IntArray {

        val pq = PriorityQueue<IntArray> { a, b -> b[0] - a[0] }

        for (i in nums.indices) {
            pq.add(intArrayOf(nums[i], i))
            if (pq.size > k) {
                pq.remove()
            }
        }


        return pq.sortedBy { it[0] }
            .map {
                it[1]
            }.toIntArray()

    }
}