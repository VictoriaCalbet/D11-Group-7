
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {

	@Query("select f from Folder f where f.actor.id = ?1 and f.parent is null")
	Collection<Folder> findAllRootFoldersByActor(int actorId);

	@Query("select f from Folder f where f.parent.id = ?1")
	Collection<Folder> findAllChilderFoldersByFolderId(int folderId);

	@Query("select f from Folder f where f.id != ?1 and f.actor.id = ?2")
	Collection<Folder> findAllPossibleParentFolders(int folderId, int actorId);

	@Query("select f from Folder f where f.name like %?2% and f.actor.id = ?1")
	Folder findOneByActorIdAndFolderName(int actorId, String folderName);
}
