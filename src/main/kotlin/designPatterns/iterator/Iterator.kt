package designPatterns.iterator

internal interface Iterator<T> {
    fun hasNext(): Boolean
    fun next(): T?
}