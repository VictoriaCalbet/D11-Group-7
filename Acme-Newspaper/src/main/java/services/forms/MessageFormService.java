
package services.forms;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		return result;
	}

	public void reconstruct(final MessageForm messageForm) {
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
}
