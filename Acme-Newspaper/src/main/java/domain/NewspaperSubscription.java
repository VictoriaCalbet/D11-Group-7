
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class NewspaperSubscription extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private Collection<CreditCard>	creditCards;
	private int						counter;


	@NotNull
	@Valid
	@ElementCollection
	public Collection<CreditCard> getCreditCards() {
		return this.creditCards;
	}
	public void setCreditCards(final Collection<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public int getCounter() {
		return this.counter;
	}
	public void setCounter(final int counter) {
		this.counter = counter;
	}


	// Relationships ----------------------------------------------------------

	private Newspaper	newspaper;
	private Customer	customer;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Newspaper getNewspaper() {
		return this.newspaper;
	}
	public void setNewspaper(final Newspaper newspaper) {
		this.newspaper = newspaper;
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
