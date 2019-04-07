package servlet;


import org.springframework.ui.Model;

import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "WelcomeServlet",
        urlPatterns = {"/welcome"}
)
public class WelcomeServlet {

    public String VIEW(String username, String password, Model model){

        String ceva ="ceva";



        return "welcome.jsp";
    }
}
