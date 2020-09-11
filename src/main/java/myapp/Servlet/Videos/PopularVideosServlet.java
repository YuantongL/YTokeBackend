

package myapp.Servlet.Videos;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class PopularVideosServlet extends HttpServlet {
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().print("\"Something\": { \"PopularVideosServletReceived\" : \"true\" }");
    resp.getWriter().flush();
  }

}