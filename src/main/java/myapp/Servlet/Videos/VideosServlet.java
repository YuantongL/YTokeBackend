
package myapp.Servlet.Videos;

import myapp.Data.VideoSearch.InvidiousVideoSearchDataProvider;
import myapp.Data.VideoStats.StandardVideoStatsDataProvider;
import myapp.Repository.Videos.StandardVideosRepository;
import myapp.Repository.Videos.Video;
import myapp.Repository.Videos.VideosRepository;
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
    	
    	System.out.println(String.format("Received search video call with p %s, page %s", query, page));

        try {
        	
        	List<Video> videoResult = videosRepository.search(query, page);
            VideosServletResult result = new VideosServletResult("query", videoResult);
            ObjectMapper mapper = new ObjectMapper();
            
            String json = mapper.writeValueAsString(result);
            System.out.println("ResultingJSONstring = " + json);
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