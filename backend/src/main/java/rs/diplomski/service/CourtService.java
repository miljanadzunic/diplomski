package rs.diplomski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.diplomski.controller.dto.CourtDTO;
import rs.diplomski.mapper.CourtMapper;
import rs.diplomski.model.Court;
import rs.diplomski.repository.CourtRepository;
import rs.diplomski.sys.exception.CustomException;

@Service
public class CourtService {

	@Autowired
	private CourtRepository courtRepository;

	@Autowired
	private CourtMapper courtMapper;

	@Transactional(readOnly = true)
	public List<CourtDTO> getAllCourts() {
		List<Court> res = courtRepository.findAll();
		List<CourtDTO> resDTOs = courtMapper.entitiesToDtos(res);
		return resDTOs;
	}

	@Transactional(rollbackFor = Exception.class)
	public CourtDTO create(CourtDTO inputDTO) throws Exception {
		if (inputDTO == null) {
			throw new CustomException("INPUT_NULL");
		}
		Court input = this.courtMapper.dtoToEntity(inputDTO);

		Court newRes = this.courtRepository.save(input);
		CourtDTO newResDTO = this.courtMapper.entityToDto(newRes);

		return newResDTO;
	}
}
