
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VolumeRepository;
import domain.Newspaper;
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
	private NewspaperService	newspaperService;

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

		Volume result = null;

		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(volume.getUser() == principal);
		Assert.notNull(principal);

		if (volume.getYear() == null) {
			final DateTime now = DateTime.now();
			volume.setYear(now.getYear());
		}
		Assert.isTrue(volume.getYear() <= DateTime.now().getYear());
		Assert.isTrue(volume.getYear() > 1950);
		result = this.save(volume);
		return result;
	}

	public Volume saveFromEdit(final Volume volume) {

		final User principal = this.userService.findByPrincipal();

		Assert.notNull(principal);
		Assert.isTrue(volume.getUser() == principal);

		Assert.isTrue(principal.getVolumes().contains(volume));

		this.save(volume);

		return volume;
	}

	public void addNewspaperToVolume(final int newspaperId, final int volumeId) {
		Assert.notNull(newspaperId);
		Assert.notNull(volumeId);
		final Newspaper a = this.newspaperService.findOne(newspaperId);
		final Volume v = this.volumeRepository.findOne(volumeId);
		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(v.getUser() == principal);
		final Collection<Newspaper> newspapers = v.getNewspapers();
		Assert.isTrue(!newspapers.contains(a));
		newspapers.add(a);
		v.setNewspapers(newspapers);
		this.save(v);
	}

	public void deleteNewspaperToVolume(final int newspaperId, final int volumeId) {

		Assert.notNull(newspaperId);
		Assert.notNull(volumeId);

		final Newspaper b = this.newspaperService.findOne(newspaperId);
		final Volume v = this.volumeRepository.findOne(volumeId);
		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(v.getUser() == principal);
		final Collection<Newspaper> newspapers = v.getNewspapers();
		Assert.isTrue(newspapers.contains(b));
		newspapers.remove(b);
		v.setNewspapers(newspapers);
		this.save(v);

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

	// Acme-Newspaper 2.0 - Requisito 11.1.1

	public Double avgNewspaperPerVolume() {
		Double result = null;
		result = this.volumeRepository.avgNewspaperPerVolume();
		return result;
	}

	// Other business methods -------------------------------------------------
}
