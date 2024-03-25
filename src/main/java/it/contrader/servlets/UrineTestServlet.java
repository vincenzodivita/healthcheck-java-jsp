package it.contrader.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.dto.UserDTO;
import it.contrader.enums.Usertype;
import it.contrader.service.UrineTestService;

import java.io.IOException;
import java.util.List;


@WebServlet("/UrineTestServlet")
public class UrineTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UrineTestService service;

    @Override
    public void init() {this.service = new UrineTestService(); }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException, ServletException {
        String mode = request.getParameter("mode");
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if (mode != null) {
            switch (mode.toUpperCase()) {
                case "GETALL":
                    updateList(request);
                    request.getRequestDispatcher("/urinetest/urinetestmanager.jsp").forward(request, response);
                    break;
                case "GETALLUSER":
                    updateListUser(request);
                    request.getRequestDispatcher("/urinetest/urinetestmanager.jsp").forward(request, response);
                    break;
                case "GETALLADMIN":
                    updateListAdmin(request);
                    request.getRequestDispatcher("/urinetest/urinetestmanager.jsp").forward(request, response);
                    break;
                case  "READ" :
                    int idUrineTestToRead = Integer.parseInt(request.getParameter("id"));
                    UrineTestDTO urinetest = service.read(idUrineTestToRead);
                    request.setAttribute("urinetest", urinetest);
                    getServletContext().getRequestDispatcher("/urinetest/readurinetest.jsp").forward(request,response);
                    break;
                case "READUSER":
                    int idUrineTest = Integer.parseInt(request.getParameter("id"));
                    int idUserUrineTest = Integer.parseInt(request.getParameter("idUser"));
                    UrineTestDTO urineTestReadUser = service.readUser(idUrineTest,idUserUrineTest);
                    request.setAttribute("urinetest", urineTestReadUser);
                    getServletContext().getRequestDispatcher("/urinetest/readurinetest.jsp").forward(request,response);
                    break;
                case "READADMIN":
                    int idUrineTestToReadAd = Integer.parseInt(request.getParameter("id"));
                    int idAdminUrineTest = Integer.parseInt(request.getParameter("idAdmin"));
                    UrineTestDTO urineTestReadAdmin = service.readAdmin(idUrineTestToReadAd,idAdminUrineTest);
                    request.setAttribute("urinetest", urineTestReadAdmin);
                    getServletContext().getRequestDispatcher("/urinetest/readurinetest.jsp").forward(request,response);
                    break;
                case "CHECK":
                    int id1 = Integer.parseInt(request.getParameter("id"));
                    if(service.check(id1)) {
                        if(user.getUsertype().equals(Usertype.ADMIN)){
                            updateListAdmin(request);
                        } else {
                            updateList(request);
                        }
                        request.getRequestDispatcher("/urinetest/urinetestmanager.jsp").forward(request, response);
                    } else throw new RuntimeException("Validazione non andato a buon fine");
                    break;
                case "PREPARE_UPDATE":
                    int idUrineTestToUpdate = Integer.parseInt(request.getParameter("id"));
                    UrineTestDTO urineTestToUpdate = service.read(idUrineTestToUpdate);
                    request.setAttribute("urineTestToUpdate", urineTestToUpdate);
                    getServletContext().getRequestDispatcher("/urinetest/updateurinetest.jsp").forward(request, response);
                    break;
                case "DELETE":
                    int idUrineTestToDelete = Integer.parseInt(request.getParameter("id"));
                    if (service.delete(idUrineTestToDelete)) {
                        if(user.getUsertype().equals(Usertype.SUPER)){
                            updateList(request);
                        } else if (user.getUsertype().equals(Usertype.USER)) {
                            updateListUser(request);
                        }
                        request.getRequestDispatcher("/urinetest/urinetestmanager.jsp").forward(request, response);
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
        if (mode != null) {
            Float color = Float.valueOf(request.getParameter("color"));
            Float ph = Float.valueOf(request.getParameter("ph"));
            Float protein = Float.valueOf(request.getParameter("protein"));
            Float hemoglobin = Float.valueOf(request.getParameter("hemoglobin"));
            Boolean isChecked = Boolean.valueOf(request.getParameter("isChecked"));
            String dataInsert = (request.getParameter("dataInsert"));
            Integer idAdmin = Integer.valueOf((request.getParameter("idAdmin")));
            Integer idUser = Integer.valueOf(request.getParameter("idUser"));
            UrineTestDTO urineTestDTO = new UrineTestDTO (color, ph, protein, hemoglobin, idAdmin, idUser, isChecked, dataInsert);
            switch (mode.toUpperCase()) {
                case "INSERT":
                    if (service.insert(urineTestDTO)) {
                        if (user.getUsertype().equals(Usertype.SUPER)) {
                            updateList(request);
                        } else if (user.getUsertype().equals(Usertype.USER)) {
                            updateListUser(request);
                        }

                        request.getRequestDispatcher("/urinetest/urinetestmanager.jsp").forward(request, response);

                    } else throw new RuntimeException("Inserimento non andato a buon fine");
                    break;
                case "UPDATE":
                    int id = Integer.parseInt(request.getParameter("id"));
                    urineTestDTO.setId(id);
                    if(service.update(urineTestDTO)) {
                        if(user.getUsertype().equals(Usertype.USER)) {
                            updateListUser(request);
                            request.getRequestDispatcher("/urinetest/urinetestmanager.jsp").forward(request,response);
                        } else if(user.getUsertype().equals(Usertype.ADMIN)) {
                            updateListAdmin(request);
                            request.getRequestDispatcher("/urinetest/urinetestmanager.jsp").forward(request,response);
                        }else{
                            updateList(request);
                            request.getRequestDispatcher("/urinetest/urinetestmanager.jsp").forward(request, response);
                        }

                    } else throw new RuntimeException("Modifica non andata a buon fine");
                    break;
            }
        }else throw new RuntimeException("Nessun metodo specificato");
    }

    public void updateList(HttpServletRequest request) {
        List<UrineTestDTO> listDTO = service.getAll();
        request.setAttribute("urinetest", listDTO);
    }
    private void updateListAdmin(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<UrineTestDTO>listDTO = service.getAll(user.getId());
        request.setAttribute("urinetest", listDTO);
    }

    public void updateListUser(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<UrineTestDTO>listDTO = service.getAllUser(user.getId());
        request.setAttribute("urinetest", listDTO);
    }
    
}

