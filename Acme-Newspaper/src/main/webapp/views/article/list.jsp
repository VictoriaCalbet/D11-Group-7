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


<form action="${requestURI}" method="get">
	<label><b><spring:message code="article.KeyWord"/>:&nbsp;</b></label>
	<input type="hidden" name="newspaperId" value="${newspaperId}"> 
	<input type="text" name="word" placeholder="<spring:message code="article.KeyWord.filter"/>"> 
	<input type="submit" value="<spring:message code="article.search" />" />&nbsp;
</form>
<br/>

<display:table name="articles" id="row" requestURI="${requestURI}" pagesize="5">

	<jstl:set var="isDraft" value="${row.isDraft}" />


	<jstl:if test="${isDraft eq true}">
		<jstl:set var="style" value="background-color:#ffad9b;" />
	</jstl:if>

	<jstl:if test="${isDraft eq false}">
		<jstl:set var="style" value="background-color:transparent;" />
	</jstl:if>

	<spring:message code="article.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="false" style="${style}" />

	<spring:message code="article.body" var="bodyHeader" />
	<display:column property="body" title="${bodyHeader}" sortable="false"
		style="${style}" />

	<spring:message code="article.summary" var="summaryHeader" />
	<display:column property="summary" title="${summaryHeader}"
		sortable="false" style="${style}" />
		
	
	<spring:message code="article.publicationMoment" var="publicationMomentHeader" />
	<display:column title="${publicationMomentHeader}" style="${style}">
		<jstl:choose>
			<jstl:when test="${row.publicationMoment != null}">
				<spring:message code="article.publicationMoment.pattern" var="datePattern"/>
				<fmt:formatDate value="${row.publicationMoment}" pattern="${datePattern}"/>
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="article.publicationMoment.dateNotYetEstablished" var="articlePublicationMomentDateNotYetEstablished"/>
				<jstl:out value="${articlePublicationMomentDateNotYetEstablished}"/>
			</jstl:otherwise>
		</jstl:choose>
	</display:column>
	
	
	
	<spring:message code="article.follow-ups" var="articleFollowUpsHeader" />
	<spring:message code="article.follow-up.listFollow-ups" var="articleListFollowUpsLink"/>
	<spring:message code="article.follow-up.cantShowFollowUps" var="articleCantShowFollowUps"/>
	<spring:message code="article.follow-up.noFollowUps" var="articleNoFollowUps"/>
	<display:column title="${articleFollowUpsHeader}" style="${style}" >
		<jstl:choose>
			<jstl:when test="${not empty row.newspaper.publicationDate and row.isDraft eq false}">
			
				<security:authorize access="hasRole('USER')">
					<a href="follow-up/user/list.do?articleId=${row.id}"><jstl:out value="${articleListFollowUpsLink}"/></a>
				</security:authorize>
				<security:authorize access="hasRole('ADMIN')">
					<a href="follow-up/administrator/list.do?articleId=${row.id}"><jstl:out value="${articleListFollowUpsLink}"/></a>
				</security:authorize>
				<security:authorize access="hasRole('CUSTOMER')">
					<jstl:choose>
						<jstl:when test="${showFollowUps eq true and not empty row.newspaper.publicationDate and row.isDraft eq false and not empty row.followUps}">
							<a href="follow-up/customer/list.do?articleId=${row.id}"><jstl:out value="${articleListFollowUpsLink}"/></a>
						</jstl:when>
						<jstl:otherwise>
							<jstl:choose>
								<jstl:when test="${empty row.followUps}">
									<jstl:out value="${articleNoFollowUps}"/>
								</jstl:when>
								<jstl:otherwise>
									<jstl:out value="${articleCantShowFollowUps}"/>
								</jstl:otherwise>
							</jstl:choose>
						</jstl:otherwise>
					</jstl:choose>
				</security:authorize>
				<security:authorize access="isAnonymous()">
					<jstl:choose>
						<jstl:when test="${row.newspaper.isPrivate eq false and not empty row.followUps}">
							<a href="follow-up/list.do?articleId=${row.id}"><jstl:out value="${articleListFollowUpsLink}"/></a>
						</jstl:when>
						<jstl:otherwise>
							<jstl:choose>
								<jstl:when test="${empty row.followUps}">
									<jstl:out value="${articleNoFollowUps}"/>
								</jstl:when>
								<jstl:otherwise>
									<jstl:out value="${articleCantShowFollowUps}"/>
								</jstl:otherwise>
							</jstl:choose>
						</jstl:otherwise>
					</jstl:choose>
				</security:authorize>
			</jstl:when>
			<jstl:otherwise>
				<jstl:out value="${articleCantShowFollowUps}"/>
			</jstl:otherwise>
		</jstl:choose>
	</display:column>
	
	<spring:message code="article.pictures" var="picturesHeader" />
	<display:column title="${picturesHeader}" style="${style}">
		<jstl:choose>
			<jstl:when test="${fn:length(row.pictures)==0}">
				<spring:message code="article.noPictures" />
			</jstl:when>
		</jstl:choose>
	</display:column>
	
	<spring:message code="article.newspaper" var="newspaperHeader" />
	<display:column property="newspaper.title" title="${newspaperHeader}"
		sortable="false" style="${style}" />
		
	
	<security:authorize access="hasRole('USER')">
	<spring:message code="article.edit" var="editHeader" />
		<display:column title="${editHeader}" style="${style}">
			<jstl:choose>
				<jstl:when test="${(row.isDraft == true) and (fn:contains(principalArticles,row))}">
					<spring:message var="articleEditLink" code="article.edit"/>
					<a href="article/user/edit.do?articleId=${row.id}"><jstl:out value="${articleEditLink}"/></a>
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="article.notEditable" var="articleNotEditableMessage" />
					<jstl:out value="${articleNotEditableMessage}"/>
				</jstl:otherwise>
			</jstl:choose>
		</display:column>
	</security:authorize>
</display:table>

<security:authorize access="isAuthenticated()">
	<jstl:if test="${not empty articles}">
		<span style="background-color: #ffad9b; border-radius: 15px 50px;">&nbsp;&nbsp;
		<spring:message	code="article.draftMessage" />&nbsp;&nbsp;
		</span>
	</jstl:if>
</security:authorize>
