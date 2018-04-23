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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form action="${requestURI}" method="get">
	<label><b><spring:message code="newspaper.keyWord"/>:&nbsp;</b></label>
	<input type="text" name="word" placeholder="<spring:message code="newspaper.KeyWord.filter"/>"/> 
	<input type="submit" value="<spring:message code="newspaper.KeyWord.filter"/>"/>
</form>
<br/>

<security:authentication property="principal" var="loggedactor"/>

<display:table name="newspapers" id="row" requestURI="${requestURI}" pagesize="5">

	<jstl:set var="isPrivate" value="${row.isPrivate}" />		
	
	<jstl:if test="${isPrivate eq true}">
		<jstl:set var="style" value="background-color:PaleVioletRed;" />
	</jstl:if>
	
	<jstl:if test="${isPrivate eq false}">
		<jstl:set var="style" value="background-color:transparent;" />
	</jstl:if>

	<spring:message code="newspaper.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" style="${style}" />
	
	<spring:message code="newspaper.publicationDate" var="publicationDateHeader" />
	<spring:message code="newspaper.publicationDate.pattern" var="datePattern"/>
	<display:column title="${publicationDateHeader}" style="${style}">
		<jstl:choose>
			<jstl:when test="${not empty row.publicationDate}">
				<fmt:formatDate value="${row.publicationDate}" pattern="${datePattern}"/> 
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="newspaper.publicationDate.null" var="newspaperPublicationDateNull" />
				<jstl:out value="${newspaperPublicationDateNull}"/>	
			</jstl:otherwise>
		</jstl:choose>
	</display:column>
	
	<spring:message code="newspaper.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" style="${style}"/>

	<spring:message code="newspaper.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}" style="${style}">
		<acme:image imageURL="${row.picture}" imageNotFoundLocation="images/fotoNotFound.png" 
		codeError="newspaper.unspecifiedImage" height="60" width="60"/>	
	</display:column>

	<spring:message code="newspaper.moreInfo" var="moreInfoHeader" />	
	<display:column title="${moreInfoHeader}" style="${style}">
			<a href="newspaper/info.do?newspaperId=${row.id}">
			 	<spring:message code="newspaper.moreInfoButton" />
			</a>
	</display:column>
	

	<security:authorize access="hasAnyRole('USER, ADMIN') or isAnonymous()">	
		<spring:message code="newspaper.articles" var="articleHeader" />	
			<display:column title="${articleHeader}" style="${style}">			
				<jstl:choose>
				
					<jstl:when test="${fn:length(row.articles) !=0}">	
						<a href="article/list.do?newspaperId=${row.id}">
						 	<spring:message code="newspaper.articlesButton" />
						</a>
					</jstl:when>
					<jstl:otherwise>
						<spring:message code= "newspaper.notArticles" var="newspaperNotArticles"/>
							<jstl:out value="${newspaperNotArticles}"/> 
					</jstl:otherwise>
				</jstl:choose>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">	

		<spring:message code="newspaper.articles" var="articleHeader" />	
			<display:column title="${articleHeader}" style="${style}">
				<jstl:if test="${ns.contains(row) && isPrivate or !isPrivate}">
			
				<jstl:choose>
				
					<jstl:when test="${fn:length(row.articles) !=0}">	
						<a href="article/list.do?newspaperId=${row.id}">
						 	<spring:message code="newspaper.articlesButton" />
						</a>
					</jstl:when>
					<jstl:otherwise>
						<spring:message code= "newspaper.notArticles" var="newspaperNotArticles"/>
							<jstl:out value="${newspaperNotArticles}"/> 
					</jstl:otherwise>
				</jstl:choose>
				</jstl:if>
		</display:column>
	</security:authorize>	
	
	<security:authorize access="hasRole('USER')">
		<spring:message code="newspaper.published" var="publishedHeader" />	
		<display:column title="${publishedHeader}" style="${style}">	
			<jstl:choose>
				<jstl:when test="${row.publicationDate == null && row.publisher.userAccount.username==loggedactor.username && fn:length(row.articles)!=0}">	
					<a href="newspaper/user/publish.do?newspaperId=${row.id}">
					 	<spring:message code="newspaper.publishButton" />
					</a>
				</jstl:when>
				<jstl:when test="${row.publicationDate == null && row.publisher.userAccount.username==loggedactor.username && fn:length(row.articles)==0}">	
					<spring:message code= "newspaper.notArticles" var="newspaperNotArticles"/>
						<jstl:out value="${newspaperNotArticles}"/> 
				</jstl:when>
				
				<jstl:otherwise>
					<spring:message code= "newspaper.notPublish" var="newspaperNotPublish"/>
						<jstl:out value="${newspaperNotPublish}"/> 
				</jstl:otherwise>
			</jstl:choose>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="newspaper.delete" var="deleteHeader" />	
		<display:column title="${deleteHeader}" style="${style}">	
			<a href="newspaper/administrator/delete.do?newspaperId=${row.id}">
			 	<spring:message code="newspaper.deleteButton" />
			</a>
		</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="isAuthenticated()">
	<jstl:if test="${not empty newspapers}">
		<span style="background-color:PaleVioletRed; border-radius: 15px 50px;">&nbsp;&nbsp;<spring:message code="newspaper.isPrivate"/>&nbsp;&nbsp;</span>
	</jstl:if>
</security:authorize>

