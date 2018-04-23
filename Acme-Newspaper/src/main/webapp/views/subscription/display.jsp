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

<fieldset>
	<legend>
		<spring:message code="subscription.newspaper" var="subscriptionNewspaperLabel"/>
		<h2><b><jstl:out value="${subscriptionNewspaperLabel}"/>:&nbsp;</b> <jstl:out value="${subscription.newspaper.title}"/></h2>
	</legend>
	
	<table>
		<tr>
			<td>
				<spring:message code="subscription.creditCard.holderName" var="subscriptionCreditCardHolderNameLabel"/>
				<b><jstl:out value="${subscriptionCreditCardHolderNameLabel}"/>:&nbsp;</b> <jstl:out value="${subscription.creditCard.holderName}"/>
				
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="subscription.creditCard.brandName" var="subscriptionCreditCardBrandNameLabel"/>
				<b><jstl:out value="${subscriptionCreditCardBrandNameLabel}"/>:&nbsp;</b> <jstl:out value="${subscription.creditCard.brandName}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="subscription.creditCard.number" var="subscriptionCreditCardNumberLabel"/>
				<b><jstl:out value="${subscriptionCreditCardNumberLabel}"/>:&nbsp;</b> <jstl:out value="${subscription.creditCard.number}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="subscription.creditCard.expirationMonth" var="subscriptionExpirationMonthLabel"/>
				<b><jstl:out value="${subscriptionExpirationMonthLabel}"/>:&nbsp;</b> <jstl:out value="${subscription.creditCard.expirationMonth}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="subscription.creditCard.expirationYear" var="subscriptionExpirationYearLabel"/>
				<b><jstl:out value="${subscriptionExpirationYearLabel}"/>:&nbsp;</b> <jstl:out value="${subscription.creditCard.expirationYear}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="subscription.creditCard.cvv" var="subscriptionCvvLabel"/>
				<b><jstl:out value="${subscriptionCvvLabel}"/>:&nbsp;</b> <jstl:out value="${subscription.creditCard.cvv}"/>
			</td>
		</tr>
	</table>
</fieldset>
<br/>
<acme:cancel url="subscription/customer/list.do" code="subscription.cancel"/>