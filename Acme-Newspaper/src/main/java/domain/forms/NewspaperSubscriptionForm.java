
package domain.forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.CreditCard;

@Service
@Transactional
public class NewspaperSubscriptionForm {

	// Form attributes
	private int			id;
	private int			newspaperId;
	private CreditCard	creditCard;


	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getNewspaperId() {
		return this.newspaperId;
	}

	public void setNewspaperId(final int newspaperId) {
		this.newspaperId = newspaperId;
	}

	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

}
