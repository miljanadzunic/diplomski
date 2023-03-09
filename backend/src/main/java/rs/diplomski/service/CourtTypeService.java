package rs.diplomski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.diplomski.controller.dto.CourtTypeDTO;
import rs.diplomski.mapper.CourtTypeMapper;
import rs.diplomski.model.CourtType;
import rs.diplomski.repository.CourtTypeRepository;

@Service
public class CourtTypeService {

	@Autowired
	private CourtTypeRepository courtTypeRepository;

	@Autowired
	private CourtTypeMapper courtTypeMapper;

	@Transactional(readOnly = true)
	public List<CourtTypeDTO> getAllCourtTypes() {
		List<CourtType> res = courtTypeRepository.findAll();
		List<CourtTypeDTO> resDTOs = courtTypeMapper.entitiesToDtos(res);
		return resDTOs;
	}
}
