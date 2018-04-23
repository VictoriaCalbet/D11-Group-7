
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SubscriptionRepository;
import domain.CreditCard;
import domain.Customer;
import domain.Subscription;

@Service
@Transactional
public class SubscriptionService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private SubscriptionRepository	subscriptionRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CustomerService			customerService;


	// Constructors -----------------------------------------------------------

	public SubscriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Subscription create() {
		Subscription result = null;

		result = new Subscription();
		result.setCustomer(this.customerService.findByPrincipal());

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Subscription save(final Subscription subscription) {
		Assert.notNull(subscription);
		Subscription result;
		result = this.subscriptionRepository.save(subscription);
		return result;
	}

	public Subscription saveFromCreate(final Subscription subscription) {
		Assert.notNull(subscription);
		Assert.isTrue(this.checkCreditCard(subscription.getCreditCard()));
		Assert.isTrue(subscription.getNewspaper().getIsPrivate());
		Assert.notNull(subscription.getNewspaper().getPublicationDate());

		Subscription result = null;
		Customer customer = null;

		customer = this.customerService.findByPrincipal();
		Assert.notNull(customer);
		Assert.isTrue(customer.equals(subscription.getCustomer()));
		Assert.isTrue(!this.subscriptionRepository.isThisCustomerSubscribeOnThisNewspaper(subscription.getCustomer().getId(), subscription.getNewspaper().getId()));

		// Paso 1: realizo la entidad del servicio Subscription

		result = this.save(subscription);

		// Paso 2: persisto el resto de relaciones a las que el objeto Subscription estén relacionadas
		result.getCustomer().getSubscriptions().add(result);
		result.getNewspaper().getSubscriptions().add(result);

		return result;
	}

	public void flush() {
		this.subscriptionRepository.flush();
	}

	public Collection<Subscription> findAll() {
		Collection<Subscription> result = null;
		result = this.subscriptionRepository.findAll();
		return result;
	}

	public Subscription findOne(final int subscriptionId) {
		Subscription result = null;
		result = this.subscriptionRepository.findOne(subscriptionId);
		return result;
	}

	// Other business methods -------------------------------------------------

	public boolean thisCustomerCanSeeThisNewspaper(final int customerId, final int newspaperId) {
		boolean result = false;
		result = this.subscriptionRepository.thisCustomerCanSeeThisNewspaper(customerId, newspaperId);
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

	// Dashboard services ------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 24.1.4

	public Double ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers() {
		Double result = null;
		result = this.subscriptionRepository.ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers();
		return result;
	}
}
