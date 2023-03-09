package rs.diplomski.controller.dto;


public class RoleDTO {

	private Long rolId;

	private String rolName;
	
	private String rolDesc;
	
	public Long getRolId() {
		return rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public String getRolDesc() {
		return rolDesc;
	}

	public void setRolDesc(String rolDesc) {
		this.rolDesc = rolDesc;
	}
	
}
