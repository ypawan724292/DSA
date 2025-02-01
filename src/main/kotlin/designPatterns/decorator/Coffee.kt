package designPatterns.decorator

internal interface Coffee {
    fun getCost() : Double
    fun getDescription(): String?
}