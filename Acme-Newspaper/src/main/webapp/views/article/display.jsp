<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<b><spring:message code="article.title" />: </b>
	<jstl:out value="${article.title}" />
	<br /> <b><spring:message code="article.summary" />: </b>
	<jstl:out value="${article.summary}" />
	<br /> <b><spring:message code="article.body" />: </b>
	<jstl:out value="${article.body}" />
	<br /> <b> <spring:message code="article.pictures" />:
	</b>
	<jstl:choose>
		<jstl:when test="${fn:length(article.pictures)==0}">
			<spring:message code="article.noPictures" />
		</jstl:when>
		<jstl:otherwise>
			<jstl:forEach items="${article.pictures}" var="url">
				<acme:image height="64" imageURL="${url}" width="64" codeError="newspaper.unspecifiedImage" imageNotFoundLocation="images/fotoNotFound.png"/>
			</jstl:forEach>
		</jstl:otherwise>
	</jstl:choose>
	<br /> <b><spring:message code="article.follow-ups" />: </b>
	
	<spring:message code="article.follow-ups" var="articleFollowUpsHeader" />
	<spring:message code="article.follow-up.listFollow-ups" var="articleListFollowUpsLink"/>
	<spring:message code="article.follow-up.cantShowFollowUps" var="articleCantShowFollowUps"/>
	<spring:message code="article.follow-up.noFollowUps" var="articleNoFollowUps"/>
	
	<jstl:choose>
		<jstl:when
			test="${not empty article.newspaper.publicationDate and article.isDraft eq false}">

			<security:authorize access="hasRole('USER')">
				<a href="follow-up/user/list.do?articleId=${article.id}"><jstl:out
						value="${articleListFollowUpsLink}" /></a>
			</security:authorize>
			<security:authorize access="hasRole('ADMIN')">
				<a href="follow-up/administrator/list.do?articleId=${article.id}"><jstl:out
						value="${articleListFollowUpsLink}" /></a>
			</security:authorize>
			<security:authorize access="hasRole('CUSTOMER')">
				<jstl:choose>
					<jstl:when
						test="${showFollowUps eq true and not empty article.newspaper.publicationDate and article.isDraft eq false and not empty article.followUps}">
						<a href="follow-up/customer/list.do?articleId=${row.id}"><jstl:out
								value="${articleListFollowUpsLink}" /></a>
					</jstl:when>
					<jstl:otherwise>
						<jstl:choose>
							<jstl:when test="${empty article.followUps}">
								<jstl:out value="${articleNoFollowUps}" />
							</jstl:when>
							<jstl:otherwise>
								<jstl:out value="${articleCantShowFollowUps}" />
							</jstl:otherwise>
						</jstl:choose>
					</jstl:otherwise>
				</jstl:choose>
			</security:authorize>
			<security:authorize access="isAnonymous()">
				<jstl:choose>
					<jstl:when
						test="${article.newspaper.isPrivate eq false and not empty article.followUps}">
						
						<a href="follow-up/list.do?articleId=${article.id}"><jstl:out value="${articleListFollowUpsLink}" /></a>
					</jstl:when>
					<jstl:otherwise>
						<jstl:choose>
							<jstl:when test="${empty article.followUps}">
								<jstl:out value="${articleNoFollowUps}" />
							</jstl:when>
							<jstl:otherwise>
								<jstl:out value="${articleCantShowFollowUps}" />
							</jstl:otherwise>
						</jstl:choose>
					</jstl:otherwise>
				</jstl:choose>
			</security:authorize>
		</jstl:when>
		<jstl:otherwise>
			<jstl:out value="${articleCantShowFollowUps}" />
		</jstl:otherwise>
	</jstl:choose>

	<security:authorize access="hasRole('USER')">
		<jstl:choose>
			<jstl:when test="${article.isDraft == true}">
				<spring:message var="articleEditLink" code="article.edit" />
				<a href="article/user/edit.do?articleId=${article.id}"><jstl:out
						value="${articleEditLink}" /></a>
			</jstl:when>
		</jstl:choose>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">

		<spring:message var="articleDeleteLink" code="article.delete" />
		<a href="article/administrator/delete.do?articleId=${article.id}"><jstl:out
				value="${articleDeleteLink}" /></a>

	</security:authorize>
</div>

<br />

<div>
	<b><spring:message code="article.advertisement" />: </b>
</div>

<jstl:if test="${advertisementBanner!=null}">
	<spring:url var="advertisementBannerURL"
		value="${advertisementBanner.bannerURL}" />
	<spring:url var="advertisementTargetURL"
		value="${advertisementBanner.targetPageURL}" />
	<spring:message code="article.advertisement" var="advertisement" />
	<a href="${advertisementTargetURL}"> <img
		src="${advertisementBannerURL}" alt="${advertisement}" />
	</a>
</jstl:if>
<jstl:if test="${advertisementBanner==null}">
	<spring:message code="article.advertisement.noBannerUrl" />
</jstl:if>
