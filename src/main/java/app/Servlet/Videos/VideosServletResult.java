
package app.Servlet.Videos;

import java.util.List;

import app.Repository.Videos.*;

public final class VideosServletResult {
    
    public String q;
    public List<Video> videos;

    VideosServletResult(String q, List<Video> videos) {
        this.q = q;
        this.videos = videos;
    }
}