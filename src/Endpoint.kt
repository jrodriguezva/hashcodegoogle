import java.util.ArrayList

/**
* @author juan
* @since 23/2/17
*/
class Endpoint {
    private var latencyList: MutableList<Latency> = ArrayList()
    var id: Int = 0
    private var requestList: MutableList<`Request.kt`> = ArrayList()
    var latency: Int = 0

    fun getLatencyList(): List<Latency> {
        return latencyList
    }

    fun setLatencyList(latencyList: MutableList<Latency>) {
        this.latencyList = latencyList
    }

    fun getRequestList(): List<`Request.kt`> {
        return requestList
    }

    fun setRequestList(requestList: MutableList<`Request.kt`>) {
        this.requestList = requestList
    }

    constructor(latencyList: MutableList<Latency>, id: Int, requestList: MutableList<`Request.kt`>, latency: Int) {
        this.latencyList = latencyList
        this.id = id
        this.requestList = requestList
        this.latency = latency
    }

    constructor(id: Int, latency: Int) {
        this.id = id
        this.latency = latency
    }

    fun addLatencyList(latencyList: Latency) {
        this.latencyList.add(latencyList)
    }

    fun addRequestList(requestList: `Request.kt`) {
        this.requestList.add(requestList)
    }
}
