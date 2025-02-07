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
                arr[mid] <= x -> {
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
                arr[mid] >= x -> {
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
    fun findFirstOrLastOccurrence(arr: IntArray, target: Int): Int {
        var res = -1
        var low = 0
        var high = arr.lastIndex
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] == target -> {
                    res = mid
                    //high = mid - 1 // for first occurrence
                    low = mid + 1
                }

                arr[mid] < target -> low = mid + 1
                else -> high = mid - 1
            }
        }
        return res

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

        val first = lowerBound(arr, target) //lower bound 1st ele which is arr[i]>= target
        val last = upperBound(arr, target) // upper bound 1st ele which is arr[i] > target
        return last - first
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
     * Search in Rotated Sorted Array II
     * Here arr may contain duplicates
     * example: [2,5,6,0,0,1,2], target = 0
     *
     * Example: [3,5,6,3,3,3,3], target = 3
     * output: true
     */
    fun searchII(arr: IntArray, x: Int): Boolean {
        var low = 0
        var high = arr.lastIndex
        while (low <= high) {
            val mid = low + (high - low) / 2
            when {
                arr[mid] == x -> return true
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
     * Now the array is rotated between 1 to N times which is unknown. Find how many times the array has been rotated.
     *
     * Example: [4,5,6,7,0,1,2]
     * Output: 4
     */
    @Revise
    fun findRotationCount(arr: IntArray): Int {
        val n = arr.size
        var low = 0
        var high = n - 1

        while (low <= high) {
            // If the array is sorted, the rotation count is 0
            if (arr[low] <= arr[high]) {
                return low
            }

            val mid = low + (high - low) / 2
            val next = (mid + 1) % n
            val prev = (mid - 1 + n) % n

            // Check if mid is the minimum element
            if (arr[mid] <= arr[next] && arr[mid] <= arr[prev]) {
                return mid
            } else if (arr[mid] <= arr[high]) {
                // Minimum element is in the left half
                high = mid - 1
            } else if (arr[mid] >= arr[low]) {
                // Minimum element is in the right half
                low = mid + 1
            }
        }

        return -1 // Should not reach here in a valid rotated sorted array
    }


    /**
     * Find square root of a number
     *
     *  from [1, x] find the number whose square is less than or equal to x
     *
     *  i*i<= x
     */
    fun findSqaureRoot(x: Int): Int {
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
                square <= x -> {
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
}

fun main() {
    val bs = `1D`()
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10)
    println(bs.floor(arr, 6))
    println(bs.ceil(arr, 11))
}