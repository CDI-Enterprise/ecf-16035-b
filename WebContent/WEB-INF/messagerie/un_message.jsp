<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="fr.cdiEnterprise.model.Item, fr.cdiEnterprise.service.Items" %>

<!-- Content messagerie -->

<% Item item = (Item) request.getAttribute("message"); %>

<div id="content">

<h1><%= item.getObject() %></h1>
<h2><%= item.getSender() %></h2>
<p><%= item.getBody() %></p>
	
</div>
