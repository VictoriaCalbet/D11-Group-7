
package controllers.administrator;

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
@RequestMapping("/newspaper/administrator")
public class NewspaperAdministratorController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;


	public NewspaperAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, defaultValue = "") final String word, @RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Newspaper> newspapers = new ArrayList<Newspaper>();

		if (word == null || word.equals(""))
			newspapers = this.newspaperService.findAll();
		else
			newspapers = this.newspaperService.getTabooNewspapers(word);

		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("message", message);
		result.addObject("requestURI", "newspaper/administrator/list.do");

		return result;
	}

	//Delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int newspaperId) {
		ModelAndView result;
		try {
			this.newspaperService.deleteAdmin(newspaperId);
			result = new ModelAndView("redirect:/newspaper/administrator/list.do");
		} catch (final Throwable oops) {
			String messageError = "newspaper.delete.error";
			if (oops.getMessage().contains("message.error"))
				messageError = oops.getMessage();
			result = new ModelAndView("redirect:/newspaper/administrator/list.do");
			result.addObject("message", messageError);
		}
		return result;
	}

}
