<%--
 * display.jsp
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

<fieldset>
	<legend>
		<spring:message code="follow-up.title" var="followUpTitleLegend"/>
		<h2><b><jstl:out value="${followUpTitleLegend}"/>:&nbsp;</b> <jstl:out value="${followup.title}"/></h2>
	</legend>
	
	<table>
		<tr>
			<td>
				<spring:message code="follow-up.article.title" var="followUpArticleTitleLabel"/>
				<b><jstl:out value="${followUpArticleTitleLabel}"/>:&nbsp;</b> <jstl:out value="${followup.article.title}"/>				
			</td>
			<td>
				<spring:message code="follow-up.publicationMoment" var="followUpPublicationMomentLabel"/>
				<spring:message code="follow-up.publicationMoment.pattern" var="datePattern"/>
				<b><jstl:out value="${followUpPublicationMomentLabel}"/>:&nbsp;</b> <fmt:formatDate value="${followup.publicationMoment}" pattern="${datePattern}"/>				
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<spring:message code="follow-up.summary" var="followUpSummaryLabel"/>
				<b><jstl:out value="${followUpSummaryLabel}"/>:&nbsp;</b> <jstl:out value="${followup.summary}"/>				
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<spring:message code="follow-up.text" var="followUpTextLabel"/>
				<b><jstl:out value="${followUpTextLabel}"/>:&nbsp;</b> <jstl:out value="${followup.text}"/>				
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<spring:message code="follow-up.pictures" var="followUpPicturesLabel"/>
				<b><jstl:out value="${followUpPicturesLabel}"/>:&nbsp;</b> <jstl:out value="${followup.pictures}"/>				
			</td>
		</tr>
	</table>
	
	<security:authorize access="hasRole('USER')">
		<jstl:if test="${followup.user.userAccount.id eq loggedactor.id}">
			<acme:cancel url="${editURI}" code="follow-up.edit"/>
		</jstl:if>
	</security:authorize>
	
	<acme:cancel url="${cancelURI}" code="follow-up.cancel"/>
</fieldset>
