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

<form:form action="${requestURI}" modelAttribute="advertisement">

	<form:hidden path="id"/>
	<form:hidden path="agent"/>
	<form:hidden path="version"/>
	
	
	<acme:select code="advertisement.newspaper" path="newspaper" items="${newspapers}" itemLabel="title"/><br/>
	<acme:textbox code="advertisement.title" path="title"/>
	<acme:textbox code="advertisement.banner" path="bannerURL"/>
	<acme:textbox code="advertisement.target" path="targetPageURL"/>

	<br/>
	<h3><strong><spring:message code="advertisement.creditcard"/></strong></h3>
	<br/>
	<acme:textbox code="advertisement.creditCard.holder" path="creditCard.holderName"/>
	<acme:textbox code="advertisement.creditCard.brand" path="creditCard.brandName"/>
	<acme:textbox code="advertisement.creditCard.number" path="creditCard.number"/>
	<acme:textbox code="advertisement.creditCard.month" path="creditCard.expirationMonth"/>
	<acme:textbox code="advertisement.creditCard.year" path="creditCard.expirationYear"/>
	<acme:textbox code="advertisement.creditCard.cvv" path="creditCard.cvv"/>
	
	<br/>
	<input type="submit" name="save" value="<spring:message code="advertisement.save"/>"/>
	<acme:cancel url="advertisement/agent/list.do" code="advertisement.cancel" /> <br/>
</form:form>