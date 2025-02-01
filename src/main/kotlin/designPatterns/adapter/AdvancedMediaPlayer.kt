package designPatterns.adapter

internal interface AdvancedMediaPlayer {
    fun playVlc(fileName: String?)
    fun playMp4(fileName: String?)
}