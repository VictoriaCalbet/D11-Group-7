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
		<form:form action="${actionURI}" modelAttribute="subscription">
							
			<form:hidden path="customer"/>
			
			<acme:select items="${availableNewspapers}" itemLabel="title" code="subscription.newspaper" path="newspaper"/>
			
			<fieldset> 
				<spring:message code="subscription.edit.creditCard.legend" var="subscriptionCreditCardLegend"/>
				<legend><b><jstl:out value="${subscriptionCreditCardLegend}"/>:&nbsp;</b></legend>
				
				<acme:textbox code="subscription.creditCard.brandName" path="creditCard.brandName"/>
				<acme:textbox code="subscription.creditCard.holderName" path="creditCard.holderName"/>
				<acme:textbox code="subscription.creditCard.number" path="creditCard.number" maxlength="16"/>
				<acme:textbox code="subscription.creditCard.expirationMonth" path="creditCard.expirationMonth" maxlength="2"/>
				<acme:textbox code="subscription.creditCard.expirationYear" path="creditCard.expirationYear" maxlength="4"/>
				<acme:textbox code="subscription.creditCard.cvv" path="creditCard.cvv" maxlength="3"/>
			</fieldset>
			
			<br/>
			<acme:submit name="save" code="subscription.subscribe"/> &nbsp;
			<acme:cancel url="subscription/customer/list.do" code="subscription.cancel"/>
		</form:form>
	</jstl:when>
	<jstl:otherwise>
		<spring:message var="subscriptionEditNewspapersNotAvailable" code="service.edit.subscription.newspapers.notAvailable"/>
		<b><jstl:out value="${subscriptionEditNewspapersNotAvailable}"/></b>
		<br/>
	</jstl:otherwise>
</jstl:choose>