package fr.isika.cda.javaee.entity.spaces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Space {

	@Id
	@GeneratedValue
	private Long idSubscription;

	private String name;
	private String password;

	@OneToOne
	private Administrative administrative;

	@OneToOne
	private Configuration configuration;

	@OneToOne
	private Infos infos;

	@OneToOne
	private OnlineShop onlineShop;

	@OneToOne
	private Planning planning;

	@OneToOne
	private Style style;

	public Administrative getAdministrative() {
		return administrative;
	}

	public void setAdministrative(Administrative administrative) {
		this.administrative = administrative;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Long getIdSubscription() {
		return idSubscription;
	}

	public void setIdSubscription(Long idSubscription) {
		this.idSubscription = idSubscription;
	}

	public String getName() {
		return name;
	}

	public Planning getPlanning() {
		return planning;
	}

	public void setPlanning(Planning planning) {
		this.planning = planning;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Infos getInfos() {
		return infos;
	}

	public void setInfos(Infos infos) {
		this.infos = infos;
	}

	public OnlineShop getOnlineShop() {
		return onlineShop;
	}

	public void setOnlineShop(OnlineShop onlineShop) {
		this.onlineShop = onlineShop;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
