<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.List"
         import="it.contrader.dto.RegistryDTO"%>
         <%@ page import="it.contrader.dto.UserDTO" %>
         <%@ page import = "it.contrader.enums.Usertype"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

  <title>Esami del Sangue</title>
</head>
<body>
    <%UserDTO user = (UserDTO) request.getSession().getAttribute("user");%>

    <% if(user.getUsertype().equals(Usertype.USER)){ %>
        <%@ include file="../css/navbarUser.jsp" %>
    <% }else if(user.getUsertype().equals(Usertype.ADMIN)){ %>
          <%@ include file="../css/navbarAdmin.jsp" %>
    <% }else{ %>
           <%@ include file="../css/navbarSuper.jsp" %>
    <% } %>

<%      UserDTO u = (UserDTO) request.getAttribute("user");
        RegistryDTO registry = (RegistryDTO) request.getAttribute("registry");
        %>

    <div class="main container d-flex justify-content-center align-items-center vh-100" style="padding-top: 8em;">
    <div class="w-75 border border-primary my-2 p-5 rounded-4">
    <h1  class="my-2 text-center" style="color: #002B7E;">Info ${registry.getName()}</h1>

    <div class="d-flex w-100">
        <div class="w-75 border m-3 h-100">
            <h2  class="my-2 text-center" style="color: #002B7E;">Credenziali</h2>
            <div class="d-flex justify-content-center">
            <ul class="list-group my-3  w-50">
              <li class="list-group-item"><span style="color: #002B7E;">Username: </span><%=u.getUsername()%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Password: </span><%=u.getPassword()%></li>
            </ul>
            </div>
            <div class="button-wrapper d-flex justify-content-center">
                     <a href=UserServlet?mode=prepare_update&id=<%=u.getId()%> class="btn btn-primary btn-lg mx-2 mb-2">Modifica</a>
                        <button type="button" class="btn btn-danger btn-lg mx-2 mb-2" data-bs-toggle="modal" data-bs-target=#modalDelete<%=u.getId()%>>
                          Elimina
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id=modalDelete<%=u.getId()%> tabindex="-1" aria-labelledby=modalDelete<%=u.getId()%> aria-hidden="true">
                          <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Elimina Profilo</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                                <p>Sei sicuro di voler eliminare il profilo?</p>
                              </div>
                              <div class="modal-footer">
                                <a class="btn btn-primary" href=UserServlet?mode=delete&id=<%=u.getId()%>>Si</a>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                              </div>

                            </div>
                          </div>
                        </div>
            </div>
        </div>

        <div class="w-75 border m-3 h-100">
            <h2  class="my-2 text-center" style="color: #002B7E;">Anagrafica</h2>
            <div class="d-flex justify-content-center">
            <ul class="list-group my-3  w-75">
              <li class="list-group-item"><span style="color: #002B7E;">Username: </span><%=u.getUsername()%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Password: </span><%=u.getPassword()%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Nome: </span><%=registry.getName()%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Cognome: </span><%=registry.getSurname()%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Data Nascita: </span><%=registry.getBirthDate()%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Email: </span><%=registry.getEmail()%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Nazione: </span><%=registry.getNationality()%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Citt&#224;: </span><%=registry.getCity()%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Indirizzo: </span><%=registry.getAddress()%></li>
              <li class="list-group-item"><span style="color: #002B7E;">Cf: </span><%=registry.getCf()%></li>
            </ul>
            </div>
            <div class="button-wrapper d-flex justify-content-center">
               <a href=RegistryServlet?mode=prepare_update&idUser=<%=u.getId()%> class="btn btn-primary btn-lg mb-2 mx-2">Modifica</a>
            </div>
        </div>
    </div>

	</div>
	</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>