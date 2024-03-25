<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO" %>
         <%@ page import= "it.contrader.enums.Usertype" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">

    <title>Edit User</title>
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

<%
    UserDTO u = (UserDTO) request.getAttribute("userToUpdate");
    UserDTO userSession= (UserDTO) request.getSession().getAttribute("user");
%>



    <div class=" main container d-flex justify-content-center align-items-center mvh-100" style="padding-top: 7em;">
          <div class="col-md-8 col-lg-6 col-xl-4 border border-primary p-5 rounded-4 shadow-lg  mb-5 bg-white rounded w-50 ">
          <h2 class="text-center" style="color: #002B7E; font-size: 2.5em;">Modifica utente</h2>
            <form action="UserServlet?mode=update" method="post">
        <% if(!userSession.getUsertype().equals(Usertype.SUPER) && !u.getUsertype().equals(Usertype.SUPER)){ %>
               <input type="hidden" name="usertype" value=<%=u.getUsertype()%>>
           <%} %>

              <input type="hidden" name="id" value=<%=u.getId()%>>
              <div class="form-outline mb-4 mx-2">
                   <label for="user" class="form-label">Username </label>
                   <input type="text" id="user" name="username" required  class="form-control form-control-lg" placeholder="Inserisci l'username" value=<%=u.getUsername()%>>
              </div>

              <div class="form-outline mb-3 mx-2">
                  <label for="pass" class="form-label">Password </label>
                  <input type="password" id="pass" name="password" required class="form-control form-control-lg" placeholder="Inserisci la password" value=<%=u.getPassword()%>>
              </div>

            <div class="form-outline mb-4 mx-2"">
        <% if(userSession.getUsertype().equals(Usertype.SUPER)){ %>
                <label for="type" class="form-label">Usertype</label>
                <select id="type" name="usertype" class="form-select">
                <option value="SUPER" >SUPER</option>
                    <option value="ADMIN" >ADMIN</option>
                    <option value="USER" >USER</option>
                </select>
           <%} %>
            </div>

              <div class="text-center text-lg-start mt-4 pt-2 d-flex justify-content-center">
                <button type="submit" class="btn btn-primary btn-lg mx-3"
                  style="padding-left: 2.5rem; padding-right: 2.5rem;">Modifica</button>
              </div>
            </form>
          </div>
    </div>
<%@ include file="../css/footer.jsp" %>
</body>
</html>