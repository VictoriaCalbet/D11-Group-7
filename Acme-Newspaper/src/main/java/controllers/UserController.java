
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ArticleService;
import services.UserService;
import services.forms.ActorFormService;
import domain.Article;
import domain.User;
import domain.forms.ActorForm;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService			userService;

	@Autowired
	private ActorFormService	actorFormService;

	@Autowired
	private ArticleService		articleService;

	@Autowired
	private ActorService		actorService;
	

	public UserController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<User> users;

		users = this.userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("users", users);
		result.addObject("requestURI", "user/list.do");
		
		if(this.actorService.checkLogin() && actorService.checkAuthority(this.actorService.findByPrincipal(), "USER")){
			
			User user = this.userService.findByPrincipal();

			result.addObject("loggedUser",user);
	}
		
		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(final int userId) {
		ModelAndView result;
		User user;
		final Collection<Article> articles;

		user = this.userService.findOne(userId);
		articles = this.articleService.findAllPublishedByUserId(userId);

		result = new ModelAndView("user/display");
		result.addObject("user", user);
		result.addObject("articles", articles);
		result.addObject("requestURI", "user/display.do");
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		ActorForm actorForm;

		actorForm = this.actorFormService.create();
		result = this.createEditModelAndView(actorForm);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(actorForm);
		else
			try {
				if (actorForm.getId() != 0)
					this.actorFormService.saveFromEdit(actorForm, "USER");
				else
					this.actorFormService.saveFromCreate(actorForm, "USER");
				result = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				String messageError = "user.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndView(actorForm, messageError);
			}

		return result;
	}
	// Ancillary methods

	public ModelAndView createEditModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);

		return result;
	}

	public ModelAndView createEditModelAndView(final ActorForm actorForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("actorForm/edit");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);
		result.addObject("requestURI", "user/edit.do");

		return result;
	}
}
