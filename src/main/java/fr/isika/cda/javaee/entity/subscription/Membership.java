package fr.isika.cda.javaee.entity.subscription;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Membership {

	@Id
	@GeneratedValue
	private Long memberShipId;

	private Float monthlyPrice;
	private Date startDateEngagement;
	private Date endDateEngagement;

	public Long getMemberShipId() {
		return memberShipId;
	}

	public void setMemberShipId(Long memberShipId) {
		this.memberShipId = memberShipId;
	}

	public Float getMonthlyPrice() {
		return monthlyPrice;
	}

	public void setMonthlyPrice(Float monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}

	public Date getStartDateEngagement() {
		return startDateEngagement;
	}

	public void setStartDateEngagement(Date startDateEngagement) {
		this.startDateEngagement = startDateEngagement;
	}

	public Date getEndDateEngagement() {
		return endDateEngagement;
	}

	public void setEndDateEngagement(Date endDateEngagement) {
		this.endDateEngagement = endDateEngagement;
	}

}
