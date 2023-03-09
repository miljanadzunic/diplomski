package rs.diplomski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import rs.diplomski.controller.dto.AccountDTO;
import rs.diplomski.model.Account;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountMapper {

	AccountDTO entityToDto(Account entity);

	Account dtoToEntity(AccountDTO dto);

	Account updateEntityFromDto(AccountDTO dto, @MappingTarget Account entity);

	List<AccountDTO> entitiesToDtos(List<Account> entities);

}
