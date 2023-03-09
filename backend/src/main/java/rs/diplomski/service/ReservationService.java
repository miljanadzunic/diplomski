package rs.diplomski.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.diplomski.controller.dto.ReservationDTO;
import rs.diplomski.mapper.ReservationMapper;
import rs.diplomski.model.Reservation;
import rs.diplomski.model.Status;
import rs.diplomski.model.StudentCoach;
import rs.diplomski.repository.ReservationRespository;
import rs.diplomski.repository.StatusRepository;
import rs.diplomski.repository.StudentCoachRepository;
import rs.diplomski.sys.exception.CustomException;

@Service
public class ReservationService {

	@Autowired
	private ReservationRespository reservationRepository;
	
	@Autowired
	private StudentCoachRepository studentCoachRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private ReservationMapper reservationMapper;
	
	@PersistenceContext
	private EntityManager entitiyManager;

	private String table = "reservation";

	@Transactional(rollbackFor = Exception.class)
	public ReservationDTO create(ReservationDTO inputDTO) throws Exception {
		Reservation input = this.reservationMapper.dtoToEntity(inputDTO);
		Status newStatus;
		if (input.getResCoach() == null) {
			newStatus = statusRepository.getStatusByCodeAndTable("STA_ODOBREN", table);
		} else {
			if(input.getResGroup() == null && input.getResStudent() != null && input.getResStudent().getAccRole().getRolName() == "recreationist") {
				newStatus = statusRepository.getStatusByCodeAndTable("STA_NOVI", table);
			} else {
				newStatus = statusRepository.getStatusByCodeAndTable("STA_ODOBREN", table);
			}
		}
		
		input.setResStatus(newStatus);
		Reservation newRes = this.reservationRepository.save(input);

		ReservationDTO newResDTO = this.reservationMapper.entityToDto(newRes);
		
		return newResDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void delete (Long id) throws Exception {
		Optional<Reservation> opt = reservationRepository.findById(id);
		opt.orElseThrow(() -> new CustomException("NOT_FOUND"));
		reservationRepository.deleteById(id);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByDateStudentCourt(Date date, Long studentId, Long courtId){
		List<Reservation> list = reservationRepository.getReservationsByDateStudentCourt(date, studentId, courtId);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByDateStudentCourtCoach(Date date, Long studentId, Long courtId, Long coachId){
		List<Reservation> list = reservationRepository.getReservationsByDateStudentCourtCoach(date, studentId, courtId, coachId);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByStudentId(Long studentId){
		List<Reservation> list = reservationRepository.getReservationsByStudentId(studentId);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByDateStudentIdStatus(Date date, Long studentId){
		List<Reservation> list = reservationRepository.getReservationsByDateStudentIdStatus(date, studentId);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByCoachId(Long coachId){
		List<Reservation> list = reservationRepository.getReservationsByCoachId(coachId);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public boolean exists(Long id) {
		boolean exist = reservationRepository.existsById(id);
		return exist;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void cancel (Long id) throws Exception {
		Optional<Reservation> opt = reservationRepository.findById(id);
		Reservation res = opt.orElseThrow(() -> new CustomException("NOT_FOUND"));
		
		res.setResStatus(statusRepository.getStatusByCodeAndTable("STA_OTKAZAN", "reservation"));
		reservationRepository.save(res);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByDate(Date date){
		List<Reservation> list = reservationRepository.getReservationsByDate(date);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByDateTimeStart(Date date, Integer timeStart){
		List<Reservation> list = reservationRepository.getReservationsByDateTimeStart(date, timeStart);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByDateTimeStartForCoaches(Date date, Integer timeStart){
		List<Reservation> list = reservationRepository.getReservationsByDateTimeStartForCoaches(date, timeStart);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void acceptReservation(Long id) throws Exception {
		Optional<Reservation> opt = reservationRepository.findById(id);
		Reservation res = opt.orElseThrow(() -> new CustomException("NOT_FOUND"));
		
		res.setResStatus(statusRepository.getStatusByCodeAndTable("STA_ODOBREN", "reservation"));
		reservationRepository.save(res);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByDateCourtCoachGroupStudents(Date date, Long courtId, Long coachId, Long groupId) throws Exception{
		List<StudentCoach> studentCoachList = this.studentCoachRepository.getByGroupId(groupId);
		
		if(studentCoachList == null) {
			throw new CustomException("NOT_FOUND");
		}
		
		
		String query = "SELECT res.* FROM reservation res"
				+ " LEFT JOIN status sta ON (sta.sta_id = res.res_status_id)"
				+ " WHERE (res.res_court_id = " + courtId
				+ " OR res.res_coach_id = " + coachId
				+ " OR res.res_group_id = " + groupId;
		
		String wherePart = "";
		for(int i = 0; i < studentCoachList.size(); i++) {
			System.out.println("MILJANA DEBUG ----" + i + ". studentCoach ---" + studentCoachList.get(i));
			if(studentCoachList.get(i) != null && studentCoachList.get(i).getSctStudent() != null) {
				Long studentId = studentCoachList.get(i).getSctStudent().getAccId();
				wherePart = wherePart + " OR res.res_student_id = " + studentId;
			}
		}
		query = query + wherePart + ")"
				+ " AND res.res_date = :date"
				+ " AND sta.sta_table = 'reservation'"
				+ " AND sta.sta_code != 'STA_OTKAZAN'"
				+ " ORDER BY res.res_time_start ASC";
		
		System.out.println("MILJANA DEBUG ----QUERY = " + query);
		
		@SuppressWarnings("unchecked")
		List<Reservation> list = entitiyManager.createNativeQuery(query,  Reservation.class).setParameter("date", date).getResultList();
		System.out.println("MILJANA DEBUG ----LIST = " + list);
		if(list == null) {
			return null;
		}
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByDateStudentCourtCoachGroup(Date date, Long studentId, Long courtId, Long coachId){
		StudentCoach sct = this.studentCoachRepository.findByCoachAndStudentAcceptedAndGroupNotNull(coachId, studentId);
		List<Reservation> list;
		if(sct != null) {
			Long groupId = sct.getSctGroup().getGrpId();
			list = reservationRepository.getReservationsByDateStudentCourtCoachGroup(date, studentId, courtId, coachId, groupId);
		} else {
			list = reservationRepository.getReservationsByDateStudentCourtCoach(date, studentId, courtId, coachId);
		}
		
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByCoachAndGroupNotNull(Long coachId){
		List<Reservation> list = reservationRepository.getReservationsByCoachAndGroupNotNull(coachId);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByGroupId(Long groupId){
		List<Reservation> list = reservationRepository.getReservationsByGroupId(groupId);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<ReservationDTO> getReservationsByStudentIdAndGroup(Long studentId){
		List<Reservation> list = reservationRepository.getReservationsByStudentIdAndGroup(studentId);
		List<ReservationDTO> listDTO = reservationMapper.entitiesToDtos(list);
		return listDTO;
	}
	
}
