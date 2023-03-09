package rs.diplomski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import rs.diplomski.controller.dto.ReservationDTO;
import rs.diplomski.model.Reservation;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ReservationMapper {
	
	ReservationDTO entityToDto(Reservation entity);

	Reservation dtoToEntity(ReservationDTO dto);

	Reservation updateEntityFromDto(ReservationDTO dto, @MappingTarget Reservation entity);

	List<ReservationDTO> entitiesToDtos(List<Reservation> entities);
}
