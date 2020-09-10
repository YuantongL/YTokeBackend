
package myapp;

import java.lang.Exception;

public final class StandardVideosRepository implements VideosRepository {
    
    private VideoStatsDataProvider videoStatsDataProvider;

    public StandardVideosRepository(VideoStatsDataProvider videoStatsDataProvider) {
        this.videoStatsDataProvider = videoStatsDataProvider;
    }

	public Video[] search(String query) {
        try {
            VideoStats stats = videoStatsDataProvider.get("123");
            return new Video[] {new Video("123", "456", stats)};
        } catch(Exception e) {
            System.out.println(e);
        }
        return new Video[] {};
	}
}