package designPatterns.abstractfactory

class BumpySole : Sole {
    override fun soleBuild(): String {
        return "Bummpy"
    }

    override fun soleMaterial(): String {
        return "Plastic Rubber"
    }
}
