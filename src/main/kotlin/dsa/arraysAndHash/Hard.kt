package dsa.arraysAndHash

class Hard {

    /**
     * 118. Pascal's Triangle
     * Given an integer numRows, return the first numRows of Pascal's triangle.
     *
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     */
    fun generate(numRows: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        for (i in 0 until numRows) {
            val row = mutableListOf<Int>()
            for (j in 0..i) {
                if (j == 0 || j == i) {
                    row.add(1)
                } else {
                    row.add(result[i - 1][j - 1] + result[i - 1][j])
                }
            }
            result.add(row)
        }
        return result
    }


    /**
     * Majority Element II with n/3
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [3,2,3]
     * Output: [3
     */
    fun majorityElement2(nums: IntArray): List<Int> {
        /**
         * At max there can be 2 elements which have count > n/3
         * Because if there are 3 elements with count > n/3 then the total count will be > n which is not possible
         */
        var candidate1 = 0
        var candidate2 = 0
        var count1 = 0
        var count2 = 0
        for (num in nums) {
            if (num == candidate1) {
                count1++
            } else if (num == candidate2) {
                count2++
            } else if (count1 == 0) {
                candidate1 = num
                count1 = 1
            } else if (count2 == 0) {
                candidate2 = num
                count2 = 1
            } else {
                // We decrement the count of both the candidates bcuz we are not sure if the current element is the candidate or not
                // it indicates that the current candidates are less likely to be the majority elements, so their counts are decremented.
                count1--
                count2--
            }
        }

        count1 = 0
        count2 = 0
        for (num in nums) {
            if (num == candidate1) {
                count1++
            } else if (num == candidate2) {
                count2++
            }
        }

        val result = mutableListOf<Int>()
        if (count1 > nums.size / 3) {
            result.add(candidate1)
        }
        if (count2 > nums.size / 3) {
            result.add(candidate2)
        }
        return result
    }


    /**
     * Subarray with given XOR
     *
     *
     * Given an array of integers A and an integer B.
     *
     * Find the total number of subarrays having bitwise XOR of all elements equals to B.
     *
     *
     * Problem Constraints
     * 1 <= length of the array <= 105
     *
     * 1 <= A[i], B <= 109
     *
     * Input Format
     * The first argument given is the integer array A.
     *
     * The second argument given is integer B.
     *
     * Output Format
     * Return the total number of subarrays having bitwise XOR equals to B.
     *
     * Example Input
     * Input 1:
     *
     *  A = [4, 2, 2, 6, 4]
     *  B = 6
     * Input 2:
     *
     *  A = [5, 6, 7, 8, 9]
     *  B = 5
     *
     * Example Output
     * Output 1:
     *  4
     * Output 2:
     *  2
     *
     *
     * Example Explanation
     * Explanation 1:
     *
     *  The subarrays having XOR of their elements as 6 are:
     *  [4, 2], [4, 2, 2, 6, 4], [2, 2, 6], [6]
     * Explanation 2:
     *
     *  The subarrays having XOR of their elements as 5 are [5] and [5, 6, 7, 8, 9]
     */
    fun solve(A: IntArray, B: Int): Int {
        val map = mutableMapOf<Int, Int>()
        var xor = 0
        var count = 0
        map[0] = 1
        for (num in A) {
            xor = xor xor num
            if (map.containsKey(xor xor B)) {
                count += map[xor xor B]!!
            }
            map[xor] = map.getOrDefault(xor, 0) + 1
        }
        return count
    }

    /**
     * 88. Merge Sorted Array
     * Solved
     * Easy
     * Topics
     * Companies
     * Hint
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
     * representing the number of elements in nums1 and nums2 respectively.
     *
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     *
     * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
     * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
     *
     *
     *
     * Example 1:
     *
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
     * Example 2:
     *
     * Input: nums1 = [1], m = 1, nums2 = [], n = 0
     * Output: [1]
     * Explanation: The arrays we are merging are [1] and [].
     * The result of the merge is [1].
     * Example 3:
     *
     * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
     * Output: [1]
     * Explanation: The arrays we are merging are [] and [1].
     * The result of the merge is [1].
     * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
     *
     */
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var i = m - 1
        var j = n - 1
        var k = m + n - 1

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i]
                i--
            } else {
                nums1[k] = nums2[j]
                j--
            }
            k--
        }

        while (j >= 0) {
            nums1[k] = nums2[j]
            j--
            k--
        }
    }

    /**
     *
     * Find the repeating and missing number
     * Given an unsorted array arr of positive integers.
     * One number a from the set [1, 2,....,n] is missing and
     * one number b occurs twice in the array. Find numbers a and b.
     *
     * Note: The test cases are generated such that there always exists one missing and one repeating number within the range [1,n].
     *
     * Examples:
     *
     * Input: arr[] = [2, 2]
     * Output: [2, 1]
     * Explanation: Repeating number is 2 and smallest positive missing number is 1.
     *
     * Input: arr[] = [4, 3, 6, 2, 1, 1]
     * Output: [1, 5]
     * Explanation: Repeating number is 1 and the missing number is 5.
     */
    fun findTwoElement(arr: IntArray): IntArray {
        //using hashing , can also solve using bit manipulation
        val n = arr.size
        val map = mutableMapOf<Int, Int>()
        for (num in arr) {
            map[num] = map.getOrDefault(num, 0) + 1
        }

        var missing = 0
        var repeating = 0
        for (i in 1..n) {
            if (!map.containsKey(i)) {
                missing = i
            } else if (map[i] == 2) {
                repeating = i
            }
        }

        return intArrayOf(repeating, missing)
    }

    /**
     * Problem Statement: Given an array of N integers, count the inversion of the array (using merge-sort).
     *
     * What is an inversion of an array? Definition: for all i & j < size of array, if i < j
     * then you have to find pair (A[i],A[j]) such that A[j] < A[i].
     *
     * Example:
     * Input: [2, 4, 1, 3, 5]
     * Output: 3 (inversions are (2,1), (4,1), (4,3))
     */
    fun mergeSortAndCount(arr: IntArray, l: Int, r: Int): Int {

        fun mergeAndCount(arr: IntArray, l: Int, mid: Int, r: Int): Int {
            val left = arr.copyOfRange(l, mid + 1)
            val right = arr.copyOfRange(mid + 1, r + 1)
            var i = 0
            var j = 0
            var k = l
            var count = 0
            while (i < left.size && j < right.size) {
                if (left[i] <= right[j]) {
                    arr[k++] = left[i++]
                } else {
                    arr[k++] = right[j++]
                    count += mid - i + 1
                }
            }
            while (i < left.size) {
                arr[k++] = left[i++]
            }
            while (j < right.size) {
                arr[k++] = right[j++]
            }
            return count
        }


        var count = 0
        if (l < r) {
            val mid = l + (r - l) / 2
            count += mergeSortAndCount(arr, l, mid)
            count += mergeSortAndCount(arr, mid + 1, r)
            count += mergeAndCount(arr, l, mid, r)
        }
        return count
    }

    /**
     * 152. Maximum Product Subarray
     * Solved
     * Medium
     * Topics
     * Companies
     * Given an integer array nums, find a
     * subarray
     *  that has the largest product, and return the product.
     *
     * The test cases are generated so that the answer will fit in a 32-bit integer.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     * Example 2:
     *
     * Input: nums = [-2,0,-1]
     * Output: 0
     * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     */
    fun maxProduct(nums: IntArray): Int {
        var max = nums[0]
        var min = nums[0]
        var result = nums[0]
        for (i in 1 until nums.size) {
            val temp = max
            max = maxOf(nums[i], max * nums[i], min * nums[i])
            min = minOf(nums[i], temp * nums[i], min * nums[i])
            result = maxOf(result, max)
        }
        return result
    }
}
