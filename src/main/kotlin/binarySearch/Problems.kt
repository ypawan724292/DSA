package binarySearch

import annotations.Revise


class Problems {


    /**
     * Smallest Divisor
     *
     * Given an array of integers nums and an integer threshold,
     * we will choose a positive integer divisor and divide all the array by it and sum the result of the division.
     *
     * Return the smallest divisor such that the result mentioned above is less than or equal to threshold.
     *
     * Example: [1,2,5,9], threshold = 6
     *
     * Output: 5
     *
     * Explanation:
     * We can get a sum to 17 (1+2+5+9) if the divisor is 1.
     * If the divisor is 4 we can get a sum to 7 (1+1+2+3)
     * and if the divisor is 5 the sum will be 5 (1+1+1+2).
     *
     *
     */
    fun smallestDivisor(nums: IntArray, threshold: Int): Int {
        // mid >= threshold
        var low = 1
        var high = nums.max()
        var res = 0
        fun isPossible(mid: Int): Boolean {
            var sum = 0
            for (it in nums) {
                var value = it / mid
                if (it % mid != 0) {
                    value++
                }
                sum += value
            }
            return sum <= threshold
        }
        while (low <= high) {
            val mid = (low + high) / 2
            if (isPossible(mid)) {
                res = mid
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return res
    }


    /**
     * Koko Eating Bananas :
     * Koko loves to eat bananas. There are N piles of bananas, the i-th pile has piles[i] bananas.
     * The guards have gone and will come back in H hours.
     * Koko can decide her bananas-per-hour eating speed of K. Each hour, she chooses some pile of bananas and eats K bananas from that pile.
     * If the pile has less than K bananas, she eats all of them instead and will not eat any more bananas during this hour.
     * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
     * Return the minimum integer K such that she can eat all the bananas within H hours.
     *
     * Example 1:
     * Input: piles = [3,6,7,11], H = 8
     * Output: 4
     *
     */
    fun findMinEatingSpeed(piles: IntArray, h: Int): Int {
        // takenKToEat < h
        var low = 1
        var high = piles.max()
        var res = 0

        fun isPossible(mid: Int): Boolean {
            var t = 0
            for (k in piles) {
                val mod = k % mid
                if (mod == 0) t += k / mid
                if (mod != 0) t += k / mid + 1
            }
            return t <= h
        }

        while (low <= high) {
            val mid = (low + high) / 2
            if (isPossible(mid)) {
                res = mid
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return res
    }

    /**
     * You are given an integer array bloomDay, an integer m and an integer k.
     *
     * You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
     *
     * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
     *
     * Return the minimum number of days you need to wait to be able to make m bouquets from the garden.
     * If it is impossible to make m bouquets return -1.
     *
     *
     *
     * Example 1:
     *
     * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
     * Output: 3
     * Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
     * We need 3 bouquets each should contain 1 flower.
     * After day 1: [x, _, _, _, _]   // we can only make one bouquet.
     * After day 2: [x, _, _, _, x]   // we can only make two bouquets.
     * After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
     * Example 2:
     *
     * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
     * Output: -1
     * Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
     * Example 3:
     *
     * Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
     * Output: 12
     * Explanation: We need 2 bouquets each should have 3 flowers.
     * Here is the garden after the 7 and 12 days:
     * After day 7: [x, x, x, x, _, x, x]
     * We can make one bouquet of the first three flowers that bloomed.
     * We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
     * After day 12: [x, x, x, x, x, x, x]
     * It is obvious that we can make two bouquets in different ways.
     *
     */
    fun minDays(bloomDay: IntArray, m: Int, k: Int): Int {
        // max days to collect is max value in the bloomday if m*k < n
        // base condition  n < m*k return -1
        // mid, count find no of flowers can bloom after min days if(bloomDay < mid) and adjcent
        // if (count <= m*k) low = mid +1 else high= mid-1
        //

        var low = bloomDay.min()
        var high = bloomDay.max()
        var res = -1

        fun canMake(t: Int): Boolean {
            var count = 0
            var noB = 0
            for (day in bloomDay) {
                if (day <= t) {
                    count++
                } else {
                    noB += count / k
                    count = 0
                }
            }

            noB += count / k
            return noB >= m
        }

        while (low <= high) {
            val mid = (low + high) / 2

            if (canMake(mid)) {
                res = mid
                high = mid - 1
            } else {
                low = mid + 1
            }
        }


        return res

    }

    /**
     * A conveyor belt has packages that must be shipped from one port to another within days days.
     *
     * The ith package on the conveyor belt has a weight of weights[i].
     * Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
     * We may not load more weight than the maximum weight capacity of the ship.
     *
     * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within given days.
     *
     *
     *
     * Example 1:
     *
     * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
     * Output: 15
     * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
     * 1st day: 1, 2, 3, 4, 5
     * 2nd day: 6, 7
     * 3rd day: 8
     * 4th day: 9
     * 5th day: 10
     *
     * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
     * Example 2:
     *
     * Input: weights = [3,2,2,4,1,4], days = 3
     * Output: 6
     * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
     * 1st day: 3, 2
     * 2nd day: 2, 4
     * 3rd day: 1, 4
     * Example 3:
     *
     * Input: weights = [1,2,3,1,1], days = 4
     * Output: 3
     * Explanation:
     * 1st day: 1
     * 2nd day: 2
     * 3rd day: 3
     * 4th day: 1, 1
     */
    fun shipWithinDays(weights: IntArray, days: Int): Int {

        var low = weights.max()
        var high = weights.sum()

        var res = 0

        fun isPossible(mid: Int): Boolean {
            var count = 1
            var sum = 0
            for (i in weights) {
                sum += i
                if (sum > mid) {
                    count++
                    sum = i
                }
            }
            return count <= days
        }

        while (low <= high) {
            val mid = (low + high) / 2

            if (isPossible(mid)) {
                res = mid
                high = mid - 1
            } else {
                low = mid + 1
            }

        }
        return res
    }

    /**
     * You are given an array with unique elements of stalls[],
     * which denote the position of a stall.
     * You are also given an integer k which denotes the number of aggressive cows.
     * Your task is to assign stalls to k cows such that the minimum distance between any two of them is the maximum possible.
     *
     * Examples :
     *
     * Input: stalls[] = [1, 2, 4, 8, 9], k = 3
     * Output: 3
     * Explanation: The first cow can be placed at stalls[0],
     * the second cow can be placed at stalls[2] and
     * the third cow can be placed at stalls[3].
     * The minimum distance between cows, in this case, is 3, which also is the largest among all possible ways.
     */
    fun aggressiveCows(A: IntArray, B: Int): Int {
        //The problem's objective is to maximize the minimum distance between the cows.
        // 1st the array
        //Placing the first cow at the smallest available stall ensures the largest possible room for
        // placing subsequent cows while adhering to the distance constraint.
        //Starting at the first stall avoids unnecessary gaps at the beginning of the array, which could lead to suboptimal solutions.
        //binary search between 1 and max distance between stalls
        //check if it is possible to place the cows in B stalls
        //if possible then return the mid value
        //else update the low and high value

        A.sort()
        var low = 1
        var high = A[A.size - 1] - A[0]
        var res = 0

        fun isPossible(mid: Int): Boolean {
            var count = 1
            var prev = A[0]
            for (i in 1 until A.size) {
                if (A[i] - prev >= mid) {
                    count++
                    prev = A[i]
                }
                if(count>B)
                    return false
            }
            return count >= B
        }

        while (low <= high) {
            val mid = (low + high) / 2

            if (isPossible(mid)) {
                res = mid
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return res
    }

    /**
     * Book Allocation Problem
     *
     * Given an array arr[] and an integer k,
     * where arr[i] denotes the number of pages of a book and k denotes total number of students.
     * All the books need to be allocated to k students in contiguous manner, with each student getting at least one book.
     *
     * The task is to minimize the maximum number of pages allocated to a student.
     * If it is not possible to allocate books to all students, return -1.
     *
     * Examples:
     *
     * Input: arr[] = [12, 34, 67, 90], k = 2
     * Output: 113
     * Explanation: Books can be distributed in following ways:
     *
     *
     * [12] and [34, 67, 90] – The maximum pages assigned to a student is  34 + 67 + 90 = 191.
     * [12, 34] and [67, 90] – The maximum pages assigned to a student is 67 + 90 = 157.
     * [12, 34, 67] and [90] – The maximum pages assigned to a student is 12 + 34 + 67 = 113.
     * The third combination has the minimum pages assigned to a student which is 113.
     *
     *
     * Input: arr[] = [15, 17, 20], k = 5
     * Output: -1
     * Explanation: Since there are more students than total books, it’s impossible to allocate a book to each student.
     *
     *
     * Input: arr[] = [22, 23, 67], k = 1
     * Output: 112
     * Explanation: Since there is only 1 student, all books are assigned to that student.
     * So, maximum pages assigned to a student is 22 + 23 + 67 = 112.
     *
     */
    fun books(A: IntArray, B: Int): Int {
        //find the max and sum of the array
        //binary search between max and sum
        //check if it is possible to allocate the books to B students
        //if possible then return the mid value
        //else update the low and high value

        var low = A.max()
        var high = A.sum()
        var res = 0

        fun isPossible(mid: Int): Boolean {
            var count = 1
            var sum = 0
            for (i in A) {
                sum += i
                if (sum > mid) {
                    count++
                    sum = i
                }
                if(count>B)
                    return false
            }
            return count == B
        }

        while (low <= high) {
            val mid = (low + high) / 2

            if (isPossible(mid)) {
                res = mid
                high = mid - 1
            } else {
                low = mid + 1
            }
        }

        return res
    }

    /**
     * Magnetic Force Between Two Balls
     *  In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket.
     *  Rick has n empty baskets, the ith basket is at position[i],
     *  Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
     *
     * Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
     *
     * Given the integer array position and the integer m. Return the required force.
     *
     *
     * Input: position = [1,2,3,4,7], m = 3
     * Output: 3
     * Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
     * Example 2:
     *
     * Input: position = [5,4,3,2,1,1000000000], m = 2
     * Output: 999999999
     * Explanation: We can use baskets 1 and 1000000000.
     */
    fun maxDistance(position: IntArray, m: Int): Int {
        //sort the array
        //find the min and max distance
        //binary search between min and max distance
        //check if it is possible to distribute the balls in m baskets
        //if possible then return the mid value
        //else update the low and high value

        position.sort()
        var low = 1
        var high = position[position.size - 1] - position[0]
        var res = 0

        fun isPossible(mid: Int): Boolean {
            var count = 1
            var prev = position[0]
            for (i in 1 until position.size) {
                if (position[i] - prev >= mid) {
                    count++
                    prev = position[i]
                }
            }
            return count >= m
        }

        while (low <= high) {
            val mid = (low + high) / 2

            if (isPossible(mid)) {
                res = mid
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return res
    }


    /**
     * Given an integer array nums and an integer k,
     * split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
     *
     * Return the minimized largest sum of the split.
     *
     * A subarray is a contiguous part of the array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [7,2,5,10,8], k = 2
     * Output: 18
     * Explanation: There are four ways to split nums into two subarrays.
     * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
     * Example 2:
     *
     * Input: nums = [1,2,3,4,5], k = 2
     * Output: 9
     * Explanation: There are four ways to split nums into two subarrays.
     * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
     *
     */
    fun splitArray(nums: IntArray, k: Int): Int {
        //find the max and sum of the array
        //binary search between max and sum
        //check if it is possible to split the array in k subarrays
        //if possible then return the mid value
        //else update the low and high value

        var low = nums.max()
        var high = nums.sum()
        var res = 0

        fun isPossible(mid: Int): Boolean {
            var count = 1
            var sum = 0
            for (i in nums) {
                sum += i
                if (sum > mid) {
                    count++
                    sum = i
                }
            }
            return count <= k
        }

        while (low <= high) {
            val mid = (low + high) / 2

            if (isPossible(mid)) {
                res = mid
                high = mid - 1
            } else {
                low = mid + 1
            }
        }

        return res
    }

    /**
     * Problem statement
     * Given an array/list of length ‘n’, where the array/list represents the boards and each element of the given
     * array/list represents the length of each board. Some ‘k’ numbers of painters are available to paint these boards.
     * Consider that each unit of a board takes 1 unit of time to paint.
     *
     *
     *
     * You are supposed to return the area of the minimum time to get this job done of painting all the
     * ‘n’ boards under a constraint that any painter will only paint the continuous sections of boards.
     *
     *
     *
     * Example :
     * Input: arr = [2, 1, 5, 6, 2, 3], k = 2
     *
     * Output: 11
     *
     * Explanation:
     * First painter can paint boards 1 to 3 in 8 units of time and the second painter can paint boards 4-6 in 11 units of time.
     * Thus both painters will paint all the boards in max(8,11) = 11 units of time.
     * It can be shown that all the boards can't be painted in less than 11 units of time.
     */
    fun boardPainting(arr: IntArray, k: Int): Int {
        //find the max and sum of the array
        //binary search between max and sum
        //check if it is possible to paint the boards in k painters
        //if possible then return the mid value
        //else update the low and high value

        var low = arr.max()
        var high = arr.sum()
        var res = 0

        fun isPossible(mid: Int): Boolean {
            var count = 1
            var sum = 0
            for (i in arr) {
                sum += i
                if (sum > mid) {
                    count++
                    sum = i
                }
            }
            return count <= k
        }

        while (low <= high) {
            val mid = (low + high) / 2

            if (isPossible(mid)) {
                res = mid
                high = mid - 1
            } else {
                low = mid + 1
            }
        }

        return res
    }

    /**
     * Problem statement
     * You are given a sorted array ‘arr’ of length ‘n’, which contains positive integer positions of ‘n’ gas stations on the X-axis.
     *
     *
     *
     * You are also given an integer ‘k’.
     *
     *
     *
     * You have to place 'k' new gas stations on the X-axis.
     *
     *
     *
     * You can place them anywhere on the non-negative side of the X-axis, even on non-integer positions.
     *
     *
     *
     * Let 'dist' be the maximum value of the distance between adjacent gas stations after adding 'k' new gas stations.
     *
     * Find the minimum value of dist.
     *
     *
     *
     * Example:
     * Input: ‘n' = 7 , ‘k’=6, ‘arr’ = {1,2,3,4,5,6,7}.
     *
     * Answer: 0.5
     *
     * Explanation:
     * We can place 6 gas stations at 1.5, 2.5, 3.5, 4.5, 5.5, 6.5.
     *
     * Thus the value of 'dist' will be 0.5. It can be shown that we can't get a lower value of 'dist' by placing 6 gas stations.
     *
     *
     * Note:
     * You will only see 1 or 0 in the output where:
     *   1 represents your answer is correct.
     *   0 represents your answer is wrong.
     * Answers within 10^-6 of the actual answer will be accepted.
     */
    @Revise
    fun gasStations(n: Int, k: Int, arr: IntArray): Double {
        //find the max and min distance between the gas stations
        //binary search between min and max distance
        //check if it is possible to place the gas stations in k positions
        //if possible then return the mid value
        //else update the low and high value

        var low = 0.0
        var high = 1e9
        var res = 0.0

        fun isPossible(mid: Double): Boolean {
            var count = 0
            for (i in 1 until n) {
                count += Math.ceil((arr[i] - arr[i - 1]) / mid).toInt() - 1
            }
            return count <= k
        }

        while (low <= high) {
            val mid = (low + high) / 2

            if (isPossible(mid)) {
                res = mid
                high = mid - 1
            } else {
                low = mid + 1
            }
        }

        return res
    }


    /**
     * Problem statement
     * To solve the problem of finding the minimum radius required to water all trees
     * given their positions and the positions of sprinklers,
     * you can use a binary search approach.
     * Here's a step-by-step explanation and the corresponding code:
     *
     */
    fun minRadius(trees: IntArray, sprinklers: IntArray): Int {
        trees.sort()
        sprinklers.sort()

        fun canWaterAllTrees(radius: Int): Boolean {
            var j = 0
            for (tree in trees) {
                // Find the leftmost sprinkler that can water the tree
                while (j < sprinklers.size && sprinklers[j] + radius < tree) {
                    j++
                }
                if (j == sprinklers.size || sprinklers[j] - radius > tree) {
                    return false
                }
            }
            return true
        }

        var low = 1
        var high = Math.max(trees.last() - sprinklers.first(), sprinklers.last() - trees.first())
        var res = 0
        while (low <= high) {
            val mid = (low + high) / 2
            if (canWaterAllTrees(mid)) {
                res = mid
                high = mid - 1
            } else {
                low = mid + 1
            }
        }

        return res
    }


    /**
     * Median of Two Sorted Arrays
     */
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        //naive approach
        //merge two arrays and find the median
        val m = nums1.size
        val n = nums2.size
        val res = IntArray(m + n)
        var i = 0
        var j = 0
        var k = 0
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                res[k++] = nums1[i++]
            } else {
                res[k++] = nums2[j++]
            }
        }
        while (i < m) {
            res[k++] = nums1[i++]
        }
        while (j < n) {
            res[k++] = nums2[j++]
        }

//        return if ((m + n) % 2 == 0) {
//            (res[(m + n) / 2 - 1] + res[(m + n) / 2]) / 2.0
//        } else {
//            res[(m + n) / 2].toDouble()
//        }


        //using binary search
        /*
        Intution
           1. Median divides the merged array into two equal halves ie. (n+m)/2 elements in each half
           2. After dividing we have x elements of arr1 and total/2 - x elements of arr2 in the first half
           3. we need try all the partitions from 0 to arr1.size

           Example
              arr1 = [1, 3, 8, 9, 15] , arr2 = [7, 11, 18, 19, 21, 25]

              we need to find the partition such the left  and right half of the partition has equal number of elements,
              consiting of x elements from arr1 and total/2-x elements from arr2

              If we partition at x = 2, y = total/2 - x = 11/2 - 2 = 3

                        [1,3]   [8,9,15]
                     [7,11,18]  [19,21,25]


           if we partition at x = 1, y = total/2 - x = 11/2 - 1 = 4

                        [1]   [3,8,9,15]
                     [7,11,18,19]  [21,25]


         */

        // Ensure nums1 is the smaller array to optimize the binary search
        if (m > n) return findMedianSortedArrays(nums2, nums1)

        var low = 0
        var high = m

        while (low <= high) {
            // Partition nums1 and nums2
            val mid1 = (low + high) / 2
            val mid2 = (m + n + 1) / 2 - mid1

            // Edge cases for partitioning
            val l1 = if (mid1 == 0) Int.MIN_VALUE else nums1[mid1 - 1]
            val l2 = if (mid2 == 0) Int.MIN_VALUE else nums2[mid2 - 1]
            val r1 = if (mid1 == m) Int.MAX_VALUE else nums1[mid1]
            val r2 = if (mid2 == n) Int.MAX_VALUE else nums2[mid2]

            // Check if we have found the correct partition
            if (l1 <= r2 && l2 <= r1) {
                return if ((m + n) % 2 == 0) {
                    (maxOf(l1, l2) + minOf(r1, r2)) / 2.0
                } else {
                    maxOf(l1, l2).toDouble()
                }
            } else if (l1 > r2) {
                high = mid1 - 1
            } else {
                low = mid1 + 1
            }
        }

        // This return statement should never be reached
        return 0.0
    }

    /**
     * Kth element of two sorted arrays
     */
    fun kthElement(nums1: IntArray, nums2: IntArray, k: Int): Int {
        val m = nums1.size
        val n = nums2.size

        if (m > n) return kthElement(nums2, nums1, k)

        var low = maxOf(0, k - n)
        var high = minOf(k, m)

        while (low <= high) {
            val mid1 = (low + high) / 2
            val mid2 = k - mid1

            val l1 = if (mid1 == 0) Int.MIN_VALUE else nums1[mid1 - 1]
            val l2 = if (mid2 == 0) Int.MIN_VALUE else nums2[mid2 - 1]
            val r1 = if (mid1 == m) Int.MAX_VALUE else nums1[mid1]
            val r2 = if (mid2 == n) Int.MAX_VALUE else nums2[mid2]

            // Check if we have found the correct partition
            if (l1 <= r2 && l2 <= r1) {
                return maxOf(l1, l2)
            } else if (l1 > r2) {
                high = mid1 - 1
            } else {
                low = mid1 + 1
            }
        }

        return 0
    }
}