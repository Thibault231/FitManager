package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Profile {
	
		// private Profil profil;
		// private Sell sell;
		// private Schedulde schedulde
	
	@Id
	@GeneratedValue
	private Long profileId;
//	private Civility civility;
//	private Adress adress;
//	private Contact contact;
	
	
	//Getter and setter
	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@Override
	public String toString() {
		return "Profile [profileId=" + profileId + ", getProfileId()=" + getProfileId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
