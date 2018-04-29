
package services.forms;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import services.UserService;
import services.VolumeService;
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

		return result;
	}

	public VolumeForm create(final int volumeId) {
		final Volume v = this.volumeService.findOne(volumeId);

		final VolumeForm volumeForm = new VolumeForm();
		volumeForm.setTitle(v.getTitle());
		volumeForm.setDescription(v.getDescription());
		volumeForm.setYear(v.getYear());
		volumeForm.setNewspapers(v.getNewspapers());

		return volumeForm;
	}
	public Volume saveFromEdit(final VolumeForm volumeForm) {
		Assert.notNull(volumeForm);
		final Volume v = this.volumeService.findOne(volumeForm.getId());
		v.setTitle(volumeForm.getTitle());
		v.setDescription(volumeForm.getDescription());
		v.setId(volumeForm.getId());
		v.setUser(this.userService.findByPrincipal());
		v.setNewspapers(volumeForm.getNewspapers());
		this.volumeService.saveFromEdit(v);

		return v;

	}
	public Volume saveFromCreate(final VolumeForm volumeForm) {

		final Volume v = this.volumeService.create();

		v.setTitle(volumeForm.getTitle());
		v.setId(volumeForm.getId());
		v.setDescription(volumeForm.getDescription());
		v.setUser(this.userService.findByPrincipal());
		v.setNewspapers(volumeForm.getNewspapers());

		final Volume volumeSave = this.volumeService.saveFromCreate(v);

		return volumeSave;

	}

}
