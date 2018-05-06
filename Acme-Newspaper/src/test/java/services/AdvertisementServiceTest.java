
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Advertisement;
import domain.CreditCard;
import domain.Newspaper;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdvertisementServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private AdvertisementService	advertisementService;
	@Autowired
	private NewspaperService		newspaperService;


	// Tests ------------------------------------------------------------------

	/**
	 * Acme-Newspaper 2.0: Requirement 4.2
	 * 
	 * Register an advertisement and place it in a newspaper.
	 * 
	 * 
	 * 
	 * Positive test1: Agent save an advertisement.
	 * Negative test2: Agent try to save an advertisement with wrong url banner.
	 * Negative test3: Agent try to save an advertisement with a credit card with past expiration year.
	 */
	@Test
	public void testSaveFromCreateAdvertisement() {

		//Advertisement: bannerURL, credit card expiration year, exception.
		final Object[][] testingData = {
			{
				"http://www.banner.com", 2020, null
			}, {
				"banner", 2020, ConstraintViolationException.class
			}, {
				"http://www.banner.com", 2014, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromCreateAdvertisementTemplate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	protected void testSaveFromCreateAdvertisementTemplate(final String bannerURL, final int expirationYear, final Class<?> expectedException) {
		Class<?> caught = null;
		try {

			this.authenticate("agent1");
			Advertisement advertisement;
			advertisement = this.advertisementService.create();
			advertisement.setBannerURL(bannerURL);
			advertisement.setTargetPageURL("http://www.targetpage.com");
			advertisement.setTitle("Add");
			advertisement.setNewspaper(this.newspaperService.findAll().iterator().next());
			CreditCard creditCard;
			creditCard = new CreditCard();
			creditCard.setBrandName("VISA");
			creditCard.setHolderName("Pepe");
			creditCard.setExpirationMonth(2);
			creditCard.setExpirationYear(expirationYear);
			creditCard.setNumber("4102960866754");
			creditCard.setCvv(667);
			advertisement.setCreditCard(creditCard);
			Advertisement advertisementInDB;
			advertisementInDB = this.advertisementService.saveFromCreate(advertisement);
			advertisementInDB.setTitle("Advertisement");
			this.advertisementService.saveFromEdit(advertisementInDB);
			this.unauthenticate();
			this.advertisementService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/**
	 * Acme-Newspaper 2.0: No listed requirement
	 * 
	 * Editing an advertisement
	 * 
	 * 
	 * 
	 * Positive test1: Agent1 edit an advertisement.
	 * Negative test2: Agent2 try to edit an advertisement.
	 * Negative test3: Unaunthenticate actor try to edit an advertisement.
	 */
	@Test
	public void testSaveFromEditAdvertisement() {

		//Advertisement: actor, exception.
		final Object[][] testingData = {
			{
				"agent1", null
			}, {
				"agent2", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromEditAdvertisementTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}
	protected void testSaveFromEditAdvertisementTemplate(final String actor, final Class<?> expectedException) {
		Class<?> caught = null;
		try {

			this.authenticate("agent1");
			Advertisement advertisement;
			advertisement = this.advertisementService.create();
			advertisement.setBannerURL("http://www.sdsds.com");
			advertisement.setTargetPageURL("http://www.targetpage.com");
			advertisement.setTitle("Add");
			advertisement.setNewspaper(this.newspaperService.findAll().iterator().next());
			CreditCard creditCard;
			creditCard = new CreditCard();
			creditCard.setBrandName("VISA");
			creditCard.setHolderName("Pepe");
			creditCard.setExpirationMonth(2);
			creditCard.setExpirationYear(2021);
			creditCard.setNumber("4102960866754");
			creditCard.setCvv(667);
			advertisement.setCreditCard(creditCard);
			Advertisement advertisementInDB;
			advertisementInDB = this.advertisementService.saveFromCreate(advertisement);
			this.unauthenticate();
			this.authenticate(actor);
			advertisementInDB.setTargetPageURL("https://www.google.es/");
			CreditCard creditCard2;
			creditCard2 = advertisementInDB.getCreditCard();
			creditCard2.setExpirationYear(2020);
			creditCard2.setBrandName("VISA");
			creditCard2.setHolderName("Pepe");
			creditCard2.setExpirationMonth(2);
			creditCard2.setNumber("4102960866754");
			creditCard2.setCvv(667);
			advertisementInDB.setCreditCard(creditCard);
			advertisementInDB.setTitle("Hola");
			advertisementInDB.setBannerURL("http://www.target.com");
			advertisementInDB.setNewspaper(this.newspaperService.findAll().iterator().next());
			this.advertisementService.saveFromEdit(advertisementInDB);
			this.unauthenticate();
			this.advertisementService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	/**
	 * Acme-Newspaper 2.0: Requirement 5.2
	 * 
	 * An actor who is authenticated as an administrator must be able to remove an advertisement that he or she thinks is inappropriate.
	 * 
	 * 
	 * 
	 * Positive test1: Admin remove an advertisement.
	 * Negative test2: Agent1 try to remove an advertisement.
	 * Negative test3: Unaunthenticate actor try to remove an advertisement.
	 */
	@Test
	public void testDeleteByAdminAdvertisement() {

		//Advertisement: actor, exception.
		final Object[][] testingData = {
			{
				"admin", null
			}, {
				"agent1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testDeleteByAdminAdvertisementTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}
	protected void testDeleteByAdminAdvertisementTemplate(final String actor, final Class<?> expectedException) {
		Class<?> caught = null;
		try {

			this.authenticate(actor);
			Advertisement advertisement;
			advertisement = this.advertisementService.findAll().iterator().next();
			this.advertisementService.deleteByAdmin(advertisement);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();

		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
	/**
	 * Acme-Newspaper 2.0: No listed requirement
	 * 
	 * List advertisement
	 * 
	 * 
	 * 
	 * Positive test1: List advertisements.
	 * 
	 */

	@Test
	public void testFindAllAdvertisementsAdvertisement() {

		//Advertisement: actor, exception.
		final Object[][] testingData = {
			{
				null
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.testFindAllAdvertisementsTemplate((Class<?>) testingData[i][0]);

	}
	protected void testFindAllAdvertisementsTemplate(final Class<?> expectedException) {
		Class<?> caught = null;
		try {

			Assert.isTrue(this.advertisementService.findAll().size() > 0);

		} catch (final Throwable oops) {
			caught = oops.getClass();

		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
	/**
	 * Acme-Newspaper 2.0: Requirement 6
	 * 
	 * Whenever an article from a newspaper is shown, the system must select a random advertisement to show, if any.
	 * (This is similar to list, only test one positive case).
	 * 
	 * 
	 * Positive test1: Display advertisement.
	 * 
	 */

	@Test
	public void testRandomAdvertisementByNewspaperId() {

		//Advertisement: bannerURL, credit card expiration year, exception.
		final Object[][] testingData = {
			{
				null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testRandomAdvertisementByNewspaperIdTemplate((Class<?>) testingData[i][0]);

	}
	protected void testRandomAdvertisementByNewspaperIdTemplate(final Class<?> expectedException) {
		Class<?> caught = null;
		try {

			this.authenticate("agent1");
			Advertisement advertisement;
			advertisement = this.advertisementService.create();
			advertisement.setBannerURL("http://www.google.com");
			advertisement.setTargetPageURL("http://www.targetpage.com");
			advertisement.setTitle("Add");
			Newspaper newspaper;
			newspaper = this.newspaperService.findAll().iterator().next();
			advertisement.setNewspaper(newspaper);
			CreditCard creditCard;
			creditCard = new CreditCard();
			creditCard.setBrandName("VISA");
			creditCard.setHolderName("Pepe");
			creditCard.setExpirationMonth(2);
			creditCard.setExpirationYear(2022);
			creditCard.setNumber("4102960866754");
			creditCard.setCvv(667);
			advertisement.setCreditCard(creditCard);
			Advertisement advertisementInDB;
			advertisementInDB = this.advertisementService.saveFromCreate(advertisement);
			advertisementInDB.setTitle("Advertisement");
			this.advertisementService.saveFromEdit(advertisementInDB);
			Assert.notNull(this.advertisementService.getRandomAdvertisementByNewspaperId(newspaper.getId()));
			this.unauthenticate();
			this.advertisementService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
}
