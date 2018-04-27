
package controllers.agent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdvertisementService;
import services.AgentService;
import controllers.AbstractController;
import domain.Advertisement;
import domain.Agent;

@Controller
@RequestMapping("/advertisement/agent")
public class AdvertisementAgentController extends AbstractController {

	@Autowired
	private AdvertisementService	advertisementService;
	@Autowired
	private AgentService			agentService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		List<Advertisement> advertisements;
		advertisements = new ArrayList<Advertisement>();
		Agent agent;
		agent = this.agentService.findByPrincipal();
		advertisements.addAll(agent.getAdvertisements());
		result = new ModelAndView("advertisement/list");
		result.addObject("advertisements", advertisements);
		result.addObject("requestURI", "advertisement/agent.list.do");
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
		result.addObject("requestURI", "advertisement/agent/edit.do?advertisementId=" + advertisement.getId());

		return result;
	}
}
