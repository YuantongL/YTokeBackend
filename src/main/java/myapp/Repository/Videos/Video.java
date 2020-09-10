
package myapp;

public final class Video {
    
    public String id;
    public String name;
    public VideoStats stats;

    public Video(String id, String name, VideoStats stats) {
        this.id = id;
        this.name = name;
        this.stats = stats;
    }
}