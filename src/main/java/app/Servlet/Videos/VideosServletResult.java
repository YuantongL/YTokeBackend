
package app.Servlet.Videos;

import java.util.List;
import java.util.stream.Collectors;

import app.Repository.Videos.Video.VideoThumbnails;

public final class VideosServletResult {

	public String q;
	public List<VideosServletResult.Video> videos;

	VideosServletResult(final String q, final List<app.Repository.Videos.Video> videos) {
		this.q = q;
		this.videos = videos.stream().map(video -> new Video(video)).collect(Collectors.toList());
	}

	public final class Video {

		public String title;
		public String videoId;
		public List<String> tags;
		public Float percentageFinished;
		public List<VideoThumbnails> thumbnails;

		Video(final app.Repository.Videos.Video video) {
			this.title = video.name;
			this.videoId = video.id;
			if (video.stats != null) {
				this.tags = video.stats.tags().stream().map(tag -> tag.getStringValue()).collect(Collectors.toList());
				this.percentageFinished = video.stats.percentageFinished();
			}
			this.thumbnails = video.thumbnails;
		}
	}
}