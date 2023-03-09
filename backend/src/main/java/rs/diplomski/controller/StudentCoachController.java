package rs.diplomski.controller;

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

import rs.diplomski.controller.dto.StudentCoachDTO;
import rs.diplomski.service.StudentCoachService;
import rs.diplomski.sys.exception.CustomException;
import rs.diplomski.sys.model.ResponseWrapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class StudentCoachController {
	
	@Autowired
	private StudentCoachService studentCoachService;
	
	@RequestMapping(value = "/studenCoachRequests/create", method = RequestMethod.POST)
	ResponseEntity<?> create(@RequestBody StudentCoachDTO inputDTO) throws Exception {
		if(inputDTO == null) {
			throw new CustomException("NOT FOUND");
		}
		
		StudentCoachDTO resDTO = this.studentCoachService.create(inputDTO);
		return new ResponseEntity<>(new ResponseWrapper(resDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/studenCoachRequests/delete/{id}", method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
		if(id == null || !studentCoachService.exists(id)) {
			throw new CustomException("NOT_FOUND");
		}
		this.studentCoachService.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studenCoachRequests/getAllByCoachId/{coachId}", method = RequestMethod.GET)
	ResponseEntity<?> getAllByCoachId(@PathVariable Long coachId) throws Exception {
		List<StudentCoachDTO> resDTOs = this.studentCoachService.getAllByCoachId(coachId);
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studenCoachRequests/cancel", method = RequestMethod.POST)
	ResponseEntity<?> cancel(@RequestBody Long id) throws Exception {
		if(id == null || !studentCoachService.exists(id)) {
			throw new CustomException("NOT_FOUND");
		}
		studentCoachService.cancel(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studenCoachRequests/accept/{id}", method = RequestMethod.GET)
	ResponseEntity<?> accept(@PathVariable Long id) throws Exception {
		if(id == null || !studentCoachService.exists(id)) {
			throw new CustomException("NOT FOUND");
		}
		
		studentCoachService.accept(id);
		return new ResponseEntity<>(new ResponseWrapper(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studenCoachRequests/findByCoachAndStudent/{coachId}/{studentId}", method = RequestMethod.GET)
	ResponseEntity<?> findByCoachAndStudent(@PathVariable Long coachId, @PathVariable Long studentId) throws Exception {
		if(coachId == null || studentId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		Boolean res = studentCoachService.findByCoachAndStudent(coachId, studentId);
		return new ResponseEntity<>(new ResponseWrapper(res), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studenCoachRequests/getAllByStudentIdAccepted/{studentId}", method = RequestMethod.GET)
	ResponseEntity<?> getAllByStudentIdAccepted(@PathVariable Long studentId) throws Exception {
		List<StudentCoachDTO> resDTOs = this.studentCoachService.getAllByStudentIdAccepted(studentId);
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studenCoachRequests/getAllByStudentIdNew/{studentId}", method = RequestMethod.GET)
	ResponseEntity<?> getAllByStudentIdNew(@PathVariable Long studentId) throws Exception {
		List<StudentCoachDTO> resDTOs = this.studentCoachService.getAllByStudentIdNew(studentId);
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studenCoachRequests/getAllByStudent/{studentId}", method = RequestMethod.GET)
	ResponseEntity<?> getAllByStudent(@PathVariable Long studentId) throws Exception {
		List<StudentCoachDTO> resDTOs = this.studentCoachService.getAllByStudent(studentId);
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studenCoachRequests/getByCoachAccepted/{coachId}", method = RequestMethod.GET)
	ResponseEntity<?> getByCoachAccepted(@PathVariable Long coachId) throws Exception {
		List<StudentCoachDTO> resDTOs = this.studentCoachService.getByCoachAccepted(coachId);
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studenCoachRequests/getAllByGroupId/{groupId}", method = RequestMethod.GET)
	ResponseEntity<?> getAllByGroupId(@PathVariable Long groupId) throws Exception {
		List<StudentCoachDTO> resDTOs = this.studentCoachService.getAllByGroupId(groupId);
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
	
}
