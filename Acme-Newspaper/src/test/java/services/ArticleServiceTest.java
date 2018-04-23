
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ArticleServiceTest extends AbstractTest {

	// The SUT (Service Under Test) -------------------------------------------

	@Autowired
	private ArticleService		articleService;

	@Autowired
	private NewspaperService	newspaperService;


	// Tests ------------------------------------------------------------------
	/**
	 * Acme-Newspaper: Requirement 6.3
	 * 
	 * Write an article and attach it to any newspaper that has not been published, yet.
	 * Note that articles may be saved in draft mode, which allows to modify them later,
	 * or final model, which freezes them forever.
	 * 
	 * 
	 * 
	 * Positive test1: A user writes an article for a public newspaper and has not been published yet.
	 * Negative test2: A user writes an article for a public newspaper and has already been published.
	 * Positive test3: A user writes an article for a private newspaper and has not been published yet.
	 * Negative test4: A user writes an article for a private newspaper and has already been published.
	 * Negative test5: A user writes an article with an empty title
	 * Negative test6: A user writes an article with an empty summary
	 * Negative test7: A user writes an article with an empty body
	 * Negative test8: An admin tries to write an article
	 * Negative test9: A customer tries to write an article
	 * Negative test10: An unlogged user tries to write an article
	 */
	@Test
	public void testCreateArticleDriver() {

		final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));

		final Object testingData[][] = {

			/** userPrincipal,private, Date, title,summary,body,exception */
			{
				"user2", n1, false, null, "Title of article1", "Summary of article1", "Body of article1", null
			}, {
				"user2", n1, false, new Date(System.currentTimeMillis() - 1), "Title of article1", "Summary of article1", "Body of article1", IllegalArgumentException.class
			}, {
				"user2", n1, true, null, "Title of article1", "Summary of article1", "Body of article1", null
			}, {
				"user2", n1, true, new Date(System.currentTimeMillis() - 1), "Title of article1", "Summary of article1", "Body of article1", IllegalArgumentException.class
			}, {
				"user2", n1, false, null, null, "Summary of article1", "Body of article1", ConstraintViolationException.class
			}, {
				"user2", n1, false, null, "Title of article1", null, "Body of article1", ConstraintViolationException.class
			}, {
				"user2", n1, false, null, "Title of article1", "Summary of article1", null, ConstraintViolationException.class
			}, {
				"admin", n1, false, null, "Title of article1", "Summary of article1", "Summary of article1", IllegalArgumentException.class
			}, {
				"customer1", n1, false, null, "Title of article1", "Summary of article1", "Summary of article1", IllegalArgumentException.class
			}, {
				"null", n1, false, null, "Title of article1", "Summary of article1", "Summary of article1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateArticleTemplate((String) testingData[i][0], (Newspaper) testingData[i][1], (boolean) testingData[i][2], (Date) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(Class<?>) testingData[i][7]);
	}
	protected void testCreateArticleTemplate(final String username, final Newspaper n1, final boolean isPrivate, final Date publicationDate, final String title, final String summary, final String body, final Class<?> expectedException) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(username);

			final Newspaper newspaperToUse = n1;
			newspaperToUse.setIsPrivate(isPrivate);
			newspaperToUse.setPublicationDate(publicationDate);

			this.newspaperService.save(newspaperToUse);
			final Article a = this.articleService.create();
			a.setIsDraft(false);
			a.setNewspaper(newspaperToUse);
			a.setPublicationMoment(publicationDate);
			a.setBody(body);
			a.setSummary(summary);
			a.setTitle(title);
			final Collection<FollowUp> followUps = new ArrayList<FollowUp>();
			a.setFollowUps(followUps);
			final Collection<String> pictures = new ArrayList<String>();
			a.setPictures(pictures);
			a.setPictures(new ArrayList<String>());
			this.articleService.saveFromCreate(a);
			this.unauthenticate();
			this.articleService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/**
	 * Acme-Newspaper: Requirement 6.3:
	 * 
	 * Write an article and attach it to any newspaper that has not been published, yet.
	 * Note that articles may be saved in draft mode, which allows to modify them later,
	 * or final model, which freezes them forever.
	 * 
	 * Positive test1: Save a draft article from a newspaper not published
	 * Negative test2: Save an article of an already published newspaper
	 * Negative test3: Save an article that is not created by him/her
	 */

	@Test
	public void testSaveArticleDriver() {

		final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));//Publicado
		final Newspaper n4 = this.newspaperService.findOne(this.getEntityId("newspaper4"));//No publicado
		final Article a4 = this.articleService.findOne(this.getEntityId("article4"));
		a4.setIsDraft(true);
		final Article a3 = this.articleService.findOne(this.getEntityId("article1"));
		final Object testingData[][] = {

			/** userPrincipal,articulo, periodico, title,summary,body,exception */
			{
				"user2", a4, n4, "Title of article1", "Summary of article1", "Body of article1", null

			}, {
				"user2", a3, n1, "Title of article1", "Summary of article1", "Body of article1", IllegalArgumentException.class
			}, {
				"user1", a4, n4, "Title of article1", "Summary of article1", "Body of article1", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveArticleTemplate((String) testingData[i][0], (Article) testingData[i][1], (Newspaper) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);
	}
	protected void testSaveArticleTemplate(final String username, final Article a1, final Newspaper n1, final String title, final String summary, final String body, final Class<?> expectedException) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(username);

			final Newspaper newspaperToUse = n1;

			this.newspaperService.save(newspaperToUse);
			final Article a = a1;
			a.setNewspaper(newspaperToUse);
			a.setBody(body);
			a.setSummary(summary);
			a.setTitle(title);
			this.articleService.saveFromEdit(a);
			this.unauthenticate();
			this.articleService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/**
	 * Acme-Newspaper: Requirement 7.1:
	 * 
	 * Remove an article that he or she thinks is inappropriate.
	 * 
	 * Positive test1: Admin deletes an article
	 * Negative test2: A user tries to delete an article
	 * Negative test3: A customer tries to delete an article
	 * Negative test4: A null actor tries to delete an article
	 */
	@Test
	public void testDeleteArticleDriver() {

		final Article a4 = this.articleService.findOne(this.getEntityId("article4"));

		final Object testingData[][] = {

			/** userPrincipal,article,exception */
			{
				"admin", a4, null
			}, {
				"user2", a4, IllegalArgumentException.class
			}, {
				"customer1", a4, IllegalArgumentException.class
			}, {
				null, a4, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.testDeleteArticleTemplate((String) testingData[i][0], (Article) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void testDeleteArticleTemplate(final String username, final Article a1, final Class<?> expectedException) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(username);
			final Article a = a1;
			this.articleService.delete(a);
			this.unauthenticate();
			this.articleService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/**
	 * Acme-Newspaper: Requirement 4.4:
	 * 
	 * Search for a published article using a single key word that must
	 * appear somewhere in its title, summary, or body.
	 * 
	 * Positive test1: The user find with a key word that must obtain 0 resuls
	 * Positive test2: The user find with a key word contained in at least 1 article
	 */
	@Test
	public void testFindKeywordArticleDriver() {

		final Object testingData[][] = {

			/** userPrincipal,keyword, encontrados > 0 ,exception */
			{
				"user2", "odkweig3nroimo", false, null
			}, {
				"user2", "title", true, null
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.testFindKeywordArticleTemplate((String) testingData[i][0], (String) testingData[i][1], (boolean) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void testFindKeywordArticleTemplate(final String username, final String keyword, final boolean encontrados, final Class<?> expectedException) {
		this.authenticate(username);
		Collection<Article> articles = new ArrayList<Article>();
		articles = this.articleService.findArticleByKeyword(keyword);
		Assert.isTrue(encontrados == articles.size() > 0);
		this.unauthenticate();
		this.articleService.flush();

		this.unauthenticate();
	}
}
