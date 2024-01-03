package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long userId;

	private boolean isActive;

	@OneToOne
	private Account account;

	@OneToOne
	private Profile profile;

//	@OneToOne
//	private Sell sell;
//	
//	@OneToOne
//	private Schedulde schedulde;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", isActive=" + isActive + ", account=" + account + ", profile=" + profile
				+ "]";
	}

}
