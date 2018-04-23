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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Administrator;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;


	// Tests ------------------------------------------------------------------

	// The following are fictitious test cases that are intended to check that 
	// JUnit works well in this project.  Just righ-click this class and run 
	// it using JUnit.

	/**
	 * Acme-Newspaper: Requirement not listed:
	 * 
	 * An actor who is not authenticated must be able to:
	 * Register to the system as a customer.
	 * 
	 * Positive test1: Correct registration
	 * Negative test2: An admin tries to register with a used username
	 * Negative test3: An admin tries to register with a invalid email
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveFromCreateAdmin() {
		// Administrator: Name, surname, postal addresses, phone numbers, email addresses, username, password, expected exception.
		final Object[][] testingData = {
			{
				"testAdminName1", "testAdminSurname1", new HashSet<>(Arrays.asList("testAdminPostalAddress1")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testAdminEmailAddress1@testAdmin1.com")), "testAdmin1", "testAdmin1",
				null
			},
			{
				"testAdminName1", "testAdminSurname1", new HashSet<>(Arrays.asList("testAdminPostalAddress1")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testAdminEmailAddress1@admin.com")), "admin", "testAdmin1",
				IllegalArgumentException.class
			},
			{
				"testAdminName1", "testAdminSurname1", new HashSet<>(Arrays.asList("testAdminPostalAddress1")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testAdminEmailAddress1")), "testAdmin1", "testAdmin1",
				IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromCreateAdminTemplate((String) testingData[i][0], (String) testingData[i][1], (Collection<String>) testingData[i][2], (Collection<String>) testingData[i][3], (Collection<String>) testingData[i][4], (String) testingData[i][5],
				(String) testingData[i][6], (Class<?>) testingData[i][7]);

	}
	protected void testSaveFromCreateAdminTemplate(final String name, final String surname, final Collection<String> postalAddresses, final Collection<String> phoneNumbers, final Collection<String> emailAddresses, final String username,
		final String password, final Class<?> expectedException) {

		Class<?> caught = null;

		try {
			this.authenticate("admin");
			Administrator result;
			UserAccount userAccount;

			result = this.administratorService.create();

			result.setName(name);
			result.setSurname(surname);
			result.setPostalAddresses(postalAddresses);
			result.setPhoneNumbers(phoneNumbers);
			result.setEmailAddresses(emailAddresses);

			userAccount = result.getUserAccount();
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			result.setUserAccount(userAccount);

			this.administratorService.saveFromCreate(result);
			this.administratorService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}

}
