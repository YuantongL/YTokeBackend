
package myapp.Data.VideoSearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvidiousVideo {
	
	public String title;
	public String videoId;
	public InvidiousVideoThumbnails[] videoThumbnails;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class InvidiousVideoThumbnails {
		public String quality;
		public String url;
	}
}
