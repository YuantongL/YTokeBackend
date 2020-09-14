
package app.Repository.Tracking.Stats;

import app.Data.VideoStats.VideoStatsDataProviderNoSuchVideoException;
import app.Repository.Videos.VideoStats;

public final class StandardVideoStatsRepository implements VideoStatsRepository {

    private VideoStatsDataProvider dataProvider;

    public StandardVideoStatsRepository(VideoStatsDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Void reportVideoPlayed(String id, Float percentage) {
    	try {
    		VideoStats existingStats = dataProvider.get(id);
    		existingStats.timesPlayed++;
            if (percentage > 0.6) {
                existingStats.timesFinished++;
            }
            dataProvider.save(id, existingStats);
    	} catch (VideoStatsDataProviderNoSuchVideoException e) {
    		if (percentage > 0.6) {
    			dataProvider.save(id, new VideoStats(1, 1, 1, 0));
    		} else {
    			dataProvider.save(id, new VideoStats(1, 0, 1, 0));
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
		return null;   
    }

    public Void reportVideoMarked(String id, VideoTag tag) {
		try {
			VideoStats existingStats = dataProvider.get(id);
			switch (tag) {
            case OFF_VOCAL:
                existingStats.timesMarkedOffVocal++;
                dataProvider.save(id, existingStats);
                break;
            case WITH_VOCAL:
                existingStats.timesMarkedWithVocal++;
                dataProvider.save(id, existingStats);
                break;
            default:
                return null;
			}
		} catch (VideoStatsDataProviderNoSuchVideoException e) {
			switch (tag) {
            case OFF_VOCAL:
    			dataProvider.save(id, new VideoStats(0, 0, 1, 0));
                break;
            case WITH_VOCAL:
    			dataProvider.save(id, new VideoStats(0, 0, 0, 1));
                break;
            default:
                return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    }

}