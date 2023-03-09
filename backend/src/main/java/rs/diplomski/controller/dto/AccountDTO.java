package rs.diplomski.controller.dto;

import java.util.Date;

public class AccountDTO {
	private Long accId;

	private String accName;

	private String accSurname;

	private String accEmail;

	private String accPassword;

	private RoleDTO accRole;

	private Boolean accEnabled;

	private String accPhone;

	private String accUsername;

	private Date accBirthday;

	private String accPhoto;

	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccSurname() {
		return accSurname;
	}

	public void setAccSurname(String accSurname) {
		this.accSurname = accSurname;
	}

	public String getAccEmail() {
		return accEmail;
	}

	public void setAccEmail(String accEmail) {
		this.accEmail = accEmail;
	}

	public String getAccPassword() {
		return accPassword;
	}

	public void setAccPassword(String accPassword) {
		this.accPassword = accPassword;
	}

	public RoleDTO getAccRole() {
		return accRole;
	}

	public void setAccRole(RoleDTO accRole) {
		this.accRole = accRole;
	}

	public Boolean getAccEnabled() {
		return accEnabled;
	}

	public void setAccEnabled(Boolean accEnabled) {
		this.accEnabled = accEnabled;
	}

	public String getAccPhone() {
		return accPhone;
	}

	public void setAccPhone(String accPhone) {
		this.accPhone = accPhone;
	}

	public String getAccUsername() {
		return accUsername;
	}

	public void setAccUsername(String accUsername) {
		this.accUsername = accUsername;
	}

	public Date getAccBirthday() {
		return accBirthday;
	}

	public void setAccBirthday(Date accBirthday) {
		this.accBirthday = accBirthday;
	}

	public String getAccPhoto() {
		return accPhoto;
	}

	public void setAccPhoto(String accPhoto) {
		this.accPhoto = accPhoto;
	}

}
