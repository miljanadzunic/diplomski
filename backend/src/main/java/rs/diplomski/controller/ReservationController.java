package rs.diplomski.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.diplomski.controller.dto.ReservationDTO;
import rs.diplomski.service.ReservationService;
import rs.diplomski.sys.exception.CustomException;
import rs.diplomski.sys.model.ResponseWrapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(value = "/reservations/create", method = RequestMethod.POST)
	ResponseEntity<?> create(@RequestBody ReservationDTO inputDTO) throws Exception {
		if(inputDTO == null) {
			throw new CustomException("NOT FOUND");
		}
		
		ReservationDTO resDTO = this.reservationService.create(inputDTO);
		return new ResponseEntity<>(new ResponseWrapper(resDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/reservations/delete/{id}", method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
		if(id == null || !reservationService.exists(id)) {
			throw new CustomException("NOT_FOUND");
		}
		this.reservationService.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getAllByDateAndStudentAndCourt/{date}/{studentId}/{crtId}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByDateStudentCourt(@PathVariable Date date, @PathVariable Long studentId, @PathVariable Long crtId) throws Exception {
		if(date == null || crtId == null || studentId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByDateStudentCourt(date, studentId, crtId);
		
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getAllByDateAndStudentAndCourtAndCoach/{date}/{studentId}/{crtId}/{coachId}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByDateStudentCourtCoach(@PathVariable Date date,  @PathVariable Long studentId, @PathVariable Long crtId, @PathVariable Long coachId) throws Exception {
		if(date == null || crtId == null || studentId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByDateStudentCourtCoach(date, studentId, crtId, coachId);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByStudentId/{studentId}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByStudentId(@PathVariable Long studentId) throws Exception {
		if(studentId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByStudentId(studentId);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByDateStudentIdStatus/{date}/{studentId}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByStudentIdStatus(@PathVariable Date date, @PathVariable Long studentId) throws Exception {
		if(studentId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByDateStudentIdStatus(date, studentId);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByCoachId/{coachId}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByCoachId(@PathVariable Long coachId) throws Exception {
		if(coachId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByCoachId(coachId);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/cancel", method = RequestMethod.POST)
	ResponseEntity<?> cancel(@RequestBody Long id) throws Exception {
		if(id == null || !reservationService.exists(id)) {
			throw new CustomException("NOT_FOUND");
		}
		reservationService.cancel(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByDate", method = RequestMethod.POST)
	ResponseEntity<?> getReservationsByCoachId(@PathVariable Date date) throws Exception {
		if(date == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByDate(date);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByDateAndTimeStart/{date}/{timeStart}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByDateTimeStart(@PathVariable Date date, @PathVariable Integer timeStart) throws Exception {
		if(date == null || timeStart == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByDateTimeStart(date, timeStart);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByDateAndTimeStartForCoaches/{date}/{timeStart}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByDateTimeStartForCoaches(@PathVariable Date date, @PathVariable Integer timeStart) throws Exception {
		if(date == null || timeStart == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByDateTimeStartForCoaches(date, timeStart);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/acceptReservation/{id}", method = RequestMethod.GET)
	ResponseEntity<?> acceptReservation(@PathVariable Long id) throws Exception {
		if(id == null || !reservationService.exists(id)) {
			throw new CustomException("NOT FOUND");
		}
		
		reservationService.acceptReservation(id);
		return new ResponseEntity<>(new ResponseWrapper(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByDateCourtCoachGroupStudents/{date}/{courtId}/{coachId}/{groupId}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByDateCourtCoachGroupStudents(@PathVariable Date date, @PathVariable Long courtId, @PathVariable Long coachId, @PathVariable Long groupId ) throws Exception {
		if(date == null || courtId == null || coachId == null || groupId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByDateCourtCoachGroupStudents(date, courtId, coachId, groupId);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByDateStudentCourtCoachGroup/{date}/{studentId}/{crtId}/{coachId}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByDateStudentCourtCoachGroup(@PathVariable Date date,  @PathVariable Long studentId, @PathVariable Long crtId, @PathVariable Long coachId) throws Exception {
		if(date == null || crtId == null || studentId == null || coachId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByDateStudentCourtCoachGroup(date, studentId, crtId, coachId);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByCoachAndGroupNotNull/{coachId}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByCoachAndGroupNotNull(@PathVariable Long coachId) throws Exception {
		if(coachId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByCoachAndGroupNotNull(coachId);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByGroupId/{groupId}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByGroupId(@PathVariable Long groupId) throws Exception {
		if(groupId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByGroupId(groupId);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/getReservationsByStudentIdAndGroup/{studentId}", method = RequestMethod.GET)
	ResponseEntity<?> getReservationsByStudentIdAndGroup(@PathVariable Long studentId) throws Exception {
		if(studentId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<ReservationDTO> listDTO = reservationService.getReservationsByStudentIdAndGroup(studentId);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
}
