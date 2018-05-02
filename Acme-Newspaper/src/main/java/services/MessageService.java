
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class MessageService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private MessageRepository	messageRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private FolderService		folderService;


	// Constructors -----------------------------------------------------------

	public MessageService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Message create() {
		Message result;

		result = new Message();

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Message save(final Message message) {
		Assert.notNull(message, "message.error.message.null");
		Message result;
		result = this.messageRepository.save(message);
		return result;
	}

	public void flush() {
		this.messageRepository.flush();
	}

	public Message saveFromCreate(final Message message) {
		Assert.notNull(message, "message.error.message.null");

		Message result;

		final Actor sender = message.getSender();
		final Actor recipient = message.getRecipient();
		final Folder folder = message.getFolder();

		result = this.save(message);

		sender.getMessagesSent().add(result);
		this.actorService.save(sender);

		recipient.getMessagesReceived().add(result);
		this.actorService.save(recipient);

		folder.getMessages().add(result);
		this.folderService.save(folder);

		return result;
	}

	public void delete(final Message message) {
		Assert.notNull(message, "message.error.message.null");

		final Folder folder = message.getFolder();
		final Actor principal = this.actorService.findByPrincipal();

		Assert.isTrue(principal.getFolders().contains(folder), "message.error.message.folder.principal.owner");

		if (folder.getName().equals("trash box"))
			this.messageRepository.delete(message);
		else {
			final Folder trashbox = this.folderService.findOneByActorIdAndFolderName(principal.getId(), "trash box");
			message.setFolder(trashbox);
			this.save(message);
		}
	}

	public Collection<Message> findAll() {
		Collection<Message> result = null;
		result = this.messageRepository.findAll();
		return result;
	}

	public Message findOne(final int messageId) {
		Message result = null;
		result = this.messageRepository.findOne(messageId);
		return result;
	}

	// Other business methods -------------------------------------------------
}
