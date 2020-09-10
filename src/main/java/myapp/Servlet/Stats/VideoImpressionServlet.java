package myapp;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class VideoImpressionServlet extends HttpServlet {
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      // TODO: get percentage
      resp.setContentType("text/html");
      resp.setCharacterEncoding("UTF-8");
      resp.getWriter().print("\"Success\": {\"PercentageServletReceived\": \"true\"}");
      resp.getWriter().flush();
  }

}