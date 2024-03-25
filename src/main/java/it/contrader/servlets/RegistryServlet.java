package it.contrader.servlets;

import it.contrader.dto.RegistryDTO;
import it.contrader.dto.UserDTO;
import it.contrader.enums.Usertype;
import it.contrader.service.RegistryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RegistryServlet")
public class RegistryServlet extends HttpServlet {

    private RegistryService service;

    @Override
    public void init() {
        this.service = new RegistryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        if(mode != null) {
            switch (mode.toUpperCase()) {
                case "GETALL":
                    updateList(request);
                    request.getRequestDispatcher("/registry/registrymanager.jsp").forward(request, response);
                    break;
                case "GETALLPATIENT":
                    updateListPatient(request);
                    request.getRequestDispatcher("/registry/getallpatient.jsp").forward(request, response);
                    break;
                case "DOCTOR_LIST":
                    updateList(request);
                    request.getRequestDispatcher("/BloodTest/bloodtestmanager.jsp").forward(request,response);
                case "READ":
                    try{
                        int idRegistryToRead = Integer.parseInt(request.getParameter("idUser"));
                        RegistryDTO registry = service.read(idRegistryToRead);
                        request.setAttribute("registry", registry);
                        getServletContext().getRequestDispatcher("/registry/readuser.jsp").forward(request, response);
                    }catch (Exception e){
                        redirectToPage(request, response, "/registry/insertregistry.jsp");
                    }
                    break;
                case "PREPARE_UPDATE":
                    int idRegistryToUpdate = Integer.parseInt(request.getParameter("idUser"));
                    RegistryDTO registryToUpdate = service.read(idRegistryToUpdate);
                    request.setAttribute("registryToUpdate", registryToUpdate);
                    getServletContext().getRequestDispatcher("/registry/updateregistry.jsp").forward(request, response);
                    break;
                default:
                    throw new RuntimeException("Metodo " + mode + " non disponibile");
            }
        } else throw new RuntimeException("Nessun metodo specificato");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        String mode = request.getParameter("mode");
        if (mode != null){
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String birthDate = request.getParameter("birthDate");
            String email = request.getParameter("email");
            String nationality = request.getParameter("nationality");
            String city = request.getParameter("city");
            String address = request.getParameter("address");
            String cf = request.getParameter("cf");

            RegistryDTO registryDTO = new RegistryDTO(name,surname,birthDate,email,nationality,city,address,cf, user.getId());
            switch (mode.toUpperCase()){
                case "INSERT":
                    if(service.insert(registryDTO)) {
                        if(user.getUsertype().equals(Usertype.SUPER)){
                            request.getRequestDispatcher("homesuper.jsp").forward(request, response);
                        }else if(user.getUsertype().equals(Usertype.ADMIN)){
                            request.getRequestDispatcher("homeadmin.jsp").forward(request, response);
                        }
                        else{
                            request.getRequestDispatcher("homeuser.jsp").forward(request, response);
                        }
                    } else throw new RuntimeException("Inserimento non andato a buon fine");
                    break;
                case "UPDATE":
                    if(service.update(registryDTO)) {
                        if(user.getUsertype().equals(Usertype.SUPER)){
                            request.getRequestDispatcher("homesuper.jsp").forward(request, response);
                        }else if(user.getUsertype().equals(Usertype.ADMIN)){
                            request.getRequestDispatcher("homeadmin.jsp").forward(request, response);
                        }
                        else{
                            request.getRequestDispatcher("homeuser.jsp").forward(request, response);
                        }
                    } else throw new RuntimeException("Modifica non andata a buon fine");
                    break;
            }


        }


    }

    private void redirectToPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }

    public void updateList(HttpServletRequest request) {
        List<RegistryDTO> listDTO = service.getAll();
        request.setAttribute("registry", listDTO);
    }

    public void updateListPatient(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<RegistryDTO> listDTO = service.getAllPatient(user.getId());
        request.setAttribute("registry", listDTO);
    }
}
