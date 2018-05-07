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
		<spring:message code="volumeSubscription.volume.title" var="volumeSubscriptionVolumeTitleLabel"/>
		<h2><b><jstl:out value="${volumeSubscriptionVolumeTitleLabel}"/>:&nbsp;</b> <jstl:out value="${volumeSubscription.volume.title}"/></h2>
	</legend>
	<br/>
	<fieldset>
		<legend>
			<spring:message code="volumeSubscription.associatedNewspapers" var="volumeSubscriptionNewspaperAssociatedLabel"/>
			<h2><b><jstl:out value="${volumeSubscriptionNewspaperAssociatedLabel}"/>:&nbsp;</b></h2>
		</legend>
		
		<table>
			<tr>
				<td> 
					<b><spring:message code="volumeSubscription.volume.newspaper.title"/>:&nbsp;</b>
				</td>
				<td> 
					<b><spring:message code="volumeSubscription.volume.newspaper.isPrivate"/>:&nbsp;</b>
				</td>
				<td>
				</td>
			</tr>
			<jstl:forEach items="${volumeSubscription.volume.newspapers}" var="row">
				<tr>
					<td>
						<jstl:out value="${row.title}"/> 
					</td>
					<td>
						<jstl:choose>
							<jstl:when test="${row.isPrivate eq true}">
								<spring:message code="volumeSubscription.volume.newspaper.isPrivate.yes" var="volumeSubscriptionVolumeNewspapersPrivateYes"/>
								<jstl:out value="${volumeSubscriptionVolumeNewspapersPrivateYes}"/>		
							</jstl:when>
							<jstl:otherwise>
								<spring:message code="volumeSubscription.volume.newspaper.isPrivate.no" var="volumeSubscriptionVolumeNewspapersPrivateNo"/>
								<jstl:out value="${volumeSubscriptionVolumeNewspapersPrivateNo}"/>
							</jstl:otherwise>
						</jstl:choose>
					</td>
					<td>
<%-- 						<spring:message code="volumeSubscription.volume.newspaper.showArticles" var="volumeSubscriptionVolumeShowArticles"/></b> --%>
<%-- 						<a href="article/list.do?newspaperId=${row.id}"><jstl:out value="${volumeSubscriptionVolumeShowArticles}"/></a> --%>
					</td>
				</tr>
			</jstl:forEach>
		</table>
	</fieldset>
	<br/><br/>
	<fieldset>
		<legend>
			<spring:message code="volumeSubscription.creditCard.legend" var="volumeSubscriptionCreditCardLegend"/>
			<h2><b><jstl:out value="${volumeSubscriptionCreditCardLegend}"/>:&nbsp;</b></h2>
		</legend>
		
		<table>
			<tr>
				<td>
					<spring:message code="volumeSubscription.creditCard.holderName" var="volumeSubscriptionCreditCardHolderNameLabel"/>
					<b><jstl:out value="${volumeSubscriptionCreditCardHolderNameLabel}"/>:&nbsp;</b> <jstl:out value="${volumeSubscription.creditCard.holderName}"/>
				</td>
				<td>
					<spring:message code="volumeSubscription.creditCard.brandName" var="volumeSubscriptionCreditCardBrandNameLabel"/>
					<b><jstl:out value="${volumeSubscriptionCreditCardBrandNameLabel}"/>:&nbsp;</b> <jstl:out value="${volumeSubscription.creditCard.brandName}"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="volumeSubscription.creditCard.number" var="volumeSubscriptionCreditCardNumberLabel"/>
					<b><jstl:out value="${volumeSubscriptionCreditCardNumberLabel}"/>:&nbsp;</b> <jstl:out value="${volumeSubscription.creditCard.number}"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="volumeSubscription.creditCard.expirationMonth" var="volumeSubscriptionExpirationMonthLabel"/>
					<b><jstl:out value="${volumeSubscriptionExpirationMonthLabel}"/>:&nbsp;</b> <jstl:out value="${volumeSubscription.creditCard.expirationMonth}"/>
				</td>
				<td>
					<spring:message code="volumeSubscription.creditCard.expirationYear" var="volumeSubscriptionExpirationYearLabel"/>
					<b><jstl:out value="${volumeSubscriptionExpirationYearLabel}"/>:&nbsp;</b> <jstl:out value="${volumeSubscription.creditCard.expirationYear}"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="volumeSubscription.creditCard.cvv" var="volumeSubscriptionCvvLabel"/>
					<b><jstl:out value="${volumeSubscriptionCvvLabel}"/>:&nbsp;</b> <jstl:out value="${volumeSubscription.creditCard.cvv}"/>
				</td>
			</tr>
		</table>
	</fieldset>
	<br/>
	<acme:cancel url="volumeSubscription/customer/list.do" code="volumeSubscription.cancel"/>
	<br/>
</fieldset>