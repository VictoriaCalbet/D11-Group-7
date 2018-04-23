
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VolumeSubscriptionRepository;
import domain.VolumeSubscription;

@Service
@Transactional
public class VolumeSubscriptionService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private VolumeSubscriptionRepository	volumeSubscriptionRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public VolumeSubscriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public VolumeSubscription create() {
		return null;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public VolumeSubscription save(final VolumeSubscription volumeSubscription) {
		Assert.notNull(volumeSubscription, "message.error.volumeSubscription.null");
		VolumeSubscription result;
		result = this.volumeSubscriptionRepository.save(volumeSubscription);
		return result;
	}

	public void flush() {
		this.volumeSubscriptionRepository.flush();
	}

	public VolumeSubscription saveFromCreate(final VolumeSubscription volumeSubscription) {
		return null;
	}

	public VolumeSubscription saveFromEdit(final VolumeSubscription volumeSubscription) {
		return null;
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
}
