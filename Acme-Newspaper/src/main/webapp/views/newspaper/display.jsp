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

<jsp:useBean id="now" class="java.util.Date"/>

<div>
 <b><spring:message code="newspaper.title" />: </b><jstl:out value="${newspaper.title}" />
 <br/>
 <b><spring:message code="newspaper.description" />: </b><jstl:out value="${newspaper.description}" />
 <br/>
 <b><spring:message code="newspaper.picture" />:</b>
 <br/>

		<jstl:if test="${newspaper.picture!=null}">
			<acme:image height="100" imageURL="${newspaper.picture}" width="100" codeError="newspaper.unspecifiedImage" imageNotFoundLocation="images/fotoNotFound.png"/>
		</jstl:if>
		<jstl:if test="${newspaper.picture==null}">
			<spring:message code="newspaper.unspecifiedImage"/>
		</jstl:if>

<jstl:if test="${visible}">
  <h3><spring:message code="newspaper.articles"/></h3>
  <display:table name="${articles}" id="row" requestURI="newspaper/info.do" pagesize="5">
	
	<spring:message code="newspaper.articles.title" var="articleTitleHeader" />	
	<display:column title="${articleTitleHeader}">	
		<a href="article/display.do?articleId=${row.id}">
		 	${row.title}
		</a>
	</display:column>
	
	<spring:message code="newspaper.articles.writer" var="articleWriterHeader" />	
	<display:column title="${articleWriterHeader}">	
		<a href="user/display.do?userId=${row.writer.id}">
		 	${row.writer.name}
		</a>
	</display:column>
	
	<jstl:set var = "string1" value = "${row.summary}"/>
	<jstl:set var = "string2" value = "${fn:substring(string1, 0, 15)} ..." />
	<jstl:set var = "idS" value = "${row.id}"/>
	
	<spring:message code="newspaper.articles.summary" var="articleSummaryHeader" />
	<display:column title="${articleSummaryHeader}">

	<p><a href="javascript:mostrar(${idS});">${string2}</a></p>
	<div id="${idS}" style="display:none;">
     ${string1}
	</div>
	
	</display:column>
	
  </display:table>
  </jstl:if>
</div>

<script type="text/javascript">
	function mostrar(id) {
	    div = document.getElementById(id);
	    div.style.display = '';
	}

</script>

