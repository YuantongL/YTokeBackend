
package app.Repository.Videos;

import java.util.List;

public interface VideoSearchDataProvider {
    public List<Video> search(String query, int page) throws Exception;
}