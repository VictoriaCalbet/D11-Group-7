
package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.NewspaperService;
import services.NewspaperSubscriptionService;
import services.forms.NewspaperSubscriptionFormService;
import controllers.AbstractController;
import domain.Customer;
import domain.Newspaper;
import domain.NewspaperSubscription;
import domain.forms.NewspaperSubscriptionForm;

@Controller
@RequestMapping("/newspaperSubscription/customer")
public class NewspaperSubscriptionCustomerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private NewspaperSubscriptionService		newspaperSubscriptionService;

	@Autowired
	private CustomerService						customerService;

	@Autowired
	private NewspaperService					newspaperService;

	@Autowired
	private NewspaperSubscriptionFormService	newspaperSubscriptionFormService;


	// Constructors ---------------------------------------------------------

	public NewspaperSubscriptionCustomerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = null;
		Customer customer = null;
		Collection<NewspaperSubscription> newspaperSubscriptions = null;
		String requestURI = null;
		String displayURI = null;
		boolean existsAvailablesNewspapers = false;

		customer = this.customerService.findByPrincipal();
		newspaperSubscriptions = customer.getNewspaperSubscriptions();
		requestURI = "newspaperSubscription/customer/list.do";
		displayURI = "newspaperSubscription/customer/display.do?newspaperSubscriptionId=";

		result = new ModelAndView("newspaperSubscription/list");
		result.addObject("newspaperSubscriptions", newspaperSubscriptions);
		result.addObject("requestURI", requestURI);
		result.addObject("displayURI", displayURI);

		if (this.newspaperService.findAvailableNewspapersByCustomerId(customer.getId()).size() != 0)
			existsAvailablesNewspapers = true;

		result.addObject("existsAvailablesNewspapers", existsAvailablesNewspapers);

		return result;
	}

	// Creation  ------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = null;
		NewspaperSubscriptionForm newspaperSubscriptionForm = null;

		newspaperSubscriptionForm = this.newspaperSubscriptionFormService.createFromCreate();
		result = this.createModelAndView(newspaperSubscriptionForm);

		return result;
	}

	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int newspaperSubscriptionId) {
		ModelAndView result = null;
		NewspaperSubscription newspaperSubscription = null;
		Customer customer = null;

		newspaperSubscription = this.newspaperSubscriptionService.findOne(newspaperSubscriptionId);
		customer = this.customerService.findByPrincipal();

		Assert.notNull(newspaperSubscription);
		Assert.isTrue(customer.equals(newspaperSubscription.getCustomer()));

		result = new ModelAndView("newspaperSubscription/display");
		result.addObject("newspaperSubscription", newspaperSubscription);
		result.addObject("cancelURI", "/newspaperSubscription/customer/list.do");

		return result;
	}

	// Edition    -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final NewspaperSubscriptionForm newspaperSubscriptionForm, final BindingResult bindingResult) {
		ModelAndView result = null;

		if (bindingResult.hasErrors())
			result = this.createModelAndView(newspaperSubscriptionForm);
		else
			try {
				this.newspaperSubscriptionFormService.saveFromCreate(newspaperSubscriptionForm);
				result = new ModelAndView("redirect:/newspaperSubscription/customer/list.do");
			} catch (final Throwable oops) {
				String messageError = "newspaperSubscription.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createModelAndView(newspaperSubscriptionForm, messageError);
			}

		return result;
	}

	// Other actions --------------------------------------------------------

	protected ModelAndView createModelAndView(final NewspaperSubscriptionForm newspaperSubscriptionForm) {
		ModelAndView result = null;

		result = this.createModelAndView(newspaperSubscriptionForm, null);

		return result;
	}

	protected ModelAndView createModelAndView(final NewspaperSubscriptionForm newspaperSubscriptionForm, final String message) {
		ModelAndView result = null;
		String actionURI = null;
		Collection<Newspaper> availableNewspapers = null;
		Customer customer = null;

		actionURI = "newspaperSubscription/customer/edit.do";

		customer = this.customerService.findByPrincipal();
		availableNewspapers = this.newspaperService.findAvailableNewspapersByCustomerId(customer.getId());

		result = new ModelAndView("newspaperSubscription/create");
		result.addObject("customer", customer);
		result.addObject("newspaperSubscriptionForm", newspaperSubscriptionForm);
		result.addObject("actionURI", actionURI);
		result.addObject("availableNewspapers", availableNewspapers);
		result.addObject("message", message);

		return result;
	}
}
