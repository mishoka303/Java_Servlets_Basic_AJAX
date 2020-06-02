package twitch.controllers;

import com.google.gson.Gson;
import twitch.models.JsonString;
import twitch.models.User;
import twitch.models.tmpUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static twitch.controllers.MyContextListener.users;

public class LoginServlet extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) != null && req.getSession(false).getAttribute("logged_id") != null) {
            resp.sendRedirect("index");
        }
        else
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String URI = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/'));

        if (URI.equals("/loginSubmit")) { LoginStart(URI, req, resp); }
    }

    protected void LoginStart(String URI, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User tmpUser = new Gson().fromJson(req.getReader(), User.class);

        int index = checkDuplicate(tmpUser.getUsername(),tmpUser.getPassword());
        if (index == -1) {
            SendJsonResult(new JsonString("Invalid login!"), resp);
        }
        else {
            req.getSession().setAttribute("logged_id", index);
            SendJsonResult(new JsonString("Success - created session with username " + tmpUser.getUsername() + "!"), resp);
        }



    }

    protected int checkDuplicate(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            User tmpUser = users.get(i);
            if (username.equals(tmpUser.getUsername()) && password.equals(tmpUser.getPassword()))
                return i;
        }
        return -1;
    }

    protected void SendJsonResult(JsonString result, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(gson.toJson(result)); //Return result back to page
        out.flush();
    }
}
