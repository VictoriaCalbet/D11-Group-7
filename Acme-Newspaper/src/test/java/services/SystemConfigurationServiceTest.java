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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Article;
import domain.Chirp;
import domain.Newspaper;
import domain.SystemConfiguration;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SystemConfigurationServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private SystemConfigurationService	systemConfigurationService;
	@Autowired
	private ChirpService				chirpService;
	@Autowired
	private UserService					userService;
	@Autowired
	private NewspaperService			newspaperService;


	// Tests ------------------------------------------------------------------
	/**
	 * Acme-Newspaper: Requirement 17.1
	 * 
	 * Manage a list of taboo words
	 * 
	 * 
	 * 
	 * Positive test1: Admin create SystemConfiguration.
	 * Negative test2: User1 try to create SystemConfiguration.
	 * Negative test3: Unregistrered user try to create SystemConfiguration.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveFromCreateSystemConfiguration() {

		final List<String> tabooWords = new ArrayList<String>();
		tabooWords.add("chaos");
		tabooWords.add("chemical");
		// SystemConfiguration: tabooWords, actor, exception.
		final Object[][] testingData = {
			{
				tabooWords, "admin", null
			}, {
				tabooWords, "user1", IllegalArgumentException.class
			}, {
				tabooWords, null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromCreateSystemConfigurationTemplate((List<String>) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	protected void testSaveFromCreateSystemConfigurationTemplate(final List<String> tabooWords, final String actor, final Class<?> expectedException) {
		Class<?> caught = null;
		try {

			this.authenticate(actor);
			SystemConfiguration systemConfiguration;
			systemConfiguration = this.systemConfigurationService.create();
			systemConfiguration.getTabooWords().addAll(tabooWords);
			this.systemConfigurationService.saveFromCreate(systemConfiguration);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/**
	 * Acme-Newspaper: Requirement 17.1
	 * 
	 * Manage a list of taboo words
	 * 
	 * 
	 * 
	 * Positive test1: Admin edit SystemConfiguration.
	 * Negative test2: User1 try to edit SystemConfiguration.
	 * Negative test3: Unregistrered user try to edit SystemConfiguration.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveFromEditSystemConfiguration() {

		final List<String> tabooWords = new ArrayList<String>();
		tabooWords.add("chaos");
		tabooWords.add("chemical");
		// SystemConfiguration: tabooWords, actor, exception.
		final Object[][] testingData = {
			{
				tabooWords, "admin", null
			}, {
				tabooWords, "user1", IllegalArgumentException.class
			}, {
				tabooWords, null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromCreateSystemConfigurationTemplate((List<String>) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	protected void testSaveFromEditSystemConfigurationTemplate(final List<String> tabooWords, final String actor, final Class<?> expectedException) {
		Class<?> caught = null;
		try {
			this.authenticate(actor);
			SystemConfiguration systemConfiguration;
			systemConfiguration = this.systemConfigurationService.findMain();
			systemConfiguration.getTabooWords().removeAll(systemConfiguration.getTabooWords());
			systemConfiguration.getTabooWords().addAll(tabooWords);
			this.systemConfigurationService.saveFromEdit(systemConfiguration);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
	/**
	 * Acme-Newspaper: Requirement 17.1
	 * 
	 * Manage a list of taboo words
	 * 
	 * 
	 * 
	 * Positive test1: Admin get SystemConfiguration.
	 * Negative test2: User1 try to get SystemConfiguration.
	 * Negative test3: Unregistrered user try to get SystemConfiguration.
	 */
	@Test
	public void testFindMain() {
		// actor, exception.
		final Object[][] testingData = {
			{
				"admin", null
			}, {
				"user1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testFindMainTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	protected void testFindMainTemplate(final String actor, final Class<?> expectedException) {
		Class<?> caught = null;
		try {
			this.authenticate(actor);
			this.systemConfigurationService.findMain();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
	/**
	 * Acme-Newspaper: Requirement 17.3
	 * 
	 * List the newspapers that contain taboo words.
	 * 
	 * 
	 * 
	 * Positive test1: Admin get taboo Newspapers.
	 * Negative test2: User1 try to get taboo Newspapers.
	 * Negative test3: Unregistrered user try to get taboo Newspapers.
	 */
	@Test
	public void testGetTabooNewpapers() {
		// actor, exception.
		final Object[][] testingData = {
			{
				"admin", null
			}, {
				"user1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testGetTabooNewspapersTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	protected void testGetTabooNewspapersTemplate(final String actor, final Class<?> expectedException) {
		Class<?> caught = null;
		try {

			this.createNewspaper("Sex", "sex");
			this.authenticate(actor);
			Collection<Newspaper> tabooNewspapers;

			tabooNewspapers = this.systemConfigurationService.getTabooNewspapers();
			Assert.isTrue(tabooNewspapers.size() == 1);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
	/**
	 * Acme-Newspaper: Requirement 17.2
	 * 
	 * List the articles that contain taboo words.
	 * 
	 * 
	 * 
	 * Positive test1: Admin get taboo Articles.
	 * Negative test2: User1 try to get taboo Articles.
	 * Negative test3: Unregistrered user try to get taboo Articles.
	 */
	@Test
	public void testGetTabooArticles() {
		// actor, exception.
		final Object[][] testingData = {
			{
				"admin", null
			}, {
				"user1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testGetTabooArticlesTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	protected void testGetTabooArticlesTemplate(final String actor, final Class<?> expectedException) {
		Class<?> caught = null;
		try {
			this.authenticate(actor);
			Collection<Article> tabooArticles;

			tabooArticles = this.systemConfigurationService.getTabooArticles();
			Assert.isTrue(tabooArticles.size() == 0);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
	/**
	 * Acme-Newspaper: Requirement 17.4
	 * 
	 * List the chirps that contain taboo words.
	 * 
	 * 
	 * 
	 * Positive test1: Admin get taboo Chirps.
	 * Negative test2: User1 try to get taboo Chirps.
	 * Negative test3: Unregistrered user try to get taboo Chirps.
	 */
	@Test
	public void testGetTabooChirps() {
		// actor, exception.
		final Object[][] testingData = {
			{
				"admin", null
			}, {
				"user1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testGetTabooArticlesTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	protected void testGetTabooChirpsTemplate(final String actor, final Class<?> expectedException) {
		Class<?> caught = null;
		try {
			this.createChirp("sex", "jkhadkshds sex");
			this.authenticate(actor);
			Collection<Chirp> tabooChirps;

			tabooChirps = this.systemConfigurationService.getTabooChirps();
			Assert.isTrue(tabooChirps.size() == 1);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	protected void createNewspaper(final String title, final String description) {
		this.authenticate("user1");
		final Newspaper n = this.newspaperService.create();
		n.setTitle(title);
		n.setDescription(description);
		n.setIsPrivate(false);
		this.newspaperService.save(n);
		this.unauthenticate();
		this.newspaperService.flush();
	}

	protected void createChirp(final String title, final String description) {

		this.authenticate("user1");

		final Chirp result = this.chirpService.create();

		result.setTitle(title);
		result.setDescription(description);
		result.setUser(this.userService.findOne(this.getEntityId("user1")));

		this.chirpService.saveFromCreate(result);
		this.unauthenticate();
		this.chirpService.flush();
	}
}
