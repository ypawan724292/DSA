package dsa.backtracking

class SubSequence {

    val arr = intArrayOf(1, 2, 3)
    val n = arr.size
    val k = 2

    /**
     * This method prints all the subsets of the given array
     */
    fun findAllSubSequence(i: Int, list: MutableList<Int>) {
        if (i >= n) {
            println(list)
            return
        }
        list.add(arr[i])
        findAllSubSequence(i + 1, list)
        list.removeAt(list.size - 1)
        findAllSubSequence(i + 1, list)
    }


    fun findAllSubSequenceWithSumk(i: Int, list: MutableList<Int>, sum: Int) {
        if (i >= n) {
            if (sum == k) {
                println(list)
            }
            return
        }
        list.add(arr[i])
        findAllSubSequenceWithSumk(i + 1, list, sum + arr[i])
        list.removeAt(list.size - 1)
        findAllSubSequenceWithSumk(i + 1, list, sum)
    }

    fun findOneSubSequenceWithSumk(i: Int, list: MutableList<Int>,  sum: Int): Boolean {
        if (i >= n) {
            if (sum == k) {
                println(list)
                return true
            }
            return false
        }
        list.add(arr[i])
        if (findOneSubSequenceWithSumk(i + 1, list, sum + arr[i])) {
            return true
        }
        list.removeAt(list.size - 1)
        if (findOneSubSequenceWithSumk(i + 1, list, sum)) return true
        return false
    }


    /**
     * Whenever there is count problem refer this
     *
     * return 1 if condition met else 0
     *
     * and add all f()
     */
    fun countOfSubSeqWithSumk(i: Int, sum: Int): Int {
        if (i >= n) return if (sum == k) 1 else 0

        val l = countOfSubSeqWithSumk(i + 1, sum + arr[i])
        val r = countOfSubSeqWithSumk(i + 1, sum)

        return l + r
    }

}