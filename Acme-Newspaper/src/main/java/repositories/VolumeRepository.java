
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Volume;

@Repository
public interface VolumeRepository extends JpaRepository<Volume, Integer> {

	@Query("select vl from Volume vl where vl.id not in (select vs.volume.id from Customer c join c.volumeSubscriptions vs where c.id = ?1)")
	Collection<Volume> findAvailableVolumesByCustomerId(int customerId);

	@Query("select avg(v.newspapers.size) from Volume v")
	Double avgNewspaperPerVolume();

}
