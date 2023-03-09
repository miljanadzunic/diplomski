package rs.diplomski.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.diplomski.controller.dto.RoleDTO;
import rs.diplomski.mapper.RoleMapper;
import rs.diplomski.model.Role;
import rs.diplomski.repository.RoleRepository;


@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Transactional(readOnly = true)
	public List<RoleDTO> getAllAccounts() {
		List<Role> res = roleRepository.findAll();
		List<RoleDTO> resDTOs = roleMapper.entitiesToDtos(res);
		return resDTOs;
	}
}
