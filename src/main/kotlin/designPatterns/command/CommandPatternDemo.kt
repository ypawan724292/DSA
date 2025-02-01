package designPatterns.command

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