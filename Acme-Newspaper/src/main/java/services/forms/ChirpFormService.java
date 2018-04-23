
package services.forms;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import services.ChirpService;
import domain.Chirp;
import domain.forms.ChirpForm;

@Service
@Transactional
public class ChirpFormService {

	//Supporting services

	@Autowired
	private ChirpService	chirpService;


	//Constructor

	public ChirpFormService() {

		super();
	}

	public ChirpForm create() {
		ChirpForm result;

		result = new ChirpForm();
		result.setPublicationMoment(new Date(System.currentTimeMillis() - 1));

		return result;
	}

	public Chirp saveFromCreate(final ChirpForm chForm) {

		Assert.notNull(chForm, "message.error.chirpForm.null");
		Assert.notNull(chForm.getDescription(), "message.error.chirpForm.description.null");
		Assert.notNull(chForm.getTitle(), "message.error.chirpForm.title.null");
		final Chirp c = this.chirpService.create();

		c.setPublicationMoment(new Date(System.currentTimeMillis() - 1));
		c.setDescription(chForm.getDescription());
		c.setTitle(chForm.getTitle());
		c.setId(chForm.getId());

		final Chirp cs = this.chirpService.saveFromCreate(c);

		return cs;
	}

}
