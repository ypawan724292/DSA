package dsa.strings

class Hard {

    /**
     * KMP Algorithm
     *
     * computeLPSArray : Longest Prefix which is also a Suffix
     *
     * pat[] = “AAACAAAA”
     *
     * => len = 0, i = 0:
     *
     * lps[0] is always 0, we move to i = 1
     * => len = 0, i = 1:
     *
     * Since pat[len] and pat[i] match, do len++,
     * store it in lps[i] and do i++.
     * Set len = 1, lps[1] = 1, i = 2
     * => len = 1, i  = 2:
     *
     * Since pat[len] and pat[i] match, do len++,
     * store it in lps[i] and do i++.
     * Set len = 2, lps[2] = 2, i = 3
     * => len = 2, i = 3:
     *
     * Since pat[len] and pat[i] do not match, and len > 0,
     * Set len = lps[len-1] = lps[1] = 1
     * => len = 1, i = 3:
     *
     * Since pat[len] and pat[i] do not match and len > 0,
     * len = lps[len-1] = lps[0] = 0
     * => len = 0, i = 3:
     *
     * Since pat[len] and pat[i] do not match and len = 0,
     * Set lps[3] = 0 and i = 4
     * => len = 0, i = 4:
     *
     * Since pat[len] and pat[i] match, do len++,
     * Store it in lps[i] and do i++.
     * Set len = 1, lps[4] = 1, i = 5
     * => len = 1, i = 5:
     *
     * Since pat[len] and pat[i] match, do len++,
     * Store it in lps[i] and do i++.
     * Set len = 2, lps[5] = 2, i = 6
     * => len = 2, i = 6:
     *
     * Since pat[len] and pat[i] match, do len++,
     * Store it in lps[i] and do i++.
     * len = 3, lps[6] = 3, i = 7
     * => len = 3, i = 7:
     *
     * Since pat[len] and pat[i] do not match and len > 0,
     * Set len = lps[len-1] = lps[2] = 2
     * => len = 2, i = 7:
     *
     * Since pat[len] and pat[i] match, do len++,
     * Store it in lps[i] and do i++.
     * len = 3, lps[7] = 3, i = 8
     * We stop here as we have constructed the whole lps[].
     */
    fun kmp(text: String, pattern: String): Boolean {        /*
        Example:
        pattern = "AAACAAAA"
        lps = [0, 1, 2, 0, 1, 2, 3, 3]
         */
        val n = text.length
        val m = pattern.length
        val lps = IntArray(m)

        /*
        Compute the LPS array
         */
        var len = 0
        var i = 1
        while (i < m) {
            if (pattern[i] == pattern[len]) {
                len++
                lps[i] = len
                i++
            } else {
                if (len != 0) {
                    len = lps[len - 1]
                } else {
                    lps[i] = len
                    i++
                }
            }
        }


        i = 0
        var j = 0
        while (i < n) {
            if (pattern[j] == text[i]) {
                i++
                j++
            }
            if (j == m) {
                return true
            } else if (i < n && pattern[j] != text[i]) {
                if (j != 0) {
                    j = lps[j - 1]
                } else {
                    i++
                }
            }
        }
        return false
    }


}