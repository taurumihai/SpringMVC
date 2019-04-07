package servlet;

import launch.DataBaseConnection;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    DataBaseConnection connection = new DataBaseConnection();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Map<String, Object> modelMap = new HashMap<>();
        String username = request.getParameter("userName");

        String password = request.getParameter("password");

        if (connection.checkUserByUserName(username, password)){

            modelMap.put("userName", username);
            response.sendRedirect("welcome.jsp");

        }
        else
        {
            System.out.println("Username or password incorrect");
            response.sendRedirect("error.jsp");
        }
    }
}
