
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Administrator;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class AdministratorService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository	administratorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private FolderService			folderService;


	// Constructors -----------------------------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Administrator create() {
		final Administrator result;
		final UserAccount userAccount;

		result = new Administrator();

		result.setEmailAddresses(new HashSet<String>());
		result.setPhoneNumbers(new HashSet<String>());
		result.setPostalAddresses(new HashSet<String>());

		result.setFolders(new HashSet<Folder>());
		result.setMessagesSent(new HashSet<Message>());
		result.setMessagesReceived(new HashSet<Message>());

		userAccount = this.userAccountService.create("ADMIN");
		result.setUserAccount(userAccount);

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Administrator save(final Administrator administrator) {
		Assert.notNull(administrator, "message.error.administrator.null");
		Administrator result;
		result = this.administratorRepository.save(administrator);
		return result;
	}

	public void flush() {
		this.administratorRepository.flush();
	}

	public Administrator saveFromCreate(final Administrator administrator) {
		Assert.notNull(administrator, "message.error.administrator.null");

		Administrator result;
		final Administrator principal;

		// Check admin as principal
		principal = this.findByPrincipal();
		Assert.notNull(principal, "message.error.administrator.principal.admin");

		// Check Authority
		final boolean isAdmin;
		isAdmin = this.actorService.checkAuthority(administrator, "ADMIN");
		Assert.isTrue(isAdmin, "message.error.administrator.authority.wrong");

		// Check repeated username
		UserAccount possibleRepeated = null;
		possibleRepeated = this.userAccountService.findByUsername(administrator.getUserAccount().getUsername());
		Assert.isNull(possibleRepeated, "message.error.administrator.username.repeated");

		result = this.save(administrator);

		// Add system folders
		final Collection<Folder> systemFolders = this.folderService.initializeFolders(result);
		result.setFolders(systemFolders);

		result = this.save(result);

		return result;
	}

	public Administrator saveFromEdit(final Administrator administrator) {
		Assert.notNull(administrator, "message.error.administrator.null");

		final Administrator result;
		final Administrator principal;

		// Check admin as principal
		principal = this.findByPrincipal();
		Assert.notNull(principal, "message.error.administrator.principal.admin");

		// Check Authority
		final boolean isAdmin;
		isAdmin = this.actorService.checkAuthority(administrator, "ADMIN");
		Assert.isTrue(isAdmin, "message.error.administrator.authority.wrong");

		// Encoding password
		UserAccount userAccount;
		userAccount = administrator.getUserAccount();
		userAccount = this.userAccountService.modifyPassword(userAccount);
		administrator.setUserAccount(userAccount);

		result = this.save(administrator);

		return result;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result = null;
		result = this.administratorRepository.findAll();
		return result;
	}

	public Administrator findOne(final int administratorId) {
		Administrator result = null;
		result = this.administratorRepository.findOne(administratorId);
		return result;
	}

	// Other business methods -------------------------------------------------

	public Administrator findByPrincipal() {
		Administrator result = null;
		UserAccount userAccount = null;

		userAccount = LoginService.getPrincipal();
		result = this.administratorRepository.findByUserAccountId(userAccount.getId());

		Assert.notNull(result);

		return result;
	}
}
