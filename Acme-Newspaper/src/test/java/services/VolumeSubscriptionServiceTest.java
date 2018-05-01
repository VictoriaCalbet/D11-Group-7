
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.CreditCard;
import domain.VolumeSubscription;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class VolumeSubscriptionServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private VolumeSubscriptionService	volumeSubscriptionService;

	@Autowired
	private VolumeService				volumeService;

	@Autowired
	private CustomerService				customerService;


	// Tests ------------------------------------------------------------------

	/**
	 * 
	 * Acme-Newspaper 2.0: Requirement 9.1
	 * 
	 * An actor who is authenticated as a customer can:
	 * - Subscribe to a volume by providing a credit card. Note that subscribing to a
	 * volume implies subscribing automatically to all of the newspapers of which
	 * it is composed, including newspapers that might be published after the
	 * subscription takes place.
	 * 
	 * Positive test 1: creating a volume subscription with customer and valid credit card
	 * Negative test 2: creating a volume subscription with admin and valid credit card
	 * Negative test 3: creating a volume subscription with user and valid credit card
	 * Negative test 4: creating a volume subscription with customer and invalid credit card
	 * Negative test 5: creating a volume subscription with customer that is already subscribed
	 */

	@Test
	public void testCreateVolumeSubscriptionDriver() {
		// principal(customer), volume, holdername, brandname, number, expirationMonth, expirationYear, cvv 
		final Object[][] testingData = {
			{
				"customer2", "volume1", "HolderName", "Brandname", "4485750721419113", 6, 2019, 673, null
			}
		//			, {
		//				"administrator1", "volume1", "HolderName", "Brandname", "4485750721419113", 6, 2019, 673, IllegalArgumentException.class
		//			}, {
		//				"user1", "volume1", "Holdername", "Brandname", "4485750721419113", 6, 2019, 673, IllegalArgumentException.class
		//			}, {
		//				"customer2", "volume1", null, "Brandname", "4485750721419113", 6, 2019, 673, IllegalArgumentException.class
		//			}, {
		//				"customer1", "volume1", "Holdername", "Brandname", "4485750721419113", 6, 2019, 673, IllegalArgumentException.class
		//			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateVolumeSubscriptionTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Integer) testingData[i][5], (Integer) testingData[i][6],
				(Integer) testingData[i][7], (Class<?>) testingData[i][8]);
	}

	private void testCreateVolumeSubscriptionTemplate(final String actor, final String volume, final String holderName, final String brandName, final String number, final Integer expirationMonth, final Integer expirationYear, final Integer cvv,
		final Class<?> expectedException) {

		Class<?> caught = null;
		VolumeSubscription volumeSubscription = null;
		CreditCard creditCard = null;

		try {
			this.authenticate(actor);

			volumeSubscription = this.volumeSubscriptionService.create();

			creditCard = new CreditCard();

			creditCard.setHolderName(holderName);
			creditCard.setBrandName(brandName);
			creditCard.setNumber(number);
			creditCard.setExpirationMonth(expirationMonth);
			creditCard.setExpirationYear(expirationYear);
			creditCard.setCvv(cvv);

			volumeSubscription.setCreditCard(creditCard);
			volumeSubscription.setVolume(this.volumeService.findOne(this.getEntityId(volume)));
			volumeSubscription.setCustomer(this.customerService.findByPrincipal());

			this.volumeSubscriptionService.saveFromCreate(volumeSubscription);
			this.volumeSubscriptionService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}
}
