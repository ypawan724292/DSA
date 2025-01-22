package greedy

class Basics {

    /**
     * 455. Assign Cookies
     * Solved
     * Easy
     * Topics
     * Companies
     * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
     *
     * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
     *
     *
     *
     * Example 1:
     *
     * Input: g = [1,2,3], s = [1,1]
     * Output: 1
     * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
     * And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
     * You need to output 1.
     * Example 2:
     *
     * Input: g = [1,2], s = [1,2,3]
     * Output: 2
     * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
     * You have 3 cookies and their sizes are big enough to gratify all of the children,
     * You need to output 2.
     *
     */
    fun findContentChildren(g: IntArray, s: IntArray): Int {
        g.sort()
        s.sort()
        var i = 0
        var j = 0
        while (i < g.size && j < s.size) {
            if (g[i] <= s[j]) {
                i++
            }
            j++
        }
        return i
    }

    /**
     * Fractional Knapsack Problem : Greedy Approach
     *
     * Problem Statement: The weight of N items and their corresponding values are given.
     * We have to put these items in a knapsack of weight W such that the total value obtained is maximized.
     *
     * Note: We can either take the item as a whole or break it into smaller units.
     *
     * Example:
     *
     * Input: N = 3, W = 50, values[] = {100,60,120}, weight[] = {20,10,30}.
     *
     * Output: 240.00
     *
     * Explanation: The first and second items  are taken as a whole  while only 20 units of the third item is taken.
     * Total value = 100 + 60 + 80 = 240.00
     */
    fun fractionalKnapsackProblem(N: Int, W: Int, values: IntArray, weight: IntArray): Double {
        val items = Array(N) { Pair(values[it].toDouble(), weight[it].toDouble()) }
        items.sortByDescending { it.first / it.second }
        var ans = 0.0
        var currentWeight = 0.0
        for (item in items) {
            if (currentWeight + item.second <= W) {
                ans += item.first
                currentWeight += item.second
            } else {
                val remainingWeight = W - currentWeight
                ans += item.first * (remainingWeight / item.second)
                break
            }
        }
        return ans
    }

    /**
     * Problem Statement:
     * Given a value V, if we want to make a change for V Rs, and
     * we have an infinite supply of each of the denominations in Indian currency, i.e.,
     * we have an infinite supply of { 1, 2, 5, 10, 20, 50, 100, 500, 1000} valued coins/notes,
     * what is the minimum number of coins and/or notes needed to make the change.
     *
     * Examples:
     *
     * Example 1:
     *
     * Input: V = 70
     *
     * Output: 2
     *
     * Explaination: We need a 50 Rs note and a 20 Rs note.
     *
     * Example 2:
     *
     * Input: V = 121
     *
     * Output: 3
     *
     * Explaination: We need a 100 Rs note, a 20 Rs note and a 1 Rs coin.
     */
    fun minCoins(V: Int): Int {
        val coins = intArrayOf(1, 2, 5, 10, 20, 50, 100, 500, 1000)
        var ans = 0
        var i = coins.size - 1
        var rem = V
        while (rem > 0) {
            ans += rem / coins[i]
            rem %= coins[i]
            i--
        }
        return ans
    }

    /**
     * Problem Statement:
     *
     * Lemonade Change
     *
     * Problem Statement: Given an array representing a queue of customers and the value of bills they hold, determine if it is possible to provide correct change to each customer. Customers can only pay with 5$, 10$ or 20$ bills and we initially do not have any change at hand. Return true,
     * if it is possible to provide correct change for each customer otherwise return false.
     *
     * Example 1:
     * Input: bills = [5, 5, 5, 10, 20]
     *
     * Output: True
     * Explanation:
     *
     * Initially we have 0 change and the queue of customers is [5, 5, 5, 10, 20].
     * First Customer pays 5$, no change required.
     * Second Customer pays 5$, no change required.
     * Third Customer pays 5$, no change required.
     * The Fourth Customer pays 10$, out of the three 5$ bills we have, we pay a 5$ bill and accept the 10$ bill.
     * Fifth Customer pays 20$, out of the two 5$ bills and one 10$ bill we have, we pay 15$ in change and have one 5$ bill left.
     * Hence, it is possible to provide change to all customers.
     */
    fun lemonadeChange(bills: IntArray): Boolean {
        var five = 0
        var ten = 0
        for (bill in bills) {
            when (bill) {
                5 -> five++
                10 -> {
                    if (five == 0) return false
                    five--
                    ten++
                }

                20 -> {
                    if (ten > 0 && five > 0) {
                        ten--
                        five--
                    } else if (five >= 3) {
                        five -= 3
                    } else {
                        return false
                    }
                }
            }
        }
        return true

    }

    /**
     * 678. Valid Parenthesis String
     * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
     *
     * The following rules define a valid string:
     *
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
     *
     *
     * Example 1:
     *
     * Input: s = "()"
     * Output: true
     * Example 2:
     *
     * Input: s = "(*)"
     * Output: true
     * Example 3:
     *
     * Input: s = "(*))"
     * Output: true
     */
    fun checkValidString(s: String): Boolean {
        var low = 0
        var high = 0
        for (c in s) {
            when (c) {
                '(' -> {
                    low++
                    high++
                }

                ')' -> {
                    low--
                    high--
                }

                '*' -> {
                    low--
                    high++
                }
            }
            if (high < 0) return false
            if (low < 0) low = 0
        }
        return low == 0
    }

}