
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Folder;

@Service
@Transactional
public class FolderService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private FolderRepository	folderRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public FolderService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Folder create() {
		return null;
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
		return null;
	}

	public Folder saveFromEdit(final Folder folder) {
		return null;
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

	// Other business methods -------------------------------------------------
}
