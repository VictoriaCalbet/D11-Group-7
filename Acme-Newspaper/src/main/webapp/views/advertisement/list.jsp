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

<display:table name="advertisements" id="row" requestURI="${requestURI}" pagesize="5">

	<spring:message code="advertisement.title" var="title" />
	<display:column title="${title}">
		<jstl:out value="${row.title}"></jstl:out>
	</display:column>
	
	<spring:message code="advertisement.banner" var="banner" />
	<display:column title="${banner}">
		<a href="<jstl:url value="${row.bannerURL}"/>">
			<jstl:url value="${row.bannerURL}"/>
		</a>
	</display:column>
	
	<spring:message code="advertisement.target" var="target" />
	<display:column title="${target}">
		<a href="<jstl:url value="${row.targetPageURL}"/>">
			<jstl:url value="${row.targetPageURL}"/>
		</a>
	</display:column>
	
	<spring:message code="advertisement.newspaper" var="newspaper" />
	<display:column title="${newspaper}">
		<jstl:out value="${row.newspaper.title}"></jstl:out>
	</display:column>
	
	<security:authorize access="hasRole('AGENT')">
	<spring:message code="advertisement.edit" var="edit" />
	<display:column sortable="false" title="${edit}">
		<a href="advertisement/agent/edit.do?advertisementId=${row.id}">
			${edit}
		</a>
	</display:column>
	</security:authorize>
	<security:authorize access="hasRole('ADMIN')">
	<spring:message code="advertisement.delete" var="delete" />
	<display:column sortable="false" title="${delete}">
		<a href="${deleteURI}${row.id}">
			${delete}
		</a>
	</display:column>
	</security:authorize>
</display:table>
<security:authorize access="hasRole('AGENT')">
<spring:message code="advertisement.create" var="create" />
<a href="advertisement/agent/create.do">${create}</a>
</security:authorize>