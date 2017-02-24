import java.util.ArrayList

/**
 * @author juan
 * @since 23/2/17
 */
class Endpoint(var id: Int, var latency: Int) {
    private var latencyList: MutableList<Latency> = ArrayList()
    private var requestList: MutableList<Request> = ArrayList()

    fun getLatencyList(): List<Latency> {
        return latencyList
    }

    fun addLatencyList(latencyList: Latency) {
        this.latencyList.add(latencyList)
    }

    fun addRequestList(requestList: Request) {
        this.requestList.add(requestList)
    }
}
