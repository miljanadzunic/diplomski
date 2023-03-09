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
@Table(name = "court")
public class Court {
	
	@Id
	@SequenceGenerator(name = "court_cor_id_seq", sequenceName = "court_cor_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_cor_id_seq")
	@Column(name = "cor_id")
	private Long corId;
	
	@Column(name = "cor_name")
	private String corName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cor_type")
	private CourtType corType;
	
	@Column(name = "cor_img")
	private String corImg;
	
	@Column(name = "cor_desc")
	private String corDesc;

	public Long getCorId() {
		return corId;
	}

	public void setCorId(Long corId) {
		this.corId = corId;
	}

	public String getCorName() {
		return corName;
	}

	public void setCorName(String corName) {
		this.corName = corName;
	}

	public CourtType getCorType() {
		return corType;
	}

	public void setCorType(CourtType corType) {
		this.corType = corType;
	}

	public String getCorImg() {
		return corImg;
	}

	public void setCorImg(String corImg) {
		this.corImg = corImg;
	}

	public String getCorDesc() {
		return corDesc;
	}

	public void setCorDesc(String corDesc) {
		this.corDesc = corDesc;
	}
	
}
