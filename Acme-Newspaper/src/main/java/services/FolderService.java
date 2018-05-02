
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class FolderService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private FolderRepository	folderRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private MessageService		messageService;


	// Constructors -----------------------------------------------------------

	public FolderService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Folder create() {
		Folder result;

		result = new Folder();
		result.setSystem(false); // Must be changed if we are creating an actor.
		result.setMessages(new HashSet<Message>());

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Folder save(final Folder folder) {
		Assert.notNull(folder, "message.error.folder.null");
		Folder result;
		result = this.folderRepository.save(folder);
		return result;
	}

	public void flush() {
		this.folderRepository.flush();
	}

	public Folder saveFromCreate(final Folder folder) {
		Assert.notNull(folder, "message.error.folder.null");
		Assert.isTrue(!this.checkSystemFolderName(folder), "message.error.folder.name.system");
		Assert.isTrue(!this.checkRepeatedFolderName(folder), "message.error.folder.name.repeated");

		Folder result;
		Actor principal;

		principal = this.actorService.findByPrincipal();
		folder.setActor(principal);

		result = this.save(folder);

		principal.getFolders().add(result);
		this.actorService.save(principal);

		return result;
	}

	public Folder saveFromEdit(final Folder folder) {
		Assert.notNull(folder, "message.error.folder.null");
		Assert.isTrue(!this.checkSystemFolderName(folder), "message.error.folder.name.system");
		Assert.isTrue(!this.checkRepeatedFolderName(folder), "message.error.folder.name.repeated");

		Folder result;
		final Folder folderInDB;
		Actor principal;

		folderInDB = this.folderRepository.findOne(folder.getId());
		principal = this.actorService.findByPrincipal();

		Assert.isTrue(folder.getActor().getId() == folderInDB.getActor().getId(), "message.error.folder.principal.owner");
		Assert.isTrue(folder.getActor().getId() == principal.getId(), "message.error.folder.principal.owner");
		Assert.isTrue(!folderInDB.getSystem(), "message.error.folder.name.system");

		result = this.save(folder);

		return result;
	}

	public void delete(final Folder folder) {
		Assert.notNull(folder, "message.error.folder.null");

		Folder folderInDB;
		final Actor principal;

		folderInDB = this.folderRepository.findOne(folder.getId());
		principal = this.actorService.findByPrincipal();

		Assert.isTrue(folder.getActor().getId() == folderInDB.getActor().getId(), "message.error.folder.principal.owner");
		Assert.isTrue(folder.getActor().getId() == principal.getId(), "message.error.folder.principal.owner");
		Assert.isTrue(!folderInDB.getSystem(), "message.error.folder.name.system");

		// Remove messages. TODO
		for (final Message message : folder.getMessages())
			this.messageService.delete(message);

		// Remove folder from Actor Collection.
		principal.getFolders().remove(folder);
		this.actorService.save(principal);

		this.folderRepository.delete(folder);
	}

	public Collection<Folder> findAll() {
		Collection<Folder> result = null;
		result = this.folderRepository.findAll();
		return result;
	}

	public Folder findOne(final int folderId) {
		Folder result = null;
		result = this.folderRepository.findOne(folderId);
		return result;
	}

	public Collection<Folder> findAllRootFoldersByActor() {
		Collection<Folder> result = null;
		final Actor principal = this.actorService.findByPrincipal();

		result = this.folderRepository.findAllRootFoldersByActor(principal.getId());

		return result;
	}

	public Collection<Folder> findAllChilderFoldersByFolderId(final int folderId) {
		Collection<Folder> result = null;
		result = this.folderRepository.findAllChilderFoldersByFolderId(folderId);
		return result;
	}

	public Collection<Folder> findAllPossibleParentFolders(final int folderId) {
		Collection<Folder> result = null;
		final Actor principal = this.actorService.findByPrincipal();

		result = this.folderRepository.findAllPossibleParentFolders(folderId, principal.getId());

		return result;
	}

	public Folder findOneByActorIdAndFolderName(final int actorId, final String folderName) {
		Assert.notNull(folderName);

		Folder result = null;
		result = this.folderRepository.findOneByActorIdAndFolderName(actorId, folderName);

		return result;
	}

	// Other business methods -------------------------------------------------

	public Collection<Folder> initializeFolders(final Actor actor) {
		final Collection<Folder> actorFolders = new HashSet<Folder>();
		final Collection<String> systemFolderNames = new HashSet<String>();

		systemFolderNames.add("in box");
		systemFolderNames.add("out box");
		systemFolderNames.add("notification box");
		systemFolderNames.add("trash box");
		systemFolderNames.add("spam box");

		for (final String name : systemFolderNames) {
			Folder unsavedFolder;
			Folder savedFolder;
			unsavedFolder = this.create();
			unsavedFolder.setName(name);
			unsavedFolder.setSystem(true);
			unsavedFolder.setActor(actor);
			savedFolder = this.save(unsavedFolder);
			actorFolders.add(savedFolder);
		}

		return actorFolders;
	}

	private boolean checkSystemFolderName(final Folder folder) {
		Assert.notNull(folder, "message.error.folder.null");

		boolean result = false;
		final Collection<String> systemFolderNames = new HashSet<String>();

		systemFolderNames.add("in box");
		systemFolderNames.add("out box");
		systemFolderNames.add("notification box");
		systemFolderNames.add("trash box");
		systemFolderNames.add("spam box");

		for (final String name : systemFolderNames)
			if (folder.getName().equals(name)) {
				result = true;
				break;
			}

		return result;
	}

	private boolean checkRepeatedFolderName(final Folder folder) {
		Assert.notNull(folder, "message.error.folder.null");

		boolean result = false;
		Actor principal;
		Collection<Folder> principalFolders;

		principal = this.actorService.findByPrincipal();
		principalFolders = principal.getFolders();

		for (final Folder f : principalFolders)
			if (f.getId() != folder.getId() && f.getName().equals(folder.getName()))
				result = true;

		return result;
	}
}
