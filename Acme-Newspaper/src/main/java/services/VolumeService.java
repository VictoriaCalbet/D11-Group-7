
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VolumeRepository;
import domain.Volume;

@Service
@Transactional
public class VolumeService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private VolumeRepository	volumeRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public VolumeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Volume create() {
		return null;
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
		return null;
	}

	public Volume saveFromEdit(final Volume volume) {
		return null;
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
