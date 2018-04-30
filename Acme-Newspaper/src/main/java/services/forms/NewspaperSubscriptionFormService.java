
package services.forms;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import services.CustomerService;
import services.NewspaperService;
import services.NewspaperSubscriptionService;
import domain.Customer;
import domain.Newspaper;
import domain.NewspaperSubscription;
import domain.forms.NewspaperSubscriptionForm;

@Service
@Transactional
public class NewspaperSubscriptionFormService {

	// Supporting services ----------------------------------------------------

	@Autowired
	private NewspaperSubscriptionService	newspaperSubscriptionService;

	@Autowired
	private CustomerService					customerService;

	@Autowired
	private NewspaperService				newspaperService;


	// Constructors -----------------------------------------------------------

	public NewspaperSubscriptionFormService() {
		super();
	}

	// Creación de formularios ------------------------------------------------

	// Utilizado al crear una nueva entidad de newspaperSubscription (createFromCreate)

	public NewspaperSubscriptionForm createFromCreate() {
		NewspaperSubscriptionForm result = null;

		result = new NewspaperSubscriptionForm();

		return result;
	}

	// Utilizado al editar una nueva entidad de newspaperSubscription (createFromEdit) - NO ES NECESARIA

	// Reconstrucción de objetos (Reconstruct) --------------------------------

	public NewspaperSubscription saveFromCreate(final NewspaperSubscriptionForm newspaperSubscriptionForm) {
		NewspaperSubscription newspaperSubscription = null;
		NewspaperSubscription result = null;
		Customer customer = null;
		Newspaper newspaper = null;

		Assert.notNull(newspaperSubscriptionForm, "message.error.newspaperSubscription.null");

		newspaperSubscription = this.newspaperSubscriptionService.create();
		customer = this.customerService.findByPrincipal();
		newspaper = this.newspaperService.findOne(newspaperSubscriptionForm.getNewspaperId());

		newspaperSubscription.setCustomer(customer);
		newspaperSubscription.setNewspaper(newspaper);
		newspaperSubscription.setCreditCard(newspaperSubscriptionForm.getCreditCard());

		result = this.newspaperSubscriptionService.saveFromCreate(newspaperSubscription);

		return result;
	}
}
