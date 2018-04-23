
package services.forms;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import services.NewspaperService;
import services.UserService;
import domain.Newspaper;
import domain.User;
import domain.forms.NewspaperForm;

@Service
@Transactional
public class NewspaperFormService {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private UserService			userService;


	// Constructors -----------------------------------------------------------

	public NewspaperFormService() {
		super();
	}

	public NewspaperForm create() {
		NewspaperForm result;

		result = new NewspaperForm();

		return result;
	}

	public Newspaper saveFromCreate(final NewspaperForm newspaperForm) {

		final Newspaper n = this.newspaperService.create();
		final User u = this.userService.findByPrincipal();
		Assert.isTrue(n.getPublisher().equals(u), "message.error.newspaper.user");

		n.setDescription(newspaperForm.getDescription());
		n.setTitle(newspaperForm.getTitle());
		n.setPicture(newspaperForm.getPicture());
		n.setIsPrivate(newspaperForm.getIsPrivate());

		final Newspaper ns = this.newspaperService.save(n);

		u.getNewspapers().add(n);
		this.userService.save(u);

		return ns;

	}

}
