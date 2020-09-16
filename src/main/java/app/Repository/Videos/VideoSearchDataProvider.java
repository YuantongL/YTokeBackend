
package app.Repository.Videos;

import java.util.List;

public interface VideoSearchDataProvider {
	public List<Video> search(final String query, final int page) throws Exception;
}