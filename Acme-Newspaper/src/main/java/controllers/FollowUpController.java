
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.FollowUpService;
import domain.Article;
import domain.FollowUp;

@Controller
@RequestMapping("/follow-up")
public class FollowUpController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private FollowUpService	followUpService;

	@Autowired
	private ArticleService	articleService;


	// Constructors ---------------------------------------------------------

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int articleId) {
		ModelAndView result = null;
		Collection<FollowUp> followUps = null;
		Article article = null;
		String requestURI = null;
		String displayURI = null;

		article = this.articleService.findOne(articleId);

		Assert.isTrue(!article.getNewspaper().getIsPrivate());
		Assert.notNull(article.getNewspaper().getPublicationDate());

		followUps = article.getFollowUps();

		Assert.isTrue(!article.getNewspaper().getIsPrivate() && article.getNewspaper().getPublicationDate() != null && article.getIsDraft() == false);

		requestURI = "follow-up/list.do";
		displayURI = "follow-up/display.do?followUpId=";

		result = new ModelAndView("follow-up/list");
		result.addObject("follow-ups", followUps);
		result.addObject("requestURI", requestURI);
		result.addObject("displayURI", displayURI);

		return result;
	}

	// Creation  ------------------------------------------------------------

	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int followUpId) {
		ModelAndView result = null;
		FollowUp followUp = null;

		followUp = this.followUpService.findOne(followUpId);

		Assert.notNull(followUp);
		Assert.isTrue(!followUp.getArticle().getNewspaper().getIsPrivate());
		Assert.notNull(followUp.getArticle().getNewspaper().getPublicationDate());

		result = new ModelAndView("follow-up/display");
		result.addObject("followup", followUp);
		result.addObject("editURI", "follow-up/user/edit.do?followUpId=" + followUpId);
		result.addObject("cancelURI", "follow-up/list.do?articleId=" + followUp.getArticle().getId());

		return result;
	}

	// Edition    -----------------------------------------------------------

	// Other actions --------------------------------------------------------
}
