
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Message;

@Service
@Transactional
public class MessageService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private MessageRepository	messageRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public MessageService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Message create() {
		return null;
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
		return null;
	}

	public Message saveFromEdit(final Message message) {
		return null;
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
