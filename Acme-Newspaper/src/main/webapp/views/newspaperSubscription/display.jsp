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
		<spring:message code="newspaperSubscription.newspaper" var="newspaperSubscriptionNewspaperLabel"/>
		<h2><b><jstl:out value="${newspaperSubscriptionNewspaperLabel}"/>:&nbsp;</b> <jstl:out value="${newspaperSubscription.newspaper.title}"/></h2>
	</legend>
	
	<table>
		<tr>
			<td>
				<spring:message code="newspaperSubscription.creditCard.holderName" var="newspaperSubscriptionCreditCardHolderNameLabel"/>
				<b><jstl:out value="${newspaperSubscriptionCreditCardHolderNameLabel}"/>:&nbsp;</b> <jstl:out value="${newspaperSubscription.creditCard.holderName}"/>
				
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="newspaperSubscription.creditCard.brandName" var="newspaperSubscriptionCreditCardBrandNameLabel"/>
				<b><jstl:out value="${newspaperSubscriptionCreditCardBrandNameLabel}"/>:&nbsp;</b> <jstl:out value="${newspaperSubscription.creditCard.brandName}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="newspaperSubscription.creditCard.number" var="newspaperSubscriptionCreditCardNumberLabel"/>
				<b><jstl:out value="${newspaperSubscriptionCreditCardNumberLabel}"/>:&nbsp;</b> <jstl:out value="${newspaperSubscription.creditCard.number}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="newspaperSubscription.creditCard.expirationMonth" var="newspaperSubscriptionExpirationMonthLabel"/>
				<b><jstl:out value="${newspaperSubscriptionExpirationMonthLabel}"/>:&nbsp;</b> <jstl:out value="${newspaperSubscription.creditCard.expirationMonth}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="newspaperSubscription.creditCard.expirationYear" var="newspaperSubscriptionExpirationYearLabel"/>
				<b><jstl:out value="${newspaperSubscriptionExpirationYearLabel}"/>:&nbsp;</b> <jstl:out value="${newspaperSubscription.creditCard.expirationYear}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="newspaperSubscription.creditCard.cvv" var="newspaperSubscriptionCvvLabel"/>
				<b><jstl:out value="${newspaperSubscriptionCvvLabel}"/>:&nbsp;</b> <jstl:out value="${newspaperSubscription.creditCard.cvv}"/>
			</td>
		</tr>
	</table>
</fieldset>
<br/>
<acme:cancel url="newspaperSubscription/customer/list.do" code="newspaperSubscription.cancel"/>