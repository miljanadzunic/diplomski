package rs.diplomski.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.diplomski.controller.dto.AccountDTO;
import rs.diplomski.controller.dto.GroupDTO;
import rs.diplomski.mapper.AccountMapper;
import rs.diplomski.model.Account;
import rs.diplomski.repository.AccountRepository;
import rs.diplomski.service.GroupService;
import rs.diplomski.sys.exception.CustomException;
import rs.diplomski.sys.model.ResponseWrapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@RequestMapping(value = "/groups/create", method = RequestMethod.POST)
	ResponseEntity<?> create(@RequestBody Map<String, String> data) throws Exception {
		if(data == null) {
			throw new CustomException("NOT FOUND");
		}
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setGrpDesc(data.get("grpDesc"));
		groupDTO.setGrpName(data.get("grpName"));
		groupDTO.setGrpStudentsNum(Integer.valueOf(data.get("grpStudentsNum")));
		Optional<Account> opt = this.accountRepository.findById(Long.valueOf(data.get("coachId")));
		Account coach = opt.orElseThrow(() -> new CustomException("COACH_NOT_FOUND")); 
		AccountDTO coachDTO = this.accountMapper.entityToDto(coach);
		groupDTO.setGrpCoach(coachDTO);
		
		Long acc1 = Long.valueOf(data.get("student1"));
		Long acc2 = Long.valueOf(data.get("student2"));
		Long acc3 = Long.valueOf(data.get("student1"));
		List<Long> listStudents = new ArrayList<>();
		listStudents.add(acc1);
		listStudents.add(acc2);
		if(acc3 != null && acc3 != 0) {
			listStudents.add(acc3);
		}
	
		
		GroupDTO resDTO = this.groupService.create(groupDTO, listStudents);
		return new ResponseEntity<>(new ResponseWrapper(resDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/groups/getGroupsByCoachId/{coachId}", method = RequestMethod.GET)
	ResponseEntity<?> getGroupsByCoachId(@PathVariable Long coachId) throws Exception {
		if(coachId == null) {
			throw new CustomException("NOT FOUND");
		}
		
		List<GroupDTO> listDTO = groupService.getGroupsByCoachId(coachId);
		return new ResponseEntity<>(new ResponseWrapper(listDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/groups/delete/{groupId}", method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable Long groupId) throws Exception {
		if(groupId == null || !this.groupService.exists(groupId)) {
			throw new CustomException("NOT FOUND");
		}
		
		 groupService.delete(groupId);
		return new ResponseEntity<>(new ResponseWrapper(), HttpStatus.OK);
	}
}
