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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authentication property="principal" var="loggedactor"/>

<display:table name="follow-ups" id="row" requestURI="${requestURI}" pagesize="5">
	<display:column>
		<spring:message code="follow-up.display" var="followUpDisplayLink"/>
		<a href="${displayURI}${row.id}"><jstl:out value="${followUpDisplayLink}"/></a>
	</display:column>
	
	<security:authorize access="hasRole('USER')">
		<display:column>
			<jstl:choose>
				<jstl:when test="${row.user.userAccount.id eq loggedactor.id}"> 
					<spring:message code="follow-up.edit" var="followUpEditLink"/>
					<a href="follow-up/user/edit.do?followUpId=${row.id}"><jstl:out value="${followUpEditLink}"/></a>
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="follow-up.noEditable" var="followUpNoEditableLink"/>
					<jstl:out value="${followUpNoEditableLink}"/>
				</jstl:otherwise>
			</jstl:choose>
		</display:column>
	</security:authorize>

	<spring:message code="follow-up.article.title" var="followUpArticleTitleHeader"/>
	<display:column property="article.title" title="${followUpArticleTitleHeader}"/>
	
	<spring:message code="follow-up.title" var="followUpTitleHeader"/>
	<display:column property="title" title="${followUpTitleHeader}"/>
	
	<spring:message code="follow-up.publicationMoment" var="followUpPublicationMomentHeader"/>
	<spring:message code="follow-up.publicationMoment.pattern" var="datePattern"/>
	<display:column title="${followUpPublicationMomentHeader}">
		<fmt:formatDate value="${row.publicationMoment}" pattern="${datePattern}"/>
	</display:column>
	
</display:table>

<security:authorize access="hasRole('USER')">
	<spring:message code="follow-up.createAFollowUp" var="followUpCreateLink"/>
	<a href="follow-up/user/create.do"><jstl:out value="${followUpCreateLink}"/></a>
	<br/>
	<br/>
</security:authorize>