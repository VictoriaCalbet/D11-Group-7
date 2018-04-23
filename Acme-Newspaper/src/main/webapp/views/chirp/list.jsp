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

<display:table name="chirps" id="row" requestURI="${requestURI}" pagesize="5">

	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="chirp.delete" var="delete" />	
		<display:column title="${delete}">	
			<a href="chirp/administrator/delete.do?chirpId=${row.id}">
			 	<spring:message code="chirp.delete" />
			</a>
		</display:column>
		
	</security:authorize>

	<spring:message code="chirp.title" var="title" />
	<display:column property="title" title="${title}" />
	
	<spring:message code="chirp.description" var="descrip" />
	<display:column property="description" title="${descrip}"/>
	
	<spring:message code="chirp.user" var="user" />
	<display:column property="user.name" title="${user}" />

</display:table>

<security:authorize access="hasRole('USER')">
		
		<a href="chirp/user/create.do">
		<spring:message code="chirp.create" /></a>
		
</security:authorize>