package designPatterns.command

internal class RemoteControlWithUndo {
    private val commands: List<Command?> = ArrayList()
    private val undoCommands: List<Command?> = ArrayList()

    fun addCommand(command: Command?) {
        commands.add(command)
    }

    fun executeCommands() {
        for (command in commands) {
            command!!.execute()
            undoCommands.add(command)
        }
        commands.clear()
    }

    fun undoLastCommand() {
        if (!undoCommands.isEmpty()) {
            val lastCommand: Command = undoCommands.remove(undoCommands.size() - 1)
            lastCommand.undo()
        }
    }
}