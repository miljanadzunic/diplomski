package rs.diplomski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import rs.diplomski.controller.dto.StudentCoachDTO;
import rs.diplomski.model.StudentCoach;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudentCoachMapper {
	
	StudentCoachDTO entityToDto(StudentCoach entity);

	StudentCoach dtoToEntity(StudentCoachDTO dto);

	StudentCoach updateEntityFromDto(StudentCoachDTO dto, @MappingTarget StudentCoach entity);

	List<StudentCoachDTO> entitiesToDtos(List<StudentCoach> entities);
}
