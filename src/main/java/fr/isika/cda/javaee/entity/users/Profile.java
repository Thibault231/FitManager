package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Profile {
	@Id
	@GeneratedValue
	private Long profileId;
	
//	@OneToOne
//	private Civility civility;
//	@OneToOne
//	private Adress adress;
//	@OneToOne
//	private Contact contact;
//	
	
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
