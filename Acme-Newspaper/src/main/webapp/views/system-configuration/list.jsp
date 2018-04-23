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

<div>
	<b><spring:message code="systemConfiguration.tabooWords" />: </b>
	<jstl:out value="${systemConfiguration.tabooWords}" />
</div><br>

<div>
	<b><spring:message code="systemConfiguration.newspaper.taboo" />: </b>
</div>

<display:table name="tabooNewspapers" requestURI="${requestURI}" id="row" pagesize="5">

	<spring:message code="newspaper.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" style="${style}" />
		
	<spring:message code="newspaper.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"
		sortable="true" style="${style}" />
		
</display:table>

<div>
	<b><spring:message code="systemConfiguration.article.taboo" />: </b>
</div>

<display:table name="tabooArticles" requestURI="${requestURI}" id="row" pagesize="5">

	<spring:message code="article.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" style="${style}" />
		
	<spring:message code="article.summary" var="summaryHeader" />
	<display:column property="summary" title="${summaryHeader}"
		sortable="true" style="${style}" />
		
	<spring:message code="article.body" var="bodyHeader" />
	<display:column property="body" title="${bodyHeader}"
		sortable="true" style="${style}" />
		
</display:table>

<div>
	<b><spring:message code="systemConfiguration.chirp.taboo" />: </b>
</div>

<display:table name="tabooChirps" requestURI="${requestURI}" id="row" pagesize="5">

	<spring:message code="chirp.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" style="${style}" />
		
	<spring:message code="chirp.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"
		sortable="true" style="${style}" />
		
</display:table>

<security:authorize access="hasRole('ADMIN')">
	<br><a href="systemConfiguration/administrator/edit.do"> <spring:message code="systemConfiguration.edit" /></a>
</security:authorize>