package designPatterns.abstractfactory

class FormalShoeFactory : ShoeFactory {
    override fun createShoeSole(): Sole {
        return FlatSole()
    }

    override fun createShoeLace(): ShoeLace {
        return RoundShoeLace()
    }
}
