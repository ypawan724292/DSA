package designPatterns.abstractfactory

class SportsShoeFactory : ShoeFactory {
    override fun createShoeSole(): Sole {
        return BumpySole()
    }

    override fun createShoeLace(): ShoeLace {
        return RoundShoeLace()
    }
}
