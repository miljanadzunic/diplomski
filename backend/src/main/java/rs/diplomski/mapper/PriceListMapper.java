package rs.diplomski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import rs.diplomski.controller.dto.PriceListDTO;
import rs.diplomski.model.PriceList;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PriceListMapper {

	PriceListDTO entityToDto(PriceList entity);

	PriceList dtoToEntity(PriceListDTO dto);

	PriceList updateEntityFromDto(PriceListDTO dto, @MappingTarget PriceList entity);

	List<PriceListDTO> entitiesToDtos(List<PriceList> entities);

}
