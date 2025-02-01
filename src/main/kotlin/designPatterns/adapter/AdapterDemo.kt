package designPatterns.adapter

object AdapterDemo {
    /**
     * The Adapter Design Pattern is a structural design pattern that allows objects with incompatible interfaces to work
     * together. It acts as a bridge between two incompatible interfaces by converting the interface of a class into
     * another interface that a client expects
     *
     * Key Components:
     * Target Interface: This is the interface that the client expects.
     * Adapter Class: This class implements the target interface and adapts the interface of the adaptee to the target interface.
     * Adaptee Class: This is the existing class that needs to be adapted.
     * Client: This uses the target interface.
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val audioPlayer = AudioPlayer()

        audioPlayer.play("mp3", "beyond_the_horizon.mp3")
        audioPlayer.play("mp4", "alone.mp4")
        audioPlayer.play("vlc", "far_far_away.vlc")
        audioPlayer.play("avi", "mind_me.avi")
    }
}