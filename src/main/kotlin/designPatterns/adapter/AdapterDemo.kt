package designPatterns.adapter

object AdapterDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val audioPlayer = AudioPlayer()

        audioPlayer.play("mp3", "beyond_the_horizon.mp3")
        audioPlayer.play("mp4", "alone.mp4")
        audioPlayer.play("vlc", "far_far_away.vlc")
        audioPlayer.play("avi", "mind_me.avi")
    }
}