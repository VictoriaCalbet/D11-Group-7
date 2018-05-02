
package controllers.agent;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/advertisement/agent")
public class AdvertisementAgentController extends AbstractController {

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
		Agent agent;
		agent = this.agentService.findByPrincipal();
		advertisements.addAll(agent.getAdvertisements());
		result = new ModelAndView("advertisement/list");
		result.addObject("advertisements", advertisements);
		result.addObject("requestURI", "advertisement/agent/list.do");
		result.addObject("deleteURI", "advertisement/agent/delete.do?advertisementId=");
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int advertisementId) {
		final ModelAndView result;
		Advertisement advertisement;

		advertisement = this.advertisementService.findOne(advertisementId);
		result = this.createEditModelAndView(advertisement);

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Advertisement advertisement;

		advertisement = this.advertisementService.create();
		result = this.createEditModelAndView(advertisement);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Advertisement advertisement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(advertisement);
		else
			try {

				if (advertisement.getId() != 0)
					this.advertisementService.saveFromEdit(advertisement);
				else
					this.advertisementService.saveFromCreate(advertisement);
				result = new ModelAndView("redirect:/advertisement/agent/list.do");
			} catch (final Throwable oops) {
				String messageError = "advertisement.commit.error";

				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndView(advertisement, messageError);

			}

		return result;
	}
	/*
	 * @RequestMapping(value = "/delete", method = RequestMethod.GET)
	 * public ModelAndView delete(@RequestParam final int advertisementId) {
	 * ModelAndView result;
	 * Advertisement advertisementInDB;
	 * advertisementInDB = this.advertisementService.findOne(advertisementId);
	 * try {
	 * this.advertisementService.deleteByAgent(advertisementInDB);
	 * result = new ModelAndView("redirect:/advertisement/agent/list.do");
	 * } catch (final Throwable oops) {
	 * List<Advertisement> advertisements;
	 * advertisements = new ArrayList<Advertisement>();
	 * Agent agent;
	 * agent = this.agentService.findByPrincipal();
	 * advertisements.addAll(agent.getAdvertisements());
	 * result = new ModelAndView("advertisement/list");
	 * result.addObject("advertisements", advertisements);
	 * result.addObject("requestURI", "advertisement/agent/list.do");
	 * String messageError = "advertisement.commit.error";
	 * 
	 * if (oops.getMessage().contains("message.error"))
	 * messageError = oops.getMessage();
	 * result.addObject("message", messageError);
	 * result.addObject("deleteURI", "advertisement/agent/delete.do?advertisementId=");
	 * 
	 * }
	 * return result;
	 * 
	 * }
	 */

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
