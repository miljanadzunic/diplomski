package rs.diplomski.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.diplomski.controller.dto.StudentCoachDTO;
import rs.diplomski.mapper.StudentCoachMapper;
import rs.diplomski.model.StudentCoach;
import rs.diplomski.repository.StatusRepository;
import rs.diplomski.repository.StudentCoachRepository;
import rs.diplomski.sys.exception.CustomException;

@Service
public class StudentCoachService {
	
	@Autowired
	private StudentCoachRepository studentCoachRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private StudentCoachMapper studentCoachMapper;
	
	@Transactional(rollbackFor = Exception.class)
	public StudentCoachDTO create(StudentCoachDTO inputDTO) throws Exception {
		StudentCoach input = this.studentCoachMapper.dtoToEntity(inputDTO);
		
		StudentCoach check = this.studentCoachRepository.findByCoachAndStudent(input.getSctCoach().getAccId(), input.getSctStudent().getAccId());
		
		if(check != null) {
			throw new CustomException("ERROR_REQUEST_ALREADY_SENT");
		}
		
		input.setSctStatus(statusRepository.getStatusByCodeAndTable("STA_NOVI", "student_coach"));
		StudentCoach newRes = this.studentCoachRepository.save(input);
		
		StudentCoachDTO newResDTO = this.studentCoachMapper.entityToDto(newRes);
		return newResDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void delete (Long id) throws Exception {
		Optional<StudentCoach> opt = studentCoachRepository.findById(id);
		opt.orElseThrow(() -> new CustomException("NOT_FOUND"));
		studentCoachRepository.deleteById(id);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void cancel(Long id) throws Exception {
		Optional<StudentCoach> opt = studentCoachRepository.findById(id);
		StudentCoach res = opt.orElseThrow(() -> new CustomException("NOT_FOUND"));
		
		if(res.getSctGroup() != null) {
			throw new CustomException("ERROR_STUDENT_HAS_GROUP");
		}
		
		res.setSctStatus(statusRepository.getStatusByCodeAndTable("STA_ODBIJEN", "student_coach"));
		studentCoachRepository.save(res);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void accept(Long id) throws Exception {
		Optional<StudentCoach> opt = studentCoachRepository.findById(id);
		StudentCoach res = opt.orElseThrow(() -> new CustomException("NOT_FOUND"));
		
		res.setSctStatus(statusRepository.getStatusByCodeAndTable("STA_PRIHVACEN", "student_coach"));
		studentCoachRepository.save(res);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<StudentCoachDTO> getAllByCoachId(Long coachId){
		List<StudentCoach> list = studentCoachRepository.getAllByCoachId(coachId);
		List<StudentCoachDTO> listDTO = studentCoachMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public boolean exists(Long id) {
		boolean exist = studentCoachRepository.existsById(id);
		return exist;
	}
	
	@Transactional(readOnly = true)
	public boolean findByCoachAndStudent(Long coachId, Long studentId) {
		StudentCoach res = studentCoachRepository.findByCoachAndStudent(coachId, studentId);
		if(res == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<StudentCoachDTO> getAllByStudentIdAccepted(Long studentId){
		List<StudentCoach> list = studentCoachRepository.getAllByStudentIdAccepted(studentId);
		List<StudentCoachDTO> listDTO = studentCoachMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<StudentCoachDTO> getAllByStudentIdNew(Long studentId){
		List<StudentCoach> list = studentCoachRepository.getAllByStudentIdNew(studentId);
		List<StudentCoachDTO> listDTO = studentCoachMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<StudentCoachDTO> getAllByStudent(Long studentId){
		List<StudentCoach> list = studentCoachRepository.getAllByStudent(studentId);
		List<StudentCoachDTO> listDTO = studentCoachMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<StudentCoachDTO> getByCoachAccepted(Long coachId){
		List<StudentCoach> list = studentCoachRepository.getByCoachAccepted(coachId);
		List<StudentCoachDTO> listDTO = studentCoachMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<StudentCoachDTO> getAllByGroupId(Long groupId){
		List<StudentCoach> list = studentCoachRepository.getByGroupId(groupId);
		List<StudentCoachDTO> listDTO = studentCoachMapper.entitiesToDtos(list);
		return listDTO;
	}
}
