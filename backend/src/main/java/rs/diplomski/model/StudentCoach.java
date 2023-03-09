package rs.diplomski.model;

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
@Table(name = "student_coach")
public class StudentCoach {
	
	@Id
	@SequenceGenerator(name = "student_coach_sct_id_seq", sequenceName = "student_coach_sct_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_coach_sct_id_seq")
	@Column(name = "sct_id")
	private Long sctId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sct_student_id")
	private Account sctStudent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sct_coach_id")
	private Account sctCoach;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sct_group_id")
	private Group sctGroup;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sct_status_id")
	private Status sctStatus;

	public Long getSctId() {
		return sctId;
	}

	public void setSctId(Long sctId) {
		this.sctId = sctId;
	}

	public Account getSctStudent() {
		return sctStudent;
	}

	public void setSctStudent(Account sctStudent) {
		this.sctStudent = sctStudent;
	}

	public Account getSctCoach() {
		return sctCoach;
	}

	public void setSctCoach(Account sctCoach) {
		this.sctCoach = sctCoach;
	}

	public Group getSctGroup() {
		return sctGroup;
	}

	public void setSctGroup(Group sctGroup) {
		this.sctGroup = sctGroup;
	}

	public Status getSctStatus() {
		return sctStatus;
	}

	public void setSctStatus(Status sctStatus) {
		this.sctStatus = sctStatus;
	}	
}
