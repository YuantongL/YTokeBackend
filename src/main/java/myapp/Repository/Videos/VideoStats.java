
package myapp.Repository.Videos;
import myapp.Repository.Tracking.Stats.VideoTag;

public final class VideoStats {
    
    public long timesPlayed;
    public long timesFinished;
    public long timesMarkedWithVocal;
    public long timesMarkedOffVocal;

    public VideoStats(long timesPlayed, long timesFinished, long timesMarkedOffVocal, long timesMarkedWithVocal) {
        this.timesPlayed = timesPlayed;
        this.timesFinished = timesFinished;
        this.timesMarkedOffVocal = timesMarkedOffVocal;
        this.timesMarkedWithVocal = timesMarkedWithVocal;
    }

    public VideoTag[] tags() {
        if (timesMarkedWithVocal > timesMarkedOffVocal) {
            return new VideoTag[] { VideoTag.WITH_VOCAL };
        } else {
            return new VideoTag[] { VideoTag.OFF_VOCAL };
        }
    } 
}