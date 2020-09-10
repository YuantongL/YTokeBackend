
package myapp;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.Value;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

public final class StandardVideoStatsDataProvider implements VideoStatsDataProvider {

    private final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    private final KeyFactory keyFactory = datastore.newKeyFactory().setKind("VideoStats");
    
    public VideoStats get(String id) throws VideoStatsDataProviderException {
        Query<Entity> query = Query.newEntityQueryBuilder()
            .setKind("VideoStats")
            .setFilter(CompositeFilter.and(PropertyFilter.eq("videoId", id)))
            .build();

        QueryResults<Entity> tasks = datastore.run(query);

        return convertTasks(tasks);
    }

    public Void save(String id, VideoStats stats) {
        Key key = datastore.allocateId(keyFactory.newKey());
        Entity task = Entity.newBuilder(key)
            .set("timesPlayed", stats.timesPlayed)
            .set("timesFinished", stats.timesFinished)
            .set("videoId", id)
            .set("timesMarkedOffVocal", stats.timesMarkedOffVocal)
            .set("timesMarkedWithVocal", stats.timesMarkedWithVocal)
            .build();
        datastore.upsert(task);
        return key;
    }

    private VideoStats convertTasks(Iterator<Entity> tasks) throws VideoStatsDataProviderException {
        while (tasks.hasNext()) {
            Entity task = tasks.next();
            System.out.println(task);
            try {
                return new VideoStats(task.getLong("timesPlayed"), 
                                      task.getLong("timesFinished"),
                                      task.getLong("timesMarkedOffVocal"),
                                      task.getLong("timesMarkedWithVocal"));
            } catch(Exception e) {
                throw new VideoStatsDataProviderException("Can not unwarp value");
            }   
        }
        System.out.println("Task is empty");
        throw new VideoStatsDataProviderException("Task is empty");
    }
}
