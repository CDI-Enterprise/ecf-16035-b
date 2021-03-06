<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fr.cdiEnterprise.model.Item, fr.cdiEnterprise.service.Items" %>

<div id="content">
	<table>
		<tr>
			<th id="select"><input type="checkbox" onchange="selectAll(this)"/></th>
			<th id="source">Source</th>
			<th id="desti">Destination</th>
			<th id="obj">Objet</th>
			<th id="date">Date Reception</th>
		</tr>
		
		<%Items items = (Items) request.getAttribute("message"); %>

		<%for(int i = 0; i < items.size(); i++){%>
			<tr class="<%= items.get(i).getId() %>" >
				<td><input value="<%= items.get(i).getId() %>" type="checkbox" name="checkbox" onchange="controleMenu(this)"/></td>
				<td><%= items.get(i).getSender() %></td>
				<td><%= items.get(i).getReceiver() %></td>
				<td><a href="/ecf-16035-b/messagerie/affichage?id=<%= items.get(i).getId() %>" > <%= items.get(i).getObject() %> </a></td>
				<td><%= items.get(i).getTimeStamp() %></td>
			</tr>
		<%}%>
	</table>
</div> 
</body>
</html>