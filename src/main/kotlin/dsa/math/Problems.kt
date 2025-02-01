package dsa.math

import kotlin.math.sqrt

class Problems {

    /**
     * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer.
     * The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
     *
     * Increment the large integer by one and return the resulting array of digits.
     */
    fun plusOne(digits: IntArray): IntArray {

        for (i in digits.size - 1 downTo 0) {
            if (digits[i] < 9) {
                digits[i]++
                return digits
            }
            digits[i] = 0
        }
        val res = IntArray(digits.size + 1)
        res[0] = 1

        return res
    }


    /**
     * Happy Number
     */
    fun isHappy(n: Int): Boolean {

        fun digitSquareSum(n: Int): Int {
            var sum = 0
            var num = n
            while (num > 0) {
                val digit = num % 10
                sum += digit * digit
                num /= 10
            }
            return sum
        }

        var temp = n
        val visited = mutableSetOf<Int>()
        while (temp != 1) {
            visited.add(temp)
            temp = digitSquareSum(temp)
            if (visited.contains(temp)) {
                return false
            }
        }
        return true


        //using slow and fast check
    }


    /**
     * Multiply Strings
     * Solved
     * Medium
     * Topics
     * Companies
     * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
     *
     * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
     *
     * Input: num1 = "2", num2 = "3"
     */
    fun multiply(num1: String, num2: String): String {
        val num1 = num1.reversed()
        val num2 = num2.reversed()
        val len1 = num1.length
        val len2 = num2.length


        val sums = IntArray(len1 + len2)


        for (i in 0 until len1) {
            val a = num1[i] - '0'
            for (j in 0 until len2) {
                val b = num2[j] - '0'
                sums[i + j] += a * b
            }
        }

        var res = ""
        var carry = 0

        for (sum in sums) {
            val digit = (sum + carry) % 10
            carry = (sum + carry) / 10
            res += digit
        }

        res = if (res.last() == '0') res.substring(0, res.length - 1) else res

        return res.reversed()
    }

    /**
     * The algorithm can be improved further by observing that all primes are of the form 6k ± 1,
     * with the exception of 2 and 3. This is because all integers can be expressed as (6k + i)
     * for some integer k and for i = -1, 0, 1, 2, 3, or 4.
     */
    fun isPrime(n: Int): Boolean {
        //navie approach
        if (n <= 1) return false
        val sqrt = sqrt(n.toDouble()).toInt()
        for (i in 2..sqrt) {
            if (n % i == 0) return false
        }

        //optimized
        if (n <= 1) return false
        if (n <= 3) return true
        if (n % 2 == 0 || n % 3 == 0) return false

        var i = 5
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false
            i += 6
        }
        return true
    }

    /**
     * Given a number N. Find its unique prime factors in increasing order.
     *
     *
     * Example 1:
     *
     * Input: N = 100
     * Output: 2 5
     * Explanation: 2 and 5 are the unique prime
     * factors of 100.
     * Example 2:
     *
     * Input: N = 35
     * Output: 5 7
     * Explanation: 5 and 7 are the unique prime
     * factors of 35.
     *
     *
     * Your Task:
     * You don't need to read or print anything. Your task is to complete the function AllPrimeFactors()
     * which takes N as input parameter and returns a list of all unique prime factors of N in increasing order.
     *
     * Expected Time Complexity: O(N)
     * Expected Space Complexity: O(N)
     */
    fun allPrimeFactors(n: Int): List<Int> {
        //naive approach
        val res = mutableListOf<Int>()
        for (i in 2..n) {
            if (n % i == 0) {
                if (isPrime(i))
                    res.add(i)
            }
        }
//        return res

        //factors approach
        val m = sqrt(n.toDouble()).toInt()
        for (i in 1..m) {
            if (n % i == 0) {
                if (isPrime(i)) res.add(i)
                if (n / i != i && isPrime(n / i)) res.add(n / i)
            }
        }
//        return res


        //optimized
        // TC : O(n)
        var num = n
        for (i in 2..num) {
            if (num % i == 0) {
                res.add(i)
                while (num % i == 0) {
                    num /= i
                }
            }
        }

//        return res

        // using sqrt of optimized
        // TC : O(sqrt(n))
        for (i in 2..sqrt(num.toDouble()).toInt()) {
            if (num % i == 0) {
                res.add(i)
                while (num % i == 0) {
                    num /= i
                }
            }
        }

        if (num > 1) {
            res.add(num)
        }
        return res
    }


    /**
     * Given an integer N, print all the divisors of N in the ascending order.
     *
     *
     * Example 1:
     *
     * Input : 20
     * Output: 1 2 4 5 10 20
     * Explanation: 20 is completely
     * divisible by 1, 2, 4, 5, 10 and 20.
     *
     * Example 2:
     *
     * Input: 21191
     * Output: 1 21191
     * Explanation: As 21191 is a prime number,
     * it has only 2 factors(1 and the number itself).
     *
     * Your Task:
     *
     * Your task is to complete the function print_divisors() which takes N as input parameter and
     * prints all the factors of N as space seperated integers in sorted order. You don't have to print any new line after printing the desired output. It will be handled in driver code.
     *
     *
     * Expected Time Complexity: O(sqrt(N))
     * Expected Space Complexity: O(sqrt(N))
     */
    fun allDivisors(num: Int): List<Int> {
        val res = mutableListOf<Int>()

        for (i in 1..num) {
            if (num % i == 0) {
                res.add(i)
            }
        }

        val n = sqrt(num.toDouble()).toInt()

        //Tc : O(sqrt(n))
        for (i in 1..n) {
            if (num % i == 0) {
                res.add(i)
                if (num / i != i) {
                    res.add(num / i)
                }
            }
        }
//        return res


        // using i*i < n

        var i = 1

        while (i * i < num) {
            if (num % i == 0) {
                res.add(i)
                if (num / i != i) {
                    res.add(num / i)
                }
            }
            i++
        }

        if (i * i == num) {
            res.add(i)
        }

        return res
    }

    /**
     * Sieve of Eratosthenes
     *
     * 204. Count Primes
     * Medium
     * Topics
     * Companies
     * Hint
     * Given an integer n, return the number of prime numbers that are strictly less than n.
     *
     *
     * Example 1:
     *
     * Input: n = 10
     * Output: 4
     * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
     * Example 2:
     *
     * Input: n = 0
     * Output: 0
     * Example 3:
     *
     * Input: n = 1
     * Output: 0
     *
     *
     * Constraints:
     *
     * 0 <= n <= 5 * 106
     *
     *
     */
    fun countPrimes(n: Int): Int {
        if (n <= 2) return 0
        val primes = BooleanArray(n) { true }
        primes[0] = false
        primes[1] = false
        var count = 0
        for (i in 2 until n) {
            if (primes[i]) {
                count++
                var j = 2
                while (i * j < n) {
                    primes[i * j] = false
                    j++
                }
            }
        }
//        return count

        //optimized
        if (n < 2) return 0
        val isPrime = IntArray(n) { 1 }

        for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
            var j = i * i
            while (j < n) {
                isPrime[j] = 0
                j += i
            }
        }

        return isPrime.sum() - 2
    }


    /**
     * Prime Factorization using Sieve
     * Difficulty: MediumAccuracy: 47.01%Submissions: 20K+Points: 4
     *
     * You are given a positive number N. Using the concept of Sieve, compute its prime factorisation.
     *
     * Example:
     *
     * Input:
     * N = 12246
     * Output:
     * 2 3 13 157
     * Explanation:
     * 2*3*13*157 = 12246 = N.
     * Your Task:
     *
     * Comple the function findPrimeFactors(), which takes a positive number N as input and returns a vector consisting of prime factors. You should implement Sieve algorithm to solve this problem.
     *
     *
     *
     * Expected Time Complexity: O(Nlog(log(N)).
     *
     * Expected Auxiliary Space: O(N).
     *
     * Constraints:
     *
     * 2<=N<=2*105
     *
     *
     */
    fun primeFactorization(n: Int): List<Int> {
        val res = mutableListOf<Int>()
        val primes = BooleanArray(n) { true }
        primes[0] = false
        primes[1] = false
        for (i in 2 until n) {
            if (primes[i]) {
                var j = 2
                while (i * j < n) {
                    primes[i * j] = false
                    j++
                }
                if (n % i == 0) {
                    res.add(i)
                }
            }
        }
        return res
    }

    /**
     * 50. Pow(x, n)
     * Solved
     * Medium
     * Topics
     * Companies
     * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
     *
     *
     *
     * Example 1:
     *
     * Input: x = 2.00000, n = 10
     * Output: 1024.00000
     * Example 2:
     *
     * Input: x = 2.10000, n = 3
     * Output: 9.26100
     * Example 3:
     *
     * Input: x = 2.00000, n = -2
     * Output: 0.25000
     * Explanation: 2-2 = 1/22 = 1/4 = 0.25
     */
    fun myPow(x: Double, n: Int): Double {
        // naive approach
        var res = 1.0
        for (i in 0 until n) {
            res *= x
        }

//        return res

        //using while loop

        var a = x
        var b = if (n < 0) -n else n
        while (b > 0) {
            if (b % 2 == 1) {
                res *= a
                b--
            } else {
                a *= a
                b /= 2
            }
        }

        return if (n < 0) 1.0 / res else res

    }
}