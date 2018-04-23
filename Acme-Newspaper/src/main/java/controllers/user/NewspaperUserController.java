
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NewspaperService;
import services.UserService;
import services.forms.NewspaperFormService;
import controllers.AbstractController;
import domain.Newspaper;
import domain.User;
import domain.forms.NewspaperForm;

@Controller
@RequestMapping("/newspaper/user")
public class NewspaperUserController extends AbstractController {

	@Autowired
	private NewspaperService		newspaperService;

	@Autowired
	private NewspaperFormService	newspaperFormService;

	@Autowired
	private UserService				userService;


	public NewspaperUserController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, defaultValue = "") final String word, @RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Newspaper> newspapers = new ArrayList<Newspaper>();
		final User u = this.userService.findByPrincipal();

		if (word == null || word.equals(""))
			newspapers = u.getNewspapers();
		else
			newspapers = this.newspaperService.findNewspaperByKeyWordByUser(word, u.getId());

		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("message", message);
		result.addObject("requestURI", "newspaper/user/list.do");

		return result;
	}
	// Creation ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		NewspaperForm newspaperForm;

		newspaperForm = this.newspaperFormService.create();
		result = this.createModelAndView(newspaperForm);
		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView create(@Valid final NewspaperForm newspaperForm, final BindingResult binding) {
		//		this.rendezvousFormValidator.validate(rendezvousForm, binding);

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(newspaperForm);
		else
			try {
				this.newspaperFormService.saveFromCreate(newspaperForm);
				result = new ModelAndView("redirect:/newspaper/user/list.do");
			} catch (final Throwable oops) {
				String messageError = "newspaper.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createModelAndView(newspaperForm, messageError);
			}

		return result;
	}

	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public ModelAndView publish(@RequestParam final int newspaperId) {
		ModelAndView result;
		try {
			this.newspaperService.publish(newspaperId);
			result = new ModelAndView("redirect:/newspaper/user/list.do");
		} catch (final Throwable oops) {
			String messageError = "newspaper.publish.error";
			if (oops.getMessage().contains("message.error"))
				messageError = oops.getMessage();
			result = new ModelAndView("redirect:/newspaper/user/list.do");
			result.addObject("message", messageError);
		}
		return result;
	}

	//	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	//	public ModelAndView publish(@RequestParam final int newspaperId) {
	//		ModelAndView result;
	//		try {
	//
	//			NewspaperPublishForm newspaperPublishForm;
	//
	//			newspaperPublishForm = this.newspaperPublishFormService.create(newspaperId);
	//			result = this.createPublishModelAndView(newspaperPublishForm);
	//		} catch (final Throwable oops) {
	//			String messageError = "";
	//			if (oops.getMessage().contains("message.error"))
	//				messageError = oops.getMessage();
	//			result = new ModelAndView("redirect:/newspaper/user/list.do");
	//			result.addObject("message", messageError);
	//		}
	//		return result;
	//
	//	}
	//
	//	@RequestMapping(value = "/publish", method = RequestMethod.POST, params = "save")
	//	public ModelAndView publish(@Valid final NewspaperPublishForm newspaperPublishForm, final BindingResult binding) {
	//
	//		ModelAndView result;
	//
	//		if (binding.hasErrors())
	//			result = this.createPublishModelAndView(newspaperPublishForm);
	//		else
	//			try {
	//				this.newspaperPublishFormService.publishTo(newspaperPublishForm);
	//				result = new ModelAndView("redirect:/newspaper/user/list.do");
	//			} catch (final Throwable oops) {
	//				String messageError = "newspaper.commit.error";
	//				if (oops.getMessage().contains("message.error"))
	//					messageError = oops.getMessage();
	//				result = this.createPublishModelAndView(newspaperPublishForm, messageError);
	//			}
	//
	//		return result;
	//	}

	// Ancillaty methods
	protected ModelAndView createModelAndView(final NewspaperForm newspaperForm) {
		ModelAndView result;

		result = this.createModelAndView(newspaperForm, null);

		return result;
	}

	protected ModelAndView createModelAndView(final NewspaperForm newspaperForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("newspaper/user/create");
		result.addObject("newspaperForm", newspaperForm);
		result.addObject("message", message);

		return result;
	}

	//	protected ModelAndView createPublishModelAndView(final NewspaperPublishForm newspaperPublishForm) {
	//		ModelAndView result;
	//
	//		result = this.createPublishModelAndView(newspaperPublishForm, null);
	//
	//		return result;
	//	}

	//	protected ModelAndView createPublishModelAndView(final NewspaperPublishForm newspaperPublishForm, final String message) {
	//		ModelAndView result;
	//
	//		result = new ModelAndView("newspaper/user/publish");
	//		result.addObject("newspaperPublishForm", newspaperPublishForm);
	//		result.addObject("message", message);
	//		return result;
	//	}

}
