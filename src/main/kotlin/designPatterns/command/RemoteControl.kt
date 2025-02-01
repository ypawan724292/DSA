package designPatterns.command

internal class RemoteControl {
    private var command: Command? = null

    fun setCommand(command: Command) {
        this.command = command
    }

    fun pressButton() {
        command!!.execute()
    }

    fun pressUndoButton() {
        command!!.undo()
    }
}