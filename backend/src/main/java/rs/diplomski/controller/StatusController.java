package rs.diplomski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.diplomski.controller.dto.StatusDTO;
import rs.diplomski.service.StatusService;
import rs.diplomski.sys.model.ResponseWrapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class StatusController {
	
	@Autowired
	private StatusService statusService;
	
	@RequestMapping(value = "/getAllStatuses", method = RequestMethod.GET)
	ResponseEntity<?> getAllStatuses() throws Exception {
		List<StatusDTO> resDTOs = this.statusService.getAllStatuses();
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getStatusesByTable/{tableName}", method = RequestMethod.GET)
	ResponseEntity<?> getStatusesByTable(@PathVariable String tableName) throws Exception {
		List<StatusDTO> resDTOs = this.statusService.getAllByTable(tableName);
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
}
