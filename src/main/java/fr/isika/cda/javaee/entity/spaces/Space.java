package fr.isika.cda.javaee.entity.spaces;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.entity.subscription.Subscription;
import fr.isika.cda.javaee.entity.users.User;

@Entity
public class Space {

	@Id
	@GeneratedValue
	private Long spaceId;

	@OneToOne
	private Infos infos;

	@OneToMany
	private List<Course> courses;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Subscription> subscriptions;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<User> users;

//*******************************************************************	
	/**
	 * Empty constructor for controller.
	 */
	public Space() {

	}

	/**
	 * Constructor for SpaceViewModel and test.
	 * 
	 * @param isViewModel (:Boolean)
	 */
	public Space(boolean isViewModel) {
		Configuration configuration = new Configuration();
		configuration.setStyle(new Style());
		this.infos = new Infos();
		this.infos.setAdministrative(new Administrative());
		this.infos.setConfiguration(configuration);
		this.users = new ArrayList<User>();
		this.subscriptions = new ArrayList<Subscription>();
	}

//*******************************************************************	
	public Long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Long spaceId) {
		this.spaceId = spaceId;
	}

	public Infos getInfos() {
		return infos;
	}

	public void setInfos(Infos infos) {
		this.infos = infos;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
