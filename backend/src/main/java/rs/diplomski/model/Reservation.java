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
@Table(name = "reservation")
public class Reservation {

	@Id
	@SequenceGenerator(name = "reservation_res_id_seq", sequenceName = "reservation_res_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_res_id_seq")
	private Long resId;
	
	@Column(name = "res_date")
	private Date resDate;
	
	@Column(name = "res_time_start")
	private Integer resTimeStart;
	
	@Column(name = "res_time_end")
	private Integer resTimeEnd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_court_id")
	private Court resCourt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_coach_id")
	private Account resCoach;
	
	@Column(name = "res_racket_num")
	private Integer resRacketNum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_status_id")
	private Status resStatus;
	
	@Column(name = "res_termin_num")
	private Integer resTerminNum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_student_id")
	private Account resStudent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_group_id")
	private Group resGroup;
	
	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public Date getResDate() {
		return resDate;
	}

	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}

	public Integer getResTimeStart() {
		return resTimeStart;
	}

	public void setResTimeStart(Integer resTimeStart) {
		this.resTimeStart = resTimeStart;
	}

	public Integer getResTimeEnd() {
		return resTimeEnd;
	}

	public void setResTimeEnd(Integer resTimeEnd) {
		this.resTimeEnd = resTimeEnd;
	}

	public Court getResCourt() {
		return resCourt;
	}

	public void setResCourt(Court resCourt) {
		this.resCourt = resCourt;
	}

	public Account getResCoach() {
		return resCoach;
	}

	public void setResCoach(Account resCoach) {
		this.resCoach = resCoach;
	}

	public Integer getResRacketNum() {
		return resRacketNum;
	}

	public void setResRacketNum(Integer resRacketNum) {
		this.resRacketNum = resRacketNum;
	}

	public Status getResStatus() {
		return resStatus;
	}

	public void setResStatus(Status resStatus) {
		this.resStatus = resStatus;
	}

	public Integer getResTerminNum() {
		return resTerminNum;
	}

	public void setResTerminNum(Integer resTerminNum) {
		this.resTerminNum = resTerminNum;
	}

	public Account getResStudent() {
		return resStudent;
	}

	public void setResStudent(Account resStudent) {
		this.resStudent = resStudent;
	}

	public Group getResGroup() {
		return resGroup;
	}

	public void setResGroup(Group resGroup) {
		this.resGroup = resGroup;
	}
	
}
