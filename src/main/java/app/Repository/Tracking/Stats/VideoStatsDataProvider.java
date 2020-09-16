
package app.Repository.Tracking.Stats;

import java.util.List;

import app.Repository.Videos.Video;
import app.Repository.Videos.VideoStats;

public interface VideoStatsDataProvider {

	/// Calling this function will add stats to the video array
	public List<Video> fetchStats(final List<Video> videos) throws Exception;

	/// Fetches stats of a video by giving id
	public VideoStats get(final String id) throws Exception;

	/// Updates video stats, if there is no such video, it will create a new record
	public Void save(final String id, final VideoStats stats);

}