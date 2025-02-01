package designPatterns.abstractfactory

interface ShoeFactory {
    fun createShoeSole(): Sole?
    fun createShoeLace(): ShoeLace?
}
