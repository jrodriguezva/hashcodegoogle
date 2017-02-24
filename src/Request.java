/**
 * Created by juan on 23/2/17.
 */
public class Request {
    private final int amount;
    private final int idVideo;
    private final Endpoint endpoint;

    public Request(int amount, int idVideo, Endpoint endpoint) {
        this.amount = amount;
        this.idVideo = idVideo;
        this.endpoint = endpoint;
    }

    public int getAmount() {
        return amount;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

}
