import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static int cacheSize = 0;

    public static void main(String[] args) {
        String filePath = "";
        HashMap<Integer, Video> videos = new HashMap<>();
        HashMap<Integer, Endpoint> endpointHashMap = new HashMap<>();
        HashMap<Integer, Cache> cacheHashMap = new HashMap<>();
        HashMap<Integer, Integer> saveTime = new HashMap<>();


        if (args.length == 0) {
            System.out.println("Please provide a url: ");
            Scanner scanner = new Scanner(System.in);
            String consolePath = scanner.nextLine();
            if (consolePath != null) {
                filePath = consolePath;
            }
        } else {
            filePath = args[0];
        }
        if (filePath != null && filePath.length() != 0) {
            Scanner in = null;
            File file = null;
            try {
                file = new File(filePath);
                in = new Scanner(new FileReader(file));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (in == null)
                System.out.println("File not found.");
            else {
                initValues(videos, endpointHashMap, cacheHashMap, in);
                for (Video video : videos.values()) {
                    if (video.getRequestList().isEmpty()) {
                        continue;
                    }
                    if (video.getSize() > cacheSize) {
                        continue;
                    }

                    for (Request request : video.getRequestList()) {
                        for (Latency latency : request.getEndpoint().getLatencyList()) {
                            Integer integer = saveTime.get(latency.idCache);
                            saveTime.put(latency.idCache, (integer != null ? integer : 0) +
                                    (request.getEndpoint().getLatency() * request.getAmount()) - (request.getAmount() * latency.value));

                        }

                    }

                    saveTime = MapUtil.sortByValue(saveTime);
                    if (!saveTime.isEmpty()) {
                        Iterator<Map.Entry<Integer, Integer>> it = saveTime.entrySet().iterator();
                        boolean inserted = false;
                        do {
                            HashMap.Entry<Integer, Integer> ent = it.next();
                            Cache cache = cacheHashMap.get(ent.getKey());
                            if (cache != null && cache.getSizeAvailable() >= video.getSize()) {
                                cache.addIdsVideo(video.getId());
                                cache.setSizeAvailable(cache.getSizeAvailable() - video.getSize());
                                inserted = true;
                            }
                        } while (it.hasNext() && !inserted);
                    }
                    saveTime = new HashMap<>();
                }

                int count = 0;
                StringBuilder sb = new StringBuilder();

                for (Cache c : cacheHashMap.values()) {
                    if (c.getIdsVideo().size() > 0) {
                        count++;
                        StringBuilder videoBuffer = new StringBuilder();
                        for (int i : c.getIdsVideo()) {
                            videoBuffer.append(i).append(" ");
                        }
                        sb.append(c.getId()).append(" ").append(videoBuffer.toString()).append("\n");
                    }
                }
                Path path = Paths.get(file.getName().replaceFirst("[.][^.]+$", "") + ".out");
                try {
                    Files.write(path, (count + "\n" + sb.toString()).getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void initValues(HashMap<Integer, Video> videos, HashMap<Integer, Endpoint> endpointHashMap, HashMap<Integer, Cache> cacheHashMap, Scanner in) {
        int video = in.nextInt();
        int endpoint = in.nextInt();
        int request = in.nextInt();
        int cache = in.nextInt();
        cacheSize = in.nextInt();

        in.nextLine();

        for (int i = 0; i < cache; i++) {
            cacheHashMap.put(i, new Cache(i, cacheSize));
        }
        for (int i = 0; i < video; i++) {
            videos.put(i, new Video(i, in.nextInt()));
        }
        in.nextLine();
        for (int i = 0; i < endpoint; i++) {
            Endpoint ePoint = new Endpoint(i, in.nextInt());
            int caches = in.nextInt();
            in.nextLine();
            for (int j = 0; j < caches; j++) {
                ePoint.addLatencyList(new Latency(i, in.nextInt(), in.nextInt()));
                in.nextLine();
            }
            endpointHashMap.put(i, ePoint);
        }
        for (int i = 0; i < request; i++) {
            int idVideo = in.nextInt();
            int idEndpoint = in.nextInt();
            int value = in.nextInt();
            Request request1 = new Request(value, idVideo, endpointHashMap.get(idEndpoint));
            videos.get(idVideo).addRequestList(request1);
            if (in.hasNext()) in.nextLine();
        }
    }
}
