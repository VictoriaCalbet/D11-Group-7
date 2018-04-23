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

<form:form action="${requestURI}" modelAttribute="chirpForm">

	<!-- Hidden attributes -->
	<form:hidden path="id"/>
	<form:hidden path="publicationMoment"/>
	<!-- Editable attributes -->
	
	<acme:textbox code="chirp.title" path="title"/>
	<acme:textarea code="chirp.description" path="description"/>	
	
		<!-- Action buttons -->
	<acme:submit name="save" code="chirp.save" /> &nbsp;
	<acme:cancel url="/" code="chirp.cancel" /> <br/>

</form:form>

