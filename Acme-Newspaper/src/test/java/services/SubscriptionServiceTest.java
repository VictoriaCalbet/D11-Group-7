
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.CreditCard;
import domain.Subscription;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SubscriptionServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private SubscriptionService	subscriptionService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private NewspaperService	newspaperService;


	// Tests ------------------------------------------------------------------

	/**
	 * 
	 * Acme-Newspaper 1.0: Requirement 22.1
	 * 
	 * An actor who is authenticated as a customer can:
	 * - Subscribe to a private newspaper by providing a valid credit card.
	 * 
	 * Positive test 1: creating an subscription with customer and valid credit card
	 * Negative test 2: creating an subscription with admin and valid credit card
	 * Negative test 3: creating an subscription with user and valid credit card
	 * Negative test 4: creating an subscription with customer and invalid credit card
	 * Negative test 5: creating an subscription with customer that is already subscribed
	 * Negative test 6: creating an subscription with customer and public newspaper
	 */

	@Test
	public void testCreateSubscriptionDriver() {
		// principal(customer), newspaper, holdername, brandname, number, expirationMonth, expirationYear, cvv 
		final Object[][] testingData = {
			{
				"customer2", "newspaper2", "HolderName", "Brandname", "4485750721419113", 6, 2019, 673, null
			}, {
				"administrator1", "newspaper2", "HolderName", "Brandname", "4485750721419113", 6, 2019, 673, IllegalArgumentException.class
			}, {
				"user1", "newspaper2", "Holdername", "Brandname", "4485750721419113", 6, 2019, 673, IllegalArgumentException.class
			}, {
				"customer2", "newspaper2", null, "Brandname", "4485750721419113", 6, 2019, 673, IllegalArgumentException.class
			}, {
				"customer1", "newspaper2", "Holdername", "Brandname", "4485750721419113", 6, 2019, 673, IllegalArgumentException.class
			}, {
				"customer2", "newspaper1", "Holdername", "Brandname", "4485750721419113", 6, 2019, 673, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateSubscriptionTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Integer) testingData[i][5], (Integer) testingData[i][6],
				(Integer) testingData[i][7], (Class<?>) testingData[i][8]);
	}
	private void testCreateSubscriptionTemplate(final String actor, final String newspaper, final String holderName, final String brandName, final String number, final Integer expirationMonth, final Integer expirationYear, final Integer cvv,
		final Class<?> expectedException) {

		Class<?> caught = null;
		Subscription subscription = null;
		CreditCard creditCard = null;

		try {
			this.authenticate(actor);

			subscription = this.subscriptionService.create();

			creditCard = new CreditCard();

			creditCard.setHolderName(holderName);
			creditCard.setBrandName(brandName);
			creditCard.setNumber(number);
			creditCard.setExpirationMonth(expirationMonth);
			creditCard.setExpirationYear(expirationYear);
			creditCard.setCvv(cvv);

			subscription.setCreditCard(creditCard);
			subscription.setNewspaper(this.newspaperService.findOne(this.getEntityId(newspaper)));
			subscription.setCustomer(this.customerService.findByPrincipal());

			this.subscriptionService.saveFromCreate(subscription);
			this.subscriptionService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
}
