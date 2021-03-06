
package app.Repository.Videos;

import java.lang.Exception;
import java.util.List;

import app.Repository.Tracking.Stats.VideoStatsDataProvider;

public final class StandardVideosRepository implements VideosRepository {

	private VideoStatsDataProvider videoStatsDataProvider;
	private VideoSearchDataProvider videoSearchDataProvider;

	public StandardVideosRepository(final VideoStatsDataProvider videoStatsDataProvider,
			final VideoSearchDataProvider videoSearchDataProvider) {
		this.videoStatsDataProvider = videoStatsDataProvider;
		this.videoSearchDataProvider = videoSearchDataProvider;
	}

	public List<Video> search(final String query, final int page) throws Exception {
		List<Video> videos = videoSearchDataProvider.search(query, page);
		return videoStatsDataProvider.fetchStats(videos);
	}
}