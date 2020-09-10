
package myapp;

public final class StandardVideoStatsRepository implements VideoStatsRepository {

    private VideoStatsDataProvider dataProvider;

    public StandardVideoStatsRepository(VideoStatsDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Void reportVideoPlayed(String id, Float percentage) {
        // TODO: need to handle no such element
        VideoStats existingStats = dataProvider.get(id);
        existingStats.timesPlayed++;
        if (percentage > 0.6) {
            existingStats.timesFinished++;
        }
        dataProvider.save(id, existingStats);
    }

    public Void reportVideoMarked(String id, VideoTag tag) {
        // TODO: need to handle no such element
        VideoStats existingStats = dataProvider.get(id);
        switch (tag) {
            case VideoTag.OFF_VOCAL:
                existingStats.timesMarkedOffVocal++;
                dataProvider.save(id, existingStats);
                break;
            case VideoTag.WITH_VOCAL:
                existingStats.timesMarkedWithVocal++;
                dataProvider.save(id, existingStats);
                break;
            default:
                return;
        }
    }

}