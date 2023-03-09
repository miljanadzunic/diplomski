package rs.diplomski.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status {
	@Id
	@SequenceGenerator(name = "status_sta_id_seq", sequenceName = "status_sta_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_sta_id_seq")
	private Long staId;

	@Column(name = "sta_code")
	private String staCode;

	@Column(name = "sta_desc")
	private String staDesc;

	@Column(name = "sta_table")
	private String staTable;

	public Long getStaId() {
		return staId;
	}

	public void setStaId(Long staId) {
		this.staId = staId;
	}

	public String getStaCode() {
		return staCode;
	}

	public void setStaCode(String staCode) {
		this.staCode = staCode;
	}

	public String getStaDesc() {
		return staDesc;
	}

	public void setStaDesc(String staDesc) {
		this.staDesc = staDesc;
	}

	public String getStaTable() {
		return staTable;
	}

	public void setStaTable(String staTable) {
		this.staTable = staTable;
	}
	
}
