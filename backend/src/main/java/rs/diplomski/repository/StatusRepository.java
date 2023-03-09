package rs.diplomski.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.diplomski.model.Status;
import rs.diplomski.sys.repository.CustomRepository;

@Repository
public interface StatusRepository extends CustomRepository<Status, Long> {
	
	@Query(value = "SELECT * FROM status sta WHERE sta.sta_code = :staCode AND sta.sta_table = :staTable", nativeQuery = true)
	Status getStatusByCodeAndTable(@Param("staCode") String staCode, @Param("staTable") String staTable);
	
	@Query(value = "SELECT * FROM status sta WHERE sta.sta_table = :staTable", nativeQuery = true)
	List<Status> getStatusByTable(@Param("staTable") String staTable);
}
