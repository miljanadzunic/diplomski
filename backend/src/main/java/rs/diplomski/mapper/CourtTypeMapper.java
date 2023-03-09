package rs.diplomski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import rs.diplomski.controller.dto.CourtTypeDTO;
import rs.diplomski.model.CourtType;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourtTypeMapper {

	CourtTypeDTO entityToDto(CourtType entity);

	CourtType dtoToEntity(CourtTypeDTO dto);

	CourtType updateEntityFromDto(CourtTypeDTO dto, @MappingTarget CourtType entity);

	List<CourtTypeDTO> entitiesToDtos(List<CourtType> entities);
}
