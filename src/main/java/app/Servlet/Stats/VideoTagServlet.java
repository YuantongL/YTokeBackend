
package app.Servlet.Stats;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.Data.VideoStats.StandardVideoStatsDataProvider;
import app.Repository.Tracking.Stats.StandardVideoStatsRepository;
import app.Repository.Tracking.Stats.VideoStatsRepository;
import app.Repository.Tracking.Stats.VideoTag;

public final class VideoTagServlet extends HttpServlet {
	
	private VideoStatsRepository videoStatsRepository = new StandardVideoStatsRepository(new StandardVideoStatsDataProvider());
  
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String videoId = req.getParameter("videoId");
		String tag = req.getParameter("tag");
		if (videoId.isEmpty() || tag.isEmpty()) {
			  resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		  } else {
			  try {
				  videoStatsRepository.reportVideoMarked(videoId, VideoTag.valueOf(tag));
				  resp.setContentType("text/html");
				  resp.setCharacterEncoding("UTF-8");
				  resp.getWriter().print("\"Success\": true");
				  resp.getWriter().flush();
			  } catch (IllegalArgumentException e) {
				  e.printStackTrace();
				  resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			  }
		  }
	  }

}