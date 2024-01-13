package fr.isika.cda.javaee.entity.users;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * All personal datas of a user, including Civility, Address and Contact.
 * 
 * @author Alex Thibault
 *
 */
@Entity
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long profileId;

	@OneToOne(cascade = CascadeType.ALL)
	private Civility civility;

	@OneToOne(cascade = CascadeType.ALL)
	private Address adress;

	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;

//*******************************************************************	
	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Civility getCivility() {
		return civility;
	}

	public void setCivility(Civility civility) {
		this.civility = civility;
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
