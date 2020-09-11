
package myapp.Data.VideoSearch;

import myapp.Repository.Videos.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;

public final class InvidiousVideoSearchDataProvider implements VideoSearchDataProvider {
	
	private OkHttpClient client = new OkHttpClient();
	private ObjectMapper objectMapper = new ObjectMapper();
	
    public List<Video> search(String query, int page) throws Exception {
    	if (query.isEmpty()) {
    		return new ArrayList<Video>();
    	}
    	
    	String url = String.format("https://www.invidio.us/api/v1/search?q=%s&page=%d", query, page);
    	System.out.println(String.format("Requesting url %s", url));
    	Request request = new Request.Builder()
    		.url(url)
    		.method("GET", null)
    		.build();
    	Response response = client.newCall(request).execute();
    	String responseString = response.body().string();
    	ArrayList<InvidiousVideo> invidiousResponse = objectMapper.readValue(responseString, new TypeReference<List<InvidiousVideo>>() {});	
    	System.out.println(invidiousResponse);
        List<Video> result = convertFrom(invidiousResponse);
        System.out.println(result);
        return result;
    }
    
    private List<Video> convertFrom(ArrayList<InvidiousVideo> response) {
    	List<Video> videoList = new ArrayList<>();
    	
    	for (InvidiousVideo invidiousVideo: response) {
    		Video newVideo = new Video(invidiousVideo.videoId, invidiousVideo.title);
    		List<Video.VideoThumbnails> thumbnails = new ArrayList<>();
    		for (InvidiousVideo.InvidiousVideoThumbnails invidiousThumbnail: invidiousVideo.videoThumbnails) {
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