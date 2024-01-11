package fr.isika.cda.javaee.entity.subscription;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Subscription offer of a fitness space.
 * 
 * @author Alex
 *
 */
@Entity
public class Subscription {

	@Id
	@GeneratedValue
	private Long subscriptionId;
	private Long memberId;
	private String subscriptionName;
	private String description;
	private String engagement;
	private String promotion;

	@OneToOne
	private Price price;

//******************************************************
	public Subscription() {
	}

	public Subscription(Boolean isforViewModel) {
		this.price = new Price();
	}

//******************************************************
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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

}
