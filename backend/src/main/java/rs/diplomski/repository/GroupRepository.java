package rs.diplomski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.diplomski.model.Group;
import rs.diplomski.sys.repository.CustomRepository;

@Repository
public interface GroupRepository extends CustomRepository<Group, Long>{
	
	@Query(value = "SELECT * FROM all_groups grp"
			+ " WHERE grp.grp_coach_id = :coachId", nativeQuery = true)
	List<Group> getGroupsByCoachId(@Param("coachId") Long coachId);
}
