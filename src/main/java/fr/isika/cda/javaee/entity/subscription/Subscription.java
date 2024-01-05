package fr.isika.cda.javaee.entity.subscription;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import fr.isika.cda.javaee.entity.spaces.Administrative;
import fr.isika.cda.javaee.entity.spaces.Configuration;
import fr.isika.cda.javaee.entity.spaces.Infos;
import fr.isika.cda.javaee.entity.spaces.OnlineShop;
import fr.isika.cda.javaee.entity.spaces.Style;

@Entity
public class Subscription {

	@Id
	@GeneratedValue
	private Long subscriptionId;

	private String subscriptionName;
	private String description;
	private String engagement;
	private String promotion;

	@OneToOne
	private Price price;

	public Subscription() {

	}

	public Subscription(boolean isViewModel) {
		this.getPrice();
		this.getSubscriptionName();
		this.getDescription();
		this.getEngagement();
		this.getEngagement();
	}

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long idSubscription) {
		this.subscriptionId = idSubscription;
	}

	public String getSubscriptionName() {
		return subscriptionName;
	}

	public void setSubscriptionName(String name) {
		this.subscriptionName = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEngagement() {
		return engagement;
	}

	public void setEngagement(String engagement) {
		this.engagement = engagement;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

}
