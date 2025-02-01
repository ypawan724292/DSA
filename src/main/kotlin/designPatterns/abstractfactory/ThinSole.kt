package designPatterns.abstractfactory

class ThinSole : Sole {
    override fun soleBuild(): String {
        return "Thin Plated"
    }

    override fun soleMaterial(): String {
        return "Rubber"
    }
}
