package rs.diplomski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import rs.diplomski.controller.dto.CourtDTO;
import rs.diplomski.model.Court;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourtMapper {

	CourtDTO entityToDto(Court entity);

	Court dtoToEntity(CourtDTO dto);

	Court updateEntityFromDto(CourtDTO dto, @MappingTarget Court entity);

	List<CourtDTO> entitiesToDtos(List<Court> entities);

}
