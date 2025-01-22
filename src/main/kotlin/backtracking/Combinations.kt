package backtracking

class Combinations {

    /**
    Generate all combinations of k numbers from a set of integers ranging from 1 to n.
     * A combination is a selection of items from a collection, such that the order of selection does not matter.
     * In mathematical terms, you are asked to find all possible subsets of size k from the given range.
     *
     * For instance, if n=4 and k=2, the function should return combinations
     * like [1,2], [1,3], [1,4], [2,3], [2,4] and [3,4].
     * Importantly, combinations do not account for order; thus, [1,2] is considered the same as [2,1] and should not be listed as a separate combination.
     *
     * The answer can be returned in any sequence, meaning the combinations do not need to be sorted in any particular order.
     */
    fun combinations(n: Int, k: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()

        fun backtrack(i: Int, list: MutableList<Int>) {
            if (list.size == k) {
                res.add(list.toList())
                return
            }
            list.add(i)
            backtrack(i + 1, list)
            list.removeAt(list.size - 1)
            backtrack(i + 1, list)
        }

        backtrack(1, mutableListOf())
        return res
    }


    /**
     * Given an array of distinct integers candidates and a target integer target,
     * return a list of all unique combinations of candidates where the chosen numbers sum to target.
     * You may return the combinations in any order.
     *
     * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
     * frequency of at least one of the chosen numbers is different.
     *
     * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
     *
     *
     *
     * Example 1:
     *
     * Input: candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]]
     * Explanation:
     * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
     * 7 is a candidate, and 7 = 7.
     * These are the only two combinations.
     * Example 2:f
     *
     * Input: candidates = [2,3,5], target = 8
     * Output: [[2,2,2,2],[2,3,3],[3,5]]
     * Example 3:
     *
     * Input: candidates = [2], target = 1
     * Output: []
     */
    fun combinationSum(arr: IntArray, target: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()

        //using loop i to end
        fun backtrack(i: Int, list: MutableList<Int>, target: Int) {
            if (target == 0) {
                res.add(list.toList())
                return
            }

            if (i > arr.lastIndex || target < 0) return

            for (j in i..arr.lastIndex) {
                list.add(arr[j])
                backtrack(j, list, target - arr[j])
                list.removeAt(list.lastIndex)
            }
        }

        backtrack(0, mutableListOf(), target)
        return res
    }


    /**
     * Given a collection of candidate numbers (candidates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sum to target.
     *
     * Each number in candidates may only be used once in the combination.
     *
     * Note: The solution set must not contain duplicate combinations.
     *
     *
     *
     * Example 1:
     *
     * Input: candidates = [10,1,2,7,6,1,5], target = 8
     * Output:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     * Example 2:
     *
     * Input: candidates = [2,5,2,1,2], target = 5
     * Output:
     * [
     * [1,2,2],
     * [5]
     * ]
     *
     */
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        // TC O(2^n)
        val result = mutableListOf<List<Int>>()
        candidates.sort()

        fun backtrack(i: Int, list: MutableList<Int>, target: Int) {
            if (target == 0) {
                result.add(list.toList())
                return
            }

            if (i > candidates.lastIndex || target < 0) return

            for (j in i..candidates.lastIndex) {
                if (j > i && candidates[j] == candidates[j - 1]) continue
                list.add(candidates[j])
                backtrack(j + 1, list, target - candidates[j])
                list.removeAt(list.lastIndex)
            }
        }

        backtrack(0, mutableListOf(), target)

        return result
    }


}