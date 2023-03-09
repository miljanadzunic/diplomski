package rs.diplomski.controller.dto;

public class CourtDTO {
	private Long corId;

	private String corName;

	private CourtTypeDTO corType;

	private String corImg;

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

	public CourtTypeDTO getCorType() {
		return corType;
	}

	public void setCorType(CourtTypeDTO corType) {
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
