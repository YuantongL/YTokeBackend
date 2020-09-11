
package myapp.Repository.Tracking.Stats;

import java.util.List;

import myapp.Repository.Videos.Video;
import myapp.Repository.Videos.VideoStats;

public interface VideoStatsDataProvider {

	/// Calling this function will add stats to the video array
    public List<Video> fetchStats(List<Video> videos) throws Exception;
    
    /// Fetches stats of a video by giving id
    public VideoStats get(String id) throws Exception;
    
    /// Updates video stats, if there is no such video, it will create a new record
    public Void save(String id, VideoStats stats);
    
}