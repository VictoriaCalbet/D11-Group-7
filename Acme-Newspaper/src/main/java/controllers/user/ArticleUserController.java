
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

import services.AdvertisementService;
import services.ArticleService;
import services.NewspaperService;
import services.UserService;
import services.forms.ArticleFormService;
import controllers.AbstractController;
import domain.Article;
import domain.Newspaper;
import domain.User;
import domain.forms.ArticleForm;

@Controller
@RequestMapping("/article/user")
public class ArticleUserController extends AbstractController {

	//Services
	@Autowired
	private ArticleService			articleService;

	@Autowired
	private UserService				userService;

	@Autowired
	private ArticleFormService		articleFormService;

	@Autowired
	private NewspaperService		newsPaperService;

	@Autowired
	private AdvertisementService	advertisementService;


	//Constructor

	public ArticleUserController() {
		super();
	}

	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int newspaperId) {
		final ModelAndView result;
		Collection<Article> articles = new ArrayList<Article>();
		User principal;

		principal = this.userService.findByPrincipal();

		Collection<Article> principalArticles = new ArrayList<Article>();
		principalArticles = principal.getArticles();
		final Newspaper newspaper = this.newsPaperService.findOne(newspaperId);
		articles = newspaper.getArticles();

		result = new ModelAndView("article/user/list");//tiles
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/user/list.do");//view
		result.addObject("principal", principal);
		result.addObject("principalArticles", principalArticles);
		result.addObject("newspaperId", newspaperId);
		return result;

	}

	@RequestMapping(value = "/listOwnArticles", method = RequestMethod.GET)
	public ModelAndView listOwnArticles(@RequestParam(required = false) final String word) {
		final ModelAndView result;
		Collection<Article> articles = new ArrayList<Article>();
		Collection<Article> principalArticles = new ArrayList<Article>();
		User principal;

		principal = this.userService.findByPrincipal();

		if (word == null || word.equals(""))
			articles = principal.getArticles();
		else
			articles = this.articleService.findArticleByKeywordByUser(word, principal.getId());

		principalArticles = principal.getArticles();

		result = new ModelAndView("article/user/list");
		result.addObject("articles", articles);
		result.addObject("principalArticles", principalArticles);
		result.addObject("requestURI", "article/user/listOwnArticles.do");
		result.addObject("principal", principal);
		return result;

	}
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int articleId) {
		final ModelAndView result;
		final Article article = this.articleService.findOne(articleId);

		result = new ModelAndView("article/user/display");
		result.addObject("article", article);
		result.addObject("requestURI", "article/user/display.do");
		result.addObject("advertisementBanner", this.advertisementService.getRandomAdvertisementByNewspaperId(article.getNewspaper().getId()));
		return result;

	}

	//Creating 
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ArticleForm articleForm;
		final Collection<Newspaper> availableNewspapers = this.newsPaperService.findAllNotPublished();

		articleForm = this.articleFormService.create();
		result = this.createEditModelAndView(articleForm);
		result.addObject("availableNewspapers", availableNewspapers);
		return result;

	}

	//EDITIONS
	//Editing
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int articleId) {

		Assert.notNull(articleId);

		final ModelAndView result;
		final ArticleForm articleForm;
		articleForm = this.articleFormService.create(articleId);
		final User principal = this.userService.findByPrincipal();
		final Collection<Newspaper> availableNewspapers = this.newsPaperService.findAllNotPublished();
		result = this.createEditModelAndView(articleForm);
		result.addObject("availableNewspapers", availableNewspapers);
		result.addObject("principal", principal);
		return result;
	}
	//Saving //TODO
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ArticleForm articleForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(articleForm);
		else
			try {
				if (articleForm.getId() > 0)
					this.articleFormService.saveFromEdit(articleForm);

				else
					this.articleFormService.saveFromCreate(articleForm);
				result = new ModelAndView("redirect:/article/user/listOwnArticles.do");

			} catch (final Throwable oops) {
				String messageError = "article.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndView(articleForm, messageError);

			}
		final Collection<Newspaper> availableNewspapers = this.newsPaperService.findAllNotPublished();

		result.addObject("availableNewspapers", availableNewspapers);

		return result;
	}
	//Ancillary methods

	protected ModelAndView createEditModelAndView(final ArticleForm articleForm) {
		ModelAndView result;

		result = this.createEditModelAndView(articleForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ArticleForm articleForm, final String messageCode) {
		ModelAndView result;
		final User principal = this.userService.findByPrincipal();
		final Collection<Article> articles = principal.getArticles();
		result = new ModelAndView("article/user/edit");
		result.addObject("articleForm", articleForm);
		result.addObject("message", messageCode);
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/user/edit.do");
		return result;
	}
}
