
package myapp.Repository.Tracking.Stats;

public interface VideoStatsRepository {
    
    /// Report that a video is played with certain percentage on a client app.
    public Void reportVideoPlayed(String id, Float percentage);

    /// Report that a video is marked with a tag from client.
    public Void reportVideoMarked(String id, VideoTag tag);

}