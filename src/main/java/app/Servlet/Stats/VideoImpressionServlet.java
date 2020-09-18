
package app.Servlet.Stats;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.Data.VideoStats.StandardVideoStatsDataProvider;
import app.Repository.Tracking.Stats.StandardVideoStatsRepository;
import app.Repository.Tracking.Stats.VideoStatsRepository;

public final class VideoImpressionServlet extends HttpServlet {

	private VideoStatsRepository videoStatsRepository = new StandardVideoStatsRepository(
			new StandardVideoStatsDataProvider());

	@Override
	public void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		if (!req.getHeader("User-Agent").contains("YToke~")) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
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