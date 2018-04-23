
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdvertisementRepository;
import domain.Advertisement;

@Service
@Transactional
public class AdvertisementService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private AdvertisementRepository	advertisementRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AdvertisementService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Advertisement create() {
		return null;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Advertisement save(final Advertisement advertisement) {
		Assert.notNull(advertisement, "message.error.advertisement.null");
		Advertisement result;
		result = this.advertisementRepository.save(advertisement);
		return result;
	}

	public void flush() {
		this.advertisementRepository.flush();
	}

	public Advertisement saveFromCreate(final Advertisement advertisement) {
		return null;
	}

	public Advertisement saveFromEdit(final Advertisement advertisement) {
		return null;
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
}
