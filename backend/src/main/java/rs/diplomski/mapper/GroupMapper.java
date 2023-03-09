package rs.diplomski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import rs.diplomski.controller.dto.GroupDTO;
import rs.diplomski.model.Group;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GroupMapper {
	
	GroupDTO entityToDto(Group entity);

	Group dtoToEntity(GroupDTO dto);

	Group updateEntityFromDto(GroupDTO dto, @MappingTarget Group entity);

	List<GroupDTO> entitiesToDtos(List<Group> entities);
}
