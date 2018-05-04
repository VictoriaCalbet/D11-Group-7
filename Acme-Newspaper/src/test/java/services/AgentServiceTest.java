/*
 * SampleTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Agent;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AgentServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private AgentService	agentService;


	// Tests ------------------------------------------------------------------

	// The following are fictitious test cases that are intended to check that 
	// JUnit works well in this project.  Just righ-click this class and run 
	// it using JUnit.

	/**
	 * Acme-Newspaper: Requirement 3.1:
	 * 
	 * An actor who is not authenticated must be able to:
	 * Register to the system as an agent.
	 * 
	 * Positive test1: Correct registration
	 * Negative test2: A user tries to register with a used username
	 * Negative test2: A user tries to register with an invalid email
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveFromCreateUser() {
		// Agent: Name, surname, postal addresses, phone numbers, email addresses, username, password, expected exception.
		final Object[][] testingData = {
			{
				"testAgentName1", "testAgentSurname1", new HashSet<>(Arrays.asList("testAgentPostalAddress1")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testAgentEmailAddress1@testAgentName1.com")), "testAgent1",
				"testAgent1", null
			},
			{
				"testAgentName2", "testAgentSurname2", new HashSet<>(Arrays.asList("testAgentPostalAddress2")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testAgentEmailAddress2@user1.com")), "user1", "user1",
				IllegalArgumentException.class
			},
			{
				"testAgentName3", "testAgentSurname3", new HashSet<>(Arrays.asList("testAgentPostalAddress3")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testUserEmailAddress1")), "testAgent3", "testAgent3",
				ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromCreateUserTemplate((String) testingData[i][0], (String) testingData[i][1], (Collection<String>) testingData[i][2], (Collection<String>) testingData[i][3], (Collection<String>) testingData[i][4], (String) testingData[i][5],
				(String) testingData[i][6], (Class<?>) testingData[i][7]);

	}

	protected void testSaveFromCreateUserTemplate(final String name, final String surname, final Collection<String> postalAddresses, final Collection<String> phoneNumbers, final Collection<String> emailAddresses, final String username,
		final String password, final Class<?> expectedException) {

		Class<?> caught = null;

		try {
			Agent result;
			UserAccount userAccount;

			result = this.agentService.create();

			result.setName(name);
			result.setSurname(surname);
			result.setPostalAddresses(postalAddresses);
			result.setPhoneNumbers(phoneNumbers);
			result.setEmailAddresses(emailAddresses);

			userAccount = result.getUserAccount();
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			result.setUserAccount(userAccount);

			this.agentService.saveFromCreate(result);
			this.agentService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}
}
