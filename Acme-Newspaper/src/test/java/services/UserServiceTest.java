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
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private UserService	userService;


	// Tests ------------------------------------------------------------------

	// The following are fictitious test cases that are intended to check that 
	// JUnit works well in this project.  Just righ-click this class and run 
	// it using JUnit.

	/**
	 * Acme-Newspaper: Requirement 4.1:
	 * 
	 * An actor who is not authenticated must be able to:
	 * Register to the system as a user.
	 * 
	 * Positive test1: Correct registration
	 * Negative test2: A user tries to register with a used username
	 * Negative test2: A user tries to register with an invalid email
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveFromCreateUser() {
		// Customer: Name, surname, postal addresses, phone numbers, email addresses, username, password, expected exception.
		final Object[][] testingData = {
			{
				"testUserName1", "testUserSurname1", new HashSet<>(Arrays.asList("testUserPostalAddress1")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testUserEmailAddress1@testUserName1.com")), "testUser1", "testUser1", null
			},
			{
				"testUserName1", "testUserSurname1", new HashSet<>(Arrays.asList("testUserPostalAddress1")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testUserEmailAddress1@user1.com")), "user1", "user1",
				IllegalArgumentException.class
			},
			{
				"testUserName1", "testUserSurname1", new HashSet<>(Arrays.asList("testUserPostalAddress1")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testUserEmailAddress1")), "testUser3", "testUser3",
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
			User result;
			UserAccount userAccount;

			result = this.userService.create();

			result.setName(name);
			result.setSurname(surname);
			result.setPostalAddresses(postalAddresses);
			result.setPhoneNumbers(phoneNumbers);
			result.setEmailAddresses(emailAddresses);

			userAccount = result.getUserAccount();
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			result.setUserAccount(userAccount);

			this.userService.saveFromCreate(result);
			this.userService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}
}
