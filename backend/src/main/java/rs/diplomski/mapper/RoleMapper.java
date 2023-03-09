package rs.diplomski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import rs.diplomski.controller.dto.RoleDTO;
import rs.diplomski.model.Role;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {

	RoleDTO entityToDto(Role entity);

	Role dtoToEntity(RoleDTO dto);

	Role updateEntityFromDto(RoleDTO dto, @MappingTarget Role entity);

	List<RoleDTO> entitiesToDtos(List<Role> entities);
}
