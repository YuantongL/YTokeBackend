
package app.Repository.Videos;

import java.util.List;

public interface VideosRepository {
	public List<Video> search(final String query, final int page) throws Exception;
}