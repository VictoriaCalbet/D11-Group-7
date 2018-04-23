
package controllers.customer;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.NewspaperService;
import controllers.AbstractController;
import domain.Actor;
import domain.Newspaper;

@Controller
@RequestMapping("/newspaper/customer")
public class NewspaperCustomerController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;
	@Autowired
	private ActorService		actorService;


	public NewspaperCustomerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, defaultValue = "") final String word, @RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Newspaper> newspapers = new ArrayList<Newspaper>();
		Collection<Newspaper> ns = new ArrayList<Newspaper>();
		final Actor a = this.actorService.findByPrincipal();
		ns = this.newspaperService.findNewspaperSubscribedOfCustomer(a.getId());

		if (word == null || word.equals(""))
			newspapers = this.newspaperService.findNewspaperSubscribedOfCustomer();
		else
			newspapers = this.newspaperService.findNewspaperByKeyWord(word);

		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("message", message);
		result.addObject("ns", ns);
		result.addObject("requestURI", "newspaper/customer/list.do");

		return result;
	}
}
