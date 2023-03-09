package rs.diplomski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.diplomski.controller.dto.GroupDTO;
import rs.diplomski.mapper.GroupMapper;
import rs.diplomski.model.Group;
import rs.diplomski.model.Reservation;
import rs.diplomski.model.StudentCoach;
import rs.diplomski.repository.GroupRepository;
import rs.diplomski.repository.ReservationRespository;
import rs.diplomski.repository.StudentCoachRepository;
import rs.diplomski.sys.exception.CustomException;

@Service
public class GroupService {
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private StudentCoachRepository studentCoachRepository;
	
	@Autowired
	private ReservationRespository reservationRepository;
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Transactional(rollbackFor = Exception.class)
	public GroupDTO create(GroupDTO inputDTO, List<Long> students) throws Exception {
		Group input = this.groupMapper.dtoToEntity(inputDTO);
		Group newRes = this.groupRepository.save(input);
		
		if(newRes == null) {
			throw new CustomException("NOT_FOUND");
		}
		
		List<StudentCoach> reqsForSave = new ArrayList<StudentCoach>();
		for(int i = 0; i < students.size(); i++) {
			StudentCoach studentCoach = this.studentCoachRepository.findByCoachAndStudentAccepted(newRes.getGrpCoach().getAccId(), students.get(i));
			
			if(studentCoach == null) {
				throw new CustomException("ERROR_NOT_COACH");
			}
			
			studentCoach.setSctGroup(newRes);
			reqsForSave.add(studentCoach);
		}
		studentCoachRepository.saveAll(reqsForSave);
		
		GroupDTO newResDTO = this.groupMapper.entityToDto(newRes);
		return newResDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<GroupDTO> getGroupsByCoachId(Long coachId){
		List<Group> list = groupRepository.getGroupsByCoachId(coachId);
		System.out.println("----------------------------------------MILJANA DEBUG" + list);
		List<GroupDTO> listDTO = groupMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long grpId) throws Exception{
		Optional<Group> opt = groupRepository.findById(grpId);
		Group toDelete = opt.orElseThrow(() -> new CustomException("NOT_FOUND"));
		
		List<StudentCoach> studentCoachList = studentCoachRepository.getByGroupId(grpId);
		List<StudentCoach> studentCoachToSave = new ArrayList<StudentCoach>();
		for(int i = 0; i < studentCoachList.size(); i++) {
			studentCoachList.get(i).setSctGroup(null);
			studentCoachToSave.add(studentCoachList.get(i));
		}
		this.studentCoachRepository.saveAll(studentCoachToSave);
		
		List<Reservation> reservationList = this.reservationRepository.getReservationsByGroupId(grpId);
		this.reservationRepository.deleteAll(reservationList);
		
		this.groupRepository.delete(toDelete);

	}
	
	@Transactional(readOnly = true)
	public boolean exists(Long id) {
		boolean exist = groupRepository.existsById(id);
		return exist;
	}
}
