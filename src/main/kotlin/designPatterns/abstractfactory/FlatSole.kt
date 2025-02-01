package designPatterns.abstractfactory

class FlatSole : Sole {
    override fun soleBuild(): String {
        return "Flat"
    }

    override fun soleMaterial(): String {
        return "Synthetic Rubber"
    }
}
