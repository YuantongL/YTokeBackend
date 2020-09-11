
package myapp.Servlet.Stats;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myapp.Data.VideoStats.StandardVideoStatsDataProvider;
import myapp.Repository.Tracking.Stats.StandardVideoStatsRepository;
import myapp.Repository.Tracking.Stats.VideoStatsRepository;

public final class VideoImpressionServlet extends HttpServlet {
	
	private VideoStatsRepository videoStatsRepository = new StandardVideoStatsRepository(new StandardVideoStatsDataProvider());
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String videoId = req.getParameter("videoId");
		String percentage = req.getParameter("percentage");
		if (videoId.isEmpty() || percentage.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			try {
				Float percentageFloat = Float.parseFloat(percentage);
				videoStatsRepository.reportVideoPlayed(videoId, percentageFloat);
				resp.setContentType("text/html");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().print("\"Success\": true");
				resp.getWriter().flush();
			} catch (NumberFormatException e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			} catch (Exception e) {
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
		}
	}

}