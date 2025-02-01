package designPatterns.adapter

internal interface MediaPlayer {
    fun play(audioType: String?, fileName: String?)
}