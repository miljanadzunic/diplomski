package rs.diplomski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.diplomski.controller.dto.StatusDTO;
import rs.diplomski.mapper.StatusMapper;
import rs.diplomski.model.Status;
import rs.diplomski.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private StatusMapper statusMapper;
	
	@Transactional(readOnly = true)
	public List<StatusDTO> getAllStatuses() {
		List<Status> res = statusRepository.findAll();
		List<StatusDTO> resDTOs = statusMapper.entitiesToDtos(res);
		return resDTOs;
	}
	
	@Transactional(readOnly = true)
	public List<StatusDTO> getAllByTable(String table) {
		List<Status> res = statusRepository.getStatusByTable(table);
		List<StatusDTO> resDTOs = statusMapper.entitiesToDtos(res);
		return resDTOs;
	}
}
