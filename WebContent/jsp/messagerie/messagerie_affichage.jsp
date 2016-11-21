<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="fr.cdiEnterprise.model.Item, fr.cdiEnterprise.service.Items" %>
<title>Insert title here</title>
</head>
<body>
<%@include file="../../WEB-INF/menu.html" %>
<%@include file="../../WEB-INF/messagerie.html" %>

<!-- Content messagerie -->

<% Item item = (Item) request.getAttribute("message"); %>

<div id="content">
	<h1><%= item.getObject() %></h1>
	<h2><%= item.getSender() %></h2>
	<p><%= item.getBody() %></p>
</div>

</body>
</html>