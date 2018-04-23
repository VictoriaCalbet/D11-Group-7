
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userAccount.id = ?1")
	public User findByUserAccountId(int id);

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 7.3.6
	@Query("select count(u1)*1.0/(select count(u2) from User u2) from User u1 where u1.newspapers is not empty")
	public Double ratioOfUsersWhoHaveEverCreatedANewspaper();

	// Acme-Newspaper 1.0 - Requisito 7.3.7
	@Query("select count(u1)*1.0/(select count(u2) from User u2) from User u1 where u1.articles is not empty")
	public Double ratioOfUsersWhoHaveEverWrittenAnArticle();

	// Acme-Newspaper 1.0 - Requisito 17.6.5
	@Query("select count(u1)*1.0/(select count(u2) from User u2) from User u1 where u1.chirps.size >= (select 0.75*avg(u3.chirps.size) from User u3)")
	public Double ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser();

}
