package backtracking

class Subsets {

    /**
     * Given an integer array nums of unique elements, return all possible
     * subsets
     *  (the power set).
     *
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3]
     * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [[],[0]]
     */
    fun findSubsets(arr: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()

        fun backtrack(i: Int, list: MutableList<Int>) {
            if(i>=arr.size){
                res.add(list.toList())
                return
            }
            list.add(arr[i])
            backtrack(i+1, list)
            list.removeAt(list.size-1)
            backtrack(i+1, list)
        }

        backtrack(0, mutableListOf())
        return res
    }


    /**
     * Given an integer array nums that may contain duplicates, return all possible
     * subsets
     *  (the power set).
     *
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,2]
     * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [[],[0]]
     */
    fun findSubSets(nums: IntArray): List<List<Int>> {
       nums.sort()
        val res = mutableListOf<List<Int>>()

        fun backtrack(i: Int, list: MutableList<Int>) {
            res.add(list.toList())
            for (j in i until nums.size) {
                if (j > i && nums[j] == nums[j - 1]) continue
                list.add(nums[j])
                backtrack(j + 1, list)
                list.removeAt(list.size - 1)
            }
        }

        backtrack(0, mutableListOf())
        return res
    }

}