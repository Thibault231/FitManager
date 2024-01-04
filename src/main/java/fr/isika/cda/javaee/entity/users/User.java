package fr.isika.cda.javaee.entity.users;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private boolean isActive;

	@OneToOne(cascade = CascadeType.ALL)
	private Account account;

	@OneToOne(cascade = CascadeType.ALL)
	private Profile profile;

//	@OneToOne
//	private Sell sell;
//	
//	@OneToOne
//	private Schedulde schedulde;

//******************************************************	
	/**
	 * Empty constructor for JEE.
	 */
	public User() {
	}

	/**
	 * Constructor for UserControler and UserService
	 * 
	 * @param isActive
	 */
	public User(boolean isActive) {
		this.setActive(isActive);
		this.setProfile(new Profile());
		this.setAccount(new Account());
		this.getProfile().setCivility(new Civility());
		this.getProfile().setAdress(new Address());
		this.getProfile().setContact(new Contact());
	}

//******************************************************	
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
