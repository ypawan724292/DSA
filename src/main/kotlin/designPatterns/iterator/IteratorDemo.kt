package designPatterns.iterator

object IteratorDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val bookShelf = BookShelf()
        bookShelf.addBook(Book("Design Patterns"))
        bookShelf.addBook(Book("Clean Code"))
        bookShelf.addBook(Book("Refactoring"))

        val iterator = bookShelf.getIterator()
        println("Books in the shelf:")
        while (iterator.hasNext()) {
            val book = iterator.next()
            println("- " + book.getTitle())
        }
    }
}