
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.Newspaper;
import domain.NewspaperSubscription;

@Repository
public interface NewspaperSubscriptionRepository extends JpaRepository<NewspaperSubscription, Integer> {

	@Query("select subs.newspaper from Customer c join c.newspaperSubscriptions subs where c.id = ?1 and subs.newspaper.id = ?2")
	NewspaperSubscription findNewspaperSubscriptionByCustomerIdAndNewspaperId(int customerId, int newspaperId);

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 24.1.4
	@Query("select count(distinct s.customer)*1.0/(select count(n1) * (select count(c) from Customer c) from Newspaper n1 where n1.isPrivate is true) from NewspaperSubscription s")
	Double ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers();

	@Query("select case when (count(subs.newspaper) > 0) then true else false end from Customer c join c.newspaperSubscriptions subs where c.id = ?1 and subs.newspaper.id = ?2")
	boolean thisCustomerCanSeeThisNewspaper(int customerId, int newspaperId);

	@Query("select case when (count(subs) = 1) then true else false end from NewspaperSubscription subs where subs.customer = ?1 and subs.newspaper = ?2")
	boolean isThisCustomerSubscribeOnThisNewspaper(Customer customer, Newspaper newspaper);

}
