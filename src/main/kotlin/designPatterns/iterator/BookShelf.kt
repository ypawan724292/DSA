package designPatterns.iterator

internal class BookShelf : Container<Book?> {
    private val books: ArrayList<Book?> = ArrayList()

    fun addBook(book: Book?) {
        books.add(book)
    }

    override fun getIterator(): Iterator<Book?> {
        return BookShelfIterator()
    }

    // ConcreteIterator
    private inner class BookShelfIterator : Iterator<Book?> {
        private var index = 0

        override fun hasNext(): Boolean {
            return index < books.size
        }

        override fun next(): Book? {
            if (this.hasNext()) {
                return books[index++]
            }
            return null
        }
    }
}