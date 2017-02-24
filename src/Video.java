import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 23/2/17.
 */
public class Video {
    private int id;
    private int size;
    private List<Request> requestList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Video(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void addRequestList(Request requestList) {
        this.requestList.add(requestList);
    }

    public List<Request> getRequestList() {
        return requestList;
    }


}
