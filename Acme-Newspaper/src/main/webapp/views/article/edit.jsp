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

<form:form action="${requestURI}" modelAttribute="articleForm">
	
	<form:hidden path="id"/>
	
<security:authorize access="hasRole('USER')">

<acme:select code="article.newspaper" path="newspaper" items="${availableNewspapers}" itemLabel="title"/><br/>

<fieldset> 
	<spring:message code="article.edit.legend" var="articleLegend"/>
	<spring:message code="article.isDraft.yes" var="articleIsDraftYes"/>
	<spring:message code="article.isDraft.no" var="articleIsDraftNo"/>
	<legend><b><jstl:out value="${articleLegend}"/>:&nbsp;</b></legend>
	
	<acme:textbox code="article.title" path="title"/>
	<acme:textbox code="article.summary" path="summary"/>
	<acme:textarea code="article.body" path="body"/>
	<acme:textarea code="article.pictures" path="pictures"/>
	<jstl:choose>
		<jstl:when test="${article.id!=0 or article.isDraft==false}">
			<br>
			<b><form:label path="isDraft"><spring:message code="article.isDraft"/></form:label> </b>
			<form:select path="isDraft">				
				<form:option label="${articleIsDraftYes}" value="true"/>
				<form:option label="${articleIsDraftNo}" value="false"/>
				<form:errors cssClass="error" path="isDraft"/>
				<br>
			</form:select>
		</jstl:when>
		<jstl:otherwise> 
			<form:hidden path="isDraft"/>
		</jstl:otherwise>
	</jstl:choose>	
	<br/> <br/>
	<input type="submit" name="save" value="<spring:message code="article.write"/>"/>
	<acme:cancel url="newspaper/list.do" code="article.cancel" /> <br/>
</fieldset>

</security:authorize>

</form:form>
