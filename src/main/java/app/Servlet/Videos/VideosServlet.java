
package app.Servlet.Videos;

import app.Data.VideoSearch.InvidiousVideoSearchDataProvider;
import app.Data.VideoStats.StandardVideoStatsDataProvider;
import app.Repository.Videos.StandardVideosRepository;
import app.Repository.Videos.Video;
import app.Repository.Videos.VideosRepository;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class VideosServlet extends HttpServlet {

    private VideosRepository videosRepository = new StandardVideosRepository(new StandardVideoStatsDataProvider(), 
    																		 new InvidiousVideoSearchDataProvider());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
    	String query = req.getParameter("q");
    	
    	int page = 0;
    	try {
    		page = Integer.parseInt(req.getParameter("page"));
    	} catch(Exception e) {}
    	
        try {
        	List<Video> videoResult = videosRepository.search(query, page);
            VideosServletResult result = new VideosServletResult("query", videoResult);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(json);
            resp.getWriter().flush();
        } catch (NumberFormatException e) {
        	e.printStackTrace();
        	resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}