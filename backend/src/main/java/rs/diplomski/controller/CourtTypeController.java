package rs.diplomski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.diplomski.controller.dto.CourtTypeDTO;
import rs.diplomski.service.CourtTypeService;
import rs.diplomski.sys.model.ResponseWrapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class CourtTypeController {
	
	@Autowired
	private CourtTypeService courtTypeService;
	
	@RequestMapping(value = "/getAllCourtTypes", method = RequestMethod.GET)
	ResponseEntity<?> getAllCourtTypes() throws Exception {
		List<CourtTypeDTO> resDTOs = this.courtTypeService.getAllCourtTypes();
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
}
