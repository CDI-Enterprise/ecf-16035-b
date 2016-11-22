<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="fr.cdiEnterprise.model.Item, fr.cdiEnterprise.service.Items" %>

<div id="content">
	<table>
		<tr>
			<th id="select">#</th>
			<th id="source">Source</th>
			<th id="desti">Destination</th>
			<th id="obj">Objet</th>
			<th id="date">Date Reception</th>
			<th id="piece jointe">Piece jointe</th>
		</tr>
		
		<%Items items = (Items) request.getAttribute("message"); %>

		<%for(int i = 0; i < items.size(); i++){%>
			<tr class="<%= items.get(i).getId() %>" >
				<th><input type="checkbox" /></th>
				<th><%= items.get(i).getSender() %></th>
				<th><%= items.get(i).getReceiver() %></th>
				<th><a href="/ecf-16035-b/messagerie/affichage?ref=<%= items.get(i).getId() %>" > <%= items.get(i).getObject() %> </a></th>
				<th><%= items.get(i).getTimeStamp() %></th>
			</tr>
		<%}%>
	</table>
</div> 
</body>
</html>