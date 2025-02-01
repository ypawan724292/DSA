package dsa.arraysAndHash

class Basics {

    /**
     * Largest Element in the Array
     */
    fun largestElement(arr: IntArray): Int {
        var max = Int.MIN_VALUE
        for (num in arr) {
            max = maxOf(max, num)
        }
        return max
    }

    /**
     * Second-largest Element in the Array without sorting
     */
    fun secondLargestElement(arr: IntArray): Int {
        var max = Int.MIN_VALUE
        var secondMax = Int.MIN_VALUE
        for (num in arr) {
            if (num > max) {
                secondMax = max
                max = num
            } else if (num > secondMax && num != max) {
                secondMax = num
            }
        }
        return secondMax
    }

    /**
     * Given an array nums, return true if the array was originally sorted in non-decreasing order,
     * then rotated some number of positions (including zero). Otherwise, return false.
     *
     * There may be duplicates in the original array.
     *
     * Note: An array A rotated by x positions results in an array B of the same length such
     * that A[i] == B[(i+x) % A.length], where % is the modulo operation.
     *
     * Example 1:
     *
     * Input: nums = [3,4,5,1,2]
     * Output: true
     * Explanation: [1,2,3,4,5] is the original sorted array.
     * You can rotate the array by x = 3 positions to begin on the the element of value 3: [3,4,5,1,2].
     * Example 2:
     *
     * Input: nums = [2,1,3,4]
     * Output: false
     * Explanation: There is no sorted array once rotated that can make nums.
     * Example 3:
     *
     * Input: nums = [1,2,3]
     * Output: true
     * Explanation: [1,2,3] is the original sorted array.
     * You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.
     *
     */
    fun checkRotation(nums: IntArray): Boolean {
        //To check only sorted
        for (i in 0 until nums.size - 1) {
            if (nums[i] > nums[i + 1]) {
                return false
            }
        }

        //rotated + sorted
        val n = nums.size
        var rotate = 0
        for (i in 0 until n) {
            if (nums[i] > nums[(i + 1) % n]) {
                rotate++
            }
        }
        return rotate <= 1
    }

    /**
     * Given an integer array nums sorted in non-decreasing order,
     * remove the duplicates in-place such that each unique element appears only once.
     * The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
     *
     * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
     *
     * Change the array nums such that the first k elements of nums contain the unique elements in the order
     * they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
     * Return k.
     *
     * Example 1:
     * Input: nums = [1,1,2]
     * Output: 2, nums = [1,2,_]
     */
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var k = 1
        for (i in 1 until nums.size) {
            if (nums[i] != nums[i - 1]) {
                nums[k++] = nums[i]
            }
        }
        return k
    }

    /**
     * 189. Rotate Array
     * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,4,5,6,7], k = 3
     * Output: [5,6,7,1,2,3,4]
     * Explanation:
     * rotate 1 steps to the right: [7,1,2,3,4,5,6]
     * rotate 2 steps to the right: [6,7,1,2,3,4,5]
     * rotate 3 steps to the right: [5,6,7,1,2,3,4]
     * Example 2:
     *
     * Input: nums = [-1,-100,3,99], k = 2
     * Output: [3,99,-1,-100]
     * Explanation:
     * rotate 1 steps to the right: [99,-1,-100,3]
     * rotate 2 steps to the right: [3,99,-1,-100]
     */
    fun rotateArray(nums: IntArray, k: Int) {
        fun reverse(start: Int, end: Int) {
            var s = start
            var e = end
            while (s < e) {
                val temp = nums[s]
                nums[s] = nums[e]
                nums[e] = temp
                s++
                e--
            }
        }

        val n = nums.size
        val k = k % n
        reverse(0, n - 1)
        reverse(0, k - 1)
        reverse(k, n - 1)
    }

    /**
     *283. Move Zeroes
     * Hint
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     *
     * Note that you must do this in-place without making a copy of the array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [0]
     */
    fun moveZeroes(nums: IntArray) {
        var i = 0
        var j = 0
        while (j < nums.size) {
            if (nums[j] != 0) {
                val temp = nums[i]
                nums[i] = nums[j]
                nums[j] = temp
                i++
            }
            j++
        }
    }

    /**
     * Given an array, arr[] sorted in ascending order and an integer k. Return true if k is present in the array, otherwise, false.
     *
     * Examples:
     *
     * Input: arr[] = [1, 2, 3, 4, 6], k = 6
     * Output: true
     * Exlpanation: Since, 6 is present in the array at index 4 (0-based indexing), output is true.
     * Input: arr[] = [1, 2, 4, 5, 6], k = 3
     * Output: false
     * Exlpanation: Since, 3 is not present in the array, output is false.
     * Input: arr[] = [2, 3, 5, 6], k = 1
     * Output: false
     * Constraints:
     * 1 <= arr.size() <= 106
     * 1 <= k <= 106
     * 1 <= arr[i] <= 106
     */
    fun searchInSortedArray(arr: IntArray, k: Int): Boolean {
        //Linear Search
        for (num in arr) {
            if (num == k) {
                return true
            }
        }

        //Binary Search
        var low = 0
        var high = arr.size - 1
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] == k -> return true
                arr[mid] < k -> low = mid + 1
                else -> high = mid - 1
            }
        }
        return false
    }

    /**
     * Union of Two Sorted Arrays with Duplicate Elements
     * Difficulty: MediumAccuracy: 31.39%Submissions: 358K+Points: 4
     * Given two sorted arrays a[] and b[], where each array may contain duplicate elements ,
     * the task is to return the elements in the union of the two arrays in sorted order.
     *
     * Union of two arrays can be defined as the set containing distinct common elements that are present in either of the arrays.
     * Examples:
     *
     * Input: a[] = [1, 2, 3, 4, 5], b[] = [1, 2, 3, 6, 7]
     * Output: 1 2 3 4 5 6 7
     * Explanation: Distinct elements including both the arrays are: 1 2 3 4 5 6 7.
     * Input: a[] = [2, 2, 3, 4, 5], b[] = [1, 1, 2, 3, 4]
     * Output: 1 2 3 4 5
     * Explanation: Distinct elements including both the arrays are: 1 2 3 4 5.
     * Input: a[] = [1, 1, 1, 1, 1], b[] = [2, 2, 2, 2, 2]
     * Output: 1 2
     * Explanation: Distinct elements including both the arrays are: 1 2.
     */
    fun unionOfTwoSortedArrays(a: IntArray, b: IntArray): IntArray {
        //Using Set
        val set = mutableSetOf<Int>()
        for (num in a) {
            set.add(num)
        }
        for (num in b) {
            set.add(num)
        }
        //return set.toIntArray()

        //two pointers approach
        val result = mutableListOf<Int>()
        var i = 0
        var j = 0
        while (i < a.size && j < b.size) {
            when {
                a[i] < b[j] -> {
                    result.add(a[i])
                    i++
                }

                a[i] > b[j] -> {
                    result.add(b[j])
                    j++
                }

                else -> {
                    if (result.isEmpty() || result.last() != a[i]) {
                        result.add(a[i])
                    }
                    i++
                    j++
                }
            }
        }

        while (i < a.size) {
            if (result.isEmpty() || result.last() != a[i]) {
                result.add(a[i])
            }
            i++
        }

        while (j < b.size) {
            if (result.isEmpty() || result.last() != b[j]) {
                result.add(b[j])
            }
            j++
        }


        return result.toIntArray()
    }


    /**
     * 268. Missing Number
     * Solved
     * Easy
     * Topics
     * Companies
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [3,0,1]
     * Output: 2
     * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3].
     * 2 is the missing number in the range since it does not appear in nums.
     */
    fun missingNumber(nums: IntArray): Int {
        // sum of n natural numbers = n*(n+1)/2
        val n = nums.size
        var sum = n * (n + 1) / 2
        for (i in nums) {
            sum -= i
        }
//        return sum
        // using xor
        var result = 0
        for (i in 0..n) {
            result = result xor i
        }
        for (i in nums) {
            result = result xor i
        }
        return result
    }

    /**
     * 485. Max Consecutive Ones
     * Solved
     * Easy
     * Topics
     * Companies
     * Hint
     * Given a binary array nums, return the maximum number of consecutive 1's in the array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,0,1,1,1]
     * Output: 3
     * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
     * Example 2:
     *
     * Input: nums = [1,0,1,1,0,1]
     * Output: 2
     *
     */
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var max = 0
        var count = 0
        for (i in nums) {
            if (i == 1) {
                count++
                max = maxOf(max, count)
            } else {
                count = 0
            }
        }
        return max
    }

    /**
     * 136. Single Number
     * Solved
     * Easy
     * Topics
     * Companies
     * Hint
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     *
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     *
     * Example 1:
     * Input: nums = [2,2,1]
     * Output: 1
     */
    fun singleNumber(nums: IntArray): Int {
        var result = 0
        for (i in nums) {
            result = result xor i
        }
        return result
    }



}

fun main() {
    val basics = Basics()
}