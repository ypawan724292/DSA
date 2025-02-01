package designPatterns.iterator

/**
 * The Iterator Design Pattern is a behavioral design pattern that provides a way to access the elements of an aggregate object
 * sequentially without exposing its underlying representation.
 * Key Components:
 * Iterator: An interface that defines methods for accessing and traversing elements.
 * Concrete Iterator: A class that implements the Iterator interface and provides the actual traversal of the aggregate.
 * Aggregate: An interface that defines a method to create an iterator.
 * Concrete Aggregate: A class that implements the Aggregate interface and returns an instance of the Concrete Iterator.
 */
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
            println("- " + book?.title)
        }
    }
}