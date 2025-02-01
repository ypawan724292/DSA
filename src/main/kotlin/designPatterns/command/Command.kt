package designPatterns.command

internal interface Command {
    fun execute()
    fun undo()
}