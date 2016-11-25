<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/company.css" />
<link rel="stylesheet" href="../css/general.css" />
<title>Erreur</title>
</head>
<body>
<%@ include file="../../WEB-INF/menu.html" %>
	<br/><br/><br/>
	<img src="../ressource/img/logo.png" alt="logo" id="logo">
	<h1 class="title">UNE ERREUR A ETE DETECTEE</h1>
	<div class="info">
<%=request.getAttribute("messageErreur") %>
</div>

<footer>
		<a href= "<%=request.getContextPath()%>/Company/Creation">Retour au formulaire </a>
</footer>
</body>
</html>