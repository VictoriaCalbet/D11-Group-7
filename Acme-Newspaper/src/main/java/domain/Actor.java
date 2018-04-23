
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import security.UserAccount;
import cz.jirutka.validator.collection.constraints.EachEmail;
import cz.jirutka.validator.collection.constraints.EachPattern;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String				name;
	private String				surname;
	private Collection<String>	postalAddresses;
	private Collection<String>	phoneNumbers;
	private Collection<String>	emailAddresses;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Valid
	@NotNull
	@ElementCollection
	public Collection<String> getPostalAddresses() {
		return this.postalAddresses;
	}

	public void setPostalAddresses(final Collection<String> postalAddresses) {
		this.postalAddresses = postalAddresses;
	}

	@Valid
	@NotNull
	@ElementCollection
	@EachPattern(regexp = "^\\+?\\d+")
	public Collection<String> getPhoneNumbers() {
		return this.phoneNumbers;
	}

	public void setPhoneNumbers(final Collection<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@Valid
	@NotEmpty
	@ElementCollection
	@EachEmail
	public Collection<String> getEmailAddresses() {
		return this.emailAddresses;
	}

	public void setEmailAddresses(final Collection<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}


	// Relationships ----------------------------------------------------------

	private UserAccount			userAccount;
	private Collection<Folder>	folders;
	private Collection<Message>	messagesSent;
	private Collection<Message>	messageReceived;


	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "actor")
	@Size(min = 5)
	public Collection<Folder> getFolders() {
		return this.folders;
	}

	public void setFolders(final Collection<Folder> folders) {
		this.folders = folders;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "sent")
	public Collection<Message> getMessagesSent() {
		return this.messagesSent;
	}

	public void setMessagesSent(final Collection<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "recipient")
	public Collection<Message> getMessageReceived() {
		return this.messageReceived;
	}

	public void setMessageReceived(final Collection<Message> messageReceived) {
		this.messageReceived = messageReceived;
	}

}
