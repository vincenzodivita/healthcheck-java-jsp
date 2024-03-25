package it.contrader.servlets;

import it.contrader.dto.UserDTO;
import it.contrader.enums.Usertype;
import it.contrader.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private LoginService service;

    @Override
    public void init() {
        this.service = new LoginService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Usertype usertype = Usertype.valueOf(request.getParameter("usertype"));
        UserDTO userDTO = service.register(username, password, usertype);

        if (userDTO != null) {
            request.getSession().setAttribute("user", userDTO);

            redirectToPage(request, response, "/registry/insertregistry.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            redirectToPage(request, response, "/index.jsp");
        }
    }

    private void redirectToPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
