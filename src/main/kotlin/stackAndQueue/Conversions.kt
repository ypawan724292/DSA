package stackAndQueue

class Conversions {

    fun isLetterOrDigit(c: Char): Boolean {
        return c in 'a'..'z' || c in 'A'..'Z' || c in '0'..'9'
    }

    /**
     *  Infix expression:  When an operator is in-between every pair of operands.
     *  Example: A + B
     *  Prefix expression: When an operator is before every pair of operands.
     *  Example: + A B
     *  Postfix expression: When an operator is after every pair of operands.
     *  Example: A B +
     */

    /**
     * Example:
     * infixToPostfix("A+B*C") -> "ABC*+"
     */
    fun infixToPostfix(expression: String): String {
        val stack = ArrayDeque<Char>()
        var res = ""

        val precedenceMap = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2, '^' to 3)

        for (c in expression) {
            when {
                c.isLetterOrDigit() -> res += c
                c == '(' -> stack.add(c)
                c == ')' -> {
                    while (stack.isNotEmpty() && stack.last() != '(') {
                        res += stack.removeLast()
                    }
                    stack.removeLast()
                }

                else -> {
                    while (stack.isNotEmpty() && (precedenceMap[stack.last()] ?: 0) >= precedenceMap[c]!!) {
                        res += stack.removeLast()
                    }
                    stack.add(c)
                }
            }
        }

        while (stack.isNotEmpty()) {
            res += stack.removeLast()
        }

        return res
    }

    fun infixToPrefix(expression: String): String {

        fun reverseString(str: String): String {
            val res = str.toCharArray()

            for (i in 0 until res.size / 2) {
                val temp = res[i]
                res[i] = res[res.size - i - 1]
                res[res.size - i - 1] = temp
            }

            for (i in res.indices) {
                if (res[i] == '(') res[i] = ')'
                else if (res[i] == ')') res[i] = '('
            }

            return String(res)
        }

        val reversedExpression = reverseString(expression)

        val stack = ArrayDeque<Char>()
        var res = ""

        val precedenceMap = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2, '^' to 3)

        for (c in reversedExpression) {
            when {
                c.isLetterOrDigit() -> res += c
                c == ')' -> stack.add(c)
                c == '(' -> {
                    while (stack.isNotEmpty() && stack.last() != ')') {
                        res += stack.removeLast()
                    }
                    stack.removeLast()
                }

                else -> {
                    while (stack.isNotEmpty() && (
                                (precedenceMap[stack.last()] ?: 0) > precedenceMap[c]!! ||
                                        c != '^' && (precedenceMap[stack.last()] ?: 0) >= precedenceMap[c]!!)
                    ) {
                        res += stack.removeLast()
                    }
                    stack.add(c)
                }
            }
        }

        while (stack.isNotEmpty()) {
            res += stack.removeLast()
        }

        return res.reversed()
    }

    /**
     * Example:
     * postfixToInfix("ABC*+") -> "A+B*C"
     */
    fun postfixToInfix(expression: String): String {
        val stack = ArrayDeque<String>()

        for (c in expression) {
            if (c.isLetterOrDigit()) {
                stack.add(c.toString())
            } else {
                val op2 = stack.removeLast()
                val op1 = stack.removeLast()
                stack.add("$op1$c$op2")
            }
        }

        return stack.removeLast()
    }

    /**
     * Example:
     * postfixToPrefix("ABC*+") -> "+A*BC"
     */
    fun postfixToPrefix(expression: String): String {
        val stack = ArrayDeque<String>()

        for (c in expression) {
            if (c.isLetterOrDigit()) {
                stack.add(c.toString())
            } else {
                val op2 = stack.removeLast()
                val op1 = stack.removeLast()
                stack.add("$c$op1$op2")
            }
        }

        return stack.removeLast()
    }

    /**
     * Example:
     * prefixToInfix("+*ABC") -> "A*B+C"
     */
    fun prefixToInfix(expression: String): String {
        val stack = ArrayDeque<String>()

        for (i in expression.length - 1 downTo 0) {
            val c = expression[i]
            if (c.isLetterOrDigit()) {
                stack.add(c.toString())
            } else {
                val op1 = stack.removeLast()
                val op2 = stack.removeLast()
                stack.add("($op1$c$op2)")
            }
        }

        return stack.removeLast()
    }


    /**
     * Example:
     * prefixToPostfix("*+ABC") -> "AB+C*"
     */
    fun prefixToPostfix(expression: String): String {
        val stack = ArrayDeque<String>()

        for (i in expression.length - 1 downTo 0) {
            val c = expression[i]
            if (c.isLetterOrDigit()) {
                stack.add(c.toString())
            } else {
                val op1 = stack.removeLast()
                val op2 = stack.removeLast()
                stack.add("$op1$op2$c")
            }
        }

        return stack.removeLast()
    }

}