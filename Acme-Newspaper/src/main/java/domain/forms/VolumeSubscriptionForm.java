
package domain.forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.CreditCard;

@Service
@Transactional
public class VolumeSubscriptionForm {

	// Form attributes
	private int			volumeId;
	private CreditCard	creditCard;


	public int getVolumeId() {
		return this.volumeId;
	}

	public void setVolumeId(final int volumeId) {
		this.volumeId = volumeId;
	}

	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}
}
