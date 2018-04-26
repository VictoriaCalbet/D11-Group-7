
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Customer;
import domain.Folder;
import domain.Message;
import domain.NewspaperSubscription;
import domain.VolumeSubscription;

@Service
@Transactional
public class CustomerService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private CustomerRepository	customerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private FolderService		folderService;


	// Constructors -----------------------------------------------------------

	public CustomerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Customer create() {
		final Customer result;
		final UserAccount userAccount;

		result = new Customer();

		result.setEmailAddresses(new HashSet<String>());
		result.setPhoneNumbers(new HashSet<String>());
		result.setPostalAddresses(new HashSet<String>());

		result.setNewspaperSubscriptions(new HashSet<NewspaperSubscription>());
		result.setVolumeSubscriptions(new HashSet<VolumeSubscription>());

		result.setFolders(new HashSet<Folder>());
		result.setMessagesSent(new HashSet<Message>());
		result.setMessagesReceived(new HashSet<Message>());

		userAccount = this.userAccountService.create("CUSTOMER");
		result.setUserAccount(userAccount);

		return result;
	}
	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Customer save(final Customer customer) {
		Assert.notNull(customer, "message.error.customer.null");
		Customer result;
		result = this.customerRepository.save(customer);
		return result;
	}

	public void flush() {
		this.customerRepository.flush();
	}

	public Customer saveFromCreate(final Customer customer) {
		Assert.notNull(customer, "message.error.customer.null");

		Customer result;

		// Check unlogged user
		Assert.isTrue(!this.actorService.checkLogin(), "message.error.customer.login");

		// Check Authority
		final boolean isCustomer;
		isCustomer = this.actorService.checkAuthority(customer, "CUSTOMER");
		Assert.isTrue(isCustomer, "message.error.customer.authority.wrong");

		// Check repeated username
		UserAccount possibleRepeated = null;
		possibleRepeated = this.userAccountService.findByUsername(customer.getUserAccount().getUsername());
		Assert.isNull(possibleRepeated, "message.error.administrator.username.repeated");

		result = this.save(customer);

		// Add system folders
		final Collection<Folder> systemFolders = this.folderService.initializeFolders(result);
		result.setFolders(systemFolders);

		result = this.save(result);

		return result;
	}

	public Customer saveFromEdit(final Customer customer) {
		Assert.notNull(customer, "message.error.customer.null");

		final Customer result;
		Customer principal;

		// Check logged customer
		principal = this.findByPrincipal();
		Assert.isTrue(principal.getId() == customer.getId(), "message.error.customer.login");

		// Check Authority
		final boolean isCustomer;
		isCustomer = this.actorService.checkAuthority(customer, "CUSTOMER");
		Assert.isTrue(isCustomer, "message.error.customer.authority.wrong");

		// Encoding password
		UserAccount userAccount;
		userAccount = customer.getUserAccount();
		userAccount = this.userAccountService.modifyPassword(userAccount);
		customer.setUserAccount(userAccount);

		result = this.save(customer);

		return result;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> result = null;
		result = this.customerRepository.findAll();
		return result;
	}

	public Customer findOne(final int customerId) {
		Customer result = null;
		result = this.customerRepository.findOne(customerId);
		return result;
	}

	// Other business methods -------------------------------------------------

	public Customer findByPrincipal() {
		Customer result = null;
		UserAccount userAccount = null;

		userAccount = LoginService.getPrincipal();
		result = this.customerRepository.findByUserAccountId(userAccount.getId());

		Assert.notNull(result);

		return result;
	}
}
