package rs.diplomski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import rs.diplomski.controller.dto.StatusDTO;
import rs.diplomski.model.Status;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StatusMapper {

	StatusDTO entityToDto(Status entity);

	Status dtoToEntity(StatusDTO dto);

	Status updateEntityFromDto(StatusDTO dto, @MappingTarget Status entity);

	List<StatusDTO> entitiesToDtos(List<Status> entities);
}
