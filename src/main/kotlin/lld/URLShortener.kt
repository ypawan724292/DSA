package lld

class URLShortener {
    private val urlMap = mutableMapOf<String, String>()
    private val keyMap = mutableMapOf<String, String>()
    private val domain = "http://short.url/"
    private var counter = 1

    // Method to shorten a URL
    fun shortenURL(longURL: String): String {
        if (urlMap.containsKey(longURL)) {
            return domain + urlMap[longURL]
        }
        val shortURL = encode(counter)
        urlMap[longURL] = shortURL
        keyMap[shortURL] = longURL
        counter++
        return domain + shortURL
    }

    // Method to retrieve the original URL
    fun getOriginalURL(shortURL: String): String? {
        val key = shortURL.replace(domain, "")
        return keyMap[key]
    }

    // Encoding method to convert an integer to a base62 string
    private fun encode(num: Int): String {
        val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        var n = num
        val sb = StringBuilder()
        while (n > 0) {
            sb.append(chars[n % 62])
            n /= 62
        }
        return sb.reverse().toString()
    }
}