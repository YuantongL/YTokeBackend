
package app.Data.VideoStats;

public class VideoStatsDataProviderNoSuchVideoException extends Exception {
	public VideoStatsDataProviderNoSuchVideoException(final String errorMessage) {
		super(errorMessage);
	}
}
