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
@Table(name = "all_groups")
public class Group {
	
	@Id
	@SequenceGenerator(name = "group_grp_id_seq", sequenceName = "group_grp_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_grp_id_seq")
	@Column(name = "grp_id")
	private Long grpId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grp_coach_id")
	private Account grpCoach;
	
	@Column(name = "grp_desc")
	private String grpDesc;
	
	@Column(name = "grp_name")
	private String grpName;
	
	@Column(name = "grp_students_num")
	private Integer grpStudentsNum;

	public Long getGrpId() {
		return grpId;
	}

	public void setGrpId(Long grpId) {
		this.grpId = grpId;
	}

	public Account getGrpCoach() {
		return grpCoach;
	}

	public void setGrpCoach(Account grpCoach) {
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
