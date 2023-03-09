package rs.diplomski.controller.dto;

public class PriceListDTO {

	private Long prlId;

	private String prlItem;

	private Double prlPrice;

	private String prlDesc;
	
	private String prlItemType;

	public Long getPrlId() {
		return prlId;
	}

	public void setPrlId(Long prlId) {
		this.prlId = prlId;
	}

	public String getPrlItem() {
		return prlItem;
	}

	public void setPrlItem(String prlItem) {
		this.prlItem = prlItem;
	}

	public Double getPrlPrice() {
		return prlPrice;
	}

	public void setPrlPrice(Double prlPrice) {
		this.prlPrice = prlPrice;
	}

	public String getPrlDesc() {
		return prlDesc;
	}

	public void setPrlDesc(String prlDesc) {
		this.prlDesc = prlDesc;
	}

	public String getPrlItemType() {
		return prlItemType;
	}

	public void setPrlItemType(String prlItemType) {
		this.prlItemType = prlItemType;
	}

}
