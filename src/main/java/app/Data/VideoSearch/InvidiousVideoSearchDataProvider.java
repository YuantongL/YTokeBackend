
package app.Data.VideoSearch;

import app.Repository.Videos.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;

public final class InvidiousVideoSearchDataProvider implements VideoSearchDataProvider {

	private OkHttpClient client = new OkHttpClient();
	private ObjectMapper objectMapper = new ObjectMapper();

	public List<Video> search(final String query, final int page) throws Exception {
		if (query.isEmpty()) {
			return new ArrayList<Video>();
		}

		String url = String.format("https://invidious.snopyta.org/api/v1/search?q=%s ktv&page=%d", query, page);
		Request request = new Request.Builder()
				.url(url)
				.method("GET", null)
				.build();
		Response response = client.newCall(request).execute();
		String responseString = response.body().string();
		List<InvidiousVideo> invidiousResponse = objectMapper.readValue(responseString,
				new TypeReference<List<InvidiousVideo>>() {
				});
		List<Video> result = convertFrom(invidiousResponse);
		return result;
	}

	private List<Video> convertFrom(final List<InvidiousVideo> response) {
		List<Video> videoList = new ArrayList<>();

		for (InvidiousVideo invidiousVideo : response) {
			Video newVideo = new Video(invidiousVideo.videoId, invidiousVideo.title);
			List<Video.VideoThumbnails> thumbnails = new ArrayList<>();
			for (InvidiousVideo.InvidiousVideoThumbnails invidiousThumbnail : invidiousVideo.videoThumbnails) {
				Video.VideoThumbnails newThumbnail = newVideo.new VideoThumbnails();
				newThumbnail.quality = invidiousThumbnail.quality;
				newThumbnail.url = invidiousThumbnail.url;
				thumbnails.add(newThumbnail);
			}
			newVideo.thumbnails = thumbnails;
			videoList.add(newVideo);
		}

		return videoList;
	}
}