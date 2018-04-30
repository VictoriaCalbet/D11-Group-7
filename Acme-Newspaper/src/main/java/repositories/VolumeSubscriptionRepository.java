
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.Volume;
import domain.VolumeSubscription;

@Repository
public interface VolumeSubscriptionRepository extends JpaRepository<VolumeSubscription, Integer> {

	@Query("select count(vs)/(select count(ns) from NewspaperSubscription ns) from VolumeSubscription vs")
	Double ratioOfVolumeSubscriptionsVsNewspaperSubscription();

	@Query("select case when (count(subs) = 1) then true else false end from VolumeSubscription subs where subs.customer = ?1 and subs.volume = ?2")
	boolean isThisCustomerSubscribeOnThisVolume(Customer customer, Volume volume);
}
