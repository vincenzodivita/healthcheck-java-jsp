<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Registration</title>
  <link href="css/navbar.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<%@ include file="../css/navbar.jsp" %>
<section class="container vh-100">
  <div class="container-fluid h-custom">
    <div class="row d-flex justify-content-center align-items-center h-100">

      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1 border border-primary p-5 rounded-4 shadow-lg p-3 mb-5 bg-white rounded">
      <h2 class="text-center" style="color: #002B7E; font-size: 2.5em;">Registrati</h2>
        <form action="RegistrationServlet" method="post">
        <input type="hidden" id="usertype" name="usertype" value="USER">
          <div class="form-outline mb-4">
          <label class="form-label" for="user">Username</label>
          <input type="text" id="user" name="username" placeholder="Inserisci username..." required class="form-control form-control-lg">
          </div>

          <div class="form-outline mb-3">
          <label class="form-label" for="pass">Password</label>
          <input type="password" id="pass" name="password" placeholder="Inserisci password..." required class="form-control form-control-lg">
          </div>

          <div class="text-center text-lg-start mt-4 pt-2 d-flex justify-content-center">
            <button type="submit" class="btn btn-primary btn-lg mx-3"
              style="padding-left: 2.5rem; padding-right: 2.5rem;">Registrati</button>
              <a class="btn btn-secondary btn-lg mx-3" href="index.jsp" >Accedi</a>
          </div>
        </form>
      </div>

      <div class="col-md-9 col-lg-6 col-xl-5">
      <img src="./images/doctor.png"
                class="img-fluid" alt="Sample image" style="transform: scaleX(-1)">
      </div>
    </div>
  </div>

</section>
<%@ include file="../css/footer.jsp" %>
</body>
</html>