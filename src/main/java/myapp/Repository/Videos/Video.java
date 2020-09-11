
package myapp.Repository.Videos;

import java.util.List;

public final class Video {
    
    public String id;
    public String name;
    public VideoStats stats;
    public List<VideoThumbnails> thumbnails; 

    public class VideoThumbnails {
    	public String quality;
    	public String url;
    }
    
    public Video(String id, String name) {
        this.id = id;
        this.name = name;
    }
}