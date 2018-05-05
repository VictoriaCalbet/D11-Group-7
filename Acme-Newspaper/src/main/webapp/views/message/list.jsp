<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<security:authentication property="principal" var="loggedactor"/>

<display:table name="messages" id="row" requestURI="${requestURI}" pagesize="5">

	<spring:message code="message.edit" var="editHeader" />	
	<display:column title="${editHeader}">
		<a href="message/actor/edit.do?messageId=${row.id}">
			<spring:message code="message.edit" />
		</a>
	</display:column>

	<spring:message code="message.subject" var="subjectHeader" />
	<display:column property="subject" title="${subjectHeader}" sortable="true" />
	
	<spring:message code="message.body" var="bodyHeader" />
	<display:column property="body" title="${bodyHeader}" sortable="true" />
	
	<spring:message code="message.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true" />
	
	<spring:message code="message.priority" var="priorityHeader" />
	<display:column property="priority" title="${priorityHeader}" sortable="true" />
	
	<spring:message code="message.sender" var="senderHeader" />
	<display:column property="sender.userAccount.username" title="${senderHeader}" sortable="true" />

	<spring:message code="message.recipient" var="recipientHeader" />
	<display:column property="recipient.userAccount.username" title="${recipientHeader}" sortable="true" />
	
</display:table>

<security:authorize access="isAuthenticated()">
		
		<a href="message/actor/create.do">
		<spring:message code="message.create" /></a>
		
</security:authorize>