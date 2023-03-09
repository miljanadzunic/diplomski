package rs.diplomski.controller.dto;

public class StudentCoachDTO {
	
	private Long sctId;

	private AccountDTO sctStudent;
	
	private AccountDTO sctCoach;
	
	private GroupDTO sctGroup;
	
	private StatusDTO sctStatus;

	public Long getSctId() {
		return sctId;
	}

	public void setSctId(Long sctId) {
		this.sctId = sctId;
	}

	public AccountDTO getSctStudent() {
		return sctStudent;
	}

	public void setSctStudent(AccountDTO sctStudent) {
		this.sctStudent = sctStudent;
	}

	public AccountDTO getSctCoach() {
		return sctCoach;
	}

	public void setSctCoach(AccountDTO sctCoach) {
		this.sctCoach = sctCoach;
	}

	public GroupDTO getSctGroup() {
		return sctGroup;
	}

	public void setSctGroup(GroupDTO sctGroup) {
		this.sctGroup = sctGroup;
	}

	public StatusDTO getSctStatus() {
		return sctStatus;
	}

	public void setSctStatus(StatusDTO sctStatus) {
		this.sctStatus = sctStatus;
	}
	
	
}
