
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ArticleService;
import services.CustomerService;
import services.NewspaperService;
import services.UserService;
import domain.Actor;
import domain.Article;
import domain.Customer;
import domain.Newspaper;
import domain.User;

@Controller
@RequestMapping("/newspaper")
public class NewspaperController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private ArticleService		articleService;

	@Autowired
	private UserService			userService;


	public NewspaperController() {
		super();
	}

	//Listing 

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, defaultValue = "") final String word, @RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Newspaper> newspapers = new ArrayList<Newspaper>();
		Collection<Newspaper> ns = new ArrayList<Newspaper>();

		try {
			if (word == null || word.equals("")) {

				final Actor a = this.actorService.findByPrincipal();
				newspapers = this.newspaperService.findPublicated();
				ns = this.newspaperService.findNewspaperSubscribedOfCustomer(a.getId());
			} else {
				final Customer c = this.customerService.findByPrincipal();
				newspapers = this.newspaperService.findNewspaperByKeyWord(word);
				ns = this.newspaperService.findNewspaperSubscribedOfCustomer(c.getId());
			}
		} catch (final Throwable oops) {
			if (word == null || word.equals(""))
				newspapers = this.newspaperService.findPublicated();
			else
				newspapers = this.newspaperService.findNewspaperByKeyWordNotPrivate(word);
		}

		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("ns", ns);
		result.addObject("message", message);
		result.addObject("requestURI", "newspaper/list.do");

		return result;
	}

	// Search by key word

	//	@RequestMapping(value = "/searchWord", method = RequestMethod.POST)
	//	public ModelAndView searchByKeyWord(@Valid final String word) {
	//
	//		ModelAndView result;
	//
	//		Collection<Newspaper> newspapers = new ArrayList<Newspaper>();
	//		Collection<Newspaper> ns = new ArrayList<Newspaper>();
	//
	//		try {
	//			final Customer c = this.customerService.findByPrincipal();
	//			newspapers = this.newspaperService.findNewspaperByKeyWord(word);
	//			ns = this.newspaperService.findNewspaperSubscribedOfCustomer(c.getId());
	//		} catch (final Throwable oops) {
	//			newspapers = this.newspaperService.findNewspaperByKeyWordNotPrivate(word);
	//
	//		}
	//
	//		result = new ModelAndView("newspaper/list");
	//		result.addObject("newspapers", newspapers);
	//		result.addObject("ns", ns);
	//		result.addObject("requestURI", "newspaper/searchWord.do");
	//		return result;
	//
	//	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(@RequestParam final int newspaperId) {
		ModelAndView result = new ModelAndView();

		final Newspaper newspaper = this.newspaperService.findOne(newspaperId);
		Collection<Newspaper> ns = new ArrayList<Newspaper>();

		boolean visible = true;

		try {
			if (this.actorService.checkAuthority(this.actorService.findByPrincipal(), "CUSTOMER")) {
				final Customer c = this.customerService.findByPrincipal();
				ns = this.newspaperService.findNewspaperSubscribedOfCustomer(c.getId());
				if (!ns.contains(newspaper) && newspaper.getIsPrivate() == true && newspaper.getPublicationDate() != null)
					visible = false;
				else if (!ns.contains(newspaper) && newspaper.getIsPrivate() == true && newspaper.getPublicationDate() == null)
					return result = new ModelAndView("redirect:/newspaper/list.do");
				else if (!ns.contains(newspaper) && newspaper.getIsPrivate() == false && newspaper.getPublicationDate() == null)
					return result = new ModelAndView("redirect:/newspaper/list.do");
			} else if (this.actorService.checkAuthority(this.actorService.findByPrincipal(), "USER")) {
				final User u = this.userService.findByPrincipal();
				if (!u.getNewspapers().contains(newspaper) && newspaper.getIsPrivate() && newspaper.getPublicationDate() == null)
					return result = new ModelAndView("redirect:/newspaper/list.do");
				else if (!u.getNewspapers().contains(newspaper) && !newspaper.getIsPrivate() && newspaper.getPublicationDate() == null)
					return result = new ModelAndView("redirect:/newspaper/list.do");
			}

		} catch (final Throwable oops) {
			if (newspaper.getIsPrivate() || newspaper.getPublicationDate() == null)
				return result = new ModelAndView("redirect:/newspaper/list.do");

		}

		result = this.infoModelAndView(newspaper, visible);
		return result;
	}
	// Ancillary methods
	protected ModelAndView infoModelAndView(final Newspaper newspaper, final boolean visible) {
		ModelAndView result;

		result = this.infoModelAndView(newspaper, visible, null);

		return result;
	}

	protected ModelAndView infoModelAndView(final Newspaper newspaper, final boolean visible, final String message) {
		final ModelAndView result;

		Collection<Article> articles = new ArrayList<Article>();
		articles = this.articleService.findAllByNewspaperId(newspaper.getId());

		result = new ModelAndView("newspaper/info");

		result.addObject("newspaper", newspaper);
		result.addObject("message", message);
		result.addObject("visible", visible);
		result.addObject("articles", articles);

		return result;
	}

}
