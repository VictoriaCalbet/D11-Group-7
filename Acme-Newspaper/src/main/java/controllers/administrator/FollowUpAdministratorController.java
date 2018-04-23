
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.ArticleService;
import services.FollowUpService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Article;
import domain.FollowUp;

@Controller
@RequestMapping("/follow-up/administrator")
public class FollowUpAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private FollowUpService			followUpService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ArticleService			articleService;


	// Constructors ---------------------------------------------------------

	public FollowUpAdministratorController() {
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
		Administrator administrator = null;

		administrator = this.administratorService.findByPrincipal();

		Assert.notNull(administrator);

		article = this.articleService.findOne(articleId);
		followUps = article.getFollowUps();

		requestURI = "follow-up/administrator/list.do";
		displayURI = "follow-up/administrator/display.do?followUpId=";

		result = new ModelAndView("follow-up/list");
		result.addObject("follow-ups", followUps);
		result.addObject("requestURI", requestURI);
		result.addObject("displayURI", displayURI);

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int followUpId) {
		ModelAndView result = null;
		FollowUp followUp = null;

		followUp = this.followUpService.findOne(followUpId);

		Assert.notNull(followUp);

		result = new ModelAndView("follow-up/display");
		result.addObject("followup", followUp);
		result.addObject("editURI", "follow-up/administrator/edit.do?followUpId=" + followUpId);
		result.addObject("cancelURI", "follow-up/administrator/list.do?articleId=" + followUp.getArticle().getId());

		return result;
	}
}
