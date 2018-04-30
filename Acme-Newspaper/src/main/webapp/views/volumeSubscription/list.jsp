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

<display:table name="volumeSubscriptions" id="row" requestURI="${requestURI}" pagesize="5">
	<display:column>
		<spring:message code="volumeSubscription.display" var="volumeSubscriptionDisplayLink"/>
		<a href="${displayURI}${row.id}"><jstl:out value="${volumeSubscriptionDisplayLink}"/></a>
	</display:column>

	<spring:message code="volumeSubscription.title" var="volumeSubscriptionTitle"/>
	<display:column property="volume.title" title="${volumeSubscriptionTitle}"/>
</display:table>

<security:authorize access="hasRole('CUSTOMER')">
	<jstl:if test="${existsAvailableVolumes eq true}">
		<spring:message code="volumeSubscription.createAVolumeSubscription" var="volumeSubscriptionCreateLink"/>
		<a href="volumeSubscription/customer/create.do"><jstl:out value="${volumeSubscriptionCreateLink}"/></a>
		<br/>
		<br/>
	</jstl:if>
</security:authorize>

<acme:cancel url="welcome/index.do" code="volumeSubscription.cancel"/>