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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<security:authentication property="principal" var="loggedactor"/>

<display:table name="newspapers" id="row" requestURI="${requestURI}" pagesize="5">
	
	<form:hidden path="volumeId"/>
	
	<jstl:set var="isPrivate" value="${row.isPrivate}" />		
	
	<jstl:if test="${isPrivate eq true}">
		<jstl:set var="style" value="background-color:PaleVioletRed;" />
	</jstl:if>
	
	<jstl:if test="${isPrivate eq false}">
		<jstl:set var="style" value="background-color:transparent;" />
	</jstl:if>

	<spring:message code="newspaper.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" style="${style}" />
			
	<security:authorize access="hasRole('USER')">
		<spring:message code="newspaper.delete" var="deleteHeader" />	
		<display:column title="${deleteHeader}" style="${style}">	
			<a href="newspaper/user/deleteNewspaper.do?newspaperId=${row.id}&volumeId=${volumeId}">
			 	<spring:message code="newspaper.deleteButton" />
			</a>
		</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="isAuthenticated()">
	<jstl:if test="${not empty newspapers}">
		<span style="background-color:PaleVioletRed; border-radius: 15px 50px;">&nbsp;&nbsp;<spring:message code="newspaper.isPrivate"/>&nbsp;&nbsp;</span>
	</jstl:if>
</security:authorize>