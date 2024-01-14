package fr.isika.cda.javaee.entity.spaces;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * Bean qui définie une variable java correspondant au paramètre. (Utilisation
 * d'annotations Lombok pour éviter de déclarer explécitement les getter/setter
 */
@Named
@RequestScoped
public class GoToSpaceView {
	private Long no;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

}
