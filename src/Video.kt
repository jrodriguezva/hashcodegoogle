import java.util.ArrayList

class Video(var id: Int, val size: Int) {
    private val requestList = ArrayList<Request>()

    fun addRequestList(requestList: Request) {
        this.requestList.add(requestList)
    }

    fun getRequestList(): List<Request> {
        return requestList
    }


}
