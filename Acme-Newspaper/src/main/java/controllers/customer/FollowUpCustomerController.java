
package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.CustomerService;
import services.FollowUpService;
import services.NewspaperSubscriptionService;
import services.VolumeSubscriptionService;
import controllers.AbstractController;
import domain.Article;
import domain.Customer;
import domain.FollowUp;

@Controller
@RequestMapping("/follow-up/customer")
public class FollowUpCustomerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private FollowUpService					followUpService;

	@Autowired
	private CustomerService					customerService;

	@Autowired
	private ArticleService					articleService;

	@Autowired
	private NewspaperSubscriptionService	newspaperSubscriptionService;

	@Autowired
	private VolumeSubscriptionService		volumeSubscriptionService;


	// Constructors ---------------------------------------------------------

	public FollowUpCustomerController() {
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
		Customer customer = null;

		customer = this.customerService.findByPrincipal();

		Assert.notNull(customer);

		article = this.articleService.findOne(articleId);
		followUps = article.getFollowUps();

		Assert.isTrue(article.getNewspaper().getPublicationDate() != null && article.getIsDraft() == false);

		if (article.getNewspaper().getIsPrivate())
			Assert.isTrue(this.newspaperSubscriptionService.thisCustomerCanSeeThisNewspaper(customer.getId(), article.getNewspaper().getId())
				|| this.volumeSubscriptionService.thisCustomerCanSeeThisNewspaper(customer.getId(), article.getNewspaper().getId()));

		requestURI = "follow-up/customer/list.do";
		displayURI = "follow-up/customer/display.do?followUpId=";

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
		Article article = null;
		Customer customer = null;

		customer = this.customerService.findByPrincipal();
		Assert.notNull(customer);

		followUp = this.followUpService.findOne(followUpId);
		Assert.notNull(followUp);

		article = followUp.getArticle();
		Assert.isTrue(article.getNewspaper().getPublicationDate() != null && article.getIsDraft() == false);

		if (article.getNewspaper().getIsPrivate())
			Assert.isTrue(this.newspaperSubscriptionService.thisCustomerCanSeeThisNewspaper(customer.getId(), article.getNewspaper().getId())
				|| this.volumeSubscriptionService.thisCustomerCanSeeThisNewspaper(customer.getId(), article.getNewspaper().getId()));

		result = new ModelAndView("follow-up/display");
		result.addObject("followup", followUp);
		result.addObject("editURI", "follow-up/customer/edit.do?followUpId=" + followUpId);
		result.addObject("cancelURI", "follow-up/customer/list.do?articleId=" + followUp.getArticle().getId());

		return result;
	}

	// Edition    -----------------------------------------------------------

	// Other actions --------------------------------------------------------
}
