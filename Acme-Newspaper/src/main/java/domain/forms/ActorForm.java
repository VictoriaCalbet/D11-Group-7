
package domain.forms;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import cz.jirutka.validator.collection.constraints.EachEmail;
import cz.jirutka.validator.collection.constraints.EachPattern;

public class ActorForm {

	// Attributes -------------------------------------------------------------

	private int					id;
	private String				name;
	private String				surname;
	private Collection<String>	postalAddresses;
	private Collection<String>	phoneNumbers;
	private Collection<String>	emailAddresses;
	private String				username;
	private String				password;
	private String				repeatedPassword;
	private boolean				acceptTerms;


	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

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

	@NotBlank
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getRepeatedPassword() {
		return this.repeatedPassword;
	}

	public void setRepeatedPassword(final String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public boolean getAcceptTerms() {
		return this.acceptTerms;
	}

	public void setAcceptTerms(final boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}

	// Relationships ----------------------------------------------------------

}
