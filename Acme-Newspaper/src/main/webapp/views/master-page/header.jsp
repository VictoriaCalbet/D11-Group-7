<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a href=""><img src="images/logo.jpg"
		alt="Acme-Newspaper Co., Inc." height="180"/></a>
</div>


<div>


	<ul id="jMenu">
	
		
		
		<security:authorize access="permitAll">
		
			<li><a class="fNiv" href="volume/list.do"><spring:message code="master.page.volumes"/></a></li>
			<li>
			<li><a class="fNiv" href="newspaper/list.do"><spring:message code="master.page.newspaper"/></a></li>
			<li>
				<a class="fNiv" href="user/list.do"> <spring:message code="master.page.users"/></a>
			</li>
			<li>
				<a class="fNiv" href="customer/list.do"> <spring:message code="master.page.customers"/></a>
			</li>
		</security:authorize>
		
	
		<security:authorize access="isAnonymous()">
			<li>			
			<a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/create.do">
						<spring:message code="master.page.user" /></a></li>
					<li><a href="customer/create.do">
						<spring:message code="master.page.customer" /></a></li>
					<li><a href="agent/create.do">
						<spring:message code="master.page.agent" /></a></li>
				</ul>
			</li>
		</security:authorize>
	
			<!-- #User -->
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv" href="volume/user/listMyVolumes.do"><spring:message code="master.page.volume.listMyVolumes" /></a></li>
			<li><a class="fNiv" href="newspaper/user/create.do"><spring:message code="master.page.newspaper.create" /></a></li>
			<li><a class="fNiv" href="newspaper/user/list.do"><spring:message code="master.page.newspaper.user.list" /></a></li>
			<li><a class="fNiv" href="chirp/user/list.do"><spring:message code="master.page.chirp.user.list" /></a></li>
			<li><a class="fNiv" href="chirp/user/listFollowedUsers.do"><spring:message code="master.page.chirp.user.listFollowed" /></a></li>
		    <li><a class="fNiv" href="chirp/user/listFollowers.do"><spring:message code="master.page.chirp.user.listFollowers" /></a></li>
			<li><a class="fNiv" href="chirp/user/listFollowedChirps.do"><spring:message code="master.page.chirp.user.listFollowedChirps" /></a></li>
			<li><a class="fNiv" href="article/user/create.do"> <spring:message code="master.page.create.article" /></a>
			<li><a class="fNiv" href="article/user/listOwnArticles.do"> <spring:message code="master.page.list.ownArticles" /></a>
		
		</security:authorize>
		<security:authorize access="hasRole('AGENT')">
			
			<li><a class="fNiv" href="advertisement/agent/list.do"><spring:message code="master.page.advertisement.myadvertisements" /></a></li>
			<li><a class="fNiv" href="newspaper/agent/list.do"><spring:message code="master.page.newspaper.withadvertisement" /></a></li>
			<li><a class="fNiv" href="newspaper/agent/listWithoutAdvertisement.do"><spring:message code="master.page.newspaper.withoutadvertisement" /></a></li>
		
		</security:authorize>
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
		
			<li><a class="fNiv" href="newspaper/administrator/list.do"><spring:message code="master.page.newspaper.admin.list" /></a></li>
			<li><a class="fNiv" href="chirp/administrator/list.do"><spring:message code="master.page.chirp.administrator.list" /></a></li>
			<li><a class="fNiv" href="advertisement/administrator/list.do"><spring:message code="master.page.advertisement.advertisements" /></a></li>
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/dashboard.do">
						<spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="administrator/administrator/list.do">
						<spring:message code="master.page.administrators" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="systemConfiguration/administrator/list.do"><spring:message code="master.page.systemconfiguration.administrator.list" /></a></li>
		</security:authorize>
	
		
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="newspaper/customer/list.do"><spring:message code="master.page.newspaper.user.list" /></a></li>
			<li>
				<a class="fNiv"><spring:message	code="master.page.subscriptions"/></a>
				<ul>
					<li class="arrow"></li>
					<li>
						<a href="newspaperSubscription/customer/list.do">
							<spring:message code="master.page.newspaperSubscriptions.mySubscriptions"/>
						</a>
					</li>
					<li>
						<a href="volumeSubscription/customer/list.do">
							<spring:message code="master.page.volumeSubscriptions.mySubscriptions"/>
						</a>
					</li>
				</ul>
			</li>
			
			
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('USER')">
						<li><a href="user/user/edit.do"><spring:message
									code="user.profile" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('CUSTOMER')">
						<li><a href="customer/customer/edit.do"><spring:message
									code="user.profile" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="administrator/administrator/edit.do"><spring:message
									code="user.profile" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('AGENT')">
						<li><a href="agent/agent/edit.do"><spring:message
									code="user.profile" /></a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li><a href="folder/actor/list.do"><spring:message
									code="folder.folders" /></a></li>
					</security:authorize>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

