<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
    <%@ page import = "it.contrader.enums.Usertype"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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


<div class=" container" >
<nav  class="navbar px-3 mx-5 rounded-bottom " style="background-color: #e3f2fd;">
  <div class="container-fluid d-flex justify-content-center">
    <div class="d-flex justify-content-center" id="navbarSupportedContent">

      <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Inserisci
      </button>


      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="exampleModalLabel">Aggiungi Referto</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="UserServlet?mode=insert" method="post">
            <div class="modal-body">

             		<h1 id="title">Inserisci Utente</h1>
             		<input type="hidden" name="idUser" value="<%=user.getId()%>">
             		<div class="mb-3">
                      <label for="user" class="form-label">Username </label>
                      <input type="text" id="user" name="username" placeholder="Inserisci username..." required class="form-control">
                    </div>
                    <div class="mb-3">
                       <label for="pass" class="form-label">Password </label>
                       <input type="password" id="pass" name="password" placeholder="Inserisci password..." required class="form-control">
                    </div>
                   <div class="form-group">

                            <label for="type" class="form-label">Usertype</label>
                            <select id="type" name="usertype" class="form-select">
                                <option value="ADMIN">ADMIN</option>
                                <option value="USER">USER</option>
                            </select>

                        </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary">Inserisci</button>
            </div>
            </form>
          </div>
        </div>
      </div>

      <form class="d-flex mx-3" action="UserServlet" method="get">

      <% if(user.getUsertype().equals(Usertype.USER)){ %>
          <input type="hidden" name="mode" value="READ_USER">
          <input type="hidden" name="idUser" value=<%=user.getId()%>>
          <% }else if(user.getUsertype().equals(Usertype.ADMIN)){ %>
          <input type="hidden" name="mode" value="READ_ADMIN">
          <input type="hidden" name="idAdmin" value=<%=user.getId()%>>
          <% }else{ %>
              <input type="hidden" name="mode" value="READ">
          <% } %>

        <input class="form-control me-2" type="number" id="search" name="id" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-primary" type="submit">Cerca</button>
      </form>
    </div>
  </div>
</nav>

<div class="main  d-flex flex-column justify-content-center align-items-center mvh-100 ">
	<%
		List<UserDTO> list = (List<UserDTO>) request.getAttribute("users");
	%>
    <h1 class="my-5 text-center" style="color: #002B7E;">Utenti</h1>
	<table class="table table-hover mx-3">
		<thead>
			<tr>
			    <th class="text-primary-emphasis" scope="col">ID</th>
                <th class="text-primary-emphasis" scope="col">Username</th>
                <th class="text-primary-emphasis" scope="col">Password</th>
                <th class="text-primary-emphasis" scope="col">Usertype</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<%
			for (UserDTO u : list) {
		%>
		<tr>
			<th scope="row">
				<%=u.getId()%>
			</th>
            <td><%=u.getUsername()%></td>
            <td><%=u.getPassword()%></td>
            <td><%=u.getUsertype()%></td>

			<td class="d-flex justify-content-end">
			<a class="btn btn-outline-success mx-1" href=UserServlet?mode=read&id=<%=u.getId()%>>Visualizza</a>
			<a class="btn btn-outline-primary mx-1" href=UserServlet?mode=prepare_update&id=<%=u.getId()%>>Modifica</a>
                   <!-- Button trigger modal -->
                        <button type="button" class="btn btn-outline-danger mx-1" data-bs-toggle="modal" data-bs-target=#modalDelete<%=u.getId()%>>
                          Cancella
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id=modalDelete<%=u.getId()%> tabindex="-1" aria-labelledby=modalDelete<%=u.getId()%> aria-hidden="true">
                          <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Elimina Referto</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>

                              <div class="modal-body">
                                <p>Sei sicuro di voler eliminare l'utente?</p>
                              </div>
                              <div class="modal-footer">
                                <a class="btn btn-primary" href=UserServlet?mode=delete&id=<%=u.getId()%>>Si</a>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                              </div>

                            </div>
                          </div>
                        </div>
            </td>
		</tr>
		</tbody>
		<%
			}
		%>
	</table>
</div>
</div>
<%@ include file="../css/footer.jsp" %>
</body>
</html>