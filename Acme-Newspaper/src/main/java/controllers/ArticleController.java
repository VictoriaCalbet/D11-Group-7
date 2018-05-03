
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdvertisementService;
import services.ArticleService;
import services.NewspaperService;
import services.NewspaperSubscriptionService;
import services.VolumeSubscriptionService;
import domain.Actor;
import domain.Article;
import domain.Newspaper;
import domain.User;

@Controller
@RequestMapping("/article")
public class ArticleController extends AbstractController {

	//Services

	@Autowired
	private NewspaperService				newsPaperService;

	@Autowired
	private ArticleService					articleService;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private NewspaperSubscriptionService	newspaperSubscriptionService;

	@Autowired
	private VolumeSubscriptionService		volumeSubscriptionService;

	@Autowired
	private AdvertisementService			advertisementService;


	//Constructor

	public ArticleController() {
		super();
	}

	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer newspaperId, @RequestParam(required = false, defaultValue = "") final String word) {
		ModelAndView result = null;
		Collection<Article> articles = new ArrayList<Article>();
		Actor actor = null;

		User principal = null;
		Collection<Article> principalArticles = null;
		Newspaper newspaper = null;
		boolean showFollowUps = true;

		articles = this.articleService.findArticleByKeyword("");

		if (newspaperId != null) {
			newspaper = this.newsPaperService.findOne(newspaperId);
			articles = newspaper.getArticles();
		}

		if (!(word == null || word.equals("")))
			articles = this.articleService.findArticleByKeyword(word);

		result = new ModelAndView("article/list");

		result.addObject("articles", articles);
		result.addObject("requestURI", "article/list.do");

		if (this.actorService.checkLogin()) {
			actor = this.actorService.findByPrincipal();

			if (this.actorService.checkAuthority(actor, "USER")) {
				principal = (User) actor;
				principalArticles = principal.getArticles();
			} else if (this.actorService.checkAuthority(actor, "CUSTOMER") && newspaperId != null) {
				newspaper = this.newsPaperService.findOne(newspaperId);
				if (newspaper != null && newspaper.getIsPrivate()) {
					showFollowUps = this.newspaperSubscriptionService.thisCustomerCanSeeThisNewspaper(actor.getId(), newspaperId) || this.volumeSubscriptionService.thisCustomerCanSeeThisNewspaper(actor.getId(), newspaperId);
					Assert.isTrue(showFollowUps);	// Si es false, significa que no está  suscrito
				}
			}
		} else if (newspaperId != null)
			Assert.isTrue(!newspaper.getIsPrivate());

		result.addObject("principalArticles", principalArticles);
		result.addObject("showFollowUps", showFollowUps);
		result.addObject("newspaperId", newspaperId);

		return result;
	}
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int articleId) {
		final ModelAndView result;
		final Article article = this.articleService.findOne(articleId);

		result = new ModelAndView("article/user/display");
		result.addObject("article", article);
		result.addObject("requestURI", "article/display.do");
		result.addObject("advertisementBanner", this.advertisementService.getRandomAdvertisementByNewspaperId(article.getNewspaper().getId()));
		return result;

	}
	//Ancillary methods

	@RequestMapping(value = "/searchArticleByKeyword", method = RequestMethod.POST)
	public ModelAndView searchByKeyWord(@Valid final String word) {

		ModelAndView result;

		Collection<Article> articles;

		articles = this.articleService.findArticleByKeyword(word);

		result = new ModelAndView("article/list");
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/searchArticleByKeyword.do");
		return result;

	}
}
