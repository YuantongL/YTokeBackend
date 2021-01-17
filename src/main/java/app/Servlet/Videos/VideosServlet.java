
package app.Servlet.Videos;

import app.Data.VideoSearch.InvidiousVideoSearchDataProvider;
import app.Data.VideoStats.StandardVideoStatsDataProvider;
import app.Repository.Videos.StandardVideosRepository;
import app.Repository.Videos.Video;
import app.Repository.Videos.VideosRepository;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class VideosServlet extends HttpServlet {

	private final VideosRepository videosRepository = new StandardVideosRepository(new StandardVideoStatsDataProvider(),
            new InvidiousVideoSearchDataProvider());

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        if (!req.getHeader("User-Agent").contains("YToke~")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        final String query = req.getParameter("q");

        int page = 0;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (final Exception e) {
        }

        try {
            final List<Video> videoResult = videosRepository.search(query, page);
            final VideosServletResult result = new VideosServletResult(query, videoResult);
            final ObjectMapper mapper = new ObjectMapper();
            final String json = mapper.writeValueAsString(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(json);
            resp.getWriter().flush();
        } catch (final NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (final Exception e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}
}