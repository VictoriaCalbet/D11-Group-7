
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Article;
import domain.Chirp;
import domain.Folder;
import domain.FollowUp;
import domain.Message;
import domain.Newspaper;
import domain.User;
import domain.Volume;

@Service
@Transactional
public class UserService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private UserRepository		userRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private FolderService		folderService;


	// Constructors -----------------------------------------------------------

	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public User create() {
		final User result;
		final UserAccount userAccount;

		result = new User();

		result.setEmailAddresses(new HashSet<String>());
		result.setPhoneNumbers(new HashSet<String>());
		result.setPostalAddresses(new HashSet<String>());

		result.setNewspapers(new HashSet<Newspaper>());
		result.setArticles(new HashSet<Article>());
		result.setFollowUps(new HashSet<FollowUp>());
		result.setChirps(new HashSet<Chirp>());
		result.setFollowed(new HashSet<User>());
		result.setFollowers(new HashSet<User>());
		result.setVolumes(new HashSet<Volume>());

		result.setFolders(new HashSet<Folder>());
		result.setMessagesSent(new HashSet<Message>());
		result.setMessagesReceived(new HashSet<Message>());

		userAccount = this.userAccountService.create("USER");
		result.setUserAccount(userAccount);

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public User save(final User user) {
		Assert.notNull(user);
		User result;
		result = this.userRepository.save(user);
		return result;
	}

	public void flush() {
		this.userRepository.flush();
	}

	public User saveFromCreate(final User user) {
		Assert.notNull(user, "message.error.user.null");

		User result;

		// Check unlogged user
		Assert.isTrue(!this.actorService.checkLogin(), "message.error.user.login");

		// Check Authority
		final boolean isUser;
		isUser = this.actorService.checkAuthority(user, "USER");
		Assert.isTrue(isUser, "message.error.user.authority.wrong");

		// Check repeated username
		UserAccount possibleRepeated = null;
		possibleRepeated = this.userAccountService.findByUsername(user.getUserAccount().getUsername());
		Assert.isNull(possibleRepeated, "message.error.user.username.repeated");

		result = this.save(user);

		// Add system folders
		final Collection<Folder> systemFolders = this.folderService.initializeFolders(result);
		result.setFolders(systemFolders);

		result = this.save(result);

		return result;
	}

	public User saveFromEdit(final User user) {
		Assert.notNull(user, "message.error.user.null");

		final User result;
		User principal;

		// Check logged customer
		principal = this.findByPrincipal();
		Assert.isTrue(principal.getId() == user.getId(), "message.error.user.login");

		// Check Authority
		final boolean isUser;
		isUser = this.actorService.checkAuthority(user, "USER");
		Assert.isTrue(isUser, "message.error.user.authority.wrong");

		// Encoding password
		UserAccount userAccount;
		userAccount = user.getUserAccount();
		userAccount = this.userAccountService.modifyPassword(userAccount);
		user.setUserAccount(userAccount);

		result = this.save(user);

		return result;
	}

	public Collection<User> findAll() {
		Collection<User> result = null;
		result = this.userRepository.findAll();
		return result;
	}

	public User findOne(final int userId) {
		User result = null;
		result = this.userRepository.findOne(userId);
		return result;
	}

	// Other business methods -------------------------------------------------

	public User findByPrincipal() {
		User result = null;
		UserAccount userAccount = null;

		userAccount = LoginService.getPrincipal();
		result = this.userRepository.findByUserAccountId(userAccount.getId());

		Assert.notNull(result);

		return result;
	}

	// Dashboard services ------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 7.3.6

	public Double ratioOfUsersWhoHaveEverCreatedANewspaper() {
		Double result = null;
		result = this.userRepository.ratioOfUsersWhoHaveEverCreatedANewspaper();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 7.3.7

	public Double ratioOfUsersWhoHaveEverWrittenAnArticle() {
		Double result = null;
		result = this.userRepository.ratioOfUsersWhoHaveEverWrittenAnArticle();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 17.6.5

	public Double ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser() {
		Double result = null;
		result = this.userRepository.ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser();
		return result;
	}

}
