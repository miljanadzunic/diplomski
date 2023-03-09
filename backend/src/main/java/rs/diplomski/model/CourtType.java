package rs.diplomski.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "court_type")
public class CourtType {

	@Id
	@SequenceGenerator(name = "court_type_crt_id_seq", sequenceName = "court_type_crt_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_type_crt_id_seq")
	@Column(name = "crt_id")
	private Long crtId;
	
	@Column(name = "crt_name")
	private String crtName;
	
	@Column(name = "crt_desc")
	private String crtDesc;

	public Long getCrtId() {
		return crtId;
	}

	public void setCrtId(Long crtId) {
		this.crtId = crtId;
	}

	public String getCrtName() {
		return crtName;
	}

	public void setCrtName(String crtName) {
		this.crtName = crtName;
	}

	public String getCrtDesc() {
		return crtDesc;
	}

	public void setCrtDesc(String crtDesc) {
		this.crtDesc = crtDesc;
	}
	
}
