package dmit2015.repository;

import dmit2015.entity.Job;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository(dataStore = "oracle-jpa-hr-pu")
public interface RegionRepository extends CrudRepository<Job, String> {
}
