package rs.diplomski.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@SequenceGenerator(name = "account_acc_id_seq", sequenceName = "account_acc_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_acc_id_seq")
	@Column(name = "acc_id")
	private Long accId;

	@Column(name = "acc_name")
	private String accName;

	@Column(name = "acc_surname")
	private String accSurname;

	@Column(name = "acc_email")
	private String accEmail;

	@Column(name = "acc_password")
	private String accPassword;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acc_role")
	private Role accRole;

	@Column(name = "acc_enabled")
	private Boolean accEnabled;

	@Column(name = "acc_phone")
	private String accPhone;

	@Column(name = "acc_username")
	private String accUsername;
	
	@Column(name = "acc_birthday")
	private Date accBirthday;
	
	@Column(name = "acc_photo")
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

	public Role getAccRole() {
		return accRole;
	}

	public void setAccRole(Role accRole) {
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
