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
		<h2><b><jstl:out value="${newspaperSubscriptionNewspaperLabel}"/>:&nbsp;</b></h2>
	</legend>
	<br/>
	
	<spring:message code="newspaperSubscription.newspaper" var="newspaperSubscriptionNewspaperLabel"/>
	
	<spring:message code="newspaperSubscription.newspaper.title" var="newspaperTitleLabel"/>
	<b><jstl:out value="${newspaperTitleLabel}"/>:&nbsp;</b><jstl:out value="${newspaperSubscription.newspaper.title}"/><br/>
	
	<spring:message code="newspaperSubscription.newspaper.publicationDate" var="newspaperPublicationDateLabel"/>
	<spring:message code="newspaperSubscription.publicationDate.pattern" var="datePattern"/>
	<b><jstl:out value="${newspaperPublicationDateLabel}"/>:&nbsp;</b><fmt:formatDate value="${newspaperSubscription.newspaper.publicationDate}" pattern="${datePattern}"/>
	<br/>
	<spring:message code="newspaperSubscription.newspaper.description" var="newspaperDescriptionLabel"/>
	<b><jstl:out value="${newspaperDescriptionLabel}"/>:&nbsp;</b><jstl:out value="${newspaperSubscription.newspaper.description}"/><br/><br/>
	
<%-- 	<spring:message code="newspaperSubscription.newspaper.showArticles" var="newspaperShowArticlesLabel"/> --%>
<%-- 	<a href="article/list.do?newspaperId=${newspaperSubscription.newspaper.id}"> <jstl:out value="${newspaperShowArticlesLabel}"/></a> --%>
</fieldset>
<br/>

<fieldset>
	<legend>
		<spring:message code="newspaperSubscription.creditCard.legend" var="newspaperSubscriptionCreditCardLegend"/>
		<h2><b><jstl:out value="${newspaperSubscriptionCreditCardLegend}"/>:&nbsp;</b></h2>
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
