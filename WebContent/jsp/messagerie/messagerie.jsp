<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="http://localhost:8085/ecf-16035-b/css/general.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CDI - Entreprise</title>

<%@ page import="fr.cdiEnterprise.model.Item, fr.cdiEnterprise.service.Items" %>

<%@include file="../../WEB-INF/menu.html" %>
</head>

<!-- ============================================= BODY ============================================= -->
<body>
<%@include file="../../WEB-INF/messagerie/messagerie.html" %>

<!-- Include des JSP messagerie -->

<% String url = (String) request.getAttribute("url"); %>
<jsp:include page="<%= url %>"></jsp:include>


</body>
</html>