package designPatterns.command

/**
 * The Command Design Pattern is a behavioral design pattern that turns a request into a stand-alone object that
 * contains all information about the request. This transformation allows you to parameterize methods with different requests,
 * delay or queue a request's execution, and support undoable operations.
 *
 * Key Components:
 * Command: An interface that declares a method for executing a command.
 * Concrete Command: Classes that implement the Command interface and define the binding between a Receiver object and an action.
 * Receiver: The object that performs the actual work.
 * Invoker: The object that knows how to execute a command and optionally does bookkeeping about the command's execution.
 * Client: The object that creates a Concrete Command and sets its receiver.
 */
object CommandPatternDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        // Set up the receiver
        val livingRoomLight = Light("Living Room")
        val kitchenLight = Light("Kitchen")

        // Create concrete commands
        val livingRoomLightOn: Command = LightOnCommand(livingRoomLight)
        val livingRoomLightOff: Command = LightOffCommand(livingRoomLight)
        val kitchenLightOn: Command = LightOnCommand(kitchenLight)
        val kitchenLightOff: Command = LightOffCommand(kitchenLight)

        // Set up the invoker
        val remote = RemoteControl()

        // Use the invoker to execute commands
        remote.setCommand(livingRoomLightOn)
        remote.pressButton()
        remote.setCommand(kitchenLightOn)
        remote.pressButton()
        remote.setCommand(livingRoomLightOff)
        remote.pressButton()
        remote.setCommand(kitchenLightOff)
        remote.pressButton()

        // Demonstrate undo
        println("\nDemonstrating undo:")
        remote.setCommand(livingRoomLightOn)
        remote.pressButton()
        remote.pressUndoButton()

        // Demonstrate RemoteControlWithUndo
        println("\nDemonstrating RemoteControlWithUndo:")
        val advancedRemote = RemoteControlWithUndo()
        advancedRemote.addCommand(livingRoomLightOn)
        advancedRemote.addCommand(kitchenLightOn)
        advancedRemote.executeCommands()
        advancedRemote.undoLastCommand()
        advancedRemote.undoLastCommand()
    }
}