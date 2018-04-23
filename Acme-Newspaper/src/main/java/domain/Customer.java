
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------
	private Collection<NewspaperSubscription>	newspaperSubscriptions;


	@Valid
	@NotNull
	@OneToMany(mappedBy = "customer")
	public Collection<NewspaperSubscription> getNewspaperSubscriptions() {
		return this.newspaperSubscriptions;
	}

	public void setNewspaperSubscriptions(final Collection<NewspaperSubscription> newspaperSubscriptions) {
		this.newspaperSubscriptions = newspaperSubscriptions;
	}
}
