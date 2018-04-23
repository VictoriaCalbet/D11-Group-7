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
import domain.Customer;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CustomerServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private CustomerService	customerService;


	// Tests ------------------------------------------------------------------

	// The following are fictitious test cases that are intended to check that 
	// JUnit works well in this project.  Just righ-click this class and run 
	// it using JUnit.

	/**
	 * Acme-Newspaper: Requirement 21.1:
	 * 
	 * An actor who is not authenticated must be able to:
	 * Register to the system as a customer.
	 * 
	 * Positive test1: Correct registration
	 * Negative test2: A customer tries to register with a used username
	 * Negative test3: A customer tries to register with a invalid email
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveFromCreateCustomer() {
		// Customer: Name, surname, postal addresses, phone numbers, email addresses, username, password, expected exception.
		final Object[][] testingData = {
			{
				"testCustomerName1", "testCustomerSurname1", new HashSet<>(Arrays.asList("testCustomerPostalAddress1")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testCustomerEmailAddress1@testCustomer1.com")),
				"testCustomer1", "testCustomer1", null
			},
			{
				"testCustomerName1", "testCustomerSurname1", new HashSet<>(Arrays.asList("testCustomerPostalAddress1")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testCustomerEmailAddress1@customer1.com")), "customer1",
				"customer1", IllegalArgumentException.class
			},
			{
				"testCustomerName1", "testCustomerSurname1", new HashSet<>(Arrays.asList("testCustomerPostalAddress1")), new HashSet<>(Arrays.asList("619619619")), new HashSet<>(Arrays.asList("testCustomerEmailAddress1")), "testCustomer1", "customer1",
				IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromCreateCustomerTemplate((String) testingData[i][0], (String) testingData[i][1], (Collection<String>) testingData[i][2], (Collection<String>) testingData[i][3], (Collection<String>) testingData[i][4], (String) testingData[i][5],
				(String) testingData[i][6], (Class<?>) testingData[i][7]);

	}

	protected void testSaveFromCreateCustomerTemplate(final String name, final String surname, final Collection<String> postalAddresses, final Collection<String> phoneNumbers, final Collection<String> emailAddresses, final String username,
		final String password, final Class<?> expectedException) {

		Class<?> caught = null;

		try {
			Customer result;
			UserAccount userAccount;

			result = this.customerService.create();

			result.setName(name);
			result.setSurname(surname);
			result.setPostalAddresses(postalAddresses);
			result.setPhoneNumbers(phoneNumbers);
			result.setEmailAddresses(emailAddresses);

			userAccount = result.getUserAccount();
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			result.setUserAccount(userAccount);

			this.customerService.saveFromCreate(result);
			this.customerService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}

}
