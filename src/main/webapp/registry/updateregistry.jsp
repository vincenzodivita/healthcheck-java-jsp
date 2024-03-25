<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.List"
         import="it.contrader.dto.RegistryDTO"%>
         <%@ page import = "it.contrader.enums.Usertype"%>
                  <%@ page import="it.contrader.dto.UserDTO" %>

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

<%RegistryDTO registry = (RegistryDTO) request.getAttribute("registryToUpdate");%>

<div class=" main container d-flex justify-content-center align-items-center mvh-100" style="padding-top: 7em;">
      <div class="col-md-8 col-lg-6 col-xl-4 border border-primary p-5 rounded-4 shadow-lg  mb-5 bg-white rounded w-50 ">
      <h2 class="text-center" style="color: #002B7E; font-size: 2.5em;">Modifica Anagrafica</h2>
        <form action="RegistryServlet?mode=update" method="post">
        <input type="hidden" name="idUser" value="<%=registry.getIdUser()%>">
          <div class="d-flex">
              <div class="form-outline mb-4 w-50 mx-2">
              <label for="name" class="form-label">Nome </label>
              <input type="text" id="name" name="name" placeholder="Inserisci il tuo nome..."  class="form-control form-control-lg"  value="<%=registry.getName()%>">
              </div>

              <div class="form-outline mb-3 w-50 mx-2">
              <label class="form-label" for="surname">Cognome</label>
              <input type="text" id="surname" name="surname" placeholder="Inserisci cognome..."  class="form-control form-control-lg"  value="<%=registry.getSurname()%>">
              </div>
          </div>

          <div class="form-outline mb-4 mx-2">
               <label for="birthDate" class="form-label">Data di nascita </label>
               <input type="date" id="birthDate" name="birthDate" placeholder="Inserisci la data di nascita..."  class="form-control form-control-lg"  value="<%=registry.getBirthDate()%>">
          </div>

          <div class="form-outline mb-3 mx-2">
              <label for="email" class="form-label">Email </label>
              <input type="text" id="email" name="email" placeholder="Inserisci la tua email..."  class="form-control form-control-lg" value="<%=registry.getEmail()%>">
          </div>

        <div class="d-flex">
        <div class="form-outline mb-4 mx-2 w-25">
               <label for="nationality" class="form-label">Nazionalit&#224;</label>
               <input type="text" id="nationality" name="nationality" placeholder="Inserisci la tua nazionalità..."  class="form-control form-control-lg" value="<%=registry.getNationality()%>">
          </div>

          <div class="form-outline mb-3 mx-2 w-75">
              <label for="city" class="form-label">Citt&#224;</label>
              <input type="text" id="city" name="city" placeholder="Inserisci la città..."  class="form-control form-control-lg" value="<%=registry.getCity()%>">
          </div>
           </div>
           <div class="form-outline mb-4 mx-2">
               <label for="address" class="form-label">Indirizzo </label>
               <input type="text" id="address" name="address" placeholder="Inserisci l'indirizzo..."  class="form-control form-control-lg" value="<%=registry.getAddress()%>">
          </div>

          <div class="form-outline mb-3 mx-2">
              <label for="cf" class="form-label">Codice Fiscale</label>
              <input type="text" id="cf" name="cf" placeholder="Inserisci il codice fiscale..."  class="form-control form-control-lg" value="<%=registry.getCf()%>">
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