
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.VolumeService;
import domain.Volume;

@Controller
@RequestMapping("/volume")
public class VolumeController extends AbstractController {

	//Services
	@Autowired
	private VolumeService	volumeService;


	//Constructor

	public VolumeController() {
		super();
	}

	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Volume> volumes = new ArrayList<Volume>();

		volumes = this.volumeService.findAll();
		result = new ModelAndView("volume/list");//tiles
		result.addObject("volumes", volumes);
		result.addObject("requestURI", "volume/list.do");//view
		return result;

	}
}
