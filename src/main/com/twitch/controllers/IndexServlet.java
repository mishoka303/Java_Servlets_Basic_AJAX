package twitch.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static twitch.controllers.MyContextListener.users;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) != null  && req.getSession(false).getAttribute("logged_id") != null) {
            int index = (int) req.getSession().getAttribute("logged_id");
            req.setAttribute("userName", "Welcome, " + users.get(index).getUsername() + " to my project! <a href=\"logout\">Log Out</a>");
        }
        if (req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/')).equals("/logout")) {
            req.getSession().invalidate();
            resp.sendRedirect("index");
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
