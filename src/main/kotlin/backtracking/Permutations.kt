package backtracking

import java.util.*
import java.util.Collections.swap

class Permutations {

    /**
     *Tree diagram for the permutations of [1,2,3]
     *Root: {1, 2, 3}
     * 1 as the first element: {2, 3}
     *      2 as the next element: {3} → 1, 2, 3
     *      3 as the next element: {2} → 1, 3, 2
     * 2 as the first element: {1, 3}
     *      1 as the next element: {3} → 2, 1, 3
     *      3 as the next element: {1} → 2, 3, 1
     * 3 as the first element: {1, 2}
     *      1 as the next element: {2} → 3, 1, 2
     *      2 as the next element: {1} → 3, 2, 1
     */
    fun findPermutations(n: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()

        fun backtrack(list: MutableSet<Int>) {
            if (list.size == n) {
                res.add(list.toList())
                return
            }
            for (i in 1..n) {
                if (i !in list) continue
                list.add(i)
                backtrack(list)
                list.remove(i)
            }
        }
        backtrack(mutableSetOf())
        return res
    }

    /**
     * Using swap
     */
    fun findPermutationsUsingSwap(n: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()

        fun swap(list: MutableList<Int>, i: Int, j: Int) {
            val temp = list.elementAt(i)
            list.remove(list.elementAt(i))
            list.add(j, temp)
        }

        fun backtrack(list: MutableList<Int>) {
            if (list.size == n) {
                res.add(list.toList())
                return
            }
            for (i in 1..n) {
                swap(list, i, list.size)
                backtrack(list)
                swap(list, i, list.size)
            }
        }
        backtrack(mutableListOf())
        return res
    }

    /**
     * Given a collection of numbers, nums, that might contain duplicates,
     * return all possible unique permutations in any order.
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,2]
     * Output:
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     * Example 2:
     *
     * Input: nums = [1,2,3]
     * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    fun findUniquePermutations(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        nums.sort()
        fun backtrack(list: MutableList<Int>, used: BooleanArray) {
            if (list.size == nums.size) {
                res.add(list.toList())
                return
            }
            for (i in nums.indices) {
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue
                used[i] = true
                list.add(nums[i])
                backtrack(list, used)
                list.removeAt(list.size - 1)
                used[i] = false
            }
        }
        backtrack(mutableListOf(), BooleanArray(nums.size))
        return res
    }


}