
package controllers.administrator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdvertisementService;
import services.AgentService;
import services.NewspaperService;
import controllers.AbstractController;
import domain.Advertisement;
import domain.Agent;

@Controller
@RequestMapping("/advertisement/administrator")
public class AdvertisementAdministratorController extends AbstractController {

	@Autowired
	private AdvertisementService	advertisementService;
	@Autowired
	private AgentService			agentService;
	@Autowired
	private NewspaperService		newspaperService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		List<Advertisement> advertisements;
		advertisements = new ArrayList<Advertisement>();

		advertisements.addAll(this.advertisementService.findAll());
		result = new ModelAndView("advertisement/list");
		result.addObject("advertisements", advertisements);
		result.addObject("requestURI", "advertisement/administrator/list.do");
		result.addObject("deleteURI", "advertisement/administrator/delete.do?advertisementId=");
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int advertisementId) {
		ModelAndView result;
		Advertisement advertisementInDB;
		advertisementInDB = this.advertisementService.findOne(advertisementId);
		try {
			this.advertisementService.deleteByAdmin(advertisementInDB);
			result = new ModelAndView("redirect:/advertisement/administrator/list.do");
		} catch (final Throwable oops) {
			List<Advertisement> advertisements;
			advertisements = new ArrayList<Advertisement>();
			Agent agent;
			agent = this.agentService.findByPrincipal();
			advertisements.addAll(agent.getAdvertisements());
			result = new ModelAndView("advertisement/list");
			result.addObject("advertisements", advertisements);
			result.addObject("requestURI", "advertisement/administrator/list.do");
			String messageError = "advertisement.commit.error";

			if (oops.getMessage().contains("message.error"))
				messageError = oops.getMessage();
			result.addObject("message", messageError);
			result.addObject("deleteURI", "advertisement/administrator/delete.do?advertisementId=");

		}
		return result;

	}

	// Ancillary methods

	public ModelAndView createEditModelAndView(final Advertisement advertisement) {
		ModelAndView result;

		result = this.createEditModelAndView(advertisement, null);

		return result;
	}

	public ModelAndView createEditModelAndView(final Advertisement advertisement, final String message) {
		ModelAndView result;

		result = new ModelAndView("advertisement/edit");
		result.addObject("advertisement", advertisement);
		result.addObject("message", message);
		result.addObject("requestURI", "advertisement/agent/edit.do");
		result.addObject("newspapers", this.newspaperService.findAll());

		return result;
	}
}
