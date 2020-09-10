
package myapp;

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
            return new VideoTag[] { VideoTag.withVocal };
        } else {
            return new VideoTag[] { VideoTag.offVocal };
        } else {
            return new VideoTag[] {};
        }
    } 
}