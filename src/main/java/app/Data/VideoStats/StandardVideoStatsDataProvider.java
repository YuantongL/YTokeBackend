
package app.Data.VideoStats;

import app.Repository.Tracking.Stats.VideoStatsDataProvider;
import app.Repository.Videos.Video;
import app.Repository.Videos.VideoStats;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

public final class StandardVideoStatsDataProvider implements VideoStatsDataProvider {

    private final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    private final KeyFactory keyFactory = datastore.newKeyFactory().setKind("VideoStats");
    
    public VideoStats get(String id) throws Exception {
    	Key taskKey = keyFactory.newKey(id);
    	Entity entity = datastore.get(taskKey);
    	if (entity == null || !entity.getString("videoId").equals(id)) {
    		throw new VideoStatsDataProviderNoSuchVideoException("Did not find video stats with given id");
    	}
    	
    	return new VideoStats(entity.getLong("timesPlayed"), 
    						  entity.getLong("timesFinished"),
    						  entity.getLong("timesMarkedOffVocal"),
    						  entity.getLong("timesMarkedWithVocal"));
    }
    
    public List<Video> fetchStats(List<Video> videos) throws Exception {
        List<Key> keysToLookup = new ArrayList<>();
        for (Video video: videos) {
        	keysToLookup.add(keyFactory.newKey(video.id));
        }
        
        Iterator<Entity> entitiesIterator = datastore.get(keysToLookup);
        Map<String, VideoStats> idToStats = convertTasks(entitiesIterator);
        
        List<Video> result = new ArrayList<>();
        for (Video video: videos) {
        	video.stats = idToStats.get(video.id);
        	result.add(video);
        }
        return result;
        
    }
    
    public Void save(String id, VideoStats stats) {
        Key key = keyFactory.newKey(id);
        Entity task = Entity.newBuilder(key)
            .set("timesPlayed", stats.timesPlayed)
            .set("timesFinished", stats.timesFinished)
            .set("videoId", id)
            .set("timesMarkedOffVocal", stats.timesMarkedOffVocal)
            .set("timesMarkedWithVocal", stats.timesMarkedWithVocal)
            .build();
        datastore.put(task);
        return null;
    }

    private Map<String, VideoStats> convertTasks(Iterator<Entity> entities) {
    	Map<String, VideoStats> result = new HashMap<String, VideoStats>();
        while (entities.hasNext()) {
            Entity entity = entities.next();
            result.put(entity.getString("videoId"),
            		   new VideoStats(entity.getLong("timesPlayed"), 
            				     	  entity.getLong("timesFinished"),
            				     	  entity.getLong("timesMarkedOffVocal"),
            				     	  entity.getLong("timesMarkedWithVocal")));
        }
        return result;
    }
}
