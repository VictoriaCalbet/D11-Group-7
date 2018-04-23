
package services.forms;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import security.UserAccount;
import security.UserAccountService;
import services.ActorService;
import services.AdministratorService;
import services.CustomerService;
import services.UserService;
import domain.Actor;
import domain.Administrator;
import domain.Customer;
import domain.User;
import domain.forms.ActorForm;

@Service
@Transactional
public class ActorFormService {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private UserService				userService;

	@Autowired
	private CustomerService			customerService;


	// Constructors -----------------------------------------------------------

	public ActorFormService() {
		super();
	}

	public ActorForm create() {
		ActorForm result;

		result = new ActorForm();
		result.setPostalAddresses(new HashSet<String>());
		result.setPhoneNumbers(new HashSet<String>());
		result.setEmailAddresses(new HashSet<String>());

		return result;
	}

	public ActorForm createFromActor() {
		ActorForm result;
		final Actor actor = this.actorService.findByPrincipal();

		result = new ActorForm();
		result.setId(actor.getId());
		result.setName(actor.getName());
		result.setSurname(actor.getSurname());
		result.setPostalAddresses(actor.getPostalAddresses());
		result.setPhoneNumbers(actor.getPhoneNumbers());
		result.setEmailAddresses(actor.getEmailAddresses());
		result.setUsername(actor.getUserAccount().getUsername());

		return result;
	}

	public Actor saveFromCreate(final ActorForm actorForm, final String authority) {
		Assert.notNull(actorForm, "message.error.actorForm.null");
		Assert.isTrue(actorForm.getPassword().equals(actorForm.getRepeatedPassword()), "message.error.actorForm.password.mismatch");
		Assert.isTrue(actorForm.getAcceptTerms(), "message.error.actorForm.acceptTerms.false");
		Assert.isTrue(actorForm.getId() == 0, "message.error.actorForm.id");

		switch (authority) {
		case "ADMIN": {
			final Administrator result;
			Administrator principal;

			principal = this.administratorService.findByPrincipal();
			Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"), "message.error.actorForm.admin.login");
			final Administrator administrator;
			final UserAccount userAccount;

			userAccount = this.userAccountService.createComplete(actorForm.getUsername(), actorForm.getPassword(), authority);
			administrator = this.administratorService.create();

			administrator.setName(actorForm.getName());
			administrator.setSurname(actorForm.getSurname());
			administrator.setPostalAddresses(actorForm.getPostalAddresses());
			administrator.setPhoneNumbers(actorForm.getPhoneNumbers());
			administrator.setEmailAddresses(actorForm.getEmailAddresses());
			administrator.setUserAccount(userAccount);

			result = this.administratorService.saveFromCreate(administrator);
			return result;
		}
		case "USER": {
			final User result;
			final User user;
			final UserAccount userAccount;

			Assert.isTrue(!this.actorService.checkLogin(), "message.error.actorForm.user.login");

			userAccount = this.userAccountService.createComplete(actorForm.getUsername(), actorForm.getPassword(), "USER");
			user = this.userService.create();

			user.setName(actorForm.getName());
			user.setSurname(actorForm.getSurname());
			user.setPostalAddresses(actorForm.getPostalAddresses());
			user.setPhoneNumbers(actorForm.getPhoneNumbers());
			user.setEmailAddresses(actorForm.getEmailAddresses());
			user.setUserAccount(userAccount);

			result = this.userService.saveFromCreate(user);
			return result;
		}
		case "CUSTOMER": {
			final Customer result;
			final Customer customer;
			final UserAccount userAccount;

			Assert.isTrue(!this.actorService.checkLogin(), "message.error.actorForm.customer.login");

			userAccount = this.userAccountService.createComplete(actorForm.getUsername(), actorForm.getPassword(), "CUSTOMER");
			customer = this.customerService.create();

			customer.setName(actorForm.getName());
			customer.setSurname(actorForm.getSurname());
			customer.setPostalAddresses(actorForm.getPostalAddresses());
			customer.setPhoneNumbers(actorForm.getPhoneNumbers());
			customer.setEmailAddresses(actorForm.getEmailAddresses());
			customer.setUserAccount(userAccount);

			result = this.customerService.saveFromCreate(customer);
			return result;
		}
		default:
			return null;
		}
	}

	public Actor saveFromEdit(final ActorForm actorForm, final String authority) {
		Assert.notNull(actorForm, "message.error.actorForm.null");
		Assert.isTrue(actorForm.getPassword().equals(actorForm.getRepeatedPassword()), "message.error.actorForm.password.mismatch");

		switch (authority) {
		case "ADMIN": {
			final Actor principal = this.actorService.findByPrincipal();
			final UserAccount userAccount;

			Assert.isTrue(principal.getId() == actorForm.getId(), "message.error.actorForm.edit.self");

			userAccount = principal.getUserAccount();
			userAccount.setPassword(actorForm.getPassword());

			final Administrator result;
			final Administrator administrator;

			administrator = this.administratorService.findByPrincipal();

			administrator.setName(actorForm.getName());
			administrator.setSurname(actorForm.getSurname());
			administrator.setPostalAddresses(actorForm.getPostalAddresses());
			administrator.setPhoneNumbers(actorForm.getPhoneNumbers());
			administrator.setEmailAddresses(actorForm.getEmailAddresses());
			administrator.setUserAccount(userAccount);

			result = this.administratorService.saveFromEdit(administrator);
			return result;
		}
		case "USER": {
			final Actor principal = this.actorService.findByPrincipal();
			final UserAccount userAccount;

			Assert.isTrue(principal.getId() == actorForm.getId(), "message.error.actorForm.edit.self");

			userAccount = principal.getUserAccount();
			userAccount.setPassword(actorForm.getPassword());

			final User result;
			final User user;

			user = this.userService.findByPrincipal();

			user.setName(actorForm.getName());
			user.setSurname(actorForm.getSurname());
			user.setPostalAddresses(actorForm.getPostalAddresses());
			user.setPhoneNumbers(actorForm.getPhoneNumbers());
			user.setEmailAddresses(actorForm.getEmailAddresses());
			user.setUserAccount(userAccount);

			result = this.userService.saveFromEdit(user);
			return result;
		}
		case "CUSTOMER": {
			final Actor principal = this.actorService.findByPrincipal();
			final UserAccount userAccount;

			Assert.isTrue(principal.getId() == actorForm.getId(), "message.error.actorForm.edit.self");

			userAccount = principal.getUserAccount();
			userAccount.setPassword(actorForm.getPassword());

			final Customer result;
			final Customer customer;

			customer = this.customerService.findByPrincipal();

			customer.setName(actorForm.getName());
			customer.setSurname(actorForm.getSurname());
			customer.setPostalAddresses(actorForm.getPostalAddresses());
			customer.setPhoneNumbers(actorForm.getPhoneNumbers());
			customer.setEmailAddresses(actorForm.getEmailAddresses());
			customer.setUserAccount(userAccount);

			result = this.customerService.saveFromEdit(customer);
			return result;
		}
		default:
			return null;
		}
	}
}
