
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NewspaperSubscriptionRepository;
import domain.CreditCard;
import domain.Customer;
import domain.Newspaper;
import domain.NewspaperSubscription;

@Service
@Transactional
public class NewspaperSubscriptionService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private NewspaperSubscriptionRepository	newspaperSubscriptionRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CustomerService					customerService;

	@Autowired
	private NewspaperService				newspaperService;


	// Constructors -----------------------------------------------------------

	public NewspaperSubscriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public NewspaperSubscription create() {
		NewspaperSubscription result = null;

		result = new NewspaperSubscription();
		result.setCustomer(this.customerService.findByPrincipal());

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public NewspaperSubscription save(final NewspaperSubscription subscription) {
		Assert.notNull(subscription);
		NewspaperSubscription result;
		result = this.newspaperSubscriptionRepository.save(subscription);
		return result;
	}

	public NewspaperSubscription saveFromCreate(final NewspaperSubscription newspaperSubscription) {
		Assert.notNull(newspaperSubscription, "message.error.newspaperSubscription.null");
		Assert.isTrue(this.checkCreditCard(newspaperSubscription.getCreditCard()), "message.error.newspaperSubscription.invalidCreditCard");
		Assert.isTrue(newspaperSubscription.getNewspaper().getIsPrivate(), "message.error.newspaperSubscription.isPublicNewspaper");
		Assert.notNull(newspaperSubscription.getNewspaper().getPublicationDate(), "message.error.newspaperSubscription.publicationDateNotDefined");

		NewspaperSubscription result = null;
		Customer customer = null;
		Newspaper newspaper = null;

		customer = this.customerService.findByPrincipal();
		newspaper = this.newspaperService.findOne(newspaperSubscription.getNewspaper().getId());

		Assert.notNull(customer, "message.error.newspaperSubscription.customer.null");
		Assert.isTrue(customer.equals(newspaperSubscription.getCustomer()), "message.error.newspaperSubscription.isNotTheSameCustomer");

		// Paso 1: realizo la entidad del servicio NewspaperSubscription

		Assert.isTrue(!this.newspaperSubscriptionRepository.isThisCustomerSubscribeOnThisNewspaper(customer, newspaper), "message.error.newspaperSubscription.itsAlreadySubscribed");
		result = this.save(newspaperSubscription);
		Assert.isTrue(this.newspaperSubscriptionRepository.isThisCustomerSubscribeOnThisNewspaper(customer, newspaper), "message.error.newspaperSubscription.itsAlreadySubscribed");

		// Paso 2: persisto el resto de relaciones a las que el objeto NewspaperSubscription estén relacionadas

		result.getCustomer().getNewspaperSubscriptions().add(result);
		result.getNewspaper().getNewspaperSubscriptions().add(result);

		return result;
	}

	public void flush() {
		this.newspaperSubscriptionRepository.flush();
	}

	public Collection<NewspaperSubscription> findAll() {
		Collection<NewspaperSubscription> result = null;
		result = this.newspaperSubscriptionRepository.findAll();
		return result;
	}

	public NewspaperSubscription findOne(final int subscriptionId) {
		NewspaperSubscription result = null;
		result = this.newspaperSubscriptionRepository.findOne(subscriptionId);
		return result;
	}

	// Other business methods -------------------------------------------------

	public boolean thisCustomerCanSeeThisNewspaper(final int customerId, final int newspaperId) {
		boolean result = false;
		result = this.newspaperSubscriptionRepository.thisCustomerCanSeeThisNewspaper(customerId, newspaperId);
		return result;
	}

	private boolean checkCreditCard(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		Assert.notNull(creditCard.getHolderName());
		Assert.notNull(creditCard.getBrandName());
		Assert.notNull(creditCard.getNumber());
		Assert.notNull(creditCard.getExpirationMonth());
		Assert.notNull(creditCard.getExpirationYear());
		Assert.notNull(creditCard.getCvv());

		boolean result = false;
		Date now = null;

		now = new Date();
		final Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		final int year = cal.get(Calendar.YEAR);
		final int month = cal.get(Calendar.MONTH) + 1;

		if (creditCard.getExpirationYear() > year)
			result = true;
		else if (creditCard.getExpirationYear() == year) {
			if (creditCard.getExpirationMonth() >= month)
				result = true;
			else
				result = false;
		} else
			result = false;

		return result;
	}

	public NewspaperSubscription findNewspaperSubscriptionByCustomerIdAndNewspaperId(final int customerId, final int newspaperId) {
		NewspaperSubscription result = null;
		result = this.newspaperSubscriptionRepository.findNewspaperSubscriptionByCustomerIdAndNewspaperId(customerId, newspaperId);
		return result;
	}

	// Dashboard services ------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 24.1.4

	public Double ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers() {
		Double result = null;
		result = this.newspaperSubscriptionRepository.ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers();
		return result;
	}

}
