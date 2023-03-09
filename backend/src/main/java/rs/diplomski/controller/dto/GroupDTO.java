package rs.diplomski.controller.dto;

public class GroupDTO {
	private Long grpId;

	private AccountDTO grpCoach;

	private String grpDesc;

	private String grpName;
	
	private Integer grpStudentsNum;

	public Long getGrpId() {
		return grpId;
	}

	public void setGrpId(Long grpId) {
		this.grpId = grpId;
	}

	public AccountDTO getGrpCoach() {
		return grpCoach;
	}

	public void setGrpCoach(AccountDTO grpCoach) {
		this.grpCoach = grpCoach;
	}

	public String getGrpDesc() {
		return grpDesc;
	}

	public void setGrpDesc(String grpDesc) {
		this.grpDesc = grpDesc;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public Integer getGrpStudentsNum() {
		return grpStudentsNum;
	}

	public void setGrpStudentsNum(Integer grpStudentsNum) {
		this.grpStudentsNum = grpStudentsNum;
	}

}
