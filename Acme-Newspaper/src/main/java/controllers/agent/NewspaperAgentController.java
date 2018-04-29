
package controllers.agent;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NewspaperService;
import controllers.AbstractController;
import domain.Newspaper;

@Controller
@RequestMapping("/newspaper/agent")
public class NewspaperAgentController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;


	public NewspaperAgentController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, defaultValue = "") final String word, @RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Newspaper> newspapers = new ArrayList<Newspaper>();

		if (word == null || word.equals(""))
			newspapers = this.newspaperService.findNewspaperWithAdvertisement();
		else
			newspapers = this.newspaperService.findNewspaperByKeyWordAdvertisement(word);

		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("message", message);
		result.addObject("requestURI", "newspaper/agent/list.do");

		return result;
	}

	@RequestMapping(value = "/listWithoutAdvertisement", method = RequestMethod.GET)
	public ModelAndView listWithAdvertisement(@RequestParam(required = false, defaultValue = "") final String word, @RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Newspaper> newspapers = new ArrayList<Newspaper>();

		if (word == null || word.equals(""))
			newspapers = this.newspaperService.findNewspaperWithoutAdvertisement();
		else
			newspapers = this.newspaperService.findNewspaperByKeyWord(word);

		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("message", message);
		result.addObject("requestURI", "newspaper/agent/list.do");

		return result;
	}

}
