package designPatterns.iterator

internal interface Container<T> {
    val iterator: Iterator<T?>?
}
