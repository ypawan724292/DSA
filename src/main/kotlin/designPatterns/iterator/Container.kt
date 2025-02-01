package designPatterns.iterator

internal interface Container<T> {
    fun getIterator(): Iterator<T?>?
}
