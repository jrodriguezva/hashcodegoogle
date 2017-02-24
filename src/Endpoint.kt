import java.util.ArrayList

/**
 * Created by juan on 23/2/17.
 */
class Endpoint {
    private var latencyList: MutableList<Latency> = ArrayList()
    var id: Int = 0
    private var requestList: MutableList<Request> = ArrayList()
    var latency: Int = 0

    fun getLatencyList(): List<Latency> {
        return latencyList
    }

    fun setLatencyList(latencyList: MutableList<Latency>) {
        this.latencyList = latencyList
    }

    fun getRequestList(): List<Request> {
        return requestList
    }

    fun setRequestList(requestList: MutableList<Request>) {
        this.requestList = requestList
    }

    constructor(latencyList: MutableList<Latency>, id: Int, requestList: MutableList<Request>, latency: Int) {
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

    fun addRequestList(requestList: Request) {
        this.requestList.add(requestList)
    }
}
