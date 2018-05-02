
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.VolumeSubscription;

@Repository
public interface VolumeSubscriptionRepository extends JpaRepository<VolumeSubscription, Integer> {

	@Query("select count(vs)/(select count(ns) from NewspaperSubscription ns) from VolumeSubscription vs")
	Double ratioOfVolumeSubscriptionsVsNewspaperSubscription();

	@Query("select case when (count(subs) = 1) then true else false end from VolumeSubscription subs where subs.customer.id = ?1 and subs.volume.id = ?2")
	boolean isThisCustomerSubscribeOnThisVolume(int customerId, int volumeId);

	@Query("select case when (count(news) > 0) then true else false end from VolumeSubscription vSubs join vSubs.volume vl join vl.newspapers news where vSubs.customer.id = ?1 and news.isPrivate is true and news.publicationDate is not null and news.id = ?2")
	boolean thisCustomerCanSeeThisNewspaper(int customerId, int newspaperId);

}
