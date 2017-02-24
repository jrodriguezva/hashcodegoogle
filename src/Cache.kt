import java.util.ArrayList

/**
 * @author juan
 * *
 * @since 23/2/17
 */
class Cache(var id: Int, maxSize: Int) {
    var sizeAvailable: Int = 0
    var idsVideo: MutableList<Int> = ArrayList()
    var videos: MutableList<Video> = ArrayList()

    fun addIdsVideo(idsVideo: Int) {
        this.idsVideo.add(idsVideo)
    }

    init {
        this.sizeAvailable = maxSize
    }
}
