
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdvertisementRepository;
import domain.Advertisement;
import domain.Agent;
import domain.CreditCard;

@Service
@Transactional
public class AdvertisementService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private AdvertisementRepository	advertisementRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private NewspaperService		newspaperService;
	@Autowired
	private AgentService			agentService;


	// Constructors -----------------------------------------------------------

	public AdvertisementService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Advertisement create() {
		Advertisement advertisement;
		advertisement = new Advertisement();
		advertisement.setAgent(this.isAgentAunthenticate());
		return advertisement;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Advertisement save(final Advertisement advertisement) {
		Assert.notNull(advertisement, "advertisement.error.null");
		Advertisement result;
		result = this.advertisementRepository.save(advertisement);
		return result;
	}
	public void delete(final Advertisement advertisement) {
		Assert.notNull(advertisement, "advertisement.error.null");

		Advertisement advertisementInDB;
		advertisementInDB = this.advertisementRepository.findOne(advertisement.getId());
		Assert.notNull(advertisementInDB, "advertisement.error.null.indb");

		this.advertisementRepository.delete(advertisement);
	}
	public void flush() {
		this.advertisementRepository.flush();
	}

	public Advertisement saveFromCreate(final Advertisement advertisement) {

		Assert.notNull(advertisement, "advertisement.error.null");
		this.isAgentAunthenticate();
		Assert.notNull(advertisement.getBannerURL(), "advertisement.error.null.banner");
		Assert.notNull(advertisement.getTitle(), "advertisement.error.null.title");
		Assert.notNull(advertisement.getTargetPageURL(), "advertisement.error.null.target");
		this.checkCreditCard(advertisement.getCreditCard());
		Assert.notNull(advertisement.getNewspaper(), "advertisement.error.null.newspaper");

		Advertisement advertisementInDB;
		advertisementInDB = this.advertisementRepository.save(advertisement);

		return advertisementInDB;
	}

	public Advertisement saveFromEdit(final Advertisement advertisement) {
		Assert.notNull(advertisement.getAgent());
		this.isCorrectAgentAunthenticate(advertisement.getAgent().getId());
		Assert.notNull(advertisement, "advertisement.error.null");
		Assert.notNull(advertisement.getBannerURL(), "advertisement.error.null.banner");
		Assert.notNull(advertisement.getTitle(), "advertisement.error.null.title");
		Assert.notNull(advertisement.getTargetPageURL(), "advertisement.error.null.target");
		this.checkCreditCard(advertisement.getCreditCard());
		Assert.notNull(advertisement.getNewspaper(), "advertisement.error.null.newspaper");
		Advertisement advertisementInDB;
		advertisementInDB = this.findOne(advertisement.getId());
		Assert.notNull(advertisementInDB);
		advertisementInDB = this.advertisementRepository.save(advertisement);

		return advertisementInDB;
	}
	public void deleteByAgent(final Advertisement advertisement) {
		Assert.notNull(advertisement, "advertisement.error.null");

		Advertisement advertisementInDB;
		advertisementInDB = this.advertisementRepository.findOne(advertisement.getId());
		Assert.notNull(advertisementInDB, "advertisement.error.null.indb");
		this.isCorrectAgentAunthenticate(advertisementInDB.getAgent().getId());
		this.advertisementRepository.delete(advertisement);
	}
	public Collection<Advertisement> findAll() {
		Collection<Advertisement> result = null;
		result = this.advertisementRepository.findAll();
		return result;
	}

	public Advertisement findOne(final int advertisementId) {
		Advertisement result = null;
		result = this.advertisementRepository.findOne(advertisementId);
		return result;
	}

	// Other business methods -------------------------------------------------
	//Get random advertisement for a newspaper
	public Advertisement getRandomAdvertisementByNewspaperId(final int newspaperId) {
		final List<Advertisement> sponsorships = new ArrayList<Advertisement>(this.newspaperService.findOne(newspaperId).getAdvertisements());
		final int nSponsorships = sponsorships.size();
		Advertisement s = null;
		if (nSponsorships != 0) {
			final Random random = new Random();
			final int randomIndex = random.nextInt(nSponsorships);
			s = sponsorships.get(randomIndex);
		}

		return s;
	}

	//Auxiliar method

	private void checkCreditCard(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		final Calendar calendar = Calendar.getInstance();
		final int year = calendar.get(Calendar.YEAR);
		System.out.println(year + "GGGGGGGGGGGGGGGGGGGGGGGG");
		if (creditCard.getExpirationYear() < year)
			Assert.isTrue(false, "advertisement.error.creditcard");
		else if (creditCard.getExpirationYear() == year)
			Assert.isTrue(!(creditCard.getExpirationMonth() <= (calendar.get(Calendar.MONTH)) + 1), "advertisement.error.creditcard");
	}

	private Agent isAgentAunthenticate() {
		Agent actor;
		actor = this.agentService.findByPrincipal();
		Assert.notNull(actor);
		String authority;
		authority = actor.getUserAccount().getAuthorities().iterator().next().getAuthority();
		Assert.isTrue(authority.equals("AGENT"), "advertisement.error.notagent");
		return actor;
	}
	private void isCorrectAgentAunthenticate(final int agentId) {
		Assert.isTrue(this.isAgentAunthenticate().getId() == agentId, "advertisement.error.badagent");
	}
}
