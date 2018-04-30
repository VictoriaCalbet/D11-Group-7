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
	<jstl:when test="${not empty availableVolumes}">
		<form:form action="${actionURI}" modelAttribute="volumeSubscriptionForm">
							
			<acme:select items="${availableVolumes}" itemLabel="title" code="volumeSubscription.volume" path="volumeId"/>
			<br/>
			
			<fieldset> 
				<spring:message code="volumeSubscription.creditCard.legend" var="volumeSubscriptionCreditCardLegend"/>
				<legend><b><jstl:out value="${volumeSubscriptionCreditCardLegend}"/>:&nbsp;</b></legend>
				
				<acme:textbox code="volumeSubscription.creditCard.brandName" path="creditCard.brandName"/>
				<acme:textbox code="volumeSubscription.creditCard.holderName" path="creditCard.holderName"/>
				<acme:textbox code="volumeSubscription.creditCard.number" path="creditCard.number" maxlength="16"/>
				<acme:textbox code="volumeSubscription.creditCard.expirationMonth" path="creditCard.expirationMonth" maxlength="2"/>
				<acme:textbox code="volumeSubscription.creditCard.expirationYear" path="creditCard.expirationYear" maxlength="4"/>
				<acme:textbox code="volumeSubscription.creditCard.cvv" path="creditCard.cvv" maxlength="3"/>
			</fieldset>
			
			<br/>
			<acme:submit name="save" code="volumeSubscription.subscribe"/> &nbsp;
			<acme:cancel url="volumeSubscription/customer/list.do" code="volumeSubscription.cancel"/>
		</form:form>
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="service.edit.volumeSubscription.volume.notAvailable" var="volumeSubscriptionEditVolumeNotAvailable"/>
		<b><jstl:out value="${volumeSubscriptionEditVolumeNotAvailable}"/></b>
		<br/>
	</jstl:otherwise>
</jstl:choose>