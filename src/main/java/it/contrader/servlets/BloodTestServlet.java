package it.contrader.servlets;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.RegistryDTO;
import it.contrader.enums.Usertype;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import it.contrader.service.BloodTestService;
import it.contrader.service.UserService;

@WebServlet("/BloodTestServlet")
public class BloodTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private BloodTestService service;

    @Override
    public void init() {
        this.service = new BloodTestService();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if(mode != null) {
            switch (mode.toUpperCase()) {
                case "GETALL":
                    updateList(request);
                    request.getRequestDispatcher("/BloodTest/bloodtestmanager.jsp").forward(request, response);
                    break;
                case "GETALLADMIN":
                    updateListAdmin(request);
                    request.getRequestDispatcher("/BloodTest/bloodtestmanager.jsp").forward(request, response);
                    break;
                case "GETALLUSER":
                    updateListUser(request);
                    request.getRequestDispatcher("/BloodTest/bloodtestmanager.jsp").forward(request, response);
                    break;
                case  "READ" :
                    int idBloodTestToRead = Integer.parseInt(request.getParameter("id"));
                    BloodTestDTO bloodTest = service.read(idBloodTestToRead);
                    request.setAttribute("bloodTest", bloodTest);
                    getServletContext().getRequestDispatcher("/BloodTest/readbloodtest.jsp").forward(request,response);
                    break;
                case "READ_USER":
                    int idBloodTest = Integer.parseInt(request.getParameter("id"));
                    int idUserBloodTest = Integer.parseInt(request.getParameter("idUser"));
                    BloodTestDTO bloodTestReadUser = service.readUser(idBloodTest,idUserBloodTest);
                    request.setAttribute("bloodTest", bloodTestReadUser);
                    getServletContext().getRequestDispatcher("/BloodTest/readbloodtest.jsp").forward(request,response);
                    break;
                case "READ_ADMIN":
                    int idBloodTestToReadAd = Integer.parseInt(request.getParameter("id"));
                    int idAdminBloodTest = Integer.parseInt(request.getParameter("idAdmin"));
                    BloodTestDTO bloodTestReadAdmin = service.readAdmin(idBloodTestToReadAd,idAdminBloodTest);
                    request.setAttribute("bloodTest", bloodTestReadAdmin);
                    getServletContext().getRequestDispatcher("/BloodTest/readbloodtest.jsp").forward(request,response);
                    break;
                case "CHECK":
                    int id1 = Integer.parseInt(request.getParameter("id"));
                    if(service.check(id1)) {
                        if(user.getUsertype().equals(Usertype.ADMIN)){
                            updateListAdmin(request);
                        } else {
                            updateList(request);
                        }
                        request.getRequestDispatcher("/BloodTest/bloodtestmanager.jsp").forward(request, response);
                    } else throw new RuntimeException("Validazione non andato a buon fine");
                    break;
                case "PREPARE_UPDATE":
                    int idBloodTestToUpdate = Integer.parseInt(request.getParameter("id"));
                    BloodTestDTO bloodTestToUpdate = service.read(idBloodTestToUpdate);
                    request.setAttribute("bloodTestToUpdate", bloodTestToUpdate);
                    getServletContext().getRequestDispatcher("/BloodTest/updatebloodtest.jsp").forward(request, response);
                    break;
                case "DELETE":
                    int idBloodTestToDelete = Integer.parseInt(request.getParameter("id"));
                    if(service.delete(idBloodTestToDelete)) {
                        if(user.getUsertype().equals(Usertype.SUPER)){
                            updateList(request);
                        }else if (user.getUsertype().equals(Usertype.USER)) {
                            updateListUser(request);
                        }else{
                            updateListAdmin(request);
                        }
                        request.getRequestDispatcher("/BloodTest/bloodtestmanager.jsp").forward(request, response);
                    } else throw new RuntimeException("Cancellazione non andata a buon fine");
                    break;
                default:
                    throw new RuntimeException("Metodo " + mode + " non disponibile");
            }
        } else throw new RuntimeException("Nessun metodo specificato");
    }



    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        String mode = request.getParameter("mode");
        if(mode != null) {
            Float redBloodCell = Float.valueOf(request.getParameter("redBloodCell"));
            Float whiteBloodCell = Float.valueOf(request.getParameter("whiteBloodCell"));
            Float platelets = Float.valueOf(request.getParameter("platelets"));
            Float hemoglobin = Float.valueOf(request.getParameter("hemoglobin"));
            Boolean isChecked = Boolean.valueOf(request.getParameter("isChecked"));
            String dataInsert = (request.getParameter("dataInsert"));
            Integer idAdmin = Integer.valueOf((request.getParameter("idAdmin")));
            Integer idUser = Integer.valueOf(request.getParameter("idUser"));
            BloodTestDTO bloodTestDTO = new BloodTestDTO (redBloodCell,whiteBloodCell, platelets,hemoglobin,idAdmin,idUser,isChecked,dataInsert);
            switch (mode.toUpperCase()) {
                case "INSERT":
                    if(service.insert(bloodTestDTO)) {
                        if(user.getUsertype().equals(Usertype.SUPER)){
                            updateList(request);
                        } else if (user.getUsertype().equals(Usertype.USER)) {
                            updateListUser(request);
                        }

                        request.getRequestDispatcher("/BloodTest/bloodtestmanager.jsp").forward(request, response);

                    } else throw new RuntimeException("Inserimento non andato a buon fine");
                    break;

                case "UPDATE":
                    int id = Integer.parseInt(request.getParameter("id"));
                    bloodTestDTO.setId(id);
                    if(service.update(bloodTestDTO)) {
                        if(user.getUsertype().equals(Usertype.USER)) {
                            updateListUser(request);
                            request.getRequestDispatcher("/BloodTest/bloodtestmanager.jsp").forward(request,response);
                        }else if(user.getUsertype().equals(Usertype.ADMIN)) {
                            updateListAdmin(request);
                            request.getRequestDispatcher("/BloodTest/bloodtestmanager.jsp").forward(request,response);
                        }else{
                            updateList(request);
                            request.getRequestDispatcher("/BloodTest/bloodtestmanager.jsp").forward(request, response);
                        }

                    } else throw new RuntimeException("Modifica non andata a buon fine");
                    break;
            }
        } else throw new RuntimeException("Nessun metodo specificato");
    }


    public void updateList(HttpServletRequest request) {
        List<BloodTestDTO>listDTO = service.getAll();
        request.setAttribute("bloodTest", listDTO);
    }
    public void updateListUser(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<BloodTestDTO>listDTO = service.getAllUser(user.getId());
        request.setAttribute("bloodTest", listDTO);
    }

    private void updateListAdmin(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<BloodTestDTO>listDTO = service.getAll(user.getId());
        request.setAttribute("bloodTest", listDTO);
    }
}
