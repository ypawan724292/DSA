package design.lld.problems

data class Book(val title: String, val author: String, val price: Double)

data class BookUser(val name: String, val email: String)

data class Order(val book: Book, val bookUser: BookUser)

class BookStore(val books: List<Book>) {
    fun searchBooks(title: String): List<Book> {
        return books.filter { it.title.contains(title, ignoreCase = true) }
    }

    fun buyBook(book: Book, bookUser: BookUser): Order {
        return Order(book, bookUser)
    }
}

fun main() {
    val books = listOf(
        Book("Kotlin Programming", "JetBrains", 29.99),
        Book("Effective Java", "Joshua Bloch", 39.99)
    )
    val store = BookStore(books)

    val bookUser = BookUser("Pavan", "pavan@example.com")
    val searchResults = store.searchBooks("Kotlin")
    println("Search results: $searchResults")

    if (searchResults.isNotEmpty()) {
        val order = store.buyBook(searchResults[0], bookUser)
        println("Order placed: $order")
    }
}
