package designPatterns.abstractfactory

class RoundShoeLace : ShoeLace {
    override fun shoeLaceBuild(): String {
        return "Round"
    }

    override fun shoeLaceMaterial(): String {
        return "Synthetic Polyster"
    }
}
