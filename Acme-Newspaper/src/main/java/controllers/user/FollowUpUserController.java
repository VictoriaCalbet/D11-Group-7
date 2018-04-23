
package controllers.user;

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

import services.ArticleService;
import services.FollowUpService;
import services.UserService;
import services.forms.FollowUpFormService;
import controllers.AbstractController;
import domain.Article;
import domain.FollowUp;
import domain.User;
import domain.forms.FollowUpForm;

@Controller
@RequestMapping("/follow-up/user")
public class FollowUpUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private FollowUpService		followUpService;

	@Autowired
	private FollowUpFormService	followUpFormService;

	@Autowired
	private UserService			userService;

	@Autowired
	private ArticleService		articleService;


	// Constructors ---------------------------------------------------------

	public FollowUpUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int articleId) {
		ModelAndView result = null;
		Collection<FollowUp> followUps = null;
		Article article = null;
		String requestURI = null;
		String displayURI = null;
		User user = null;

		user = this.userService.findByPrincipal();

		Assert.notNull(user);

		article = this.articleService.findOne(articleId);
		followUps = article.getFollowUps();

		Assert.isTrue(article.getNewspaper().getPublicationDate() != null && article.getIsDraft() == false);

		requestURI = "follow-up/user/list.do";
		displayURI = "follow-up/user/display.do?followUpId=";

		result = new ModelAndView("follow-up/list");
		result.addObject("follow-ups", followUps);
		result.addObject("requestURI", requestURI);
		result.addObject("displayURI", displayURI);

		return result;
	}
	// Creation  ------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = null;
		FollowUpForm followUpForm = null;

		followUpForm = this.followUpFormService.createFromCreate();
		result = this.createEditModelAndView(followUpForm);

		return result;
	}

	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int followUpId) {
		ModelAndView result = null;
		FollowUp followUp = null;

		followUp = this.followUpService.findOne(followUpId);

		Assert.notNull(followUp);

		result = new ModelAndView("follow-up/display");
		result.addObject("followup", followUp);
		result.addObject("editURI", "follow-up/user/edit.do?followUpId=" + followUpId);
		result.addObject("cancelURI", "follow-up/user/list.do?articleId=" + followUp.getArticle().getId());

		return result;
	}
	// Edition    -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int followUpId) {
		ModelAndView result = null;
		FollowUpForm followUpForm = null;
		FollowUp followUp = null;
		User user = null;

		followUp = this.followUpService.findOne(followUpId);
		user = this.userService.findByPrincipal();

		Assert.isTrue(followUp.getUser().equals(user));

		followUpForm = this.followUpFormService.createFromEdit(followUpId);
		result = this.createEditModelAndView(followUpForm);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FollowUpForm followUpForm, final BindingResult bindingResult) {
		ModelAndView result = null;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(followUpForm);
		else
			try {
				if (followUpForm.getId() == 0)
					this.followUpFormService.saveFromCreate(followUpForm);
				else
					this.followUpFormService.saveFromEdit(followUpForm);

				result = new ModelAndView("redirect:/follow-up/user/list.do?articleId=" + followUpForm.getArticleId());

			} catch (final Throwable oops) {
				String messageError = "follow-up.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndView(followUpForm, messageError);
			}

		return result;
	}

	// Other actions --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final FollowUpForm followUpForm) {
		ModelAndView result = null;

		result = this.createEditModelAndView(followUpForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final FollowUpForm followUpForm, final String message) {
		ModelAndView result = null;
		String actionURI = null;
		Collection<Article> availableArticles = null;
		User user = null;

		actionURI = "follow-up/user/edit.do";

		user = this.userService.findByPrincipal();

		availableArticles = this.articleService.findAvailableArticlesToCreateFollowUps(user.getId());

		if (followUpForm.getId() == 0)
			result = new ModelAndView("follow-up/create");
		else
			result = new ModelAndView("follow-up/edit");

		result.addObject("user", user);
		result.addObject("followUpForm", followUpForm);
		result.addObject("actionURI", actionURI);
		result.addObject("availableArticles", availableArticles);
		result.addObject("message", message);

		return result;
	}
}
