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

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Article;
import domain.FollowUp;
import domain.Newspaper;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class NewspaperServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private ArticleService		articleService;
	@Autowired
	private UserService			userService;


	// Tests ------------------------------------------------------------------

	// The following are fictitious test cases that are intended to check that 
	// JUnit works well in this project.  Just righ-click this class and run 
	// it using JUnit.

	/***
	 * 
	 * Requirement 4.2 to Acme-Newspaper
	 * 
	 * List the newspapers that are published and browse their articles
	 * 
	 * Testing cases:
	 * Positive test 1: List the newspapers published
	 */

	@Test
	public void listNewspaperAnonymous() {

		//final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));

		final Object testingData[][] = {
			//expected exception
			{
				null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.listNewspaperAnonymous((Class<?>) testingData[i][0]);
	}
	protected void listNewspaperAnonymous(final Class<?> expectedException) {
		Class<?> caught = null;

		try {
			final Collection<Newspaper> newspapers = this.newspaperService.findPublicated();
			Assert.isTrue(newspapers.size() == 1);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
		}

		this.checkExceptions(expectedException, caught);
	}

	/***
	 * 
	 * Requirement 5.1 to Acme-Newspaper
	 * 
	 * Do the same as an actor who is not authenticated, but register to the system.
	 * 
	 * Testing cases:
	 * Positive test 1: List the newspapers published
	 */

	@Test
	public void listNewspaperLogged() {

		//final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));

		final Object testingData[][] = {
			//expected exception
			{
				null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.listNewspaperLogged((Class<?>) testingData[i][0]);
	}
	protected void listNewspaperLogged(final Class<?> expectedException) {
		Class<?> caught = null;

		try {
			final Collection<Newspaper> newspapers = this.newspaperService.findPublicatedAll();
			Assert.isTrue(newspapers.size() == 2);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
		}

		this.checkExceptions(expectedException, caught);
	}

	/***
	 * 
	 * Requirement 4.5 to Acme-Newspaper
	 * 
	 * Search for a published newspaper using a single
	 * keyword that must appear somewhere in its title or its description.
	 * 
	 * Testing cases:
	 * Positive test 1: Search a word in the title
	 */

	@Test
	public void searchNewspaperAnonymous() {

		//		final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));
		final String w = "believe";

		final Object testingData[][] = {
			//expected exception
			{
				w, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.searchNewspaperAnonymous((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	protected void searchNewspaperAnonymous(final String word, final Class<?> expectedException) {
		Class<?> caught = null;

		try {
			final Collection<Newspaper> newspapers = this.newspaperService.findNewspaperByKeyWord(word);
			Assert.isTrue(newspapers.size() == 1);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
		}

		this.checkExceptions(expectedException, caught);
	}

	/***
	 * Acme-Newspaper: Requirement 6.1
	 * 
	 * Create a newspaper. A user who has created a newspaper
	 * is commonly referred to as a publisher.
	 * 
	 * Positive test 1: Create a newspaper.
	 * Negative test 2: Create a newspaper without title.
	 * Negative test 3: Create a newspaper without description.
	 */
	@Test
	public void testCreateNewspaper() {
		final Object[][] testingData = {
			// Newspaper: principal, title, description, picture, isPrivate, expected exception
			{
				"user1", "newspaper6", "description of newspaper6", "https://goo.gl/UscuZg", false, null
			}, {
				"user1", "", "description of newspaper6", "https://goo.gl/UscuZg", true, ConstraintViolationException.class
			}, {
				"user3", "newspaper6", "", "https://goo.gl/UscuZg", false, ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.createNewspaperTemplated((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (boolean) testingData[i][4], (Class<?>) testingData[i][5]);
	}

	protected void createNewspaperTemplated(final String principal, final String title, final String description, final String picture, final boolean isPrivate, final Class<?> expectedException) {
		Class<?> caught = null;

		try {
			this.authenticate(principal);
			final Newspaper n = this.newspaperService.create();
			n.setTitle(title);
			n.setDescription(description);
			n.setPicture(picture);
			n.setIsPrivate(isPrivate);
			this.newspaperService.save(n);
			this.unauthenticate();
			this.newspaperService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/***
	 * Acme-Newspaper: Requirement 6.2
	 * 
	 * Publish a newspaper that he or she's created.
	 * Note that no newspaper can be published until each of the
	 * articles of which it is composed is saved in final mode.
	 * 
	 * Positive test 1: Publish a newspaper.
	 * Negative test 2: Publish a newspaper with article in mode draft.
	 * Negative test 3: Publish a newspaper as a customer.
	 */
	@Test
	public void testPublishNewspaper() {

		final Object[][] testingData = {
			// Publish: id, date, expected exception
			{
				"user1", "newspaper6", "description of newspaper6", "https://goo.gl/UscuZg", false, "article6", "summary of article6", "body of article6", false, null
			}, {
				"user1", "newspaper6", "description of newspaper6", "https://goo.gl/UscuZg", false, "article6", "summary of article6", "body of article6", true, IllegalArgumentException.class
			}, {
				"customer1", "newspaper6", "description of newspaper6", "https://goo.gl/UscuZg", false, "article6", "summary of article6", "body of article6", false, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.publishNewspaperTemplated((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (boolean) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (boolean) testingData[i][8], (Class<?>) testingData[i][9]);
	}
	protected void publishNewspaperTemplated(final String principal, final String title, final String description, final String picture, final boolean isPrivate, final String titleArticle, final String summaryArticle, final String bodyArticle,
		final boolean isDraft, final Class<?> expectedException) {
		Class<?> caught = null;

		try {
			this.authenticate(principal);
			final Newspaper n = this.newspaperService.create();
			n.setTitle(title);
			n.setDescription(description);
			n.setPicture(picture);
			n.setIsPrivate(isPrivate);
			final Newspaper n1 = this.newspaperService.save(n);
			final Article a1 = new Article();
			a1.setTitle(titleArticle);
			a1.setBody(bodyArticle);
			a1.setSummary(summaryArticle);
			a1.setIsDraft(isDraft);
			a1.setFollowUps(new HashSet<FollowUp>());
			a1.setNewspaper(n1);
			a1.setWriter(this.userService.findByPrincipal());
			a1.setPictures(new HashSet<String>());
			final Article a2 = this.articleService.save(a1);
			n1.getArticles().add(a2);
			this.newspaperService.publish(n1.getId());

			this.unauthenticate();
			this.newspaperService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
	/***
	 * 
	 * Requirement 7.2 to Acme-Newspaper
	 * 
	 * Remove a newspaper that he or she thinks is inappropriate.
	 * Removing a newspaper implies removing all of the articles of which it is composed.
	 * 
	 * Positive test 1: Delete a newspaper from the database by an admin.
	 * Negative test 2: Delete a newspaper from the database by a user.
	 * Negative test 3: Delete a newspaper from the database by a customer.
	 */
	@Test
	public void testDeleteNewspaperAdmin() {
		final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));

		final Object[][] testingData = {
			//actor, newspaperId, expected exception
			{
				"admin", n1, null
			}, {
				"user1", n1, IllegalArgumentException.class
			}, {
				"customer1", n1, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.deleteNewspaperAdminTemplated((String) testingData[i][0], (Newspaper) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void deleteNewspaperAdminTemplated(final String principal, final Newspaper n, final Class<?> expectedException) {
		Class<?> caught = null;

		try {
			this.authenticate(principal);
			this.newspaperService.deleteAdmin(n.getId());
			this.unauthenticate();
			this.newspaperService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/***
	 * Acme-Newspaper: Requirement 23.1
	 * 
	 * Decide on whether a newspaper that he or she's created is public or private.
	 * 
	 * Positive test 1: Create a newspaper public.
	 * Positive test 2: Create a newspaper private.
	 */
	@Test
	public void testPublicOrPrivateNewspaper() {
		final Object[][] testingData = {
			// Newspaper: principal, title, description, picture, isPrivate, expected exception
			{
				"user1", "newspaper6", "description of newspaper6", "https://goo.gl/UscuZg", false, null
			}, {
				"user1", "newspaper6", "description of newspaper6", "https://goo.gl/UscuZg", true, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.publicOrPrivateNewspaperTemplated((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (boolean) testingData[i][4], (Class<?>) testingData[i][5]);
	}

	protected void publicOrPrivateNewspaperTemplated(final String principal, final String title, final String description, final String picture, final boolean isPrivate, final Class<?> expectedException) {
		Class<?> caught = null;

		try {
			this.authenticate(principal);
			final Newspaper n = this.newspaperService.create();
			n.setTitle(title);
			n.setDescription(description);
			n.setPicture(picture);
			n.setIsPrivate(isPrivate);
			this.newspaperService.save(n);
			this.unauthenticate();
			this.newspaperService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/***
	 * 
	 * Requirement 4.3 to Acme-Newspaper 2.0
	 * 
	 * List the newspapers in which they have placed an advertisement.
	 * 
	 * Testing cases:
	 * Positive test 1: List the newspapers with advertisement
	 */

	@Test
	public void listNewspaperWithAdvertisement() {

		//final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));

		final Object testingData[][] = {
			//expected exception
			{
				null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.listNewspaperAnonymous((Class<?>) testingData[i][0]);
	}
	protected void listNewspaperWithAdvertisement(final Class<?> expectedException) {
		Class<?> caught = null;

		try {
			final Collection<Newspaper> newspapers = this.newspaperService.findNewspaperWithAdvertisement();
			Assert.isTrue(newspapers.size() == 2);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
		}

		this.checkExceptions(expectedException, caught);
	}

	/***
	 * 
	 * Requirement 4.4 to Acme-Newspaper 2.0
	 * 
	 * List the newspapers in which they have not placed an advertisement.
	 * 
	 * Testing cases:
	 * Positive test 1: List the newspapers without advertisement
	 */

	@Test
	public void listNewspaperWithoutAdvertisement() {

		//final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));

		final Object testingData[][] = {
			//expected exception
			{
				null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.listNewspaperAnonymous((Class<?>) testingData[i][0]);
	}
	protected void listNewspaperWithoutAdvertisement(final Class<?> expectedException) {
		Class<?> caught = null;

		try {
			final Collection<Newspaper> newspapers = this.newspaperService.findNewspaperWithoutAdvertisement();
			Assert.isTrue(newspapers.size() == 1);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
		}

		this.checkExceptions(expectedException, caught);
	}
}
