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

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<display:table name="volumes" id="row" requestURI="${requestURI}" pagesize="5">
	
	<spring:message code="volume.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="false" style="${style}" />

	<spring:message code="volume.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false"
		style="${style}" />

	<spring:message code="volume.year" var="yearHeader" />
	<display:column property="year" title="${yearHeader}"
		sortable="false" style="${style}" />
	
	
	
	<spring:message code="volume.newspapers" var="newspapersHeader" />	
			<display:column title="${newspapersHeader}" style="${style}">
				<jstl:choose>
				
					<jstl:when test="${fn:length(row.newspapers) !=0}">	
						<a href="newspaper/list.do?volumeId=${row.id}">
						 	<spring:message code="volume.newspapersButton" />
						</a>
					</jstl:when>
					<jstl:otherwise>
						<spring:message code= "volume.noNewspapers" var="volumeNoNewspapers"/>
							<jstl:out value="${newspaperNoArticles}"/> 
					</jstl:otherwise>
				</jstl:choose>
		</display:column>
		
		
</display:table>

<security:authorize access="hasRole('USER')">
		<spring:message code="volume.create" var="volumeCreate"/>
		<a href="volume/user/create.do"><jstl:out value="${volumeCreate}"/></a>
		<br/>
		</security:authorize>
