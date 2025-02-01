package dsa.stackAndQueue

class Problems {

    /**
     *
     * Code
     * Testcase
     * Test Result
     * 921. Minimum Add to Make Parentheses Valid
     * Solved
     * Medium
     * Topics
     * Companies
     * A parentheses string is valid if and only if:
     *
     * It is the empty string,
     * It can be written as AB (A concatenated with B), where A and B are valid strings, or
     * It can be written as (A), where A is a valid string.
     * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
     *
     * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
     * Return the minimum number of moves required to make s valid.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "())"
     * Output: 1
     * Example 2:
     *
     * Input: s = "((("
     * Output: 3
     *
     */
    fun minAddToMakeValid(s: String): Int {
        var open = 0
        var close = 0
        for (c in s) {
            when (c) {
                '(' -> open++
                ')' -> if (open > 0) open-- else close++
            }
        }
//        return open + close

        //using stack
        val stack = ArrayDeque<Char>()
        for(ch in s){
            when(ch){
                '(' -> stack.add(ch)
                ')' -> {
                    val top = stack.lastOrNull() ?: ' '
                    if(top=='('){
                        stack.removeLast()
                    }else{
                        stack.add(ch)
                    }
                }
            }
        }
        return stack.size
    }
}