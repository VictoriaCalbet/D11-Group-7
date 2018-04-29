<%--
 * edit.jsp
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

<jstl:choose>
	<jstl:when test="${not empty availableNewspapers}">
		<form:form action="${actionURI}" modelAttribute="newspaperSubscriptionForm">
							
			<acme:select items="${availableNewspapers}" itemLabel="title" code="newspaperSubscription.newspaper" path="newspaperId"/>
			<br/>
			
			<fieldset> 
				<spring:message code="newspaperSubscription.edit.creditCard.legend" var="subscriptionCreditCardLegend"/>
				<legend><b><jstl:out value="${subscriptionCreditCardLegend}"/>:&nbsp;</b></legend>
				
				<acme:textbox code="newspaperSubscription.creditCard.brandName" path="creditCard.brandName"/>
				<acme:textbox code="newspaperSubscription.creditCard.holderName" path="creditCard.holderName"/>
				<acme:textbox code="newspaperSubscription.creditCard.number" path="creditCard.number" maxlength="16"/>
				<acme:textbox code="newspaperSubscription.creditCard.expirationMonth" path="creditCard.expirationMonth" maxlength="2"/>
				<acme:textbox code="newspaperSubscription.creditCard.expirationYear" path="creditCard.expirationYear" maxlength="4"/>
				<acme:textbox code="newspaperSubscription.creditCard.cvv" path="creditCard.cvv" maxlength="3"/>
			</fieldset>
			
			<br/>
			<acme:submit name="save" code="newspaperSubscription.subscribe"/> &nbsp;
			<acme:cancel url="newspaperSubscription/customer/list.do" code="newspaperSubscription.cancel"/>
		</form:form>
	</jstl:when>
	<jstl:otherwise>
		<spring:message var="newspaperSubscriptionEditNewspapersNotAvailable" code="service.edit.newspaperSubscription.newspapers.notAvailable"/>
		<b><jstl:out value="${newspaperSubscriptionEditNewspapersNotAvailable}"/></b>
		<br/>
	</jstl:otherwise>
</jstl:choose>