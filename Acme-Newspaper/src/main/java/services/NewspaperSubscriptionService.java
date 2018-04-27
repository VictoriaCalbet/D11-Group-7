
package services;

import java.util.ArrayList;
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


	// Constructors -----------------------------------------------------------

	public NewspaperSubscriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public NewspaperSubscription create() {
		NewspaperSubscription result = null;

		result = new NewspaperSubscription();
		result.setCustomer(this.customerService.findByPrincipal());
		result.setCreditCards(new ArrayList<CreditCard>());

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
		Assert.notNull(newspaperSubscription);
		Assert.isTrue(this.checkCreditCards(newspaperSubscription));
		Assert.isTrue(newspaperSubscription.getNewspaper().getIsPrivate());
		Assert.notNull(newspaperSubscription.getNewspaper().getPublicationDate());

		NewspaperSubscription result = null;
		Customer customer = null;

		customer = this.customerService.findByPrincipal();
		Assert.notNull(customer);
		Assert.isTrue(customer.equals(newspaperSubscription.getCustomer()));
		Assert.isTrue(!this.newspaperSubscriptionRepository.isThisCustomerSubscribeOnThisNewspaper(newspaperSubscription.getCustomer().getId(), newspaperSubscription.getNewspaper().getId()));

		// Paso 1: realizo la entidad del servicio NewspaperSubscription

		result = this.save(newspaperSubscription);

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

	private boolean checkCreditCards(final NewspaperSubscription newspaperSubscription) {
		boolean result = false;

		Collection<CreditCard> creditCardsInDB = new ArrayList<CreditCard>();
		Collection<CreditCard> creditCardsInObject = new ArrayList<CreditCard>();

		if (newspaperSubscription.getId() != 0) {
			final NewspaperSubscription newspaperSubscriptionInDB = this.findOne(newspaperSubscription.getId());
			creditCardsInDB = newspaperSubscriptionInDB.getCreditCards();
		}

		creditCardsInObject = newspaperSubscription.getCreditCards();

		creditCardsInObject.removeAll(creditCardsInDB);

		CreditCard creditCardToCheck;

		creditCardToCheck = creditCardsInObject.iterator().next();

		result = this.checkCreditCard(creditCardToCheck);

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
		result = this.newspaperSubscriptionRepository.ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers();
		return result;
	}
}
