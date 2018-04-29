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

<form:form action="${requestURI}" modelAttribute="folderForm">

	<!-- Hidden attributes -->
	<form:hidden path="id"/>
	
	<!-- Editable attributes -->
	<acme:textbox code="folder.name" path="name"/>
	<acme:select items="${possibleParentFolders}" itemLabel="name" optionalRow="true" code="folder.parent" path="parentId"/>
	
	<!-- Action buttons -->
	<acme:submit name="save" code="folder.save" /> &nbsp;
	
	<jstl:if test="${folderForm.id ne 0}">
		<acme:submit name="delete" code="folder.delete" /> &nbsp;
	</jstl:if>
	
	<acme:cancel url="folder/actor/list.do" code="folder.cancel" /> <br/>	

</form:form>