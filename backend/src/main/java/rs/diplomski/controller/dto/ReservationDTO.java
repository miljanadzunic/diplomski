package rs.diplomski.controller.dto;

import java.util.Date;

public class ReservationDTO {

	private Long resId;

	private Date resDate;

	private Integer resTimeStart;

	private Integer resTimeEnd;

	private CourtDTO resCourt;

	private AccountDTO resCoach;

	private Integer resRacketNum;

	private StatusDTO resStatus;

	private Integer resTerminNum;
	
	private AccountDTO resStudent;
	
	private GroupDTO resGroup;

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

	public CourtDTO getResCourt() {
		return resCourt;
	}

	public void setResCourt(CourtDTO resCourt) {
		this.resCourt = resCourt;
	}

	public AccountDTO getResCoach() {
		return resCoach;
	}

	public void setResCoach(AccountDTO resCoach) {
		this.resCoach = resCoach;
	}

	public Integer getResRacketNum() {
		return resRacketNum;
	}

	public void setResRacketNum(Integer resRacketNum) {
		this.resRacketNum = resRacketNum;
	}

	public StatusDTO getResStatus() {
		return resStatus;
	}

	public void setResStatus(StatusDTO resStatus) {
		this.resStatus = resStatus;
	}

	public Integer getResTerminNum() {
		return resTerminNum;
	}

	public void setResTerminNum(Integer resTerminNum) {
		this.resTerminNum = resTerminNum;
	}

	public AccountDTO getResStudent() {
		return resStudent;
	}

	public void setResStudent(AccountDTO resStudent) {
		this.resStudent = resStudent;
	}

	public GroupDTO getResGroup() {
		return resGroup;
	}

	public void setResGroup(GroupDTO resGroup) {
		this.resGroup = resGroup;
	}

}
