
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.UserService;
import services.VolumeService;
import domain.Actor;
import domain.User;
import domain.Volume;

@Controller
@RequestMapping("/volume")
public class VolumeController extends AbstractController {

	//Services
	@Autowired
	private VolumeService	volumeService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private UserService		userService;


	//Constructor

	public VolumeController() {
		super();
	}

	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Volume> volumes = new ArrayList<Volume>();
		Collection<Volume> principalVolumes = new ArrayList<Volume>();

		if (this.actorService.checkLogin()) {
			final Actor principal1 = this.actorService.findByPrincipal();
			User principal2 = null;
			if (this.actorService.checkAuthority(principal1, "USER")) {
				principal2 = this.userService.findByPrincipal();
				principalVolumes = principal2.getVolumes();
			}
		}
		volumes = this.volumeService.findAll();
		result = new ModelAndView("volume/list");
		result.addObject("volumes", volumes);
		result.addObject("principalVolumes", principalVolumes);
		result.addObject("requestURI", "volume/list.do");
		return result;

	}
}
