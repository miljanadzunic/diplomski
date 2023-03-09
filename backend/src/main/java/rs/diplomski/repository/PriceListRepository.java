package rs.diplomski.repository;

import org.springframework.stereotype.Repository;

import rs.diplomski.model.PriceList;
import rs.diplomski.sys.repository.CustomRepository;

@Repository
public interface PriceListRepository extends CustomRepository<PriceList, Long> {

}
