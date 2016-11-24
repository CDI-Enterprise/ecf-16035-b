<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fr.cdiEnterprise.model.Item, fr.cdiEnterprise.service.Items" %>

<!-- Content messagerie -->

<% Item item = (Item) request.getAttribute("message"); %>

<div id="content">

<h1>Nouveau Message</h1><hr /><br />
	<form method="post" action="../../ecf-16035-b/messagerie/nouveau?id=<%= item.getId() %>" onsubmit="return controleNouveauMail()">
		<legend>Boite de destination :</legend>
		<input id="destination" type="text" name="receiver" placeholder="Destination" value="<%= item.getReceiver() %>" />
		<legend>Objet du mail : </legend>
		<input id="objet" type="text" name="objet" placeholder="Objet du mail" value="<%= item.getObject() %>" />
		<hr />
		<textarea id="body" name="body" rows="10" cols="10"><%= item.getBody() %></textarea>
		<input type="submit" value="Envoyer">
		<input type="reset" value="Annuler">
	</form>
</div>