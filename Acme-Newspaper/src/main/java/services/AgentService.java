
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AgentRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Advertisement;
import domain.Agent;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class AgentService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private AgentRepository		agentRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private FolderService		folderService;


	// Constructors -----------------------------------------------------------

	public AgentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Agent create() {
		Agent result;
		UserAccount userAccount;

		result = new Agent();

		result.setEmailAddresses(new HashSet<String>());
		result.setPhoneNumbers(new HashSet<String>());
		result.setPostalAddresses(new HashSet<String>());

		result.setAdvertisements(new HashSet<Advertisement>());

		result.setFolders(new HashSet<Folder>());
		result.setMessagesSent(new HashSet<Message>());
		result.setMessagesReceived(new HashSet<Message>());

		userAccount = this.userAccountService.create("AGENT");
		result.setUserAccount(userAccount);

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Agent save(final Agent agent) {
		Assert.notNull(agent, "message.error.agent.null");
		Agent result;
		result = this.agentRepository.save(agent);
		return result;
	}

	public void flush() {
		this.agentRepository.flush();
	}

	public Agent saveFromCreate(final Agent agent) {
		Assert.notNull(agent, "message.error.agent.null");

		Agent result;

		// Check unlogged user
		Assert.isTrue(!this.actorService.checkLogin(), "message.error.agent.login");

		// Check Authority
		final boolean isAgent;
		isAgent = this.actorService.checkAuthority(agent, "AGENT");
		Assert.isTrue(isAgent, "message.error.agent.authority.wrong");

		// Check repeated username
		UserAccount possibleRepeated = null;
		possibleRepeated = this.userAccountService.findByUsername(agent.getUserAccount().getUsername());
		Assert.isNull(possibleRepeated, "message.error.agent.username.repeated");

		result = this.save(agent);

		// Add system folders
		final Collection<Folder> systemFolders = this.folderService.initializeFolders(result);
		result.setFolders(systemFolders);

		result = this.save(result);

		return result;
	}

	public Agent saveFromEdit(final Agent agent) {
		Assert.notNull(agent, "message.error.agent.null");

		final Agent result;
		Agent principal;

		// Check logged customer
		principal = this.findByPrincipal();
		Assert.isTrue(principal.getId() == agent.getId(), "message.error.agent.login");

		// Check Authority
		final boolean isAgent;
		isAgent = this.actorService.checkAuthority(agent, "AGENT");
		Assert.isTrue(isAgent, "message.error.agent.authority.wrong");

		// Encoding password
		UserAccount userAccount;
		userAccount = agent.getUserAccount();
		userAccount = this.userAccountService.modifyPassword(userAccount);
		agent.setUserAccount(userAccount);

		result = this.save(agent);

		return result;
	}

	public Collection<Agent> findAll() {
		Collection<Agent> result = null;
		result = this.agentRepository.findAll();
		return result;
	}

	public Agent findOne(final int agentId) {
		Agent result = null;
		result = this.agentRepository.findOne(agentId);
		return result;
	}

	// Other business methods -------------------------------------------------

	public Agent findByPrincipal() {
		Agent result = null;
		UserAccount userAccount = null;

		userAccount = LoginService.getPrincipal();
		result = this.agentRepository.findByUserAccountId(userAccount.getId());

		Assert.notNull(result);

		return result;
	}
}
