package it.contrader.servlets;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.contrader.dto.RegistryDTO;
import it.contrader.enums.Usertype;
import it.contrader.dto.UserDTO;
import it.contrader.service.RegistryService;
import it.contrader.service.UserService;

/**
 * This servlet class handles various CRUD operations for users.
 * It provides functionality to read, create, update, and delete users.
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** Service used for user-related operations. */
	private UserService service;
	private RegistryService serviceregistry;

	/**
	 * Initializes the servlet and creates a new instance of the UserService.
	 */
	@Override
	public void init() {
		this.service = new UserService();
		this.serviceregistry = new RegistryService();
	}

	/**
	 * Handles GET requests. It provides functionality based on the "mode" parameter.
	 *
	 * @param request the HttpServletRequest object containing client request data
	 * @param response the HttpServletResponse object to send data to the client
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO userSession = (UserDTO) request.getSession().getAttribute("user");
		String mode = request.getParameter("mode");
		if(mode != null) {
			switch (mode.toUpperCase()) {
				case "GETALL":
					updateList(request);
					request.getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
					break;
				case "READ":
					int idUserToRead = Integer.parseInt(request.getParameter("id"));
					UserDTO user = service.read(idUserToRead);
					request.setAttribute("user", user);
					RegistryDTO registry = serviceregistry.read(idUserToRead);
					request.setAttribute("registry", registry);
					getServletContext().getRequestDispatcher("/user/readuser.jsp").forward(request, response);
					break;
				case "PREPARE_UPDATE":
					int idUserToUpdate = Integer.parseInt(request.getParameter("id"));
					UserDTO userToUpdate = service.read(idUserToUpdate);
					request.setAttribute("userToUpdate", userToUpdate);
					getServletContext().getRequestDispatcher("/user/updateuser.jsp").forward(request, response);
					break;
				case "DELETE":
					int idUserToDelete = Integer.parseInt(request.getParameter("id"));
					if(service.delete(idUserToDelete)) {
						if(userSession.getUsertype().equals(Usertype.SUPER)){
							updateList(request);
							request.getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
						}else{
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}
					} else throw new RuntimeException("Cancellazione non andata a buon fine");
					break;
				default:
					throw new RuntimeException("Metodo " + mode + " non disponibile");
			}
		} else throw new RuntimeException("Nessun metodo specificato");
	}

	/**
	 * Handles POST requests. It provides functionality based on the "mode" parameter.
	 *
	 * @param request the HttpServletRequest object containing client request data
	 * @param response the HttpServletResponse object to send data to the client
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		UserDTO userSession = (UserDTO) request.getSession().getAttribute("user");
		if(mode != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String usertype = request.getParameter("usertype");
			UserDTO user = new UserDTO (username,password, Usertype.valueOf(usertype));
			switch (mode.toUpperCase()) {
				case "INSERT":
					if(service.insert(user)) {
						updateList(request);
						request.getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
					} else throw new RuntimeException("Inserimento non andato a buon fine");
					break;
				case "UPDATE":
					int id = Integer.parseInt(request.getParameter("id"));
					user.setId(id);
					UserDTO userRead = service.read(id);
					request.setAttribute("user", userRead);
					RegistryDTO registry = serviceregistry.read(id);
					request.setAttribute("registry", registry);
					if(service.update(user)) {
						if(userSession.getUsertype().equals(Usertype.SUPER)){
							updateList(request);
							request.getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
						}else if(userSession.getUsertype().equals(Usertype.ADMIN)){
							request.getRequestDispatcher("/user/readuser.jsp").forward(request, response);
						}else{
							request.getRequestDispatcher("/user/readuser.jsp").forward(request, response);
						}
					} else{
						if(userSession.getUsertype().equals(Usertype.SUPER)){
							updateList(request);
							request.getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
						}else{
							request.getRequestDispatcher("/user/readuser.jsp").forward(request, response);
						}
					};
					break;
			}
		} else throw new RuntimeException("Nessun metodo specificato");
	}

	/**
	 * Updates the list of users and sets it as a request attribute.
	 *
	 * @param request the HttpServletRequest object containing client request data
	 */
	public void updateList(HttpServletRequest request) {
		List<UserDTO>listDTO = service.getAll();
		request.setAttribute("users", listDTO);
	}
}