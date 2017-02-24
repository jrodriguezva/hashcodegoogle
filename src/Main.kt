import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*


fun main(args: Array<String>) {
    var filePath: String = ""
    val videos = mutableListOf<Video>()
    val endpointHashMap = mutableListOf<Endpoint>()
    val cacheHashMap = mutableListOf<Cache>()
    val saveTime = mutableListOf<SaveTime>()


    if (args.isEmpty()) {
        println("Please provide a url: ")
        val scanner = Scanner(System.`in`)
        val consolePath = scanner.nextLine()
        if (consolePath != null) {
            filePath = consolePath
        }
    } else {
        filePath = args[0]
    }
    if (filePath.isNotEmpty()) {
        var scanner: Scanner? = null
        var file: File? = null
        try {
            file = File(filePath)
            scanner = Scanner(FileReader(file))

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        if (scanner == null)
            println("File not found.")
        else {
            val cacheSize = initValues(videos, endpointHashMap, cacheHashMap, scanner)

            val videosOrdered = videos.sortedByDescending { it.size }.sortedByDescending { it.getRequestList().size }

            for (video in videosOrdered) {
                if (video.getRequestList().isEmpty()) {
                    continue
                }

                if (video.size > cacheSize) {
                    continue
                }

                for (request in video.getRequestList()) {
                    for (latency in request.endpoint.getLatencyList()) {
                        val save = saveTime.find { it.idCache == latency.idCache }
                        val size = (request.amount * request.endpoint.latency) - (request.amount * latency.value)
                        if (save != null) {
                            saveTime.remove(save)
                            save.size.plus(size)
                            saveTime.add(save)
                        } else {
                            saveTime.add(SaveTime(latency.idCache, size))
                        }
                    }

                }

                if (!saveTime.isEmpty()) {
                    val saveTimeOrder = saveTime.sortedByDescending { it.size }
                    for (saves in saveTimeOrder) {
                        val cache = cacheHashMap[saves.idCache]
                        if (cache.getSizeAvailable() >= video.size) {
                            cache.addIdsVideo(video.id)
                            cache.setSizeAvailable(cache.getSizeAvailable() - video.size)
                            break
                        }
                    }
                }
            }

            var count = 0
            val sb = StringBuilder()

            for (c in cacheHashMap) {
                if (c.getIdsVideo().size > 0) {
                    count++
                    val videoBuffer = StringBuilder()
                    for (i in c.getIdsVideo()) {
                        videoBuffer.append(i).append(" ")
                    }
                    sb.append(c.getId()).append(" ").append(videoBuffer.toString()).append("\n")
                }
            }
            val path = Paths.get(file!!.name.replaceFirst("[.][^.]+$".toRegex(), "") + ".out")
            try {
                Files.write(path, (count.toString() + "\n" + sb.toString()).toByteArray())
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

}


private fun initValues(videos: MutableList<Video>, endpointHashMap: MutableList<Endpoint>, cacheHashMap: MutableList<Cache>, scanner: Scanner): Int {
    val video = scanner.nextInt()
    val endpoint = scanner.nextInt()
    val request = scanner.nextInt()
    val cache = scanner.nextInt()
    val cacheSize = scanner.nextInt()

    scanner.nextLine()

    (0..cache - 1).mapTo(cacheHashMap) { Cache(it, cacheSize) }
    (0..video - 1).mapTo(videos) { Video(it, scanner.nextInt()) }
    scanner.nextLine()
    for (i in 0..endpoint - 1) {
        val ePoint = Endpoint(i, scanner.nextInt())
        val caches = scanner.nextInt()
        scanner.nextLine()
        for (j in 0..caches - 1) {
            ePoint.addLatencyList(Latency(i, scanner.nextInt(), scanner.nextInt()))
            scanner.nextLine()
        }
        endpointHashMap.add(ePoint)
    }
    for (i in 0..request - 1) {
        val idVideo = scanner.nextInt()
        val idEndpoint = scanner.nextInt()
        val value = scanner.nextInt()
        val request1 = Request(value, idVideo, endpointHashMap[idEndpoint])
        videos[idVideo].addRequestList(request1)
        if (scanner.hasNext()) scanner.nextLine()
    }
    return cacheSize
}