
package services.forms;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import services.UserService;
import services.VolumeService;
import domain.Newspaper;
import domain.Volume;
import domain.forms.VolumeForm;

@Service
@Transactional
public class VolumeFormService {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private VolumeService	volumeService;
	@Autowired
	private UserService		userService;


	// Constructors -----------------------------------------------------------

	public VolumeFormService() {
		super();
	}

	public VolumeForm create() {
		VolumeForm result;

		result = new VolumeForm();
		result.setNewspapers(new HashSet<Newspaper>());

		return result;
	}

	public VolumeForm create(final int volumeId) {
		final Volume volume = this.volumeService.findOne(volumeId);

		Assert.isTrue(volume.getUser().getId() == this.userService.findByPrincipal().getId(), "message.error.volume.user.principal");

		final VolumeForm volumeForm = new VolumeForm();
		volumeForm.setTitle(volume.getTitle());
		volumeForm.setDescription(volume.getDescription());
		volumeForm.setYear(volume.getYear());
		volumeForm.setNewspapers(volume.getNewspapers());
		volumeForm.setId(volume.getId());

		return volumeForm;
	}

	public Volume saveFromCreate(final VolumeForm volumeForm) {

		Volume result;
		final Volume volume = this.volumeService.create();

		volume.setTitle(volumeForm.getTitle());
		volume.setDescription(volumeForm.getDescription());
		volume.setUser(this.userService.findByPrincipal());
		volume.setNewspapers(volumeForm.getNewspapers());

		if (volumeForm.getYear() == null) {
			final DateTime now = DateTime.now();
			volume.setYear(now.getYear());
		} else
			volume.setYear(volumeForm.getYear());

		result = this.volumeService.saveFromCreate(volume);

		return result;

	}

	public Volume saveFromEdit(final VolumeForm volumeForm) {
		Assert.notNull(volumeForm, "message.error.volume.null");

		Volume result;
		final Volume volume = this.volumeService.findOne(volumeForm.getId());
		volume.setTitle(volumeForm.getTitle());
		volume.setDescription(volumeForm.getDescription());
		volume.setId(volumeForm.getId());
		volume.setUser(this.userService.findByPrincipal());
		volume.setNewspapers(volumeForm.getNewspapers());

		if (volumeForm.getYear() == null) {
			final DateTime now = DateTime.now();
			volume.setYear(now.getYear());
		} else
			volume.setYear(volumeForm.getYear());

		result = this.volumeService.saveFromEdit(volume);

		return result;

	}

}
