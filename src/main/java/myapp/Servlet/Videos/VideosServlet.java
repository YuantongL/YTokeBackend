
package myapp;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class VideosServlet extends HttpServlet {

    private VideosRepository videosRepository = new StandardVideosRepository(new StandardVideoStatsDataProvider());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: get params
        
        Video[] videoResult = videosRepository.search("query");
        VideosServletResult result = new VideosServletResult("query", videoResult);
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(result);
            System.out.println("ResultingJSONstring = " + json);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(json);
            resp.getWriter().flush();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}