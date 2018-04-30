
package services.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.CustomerService;
import services.VolumeService;
import services.VolumeSubscriptionService;
import domain.Customer;
import domain.Volume;
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

	@Autowired
	private VolumeService				volumeService;


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
		VolumeSubscription volumeSubscription = null;
		VolumeSubscription result = null;
		Customer customer = null;
		Volume volume = null;

		Assert.notNull(volumeSubscriptionForm, "message.error.volumeSubscription.null");

		volumeSubscription = this.volumeSubscriptionService.create();
		customer = this.customerService.findByPrincipal();
		volume = this.volumeService.findOne(volumeSubscriptionForm.getVolumeId());

		volumeSubscription.setCustomer(customer);
		volumeSubscription.setVolume(volume);
		volumeSubscription.setCreditCard(volumeSubscriptionForm.getCreditCard());

		result = this.volumeSubscriptionService.saveFromCreate(volumeSubscription);

		return result;
	}
}
