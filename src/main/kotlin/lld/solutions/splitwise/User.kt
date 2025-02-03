package lld.solutions.splitwise

import java.util.concurrent.ConcurrentHashMap

class User(val id: String?, val name: String?, val email: String?) {
    val balances: MutableMap<String?, Double?>

    init {
        this.balances = ConcurrentHashMap<String?, Double?>()
    }
}
