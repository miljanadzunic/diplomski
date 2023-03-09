package rs.diplomski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.diplomski.controller.dto.RoleDTO;
import rs.diplomski.service.RoleService;
import rs.diplomski.sys.model.ResponseWrapper;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "roles/getAllRoles", method = RequestMethod.GET)
	ResponseEntity<?> getAllRoles() throws Exception {
		List<RoleDTO> resDTOs = this.roleService.getAllAccounts();
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
}
