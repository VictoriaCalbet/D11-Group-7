
package services.forms;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import services.FolderService;
import services.MessageService;
import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.forms.MessageForm;

@Service
@Transactional
public class MessageFormService {

	// Supporting services ----------------------------------------------------

	@Autowired
	private MessageService	messageService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private FolderService	folderService;


	// Constructors -----------------------------------------------------------

	public MessageFormService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public MessageForm create() {
		MessageForm result;

		result = new MessageForm();
		result.setId(0);
		result.setFolderId(0);

		return result;
	}

	public MessageForm createFromMessage(final int messageId) {
		MessageForm result;
		Message message;

		final Actor principal = this.actorService.findByPrincipal();

		message = this.messageService.findOne(messageId);

		Assert.isTrue(message.getFolder().getActor().getId() == principal.getId(), "message.error.message.principal.owner");

		result = new MessageForm();
		result.setId(message.getId());
		result.setSubject(message.getSubject());
		result.setBody(message.getBody());
		result.setPriority(message.getPriority());
		result.setRecipientId(message.getRecipient().getId());
		result.setFolderId(message.getFolder().getId());

		return result;
	}
	public MessageForm createForBroadcast() {
		MessageForm result;

		result = new MessageForm();
		result.setId(0);
		result.setFolderId(0);
		result.setRecipientId(0);

		return result;
	}

	public void saveFromCreate(final MessageForm messageForm) {
		Assert.notNull(messageForm, "message.error.messageForm.null");

		Message messageForSender;
		Message messageForRecipient;

		final Actor principal = this.actorService.findByPrincipal();
		final Actor recipient = this.actorService.findOne(messageForm.getRecipientId());

		final Folder senderOutbox = this.folderService.findOneByActorIdAndFolderName(principal.getId(), "out box");
		final Folder recipientInbox = this.folderService.findOneByActorIdAndFolderName(recipient.getId(), "in box");

		// Message for the sender
		messageForSender = this.messageService.create();
		messageForSender.setSubject(messageForm.getSubject());
		messageForSender.setBody(messageForm.getBody());
		messageForSender.setPriority(messageForm.getPriority());
		messageForSender.setMoment(new Date(System.currentTimeMillis() - 1));

		messageForSender.setSender(principal);
		messageForSender.setRecipient(recipient);
		messageForSender.setFolder(senderOutbox);

		messageForSender = this.messageService.saveFromCreate(messageForSender);

		// Message for the recipient
		messageForRecipient = this.messageService.create();
		messageForRecipient.setSubject(messageForm.getSubject());
		messageForRecipient.setBody(messageForm.getBody());
		messageForRecipient.setPriority(messageForm.getPriority());
		messageForRecipient.setMoment(new Date(System.currentTimeMillis() - 1));

		messageForRecipient.setSender(principal);
		messageForRecipient.setRecipient(recipient);
		messageForRecipient.setFolder(recipientInbox);

		messageForRecipient = this.messageService.saveFromCreate(messageForRecipient);

	}

	public void saveFromEdit(final MessageForm messageForm) {
		Assert.notNull(messageForm, "message.error.messageForm.null");

		final Message message = this.messageService.findOne(messageForm.getId());
		final Folder folder = this.folderService.findOne(messageForm.getFolderId());
		final Actor principal = this.actorService.findByPrincipal();

		Assert.isTrue(message.getFolder().getActor().getId() == folder.getActor().getId(), "message.error.message.folder.principal.owner");
		Assert.isTrue(principal.getFolders().contains(folder), "message.error.message.folder.principal.owner");

		message.setFolder(folder);

		this.messageService.save(message);
	}

	public void saveFromBroadcast(final MessageForm messageForm) {
		Assert.notNull(messageForm, "message.error.messageForm.null");

		final Actor principal = this.actorService.findByPrincipal();
		final Collection<Actor> allActors = this.actorService.findAll();
		allActors.remove(principal);

		for (final Actor recipient : allActors) {
			final Message message = this.messageService.create();
			message.setSubject(messageForm.getSubject());
			message.setBody(messageForm.getBody());
			message.setPriority(messageForm.getPriority());
			message.setMoment(new Date(System.currentTimeMillis() - 1));

			message.setSender(principal);
			message.setRecipient(recipient);

			final Folder recipientNotificationBox = this.folderService.findOneByActorIdAndFolderName(recipient.getId(), "notification box");
			message.setFolder(recipientNotificationBox);

			this.messageService.saveFromCreate(message);
		}

	}

	public void delete(final MessageForm messageForm) {
		Assert.notNull(messageForm, "message.error.messageForm.null");

		final Message message = this.messageService.findOne(messageForm.getId());
		final Folder folder = this.folderService.findOne(messageForm.getFolderId());
		final Actor principal = this.actorService.findByPrincipal();

		Assert.isTrue(principal.getFolders().contains(folder), "message.error.message.folder.principal.owner");

		this.messageService.delete(message);

	}
}
