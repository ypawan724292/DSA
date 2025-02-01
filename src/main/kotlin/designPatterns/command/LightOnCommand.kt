package designPatterns.command

internal class LightOnCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOn()
    }

    override fun undo() {
        light.turnOff()
    }
}