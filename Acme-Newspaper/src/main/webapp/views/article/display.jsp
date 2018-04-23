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
 <div>	
 <b><spring:message code="article.title" />: </b><jstl:out value="${article.title}" />
 <br/>
 <b><spring:message code="article.summary" />: </b><jstl:out value="${article.summary}" />
 <br/>
 <b><spring:message code="article.body" />: </b><jstl:out value="${article.body}" />
 <br/>
 <b>
 <spring:message code="article.pictures"/>:		</b>			
	<jstl:choose>
		<jstl:when test="${fn:length(article.pictures)==0}">
			 	<spring:message code="article.noPictures"  />
	</jstl:when>
	<jstl:otherwise>
	<jstl:out value="${article.pictures}"/>
	</jstl:otherwise>
	</jstl:choose>
 <br/>
 
 <security:authorize access="hasRole('USER')">
 <jstl:choose>
 <jstl:when test="${article.isDraft == true}">
 <spring:message var="articleEditLink" code="article.edit"/>
 <a href="article/user/edit.do?articleId=${article.id}"><jstl:out value="${articleEditLink}"/></a>
 </jstl:when>
 </jstl:choose>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
 
 <spring:message var="articleDeleteLink" code="article.delete"/>
 <a href="article/administrator/delete.do?articleId=${article.id}"><jstl:out value="${articleDeleteLink}"/></a>

</security:authorize>
</div>
