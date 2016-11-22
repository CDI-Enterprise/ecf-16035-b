<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="fr.cdiEnterprise.model.Item, fr.cdiEnterprise.service.Items ,fr.cdiEnterprise.util.MpClientV2" %>
<%int id = (int) request.getAttribute("id"); %>
<div id="content">
	<h1>Nouveau Message</h1><hr /><br />
	<form method="post" action="../../messagerie/nouveau?id=<%= id %>" onsubmit="controleNouveauMail(this)">
		<legend>Boite de destination :</legend>
		<input id="destination" type="text" name="receiver" placeholder="Destination" />
		<legend>Objet du mail : </legend>
		<input id="objet" type="text" name="objet" placeholder="Objet du mail" />
		<hr />
		<textarea id="body" name="body" rows="10" cols="10"></textarea>
		<input type="submit" value="Envoyer">
		<input type="reset" value="Annuler">
	</form>
</div>