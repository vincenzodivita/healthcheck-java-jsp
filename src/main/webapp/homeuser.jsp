<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<%@ include file="../css/navbarUser.jsp" %>

   <div class="main container d-flex flex-column justify-content-center align-content-center">
        <h1 class="my-2 text-center" style="color: #002B7E;">Benvenuto ${user.getUsername()}</h1>

        <div>
                     Il nostro innovativo gestionale medico online si propone di rivoluzionare il modo in cui vengono gestiti i referti medici, puntando a rendere l'intero processo non solo pi&#249 efficiente, ma anche estremamente trasparente e accessibile. Con un impegno costante per la sicurezza dei dati e la privacy dei pazienti, offriamo un ambiente completamente sicuro dove i referti possono essere consultati e gestiti in modo rapido e intuitivo.
                 </div>
                 <div>
                     Dotato di strumenti avanzati e di un'interfaccia user-friendly, il nostro sistema favorisce una migliore comunicazione e collaborazione tra pazienti e dottori. I pazienti possono accedere facilmente ai propri referti, comprendere meglio le informazioni fornite e discuterne con i propri medici in modo pi&#249 informato e consapevole. Allo stesso tempo, i medici beneficiano di un'organizzazione pi&#249 efficiente delle informazioni mediche dei loro pazienti, consentendo loro di fornire cure personalizzate e tempestive.
                 </div>
                 <div>
                     In definitiva, il nostro gestionale medico online rappresenta un vero e proprio passo avanti nell'evoluzione della cura medica, mettendo al centro la trasparenza, l'efficienza e la collaborazione per migliorare l'esperienza complessiva di pazienti e operatori sanitari.
                 </div>
    </div>
<%@ include file="../css/footer.jsp" %>
</body>
</html>
