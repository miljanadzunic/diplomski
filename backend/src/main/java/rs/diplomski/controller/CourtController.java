package rs.diplomski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.diplomski.controller.dto.CourtDTO;
import rs.diplomski.service.CourtService;
import rs.diplomski.sys.model.ResponseWrapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class CourtController {

	@Autowired
	private CourtService courtService;
	
	@RequestMapping(value = "/getAllCourts", method = RequestMethod.GET)
	ResponseEntity<?> getAllCourts() throws Exception {
		List<CourtDTO> resDTOs = this.courtService.getAllCourts();
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
}
