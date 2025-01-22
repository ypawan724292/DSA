package bitManipulation

class Basics {

    /**
     *
     *  Decimal Interpretation: (x) base10
     *
     *  Binary Interpretation: (y) base 2
     *
     *  All numbers in convert to binary and then do the operation
     *
     *  One's Complement: Inverting the bits of a number
     *  Example: 5 -> 101 -> 010 -> 2
     *
     *
     *  Two's Complement: Inverting the bits of a number and adding 1 to the least significant bit
     *  Example: 5 -> 101 -> 010 -> 011 -> 3
     *
     */
    fun convertToBinary(num: Int): String {
        var res = ""
        var n = num
        while (n > 0) {
            res = "${n % 2}$res"
            n /= 2
        }
        return res
    }

    fun convertToDecimal(binary: String): Int {
        var res = 0
        var power = 1
        for (i in binary.lastIndex downTo 0) {
            if (binary[i] == '1') {
                res += power
            }
            power *= 2
        }
        return res
    }

    /**
     * Signed and Unsigned Numbers:
     * 1. Signed Numbers: The leftmost bit is the sign bit, 0 for positive and 1 for negative
     *  Example: 5 -> 0000 0101,
     *          -5 -> 1000 0101 it is 2's complement of 5
     *
     *          Negative numbers are always stored in 2's complement form in memory
     * 2. Unsigned Numbers: The leftmost bit is not the sign bit, it is used to represent the number
     *
     *
     * Bitwise Operators:
     *
     * 1. AND (&), if all are 1's then 1 else 0
     * 2. OR (|), if any one is 1 then 1 else 0
     * 3. XOR (^), if no of 1's are odd then 1 else 0
     * 4. NOT (~), inverts the bits if -ve then 2's complement else stop
     * 5. Left Shift (<<), shifts the bits to the left
     * when (x << k) then x * 2^k
     * 6. Right Shift (>>), shifts the bits to the right
     * when (x >> k) then x / 2^k
     *
     *
     *
     *
     * Largest and Smallest Number of 32 bit signed integer:
     * 1. Largest: 2^31 - 1 , 0111 1111 1111 1111 1111 1111 1111 1111,
     *  Geometric Progression Sum = a * (r^n - 1) / (r - 1)
     *        2 * (2^31 - 1) / (2 - 1) = 2^31 + 2^30 +.....+ 2^1 + 2^0 = 2^31 - 1
     * 2. Smallest: -2^31,
     *      2^31 = 1000 0000 0000 0000 0000 0000 0000 0000
     *
     */


    fun swapTwoNumbers(a: Int, b: Int): Pair<Int, Int> {
        var x = a
        var y = b
        x = x xor y
        y = x xor y
        x = x xor y
        return Pair(x, y)
    }

    fun checkEvenOrOdd(num: Int): Boolean {
        return num and 1 == 0
    }

    fun checkKthBitSet(num: Int, k: Int): Boolean {
        //using right shift operator
//        return (num shr k) and 1 == 1

        // 1 << k, 1 is shifted k times to the left
        return num and (1 shl k) != 0
    }


    fun setKthBit(num: Int, k: Int): Int {
        return num or (1 shl k)
    }

    fun clearKthBit(num: Int, k: Int): Int {
        val mask = 1 shl k
        return num and mask.inv()
    }

    fun toggleKthBit(num: Int, k: Int): Int {
        return num xor (1 shl k)
    }

    fun clearRightMostSetBit(num: Int): Int {
        return num and (num - 1)
    }

    fun checkPowerOfTwo(num: Int): Boolean {
        return num and (num - 1) == 0
    }

    fun countSetBits(num: Int): Int {
        var n = num
        var count = 0
        while (n > 0) {
            n = n and (n - 1)
            count++
        }
        return count
    }


}