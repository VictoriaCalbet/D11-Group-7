
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NewspaperService;
import services.UserService;
import services.forms.VolumeFormService;
import controllers.AbstractController;
import domain.Newspaper;
import domain.User;
import domain.Volume;
import domain.forms.VolumeForm;

@Controller
@RequestMapping("/volume/user")
public class VolumeUserController extends AbstractController {

	//Services

	@Autowired
	private UserService			userService;

	@Autowired
	private VolumeFormService	volumeFormService;

	@Autowired
	private NewspaperService	newsPaperService;


	//Constructor

	public VolumeUserController() {
		super();
	}

	@RequestMapping(value = "/listMyVolumes", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		User principal;

		principal = this.userService.findByPrincipal();

		Collection<Volume> volumes = new ArrayList<Volume>();
		Collection<Volume> principalVolumes = new ArrayList<Volume>();
		volumes = principal.getVolumes();
		principalVolumes = volumes;
		result = new ModelAndView("volume/user/list");
		result.addObject("volumes", volumes);
		result.addObject("principalVolumes", principalVolumes);
		result.addObject("requestURI", "volume/user/list.do");
		result.addObject("principal", principal);
		return result;

	}
	//Creating 
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		VolumeForm volumeForm;
		final Collection<Newspaper> availableNewspapers = this.newsPaperService.findPublicatedAll();

		volumeForm = this.volumeFormService.create();
		result = this.createEditModelAndView(volumeForm);
		result.addObject("availableNewspapers", availableNewspapers);
		return result;

	}

	//EDITIONS
	//Editing
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int volumeId) {

		Assert.notNull(volumeId);

		final ModelAndView result;
		final VolumeForm volumeForm;
		volumeForm = this.volumeFormService.create(volumeId);
		final User principal = this.userService.findByPrincipal();
		result = this.createEditModelAndView(volumeForm);
		result.addObject("principal", principal);
		return result;
	}
	//Saving 
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final VolumeForm volumeForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(volumeForm);
		else
			try {
				if (volumeForm.getId() > 0)
					this.volumeFormService.saveFromEdit(volumeForm);

				else
					this.volumeFormService.saveFromCreate(volumeForm);
				result = new ModelAndView("redirect:/volume/user/listMyVolumes.do");

			} catch (final Throwable oops) {
				String messageError = "volume.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndView(volumeForm, messageError);

			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final VolumeForm volumeForm) {
		ModelAndView result;

		result = this.createEditModelAndView(volumeForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final VolumeForm volumeForm, final String messageCode) {
		ModelAndView result;
		final User principal = this.userService.findByPrincipal();
		final Collection<Volume> volumes = principal.getVolumes();
		final Collection<Newspaper> availableNewspapers = this.newsPaperService.findPublicatedAll();
		result = new ModelAndView("volume/user/edit");
		result.addObject("volumeForm", volumeForm);
		result.addObject("message", messageCode);
		result.addObject("volumes", volumes);
		result.addObject("availableNewspapers", availableNewspapers);
		result.addObject("requestURI", "volume/user/edit.do");
		return result;
	}
}
