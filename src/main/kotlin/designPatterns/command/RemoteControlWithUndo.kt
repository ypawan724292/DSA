package designPatterns.command

internal class RemoteControlWithUndo {
    private val commands = mutableListOf<Command>()
    private val undoCommands = mutableListOf<Command>()

    fun addCommand(command: Command) {
        commands.add(command)
    }

    fun executeCommands() {
        for (command in commands) {
            command.execute()
            undoCommands.add(command)
        }
        commands.clear()
    }

    fun undoLastCommand() {
        if (!undoCommands.isEmpty()) {
            val lastCommand: Command = undoCommands.last()
            lastCommand.undo()
        }
    }
}