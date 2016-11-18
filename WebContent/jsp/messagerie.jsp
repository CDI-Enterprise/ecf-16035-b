<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="http://localhost:8085/ecf-16035-b/css/general.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CDI - Entreprise</title>

<%@ page import="fr.cdiEnterprise.model.Item, fr.cdiEnterprise.service.Items" %>

<%@include file="../WEB-INF/menu.html" %>
</head>
<body onload="/messagerie">
<%@include file="../WEB-INF/messagerie.html" %>

<!-- Content messagerie -->

<div id="content">

	<table>
		<tr>
			<th>#</th>
			<th>Source</th>
			<th>Destination</th>
			<th>Objet</th>
			<th>Date Reception</th>
			<th>Piece jointe</th>
		</tr>
		<%Items items = (Items) request.getAttribute("message"); %>
		<%for(int i = 0; i < items.size(); i++){%>
		<tr>
			<th><%= items.get(i).getId() %></th>
			<th><%= items.get(i).getSender() %></th>
			<th><%= items.get(i).getReceiver() %></th>
			<th><%= items.get(i).getObject() %></th>
			<th><%= items.get(i).getTimeStamp() %></th>
		</tr><%}%>
	</table>
	
	
</div> 

</body>
</html>