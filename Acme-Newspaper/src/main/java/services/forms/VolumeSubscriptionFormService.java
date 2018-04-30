
package services.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.CustomerService;
import services.VolumeSubscriptionService;
import domain.Customer;
import domain.Newspaper;
import domain.VolumeSubscription;
import domain.forms.VolumeSubscriptionForm;

@Service
@Transactional
public class VolumeSubscriptionFormService {

	// Supporting services ----------------------------------------------------

	@Autowired
	private VolumeSubscriptionService	volumeSubscriptionService;

	@Autowired
	private CustomerService				customerService;


	// Constructors -----------------------------------------------------------

	public VolumeSubscriptionFormService() {
		super();
	}

	// Creación de formularios ------------------------------------------------

	// Utilizado al crear una nueva entidad de volumeSubscription (createFromCreate)

	public VolumeSubscriptionForm createFromCreate() {
		VolumeSubscriptionForm result = null;

		result = new VolumeSubscriptionForm();

		return result;
	}

	// Utilizado al editar una nueva entidad de newspaperSubscription (createFromEdit)

	// Reconstrucción de objetos (Reconstruct) --------------------------------

	public VolumeSubscription saveFromCreate(final VolumeSubscriptionForm volumeSubscriptionForm) {
		final VolumeSubscription volumeSubscription = null;
		final VolumeSubscription result = null;
		final Customer customer = null;
		final Newspaper newspaper = null;

		Assert.notNull(volumeSubscriptionForm, "message.error.volumeSubscription.null");

		//		volumeSubscription = this.volumeSubscriptionService.create();
		//		customer = this.customerService.findByPrincipal();
		//		newspaper = this.newspaperService.findOne(volumeSubscriptionForm.getNewspaperId());
		//
		//		newspaperSubscription.setCustomer(customer);
		//		newspaperSubscription.setNewspaper(newspaper);
		//		newspaperSubscription.getCreditCards().add(volumeSubscriptionForm.getCreditCard());
		//
		//		newspaperSubscription.setCounter(1);		// Es el primer newspaperSubscription que se crea
		//
		//		result = this.volumeSubscriptionService.saveFromCreate(volumeSubscription);
		//
		return result;
	}
}
