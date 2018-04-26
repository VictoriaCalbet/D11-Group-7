
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VolumeSubscriptionRepository;
import domain.CreditCard;
import domain.Customer;
import domain.Newspaper;
import domain.NewspaperSubscription;
import domain.VolumeSubscription;

@Service
@Transactional
public class VolumeSubscriptionService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private VolumeSubscriptionRepository	volumeSubscriptionRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CustomerService					customerService;

	@Autowired
	private NewspaperService				newspaperService;

	@Autowired
	private NewspaperSubscriptionService	newspaperSubscriptionService;


	// Constructors -----------------------------------------------------------

	public VolumeSubscriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public VolumeSubscription create() {
		VolumeSubscription result = null;

		result = new VolumeSubscription();
		result.setCustomer(this.customerService.findByPrincipal());

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public VolumeSubscription save(final VolumeSubscription volumeSubscription) {
		Assert.notNull(volumeSubscription, "message.error.volumeSubscription.null");

		VolumeSubscription result = null;
		result = this.volumeSubscriptionRepository.save(volumeSubscription);
		return result;
	}

	public void flush() {
		this.volumeSubscriptionRepository.flush();
	}

	public VolumeSubscription saveFromCreate(final VolumeSubscription volumeSubscription) {
		Assert.notNull(volumeSubscription);
		Assert.isTrue(this.checkCreditCard(volumeSubscription.getCreditCard()));

		VolumeSubscription result = null;
		Customer customer = null;
		Collection<Newspaper> newspapersNotSubscribeYet = null;

		customer = this.customerService.findByPrincipal();
		Assert.isTrue(customer.equals(volumeSubscription.getCustomer()));

		// Paso 1: realizo la entidad del servicio VolumeSubscription

		/**
		 * - Obtener los periodicos privados de un volumen - HECHO
		 * - Retirar aquellos a los que ya se esta suscrito - HECHO
		 * - Subscribirse a los periódicos restantes - HECHO
		 */
		newspapersNotSubscribeYet = this.newspaperService.findPrivateNewspapersToThisVolumeThatNotSubscribeYet(volumeSubscription.getVolume().getId(), customer.getId());

		for (final Newspaper newspaper : newspapersNotSubscribeYet) {
			NewspaperSubscription subscription = null;

			subscription = this.newspaperSubscriptionService.create();
			subscription.getCreditCards().add(volumeSubscription.getCreditCard());
			subscription.setNewspaper(newspaper);

			this.newspaperSubscriptionService.saveFromCreate(subscription);
		}

		result = this.save(volumeSubscription);

		// Paso 2: persisto el resto de relaciones a las que el objeto VolumeSubscription estén relacionadas 

		result.getCustomer().getVolumeSubscriptions().add(result);
		result.getVolume().getVolumeSubscriptions().add(result);

		return result;
	}

	public Collection<VolumeSubscription> findAll() {
		Collection<VolumeSubscription> result = null;
		result = this.volumeSubscriptionRepository.findAll();
		return result;
	}

	public VolumeSubscription findOne(final int volumeSubscriptionId) {
		VolumeSubscription result = null;
		result = this.volumeSubscriptionRepository.findOne(volumeSubscriptionId);
		return result;
	}

	// Other business methods -------------------------------------------------

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
}
