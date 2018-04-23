
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AgentRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Agent;

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


	// Constructors -----------------------------------------------------------

	public AgentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Agent create() {
		return null;
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
		return null;
	}

	public Agent saveFromEdit(final Agent agent) {
		return null;
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
