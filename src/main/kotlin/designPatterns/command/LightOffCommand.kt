package designPatterns.command

internal class LightOffCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOff()
    }

    override fun undo() {
        light.turnOn()
    }
}