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

<display:table name="rangers" id="row" requestURI="${requestURI}" 
	pagesize="5" class="displaytag">
	
	<display:column>
		<jstl:if test="${row.isSuspicious && !row.isBanned}">
			<a href="ranger/administrator/ban.do?rangerId=${row.id}">
				<spring:message	code="ranger.ban" />
			</a>
		</jstl:if>
		<jstl:if test="${row.isBanned}">
			<a href="ranger/administrator/unban.do?rangerId=${row.id}">
				<spring:message	code="ranger.unban" />
			</a>
		</jstl:if>
	</display:column>
		
	<spring:message code="ranger.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="ranger.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" sortable="true" />
	
	<spring:message code="ranger.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" sortable="false" />
	
	<spring:message code="ranger.phone" var="phoneHeader" />
	<display:column property="phone" title="${phoneHeader}" sortable="false" />
	
	<spring:message code="ranger.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}" sortable="false" />
	
	<spring:message code="ranger.username" var="usernameHeader" />
	<display:column property="userAccount.username" title="${usernameHeader}" sortable="true" />
			
</display:table>
