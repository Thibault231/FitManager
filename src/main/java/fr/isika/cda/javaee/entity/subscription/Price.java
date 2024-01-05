package fr.isika.cda.javaee.entity.subscription;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Price {

	@Id
	@GeneratedValue
	private Long priceId;

	private float monthlyPrice;

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long idPrice) {
		this.priceId = idPrice;
	}

	public float getMonthlyPrice() {
		return monthlyPrice;
	}

	public void setMonthlyPrice(float price) {
		this.monthlyPrice = price;
	}

}
