<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.List"
         import="it.contrader.dto.RegistryDTO"%>
         <%@ page import="it.contrader.dto.UserDTO" %>
         <%@ page import = "it.contrader.enums.Usertype"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">

  <title>Registration</title>
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

<div class="main container">
		<%RegistryDTO registry = (RegistryDTO) request.getAttribute("registry");
		%>

		<div class="card">
			<div class="row g-0">
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title">Anagrafica Info</h5>
						<p class="card-text">Nome: <%=registry.getName()%></p>
						<p class="card-text">Cognome: <%=registry.getSurname()%></p>
						<p class="card-text">Data Nascita: <%=registry.getBirthDate()%></p>
						<p class="card-text">Email: <%=registry.getEmail()%></p>
						<p class="card-text"> Nazione: <%=registry.getNationality()%></p>
						<p class="card-text">Citt&#224;: <%=registry.getCity()%></p>
						<p class="card-text">Indirizzo: <%=registry.getAddress()%></p>
						<p class="card-text">cf: <%=registry.getCf()%></p>

                        <a href="RegistryServlet?mode=prepare_update&idUser=${user.getId()}">Edit</a>
					</div>
				</div>
			</div>
		</div>

	</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>