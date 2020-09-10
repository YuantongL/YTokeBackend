
package myapp;

public interface VideoStatsDataProvider {
    public VideoStats get(String id) throws VideoStatsDataProviderException;
    public Void save(String id, VideoStats stats);
}