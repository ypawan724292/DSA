package designPatterns.adapter

internal class AudioPlayer : MediaPlayer {
    var mediaAdapter: MediaAdapter? = null

    override fun play(audioType: String, fileName: String) {
        // inbuilt support to play mp3 music files
        if (audioType.equals("mp3", ignoreCase = true)) {
            println("Playing mp3 file. Name: " + fileName)
        } else if (audioType.equals("vlc", ignoreCase = true) || audioType.equals("mp4", ignoreCase = true)) {
            mediaAdapter = MediaAdapter(audioType)
            mediaAdapter!!.play(audioType, fileName)
        } else {
            println("Invalid media. " + audioType + " format not supported")
        }
    }
}
