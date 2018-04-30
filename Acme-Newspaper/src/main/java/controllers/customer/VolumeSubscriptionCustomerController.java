
package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.VolumeService;
import services.VolumeSubscriptionService;
import controllers.AbstractController;
import domain.Customer;
import domain.Volume;
import domain.VolumeSubscription;

@Controller
@RequestMapping("/volumeSubscription/customer")
public class VolumeSubscriptionCustomerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private VolumeSubscriptionService	volumeSubscriptionService;

	@Autowired
	private VolumeService				volumeService;

	@Autowired
	private CustomerService				customerService;


	// Constructors ---------------------------------------------------------

	public VolumeSubscriptionCustomerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = null;
		Customer customer = null;
		Collection<VolumeSubscription> volumeSubscriptions = null;
		String requestURI = null;
		String displayURI = null;
		boolean existsAvailableVolumes = false;

		customer = this.customerService.findByPrincipal();
		volumeSubscriptions = customer.getVolumeSubscriptions();
		requestURI = "volumeSubscription/customer/list.do";
		displayURI = "volumeSubscription/customer/display.do?volumeSubscriptionId=";

		result = new ModelAndView("volumeSubscription/list");
		result.addObject("volumeSubscriptions", volumeSubscriptions);
		result.addObject("requestURI", requestURI);
		result.addObject("displayURI", displayURI);

		if (this.volumeService.findAvailableVolumesByCustomerId(customer.getId()).size() != 0)
			existsAvailableVolumes = true;

		result.addObject("existsAvailableVolumes", existsAvailableVolumes);

		return result;

	}

	// Creation  ------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = null;
		VolumeSubscription volumeSubscription = null;

		volumeSubscription = this.volumeSubscriptionService.create();
		result = this.createModelAndView(volumeSubscription);

		return result;
	}

	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int volumeSubscriptionId) {
		ModelAndView result = null;
		VolumeSubscription volumeSubscription = null;

		volumeSubscription = this.volumeSubscriptionService.findOne(volumeSubscriptionId);

		Assert.notNull(volumeSubscription);

		result = new ModelAndView("volumeSubscription/display");
		result.addObject("volumeSubscription", volumeSubscription);
		result.addObject("cancelURI", "/volumeSubscription/customer/list.do");

		return result;
	}

	// Edition    -----------------------------------------------------------
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid final VolumeSubscriptionForm volumeSubscriptionForm, final BindingResult bindingResult) {
	//		ModelAndView result = null;
	//
	//		if (bindingResult.hasErrors())
	//			result = this.createModelAndView(volumeSubscriptionForm);
	//		else
	//			try {
	//				this.volumeSubscriptionFormService.saveFromCreate(volumeSubscriptionForm);
	//				result = new ModelAndView("redirect:/volumeSubscription/customer/list.do");
	//			} catch (final Throwable oops) {
	//				String messageError = "volumeSubscription.commit.error";
	//				if (oops.getMessage().contains("message.error"))
	//					messageError = oops.getMessage();
	//				result = this.createModelAndView(volumeSubscriptionForm, messageError);
	//			}
	//
	//		return result;
	//	}

	// Other actions --------------------------------------------------------

	private ModelAndView createModelAndView(final VolumeSubscription volumeSubscription) {
		ModelAndView result = null;
		result = this.createModelAndView(volumeSubscription, null);
		return result;
	}

	private ModelAndView createModelAndView(final VolumeSubscription volumeSubscription, final String message) {
		ModelAndView result = null;
		String actionURI = null;
		Collection<Volume> availableVolumes = null;
		Customer customer = null;

		actionURI = "volumeSubscription/customer/edit.do";

		customer = this.customerService.findByPrincipal();
		availableVolumes = this.volumeService.findAvailableVolumesByCustomerId(customer.getId());

		result = new ModelAndView("volumeSubscription/create");
		result.addObject("volumeSubscription", volumeSubscription);
		result.addObject("actionURI", actionURI);
		result.addObject("availableVolumes", availableVolumes);
		result.addObject("message", message);

		return result;
	}
}
