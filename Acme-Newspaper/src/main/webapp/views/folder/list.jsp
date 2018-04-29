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

<security:authorize access="hasRole('ADMIN')">
		
		<a href="message/administrator/create.do">
		<spring:message code="message.broadcast" /></a>
		
</security:authorize>

<display:table name="folders" id="row" requestURI="${requestURI}" pagesize="5">

	<spring:message code="folder.edit" var="editHeader" />	
	<display:column title="${editHeader}">
		<jstl:choose>
			<jstl:when test="${row.system == true}">
				<spring:message code="folder.system"/>
			</jstl:when>
			<jstl:when test="${row.system == false}">
				<a href="folder/actor/edit.do?folderId=${row.id}">
		 			<spring:message code="folder.edit" />
				</a>
			</jstl:when>
		</jstl:choose>
	</display:column>

	<spring:message code="folder.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="folder.children" var="childrenHeader" />	
	<display:column title="${childrenHeader}">
		<a href="folder/actor/list.do?folderId=${row.id}">
		 			<spring:message code="folder.children" />
				</a>
	</display:column>
	
	<spring:message code="folder.messages" var="messagesHeader" />	
	<display:column title="${messagesHeader}">
		<a href="message/actor/list.do?folderId=${row.id}">
			<spring:message code="folder.messages" />
		</a>
	</display:column>

</display:table>

<security:authorize access="isAuthenticated()">
		
		<a href="folder/actor/create.do">
		<spring:message code="folder.create" /></a>
		
</security:authorize>
