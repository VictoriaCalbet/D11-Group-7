
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Actor;
import domain.Folder;
import domain.Message;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MessageServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private MessageService	messageService;

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ActorService	actorService;


	// Tests ------------------------------------------------------------------

	// The following are fictitious test cases that are intended to check that 
	// JUnit works well in this project.  Just righ-click this class and run 
	// it using JUnit.

	/**
	 * Acme-Newspaper 2.0: Requirement 13.1:
	 * 
	 * An actor who is authenticated must be able to:
	 * Exchange messages with other actors and manage them, which includes deleting and moving them from one folder to another folder.
	 * 
	 * Positive test1: Send a message folder creation
	 * Negative test2: Blank subject
	 * Negative test3: Blank body
	 */
	@Test
	public void testSaveFromCreateMessage() {
		final Object[][] testingData = {
			{
				"testSubject1", "testBody1", "NEUTRAL", "admin", "user1", null
			}, {
				"", "testBody2", "NEUTRAL", "admin", "user1", ConstraintViolationException.class
			}, {
				"testSubject3", "", "NEUTRAL", "admin", "user1", ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromCreateMessageTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);
	}

	protected void testSaveFromCreateMessageTemplate(final String subject, final String body, final String priority, final String senderUserName, final String recipientUserName, final Class<?> expectedException) {

		Class<?> caught = null;

		try {
			this.authenticate(senderUserName);
			final Actor sender = this.actorService.findByPrincipal();
			final Actor recipient = this.actorService.findOne(this.getEntityId(recipientUserName));
			final Folder outBoxSender = this.folderService.findOneByActorIdAndFolderName(sender.getId(), "out box");

			final Message message = this.messageService.create();
			message.setSubject(subject);
			message.setBody(body);
			message.setPriority(priority);
			message.setSender(sender);
			message.setRecipient(recipient);
			message.setFolder(outBoxSender);

			this.messageService.saveFromCreate(message);
			this.messageService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}

	/**
	 * Acme-Newspaper 2.0: Requirement 13.1:
	 * 
	 * An actor who is authenticated must be able to:
	 * Exchange messages with other actors and manage them, which includes deleting and moving them from one folder to another folder.
	 * 
	 * Positive test1: Delete a message
	 */
	@Test
	public void testDeleteMessage() {
		final Object[][] testingData = {
			{
				"testSubject1", "testBody1", "NEUTRAL", "admin", "user1", null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveFromCreateMessageTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);
	}

	protected void testDeleteMessageTemplate(final String subject, final String body, final String priority, final String senderUserName, final String recipientUserName, final Class<?> expectedException) {

		Class<?> caught = null;

		try {
			this.authenticate(senderUserName);
			final Actor sender = this.actorService.findByPrincipal();
			final Actor recipient = this.actorService.findOne(this.getEntityId(recipientUserName));
			final Folder outBoxSender = this.folderService.findOneByActorIdAndFolderName(sender.getId(), "out box");

			final Message message = this.messageService.create();
			message.setSubject(subject);
			message.setBody(body);
			message.setPriority(priority);
			message.setSender(sender);
			message.setRecipient(recipient);
			message.setFolder(outBoxSender);

			final Message result = this.messageService.saveFromCreate(message);

			this.messageService.delete(result);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);

	}
}
