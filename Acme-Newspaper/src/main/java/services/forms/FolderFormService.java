
package services.forms;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import services.ActorService;
import services.FolderService;
import domain.Actor;
import domain.Folder;
import domain.forms.FolderForm;

@Service
@Transactional
public class FolderFormService {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public FolderFormService() {
		super();
	}

	public FolderForm create() {
		FolderForm result;

		result = new FolderForm();
		result.setId(0);

		return result;
	}

	public FolderForm createFromFolder(final int folderId) {
		FolderForm result;
		Folder folder;

		folder = this.folderService.findOne(folderId);

		result = new FolderForm();
		result.setId(folderId);
		result.setName(folder.getName());
		result.setSystem(folder.getSystem());

		if (folder.getParent() != null)
			result.setParentId(folder.getParent().getId());

		return result;
	}

	public Folder saveFromCreate(final FolderForm folderForm) {
		Assert.notNull(folderForm, "message.error.folderForm.null");

		Folder result;
		Folder folder;
		Folder parent;

		folder = this.folderService.create();
		parent = this.folderService.findOne(folderForm.getParentId());

		if (parent != null)
			Assert.isTrue(parent.getActor().getId() == this.actorService.findByPrincipal().getId(), "message.error.folderForm.parent.owner");

		folder.setName(folderForm.getName());
		folder.setParent(parent);

		result = this.folderService.saveFromCreate(folder);

		return result;

	}

	public Folder saveFromEdit(final FolderForm folderForm) {
		Assert.notNull(folderForm, "message.error.folderForm.null");

		Folder result;
		Folder folder;
		Folder parent;

		folder = this.folderService.findOne(folderForm.getId());
		parent = this.folderService.findOne(folderForm.getParentId());

		if (parent != null)
			Assert.isTrue(parent.getActor().getId() == this.actorService.findByPrincipal().getId(), "message.error.folderForm.parent.owner");

		Assert.isTrue(folder.getActor().getId() == this.actorService.findByPrincipal().getId(), "message.error.folder.principal.owner");

		folder.setName(folderForm.getName());
		folder.setParent(parent);

		result = this.folderService.saveFromEdit(folder);

		return result;

	}

	public void delete(final FolderForm folderForm) {
		Assert.notNull(folderForm, "message.error.folderForm.null");

		final Folder folder = this.folderService.findOne(folderForm.getId());
		final Actor principal = this.actorService.findByPrincipal();

		Assert.isTrue(principal.getFolders().contains(folder), "message.error.folder.principal.owner");

		this.folderService.delete(folder);

	}
}
