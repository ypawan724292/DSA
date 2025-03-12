package dsa.binarySearch

import annotations.Important
import annotations.Revise

class `1D` {

    /**
     * Binary Search to find an element in a sorted array
     * Time complexity: O(log n)
     *
     * We can perform binary search only on a sorted array
     *
     * Example: [1,2,3,4,5,6,7,8,9], target = 8
     * Output: 7
     */
    fun binarySearch(arr: IntArray, x: Int): Int {
        var low = 0
        var high = arr.lastIndex
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] == x -> return mid
                arr[mid] < x -> low = mid + 1
                else -> high = mid - 1
            }
        }
        return -1
    }


    /**
     * Find the lower bound of an element in a sorted array
     *
     * Lower bound is the index of the first element that is greater than or equal to the target element
     *
     * Example: [1,2,3,4,5,6,7,8,9], target = 8
     * Output: 7
     *
     * arr[i] >= target
     *
     *
     */
    fun lowerBound(arr: IntArray, x: Int): Int {
        var low = 0
        var high = arr.lastIndex
        var res = arr.size
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] >= x -> {
                    res = mid
                    high = mid - 1
                }

                else -> {
                    low = mid + 1
                }
            }
        }
        return res
    }


    /**
     * Find the upper bound of an element in a sorted array
     *
     * Upper bound is the index of the first element that is greater than the target element
     *
     * Example: [1,2,3,4,5,6,7,8,9], target = 8
     * Output: 8
     *
     * arr[i] > target
     *
     */
    fun upperBound(arr: IntArray, x: Int): Int {
        var low = 0
        var high = arr.lastIndex
        var res = arr.size
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] > x -> {
                    res = mid
                    high = mid - 1
                }

                else -> {
                    low = mid + 1
                }
            }
        }
        return res
    }


    /**
     * Floor of an element in a sorted array
     *
     * Floor of an element is the greatest element in the array which is less than or equal to the target element
     *
     * Example: [1,2,3,4,5,6,7,9,10], target = 4
     * Output: 3
     *
     * arr[i] <= target
     */
    fun floor(arr: IntArray, x: Int): Int {
        var low = 0
        var high = arr.lastIndex
        var res = -1
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] == x -> return mid
                arr[mid] < x -> {
                    res = mid
                    low = mid + 1
                }

                else -> high = mid - 1
            }
        }
        return res
    }

    /**
     * Ceil of an element in a sorted array
     *
     * Ceil of an element is the smallest element in the array which is greater than or equal to the target element
     *
     * Example: [1,2,3,4,5,6,7,9,10], target = 8
     * Output: 7
     *
     * arr[i] >= target
     */
    fun ceil(arr: IntArray, x: Int): Int {
        var low = 0
        var high = arr.lastIndex
        var res = -1
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] == x -> return mid
                arr[mid] > x -> {
                    res = mid
                    high = mid - 1
                }

                else -> low = mid + 1
            }
        }
        return res
    }

    /**
     * Search insert position
     *
     * You are given a sorted array arr of distinct values and a target value x.
     * You need to search for the index of the target value in the array.
     *
     * If the value is present in the array, then return its index.
     * Otherwise, determine the index where it would be inserted in the array while maintaining the sorted order.
     *
     * Example: [1,3,5,6], target = 5
     *
     * Output: 2
     *
     * Example: [1,3,5,6], target = 2
     *
     * Output: 1
     *
     * This is 1st element arr[i] >= x
     */
    fun searchInsert(arr: IntArray, x: Int): Int {
        var low = 0
        var high = arr.lastIndex
        var res = arr.size
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] < x -> {
                    low = mid + 1
                }

                else -> {
                    res = mid
                    high = mid - 1
                }
            }
        }
        return res
    }

    /**
     * Find the first or last occurrence of an element in a sorted array
     *
     * Given a sorted array of N integers, write a program to find the index of the last occurrence of the target key.
     *
     * If the target is not found then return -1.
     *
     * Example: [1,2,2,2,2,3,4,7,8,8], target = 2
     *
     * Output: 4 for last occurrence
     * Output: 2 for first occurrence
     */
    fun findFirstOrLastOccurrence(arr: IntArray, target: Int, isFirst: Boolean): Int {
        var res = -1
        var low = 0
        var high = arr.lastIndex
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] == target -> {
                    res = mid
                    if (isFirst) {
                        high = mid - 1
                    } else {
                        low = mid + 1
                    }
                }

                arr[mid] < target -> low = mid + 1
                else -> high = mid - 1
            }
        }
        return res

    }

    /**
     * Find the smallest missing element from a sorted array
     * Given a sorted array of non-negative distinct integers, find the smallest missing non-negative element in it.
     *
     * For example,
     *
     * Input:  nums[] = [0, 1, 2, 6, 9, 11, 15]
     * Output: The smallest missing element is 3
     *
     * Input:  nums[] = [1, 2, 3, 4, 6, 9, 11, 15]
     * Output: The smallest missing element is 0
     *
     * Input:  nums[] = [0, 1, 2, 3, 4, 5, 6]
     * Output: The smallest missing element is 7
     */
    fun smalledMissingElement(num: IntArray): Int {
        var low = 0
        var high = num.lastIndex

        while (low <= high) {
            val mid = low + (high - low) / 2
            if (num[mid] == mid) low = mid + 1
            else {
                high = mid - 1
            }
        }

        return low
    }

    /**
     * You are given a sorted array containing N integers and a number X,
     * you have to find the occurrences of X in the given array.
     *
     *
     * Example: [1,2,2,2,2,3,4,7,8,8], target = 2
     * Output: 4 i.e 4 occurrences of 2
     *
     *
     * Example : [1,2,3,4,5,6,7,8,9], target = 10
     * Output: 0
     *
     */
    fun countOccurrences(arr: IntArray, target: Int): Int {
        // naive approach
        var count = 0
        for (i in arr.indices) {
            if (arr[i] == target) {
                count++
            }
        }
        // return count

        // we can also find the no of 1's in the binary sorted array using this
        val first = findFirstOrLastOccurrence(arr, target, true) //lower bound 1st ele which is arr[i]>= target
        val last = findFirstOrLastOccurrence(arr, target, false) // upper bound 1st ele which is arr[i] > target
        return (last - first) + 1
    }

    /**
     * Search in Rotated Sorted Array I
     *
     * example: [4,5,6,7,0,1,2], target = 0
     */
    @Important
    fun search(arr: IntArray, x: Int): Int {
        var low = 0
        var high = arr.lastIndex
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {

                arr[mid] == x -> return mid
                //left half is sorted
                arr[low] <= arr[mid] -> {
//                    if (x in arr[low]..arr[mid]) {
                    if (arr[low] <= x && x <= arr[mid]) {
                        high = mid - 1
                    } else {
                        low = mid + 1
                    }

                }
                //right half is sorted
                else -> {
//                    if (x in arr[mid]..arr[high]) {
                    if (arr[mid] <= x && x <= arr[high]) {
                        low = mid + 1
                    } else {
                        high = mid - 1
                    }
                }
            }
        }
        return -1
    }

    /**
     * Find the peak element in an array
     * Given an integer array, find the peak element in it. A peak element is an element that is greater than its neighbors.
     * There might be multiple peak elements in an array, and the solution should report any peak element.
     *
     * An element A[i] of an array A is a peak element if it’s not smaller than its neighbor(s).
     *
     * A[i-1] <= A[i] >= A[i+1] for 0 < i < n-1A[i-1] <= A[i] if i = n – 1A[i] >= A[i+1] if i = 0
     * For example,
     *
     * Input : [8, 9, 10, 2, 5, 6]
     * Output: The peak element is 10 (or 6)
     * Input : [8, 9, 10, 12, 15]
     * Output: The peak element is 15  Input : [10, 8, 6, 5, 3, 2]Output: The peak element is 10
     */

    fun findPeakElement(num: IntArray): Int {
        var low = 0
        var high = num.lastIndex

        while (low <= high) {
            val mid = low + (high - low) / 2

            // if mid > mid -1 and mid > mid +1 return mid bc it is peak,
            // edge case if mid =0 || mid = n-1 , we reach last ele

            if (mid == 0 || mid == num.lastIndex ||
                (num[mid - 1] < num[mid] && num[mid] > num[mid + 1])
            ) {
                return mid
            } else if (num[mid - 1] > num[mid]) {
                //move left
                high = mid - 1
            } else {
                // move righ
                low = mid + 1
            }
        }
        return -1
    }

    /**
     * Search in Rotated Sorted Array II
     * Here arr may contain duplicates
     * example: [2,5,6,0,0,1,2], target = 0
     *
     * Example: [3,5,6,3,3,3,3], target = 6
     * output: true
     */
    fun searchII(arr: IntArray, x: Int): Boolean {
        var low = 0
        var high = arr.lastIndex
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] == x -> return true
                // Handling Duplicates: When duplicates are present,
                // the usual checks to determine if the left or right half is sorted can fail.
                arr[low] == arr[mid] && arr[high] == arr[mid] -> {
                    low++
                    high--
                }
                // left half is sorted
                arr[low] <= arr[mid] -> {
                    if (arr[low] <= x && x >= arr[mid]) {
                        high = mid - 1
                    } else {
                        low = mid + 1
                    }
                }

                else -> {
                    if (arr[mid] >= x && x >= arr[high]) {
                        low = mid + 1
                    } else {
                        high = mid - 1
                    }
                }
            }
        }

        return false
    }

    /**
     * Search in a nearly sorted array in logarithmic time
     * Given a nearly sorted array such that each of the n elements may be misplaced by no more than one position from
     * the correct sorted order, search a given element in it efficiently.
     *
     * Report if the element is not present in the array.
     *
     * An element at index i in a correctly sorted order can be misplaced by the ± 1 position, i.e.,
     * it can be present at index i-1 or i or i+1 in the input array.
     *
     *  For example,
     *
     * Input: nums = [2, 1, 3, 5, 4, 7, 6, 8, 9]target = 5
     * Output: Element 5 found at index 3
     * Input: nums = [2, 1, 3, 5, 4, 7, 6, 8, 9]target = 10
     * Output: Element not found in the array
     *
     * Q1. -ve ?, duplicate?, sorted?, i-1, i , i+1 are probaliy shifted
     *  if empty() or  not found -1?
     *
     *  [2, 1, 3, 5, 4, 7, 6, 8, 9]
     *
     *  for m , check m-1 and m+1 if found return
     *  else,
     *  if(t>x) , low = m+2
     *  else high = m-2
     *
     */
    fun searchNearlySorted(num: IntArray, target: Int): Int {
        var low = 0
        var high = num.lastIndex

        while (low <= high) {
            val mid = low + (high - low) / 2

            //check mid is taget
            if (num[mid] == target) {
                return mid
            }

            //check if mid-1 is target
            if (mid - 1 >= low && num[mid - 1] == target) {
                return mid - 1
            }

            //check if mid+1 is target
            if (mid + 1 <= high && num[mid + 1] == target) {
                return mid + 1
            }

            // [low..mid] <target move right
            if (num[mid] < target) {
                low = mid + 2
            }

            //[mid..high] > target move left
            else if (num[mid] < target) {
                high = mid - 2
            }
        }

        return -1
    }

    /**
     * Find the number of 1’s in a sorted binary array
     * Given a sorted binary array, efficiently count the total number of 1’s in it.
     *
     * For example,
     *
     * Input:  nums[] = [0, 0, 0, 0, 1, 1, 1]
     * Output: The total number of 1’s present is 3
     *
     * Input:  nums[] = [0, 0, 1, 1, 1, 1, 1]
     * Output: The total number of 1’s present is 5
     */


    /**
     * Find Minimum in Rotated Sorted Array
     * Example: [3,4,5,1,2]
     * output: 1
     */
    fun findMin(arr: IntArray): Int {
        var low = 0
        var high = arr.lastIndex
        var res = Int.MAX_VALUE
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[low] <= arr[mid] -> {
                    res = minOf(res, arr[low])
                    low = mid + 1
                }

                else -> {
                    res = minOf(res, arr[mid])
                    high = mid - 1
                }
            }
        }
        return res
    }

    /**
     * Given an integer array arr of size N, sorted in ascending order (with distinct values).
     * Now the array is rotated between 1 to N times which is unknown.
     * Find how many times the array has been rotated.
     *
     * Example: [4,5,6,7,0,1,2]
     * Output: 4
     */
    @Revise
    fun findMinAndRotationCount(arr: IntArray): Pair<Int, Int> {
        val n = arr.size
        var low = 0
        var high = n - 1

        while (low <= high) {
            // If the array is already sorted, the minimum element is at index 0
            if (arr[low] <= arr[high]) {
                return Pair(arr[low], low)
            }

            val mid = low + (high - low) / 2
            val next = (mid + 1) % n
            val prev = (mid - 1 + n) % n

            // Check if mid is the minimum element
            if (arr[mid] <= arr[next] && arr[mid] <= arr[prev]) {
                return Pair(arr[mid], mid)
            } else if (arr[mid] <= arr[high]) {
                // Minimum element is in the left half
                high = mid - 1
            } else if (arr[mid] >= arr[low]) {
                // Minimum element is in the right half
                low = mid + 1
            }
        }

        return Pair(-1, -1) // Should not reach here in a valid rotated sorted array
    }


    /**
     * Find the missing term in a sequence in logarithmic time
     * Given a sequence of n numbers such that the difference between the consecutive terms is constant,
     * find the missing term in logarithmic time.
     * Assume that the first and last elements are always part of the input sequence and the missing number
     * lies between index 1 to n-1.
     *
     * For example,
     *
     * Input:  [5, 7, 9, 11, 15]Output: The missing term is 13
     * Input:  [1, 4, 7, 13, 16]Output: The missing term is 10
     */
    fun checkMissingElement(num: IntArray): Int {
        var low = 0
        var high = num.lastIndex
        val n = num.size

        val d = (num[high] - num[high]) / n

        while (low <= high) {
            val mid = (high - low) / 2

            //check if left to mid is missing
            if (mid > 0 && (num[mid] - num[mid - 1] != d)) {
                return num[mid] - d
            }
            // check if the right to mid is missing
            else if (mid < n - 1 && (num[mid] - num[mid + 1] != d)) {
                return num[mid] + d
            }


            if ((num[mid] - num[low]) != (mid - low) * d) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }

        return -1
    }


    /**
     * Find the frequency of each element in a sorted array containing duplicates
     * Given a sorted array containing duplicates, efficiently find each element’s frequency without traversing the whole array.
     *
     * For example,
     *
     * Input: [2, 2, 2, 4, 4, 4, 5, 5, 6, 8, 8, 9]Output: {2: 3, 4: 3, 5: 2, 6: 1, 8: 2, 9: 1}
     * Explanation: 2 and 4 occurs thrice5 and 8 occurs twice6 and 9 occurs once
     */
    fun findOccurranceOfElements(num: IntArray): Map<Int, Int> {
        val freqMap = mutableMapOf<Int, Int>()

        var low = 0
        var high = num.lastIndex

        while (low <= high) {

            if (num[low] == num[high]) {
                val count = high - low + 1
                freqMap[low] = freqMap.getOrDefault(low, 0) + count

                low = high + 1
                high = num.lastIndex
            } else {
                high = (low + high) / 2
            }
        }

        return freqMap
    }


    /**
     * Find square root of a number
     *
     *  from [1, x] find the number whose square is less than or equal to x
     *
     *  i*i<= x
     */
    fun findSquareRoot(x: Int): Int {
        if (x == 0 || x == 1) {
            return x
        }
        var low = 1
        var high = x
        var res = 0

        while (low <= high) {
            val mid = low + (high - low) / 2
            val square = mid.toLong() * mid.toLong() // Use Long to avoid overflow

            when {
                square == x.toLong() -> return mid
                square < x -> {
                    low = mid + 1
                    res = mid
                }

                else -> high = mid - 1
            }
        }
        return res
    }

    /**
     * Nth root of a number
     *
     * Given two numbers N and M, find the Nth root of M.
     *
     * The nth root of a number M is defined as a number X when raised to the power N equals M. If the 'nth root is not an integer, return -1.
     *
     * Example 1:
     * Input Format:
     *  N = 3, M = 27
     * Result:
     *  3
     * Explanation:
     *  The cube root of 27 is equal to 3.
     *
     * Example 2:
     * Input Format:
     *  N = 4, M = 69
     * Result:
     *  -1
     * Explanation:
     *  The 4th root of 69 does not exist. So, the answer is -1.
     *
     *
     *
     */
    fun nthRoot(n: Int, m: Int): Int {
        var low = 1
        var high = m

        while (low <= high) {
            val mid = (low + high) / 2

            // we can't use Math.pow as it returns double,
            val result = Math.pow(mid.toDouble(), n.toDouble()).toInt()
            when {
                result == m -> return mid
                result < m -> low = mid + 1
                else -> high = mid - 1
            }

        }
        return -1
    }

    /**
     * Division of two integers or decimal
     *
     * i * y >= x
     */
    fun division(x: Double, y: Double): Double {
        if (y == 0.0) return Double.MAX_VALUE

        var low = 0.0
        var high = x

        var sign = 1
        if (x * y < 0.0) {
            sign = -1
        }

        var res = 0.0

        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                mid * y == x -> return mid * sign
                mid * y < x -> {
                    res = mid
                    low = mid + 1
                }

                else -> {
                    high = mid - 1
                }
            }
        }

        return res * sign
    }

    /**
     * Find the odd occurring element in an array in logarithmic time
     * Given an integer array where every element appears an even number of times,
     * except one element which appears an odd number of times. If the identical elements appear
     * in pairs in the array and there cannot be more than two consecutive occurrences of an element,
     * find the odd occurring element in logarithmic time and constant space.
     *
     * For instance, both these arrays are invalid – {1, 2, 1} and {1, 1, 2, 2, 2, 3, 3}.
     * The first one doesn’t have identical elements appear in pairs, and the second one
     * contains three consecutive instances of an element.
     *
     * On the other hand, the array {2, 2, 3, 3, 2, 2, 4, 4, 3, 1, 1} is valid, and the odd occurring
     * element present in it is 3.
     */
    fun findOddOccurringElement(num: IntArray): Int {
        var low = 0
        var high = num.lastIndex
        var res = -1
        while (low <= high) {
            val mid = low + (high - low) / 2

            // [2, 2, 3, 3, 2, 2, 4, 4, 3, 1, 1]
            /**
             * l =0 , h = 11 , m = 5, odd , num[4]==num[5] , l = 6
             *
             * l= 6, h =11, m= 8 , even , num[8]!=num[9] , h = 7
             *
             * l = 6, h = 7, m = 6,
             */
            if (mid % 2 == 0) {
                // check right ele if same move to right else left
                if (num[mid] == num[mid + 1]) {
                    low = mid + 2
                } else {
                    res = mid
                    high = mid - 1
                }
            } else {
                if (num[mid] == num[mid - 1]) {
                    low = mid + 1
                } else {
                    res = mid
                    high = mid - 1
                }
            }
        }

        return res
    }

    /**
     * Find `k` closest elements to a given value in an array
     *
     * Input:  [10, 12, 15, 17, 18, 20, 25], k = 4, target = 16
     * Output: [12, 15, 17, 18] Input:  [2, 3, 4, 5, 6, 7], k = 3, target = 1Output: [2, 3, 4]
     */
    fun kClosetElement(num: IntArray, k: Int, target: Int): List<Int> {
        val insertionIndex = searchInsert(num, target)
        val n = num.lastIndex

        val list = mutableListOf<Int>()
        var left = insertionIndex
        var right = insertionIndex + 1

        repeat(k) {
            if (left < 0 || (right < n && Math.abs(num[left] - target) < Math.abs(num[right] - target))) {
                list.add(num[right])
                right--
            } else {
                list.add(num[left])
                left++
            }

        }

        return list
    }
}

fun main() {
    val bs = `1D`()
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10)
    println(bs.floor(arr, 6))
    println(bs.ceil(arr, 11))
}