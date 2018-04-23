
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chirp;

@Repository
public interface ChirpRepository extends JpaRepository<Chirp, Integer> {

	@Query("select c from Chirp c where c.user.id = ?1")
	public Collection<Chirp> listAllChirpsByUser(int id);

	@Query("select c from Chirp c, User u where u.id = ?1 and c.user in elements(u.followed)")
	public Collection<Chirp> listAllChirpsByFollowedUsers(int id);

	@Query("select c from Chirp c where (c.title like %?1% or c.description like %?1%)")
	Collection<Chirp> getTabooChirps(String tabooWord);

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 17.6.4
	@Query("select avg(usr.chirps.size) from User usr")
	public Double avgNoChirpsPerUser();

	@Query("select sqrt(sum(usr.chirps.size * usr.chirps.size) / count(usr.chirps.size) - (avg(usr.chirps.size) * avg(usr.chirps.size))) from User usr")
	public Double stdNoChirpsPerUser();

}
