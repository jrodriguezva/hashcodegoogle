import java.util.ArrayList

class Video(var id: Int, val size: Int) {
    private val requestList = ArrayList<`Request.kt`>()

    fun addRequestList(requestList: `Request.kt`) {
        this.requestList.add(requestList)
    }

    fun getRequestList(): List<`Request.kt`> {
        return requestList
    }


}
