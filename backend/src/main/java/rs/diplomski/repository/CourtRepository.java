package rs.diplomski.repository;

import org.springframework.stereotype.Repository;

import rs.diplomski.model.Court;
import rs.diplomski.sys.repository.CustomRepository;

@Repository
public interface CourtRepository extends CustomRepository<Court, Long>{

}
