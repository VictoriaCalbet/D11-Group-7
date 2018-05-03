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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="volumeForm">
	
	<form:hidden path="id"/>
	
<security:authorize access="hasRole('USER')">


	<spring:message code="volume.title" var="title"/>
	<acme:textbox code="volume.title" path="title"/>
	
	<spring:message code="volume.description" var="description"/>
	<acme:textbox code="volume.description" path="description"/>
	
	<spring:message code="volume.year" var="year"/>
	<acme:textbox code="volume.year" path="year"/>
	
	<acme:selectMultiple items="${availableNewspapers}"  itemLabel="title" code="volume.newspapers" path="newspapers" />
     <jstl:out value="${newspapersSelectInfo}"> <spring:message code="volume.newspapersSelectInfo" var="newspapersSelectInfo" /></jstl:out>
	<spring:message code="volume.selectNewspapers"/>
	<br/> <br/>
	<input type="submit" name="save" value="<spring:message code="volume.save"/>"/>
	<acme:cancel url="volume/list.do" code="volume.cancel" /> <br/>

</security:authorize>

</form:form>


