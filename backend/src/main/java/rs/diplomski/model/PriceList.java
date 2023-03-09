package rs.diplomski.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "price_list")
public class PriceList {

	@Id
	@SequenceGenerator(name = "price_list_prl_id_seq", sequenceName = "price_list_prl_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_list_prl_id_seq")
	private Long prlId;

	@Column(name = "prl_item")
	private String prlItem;

	@Column(name = "prl_price")
	private Double prlPrice;

	@Column(name = "prl_desc")
	private String prlDesc;
	
	@Column(name = "prl_item_type")
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
