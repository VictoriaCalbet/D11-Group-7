
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
import controllers.AbstractController;
import domain.Customer;
import domain.Newspaper;
import domain.NewspaperSubscription;

@Controller
@RequestMapping("/newspaperSubscription/customer")
public class NewspaperSubscriptionCustomerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private NewspaperSubscriptionService	newspaperSubscriptionService;

	@Autowired
	private CustomerService					customerService;

	@Autowired
	private NewspaperService				newspaperService;


	// Constructors ---------------------------------------------------------

	public NewspaperSubscriptionCustomerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = null;
		Customer customer = null;
		Collection<NewspaperSubscription> subscriptions = null;
		String requestURI = null;
		String displayURI = null;
		boolean existsAvailablesNewspapers = false;

		customer = this.customerService.findByPrincipal();
		subscriptions = customer.getNewspaperSubscriptions();
		requestURI = "newspaperSubscription/customer/list.do";
		displayURI = "newspaperSubscription/customer/display.do?subscriptionId=";

		result = new ModelAndView("newspaperSubscription/list");
		result.addObject("subscriptions", subscriptions);
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
		NewspaperSubscription subscription = null;

		subscription = this.newspaperSubscriptionService.create();
		result = this.createModelAndView(subscription);

		return result;
	}

	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int subscriptionId) {
		ModelAndView result = null;
		NewspaperSubscription subscription = null;

		subscription = this.newspaperSubscriptionService.findOne(subscriptionId);

		Assert.notNull(subscription);

		result = new ModelAndView("newspaperSubscription/display");
		result.addObject("subscription", subscription);
		result.addObject("cancelURI", "/newspaperSubscription/customer/list.do");

		return result;
	}

	// Edition    -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final NewspaperSubscription subscription, final BindingResult bindingResult) {
		ModelAndView result = null;

		if (bindingResult.hasErrors())
			result = this.createModelAndView(subscription);
		else
			try {
				this.newspaperSubscriptionService.saveFromCreate(subscription);
				result = new ModelAndView("redirect:/newspaperSubscription/customer/list.do");
			} catch (final Throwable oops) {
				String messageError = "subscription.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createModelAndView(subscription, messageError);
			}

		return result;
	}

	// Other actions --------------------------------------------------------

	protected ModelAndView createModelAndView(final NewspaperSubscription subscription) {
		ModelAndView result = null;

		result = this.createModelAndView(subscription, null);

		return result;
	}

	protected ModelAndView createModelAndView(final NewspaperSubscription subscription, final String message) {
		ModelAndView result = null;
		String actionURI = null;
		Collection<Newspaper> availableNewspapers = null;
		Customer customer = null;

		actionURI = "newspaperSubscription/customer/edit.do";

		customer = this.customerService.findByPrincipal();
		availableNewspapers = this.newspaperService.findAvailableNewspapersByCustomerId(customer.getId());

		result = new ModelAndView("newspaperSubscription/create");
		result.addObject("customer", customer);
		result.addObject("subscription", subscription);
		result.addObject("actionURI", actionURI);
		result.addObject("availableNewspapers", availableNewspapers);
		result.addObject("message", message);

		return result;
	}
}
