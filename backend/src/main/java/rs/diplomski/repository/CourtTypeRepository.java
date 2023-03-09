package rs.diplomski.repository;

import org.springframework.stereotype.Repository;

import rs.diplomski.model.CourtType;
import rs.diplomski.sys.repository.CustomRepository;

@Repository
public interface CourtTypeRepository extends CustomRepository<CourtType, Long> {

}
