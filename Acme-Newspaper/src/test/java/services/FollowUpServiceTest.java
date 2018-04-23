
package services;

import java.util.ArrayList;
import java.util.Arrays;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.FollowUp;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FollowUpServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private FollowUpService	followUpService;

	@Autowired
	private ArticleService	articleService;


	// Tests ------------------------------------------------------------------

	/**
	 * 
	 * Acme-Newspaper 1.0: information requirement 14
	 * 
	 * - The writer of an article may write follow-ups on it. Follow-ups can be written
	 * only after an article is saved in final mode and the corresponding newspaper
	 * is published. For every follow-up, the system must store the following data:
	 * title, publication moment, summary, text and optional pictures.
	 * 
	 * Positive test 1: create a follow-up with a user that wrote the article
	 * Negative test 2: create a follow-up with a user that didn't write the article
	 * Negative test 3: create a follow-up with a customer
	 * Negative test 4: create a follow-up with a admin
	 */

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateFollowUpDriver() {
		// principal(actor), title, summary, text, pictures 
		final Object[][] testingData = {
			{
				"user1", "article1", "Title", "Summary", "Text", new ArrayList<String>(Arrays.asList("http://www.myImage.com")), null
			}, {
				"user1", "article3", "Title", "Summary", "Text", new ArrayList<String>(Arrays.asList("http://www.myImage.com")), IllegalArgumentException.class
			}, {
				"customer1", "article1", "Title", "Summary", "Text", new ArrayList<String>(Arrays.asList("http://www.myImage.com")), IllegalArgumentException.class
			}, {
				"administrator1", "article1", "Title", "Summary", "Text", new ArrayList<String>(Arrays.asList("http://www.myImage.com")), IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateFollowUpTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (ArrayList<String>) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	private void testCreateFollowUpTemplate(final String actor, final String article, final String title, final String summary, final String text, final ArrayList<String> pictures, final Class<?> expectedException) {
		Class<?> caught = null;

		try {
			this.authenticate(actor);

			FollowUp followUp = null;

			followUp = this.followUpService.create();

			followUp.setArticle(this.articleService.findOne(this.getEntityId(article)));
			followUp.setTitle(title);
			followUp.setSummary(summary);
			followUp.setText(text);
			followUp.setPictures(pictures);

			this.followUpService.saveFromCreate(followUp);
			this.followUpService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}

	/**
	 * 
	 * Acme-Newspaper 1.0: information requirement 14
	 * 
	 * - The writer of an article may write follow-ups on it. Follow-ups can be written
	 * only after an article is saved in final mode and the corresponding newspaper
	 * is published. For every follow-up, the system must store the following data:
	 * title, publication moment, summary, text and optional pictures.
	 * 
	 * Positive test 1: edit a follow-up with user that is the property
	 * Negative test 2: edit a follow-up with user that is not the property
	 * Negative test 3: edit a follow-up with customer
	 * Negative test 4: edit a follow-up with user and title is null
	 * Negative test 5: edit a follow-up with user and summary is null
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testEditFollowUpDriver() {
		final Object[][] testingData = {
			{
				"user2", "followUp1", "Title mod", "Summary mod", "Text mod", new ArrayList<String>(Arrays.asList("http://www.myImageMOD.com")), null
			}, {
				"user1", "followUp1", "Title mod", "Summary mod", "Text mod", new ArrayList<String>(Arrays.asList("http://www.myImageMOD.com")), IllegalArgumentException.class
			}, {
				"customer1", "followUp1", "Title mod", "Summary mod", "Text mod", new ArrayList<String>(Arrays.asList("http://www.myImageMOD.com")), IllegalArgumentException.class
			}, {
				"administrator1", "followUp1", "Title mod", "Summary mod", "Text mod", new ArrayList<String>(Arrays.asList("http://www.myImageMOD.com")), IllegalArgumentException.class
			}, {
				"user2", "followUp1", null, "Summary mod", "Text mod", new ArrayList<String>(Arrays.asList("http://www.myImageMOD.com")), ConstraintViolationException.class
			}, {
				"user2", "followUp1", null, "Summary mod", "Text mod", new ArrayList<String>(Arrays.asList("http://www.myImageMOD.com")), ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testEditFollowUpTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (ArrayList<String>) testingData[i][5], (Class<?>) testingData[i][6]);

	}
	private void testEditFollowUpTemplate(final String actor, final String followUpBean, final String title, final String summary, final String text, final ArrayList<String> pictures, final Class<?> expectedException) {
		Class<?> caught = null;

		try {

			this.authenticate(actor);

			FollowUp followUp = null;

			followUp = this.followUpService.findOne(this.getEntityId(followUpBean));

			followUp.setTitle(title);
			followUp.setSummary(summary);
			followUp.setText(text);
			followUp.setPictures(pictures);

			this.followUpService.saveFromEdit(followUp);
			this.followUpService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
}
