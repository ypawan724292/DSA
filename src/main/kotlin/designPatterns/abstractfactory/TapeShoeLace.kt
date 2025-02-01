package designPatterns.abstractfactory

class TapeShoeLace : ShoeLace {
    override fun shoeLaceBuild(): String {
        return "Tape Flat"
    }

    override fun shoeLaceMaterial(): String {
        return "Synthetic Cotton"
    }
}
