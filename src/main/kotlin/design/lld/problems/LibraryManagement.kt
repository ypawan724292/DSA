package design.lld.problems


/**
 * Library Management System - LLD
 * Functional Requirements
 * Users can search for books by title, author, or category.
 * Users can borrow and return books.
 * Admin can add/remove books.
 * Books have due dates and late fees.
 * System tracks issued books and available books.
 */
data class Book(val id: Int, val title: String, val author: String, var isAvailable: Boolean = true)

data class User(val id: Int, val name: String)

class Library(private val books: MutableList<Book>) {
    private val issuedBooks = mutableMapOf<PaymentUser, MutableList<Book>>()

    fun searchBook(title: String): List<Book> {
        return books.filter { it.title.contains(title, ignoreCase = true) && it.isAvailable }
    }

    fun issueBook(user: PaymentUser, book: Book): Boolean {
        if (!book.isAvailable) return false
        book.isAvailable = false
        issuedBooks.computeIfAbsent(user) { mutableListOf() }.add(book)
        return true
    }

    fun returnBook(user: PaymentUser, book: Book): Boolean {
        val userBooks = issuedBooks[user] ?: return false
        if (!userBooks.contains(book)) return false

        book.isAvailable = true
        userBooks.remove(book)
        return true
    }
}

fun main() {
    val books = mutableListOf(
        Book(1, "Kotlin Programming", "JetBrains"),
        Book(2, "Effective Java", "Joshua Bloch")
    )

    val library = Library(books)
    val user = PaymentUser(1, "Pavan")

    println("Searching for Kotlin books: ${library.searchBook("Kotlin")}")

    val book = library.searchBook("Kotlin").firstOrNull()
    if (book != null) {
        library.issueBook(user, book)
        println("Book issued: $book")
    }
}
