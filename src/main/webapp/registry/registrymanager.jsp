<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.RegistryDTO"%>
	<%@ page import = "it.contrader.enums.Usertype"%>
	         <%@ page import="it.contrader.dto.UserDTO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

<title>User Manager</title>

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
	<%
		List<RegistryDTO> list = (List<RegistryDTO>) request.getAttribute("registry");
	%>

	<table class="table w-25">
		<thead>
			<tr>
				<th class="text-primary-emphasis" scope="col">ID</th>
				<th class="text-primary-emphasis" scope="col">Nome</th>
				<th class="text-primary-emphasis" scope="col">Cognome</th>
				<th class="text-primary-emphasis" scope="col">Data Nascita</th>
				<th class="text-primary-emphasis" scope="col">Email</th>
				<th class="text-primary-emphasis" scope="col">Nazionalit&#224;</th>
				<th class="text-primary-emphasis" scope="col">Citt&#224;</th>
				<th class="text-primary-emphasis" scope="col">Indirizzo</th>
				<th class="text-primary-emphasis" scope="col">CF</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<%
			for (RegistryDTO r : list) {
		%>
		<tr>
			<th scope="row"><a href=RegistryServlet?mode=read&id=<%=r.getId()%>>
				<%=r.getId()%>
			</a></th>
			<td><%=r.getName()%></td>
			<td><%=r.getSurname()%></td>
			<td><%=r.getBirthDate()%></td>
			<td><%=r.getEmail()%></td>
			<td><%=r.getNationality()%></td>
			<td><%=r.getCity()%></td>
			<td><%=r.getAddress()%></td>
			<td><%=r.getCf()%></td>
			<td><a href=RegistryServlet?mode=prepare_update&idUser=<%=r.getIdUser()%>>Edit</a></td>
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