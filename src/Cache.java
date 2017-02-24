import java.util.ArrayList;
import java.util.List;

/**
 * @author juan
 * @since 23/2/17
 */
public class Cache {
    int id;
    int maxSize;
    int sizeAvailable;
    List<Integer> idsVideo = new ArrayList<>();

    public List<Integer> getIdsVideo() {
        return idsVideo;
    }

    public void setIdsVideo(List<Integer> idsVideo) {
        this.idsVideo = idsVideo;
    }

    public void addIdsVideo(Integer idsVideo) {
        this.idsVideo.add(idsVideo);
    }

    public Cache(int id, int maxSize) {
        this.id = id;
        this.maxSize = maxSize;
        this.sizeAvailable = maxSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getSizeAvailable() {
        return sizeAvailable;
    }

    public void setSizeAvailable(int sizeAvailable) {
        this.sizeAvailable = sizeAvailable;
    }
}
