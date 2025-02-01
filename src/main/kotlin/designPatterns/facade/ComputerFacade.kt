package designPatterns.facade

internal class ComputerFacade {
    private val cpu: CPU
    private val memory: Memory
    private val hardDrive: HardDrive

    init {
        this.cpu = CPU()
        this.memory = Memory()
        this.hardDrive = HardDrive()
    }

    fun start() {
        println("ComputerFacade: Starting the computer...")
        cpu.freeze()
        memory.load(0, hardDrive.read(0, 1024))
        cpu.jump(0)
        cpu.execute()
        println("ComputerFacade: Computer has started.")
    }
}