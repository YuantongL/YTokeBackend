
package app.Repository.Videos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.Repository.Tracking.Stats.VideoTag;

public final class VideoStats {

	public long timesPlayed;
	public long timesFinished;
	public long timesMarkedWithVocal;
	public long timesMarkedOffVocal;

	public VideoStats(final long timesPlayed, 
			final long timesFinished,
			final long timesMarkedOffVocal,
			final long timesMarkedWithVocal) {
		this.timesPlayed = timesPlayed;
		this.timesFinished = timesFinished;
		this.timesMarkedOffVocal = timesMarkedOffVocal;
		this.timesMarkedWithVocal = timesMarkedWithVocal;
	}

	public List<VideoTag> tags() {
		if (timesMarkedWithVocal > timesMarkedOffVocal) {
			return new ArrayList<>(Arrays.asList(VideoTag.WITH_VOCAL));
		} else if (timesMarkedWithVocal < timesMarkedOffVocal) {
			return new ArrayList<>(Arrays.asList(VideoTag.OFF_VOCAL));
		} else {
            return new ArrayList<>();
        }
	}

	public Float percentageFinished() {
		if (timesPlayed == 0 || timesFinished == 0) {
			return null;
		}
		return (float) timesFinished / (float) timesPlayed;
	}
}