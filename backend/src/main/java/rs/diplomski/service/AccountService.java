package rs.diplomski.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.diplomski.controller.dto.AccountDTO;
import rs.diplomski.mapper.AccountMapper;
import rs.diplomski.model.Account;
import rs.diplomski.model.Role;
import rs.diplomski.repository.AccountRepository;
import rs.diplomski.repository.RoleRepository;
import rs.diplomski.sys.exception.CustomException;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Transactional(readOnly = true)
	public List<AccountDTO> getAllAccounts() {
		List<Account> res = accountRepository.findAll();
		List<AccountDTO> resDTOs = accountMapper.entitiesToDtos(res);
		return resDTOs;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public AccountDTO create(AccountDTO inputDTO) throws Exception {
		Account input = this.accountMapper.dtoToEntity(inputDTO);
		
		Account accByUsername = this.accountRepository.findAccountByUsername(input.getAccUsername());
		if(accByUsername != null) {
			throw new CustomException("ACCOUNT_USERNAME_EXISTS");
		}
		
		Account accByEmail = this.accountRepository.getAccountByEmail(input.getAccEmail());
		if(accByEmail != null) {
			throw new CustomException("ACCOUNT_EMAIL_EXISTS");
		}
		
		input.setAccEnabled(false);
		Account newRes = this.accountRepository.save(input);
		
		AccountDTO newResDTO = this.accountMapper.entityToDto(newRes);
		return newResDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public AccountDTO getAccountByUsernameAndPassword(String username, String password) throws Exception{
		Account res = accountRepository.getAccountByUsernameAndPassword(username, password);
		if(res != null && res.getAccEnabled() == false) {
			throw new CustomException("ERROR_ACCOUNT_NOT_ENABLED");
		}
		
		AccountDTO resDTO = accountMapper.entityToDto(res);
		return resDTO;
	}
	
	@Transactional(readOnly = true)
	public AccountDTO getAccountByEmail(String email) {
		Account res = accountRepository.getAccountByEmail(email);
		AccountDTO resDTO = accountMapper.entityToDto(res);
		return resDTO;
	}
	
	@Transactional(readOnly = true)
	public AccountDTO getAccountByUsername(String username) {
		Account res = accountRepository.findAccountByUsername(username);
		AccountDTO resDTO = accountMapper.entityToDto(res);
		return resDTO;
	}
	
	@Transactional(readOnly = true)
	public List<AccountDTO> getAccountsByRole(String rolName) throws Exception {
		Role accRole = roleRepository.findRoleByRolName(rolName);
		if(accRole == null) {
			throw new CustomException("NOT_FOUND");
		}
		List<Account> res = accountRepository.findAccountsByRole(accRole.getRolId());
		List<AccountDTO> resDTO = accountMapper.entitiesToDtos(res);
		return resDTO;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public AccountDTO enableAccount(AccountDTO accDTO) throws Exception {
		Account check = accountMapper.dtoToEntity(accDTO);
		Optional<Account> opt = accountRepository.findById(check.getAccId());
		Account acc = opt.orElseThrow(() -> new CustomException("NOT_FOUND"));
		if(acc == null) {
			throw new CustomException("NOT_FOUND");
		}
		
		acc.setAccEnabled(true);
		Account newAcc = accountRepository.save(acc);
		
		AccountDTO resDTO = accountMapper.entityToDto(newAcc);
		return resDTO;

	}

}
