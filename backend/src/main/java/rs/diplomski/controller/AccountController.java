package rs.diplomski.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.diplomski.controller.dto.AccountDTO;
import rs.diplomski.service.AccountService;
import rs.diplomski.sys.exception.CustomException;
import rs.diplomski.sys.model.ResponseWrapper;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/getAllAccounts", method = RequestMethod.GET)
	ResponseEntity<?> getAllAccounts() throws Exception {
		List<AccountDTO> resDTOs = this.accountService.getAllAccounts();
		return new ResponseEntity<>(new ResponseWrapper(resDTOs), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	ResponseEntity<?> findByUsernameAndPassword(@RequestBody Map<String, String> params) throws Exception {
		String username = params.get("username");
		String password = params.get("password");

		if (username == null || password == null) {
			throw new CustomException("NOT FOUND");
		}

		AccountDTO resDTO = this.accountService.getAccountByUsernameAndPassword(username, password);
		return new ResponseEntity<>(new ResponseWrapper(resDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts/create", method = RequestMethod.POST)
	ResponseEntity<?> create(@RequestBody AccountDTO inputDTO) throws Exception {
		if(inputDTO == null) {
			throw new CustomException("NOT FOUND");
		}
		
		AccountDTO accDTO = this.accountService.create(inputDTO);
		return new ResponseEntity<>(new ResponseWrapper(accDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/accounts/findByRole", method = RequestMethod.POST)
	ResponseEntity<?> findByRole(@RequestBody String inputDTO) throws Exception {
		if(inputDTO == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<AccountDTO> accDTOs = this.accountService.getAccountsByRole(inputDTO);
		return new ResponseEntity<>(new ResponseWrapper(accDTOs), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/accounts/enableAccount", method = RequestMethod.POST)
	ResponseEntity<?> enableAccount(@RequestBody AccountDTO inputDTO) throws Exception {
		if(inputDTO == null) {
			throw new CustomException("NOT FOUND");
		}
		
		AccountDTO resDTO = this.accountService.enableAccount(inputDTO);
		return new ResponseEntity<>(new ResponseWrapper(resDTO), HttpStatus.OK);
	}

}
