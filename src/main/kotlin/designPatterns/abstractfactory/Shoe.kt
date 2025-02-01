package designPatterns.abstractfactory

class Shoe(private val sole: Sole, private val shoeLace: ShoeLace) {
    fun displayBuildShoe() {
        println("Sole Type is: " + this.sole.soleBuild())
        println("Sole Material is: " + this.sole.soleMaterial())
        println("ShoeLace Type is: " + this.shoeLace.shoeLaceBuild())
        println("ShoeLace Material is: " + this.shoeLace.shoeLaceMaterial())
    }
}
