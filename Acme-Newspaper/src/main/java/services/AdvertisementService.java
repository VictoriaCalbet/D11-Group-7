
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdvertisementRepository;
import domain.Administrator;
import domain.Advertisement;
import domain.Agent;
import domain.CreditCard;

@Service
@Transactional
public class AdvertisementService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private AdvertisementRepository		advertisementRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private NewspaperService			newspaperService;
	@Autowired
	private AgentService				agentService;
	@Autowired
	private AdministratorService		administratorService;

	@Autowired
	private SystemConfigurationService	systemConfigurationService;


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
		Assert.notNull(advertisement, "message.error.advertisement.null");
		Advertisement result;
		result = this.advertisementRepository.save(advertisement);
		return result;
	}
	public void delete(final Advertisement advertisement) {
		Assert.notNull(advertisement, "message.error.advertisement.null");

		Advertisement advertisementInDB;
		advertisementInDB = this.advertisementRepository.findOne(advertisement.getId());
		Assert.notNull(advertisementInDB, "message.error.advertisement.null.indb");

		this.advertisementRepository.delete(advertisement);
	}

	public void flush() {
		this.advertisementRepository.flush();
	}

	public Advertisement saveFromCreate(final Advertisement advertisement) {

		Assert.notNull(advertisement, "message.error.advertisement.null");
		advertisement.setAgent(this.isAgentAunthenticate());

		Assert.notNull(advertisement.getBannerURL(), "message.error.advertisement.null.banner");
		Assert.notNull(advertisement.getTitle(), "message.error.advertisement.null.title");
		Assert.notNull(advertisement.getTargetPageURL(), "message.error.advertisement.null.target");
		this.checkCreditCard(advertisement.getCreditCard());
		Assert.notNull(advertisement.getNewspaper(), "message.error.advertisement.null.newspaper");

		Advertisement advertisementInDB;
		advertisementInDB = this.advertisementRepository.save(advertisement);

		return advertisementInDB;
	}

	public Advertisement saveFromEdit(final Advertisement advertisement) {
		Assert.notNull(advertisement.getAgent());
		this.isCorrectAgentAunthenticate(advertisement.getAgent().getId());
		Assert.notNull(advertisement, "message.error.advertisement.null");
		Assert.notNull(advertisement.getBannerURL(), "message.error.advertisement.null.banner");
		Assert.notNull(advertisement.getTitle(), "message.error.advertisement.null.title");
		Assert.notNull(advertisement.getTargetPageURL(), "message.error.advertisement.null.target");
		this.checkCreditCard(advertisement.getCreditCard());
		Assert.notNull(advertisement.getNewspaper(), "message.error.advertisement.null.newspaper");
		Advertisement advertisementInDB;
		advertisementInDB = this.findOne(advertisement.getId());
		Assert.notNull(advertisementInDB);
		this.isCorrectAgentAunthenticate(advertisementInDB.getAgent().getId());

		advertisementInDB = this.advertisementRepository.save(advertisement);

		return advertisementInDB;
	}
	public void deleteByAgent(final Advertisement advertisement) {
		Assert.notNull(advertisement, "message.error.advertisement.null");

		Advertisement advertisementInDB;
		advertisementInDB = this.advertisementRepository.findOne(advertisement.getId());
		Assert.notNull(advertisementInDB, "message.error.advertisement.null.indb");
		this.isCorrectAgentAunthenticate(advertisementInDB.getAgent().getId());
		this.advertisementRepository.delete(advertisement);
	}

	public void deleteByAdmin(final Advertisement advertisement) {
		Assert.notNull(advertisement, "message.error.advertisement.null");

		Advertisement advertisementInDB;
		advertisementInDB = this.advertisementRepository.findOne(advertisement.getId());
		Assert.notNull(advertisementInDB, "message.error.advertisement.null.indb");
		this.isAdminAunthenticate();
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

	// Acme-Newspaper 2.0 - Requisito 5.3.2

	public Double ratioOfAdvertisementsWithTabooWords() {
		Double result = null;
		Collection<String> tabooWords;
		final Collection<Advertisement> advertisements = new HashSet<Advertisement>();

		tabooWords = this.systemConfigurationService.findMain().getTabooWords();

		for (final String tabooWord : tabooWords)
			advertisements.addAll(this.getTabooAdvertisements(tabooWord));

		result = new Double(advertisements.size());
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
	public Collection<Advertisement> getTabooAdvertisements(final String keyWord) {
		Collection<Advertisement> advertisements;
		advertisements = this.advertisementRepository.getTabooAdvertisements(keyWord);
		return advertisements;
	}

	//Auxiliar method

	private void checkCreditCard(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		final Calendar calendar = Calendar.getInstance();
		final int year = calendar.get(Calendar.YEAR);

		if (creditCard.getExpirationYear() < year)
			Assert.isTrue(false, "message.error.advertisement.creditcard");
		else if (creditCard.getExpirationYear() == year)
			Assert.isTrue(!(creditCard.getExpirationMonth() <= (calendar.get(Calendar.MONTH)) + 1), "message.error.advertisement.creditcard");
	}

	private Agent isAgentAunthenticate() {
		Agent actor;
		actor = this.agentService.findByPrincipal();
		Assert.notNull(actor);
		String authority;
		authority = actor.getUserAccount().getAuthorities().iterator().next().getAuthority();
		Assert.isTrue(authority.equals("AGENT"), "message.error.advertisement.notagent");
		return actor;
	}
	private Administrator isAdminAunthenticate() {
		Administrator actor;
		actor = this.administratorService.findByPrincipal();
		Assert.notNull(actor);
		String authority;
		authority = actor.getUserAccount().getAuthorities().iterator().next().getAuthority();
		Assert.isTrue(authority.equals("ADMIN"), "message.error.advertisement.notadmin");
		return actor;
	}
	private void isCorrectAgentAunthenticate(final int agentId) {
		Assert.isTrue(this.isAgentAunthenticate().getId() == agentId, "message.error.advertisement.badagent");
	}
}
