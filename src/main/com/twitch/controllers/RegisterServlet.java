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

public class RegisterServlet extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) != null && req.getSession(false).getAttribute("logged_id") != null) {
            resp.sendRedirect("index");
        }
        else
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String URI = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/'));

        if (URI.equals("/registerSubmit")) { RegisterStart(URI, req, resp); }
    }

    protected void RegisterStart(String URI, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        tmpUser tUser = tempUser(req);

        if (tUser.getPassword().equals(tUser.getCpassword())) {
            if (!checkDuplicate(tUser.getUsername())) {
                users.add(new User(tUser.getUsername(), tUser.getPassword()));
                SendJsonResult(new JsonString("Account successfully created!"), resp);
            }
            else {
                SendJsonResult(new JsonString("Account already exists!"), resp);
            }
        }
        else {
            SendJsonResult(new JsonString("Passwords do not match!"), resp);
        }
    }

    protected void SendJsonResult(JsonString result, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(gson.toJson(result)); //Return result back to page
        out.flush();
    }

    protected boolean checkDuplicate(String username) {
        for (int i = 0; i < users.size(); i++) {
            User tmpUser = users.get(i);
            if (username.equals(tmpUser.getUsername()))
                return true;
        }
        return false;
    }

    protected tmpUser tempUser(HttpServletRequest req) throws IOException {
        tmpUser tempUsr = new Gson().fromJson(req.getReader(), tmpUser.class);
        return tempUsr;
    }
}
