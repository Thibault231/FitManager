package fr.isika.cda.javaee.entity.spaces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Space {

	@Id
	@GeneratedValue
	private Long spaceId;

	@OneToOne
	private Infos infos;

	@OneToOne
	private OnlineShop onlineShop;

	@OneToOne
	private Planning planning;

//**************************************
	/**
	 * Empty constructor for controler.
	 */
	public Space() {

	}

	/**
	 * Constructor for SpaceViewModel.
	 * 
	 * @param isViewModel (:Boolean)
	 */
	public Space(boolean isViewModel) {
		Configuration configuration = new Configuration();
		configuration.setStyle(new Style());
		this.onlineShop = new OnlineShop();
		this.planning = new Planning();
		this.infos = new Infos();
		this.infos.setAdministrative(new Administrative());
		this.infos.setConfiguration(configuration);
	}

//**************************************
	public Long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Long spaceId) {
		this.spaceId = spaceId;
	}

	public Planning getPlanning() {
		return planning;
	}

	public void setPlanning(Planning planning) {
		this.planning = planning;
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

}
