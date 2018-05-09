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
	<display:column title="${title}"><jstl:out value="${row.title}"></jstl:out></display:column>
	
	<spring:message code="chirp.description" var="descrip" />
	<display:column title="${descrip}"><jstl:out value="${row.description}"></jstl:out></display:column>
	
	<spring:message code="chirp.publicationMoment" var="chirpPublicationMomentHeader"/>
	<spring:message code="chirp.publicationMoment.pattern" var="datePattern"/>
	<display:column title="${chirpPublicationMomentHeader}">
		<fmt:formatDate value="${row.publicationMoment}" pattern="${datePattern}"/>
	</display:column>
	
	<spring:message code="chirp.user" var="user" />
	<display:column title="${user}"><jstl:out value="${row.user.name}"></jstl:out></display:column>

</display:table>

<security:authorize access="hasRole('USER')">
		
		<a href="chirp/user/create.do">
		<spring:message code="chirp.create" /></a>
		
</security:authorize>