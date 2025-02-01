package designPatterns.abstractfactory

class CasualShoeFactory : ShoeFactory {
    override fun createShoeSole(): Sole {
        return ThinSole()
    }

    override fun createShoeLace(): ShoeLace {
        return TapeShoeLace()
    }
}
