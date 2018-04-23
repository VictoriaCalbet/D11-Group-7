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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authentication property="principal" var="loggedactor"/>

<jstl:choose>
	<jstl:when test="${followUpForm.id eq 0}"> <!-- Estoy creando un nuevo formulario -->
		
		<jstl:choose>
			<jstl:when test="${empty availableArticles}">  <!-- No hay artículos disponibles -->
				<jstl:set var="showForm" value="false"/>
				<jstl:set var="noAvailableArticles" value="true"/>
			</jstl:when>
			
			<jstl:otherwise>							   <!-- Hay artículos disponibles -->
				<jstl:set var="showForm" value="true"/>
				<jstl:set var="newForm" value="true"/>
			</jstl:otherwise>
		</jstl:choose>
		
	</jstl:when>
	
	<jstl:otherwise>						<!-- Estoy editando un formulario -->

		<jstl:set var="showForm" value="true"/>
		<jstl:set var="newForm" value="false"/>

	</jstl:otherwise>
</jstl:choose>


<jstl:choose>
	<jstl:when test="${showForm eq true}">	<!-- Mostramos el formulario -->
	
		<form:form action="${actionURI}" modelAttribute="followUpForm">
			<form:hidden path="id"/>
			<form:hidden path="userId"/>
			
			<jstl:choose>
				<jstl:when test="${newForm eq true}">
					<acme:select items="${availableArticles}" itemLabel="title" code="follow-up.article.title" path="articleId"/>
				</jstl:when>
				<jstl:otherwise>
					<form:hidden path="articleId"/>
				</jstl:otherwise>
			</jstl:choose>
			
			<acme:textbox code="follow-up.title" path="title"/>
			<acme:textbox code="follow-up.summary" path="summary"/>
			<acme:textarea code="follow-up.text" path="text"/>
			<acme:textarea code="follow-up.pictures" path="pictures"/>
			
			<acme:submit name="save" code="follow-up.save"/> &nbsp;
			<acme:cancel url="article/user/listOwnArticles.do" code="follow-up.cancel"/>
			<br/>
		</form:form>
		
	</jstl:when>
	
	<jstl:otherwise>						<!-- No mostramos el formulario... -->
		<!-- ...y mostramos los motivos -->

		<jstl:if test="${noAvailableArticles eq true}">
			<spring:message var="followUpEditArticlesNotAvailable" code="follow-up.edit.articles.notAvailable"/>
			<b><jstl:out value="${followUpEditArticlesNotAvailable}"/></b>	
		</jstl:if>
		
		<jstl:if test="${youAreNotTheProperty eq true}">
			<spring:message var="followUpEditYouAreNotTheProperty" code="follow-up.edit.youAreNotTheProperty"/>
			<b><jstl:out value="${followUpEditYouAreNotTheProperty}"/></b>	
		</jstl:if>
		
	</jstl:otherwise>
</jstl:choose>
