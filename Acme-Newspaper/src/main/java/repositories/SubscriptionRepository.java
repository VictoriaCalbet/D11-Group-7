
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 24.1.4
	@Query("select count(distinct s.customer)*1.0/(select count(n1) * (select count(c) from Customer c) from Newspaper n1 where n1.isPrivate is true) from Subscription s")
	Double ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers();

	@Query("select case when (count(subs.newspaper) > 0) then true else false end from Customer c join c.subscriptions subs where c.id = ?1 and subs.newspaper.id = ?2")
	boolean thisCustomerCanSeeThisNewspaper(int customerId, int newspaperId);

	@Query("select case when (count(subs) > 0) then true else false end from Subscription subs where subs.customer.id = ?1 and subs.newspaper.id = ?2")
	boolean isThisCustomerSubscribeOnThisNewspaper(int customerId, int newspaperId);

}
