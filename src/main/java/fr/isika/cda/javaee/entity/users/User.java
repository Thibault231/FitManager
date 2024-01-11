package fr.isika.cda.javaee.entity.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import fr.isika.cda.javaee.entity.relations.Schedulde;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.subscription.Subscription;

/**
 * Person of a fitness space, regardless it's role.
 * 
 * @author Thibault
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private boolean isActive;

	@OneToOne(cascade = CascadeType.ALL)
	@Enumerated(EnumType.STRING)
	private Account account;

	@OneToOne(cascade = CascadeType.ALL)
	private Profile profile;

	@ManyToMany(mappedBy = "users")
	private List<Space> linkedSpaces;

	@OneToOne
	private Schedulde schedulde;

	private Long currentSubScriptionId;

//******************************************************	
	/**
	 * Empty constructor for JEE.
	 */
	public User() {
	}

	/**
	 * Constructor for UserControler and UserService
	 * 
	 * @param isActive (: boolean)
	 */
	public User(boolean isActive) {
		this.setActive(isActive);
		this.setAccount(new Account());
		this.getAccount().setAdministrativeDocument(new AdministrativeDocument());
		this.setProfile(new Profile());
		this.getProfile().setCivility(new Civility());
		this.getProfile().setAdress(new Address());
		this.getProfile().setContact(new Contact());
		this.linkedSpaces = new ArrayList<Space>();
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

	public Schedulde getSchedulde() {
		return schedulde;
	}

	public void setSchedulde(Schedulde schedulde) {
		this.schedulde = schedulde;
	}

	public List<Space> getLinkedSpaces() {
		return linkedSpaces;
	}

	public void setLinkedSpaces(List<Space> linkedSpaces) {
		this.linkedSpaces = linkedSpaces;
	}

	public Long getCurrentSubScriptionId() {
		return currentSubScriptionId;
	}

	public void setCurrentSubScriptionId(Long currentSubScriptionId) {
		this.currentSubScriptionId = currentSubScriptionId;
	}

}
