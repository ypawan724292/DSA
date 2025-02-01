package dsa.backtracking


//https://docs.google.com/document/d/1JcGu-mAaycYwMY_bX2Uya1td6hZwmSznH3_eLUHATpc/edit#heading=h.qlh7ecjgd1b
class Basics {


    /**
     * What is Recursion?
     * function calling itself until base condition is met
     *
     * What is Stack overflow?
     * When the stack is full, we try to push more elements into it
     *
     * Recursive tree :
     * It is a tree that represents the recursive calls of a function
     *
     *
     * What is Backtracking?
     * When we try to solve a problem by trying all the possibilities without cosidering the subproblem
     *
     * In summary, backtracking is a specialized form of recursion used for solving
     * constraint-based problems by exploring all possible solutions
     * and backtracking when necessary
     */


    fun printName(count: Int, n: Int) {
        /*
         Time complexity: O(n)
         Space complexity: O(n)
         */
        if (count > n) return
        print("Pavan")
        printName(count + 1, n)
    }

    /**
     * This method prints 1 to n using recursion
     */
    fun printNumbers(i: Int, n: Int) {
        if (i > n) return
        print(i)
        printNumbers(i + 1, n)
    }


    /**
     * this method prints n to 1 using recursion
     */
    fun printNumberReverse(i: Int, n: Int) {
        if (i < 1) return
        print(i)
        printNumberReverse(i - 1, n)
    }


    /**
     * This method prints 1 to n using backtracking
     */
    fun printNumberBacktracking(i: Int, n: Int) {
        if (i < 1) return
        printNumberBacktracking(i - 1, n)
        print(i)
    }

    /**
     * this method prints n to 1
     */
    fun printNumberReverseByBacktracking(i: Int, n: Int) {
        if (i > n) return
        printNumberReverseByBacktracking(i + 1, n)
        print(i)
    }


    /**
     * This method takes parameters and prints the sum of numbers from 1 to n
     */
    fun paremetrizedSum(i: Int, sum: Int) {
        if (i < i) {
            println(sum)
            return
        }
        paremetrizedSum(i - 1, sum + i)
    }

    /**
     * This funtion helps calculate sum and returns the sum
     */
    fun funtionalSum(n: Int): Int {
        if (n == 0) return 0
        return n + funtionalSum(n - 1)
    }

    fun factorail(n: Int): Int {
        if (n == 0) return 1
        return n * factorail(n - 1)
    }


    val arr = intArrayOf(1, 2, 3, 4, 5)

    /**
     * using two pointers
     */
    fun reverseArray(left: Int, right: Int) {
        if (left >= right) return

        val temp = arr[left]
        arr[left] = arr[right]
        arr[right] = temp
        reverseArray(left + 1, right - 1)
    }

    /**
     * using one pointer
     */
    fun reverse(i: Int) {
        if (i >= arr.size / 2) return
        val temp = arr[i]
        arr[i] = arr[arr.size - i - 1]
        reverse(i + 1)
    }

    /**
     * Time complexity: O(n/2)
     * Space complexity: O(n/2)
     */
    fun checkPalindrome(s: String, i: Int): Boolean {
        if (i >= s.length / 2) return true
        if (s[i] != s[s.length - i - 1]) return false
        return checkPalindrome(s, i + 1)
    }

    /**
     * fun multiple recursive calls
     * Time complexity: O(2^n)
     *
     *
     *      Recursion tree:
     *              f(4)
     *              /  \
     *            f(3) f(2)
     *           /  \   /  \
     *        f(2) f(1) f(1) f(0)
     *       /  \
     *    f(1) f(0)
     */
    fun fibonacci(n: Int): Int {
        if (n <= 1) return n
        return fibonacci(n - 1) + fibonacci(n - 2)
    }


    /**
     * Understand the Problem's Requirements:
     *
     *   1. Is the problem asking for all subsets, combinations, substring/subsequence or permutations?
     *              "Pick or Not Pick" : Likely { Not always }
     *                      Subsets : When generating all subsets, the core idea is that for each element,
     *                                  you decide whether to include it in the current subset or not.
     *                                  This naturally leads to a binary choice at each step (pick or do not pick),
     *                                  which is why the "Pick or Not Pick" approach is commonly used.
     *              "Loop from i to End" :  Likely {Not always}
     *                      Combinations: You're selecting elements in a specific order, typically without repetition.
     *                                  You loop through the remaining elements to add them to the current combination,
     *                                  which is why the "Loop from i to End" approach fits well.
     *                      Permutations: You need to consider all possible orders of the elements, which often involves
     *                                  swapping elements as you loop through them, hence using the "Loop from i to End" approach.
     *                      Substrings/Subsequences: You often explore starting points and then expand the substring or
     *                                      subsequence by looping through the elements from that point onward.
     *
     *   2. Are elements unique or can they be repeated?
     *              Presence of duplicates might lean towards "Loop from i to End" with conditions to handle duplicates.
     *
     *   3. Analyze Constraints:
     *              Is the problem size manageable with 2^n Time complexcity ?
     *                   If yes, "Pick or Not Pick" can be suitable.
     *   4. Consider the Nature of Choices:
     *              Binary Choices: "Pick or Not Pick".
     *              Multiple Candidates at Each Step: "Loop from i to End".
     *
     * Look for Similar Leetcode Problems:
     *
     * Often, problems are variants of classic ones. Identifying similarities can guide the approach.
     */

}