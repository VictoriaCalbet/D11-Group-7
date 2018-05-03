
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"customer_id", "volume_id"
	})
})
public class VolumeSubscription extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private CreditCard	creditCard;


	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}


	// Relationships ----------------------------------------------------------

	private Volume		volume;
	private Customer	customer;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Volume getVolume() {
		return this.volume;
	}
	public void setVolume(final Volume volume) {
		this.volume = volume;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

}
