<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
	<%@ page import="it.contrader.dto.RegistryDTO"%>
	<%@ page import = "it.contrader.enums.Usertype"%>
	         <%@ page import="it.contrader.dto.UserDTO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Pazienti</title>
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

<div class="main container d-flex flex-column justify-content-center align-items-center">
	<%
		List<RegistryDTO> list = (List<RegistryDTO>) request.getAttribute("registry");
	%>

	<h1 class="my-5 text-center" style="color: #002B7E;">I miei pazienti</h1>

	<table class="table w-50">
		<thead>
			<tr>
				<th class="text-primary-emphasis" scope="col">ID User</th>
				<th class="text-primary-emphasis" scope="col">Nome</th>
				<th class="text-primary-emphasis" scope="col">Cognome</th>
			</tr>
		</thead>
		<tbody>
		<%
			for (RegistryDTO r : list) {
		%>
		<tr>
			<td scope="row">
				<%=r.getIdUser()%>
			</td>
			<td><%=r.getName()%></td>
			<td><%=r.getSurname()%></td>
		</tr>
		</tbody>
		<%
			}
		%>
	</table>

</div>
<%@ include file="../css/footer.jsp" %>
</body>
</html>
