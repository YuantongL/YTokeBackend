
package app.Repository.Videos;

import java.util.List;

public interface VideosRepository {
    public List<Video> search(String query, int page) throws Exception;
}