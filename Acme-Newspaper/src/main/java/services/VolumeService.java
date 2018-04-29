
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VolumeRepository;
import domain.User;
import domain.Volume;
import domain.VolumeSubscription;

@Service
@Transactional
public class VolumeService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private VolumeRepository	volumeRepository;

	@Autowired
	private UserService			userService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public VolumeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Volume create() {
		final Volume result = new Volume();

		result.setYear(Calendar.YEAR);
		final User principal = this.userService.findByPrincipal();
		result.setUser(principal);
		final Collection<VolumeSubscription> subs = new ArrayList<VolumeSubscription>();
		result.setVolumeSubscriptions(subs);
		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Volume save(final Volume volume) {
		Assert.notNull(volume, "message.error.message.null");
		Volume result;
		result = this.volumeRepository.save(volume);
		return result;
	}

	public void flush() {
		this.volumeRepository.flush();
	}

	public Volume saveFromCreate(final Volume volume) {

		Volume result = volume;

		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(volume.getUser() == principal);
		Assert.notNull(principal);

		Collection<Volume> newVolumes = new ArrayList<Volume>();
		newVolumes = principal.getVolumes();
		newVolumes.add(volume);
		principal.setVolumes(newVolumes);
		this.userService.saveFromEdit(principal);

		result = this.volumeRepository.save(result);

		return result;
	}

	public Volume saveFromEdit(final Volume volume) {

		Volume result = volume;
		final User principal = this.userService.findByPrincipal();

		Assert.notNull(principal);
		Assert.isTrue(volume.getUser() == principal);

		Assert.isTrue(principal.getVolumes().contains(volume));

		result = this.volumeRepository.save(result);

		return result;
	}

	public Collection<Volume> findAll() {
		Collection<Volume> result = null;
		result = this.volumeRepository.findAll();
		return result;
	}

	public Volume findOne(final int volumeId) {
		Volume result = null;
		result = this.volumeRepository.findOne(volumeId);
		return result;
	}

	public Collection<Volume> findAvailableVolumesByCustomerId(final int customerId) {
		Collection<Volume> result = null;
		result = this.volumeRepository.findAvailableVolumesByCustomerId(customerId);
		return result;
	}

	// Other business methods -------------------------------------------------
}
