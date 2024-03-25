<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.List"
         import="it.contrader.dto.BloodTestDTO"%>
         <%@ page import="it.contrader.dto.UserDTO" %>
         <%@ page import = "it.contrader.enums.Usertype"%>
         <%@ page import="java.time.LocalTime" %>
         <%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Esami del Sangue</title>
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


<div class="main container d-flex justify-content-center align-items-center vh-100">

  <form class="w-75 my-3 border border-primary p-5 rounded-4" action="BloodTestServlet" method="post">
  <%BloodTestDTO bloodTest = (BloodTestDTO) request.getAttribute("bloodTestToUpdate");%>
    <h1 id="title" class="my-2 pb-3 d-flex justify-content-center" style="color: #002B7E;">Esami del Sangue</h1>
        <input type="hidden" name="mode" value="update">
        <input type="hidden" name="idAdmin" value="<%=bloodTest.getIdAdmin()%>">
        <input type="hidden" name="idUser" value="<%=bloodTest.getIdUser()%>">
        <input type="hidden" name="id" value="<%=bloodTest.getId()%>">


        <div class="row mb-3">
            <label for="redBloodCell" class="col-sm-2 col-form-label" style="color: #002B7E;">Globuli Rossi</label>
            <div class="col-sm-10">
              <input class="form-control" type="number" step="any" id="redBloodCell" name="redBloodCell" value="<%=bloodTest.getRedBloodCell()%>">
            </div>
        </div>
          <div class="row mb-3">
            <label for="whiteBloodCell" class="col-sm-2 col-form-label" style="color: #002B7E;">Globuli Bianchi</label>
            <div class="col-sm-10">
              <input class="form-control" type="number" step="any" id="whiteBloodCell" name="whiteBloodCell" value="<%=bloodTest.getWhiteBloodCell()%>">
            </div>
          </div>
        <div class="row mb-3">
            <label for="platelets" class="col-sm-2 col-form-label" style="color: #002B7E;">Piastrine</label>
            <div class="col-sm-10">
              <input class="form-control" type="number" step="any" id="platelets" name="platelets" value="<%=bloodTest.getPlatelets()%>">
            </div>
        </div>
          <div class="row mb-3">
            <label for="hemoglobin" class="col-sm-2 col-form-label" style="color: #002B7E;">Emoglobina</label>
            <div class="col-sm-10">
              <input class="form-control" type="number" step="any" id="hemoglobin" name="hemoglobin" value="<%=bloodTest.getHemoglobin()%>">
            </div>
          </div>
          <div class="row mb-3">
            <label for="dataInsert" class="col-sm-2 col-form-label" style="color: #002B7E;">Data</label>
            <div class="col-sm-10">
              <input class="form-control" type="date" id="dataInsert" name="dataInsert" class="form-control" value="<%= java.time.LocalDate.now() %>">
            </div>
          </div>


                <div class="button-wrapper d-flex justify-content-center">
                  <button type="submit" class="btn btn-primary btn-lg"  >Modifica dati</button>
                </div>
              </form>

            </div>

            <%@ include file="../css/footer.jsp" %>
            </body>
            </html>