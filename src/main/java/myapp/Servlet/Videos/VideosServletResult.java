
package myapp;

public final class VideosServletResult {
    
    public String q;
    public Video[] videos;

    VideosServletResult(String q, Video[] videos) {
        this.q = q;
        this.videos = videos;
    }
}