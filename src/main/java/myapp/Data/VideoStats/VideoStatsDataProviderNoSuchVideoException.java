
package myapp.Data.VideoStats;

public class VideoStatsDataProviderNoSuchVideoException extends Exception {
	public VideoStatsDataProviderNoSuchVideoException(String errorMessage) {
        super(errorMessage);
    }
}
