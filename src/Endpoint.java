import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 23/2/17.
 */
public class Endpoint {
    private List<Latency> latencyList = new ArrayList<>();
    private int id;
    private List<Request> requestList = new ArrayList<>();
    private int latency;

    public int getLatency() {
        return latency;
    }

    public List<Latency> getLatencyList() {
        return latencyList;
    }

    public void setLatencyList(List<Latency> latencyList) {
        this.latencyList = latencyList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public void setLatency(int latency) {
        this.latency = latency;
    }

    public Endpoint(List<Latency> latencyList, int id, List<Request> requestList, int latency) {
        this.latencyList = latencyList;
        this.id = id;
        this.requestList = requestList;
        this.latency = latency;
    }

    public Endpoint(int id, int latency) {
        this.id = id;
        this.latency = latency;
    }

    public void addLatencyList(Latency latencyList) {
        this.latencyList.add(latencyList);
    }

    public void addRequestList(Request requestList) {
        this.requestList.add(requestList);
    }
}
