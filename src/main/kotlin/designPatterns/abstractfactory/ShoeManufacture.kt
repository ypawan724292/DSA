package designPatterns.abstractfactory

object ShoeManufacture {
    var shoeFactory: ShoeFactory? = null
    fun produceShoe(shoeType: String?): Shoe {
        if (shoeType === "Formal") {
            shoeFactory = FormalShoeFactory()
        }
        if (shoeType === "Sports") {
            shoeFactory = SportsShoeFactory()
        }
        if (shoeType === "Casual") {
            shoeFactory = CasualShoeFactory()
        }
        return Shoe(shoeFactory!!.createShoeSole(), shoeFactory!!.createShoeLace())
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val formalShoe = produceShoe("Formal")
        val sportaShoe = produceShoe("Sports")
        val casualShoe = produceShoe("Casual")

        formalShoe.displayBuildShoe()
        sportaShoe.displayBuildShoe()
        casualShoe.displayBuildShoe()
    }
}
