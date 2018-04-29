<%--
 * dashboard.jsp
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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('ADMIN')">

	<spring:message code="dashboard.avg" var="dashboardAvg"/>
	<spring:message code="dashboard.std" var="dashboardStd"/>
	<spring:message code="dashboard.min" var="dashboardMin"/>
	<spring:message code="dashboard.max" var="dashboardMax"/>
	<spring:message code="dashboard.ratio" var="dashboardRatio"/>
	<spring:message code="dashboard.newspaper.title" var="newspaperTitle"/>
	<spring:message code="dashboard.article.title" var="articleTitle"/>
	
	<!-- Dashboard 1 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgNewspaperCreatedPerUser"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardAvg}"/>:&nbsp; </b> <jstl:out value="${avgNewspaperCreatedPerUser}"/></td>
			<td> <b> <jstl:out value="${dashboardStd}"/>:&nbsp; </b> <jstl:out value="${stdNewspaperCreatedPerUser}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 2 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgArticlesWrittenByWriter"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardAvg}"/>:&nbsp; </b> <jstl:out value="${avgArticlesWrittenByWriter}"/></td>
			<td> <b> <jstl:out value="${dashboardStd}"/>:&nbsp; </b> <jstl:out value="${stdArticlesWrittenyByWriter}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 3 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgArticlesPerNewspaper"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardAvg}"/>:&nbsp; </b> <jstl:out value="${avgArticlesPerNewspaper}"/></td>
			<td> <b> <jstl:out value="${dashboardStd}"/>:&nbsp; </b> <jstl:out value="${stdArticlesPerNewspaper}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 4 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.newspapersThatHaveAtLeast10PerCentMoreArticlesThatTheAvg"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${newspaperTitle}"/>:&nbsp; </b> </td>
		</tr>
		<jstl:forEach items="${newspapersThatHaveAtLeast10PerCentMoreArticlesThatTheAvg}" var="news">
			<tr>
				<td> <jstl:out value="${news.title}"/></td>
			</tr>
		</jstl:forEach>
	</table>
	
	<!-- Dashboard 5 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.newspapersThatHaveAtLeast10PerCentFewerArticlesThatTheAvg"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${newspaperTitle}"/>:&nbsp; </b> </td>
		</tr>
		<jstl:forEach items="${newspapersThatHaveAtLeast10PerCentFewerArticlesThatTheAvg}" var="news">
			<tr>
				<td> <jstl:out value="${news.title}"/></td>
			</tr>
		</jstl:forEach>
	</table>
	
	<!-- Dashboard 6 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.ratioOfUsersWhoHaveEverCreatedANewspaper"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardRatio}"/>:&nbsp; </b> <jstl:out value="${ratioOfUsersWhoHaveEverCreatedANewspaper}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 6 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.ratioOfUsersWhoHaveEverWrittenAnArticle"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardRatio}"/>:&nbsp; </b> <jstl:out value="${ratioOfUsersWhoHaveEverWrittenAnArticle}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 7 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgFollowUpsPerArticle"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardAvg}"/>:&nbsp; </b> <jstl:out value="${avgFollowUpsPerArticle}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 8 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardAvg}"/>:&nbsp; </b> <jstl:out value="${avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 9 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgNoFollowUpsPerArticleUpToTwoWeeksAfterTheCorrespondingNewspapersBeenPublished"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardAvg}"/>:&nbsp; </b> <jstl:out value="${avgNoFollowUpsPerArticleUpToTwoWeeksAfterTheCorrespondingNewspapersBeenPublished}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 10 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgNoChirpsPerUser"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardAvg}"/>:&nbsp; </b> <jstl:out value="${avgNoChirpsPerUser}"/></td>
			<td> <b> <jstl:out value="${dashboardStd}"/>:&nbsp; </b> <jstl:out value="${stdNoChirpsPerUser}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 11 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardRatio}"/>:&nbsp; </b> <jstl:out value="${ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser}"/></td>
		</tr>
	</table>

	<!-- Dashboard 12 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.ratioOfPublicVsPrivateNewspapers"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardRatio}"/>:&nbsp; </b> <jstl:out value="${ratioOfPublicVsPrivateNewspapers}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 13 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgNoArticlesPerPrivateNewspapers"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardAvg}"/>:&nbsp; </b> <jstl:out value="${avgNoArticlesPerPrivateNewspapers}"/></td>
		</tr>
	</table>
	

	<!-- Dashboard 13 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgNoArticlesPerPublicNewspapers"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardAvg}"/>:&nbsp; </b> <jstl:out value="${avgNoArticlesPerPublicNewspapers}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 14 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardRatio}"/>:&nbsp; </b> <jstl:out value="${ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 15 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgRatioOfPrivateVsPublicNewspaperPerPublisher"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardRatio}"/>:&nbsp; </b> <jstl:out value="${avgRatioOfPrivateVsPublicNewspaperPerPublisher}"/></td>
		</tr>
	</table>

	<!-- Acme - Newspaper 2.0 -->
	
	<!-- Dashboard 1 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.ratioOfNewspapersWithAtLeastOneAdvertisementVsNewspapersWithNoOne"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardRatio}"/>:&nbsp; </b> <jstl:out value="${ratioOfNewspapersWithAtLeastOneAdvertisementVsNewspapersWithNoOne}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 2 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.ratioOfAdvertisementsWithTabooWords"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardRatio}"/>:&nbsp; </b> <jstl:out value="${ratioOfAdvertisementsWithTabooWords}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 3 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.avgNewspaperPerVolume"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardRatio}"/>:&nbsp; </b> <jstl:out value="${avgNewspaperPerVolume}"/></td>
		</tr>
	</table>
	
	<!-- Dashboard 4 -->
	<table border="1">
		<tr>
			<td colspan="2"> <b> <spring:message code="administrator.ratioOfVolumeSubscriptionsVsNewspaperSubscription"/>:&nbsp; </b> </td>
		</tr>
		<tr>
			<td> <b> <jstl:out value="${dashboardRatio}"/>:&nbsp; </b> <jstl:out value="${ratioOfVolumeSubscriptionsVsNewspaperSubscription}"/></td>
		</tr>
	</table>
	
</security:authorize>